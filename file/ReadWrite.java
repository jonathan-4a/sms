package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadWrite {

    // staffs = [["james", "mark", "supervisor"], ["jessica", "smith", "casework"]]
    public List<List<String>> readCSV(String fileName) throws IOException {
        List<List<String>> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                rows.add(Arrays.asList(values));
            }
        }
        return rows;
    }

    public void appendToCSV(String fileName, List<String> row) throws IOException {
        File file = new File(fileName);

        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(String.join(",", row));
            bw.newLine();
        }
    }

    public void writeAll(String fileName, List<List<String>> rows) throws IOException {
        File file = new File(fileName);

        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (List<String> row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
    }
}
