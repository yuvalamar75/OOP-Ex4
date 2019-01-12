package GUI;

import Accessories.SQL;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SQLDisplay extends JFrame {
    TextArea text = new TextArea();
    private BufferedImage stats;

    public SQLDisplay(){

        this.setTitle("Statistcs");
        this.setBounds(100,100,700,240);
       /* try {
            stats = ImageIO.read(new File("stats.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //this.setIconImage(stats);
        this.setVisible(true);
        this.setResizable(false);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        add(mainPanel);
        text.setEditable(false);
        text.setSize(700,240);
        mainPanel.add(text);











    }
    public TextArea getText() { return text; }
    public void setText(TextArea text) { this.text = text; }
}
