import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

//原本是打算建一个包含两个控件的panel，不过后来多重panel越来越多，就都写在这里了

class StandardSearchPanel extends JPanel {
    JLabel l = new JLabel();
    JTextField t = new JTextField("", 10);
    JButton b = new JButton("查询");

    StandardSearchPanel(String label) {
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

public class DoublePanel extends JPanel {
    DoublePanel() {
    }

    DoublePanel(int row, int col) {
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

class QuickPanelWithLabelAndText extends DoublePanel {
    JLabel label = new JLabel("");
    JTextField text = new JTextField("", 10);

    QuickPanelWithLabelAndText(String labelText) {
        super(2, 1);
        label.setText(labelText);
        super.add(label, text);
    }

    QuickPanelWithLabelAndText(String labelText, String textAreaText) {
        super(2, 1);
        label.setText(labelText);
        text.setText(textAreaText);
        super.add(label, text);
    }

    String getText() {
        if (text.getText().length() < 1) return "0";
        return text.getText();
    }

    void setText(String t) {
        text.setText(t);
    }

    void setText(String t, boolean b) {
        text.setText(t);
        text.setEnabled(b);
    }

}

class QuickPanelWithTwoLabels extends DoublePanel {


    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();


    QuickPanelWithTwoLabels(String str) {
        super(2, 1);
        label1.setText(str);
        super.add(label1, label2);
    }

    QuickPanelWithTwoLabels(String labelText1, String labelText2) {
        super(2, 1);
        label1.setText(labelText1);
        label2.setText(labelText2);
        super.add(label1, label2);
    }

    QuickPanelWithTwoLabels(String labelText1, Integer age) {
        super(2, 1);
        label1.setText(labelText1);
        label2.setText(age.toString());
    }

    void setSecondLabel(String str) {
        label2.setText(str);
        super.add(label1, label2);
    }

    void setSecondLabelText(String str) {
        label2.setText(str);
    }

}

class MyCheckBox extends JCheckBox {
    MyCheckBox(String s, boolean selected, boolean enabled) {
        //设定一个初始化的操作
        super(s, selected);
        super.setEnabled(enabled);
    }

    void setMyCheckBoxStatus(boolean selected, boolean enabled) {
        super.setSelected(selected);
        super.setEnabled(enabled);
    }

    static void quicklySetOneCheckBoxSelected(MyCheckBox c1, MyCheckBox c2, MyCheckBox c3, Student s) {
        c1.setSelected(false);
        c2.setSelected(false);
        c3.setSelected(false);
        c1.setEnabled(false);
        c2.setEnabled(false);
        c3.setEnabled(false);
        switch (s.getTag()) {
            case "BA" -> makeMyCheckBoxAbleButNotUsable(c1);
            case "MA" -> makeMyCheckBoxAbleButNotUsable(c2);
            case "DO" -> makeMyCheckBoxAbleButNotUsable(c3);
        }

    }


    static void makeMyCheckBoxAbleButNotUsable(MyCheckBox supreme) {
        supreme.setEnabled(true);
        supreme.setSelected(true);
        supreme.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                supreme.setSelected(true);
            }
        });
    }
}

class TemporaryDialog {
    JDialog dialog = new JDialog();
    int suspendTime = 1000;
    int width = 200, height = 100;
    boolean withOpeningMotion = true;
    boolean isAboutToExit = false;
    boolean showBar = true;

    TemporaryDialog(String headline, int height, int width, int suspendTime, boolean withOpeningMotion, boolean withBar, Window f) {
        this.height = height;
        this.width = width;
        this.suspendTime = suspendTime;
        this.withOpeningMotion = withOpeningMotion;
        this.showBar = withBar;
        //考虑到简化代码的同时并保持简便的实现
        showDialog(headline, f);
    }

    TemporaryDialog(String headline, int height, int width, int suspendTime, Window f, boolean showBar) {
        this.height = height;
        this.width = width;
        this.suspendTime = suspendTime;
        this.showBar = showBar;
        //考虑到简化代码的同时并保持简便的实现
        showDialog(headline, f);
    }

    TemporaryDialog(String headline, int height, int width, Window f) {
        this.height = height;
        this.width = width;
        //考虑到简化代码的同时并保持简便的实现
        showDialog(headline, f);
    }

    TemporaryDialog(String headline, int suspendTime, Window f) {
        this.suspendTime = suspendTime;
        showDialog(headline, f);
        //考虑到简化代码的同时并保持简便的实现
    }

    TemporaryDialog(String headline, Window f) {
        dialog.setAlwaysOnTop(true);
        dialog.setUndecorated(showBar);
        showDialog(headline, f);
    }

    void showDialog(String headline, Window f) {
        //创建窗口
        dialog.setUndecorated(!showBar);
        var panel = new JPanel();
        dialog.add(panel);
        panel.add(new JLabel(headline));
        dialog.setSize(width, height);
        SimpleMotion.centerize(dialog, f);
        if (withOpeningMotion) {
            SimpleMotion.openMotion(dialog, width, height, f);
        } else {
            dialog.setVisible(true);
        }

        //销毁窗口
        new Thread(() -> {
            SimpleMotion.sleep(suspendTime);
            isAboutToExit = true;
            SimpleMotion.exitMotion(dialog,MainWindow.MAIN_WINDOW);
        }).start();
    }

    static void showLoadingCircleDialog(String infoText, int height, int width, boolean withOpeningMotion, Window f) {
        var loadDialog = new TemporaryDialog("", height + 30, width + 30, (int) (Math.random() * 10000) % 1000 + 2000, withOpeningMotion, false, MainWindow.MAIN_WINDOW);
        var loadPanel = new LoadingPanel();
        loadDialog.dialog.add(loadPanel);
        loadPanel.setBackground(Color.WHITE);
        loadPanel.show();
        SimpleMotion.centerize(loadDialog.dialog,MainWindow.MAIN_WINDOW);
        new Thread(() -> {
            while (true) {
                SimpleMotion.sleep(50);//降低占用率
                if (loadDialog.isAboutToExit) break;
            }
            while (loadPanel.miniCounter < 200) {
                loadPanel.miniCounter += 10;
                SimpleMotion.sleep(40);
            }
        }).start();
    }

}

