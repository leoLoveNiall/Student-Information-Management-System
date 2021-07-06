package Window;

import DataClassAsset.Grade;
import FusionUIAsset.CompoundJFrame;
import FusionUIAsset.QuickPanelWithLabelAndText;
import FusionUIAsset.TemporaryDialog;
import MyUtil.MotionUtil;
import MyUtil.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * AddGradeDialog allows user add student grade data voa GUI.
 * Extends none.
 * Less static built-in.
 *
 * @author Kong Weirui
 * @since 3.3
 */
public class AddGradeDialog {
    private static final byte MAXIMUM_COURSE_CREDIT = 20;
    JFrame addGradeDialog = new JFrame();
    JPanel mainPanel;
    QuickPanelWithLabelAndText stuInfo_aP;
    QuickPanelWithLabelAndText courseName_aP;
    QuickPanelWithLabelAndText courseID_aP;
    QuickPanelWithLabelAndText courseCredit_aP;
    QuickPanelWithLabelAndText courseRegG_aP;
    QuickPanelWithLabelAndText courseMidG_aP;
    QuickPanelWithLabelAndText courseFinG_aP;
    QuickPanelWithLabelAndText courseAvg_aP;

    AddGradeDialog() {
        MainWindow.setDefaultClosingMotion(
                addGradeDialog,
                new CompoundJFrame(addGradeDialog, MainWindow.MAIN_WINDOW, MainWindow.MAIN_WINDOW_WIDTH, MainWindow.MAIN_WINDOW_HEIGHT),
                MainWindow.IN_WARD,
                false
        );
        addGradeDialog.setTitle("录入成绩");
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(9, 1));
        addGradeDialog.add(mainPanel);
        stuInfo_aP = new QuickPanelWithLabelAndText("操作对象", MainWindow.getCurrentStudent().getName());
        stuInfo_aP.setTextEnabled(false);
        mainPanel.add(stuInfo_aP);
        courseName_aP = new QuickPanelWithLabelAndText("课程名称:", "...");
        mainPanel.add(courseName_aP);
        courseID_aP = new QuickPanelWithLabelAndText("课程代码:", "...");
        mainPanel.add(courseID_aP);
        courseCredit_aP = new QuickPanelWithLabelAndText("课程学分:", "0");
        mainPanel.add(courseCredit_aP);
        courseRegG_aP = new QuickPanelWithLabelAndText("平时成绩:", "0");
        mainPanel.add(courseRegG_aP);
        courseMidG_aP = new QuickPanelWithLabelAndText("期中成绩:", "0");
        mainPanel.add(courseMidG_aP);
        courseFinG_aP = new QuickPanelWithLabelAndText("期末成绩:", "0");
        mainPanel.add(courseFinG_aP);
        courseAvg_aP = new QuickPanelWithLabelAndText("综合成绩:", "0");
        mainPanel.add(courseAvg_aP);
        courseAvg_aP.setTextEnabled(false);//自动计算，不能手动输入
        //添加自动计算成绩脚本和有效性验证脚本
        {
            courseCredit_aP.text.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    try {
                        StringUtil.verifyGradeEditLegit(courseCredit_aP, MAXIMUM_COURSE_CREDIT);
                    } catch (InterruptedException | AWTException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });
            courseRegG_aP.text.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    try {
                        if (StringUtil.verifyGradeEditLegit(courseRegG_aP)) {
                            courseAvg_aP.text.setText(String.valueOf(MainWindow.calculateAvgGrade(Integer.parseInt(courseRegG_aP.getFilteredText()),
                                    Integer.parseInt(courseMidG_aP.getFilteredText()), Integer.parseInt(courseFinG_aP.getFilteredText()))));
                        } else courseAvg_aP.setText("...");
                    } catch (InterruptedException | AWTException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });
            courseMidG_aP.text.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    try {
                        if (StringUtil.verifyGradeEditLegit(courseMidG_aP)) {
                            courseAvg_aP.text.setText(String.valueOf(MainWindow.calculateAvgGrade(Integer.parseInt(courseRegG_aP.getFilteredText()),
                                    Integer.parseInt(courseMidG_aP.getFilteredText()), Integer.parseInt(courseFinG_aP.getFilteredText()))));

                        } else courseAvg_aP.setText("...");
                    } catch (InterruptedException | AWTException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });
            courseFinG_aP.text.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    try {
                        if (StringUtil.verifyGradeEditLegit(courseFinG_aP)) {
                            courseAvg_aP.text.setText(String.valueOf(MainWindow.calculateAvgGrade(Integer.parseInt(courseRegG_aP.getFilteredText()),
                                    Integer.parseInt(courseMidG_aP.getFilteredText()), Integer.parseInt(courseFinG_aP.getFilteredText()))));

                        } else courseAvg_aP.setText("...");
                    } catch (InterruptedException | AWTException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });

        }

        var addConfirmButton = new JButton("确认录入");
        mainPanel.add(addConfirmButton);


        addGradeDialog.setResizable(false);
        MainWindow.hide_MAIN_WINDOW();
        MotionUtil.openMotion(addGradeDialog, 300, 480, MainWindow.MAIN_WINDOW);

        //添加成绩按钮监听
        addConfirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (ifCurriculumIsAlreadyExisted()) {
                    new TemporaryDialog("该科目ID已存在！\n请重试", addGradeDialog);
                } else {
                    MainWindow.getCurrentStudent().gradeArrayList.add(new Grade(
                            MainWindow.getCurrentStudent().getID(),
                            courseName_aP.getFilteredText(),
                            courseID_aP.getFilteredText(),
                            Byte.parseByte(courseCredit_aP.getFilteredText()),
                            Integer.parseInt(courseRegG_aP.getFilteredText()),
                            Integer.parseInt(courseMidG_aP.getFilteredText()),
                            Integer.parseInt(courseFinG_aP.getFilteredText()))
                    );
                    new TemporaryDialog("录入成功", 800, addGradeDialog);
                    new Thread(() -> {
                        MotionUtil.exitMotion(addGradeDialog, null);
                        MainWindow.show_MAIN_WINDOW(addGradeDialog);
                    }).start();

                }
            }
        });
    }

    /**
     * 验证科目号是否存在
     * @return 返回验证结果
     */
    public boolean ifCurriculumIsAlreadyExisted() {
        for (Grade g : MainWindow.getCurrentStudent().gradeArrayList) {
            if (g.getCourseID().equals(courseID_aP.getFilteredText())) {
                return true;
            }
        }
        return false;
    }
}
