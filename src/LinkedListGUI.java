import javax.swing.*;
import java.awt.*;

public class LinkedListGUI extends JFrame {
    private LinkedList myList;
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton insertButton, deleteButton, searchButton;
    private JPanel listPanel;

    public LinkedListGUI() {
        myList = new LinkedList(new LinkedListNode(0)); // Create an initial node
        setTitle("Data Structures Visualizer - Linked List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        // Input panel
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(20);
        insertButton = new JButton("Insert");
        insertButton.setPreferredSize(new Dimension(100, 30));
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 30));

        inputPanel.add(new JLabel("Enter value:"));
        inputPanel.add(inputField);
        inputPanel.add(insertButton);
        inputPanel.add(deleteButton);
        inputPanel.add(searchButton);

        // Output area
        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Linked List visualization panel
        listPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawLinkedList(g);
            }
        };
        listPanel.setPreferredSize(new Dimension(600, 200));

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(listPanel, BorderLayout.SOUTH);

        // Add action listeners
        insertButton.addActionListener(e -> insertAction());
        deleteButton.addActionListener(e -> deleteAction());
        searchButton.addActionListener(e -> searchAction());
    }

    private void drawLinkedList(Graphics g) {
        int width = listPanel.getWidth();
        int height = listPanel.getHeight();
        int nodeWidth = 60;
        int nodeHeight = 30;
        int x = 10;
        int y = height / 2 - nodeHeight / 2;

        LinkedListNode current = myList.getStart();
        while (current != null) {
            // Draw node
            g.setColor(Color.BLUE);
            g.fillRect(x, y, nodeWidth, nodeHeight);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(current.getData()), x + 5, y + 20);

            // Draw arrow
            if (current.getNext() != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x + nodeWidth, y + nodeHeight / 2, x + nodeWidth + 20, y + nodeHeight / 2);
                g.drawLine(x + nodeWidth + 20, y + nodeHeight / 2, x + nodeWidth + 15, y + nodeHeight / 2 - 5);
                g.drawLine(x + nodeWidth + 20, y + nodeHeight / 2, x + nodeWidth + 15, y + nodeHeight / 2 + 5);
            }

            x += nodeWidth + 30;
            current = current.getNext();
        }
    }

    private void insertAction() {
        try {
            int value = Integer.parseInt(inputField.getText());
            LinkedListNode newNode = new LinkedListNode(value);
            myList.InsertNode(newNode);
            updateOutput("Inserted: " + value);
            listPanel.repaint();
        } catch (NumberFormatException ex) {
            updateOutput("Please enter a valid integer.");
        }
        inputField.setText("");
    }

    private void deleteAction() {
        try {
            int value = Integer.parseInt(inputField.getText());
            myList.deleteNode(value);
            updateOutput("Deleted: " + value);
            listPanel.repaint();
        } catch (NumberFormatException ex) {
            updateOutput("Please enter a valid integer.");
        }
        inputField.setText("");
    }

    private void searchAction() {
        try {
            int value = Integer.parseInt(inputField.getText());
            LinkedListNode found = myList.searchNode(value);
            if (found != null) {
                updateOutput("Found: " + value);
            } else {
                updateOutput("Not found: " + value);
            }
        } catch (NumberFormatException ex) {
            updateOutput("Please enter a valid integer.");
        }
        inputField.setText("");
    }

    private void updateOutput(String message) {
        outputArea.append(message + "\n");
    }
}