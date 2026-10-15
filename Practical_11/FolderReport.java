import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
public class FolderReport 
{
    public static void main(String[] args) 
    {
        Path folder = Paths.get("MyFolder");
        Path report = Paths.get("report.txt");
        try 
        {
            Files.createDirectories(folder);
            Files.write(folder.resolve("file1.txt"),"Hello Java".getBytes());
            Files.write(folder.resolve("file2.txt"),"Learning NIO".getBytes());
            BufferedWriter writer = Files.newBufferedWriter(report);
            writer.write("FOLDER FILE REPORT");
            writer.newLine();
            writer.write("------------------");
            writer.newLine();
            Files.walkFileTree(folder, new SimpleFileVisitor<Path>() 
            {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException 
                {
                    writer.write("Name: " + file.getFileName());
                    writer.newLine();
                    writer.write("Size: " + attrs.size() + " bytes");
                    writer.newLine();
                    writer.write("Modified: " + attrs.lastModifiedTime());
                    writer.newLine();
                    writer.newLine();
                    return FileVisitResult.CONTINUE;
                }
            });
            writer.close();
            System.out.println("Report generated successfully.");
            System.out.println("Check report.txt");
        } 
        catch (IOException e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
