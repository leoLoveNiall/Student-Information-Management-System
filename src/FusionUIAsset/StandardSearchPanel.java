package FusionUIAsset;

import javax.swing.*;

/**
 * This is a fusion class consists of a JLabel, a JText and a JButton.
 * Extends JPanel.
 *
 * @author Kong Weirui
 * @since 1.4
 */
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
    public String getFilteredText(){
        return t.getText().replace(" ","");
    }
}