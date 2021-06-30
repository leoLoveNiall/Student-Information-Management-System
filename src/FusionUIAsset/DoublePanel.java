package FusionUIAsset;

import javax.swing.*;
import java.awt.*;

//原本是打算建一个包含两个控件的panel，不过后来多重panel越来越多，就都写在这里了



public class DoublePanel extends JPanel {
    DoublePanel() {
    }

    public DoublePanel(int row, int col) {
        setLayout(new FlowLayout());
    }

//    void setDefaultSize()
//    {
//        this.setSize(10,20);
//    }

    void add(JLabel l, JTextField t) {
        super.add(l);
        super.add(t);
    }

    void add(JLabel l, JPasswordField p) {
        super.add(l);
        super.add(p);
    }

    void add(JLabel l1, JLabel l2) {
        super.add(l1);
        super.add(l2);
    }

}





