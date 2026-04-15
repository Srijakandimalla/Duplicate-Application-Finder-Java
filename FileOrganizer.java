import java.io.File;
import java.util.*;
import javax.swing.JTextArea;

public class FileOrganizer {

    private Map<String, String[]> rules = new HashMap<>();
    private JTextArea outputArea;

    public FileOrganizer(JTextArea outputArea) {
        this.outputArea = outputArea;

        rules.put("Images", new String[]{"jpg", "png", "jpeg"});
        rules.put("Documents", new String[]{"pdf", "pptx", "docx", "txt"});
        rules.put("Music", new String[]{"mp3"});
        rules.put("Videos", new String[]{"mp4"});
    }

    public void organizeFiles(String folderPath) {

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null) {
            outputArea.append("\n⚠ No files found!\n");
            return;
        }

        outputArea.append("\n📂 Organizing files...\n");

        for (File file : files) {

            if (file.isFile()) {

                String ext = getExt(file.getName());

                for (String category : rules.keySet()) {

                    for (String r : rules.get(category)) {

                        if (ext.equalsIgnoreCase(r)) {

                            File dir = new File(folderPath + "\\" + category);

                            if (!dir.exists()) {
                                dir.mkdir();
                                outputArea.append("📁 Created folder: " + category + "\n");
                            }

                            File newFile = new File(dir, file.getName());

                            boolean moved = file.renameTo(newFile);

                            if (moved) {
                                outputArea.append("➡ Moved: " + file.getName() + " → " + category + "\n");
                            } else {
                                outputArea.append("❌ Failed to move: " + file.getName() + "\n");
                            }

                            break;
                        }
                    }
                }
            }
        }

        outputArea.append("\n✅ Organization Completed!\n");
    }

    private String getExt(String name) {
        int i = name.lastIndexOf('.');
        return (i > 0) ? name.substring(i + 1) : "";
    }
}