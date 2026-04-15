import java.io.File;
import java.util.*;
import javax.swing.JTextArea;

public class FileProcessorGUI {

    private HashMap<String, String> fileMap = new HashMap<>();
    private List<File> duplicates = new ArrayList<>();
    private JTextArea outputArea;

    public FileProcessorGUI(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    public void findDuplicates(String folderPath) {

        fileMap.clear();
        duplicates.clear();

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null) return;

        for (File file : files) {

            if (file.isFile()) {

                String hash = HashUtil.getFileHash(file);

                if (fileMap.containsKey(hash)) {

                    outputArea.append("\nDuplicate Found:\n");
                    outputArea.append("✔ Original: " + fileMap.get(hash) + "\n");
                    outputArea.append("❌ Duplicate: " + file.getAbsolutePath() + "\n");

                    duplicates.add(file);

                } else {
                    fileMap.put(hash, file.getAbsolutePath());
                }
            }
        }
    }

    public void deleteDuplicates() {

        int count = 0;

        for (File file : duplicates) {
            if (file.delete()) {
                count++;
                outputArea.append("🗑 Deleted: " + file.getAbsolutePath() + "\n");
            }
        }

        outputArea.append("\n🗑 Deleted " + count + " duplicate files!\n");
    }
}