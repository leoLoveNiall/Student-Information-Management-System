import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FindStudentDialog extends JDialog {
    JDialog findDialog = new JDialog();
    static private final int HEIGHT = 300, WIDTH = 400;


    public FindStudentDialog(){

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
        stuSerInfoPanel.setLayout(new GridLayout(7, 1));
        //一般重名的人不会太多
        resultPanel.add(stuSerInfoPanel);
        var switchStuButton = new JButton("切换至该学生");


        //结果

        searchPanel.b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SimpleMotion.upAndDown_A(findDialog, HEIGHT);
                stuSerInfoPanel.removeAll();
                var findLabel = new JLabel("查询到");
                stuSerInfoPanel.add(findLabel);
                ArrayList<JRadioButton> resultStu = new ArrayList<>();
                var buttonGroup = new ButtonGroup();

                var index = 0;
                super.mouseReleased(e);
                boolean foundOrNot = false;
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
                stuSerInfoPanel.add(switchStuButton);
                switchStuButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        super.mouseReleased(e);
                        String[] s;
                        for (JRadioButton rb : resultStu) {
                            if (rb.isSelected()) {
                                s = rb.getText().split(",");
                                for (Student stu : MainWindow.studentArrayList) {
                                    if (stu.getID().equals(s[2])) MainWindow.switchStu(stu);
                                }
                                break;
                            }
                        }
                    }
                });
                if (!foundOrNot) {

                    SimpleMotion.displayErrorInfo(searchPanel.t, "找不到此学生");
                    findLabel.setText("查询到0个结果");
                    switchStuButton.setVisible(false);

                } else switchStuButton.setVisible(true);
                SimpleMotion.upAndDown_B(findDialog, HEIGHT);

            }
        });

        switchStuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                //System.out.println("切换至学生" + group.getSelection().toString());

                // MainWindow.switchStu();
                SimpleMotion.exitMotion(findDialog);
                findDialog.dispose();
            }
        });


        SimpleMotion.openMotion(findDialog, WIDTH, HEIGHT);
        SimpleMotion.centerize(findDialog);
    }

}
