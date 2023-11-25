package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 4;

    private final JFrame frame = new JFrame();

    public SimpleGUI(final Controller controller) {
        // Main panel
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        // Upper text field
        final JTextField textField = new JTextField();
        canvas.add(textField, BorderLayout.NORTH);

        // Central text area
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        textArea.setEditable(false); // This text area is output only

        // Panel for the two buttons
        final JPanel buttonsPanel = new JPanel();
        canvas.add(buttonsPanel, BorderLayout.SOUTH);

        final JButton printButton = new JButton("Print");
        buttonsPanel.add(printButton);
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNextStringToPrint(textField.getText());
                controller.printCurrentString();
            }
        });

        final JButton showHistoryButton = new JButton("Show history");
        buttonsPanel.add(showHistoryButton);
        showHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (final String string : controller.getPrintedStringsHistory()) {
                    textArea.append(string + '\n'); // Show one string per line in the textArea
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(canvas);
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);

        frame.setLocationByPlatform(true);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }
}
