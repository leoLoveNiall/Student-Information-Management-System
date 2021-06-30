package FusionUIAsset;

import javax.swing.*;
//这个是标准组件集合，因此不需要私有
public class StandardSearchPanel extends JPanel {
    public JLabel l = new JLabel();
    public JTextField t = new JTextField("", 10);
    public JButton b = new JButton("查询");

    public StandardSearchPanel(String label) {
        l.setText(label);
        add(l);
        add(t);
        add(b);
    }

    StandardSearchPanel(String label, String text) {
        l.setText(label);
        t.setText(text);
        add(l);
        add(t);
        add(b);
    }

    StandardSearchPanel(String label, String text, String bText) {
        l.setText(label);
        t.setText(text);
        b.setText(bText);
        add(l);
        add(t);
        add(b);
    }
}