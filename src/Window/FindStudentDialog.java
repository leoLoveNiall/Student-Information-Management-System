package Window;

import FusionUIAsset.*;
import MyUtil.*;
import DataClassAsset.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FindStudentDialog {
    //名义上是dialog但实际上并不是
    static JFrame findDialog;
    static final int HEIGHT = 350, WIDTH = 450;


    FindStudentDialog(String title){
        findDialog = new JFrame();
        findDialog.setTitle(title);
        //初始化窗体
        var mainPanel = new JPanel();
        findDialog.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        //查询控件
        var searchPanel = new StandardSearchPanel("查询学生：");
        mainPanel.add(searchPanel, BorderLayout.PAGE_START);


        //查询-结果,左侧学生简要信息，右侧切换
        var resultPanel = new JPanel();
        mainPanel.add(resultPanel);
        //resultPanel.setLayout(new GridLayout(1, 2));


        var stuSerInfoPanel = new JPanel();
        stuSerInfoPanel.setLayout(new GridLayout(10,1));
        //重名的人
        resultPanel.add(stuSerInfoPanel);

        //结果

        searchPanel.b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                var switchStuButton = new JButton("切换至该学生");
                MotionUtil.upAndDown_A(findDialog, HEIGHT);
                stuSerInfoPanel.removeAll();
                var findLabel = new JLabel("查询到");
                stuSerInfoPanel.add(findLabel);
                ArrayList<JRadioButton> resultStu = new ArrayList<>();
                var buttonGroup = new ButtonGroup();

                var index = 0;
                super.mouseReleased(e);
                var foundOrNot = false;
                for (Student tmpStu : MainWindow.studentArrayList) {
                    if (tmpStu.getName().equals(searchPanel.t.getText()) || tmpStu.getID().equals(searchPanel.t.getText())) {

                        resultStu.add(new JRadioButton(tmpStu.getName() + "," + tmpStu.getMajor() + "," + tmpStu.getID()));
                        stuSerInfoPanel.add(resultStu.get(index));
                        buttonGroup.add(resultStu.get(index));
                        findDialog.setSize(findDialog.getWidth() + 1, findDialog.getHeight());
                        findDialog.setSize(findDialog.getWidth() - 1, findDialog.getHeight());
                        foundOrNot = true;
                        index++;
                        System.out.println("found:" + tmpStu);
                        findLabel.setText("查询到" + resultStu.size() + "个结果");

                    }
                }
                if (!foundOrNot) {

                    MotionUtil.displayErrorInfo(searchPanel.t, "找不到此学生");
                    findLabel.setText("查询到0个结果");
                    switchStuButton.setVisible(false);

                } else switchStuButton.setVisible(true);

                stuSerInfoPanel.add(switchStuButton);
                switchStuButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String[] s;
                        var groupHasBeenSelected = false;
                        for (var rb : resultStu) {
                            if (rb.isSelected()) {
                                groupHasBeenSelected = true;
                                s = rb.getText().split(",");
                                for (var stu : MainWindow.studentArrayList) {
                                    if (stu.getID().equals(s[2])) {
                                        MotionUtil.exitMotion(findDialog, MainWindow.MAIN_WINDOW);
                                        MainWindow.switchStu(stu);
                                        return;
                                    }
                                }
                            }
                        }
                        if (!groupHasBeenSelected){
                            new TemporaryDialog("请选择学生！",findDialog);
                        }

                    }
                });

                MotionUtil.upAndDown_B(findDialog, HEIGHT);

            }
        });
        MotionUtil.addEscToExist(findDialog, MainWindow.MAIN_WINDOW, MainWindow.IN_WARD);
        MotionUtil.openMotion(findDialog, WIDTH, HEIGHT, MainWindow.MAIN_WINDOW);
    }

    @Override
    public String toString() {
        return "Window.FindStudentDialog{}";
    }
}
