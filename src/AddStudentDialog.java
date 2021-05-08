import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;

public class AddStudentDialog extends JDialog
{
    JDialog addDialog = new JDialog();

    public AddStudentDialog(Frame owner, String title, boolean modal)
    {
        super(owner, title, modal);
//owner.setEnabled(false);
        JPanel addStuPanel = new JPanel(new GridLayout(10, 1));
        addDialog.add(addStuPanel, BorderLayout.CENTER);


        //topPanel
        JPanel tpp = new JPanel(new FlowLayout());
        tpp.add(new JLabel("添加学生"));
        addStuPanel.add(tpp);
        //学位类别
        JPanel degreeP = new JPanel(new FlowLayout());
        degreeP.add(new JLabel("学历："));
        String[] degree = {"本科", "硕士", "博士"};
        JComboBox degreeCB = new JComboBox(degree);
        degreeP.add(degreeCB);
        addStuPanel.add(degreeP);
        //标准信息
        QuickPanelWithLabelAndText c_nameP = new QuickPanelWithLabelAndText("  姓名:");
        addStuPanel.add(c_nameP);
        QuickPanelWithLabelAndText c_idP = new QuickPanelWithLabelAndText("  学号:");
        addStuPanel.add(c_idP);
        QuickPanelWithLabelAndText c_genderP = new QuickPanelWithLabelAndText("  性别:");
        addStuPanel.add(c_genderP);
        QuickPanelWithLabelAndText c_majorP = new QuickPanelWithLabelAndText("  专业班级:");
        addStuPanel.add(c_majorP);
        QuickPanelWithLabelAndText c_dormP = new QuickPanelWithLabelAndText("寝室号:");
        addStuPanel.add(c_dormP);
        QuickPanelWithLabelAndText c_tutorP = new QuickPanelWithLabelAndText("  导师:", "...");
        addStuPanel.add(c_tutorP);
        c_tutorP.setText("不可用", false);
        QuickPanelWithLabelAndText c_labP = new QuickPanelWithLabelAndText("实验室:", "...");
        addStuPanel.add(c_labP);
        c_labP.setText("不可用", false);
        //根据学位选择是否展示tutor和lab
        degreeCB.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (degreeCB.getSelectedItem().equals("本科"))
                {
                    c_tutorP.setText("不可用", false);
                    c_labP.setText("不可用", false);
                }
                if (degreeCB.getSelectedItem().equals("硕士"))
                {
                    c_tutorP.setText("", true);
                    c_labP.setText("不可用", false);
                }
                if (degreeCB.getSelectedItem().equals("博士"))
                {
                    c_tutorP.setText("", true);
                    c_labP.setText("", true);
                }
            }
        });

//        JButton b = new JButton(Integer.toString((int) System.currentTimeMillis()));
//        addDialog.add(b);
//        b.addMouseListener(new MouseAdapter()
//        {
//            @Override
//            public void mouseReleased(MouseEvent e)
//            {
//                super.mouseReleased(e);
//                owner.setEnabled(true);
//                addDialog.dispose();
//            }
//        });
        SimpleMotion.openMotion(addDialog, 300, 500);
    }
}
