package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
        // Main panel
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        // Text area
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        // "Save" button
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);
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

        // Browse panel
        final JPanel browser = new JPanel();
        browser.setLayout(new BorderLayout());
        canvas.add(browser, BorderLayout.NORTH);

        // Field with the selected file path
        final JTextField filePath = new JTextField(controller.getFilePath());
        browser.add(filePath, BorderLayout.CENTER);
        filePath.setEditable(false); // Can't be modified

        // Button to browse on the file system
        final JButton browseButton = new JButton("Browse...");
        browser.add(browseButton, BorderLayout.LINE_END);
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                final int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    controller.setFile(fileChooser.getSelectedFile());
                    filePath.setText(controller.getFilePath());
                } else if (result == JFileChooser.CANCEL_OPTION) {

                } else {
                    JOptionPane.showMessageDialog(frame, result, "An error is occurred",
                            JOptionPane.ERROR_MESSAGE);
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

    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }
}