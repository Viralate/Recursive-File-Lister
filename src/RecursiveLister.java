import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class RecursiveLister {
    public static void main(String[] args) {
        // Create the GUI frame
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Recursive File Lister");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new BorderLayout());

            // Create title label
            JLabel titleLabel = new JLabel("Recursive File Lister", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            frame.add(titleLabel, BorderLayout.NORTH);

            // Create JTextArea with JScrollPane for displaying files
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Create JPanel for buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton startButton = new JButton("Start");
            JButton quitButton = new JButton("Quit");
            buttonPanel.add(startButton);
            buttonPanel.add(quitButton);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // Action listener for the Start button
            startButton.addActionListener(e -> {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedDirectory = fileChooser.getSelectedFile();
                    textArea.setText(""); // Clear the text area
                    listFilesRecursive(selectedDirectory, textArea);
                }
            });

            // Action listener for the Quit button
            quitButton.addActionListener(e -> System.exit(0));

            frame.setVisible(true);
        });
    }

    /**
     * Recursively lists all files in a directory and appends them to the JTextArea.
     *
     * @param directory The directory to list files from.
     * @param textArea  The JTextArea to append file paths to.
     */
    private static void listFilesRecursive(File directory, JTextArea textArea) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    textArea.append("Directory: " + file.getAbsolutePath() + "\n");
                    listFilesRecursive(file, textArea); // Recurse into subdirectories
                } else {
                    textArea.append("File: " + file.getAbsolutePath() + "\n");
                }
            }
        }
    }
}
