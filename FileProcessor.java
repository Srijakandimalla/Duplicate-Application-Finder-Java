import java.io.File;
import java.util.*;

public class FileOrganizer {

    private Map<String, String[]> rules = new HashMap<>();

    public FileOrganizer() {

        rules.put("Images", new String[]{"jpg", "png"});
        rules.put("Documents", new String[]{"pdf", "pptx", "txt"});
        rules.put("Music", new String[]{"mp3"});
    }

    public void organizeFiles(String folderPath) {

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null) return;

        for (File file : files) {

            if (file.isFile()) {

                String ext = getExtension(file.getName());

                for (String category : rules.keySet()) {

                    for (String ruleExt : rules.get(category)) {

                        if (ext.equalsIgnoreCase(ruleExt)) {

                            File newDir = new File(folderPath + "\\" + category);

                            if (!newDir.exists()) newDir.mkdir();

                            file.renameTo(new File(newDir, file.getName()));

                            break;
                        }
                    }
                }
            }
        }
    }

    private String getExtension(String name) {
        int i = name.lastIndexOf('.');
        return (i > 0) ? name.substring(i + 1) : "";
    }
}