import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SortingAppSplitPane extends JFrame {

    private JTextField inputField;
    private JTextArea outputArea;
    private JButton bubbleSortButton, insertionSortButton, clearButton;

    public SortingAppSplitPane() {
        setTitle("Sorting Application - Responsive 50:50 Layout");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(600, 400)); // Minimum window size

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(63, 81, 181)); // Indigo
        JLabel headerLabel = new JLabel("Aplikasi Pengurutan Nilai");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Input Area
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBackground(new Color(236, 239, 241)); // Light Grey
        JLabel inputLabel = new JLabel("Masukkan daftar nilai (pisahkan dengan koma):");
        inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(inputLabel, BorderLayout.NORTH);

        inputField = new JTextField();
        inputField.setToolTipText("Contoh: 10,20,30. Pisahkan nilai dengan koma.");
        inputPanel.add(inputField, BorderLayout.CENTER);

        // Output Area
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBackground(Color.WHITE);
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBorder(BorderFactory.createTitledBorder("Hasil pengurutan:"));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        // Split Pane for 50:50 Layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, outputPanel);
        splitPane.setDividerLocation(0.5); // 50:50 ratio
        splitPane.setResizeWeight(0.5); // Balance resizing
        add(splitPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(new Color(144, 164, 174)); // Grey Blue
        bubbleSortButton = new JButton("Bubble Sort");
        insertionSortButton = new JButton("Insertion Sort");
        clearButton = new JButton("Clear");

        setButtonStyle(bubbleSortButton, new Color(0, 150, 136)); // Teal
        setButtonStyle(insertionSortButton, new Color(33, 150, 243)); // Blue
        setButtonStyle(clearButton, new Color(244, 67, 54)); // Red

        buttonPanel.add(bubbleSortButton);
        buttonPanel.add(insertionSortButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortValues("bubble");
            }
        });

        insertionSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortValues("insertion");
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                outputArea.setText("");
            }
        });
    }

    private void setButtonStyle(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void sortValues(String method) {
        String input = inputField.getText();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Tidak ada nilai yang dimasukkan. Harap masukkan daftar nilai terlebih dahulu!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String[] inputArray = input.split(",");
            int[] values = Arrays.stream(inputArray).mapToInt(Integer::parseInt).toArray();

            if (method.equals("bubble")) {
                bubbleSort(values);
                outputArea.setText("Hasil Bubble Sort: " + Arrays.toString(values));
            } else if (method.equals("insertion")) {
                insertionSort(values);
                outputArea.setText("Hasil Insertion Sort: " + Arrays.toString(values));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Masukkan hanya angka yang valid, dipisahkan dengan koma!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingAppSplitPane app = new SortingAppSplitPane();
            app.setVisible(true);
        });
    }
}
