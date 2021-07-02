package Window;

import FusionUIAsset.*;
import MyUtil.*;
import DataClassAsset.*;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class MainWindow {

    //public static String workFolder;
    public final static double REG_PER = 0.1, MID_PER = 0.2, FIN_PER = 0.7;
    private static final int MAIN_WINDOW_WIDTH = 550, MAIN_WINDOW_HEIGHT = 450;
    private static Student currentStudent = null;
    //tip：这里是引用，直接操作，不用重新赋
    public static JFrame MAIN_WINDOW = new JFrame();
    //standard motion title
    public static final int IN_WARD = 0, TO_EDGE = 1;

    //关键元素
    public static ArrayList<Student> studentArrayList;
    static JButton confirmCgInfoButton;
    static JComboBox<String> cDegreeCB;
    static JMenu help;
    static JMenu manipulate;
    static JMenu save;
    static JMenuBar menuBar;
    static JMenuItem addStu;
    static JMenuItem findStu;
    static JMenuItem helpItem;
    static JMenuItem saveAndExit;
    static JMenuItem saveS;
    static JPanel cDegreeP;
    static JPanel cInfoPanel;
    static JPanel degreeBoxPanel;
    static JPanel pGradePanel;
    static JPanel pGradePanelChangeP;
    static JPanel pGradePanelInfoP;
    static JPanel pGradePanelSearchP;
    static JPanel pInfoPanel;
    static JPanel pInfoPanelSec;
    static JPanel RightPanel;
    static JTabbedPane topTab;
    static MyCheckBox BA_;
    static MyCheckBox DO_;
    static MyCheckBox MA_;
    static QuickPanelWithLabelAndText c_dormP;
    static QuickPanelWithLabelAndText c_genderP;
    static QuickPanelWithLabelAndText c_idP;
    static QuickPanelWithLabelAndText c_labP;
    static QuickPanelWithLabelAndText c_majorP;
    static QuickPanelWithLabelAndText c_nameP;
    static QuickPanelWithLabelAndText c_tutorP;
    static QuickPanelWithLabelAndText courseAvg_cP;
    static QuickPanelWithLabelAndText courseCredit_cP;
    static QuickPanelWithLabelAndText courseFinG_cP;
    static QuickPanelWithLabelAndText courseID_cP;
    static QuickPanelWithLabelAndText courseMidG_cP;
    static QuickPanelWithLabelAndText courseName_cP;
    static QuickPanelWithLabelAndText courseRegG_cP;
    static QuickPanelWithTwoLabels courseAvgP;
    static QuickPanelWithTwoLabels courseCreditP;
    static QuickPanelWithTwoLabels courseFinGP;
    static QuickPanelWithTwoLabels courseIDP;
    static QuickPanelWithTwoLabels courseMidGP;
    static QuickPanelWithTwoLabels courseNameP;
    static QuickPanelWithTwoLabels courseRegGP;
    static QuickPanelWithTwoLabels dormP;
    static QuickPanelWithTwoLabels genderP;
    static QuickPanelWithTwoLabels idP;
    static QuickPanelWithTwoLabels labP;
    static QuickPanelWithTwoLabels majorP;
    static QuickPanelWithTwoLabels nameP;
    static QuickPanelWithTwoLabels tutorP;
    static StandardSearchPanel gradeChangeSearchP;
    static StandardSearchPanel gradeSearch;
    static JLabel iconLabel;
    //ArrayList待改进
    MainWindow() {
//主程序部分
        MAIN_WINDOW.setResizable(false);
        MAIN_WINDOW.setTitle("学生信息管理系统");
        MotionUtil.addEscToExist(MAIN_WINDOW,null,MainWindow.IN_WARD);
        MotionUtil.openMotion(MAIN_WINDOW, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT, LaunchWindow.LAUNCH_WINDOW);
        studentArrayList = initializeStudent();
        System.out.println("加载完成");
//初始化窗口
        setDefaultClosingMotion(MAIN_WINDOW,TO_EDGE,true);

        MAIN_WINDOW.setLayout(new BorderLayout());

//设置基本框架
        //顶部菜单栏
        menuBar = new JMenuBar();
        MAIN_WINDOW.add(menuBar, BorderLayout.PAGE_START);
        manipulate = new JMenu("操作(M)");
        addStu = new JMenuItem("新增学生(N)");
        addStu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                new AddStudentDialog("新增学生");
                setDefaultClosingMotion(AddStudentDialog.addDialog,IN_WARD,false);
            }
        });
        findStu = new JMenuItem("切换学生(W)");
        findStu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                new FindStudentDialog("切换学生");
                setDefaultClosingMotion(FindStudentDialog.findDialog,IN_WARD,false);
            }
        });
        manipulate.add(addStu);
        manipulate.add(findStu);
        menuBar.add(manipulate);
///////////////////////////////////////////////////////////
        save = new JMenu("保存(S)");
        saveS = new JMenuItem("保存");
        saveS.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                TemporaryDialog.showLoadingCircleDialog("保存数据中", MAIN_WINDOW_HEIGHT, MAIN_WINDOW_WIDTH, true, MAIN_WINDOW);
            }
        });
        save.add(saveS);
        saveAndExit = new JMenuItem("保存并退出");
        saveAndExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                TemporaryDialog.showLoadingCircleDialog("保存数据中", MAIN_WINDOW_HEIGHT, MAIN_WINDOW_WIDTH, true, MAIN_WINDOW);
                new Thread(() -> {
                    MotionUtil.sleep(3100);
                    MotionUtil.exitToEdge(MAIN_WINDOW);
                    System.exit(0);
                }).start();
            }
        });
        save.add(saveAndExit);
        menuBar.add(save);
//////////////////////////////////////////////////////////
        help = new JMenu("帮助(H)");
        helpItem = new JMenuItem("获得帮助");
        helpItem.addMouseListener(new MouseAdapter() {

                                      @Override
                                      public void mouseReleased(MouseEvent e) {
                                          super.mouseReleased(e);
                                          var helpDialog = new JDialog();
                                          helpDialog.setLayout(new FlowLayout());
                                          helpDialog.setTitle("帮助");
                                          helpDialog.add(new JLabel("学生管理系统 V2.0.2 by Leo  2021®"));
                                          MotionUtil.openMotion(helpDialog, 400, 150, MAIN_WINDOW);
                                          System.out.println("帮助");
                                          helpDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                                          helpDialog.addWindowListener(new WindowAdapter() {
                                              @Override
                                              public void windowClosing(WindowEvent e) {
                                                  super.windowClosing(e);
                                                  MotionUtil.exitToEdge(helpDialog);
                                              }
                                          });
                                      }
                                  }
        );
        help.add(helpItem);
        menuBar.add(help);
        //右半边大块
        topTab = new JTabbedPane();
        RightPanel = new JPanel(new BorderLayout());
        MAIN_WINDOW.add(RightPanel, BorderLayout.CENTER);
        //顶部侧边栏
        RightPanel.add(topTab, BorderLayout.CENTER);
        //左边侧边栏(图片)
        {
            var imageP = new JPanel(new BorderLayout());
            var p1 = new JPanel();
            var p2 = new JPanel();
            imageP.add(p1, BorderLayout.PAGE_START);
            imageP.add(p2, BorderLayout.CENTER);
            p1.add(new JLabel("PROFILE"));
            MAIN_WINDOW.add(imageP, BorderLayout.WEST);
            iconLabel = new JLabel();
            p2.add(iconLabel);


        }


        //详细组件
        //查询个人信息面板
        pInfoPanel = new JPanel(new GridLayout(1, 2));
        topTab.add("个人信息", pInfoPanel);
        degreeBoxPanel = new JPanel(new FlowLayout());
        pInfoPanel.add(degreeBoxPanel);
        BA_ = new MyCheckBox("本科", false, false);
        MA_ = new MyCheckBox("硕士", false, false);
        DO_ = new MyCheckBox("博士", false, false);

        degreeBoxPanel.add(BA_);
        degreeBoxPanel.add(MA_);
        degreeBoxPanel.add(DO_);

        pInfoPanelSec = new JPanel(new GridLayout(8, 1));
        pInfoPanel.add(pInfoPanelSec);
        nameP = new QuickPanelWithTwoLabels("姓名:");
        pInfoPanelSec.add(nameP);
        idP = new QuickPanelWithTwoLabels("学号:");
        pInfoPanelSec.add(idP);
        genderP = new QuickPanelWithTwoLabels("性别:");
        pInfoPanelSec.add(genderP);
        majorP = new QuickPanelWithTwoLabels("专业班级:");
        pInfoPanelSec.add(majorP);
        tutorP = new QuickPanelWithTwoLabels("导师:");
        pInfoPanelSec.add(tutorP);
        labP = new QuickPanelWithTwoLabels("实验室:");
        pInfoPanelSec.add(labP);
        dormP = new QuickPanelWithTwoLabels("寝室号:");
        pInfoPanelSec.add(dormP);

//        nameP.setSecondLabel("恺恺子");
//        idP.setSecondLabel("0000");
//        ageP.setSecondLabel("48");
//        majorP.setSecondLabel("CS");
//        tutorP.setSecondLabel("暂无");
//        labP.setSecondLabel("暂无");
//        dormP.setSecondLabel("暂无");


        //修改信息面板
        cInfoPanel = new JPanel(new FlowLayout());
        topTab.add("更正信息", cInfoPanel);
        cDegreeP = new JPanel(new FlowLayout());
        cDegreeP.add(new JLabel("学历："));
        String[] degree = {"本科", "硕士", "博士"};
        cDegreeCB = new JComboBox<>(degree);
        cDegreeP.add(cDegreeCB);
        cInfoPanel.add(cDegreeP);
        c_nameP = new QuickPanelWithLabelAndText("  姓名:");
        cInfoPanel.add(c_nameP);
        c_idP = new QuickPanelWithLabelAndText("  学号:");
        cInfoPanel.add(c_idP);
        c_genderP = new QuickPanelWithLabelAndText("  性别:");
        cInfoPanel.add(c_genderP);
        c_majorP = new QuickPanelWithLabelAndText("  专业班级:");
        cInfoPanel.add(c_majorP);
        c_tutorP = new QuickPanelWithLabelAndText("  导师:");
        cInfoPanel.add(c_tutorP);
        c_labP = new QuickPanelWithLabelAndText("实验室:");
        cInfoPanel.add(c_labP);
        c_dormP = new QuickPanelWithLabelAndText("寝室号:");
        cInfoPanel.add(c_dormP);
        c_tutorP.setText("不可用", false);
        c_labP.setText("不可用", false);
        //根据学位选择是否展示tutor和lab
        cDegreeCB.addActionListener(e ->
        {
            if (cDegreeCB.getSelectedItem() == null) {
                new TemporaryDialog("ERROR!", MAIN_WINDOW);
            }
            if (cDegreeCB.getSelectedItem().equals("本科")) {
                c_tutorP.setText("不可用", false);
                c_labP.setText("不可用", false);
            }
            if (cDegreeCB.getSelectedItem().equals("硕士")) {
                c_tutorP.setText("...", true);
                c_labP.setText("不可用", false);
            }
            if (cDegreeCB.getSelectedItem().equals("博士")) {
                c_tutorP.setText("...", true);
                c_labP.setText("...", true);
            }
        });
        confirmCgInfoButton = new JButton("确认修改");
        confirmCgInfoButton.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                Student toBeAdd = switch (degree[cDegreeCB.getSelectedIndex()]) {
                    case "本科" -> new Bachelor(c_nameP.text.getText(), c_idP.text.getText(),
                            c_genderP.text.getText(), c_majorP.text.getText(),
                            c_dormP.text.getText());
                    case "硕士" -> new Master(c_nameP.text.getText(), c_idP.text.getText(),
                            c_genderP.text.getText(), c_majorP.text.getText(),
                            c_dormP.text.getText(), c_tutorP.text.getText());
                    case "博士" -> new Doctor(c_nameP.text.getText(), c_idP.text.getText(),
                            c_genderP.text.getText(), c_majorP.text.getText(),
                            c_dormP.text.getText(), c_tutorP.text.getText(), c_labP.text.getText());
                    default -> null;
                };
                try {
                    assert toBeAdd != null;
                    toBeAdd.gradeArrayList = (ArrayList<Grade>) currentStudent.gradeArrayList.clone();
                } catch (NullPointerException crash) {
                    new TemporaryDialog("发生异常！\n" + crash, MAIN_WINDOW);
                }

                if (ifIDExists(toBeAdd.getID()) && !toBeAdd.getID().equals(currentStudent.getID())) {
                    //一个短暂的对话框
                    new TemporaryDialog("学号已存在！", 100, 300, MAIN_WINDOW);
                } else {
                    studentArrayList.remove(currentStudent);
                    studentArrayList.add(toBeAdd);
                    currentStudent = studentArrayList.get(studentArrayList.size() - 1);
                    System.out.println("修改成功");
                    //重新加载
                    switchStu(currentStudent);
                }
            }
        });
        cInfoPanel.add(confirmCgInfoButton);


        //查询成绩面板
        pGradePanel = new JPanel(new BorderLayout());
        topTab.add("查询成绩", pGradePanel);

        pGradePanelSearchP = new JPanel();
        pGradePanel.add(pGradePanelSearchP, BorderLayout.PAGE_START);
        gradeSearch = new StandardSearchPanel("输入查询科目或者课程代码:");
        pGradePanelSearchP.add(gradeSearch);
        pGradePanelInfoP = new JPanel(new GridLayout(7, 1));
        pGradePanel.add(pGradePanelInfoP, BorderLayout.CENTER);
        courseNameP = new QuickPanelWithTwoLabels("课程名称:");
        pGradePanelInfoP.add(courseNameP);
        courseIDP = new QuickPanelWithTwoLabels("课程代码:");
        pGradePanelInfoP.add(courseIDP);
        courseCreditP = new QuickPanelWithTwoLabels("课程学分:");
        pGradePanelInfoP.add(courseCreditP);
        courseRegGP = new QuickPanelWithTwoLabels("平时成绩:");
        pGradePanelInfoP.add(courseRegGP);
        courseMidGP = new QuickPanelWithTwoLabels("期中成绩:");
        pGradePanelInfoP.add(courseMidGP);
        courseFinGP = new QuickPanelWithTwoLabels("期末成绩:");
        pGradePanelInfoP.add(courseMidGP);
        courseAvgP = new QuickPanelWithTwoLabels("综合成绩:");
        pGradePanelInfoP.add(courseAvgP);
        gradeSearch.b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                var foundOrNot = false;
                for (Grade g : currentStudent.gradeArrayList) {
                    if (gradeSearch.t.getText().equals(g.getCourseID()) || gradeSearch.t.getText().equals(g.getCourseName())) {
                        courseNameP.setSecondLabelText(g.getCourseName());
                        courseIDP.setSecondLabelText(g.getCourseID());
                        courseCreditP.setSecondLabelText(g.getCredit());
                        courseRegGP.setSecondLabelText(String.valueOf(g.getReg()));
                        courseMidGP.setSecondLabelText(String.valueOf(g.getMid()));
                        courseFinGP.setSecondLabelText(String.valueOf(g.getFin()));
                        courseAvgP.setSecondLabelText(String.valueOf(calculateAvgGrade(g.getReg(), g.getMid(), g.getFin())));
                        foundOrNot = true;
                        break;
                    }
                }
                if (!foundOrNot) new TemporaryDialog("找不到此门科目！", MAIN_WINDOW);
            }
        });


        //录入成绩面板
        var Grade_cPanel = new JPanel(new BorderLayout());
        topTab.add("修改成绩", Grade_cPanel);

        gradeChangeSearchP = new StandardSearchPanel("输入课程代码:");
        Grade_cPanel.add(gradeChangeSearchP, BorderLayout.PAGE_START);
        pGradePanelChangeP = new JPanel(new GridLayout(8, 1));
        Grade_cPanel.add(pGradePanelChangeP, BorderLayout.CENTER);


        courseName_cP = new QuickPanelWithLabelAndText("课程名称:", "...");
        pGradePanelChangeP.add(courseName_cP);
        courseName_cP.text.setEnabled(false); //学科固有属性不可更改
        courseID_cP = new QuickPanelWithLabelAndText("课程代码:", "...");
        pGradePanelChangeP.add(courseID_cP);
        courseID_cP.text.setEnabled(false);//学科固有属性不可更改
        courseCredit_cP = new QuickPanelWithLabelAndText("课程学分:", "...");
        pGradePanelChangeP.add(courseCredit_cP);
        courseCredit_cP.text.setEnabled(false);//学科固有属性不可更改
        courseRegG_cP = new QuickPanelWithLabelAndText("平时成绩:", "0");
        pGradePanelChangeP.add(courseRegG_cP);
        courseMidG_cP = new QuickPanelWithLabelAndText("期中成绩:", "0");
        pGradePanelChangeP.add(courseMidG_cP);
        courseFinG_cP = new QuickPanelWithLabelAndText("期末成绩:", "0");
        pGradePanelChangeP.add(courseFinG_cP);
        courseAvg_cP = new QuickPanelWithLabelAndText("综合成绩:", "0");
        courseAvg_cP.text.setEnabled(false);//自动计算，不能手动输入

        //修改成绩查询
        gradeChangeSearchP.b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                var foundOrNot = false;
                for (var g : currentStudent.gradeArrayList) {
                    if (gradeChangeSearchP.t.getText().equals(g.getCourseID()) || gradeChangeSearchP.t.getText().equals(g.getCourseName())) {
                        courseName_cP.setText(g.getCourseName());
                        courseID_cP.setText(g.getCourseID());
                        courseCredit_cP.setText(g.getCredit());
                        courseRegG_cP.setText(String.valueOf(g.getReg()));
                        courseMidG_cP.setText(String.valueOf(g.getMid()));
                        courseFinG_cP.setText(String.valueOf(g.getFin()));
                        courseAvg_cP.setText(String.valueOf(calculateAvgGrade(g.getReg(), g.getMid(), g.getFin())));
                        foundOrNot = true;
                        break;
                    }
                }
                if (!foundOrNot) new TemporaryDialog("找不到此门科目！", MAIN_WINDOW);
            }
        });
        courseRegG_cP.addMouseListener(new MouseInputAdapter() {
        });
        //添加自动计算成绩脚本
        {
            courseRegG_cP.text.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    try {
                        if (verifyGradeEditLegit(courseRegG_cP)) {
                            courseAvg_cP.text.setText(String.valueOf(calculateAvgGrade(Integer.parseInt(courseRegG_cP.getText()), Integer.parseInt(courseMidG_cP.getText()), Integer.parseInt(courseFinG_cP.getText()))));
                        } else courseAvg_cP.setText("...");
                    } catch (InterruptedException | AWTException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });
            courseMidG_cP.text.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    try {
                        if (verifyGradeEditLegit(courseMidG_cP)) {
                            courseAvg_cP.text.setText(String.valueOf(calculateAvgGrade(Integer.parseInt(courseRegG_cP.getText()), Integer.parseInt(courseMidG_cP.getText()), Integer.parseInt(courseFinG_cP.getText()))));

                        } else courseAvg_cP.setText("...");
                    } catch (InterruptedException | AWTException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });
            courseFinG_cP.text.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    try {
                        if (verifyGradeEditLegit(courseFinG_cP)) {
                            courseAvg_cP.text.setText(String.valueOf(calculateAvgGrade(Integer.parseInt(courseRegG_cP.getText()), Integer.parseInt(courseMidG_cP.getText()), Integer.parseInt(courseFinG_cP.getText()))));

                        } else courseAvg_cP.setText("...");
                    } catch (InterruptedException | AWTException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            });

        }

        pGradePanelChangeP.add(courseAvg_cP);
        var changeConfirmPanel = new JPanel();
        pGradePanelChangeP.add(changeConfirmPanel);
        var changeConfirmButton = new JButton("确认修改");
        changeConfirmPanel.add(changeConfirmButton);

        //修改成绩按钮监听
        changeConfirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                for (var i = 0; i < currentStudent.gradeArrayList.size(); i++) {
                    if (gradeChangeSearchP.t.getText().equals(currentStudent.gradeArrayList.get(i).getCourseID())
                            || gradeChangeSearchP.t.getText().equals(currentStudent.gradeArrayList.get(i).getCourseName())) {
                        currentStudent.gradeArrayList.get(i).setGrade(
                                Integer.parseInt(courseRegG_cP.text.getText()),
                                Integer.parseInt(courseMidG_cP.text.getText()),
                                Integer.parseInt(courseFinG_cP.text.getText()));
                        switchStu(currentStudent);
                        break;
                    }

                }
            }
        });


        //随机展示一个学生
        var randStu = studentArrayList.get((int) (Math.random() * 100000) % studentArrayList.size());
        switchStu(randStu);


    }

    public static void switchStu(Student currentStu) {
        MotionUtil.upAndDown_A(MAIN_WINDOW, MAIN_WINDOW_HEIGHT);
        currentStudent = currentStu;
        System.out.println("切换学生:" + currentStu.toString());
        nameP.setSecondLabelText(currentStu.getName());
        idP.setSecondLabelText(currentStu.getID());
        genderP.setSecondLabelText(currentStu.getGender());
        majorP.setSecondLabelText(currentStu.getMajor());
        dormP.setSecondLabelText(currentStu.getDorm());
        MyCheckBox.quicklySetOneCheckBoxSelected(BA_, MA_, DO_, currentStu);
        switch (currentStu.getTag()) {
            case "BA" -> {
                tutorP.setSecondLabelText("--不可用--");
                labP.setSecondLabelText("--不可用--");
                cDegreeCB.setSelectedIndex(0);
            }
            case "MA" -> {
                tutorP.setSecondLabelText(currentStu.getTutor());
                labP.setSecondLabelText("--不可用--");
                cDegreeCB.setSelectedIndex(1);
                c_tutorP.text.setText(currentStu.getTutor());
            }
            case "DO" -> {
                tutorP.setSecondLabelText(currentStu.getTutor());
                labP.setSecondLabelText(currentStu.getLab());
                cDegreeCB.setSelectedIndex(2);
                c_tutorP.text.setText(currentStu.getTutor());
                c_labP.text.setText(currentStu.getLab());
            }
        }
        //更正信息面板内容填充⬆️⬇️
        c_nameP.text.setText(currentStu.getName());
        c_idP.text.setText(currentStu.getID());
        c_genderP.text.setText(currentStu.getGender());
        c_majorP.text.setText(currentStu.getMajor());
        c_dormP.text.setText(currentStu.getDorm());
        topTab.setSelectedIndex(0);

        var icon = new File(LaunchWindow.MEDIA_ASSET_FOLDER + "/profile/"+currentStu.getID()+".jpg");
        if (icon.exists()){
            var realIcon = new ImageIcon(icon.getPath());
            var img = realIcon.getImage();
            img = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            realIcon.setImage(img);
            iconLabel.setIcon(realIcon);
        }else {
            var defaultIcon = new ImageIcon(LaunchWindow.MEDIA_ASSET_FOLDER + "/profile.jpeg");
            var img = defaultIcon.getImage();
            img = img.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
            defaultIcon.setImage(img);
            iconLabel.setIcon(defaultIcon);
        }



        MotionUtil.upAndDown_B(MAIN_WINDOW, MAIN_WINDOW_HEIGHT);
    }

    static int calculateAvgGrade(int reg, int mid, int fin) {
        return (int) (reg * REG_PER + mid * MID_PER + fin * FIN_PER);
    }

    static boolean verifyGradeEditLegit(QuickPanelWithLabelAndText gradeEditPanel) throws InterruptedException, AWTException {
        //其他字符验证
        if (!verifyInteger(gradeEditPanel.getText())) {
            MotionUtil.displayErrorInfo(gradeEditPanel.text, "输入了不合法的字符");
            return false;
        }

        //区间位于0～100
        if (Integer.parseInt(gradeEditPanel.getText()) > 100 || Integer.parseInt(gradeEditPanel.getText()) < 0) {
            MotionUtil.displayErrorInfo(gradeEditPanel.text, "成绩须为0～100");
            return false;
        }

        return true;
    }

    static boolean verifyInteger(String s) {
        char[] cs = s.toCharArray();
        for (var c : cs) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    ArrayList<Student> initializeStudent() {
        var studentArrayList = new ArrayList<Student>();
        new Thread(() -> TemporaryDialog.showLoadingCircleDialog("", (int) (MAIN_WINDOW_HEIGHT*1.5), (int) (MAIN_WINDOW_WIDTH*1.5), true, MAIN_WINDOW)).start();
        try {
            var path = LaunchWindow.MEDIA_ASSET_FOLDER + "//STUDENT.txt";
            var filename = new File(path);
            var reader = new InputStreamReader(new FileInputStream(filename));
            var bufferedReader = new BufferedReader(reader);
            String line;
            line = bufferedReader.readLine();
            while (line != null) {
                //line格式如：黄京源 14408010120 男 软件工程1401 博士 史晓楠 科创 9#232
                var infoLine = line.split("\\s+");//分割字符串
                if (infoLine[4].equals("本科"))
                    studentArrayList.add(new Bachelor(infoLine[0], infoLine[1], infoLine[2], infoLine[3], infoLine[7]));
                if (infoLine[4].equals("硕士"))
                    studentArrayList.add(new Master(infoLine[0], infoLine[1], infoLine[2], infoLine[3], infoLine[7], infoLine[5]));
                if (infoLine[4].equals("博士"))
                    studentArrayList.add(new Doctor(infoLine[0], infoLine[1], infoLine[2], infoLine[3], infoLine[7], infoLine[5], infoLine[6]));

                line = bufferedReader.readLine();
            }
        } catch (IOException ignored) {

        }
        //加载整个成绩单
//      int csNum = 0;
        var wholeGradeData = new ArrayList<Grade>();
        try {
            var path = LaunchWindow.MEDIA_ASSET_FOLDER + "//GRADE.txt";
            var filename = new File(path);
            var reader = new InputStreamReader(new FileInputStream(filename));
            var bufferedReader = new BufferedReader(reader);
            var courseLine = bufferedReader.readLine();

            while (courseLine != null) {
                var gradeLineArr = courseLine.split(",");//分割字符串
                //csNum = (gradeLineArr.length - 1) / 6;
                for (var i = 1; i < gradeLineArr.length - 1; i += 6) {
                    wholeGradeData.add(new Grade(gradeLineArr[0], gradeLineArr[i + 1], gradeLineArr[i],
                            gradeLineArr[i + 2], Integer.parseInt(gradeLineArr[i + 3]),
                            Integer.parseInt(gradeLineArr[i + 4]), Integer.parseInt(gradeLineArr[i + 5])));
                }

                courseLine = bufferedReader.readLine();
            }
        } catch (IOException ignored) {

        }
        //学习来源：https://blog.csdn.net/shenziheng1/article/details/100110816

        //将每个Grade成员赋给相应的Student
        for (var i = 0; i < studentArrayList.size(); i++) {
            var tmpStuEl = studentArrayList.get(i);

            for (var tmpGradeEl : wholeGradeData) {
                if (tmpGradeEl.getStuID().equals(tmpStuEl.getID()))
                    tmpStuEl.gradeArrayList.add(tmpGradeEl);
            }
            studentArrayList.add(i, tmpStuEl);
            try {
                studentArrayList.remove(i);
            } catch (Exception e) {
                new TemporaryDialog("失败！请联系管理员", MAIN_WINDOW);
            }
        }
        return studentArrayList;
    }

    public static boolean ifIDExists(String str) {
        for (var stu : studentArrayList) {
            if (stu.getID().equals(str)) return true;
        }
        return false;
    }


    public static void setDefaultClosingMotion(JFrame w,int motionType, boolean shutDownJVMOrNot) {
        w.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        switch (motionType){
            case IN_WARD:w.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    MotionUtil.exitMotion(w,null);
                    if (shutDownJVMOrNot) {
                        System.exit(0);
                    }
                }
            });break;
            case TO_EDGE:w.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    MotionUtil.exitToEdge(w);
                    if (shutDownJVMOrNot) {
                        System.exit(0);
                    }
                }
            });break;
            default:break;
        }

    }

}
