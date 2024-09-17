import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DataStructuresGUI extends JFrame {
    private Stack myStack;
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton addButton, popButton, peekButton;
    private JPanel stackPanel;
    private Timer animationTimer;
    private int animationStep = 0;

    public DataStructuresGUI() {
        myStack = new Stack(5); // Create a stack with size 10
        setTitle("Data Structures Visualizer - Stack");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        // Input panel
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(20);
        addButton = new JButton("Push");
        addButton.setPreferredSize(new Dimension(75, 30));
        popButton = new JButton("Pop");
        popButton.setPreferredSize(new Dimension(75, 30));
        peekButton = new JButton("Peek");
        peekButton.setPreferredSize(new Dimension(75, 30));

        inputPanel.add(new JLabel("Enter value:"));
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        inputPanel.add(popButton);
        inputPanel.add(peekButton);

        // Output area
        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Stack visualization panel
        stackPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawStack(g);
            }
        };
        stackPanel.setPreferredSize(new Dimension(200, 300));

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(stackPanel, BorderLayout.EAST);

        // Add action listeners
        addButton.addActionListener(e -> pushAction());
        popButton.addActionListener(e -> popAction());
        peekButton.addActionListener(e -> peekAction());
    }

    private void drawStack(Graphics g) {
        int width = stackPanel.getWidth();
        int height = stackPanel.getHeight();
        int elementHeight = height / 10;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        for (int i = 0; i < myStack.getSize(); i++) {
            int y = height - (i + 1) * elementHeight;
            g.setColor(Color.BLUE);
            g.fillRect(1, y, width - 2, elementHeight - 1);
            g.setColor(Color.WHITE);
            g.drawString("Element " + (i + 1), 10, y + elementHeight / 2);
        }

        if (animationStep > 0) {
            g.setColor(Color.RED);
            g.fillRect(1, height - (myStack.getSize() + 1) * elementHeight + animationStep,
                    width - 2, elementHeight - 1);
        }
    }

    private void pushAction() {
        try {
            int value = Integer.parseInt(inputField.getText());
            if(!myStack.isFull()) {
                animateInsertion();
                myStack.push(value);
                updateOutput("pushing " + value + " to the stack");
            }
            else{
                updateOutput("the stack is full");
            }
        } catch (NumberFormatException ex) {
            updateOutput("Please enter a valid integer.");
        }
        inputField.setText("");
    }

    private void popAction() {
        if (!myStack.isEmpty()) {
            animateRemoval();
            int value = myStack.pop();
            updateOutput("Popped: " + value);
        } else {
            updateOutput("Stack is empty.");
        }
    }

    private void peekAction() {
        if (!myStack.isEmpty()) {
            int value = myStack.topOfStack();
            updateOutput("Top of stack: " + value);
        } else {
            updateOutput("Stack is empty.");
        }
    }

    private void updateOutput(String message) {
        outputArea.append(message + "\n");
    }

    private void animateInsertion() {
        animationStep = 20;
        animationTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationStep -= 2;
                if (animationStep <= 0) {
                    ((Timer)e.getSource()).stop();
                }
                stackPanel.repaint();
            }
        });
        animationTimer.start();
    }

    private void animateRemoval() {
        animationStep = 0;
        animationTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationStep += 2;
                if (animationStep >= 20) {
                    ((Timer)e.getSource()).stop();
                }
                stackPanel.repaint();
            }
        });
        animationTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataStructuresGUI());
    }
}