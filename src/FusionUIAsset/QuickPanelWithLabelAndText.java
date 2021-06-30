package FusionUIAsset;

import javax.swing.*;
public class QuickPanelWithLabelAndText extends DoublePanel {
    public JLabel label = new JLabel("");
    public JTextField text = new JTextField("", 10);//错误动画必要操作

    public QuickPanelWithLabelAndText(String labelText) {
        super(2, 1);
        label.setText(labelText);
        super.add(label, text);
    }

    public QuickPanelWithLabelAndText(String labelText, String textAreaText) {
        super(2, 1);
        label.setText(labelText);
        text.setText(textAreaText);
        super.add(label, text);
    }

    public String getText() {
        if (text.getText().length() < 1) return "0";
        return text.getText();
    }

    public void setText(String t) {
        text.setText(t);
    }

    public void setText(String t, boolean b) {
        text.setText(t);
        text.setEnabled(b);
    }

}
