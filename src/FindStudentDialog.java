import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FindStudentDialog extends JDialog
{
    JDialog findDialog = new JDialog();
    static Student selectedStu = null;
    static private final int HEIGHT = 300, WIDTH = 400;

    static String findStu(ArrayList<Student> studentArrayList)
    {
        return "";
    }


    public FindStudentDialog(Frame owner, String title, boolean modal) throws Exception
    {

        //初始化窗体
        JPanel mainPanel = new JPanel();
        findDialog.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        ArrayList<Student> searchResultArrayList = new ArrayList<Student>();
        //查询控件
        StandardSearchPanel searchPanel = new StandardSearchPanel("查询学生：");
        mainPanel.add(searchPanel, BorderLayout.PAGE_START);


        //查询-结果,左侧学生简要信息，右侧切换
        JPanel resultPanel = new JPanel();
        mainPanel.add(resultPanel);
        //resultPanel.setLayout(new GridLayout(1, 2));


        JPanel stuSerInfoPanel = new JPanel();
        stuSerInfoPanel.setLayout(new GridLayout(7, 1));
        //一般重名的人不会太多
        resultPanel.add(stuSerInfoPanel);
        JButton switchStuButton = new JButton("切换至该学生");


        //结果

        searchPanel.b.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                SimpleMotion.upAndDown_A(findDialog, HEIGHT);
                stuSerInfoPanel.removeAll();
                stuSerInfoPanel.add(new JLabel("查询结果："));
                ArrayList<JRadioButton> resultStu = new ArrayList<JRadioButton>();
                ButtonGroup buttonGroup = new ButtonGroup();

                int index = 0;
                super.mouseReleased(e);
                boolean foundOrNot = false;
                for (Student tmpStu : MainWindow.studentArrayList)
                {
                    if (tmpStu.getName().equals(searchPanel.t.getText()) || tmpStu.getID().equals(searchPanel.t.getText()))
                    {

                        resultStu.add(new JRadioButton(tmpStu.getName() + "," + tmpStu.getMajor() + "," + tmpStu.getID()));
                        stuSerInfoPanel.add(resultStu.get(index));
                        buttonGroup.add(resultStu.get(index));
                        findDialog.setSize(findDialog.getWidth() + 1, findDialog.getHeight());
                        findDialog.setSize(findDialog.getWidth() - 1, findDialog.getHeight());
                        foundOrNot = true;
                        index++;
                        System.out.println("found:" + tmpStu.getName());

                    }
                }
                stuSerInfoPanel.add(switchStuButton);
                switchStuButton.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseReleased(MouseEvent e)
                    {
                        super.mouseReleased(e);
                        String[] s = new String[3];
                        for (JRadioButton rb : resultStu)
                        {
                            if (rb.isSelected())
                            {
                                s = rb.getText().split(",");
                                for (Student stu : MainWindow.studentArrayList)
                                {
                                    if (stu.getID().equals(s[2])) MainWindow.switchStu(stu);
                                }
                                break;
                            }
                        }
                    }
                });
                if (!foundOrNot)
                {
                    try
                    {
                        SimpleMotion.displayErrorInfo(searchPanel.t, "找不到此学生");
                    } catch (InterruptedException interruptedException)
                    {
                        interruptedException.printStackTrace();
                    } catch (AWTException awtException)
                    {
                        awtException.printStackTrace();
                    }
                }

                SimpleMotion.upAndDown_B(findDialog, HEIGHT);

            }
        });


//
//        JRadioButton jrb1 = new JRadioButton("张三 本科生 1234 软件工程2001");
//        JRadioButton jrb2 = new JRadioButton("李四 本科生 5678 计算机科学与技术2002");
//        JRadioButton jrb3 = new JRadioButton("王五 本科生 9012 大数据2003");
//        btnG.add(jrb1);
//        btnG.add(jrb2);
//        btnG.add(jrb3);
//        stuSerInfoPanel.add(jrb1);
//        stuSerInfoPanel.add(jrb2);
//        stuSerInfoPanel.add(jrb3);

        //https://blog.csdn.net/xietansheng/article/details/74363379?utm_source=app&app_version=4.7.0


        switchStuButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
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
