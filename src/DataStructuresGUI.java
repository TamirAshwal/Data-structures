import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class DataStructuresGUI extends JFrame {
    private Stack myStack;
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton addButton, popButton, peekButton;
    private JPanel stackPanel;
    private Timer animationTimer;
    private int animationStep = 0;
    private boolean isRemoving = false;
    private int removingIndex = -1;
    private int elementHeight;
    private Color[] elementColors;
    private Random random = new Random();


    public DataStructuresGUI() {
        myStack = new Stack(5); // Create a stack with size 5
        elementColors = new Color[5];
        setTitle("Data Structures Visualizer - Stack");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize elementHeight
        elementHeight = 500 / 5;  // Adjust this value based on your preferred height

        initComponents();

        setVisible(true);
    }
    private void initComponents() {
        // Input panel
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(10);
        addButton = createStyledButton("Push", new Color(100, 200, 100));
        popButton = createStyledButton("Pop", new Color(200, 100, 100));
        peekButton = createStyledButton("Peek", new Color(100, 100, 200));

        inputPanel.add(new JLabel("Enter value:"));
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        inputPanel.add(popButton);
        inputPanel.add(peekButton);

        // Output area
        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Stack visualization panel
        stackPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawStack(g);
            }
        };
        stackPanel.setPreferredSize(new Dimension(300, 400));

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(stackPanel, BorderLayout.EAST);

        // Add action listeners
        addButton.addActionListener(e -> pushAction());
        popButton.addActionListener(e -> popAction());
        peekButton.addActionListener(e -> peekAction());
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(80, 30));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    private void drawStack(Graphics g) {
        int width = stackPanel.getWidth();
        int height = stackPanel.getHeight();
        int elementHeight = height / 5;

        g.setColor(new Color(240, 240, 240));
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        for (int i = 0; i < myStack.getSize(); i++) {
            int y = height - (i + 1) * elementHeight;
            if (isRemoving && i == removingIndex) {
                g.setColor(new Color(255, 0, 0, 128));
                g.fillRect(1 + animationStep, y, width - 2, elementHeight - 1);
            } else {
                g.setColor(elementColors[i]);
                g.fillRect(1, y, width - 2, elementHeight - 1);
            }
            g.setColor(Color.BLACK);
            g.drawRect(1, y, width - 2, elementHeight - 1);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            String elementText = "Element " + myStack.getElementAt(i);
            FontMetrics fm = g.getFontMetrics();
            int textX = (width - fm.stringWidth(elementText)) / 2;
            int textY = y + (elementHeight + fm.getAscent() - fm.getDescent()) / 2;
            g.drawString(elementText, textX, textY);
        }

        if (animationStep > 0 && !isRemoving) {
            int y = height - (myStack.getSize()) * elementHeight;
            g.setColor(new Color(0, 255, 0, 128));
            g.fillRect(1, y + animationStep, width - 2, elementHeight - 1);
        }
    }



    private void pushAction() {
        try {
            int value = Integer.parseInt(inputField.getText());
            if (!myStack.isFull()) {
                animateInsertion();
                myStack.push(value);
                elementColors[myStack.getSize() - 1] = getRandomColor();
                updateOutput("Pushing " + value + " to the stack");
            } else {
                updateOutput("The stack is full");
            }
        } catch (NumberFormatException ex) {
            updateOutput("Please enter a valid integer.");
        }
        inputField.setText("");
    }



    private void popAction() {
        if (!myStack.isEmpty()) {
            removingIndex = myStack.getSize() - 1;
            animateRemoval();
            updateOutput("Popped: " + myStack.topOfStack());
            myStack.pop();
        } else {
            updateOutput("Stack is empty.");
        }
    }
    private void peekAction() {
        if (!myStack.isEmpty()) {
            int value = myStack.topOfStack();
            updateOutput("Top of stack: " + value);
            flashTopElement();
        } else {
            updateOutput("Stack is empty.");
        }
    }

    private void updateOutput(String message) {
        outputArea.append(message + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void animateInsertion() {
        isRemoving = false;
        animationStep = elementHeight;
        animationTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationStep -= 2;
                if (animationStep <= 0) {
                    ((Timer) e.getSource()).stop();
                    animationStep = 0;
                }
                stackPanel.repaint();
            }
        });
        animationTimer.start();
    }

    private void animateRemoval() {
        isRemoving = true;
        animationStep = 0;
        animationTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationStep += 2;
                if (animationStep >= stackPanel.getWidth()) {
                    ((Timer) e.getSource()).stop();
                    animationStep = 0;
                    isRemoving = false;
                    removingIndex = -1;
                }
                stackPanel.repaint();
            }
        });
        animationTimer.start();
    }

    private void flashTopElement() {
        if (myStack.isEmpty()) return;

        final Color originalColor = elementColors[myStack.getSize() - 1];
        final Color flashColor = Color.YELLOW;

        Timer flashTimer = new Timer(200, new ActionListener() {
            int flashCount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flashCount % 2 == 0) {
                    elementColors[myStack.getSize() - 1] = flashColor;
                } else {
                    elementColors[myStack.getSize() - 1] = originalColor;
                }
                stackPanel.repaint();
                flashCount++;
                if (flashCount >= 6) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        flashTimer.start();
    }

    private Color getRandomColor() {
        return new Color(random.nextInt(200) + 55, random.nextInt(200) + 55, random.nextInt(200) + 55);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataStructuresGUI());
    }
}