package FusionUIAsset;

import javax.swing.*;

/**
 * This is a fusion class.
 * Extends DoublePanel.
 *
 * @author Kong Weirui
 * @since 2.1
 */
public class QuickPanelWithTwoLabels extends DoublePanel {
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();

    public QuickPanelWithTwoLabels(String str) {
        super(2, 1);
        label1.setText(str);
        super.add(label1, label2);
    }

    public QuickPanelWithTwoLabels(String labelText1, String labelText2) {
        super(2, 1);
        label1.setText(labelText1);
        label2.setText(labelText2);
        super.add(label1, label2);
    }

    public QuickPanelWithTwoLabels(String labelText1, Integer age) {
        super(2, 1);
        label1.setText(labelText1);
        label2.setText(age.toString());
    }

    public void setSecondLabel(String str) {
        label2.setText(str);
        super.add(label1, label2);
    }

    public void setSecondLabelText(String str) {
        label2.setText(str);
    }

}
