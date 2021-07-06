package FusionUIAsset;
import javax.swing.*;
import java.awt.*;

/**
 * This is a fusion class for two syn-unit class.
 * By using DoublePanel to decrease redundant add and set command.
 * @author Kong Weirui
 * @since 1.1
 */

public class DoublePanel extends JPanel {
    DoublePanel() {
    }

    public DoublePanel(int row, int col) {
        setLayout(new FlowLayout());
    }

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





