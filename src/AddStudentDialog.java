
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddStudentDialog{
    //最开始的时候都把swing控件写在构造函数里了，后来又全部移出来，方便操作
    //最开始的时候都把swing控件写在构造函数里了，后来又全部移出来，方便操作
    //最开始的时候都把swing控件写在构造函数里了，后来又全部移出来，方便操作
    final static String[] degree = {"本科", "硕士", "博士"};
    final static String[] gender = {"顺性男 Cis Man", "顺性女 Cis Woman", "双性人 Bigender", "无性别 Agender",
            "跨性别 Trans", "流性人 Gender Fluid", "非常规性别 Nonconforming", "酷儿性别 Genderqueer",
            "间性人 Intersex", "泛性别 Pangender", "不确定"};
    static JComboBox<String> degreeCB = new JComboBox<>(degree), genderCB = new JComboBox<>(gender);
    static Student add;
    static QuickPanelWithLabelAndText a_IDP;
    static JPanel a_genderP;
    static QuickPanelWithLabelAndText a_nameP;
    static QuickPanelWithLabelAndText a_majorP;
    static QuickPanelWithLabelAndText a_tutorP;
    static QuickPanelWithLabelAndText a_dormP;
    static QuickPanelWithLabelAndText a_labP;
    static JPanel addStuPanel;
    static JDialog addDialog;
    static JButton addBtn;
    static JPanel tpp;
    static JPanel degreeP;

     AddStudentDialog() {
//owner.setEnabled(false);

        addStuPanel = new JPanel(new GridLayout(10, 1));
        addDialog = new JDialog();
        addDialog.setAlwaysOnTop(true);
        addDialog.add(addStuPanel, BorderLayout.CENTER);


        //topPanel
        tpp = new JPanel(new FlowLayout());
        tpp.add(new JLabel("添加学生"));
        addStuPanel.add(tpp);
        //学位类别
        degreeP = new JPanel(new FlowLayout());
        degreeP.add(new JLabel("学历："));
        degreeP.add(degreeCB);
        addStuPanel.add(degreeP);
        //标准信息
        a_nameP = new QuickPanelWithLabelAndText("  姓名:");
        addStuPanel.add(a_nameP);
        a_IDP = new QuickPanelWithLabelAndText("  学号:");
        addStuPanel.add(a_IDP);
        a_genderP = new JPanel();
        a_genderP.setLayout(new FlowLayout());
        a_genderP.add(new JLabel("性别"));

        a_genderP.add(genderCB);
        addStuPanel.add(a_genderP);

        a_majorP = new QuickPanelWithLabelAndText("  专业班级:");
        addStuPanel.add(a_majorP);
        a_dormP = new QuickPanelWithLabelAndText("寝室号:");
        addStuPanel.add(a_dormP);
        a_tutorP = new QuickPanelWithLabelAndText("  导师:", "...");
        addStuPanel.add(a_tutorP);
        a_tutorP.setText("不可用", false);
        a_labP = new QuickPanelWithLabelAndText("实验室:", "...");
        addStuPanel.add(a_labP);
        a_labP.setText("不可用", false);
        addBtn = new JButton("确认添加");
        addStuPanel.add(addBtn);


        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (isAllFilled() && !MainWindow.ifIDExists(a_IDP.text.getText())) {
                    switch (degree[degreeCB.getSelectedIndex()]) {
                        case "本科" -> add = new Bachelor(a_nameP.text.getText(), a_IDP.text.getText(),
                                gender[genderCB.getSelectedIndex()], a_majorP.text.getText(),
                                a_dormP.text.getText());
                        case "硕士" -> add = new Master(a_nameP.text.getText(), a_IDP.text.getText(),
                                gender[genderCB.getSelectedIndex()], a_majorP.text.getText(),
                                a_dormP.text.getText(), a_tutorP.text.getText());
                        case "博士" -> add = new Doctor(a_nameP.text.getText(), a_IDP.text.getText(),
                                gender[genderCB.getSelectedIndex()], a_majorP.text.getText(),
                                a_dormP.text.getText(), a_tutorP.text.getText(), a_labP.text.getText());
                    }
                    MainWindow.studentArrayList.add(add);
                    SimpleMotion.exitMotion(addDialog);
                    MainWindow.switchStu(add);
                } else {
                    if (isAllFilled() && MainWindow.ifIDExists(a_IDP.text.getText())) {
                        new TemporaryDialog("学号已存在！", 100, 200, addDialog);
                    }

                }
            }
        });
        //根据学位选择是否展示tutor和lab
        degreeCB.addActionListener(e -> {
            if (degreeCB.getSelectedItem() == null) {
                new TemporaryDialog("还为选中一项！", addDialog);
            }
            if (degreeCB.getSelectedItem().equals("本科")) {
                a_tutorP.setText("不可用", false);
                a_labP.setText("不可用", false);
            }
            if (degreeCB.getSelectedItem().equals("硕士")) {
                a_tutorP.setText("", true);
                a_labP.setText("不可用", false);
            }
            if (degreeCB.getSelectedItem().equals("博士")) {
                a_tutorP.setText("", true);
                a_labP.setText("", true);
            }

        });

        SimpleMotion.openMotion(addDialog, 300, 500,MainWindow.MAIN_WINDOW);
    }

    public boolean isAllFilled() {

        if (a_nameP.text.getText().equals("")) {
            SimpleMotion.displayErrorInfo(a_nameP.text, "姓名不能为空！");
            return false;
        }
        if (a_IDP.text.getText().equals("")) {
            SimpleMotion.displayErrorInfo(a_IDP.text, "学号不能为空！");
            return false;
        }
        if (a_majorP.text.getText().equals("")) {
            SimpleMotion.displayErrorInfo(a_majorP.text, "专业班级不能为空！");
            return false;
        }
        if (a_dormP.text.getText().equals("")) {
            SimpleMotion.displayErrorInfo(a_dormP.text, "寝室号不能为空！");
            return false;
        }
        if (a_tutorP.text.getText().equals("")) {
            SimpleMotion.displayErrorInfo(a_tutorP.text, "导师不能为空！");
            return false;
        }
        if (a_labP.text.getText().equals("")) {
            SimpleMotion.displayErrorInfo(a_labP.text, "实验室不能为空！");
            return false;
        }
        return true;
    }
}
