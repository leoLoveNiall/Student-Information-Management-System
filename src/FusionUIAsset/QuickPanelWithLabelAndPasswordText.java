package FusionUIAsset;

import javax.swing.*;

public class QuickPanelWithLabelAndPasswordText  extends DoublePanel {
    public JLabel label = new JLabel("");
    public JPasswordField text = new JPasswordField("", 10);

    public QuickPanelWithLabelAndPasswordText(String password) {
        super(2, 1);
        label.setText(password);
        super.add(label, text);
    }

    public QuickPanelWithLabelAndPasswordText(String labelText, String password) {
        super(2, 1);
        label.setText(labelText);
        text.setText(password);
        super.add(label, text);
    }

    public String getPassword() {
        if (String.valueOf(text.getPassword()).length() < 1) return "0";
        return String.valueOf(text.getPassword());
    }

    public void setPassword(String t) {
        text.setText(t);
    }
}
