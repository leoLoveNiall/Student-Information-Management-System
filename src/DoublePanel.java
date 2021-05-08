import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//原本是打算建一个包含两个控件的panel，不过后来多重panel越来越多，就都写在这里了

class StandardSearchPanel extends JPanel
{
    JLabel l = new JLabel();
    JTextField t = new JTextField("", 10);
    JButton b = new JButton("查询");

    StandardSearchPanel(String label)
    {
        l.setText(label);
        add(l);
        add(t);
        add(b);
    }

    StandardSearchPanel(String label, String text)
    {
        l.setText(label);
        t.setText(text);
        add(l);
        add(t);
        add(b);
    }

    StandardSearchPanel(String label, String text, String bText)
    {
        l.setText(label);
        t.setText(text);
        b.setText(bText);
        add(l);
        add(t);
        add(b);
    }
}

public class DoublePanel extends JPanel
{
    DoublePanel()
    {
    }

    DoublePanel(int row, int col)
    {
        setLayout(new FlowLayout());
    }

//    void setDefaultSize()
//    {
//        this.setSize(10,20);
//    }

    void add(JLabel l, JTextField t)
    {
        super.add(l);
        super.add(t);
    }

    void add(JLabel l, JPasswordField p)
    {
        super.add(l);
        super.add(p);
    }

    void add(JLabel l1, JLabel l2)
    {
        super.add(l1);
        super.add(l2);
    }

}

class QuickPanelWithLabelAndText extends DoublePanel
{
    JLabel label = new JLabel("");
    JTextField text = new JTextField("", 10);

    QuickPanelWithLabelAndText(String labelText)
    {
        super(2, 1);
        label.setText(labelText);
        super.add(label, text);
    }

    QuickPanelWithLabelAndText(String labelText, String textAreaText)
    {
        super(2, 1);
        label.setText(labelText);
        text.setText(textAreaText);
        super.add(label, text);
    }

    String getText()
    {
        if (text.getText().length() < 1) return "0";
        return text.getText();
    }
    void setText(String t)
    {
        text.setText(t);
    }
    void setText(String t, boolean b)
    {
        text.setText(t);
        text.setEnabled(b);
    }

}

class QuickPanelWithTwoLabels extends DoublePanel
{

    QuickPanelWithTwoLabels()
    {

    }

    private JLabel label1 = new JLabel("");
    private JLabel label2 = new JLabel("");


    QuickPanelWithTwoLabels(String str)
    {
        super(2, 1);
        label1.setText(str);
        super.add(label1, label2);
    }

    QuickPanelWithTwoLabels(String labelText1, String labelText2)
    {
        super(2, 1);
        label1.setText(labelText1);
        label2.setText(labelText2);
        super.add(label1, label2);
    }

    QuickPanelWithTwoLabels(String labelText1, Integer age)
    {
        super(2, 1);
        label1.setText(labelText1);
        label2.setText(age.toString());

    }

    void setSecondLabel(String str)
    {
        label2.setText(str);
        super.add(label1, label2);
    }
    void setSecondLabelText(String str)
    {
        label2.setText(str);
    }

}

class MyCheckBox extends JCheckBox
{
    MyCheckBox(String s, boolean selected, boolean enabled)
    {
        //设定一个初始化的操作
        super(s, selected);
        super.setEnabled(enabled);
    }

    void setMyCheckBoxStatus(boolean selected, boolean enabled)
    {
        super.setSelected(selected);
        super.setEnabled(enabled);
    }

    static void quicklySetOneCheckBoxSelected(MyCheckBox c1, MyCheckBox c2, MyCheckBox c3, Student s)
    {
        c1.setSelected(false);
        c2.setSelected(false);
        c3.setSelected(false);
        c1.setEnabled(false);
        c2.setEnabled(false);
        c3.setEnabled(false);
        switch (s.getTag())
        {
            case "BA":  makeMyCheckBoxAbleButNotUsable(c1); break;
            case "MA":  makeMyCheckBoxAbleButNotUsable(c2); break;
            case "DO":  makeMyCheckBoxAbleButNotUsable(c3); break;
        }
       ;
    }


    static void makeMyCheckBoxAbleButNotUsable(MyCheckBox supreme)
    {
        supreme.setEnabled(true);
        supreme.setSelected(true);
        supreme.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                super.mouseReleased(e);
                supreme.setSelected(true);
            }
        });
    }
}



