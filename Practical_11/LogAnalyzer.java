import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
public class LogAnalyzer
{
    public static void main(String[] args)
    {
        try
        {
            Files.write(Paths.get("log1.txt"),
                ("Server started\nError found\nUser logged in").getBytes());
            Files.write(Paths.get("log2.txt"),
                ("Connection successful\nError occurred\nSystem running").getBytes());
            String[] files = {"log1.txt", "log2.txt"};
            String keyword = "Error";
            int totalLines = 0;
            int keywordLines = 0;
            for (String file : files)
                {
                Path path = Paths.get(file);
                int fileLines = 0;
                BufferedReader br = Files.newBufferedReader(path);
                String line;
                while ((line = br.readLine()) != null)
                {
                    totalLines++;
                    fileLines++;
                    if (line.contains(keyword))
                    {
                        keywordLines++;
                    }
                }
                br.close();
                BasicFileAttributes attr =Files.readAttributes(path, BasicFileAttributes.class);
                System.out.println("File: " + file);
                System.out.println("Lines: " + fileLines);
                System.out.println("Size: " + attr.size() + " bytes");
                System.out.println();
            }
            System.out.println("Total lines: " + totalLines);
            System.out.println("Lines containing '" + keyword +"': " + keywordLines);
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
