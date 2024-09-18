import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DataStructuresMenuGUI extends JFrame {
    private JButton stackButton;
    private JButton linkedListButton;

    public DataStructuresMenuGUI() {
        setTitle("Data Structures Visualizer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Choose a Data Structure:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, gbc);

        stackButton = new JButton("Stack");
        stackButton.setPreferredSize(new Dimension(150, 40));
        add(stackButton, gbc);

        linkedListButton = new JButton("Linked List");
        linkedListButton.setPreferredSize(new Dimension(150, 40));
        add(linkedListButton, gbc);

        stackButton.addActionListener(e -> openStackGUI());
        linkedListButton.addActionListener(e -> openLinkedListGUI());
    }

    private void openStackGUI() {
        SwingUtilities.invokeLater(() -> new DataStructuresGUI());
    }

    private void openLinkedListGUI() {
        SwingUtilities.invokeLater(() -> new LinkedListGUI());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataStructuresMenuGUI());
    }
}