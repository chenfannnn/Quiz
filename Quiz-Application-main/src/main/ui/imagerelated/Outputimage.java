package ui.imagerelated;

import javax.swing.*;

public class Outputimage extends JFrame {
    private JFrame frame = new JFrame();
    ImageIcon pic;

    // MODIFIES: this
    // EFFECTS: adds the image with the given string location in file
    public Outputimage() {
    }

    public void showimage(String x) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setSize(1200,1200);
        frame.setLocationRelativeTo(null);

        pic = new ImageIcon(x);
        JLabel labeled = new JLabel(pic);
        frame.add(labeled);
        frame.setVisible(true);

    }
}
