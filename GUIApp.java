import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIApp {

    private JTextArea outputArea;
    private JTextField pathField;
    private FileProcessorGUI processor;

    public GUIApp() {

        JFrame frame = new JFrame("Duplicate File Finder");
        frame.setSize(800, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 🔷 Title
        JLabel title = new JLabel("Duplicate File Finder", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // 🔷 Top Panel (Path + Browse)
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pathField = new JTextField();

        JButton browseBtn = new JButton("Browse");
        browseBtn.addActionListener(e -> chooseFolder());

        topPanel.add(pathField, BorderLayout.CENTER);
        topPanel.add(browseBtn, BorderLayout.EAST);

        // 🔷 Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea);

        // 🔷 Buttons
        JPanel buttonPanel = new JPanel();

        JButton scanBtn = new JButton("Scan");
        JButton deleteBtn = new JButton("Delete Duplicates");
        JButton organizeBtn = new JButton("Organize Files");

        buttonPanel.add(scanBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(organizeBtn);

        // 🔍 SCAN
        scanBtn.addActionListener(e -> scanFiles());

        // 🗑 DELETE
        deleteBtn.addActionListener(e -> {

            if (processor == null) {
                outputArea.append("\n⚠ Please run scan first!\n");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to delete duplicate files?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                processor.deleteDuplicates();
            } else {
                outputArea.append("\n❌ Delete cancelled\n");
            }
        });

        // 📂 ORGANIZE (UPDATED)
        organizeBtn.addActionListener(e -> {

            String path = pathField.getText();

            if (path.isEmpty()) {
                outputArea.append("\n⚠ Please select folder first!\n");
                return;
            }

            FileOrganizer organizer = new FileOrganizer(outputArea);
            organizer.organizeFiles(path);
        });

        // 🔷 Layout
        frame.add(title, BorderLayout.NORTH);
        frame.add(topPanel, BorderLayout.BEFORE_FIRST_LINE);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // 📁 Choose folder
    private void chooseFolder() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            pathField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    // 🔍 Scan files
    private void scanFiles() {

        String path = pathField.getText();

        if (path.isEmpty()) {
            outputArea.setText("⚠ Please select a folder!\n");
            return;
        }

        outputArea.setText("🔍 Starting scan...\n");

        File folder = new File(path);

        if (!folder.exists()) {
            outputArea.append("❌ Folder not found!\n");
            return;
        }

        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            outputArea.append("⚠ No files found!\n");
            return;
        }

        outputArea.append("📁 Files found: " + files.length + "\n");

        processor = new FileProcessorGUI(outputArea);
        processor.findDuplicates(path);

        outputArea.append("\n✅ Scan Done!\n");
    }

    public static void main(String[] args) {
        new GUIApp();
    }
}