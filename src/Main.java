import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileSystemView;

public class Main extends JFrame {
    private JButton startButton, quitButton;
    private JTextArea textArea;
    private JLabel titleLabel;
    public Main() {
        setTitle("Recursive Lister");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        titleLabel = new JLabel("Recursive Directory Lister");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    listFiles(selectedFile);
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    private void listFiles(File directory) {
        textArea.setText("");
        listFilesRecursive(directory);
    }
    private void listFilesRecursive(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFilesRecursive(file);
                } else {
                    textArea.append(file.getAbsolutePath() + "\n");
                }
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main lister = new Main();
                lister.setVisible(true);
            }
        });
    }
}