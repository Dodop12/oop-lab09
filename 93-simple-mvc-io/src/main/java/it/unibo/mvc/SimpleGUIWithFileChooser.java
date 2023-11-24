package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 4;

    private final JFrame frame = new JFrame();

    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);

        final JPanel browser = new JPanel();
        browser.setLayout(new BorderLayout());
        canvas.add(browser, BorderLayout.NORTH);

        final JTextField browseField = new JTextField();
        browser.add(browseField, BorderLayout.CENTER);
        browseField.setEditable(false);

        final JButton browseButton = new JButton("Browse...");
        browser.add(browseButton, BorderLayout.LINE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(canvas);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.saveString(textArea.getText());
                    System.out.println("Successfully saved on " + controller.getFilePath());
                    JOptionPane.showMessageDialog(frame, "Successfully saved on " + controller.getFilePath());
                } catch (final IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error occurred while trying to save",
                            JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);

        frame.setLocationByPlatform(true);

        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }
}