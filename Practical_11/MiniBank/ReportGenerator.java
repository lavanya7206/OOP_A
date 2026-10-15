import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
public class ReportGenerator
{
    public static void generate(Path folder, Path report)
            throws IOException
            {
        long deposits = 0;
        long withdrawals = 0;
        try (BufferedWriter writer = Files.newBufferedWriter(report, StandardCharsets.UTF_8,StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING)) 
        {
            writer.write("END-OF-DAY BANK REPORT");
            writer.newLine();
            try (DirectoryStream<Path> stream =Files.newDirectoryStream(folder)) 
            {
                for (Path file : stream)
                    {
                    if (!Files.isRegularFile(file) || file.equals(report)) 
                    {
                        continue;
                    }
                    try (BufferedReader reader = Files.newBufferedReader(file))
                    {
                        String line;
                        while ((line = reader.readLine()) != null)
                        {
                            String[] parts = line.trim().split("\\s+");
                            if (parts.length != 2)
                            {
                                continue;
                            }
                            try
                            {
                                long amount =Long.parseLong(parts[1]);
                                if (parts[0].equals("DEPOSIT"))
                                {
                                    deposits += amount;
                                }
                                else if (parts[0].equals("WITHDRAW"))
                                {
                                    withdrawals += amount;
                                }
                            }
                            catch (NumberFormatException e)
                            {
                            }
                        }
                    }
                    BasicFileAttributes attr =Files.readAttributes(file,BasicFileAttributes.class);
                    writer.write("File: " + file.getFileName());
                    writer.newLine();
                    writer.write("Size: " + attr.size() + " bytes");
                    writer.newLine();
                    writer.write("Modified: " +attr.lastModifiedTime());
                    writer.newLine();
                    writer.newLine();
                }
            }
            writer.write("Total Deposits: Rs. " + deposits);
            writer.newLine();
            writer.write("Total Withdrawals: Rs. " + withdrawals);
            writer.newLine();
            writer.write("Net Transaction: Rs. " +(deposits - withdrawals));
            writer.newLine();
        }
    }
}
