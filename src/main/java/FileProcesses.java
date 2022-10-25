import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileProcesses {

    private static List<String> fileReader() throws IOException {

        String fileName = "/Users/serda.ucar/Downloads/TextProject/jiraTemplate.txt";

        List<String> response = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            List<String> lines = br.lines().collect(Collectors.toList()); // string ifadeye çevirdi br.lines, onu listeye çevirdi
            response = lines;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private static void fileWireter(List<String> readLines, Path filePath) throws IOException {

        String entryMessage = "**** Hello welcome to the automated bug report generater****";
        String step = "{color#57d9a3}Steps{color}";
        String actualResult = "{color#de350b}Actual Result{color}";
        String expectedResult = "{color#00875a}Expected Result{color}";
        String text = "";
        text = text.concat(entryMessage).concat("\n" + step);
        for (String line : readLines) {

            if (!line.contains("!"))

                text = text.concat("\n").concat(line);
            text = text.replace("!", "");

            if (line.contains("+"))

                text = text.concat("\n").concat(actualResult).concat("\n" + line);
            text = text.replace("+", "");
            if (line.contains("="))

                text = text.concat("\n").concat(expectedResult).concat("\n" + line);
            text = text.replace("=", "");
        }
        Files.write(filePath, text.getBytes(StandardCharsets.UTF_8)); //IO işlemler
        System.out.println("*/*/*/" + text + "/*/*/*/");

    }

    public static void main(String[] args) {
        String filePath = "/Users/serda.ucar/Downloads/TextProject/newJiraTemplate.txt";
        Path path = Paths.get(filePath);

        try {
            List<String> lines = fileReader();
            fileWireter(lines, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



