package Window;

import FusionUIAsset.*;
import MyUtil.*;
import DataClassAsset.*;
import User.User;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Main console.
 * Extends none.
 * Most static built-in.
 *
 * @author 13.7
 */
public class MainWindow {
    public final static double REG_PER = 0.1, MID_PER = 0.2, FIN_PER = 0.7;
    public static final int MAIN_WINDOW_WIDTH = 550, MAIN_WINDOW_HEIGHT = 450;
    private static Student currentStudent = null;
    private static Grade currentGrade = null;
    //tip：这里是引用，直接操作，不用重新赋
    public static JFrame MAIN_WINDOW = new JFrame();
    //standard motion title
    public static final int IN_WARD = 0, TO_EDGE = 1;
    public static final int BACKUP_TO_BIN = 0, BACKUP_TO_DESKTOP = 1, BACKUP_TO_CLOUD = 2;
    public static final String tempFolder = System.getProperty("user.dir") + "/temp";
    private final User currentUser = LaunchWindow.getCurrentUser();
    //关键元素
    public static ArrayList<Student> studentArrayList;
    static JButton confirmCgInfoButton;
    static JComboBox<String> cDegreeCB;
    static JMenu help;
    static JMenu manipulate;
    static JMenu data;
    static JMenuBar menuBar;
    static JMenuItem reLoad;
    static JMenuItem addStu;
    static JMenuItem deleteStu;
    static JMenuItem findStu;
    static JMenuItem helpItem;
    static JMenuItem saveAndExit;
    static JMenuItem saveS;
    static JMenu saveTree;
    static JMenu backup;
    static JMenuItem backupTpDesktop;
    static JMenuItem backupTpCloud;
    static JMenuItem pullCloudBak;
    static JMenuItem addGrade;
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
        MotionUtil.addEscToExist(MAIN_WINDOW, null, MainWindow.IN_WARD);
        MotionUtil.openMotion(MAIN_WINDOW, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT, LaunchWindow.LAUNCH_WINDOW);
        studentArrayList = initializeStudent();
        System.out.println("加载完成");
//初始化窗口
        setDefaultClosingMotion(MAIN_WINDOW, TO_EDGE, true);
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
                if (currentUser.getTag() == User.SUPER_ADMIN_USER) {
                    super.mouseReleased(e);
                    hide_MAIN_WINDOW();
                    new AddStudentDialog("新增学生");
                    setDefaultClosingMotion(AddStudentDialog.addDialog,
                            new CompoundJFrame(AddStudentDialog.addDialog, MAIN_WINDOW, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT),
                            IN_WARD, false);
                } else {
                    showNoAccess();
                    //admin only
                }

            }
        });
        findStu = new JMenuItem("切换学生(W)");
        findStu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                hide_MAIN_WINDOW();
                new FindStudentDialog("切换学生");
                setDefaultClosingMotion(FindStudentDialog.findDialog,
                        new CompoundJFrame(AddStudentDialog.addDialog, MAIN_WINDOW, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT),
                        IN_WARD, false);
            }
        });
        manipulate.add(addStu);
        manipulate.add(findStu);
        menuBar.add(manipulate);
        //////////////////////////////////////////////////////////
        addGrade = new JMenuItem("录入成绩");
        manipulate.add(addGrade);
        addGrade.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (!(currentUser.getTag() == User.STUDENT_USER)) {
                    new AddGradeDialog();
                } else {
                    showNoAccess();
                    //teacher and admin
                }
            }
        });
        deleteStu = new JMenuItem("删除当前学生");

        manipulate.add(deleteStu);
        deleteStu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (currentUser.getTag() == User.SUPER_ADMIN_USER) {
                    studentArrayList.remove(currentStudent);
                    switchRandStu();
                } else {
                    showNoAccess();
                    //admin only
                }


            }
        });
///////////////////////////////////////////////////////////
        data = new JMenu("数据(D)");
        reLoad = new JMenuItem("重载本地数据");
        data.add(reLoad);
        reLoad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                initializeStudent("重载数据中！\n未保存数据将丢失！");
                //随机展示一个学生
                try {
                    var randStu = studentArrayList.get((int) (Math.random() * 100000) % studentArrayList.size());
                    switchStu(randStu);
                } catch (Exception ex) {
                    new TemporaryDialog("加载错误！", 5000, MAIN_WINDOW);
                }
            }
        });
        //////////////////////////////////////////////////////////
        saveTree = new JMenu("保存");
        data.add(saveTree);
        saveS = new JMenuItem("保存至本地");
        saveS.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                TemporaryDialog.showLoadingCircleDialog("保存数据中", MAIN_WINDOW_HEIGHT, MAIN_WINDOW_WIDTH, true, MAIN_WINDOW);
                saveData(BACKUP_TO_BIN);
            }
        });
        saveTree.add(saveS);
        saveAndExit = new JMenuItem("保存并退出");
        saveAndExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                TemporaryDialog.showLoadingCircleDialog("保存数据中", MAIN_WINDOW_HEIGHT, MAIN_WINDOW_WIDTH, true, MAIN_WINDOW);
                saveData(BACKUP_TO_BIN);
                new Thread(() -> {
                    MotionUtil.sleep(3100);
                    MotionUtil.exitToEdge(MAIN_WINDOW);
                    System.exit(0);
                }).start();
            }
        });
        saveTree.add(saveAndExit);
        menuBar.add(data);
        //////////////////////////////////////////////////////////
        backup = new JMenu("备份数据");
        data.add(backup);
        backupTpDesktop = new JMenuItem("备份至桌面");
        backupTpDesktop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                TemporaryDialog.showLoadingCircleDialog("备份数据中", MAIN_WINDOW_HEIGHT, MAIN_WINDOW_WIDTH, true, MAIN_WINDOW);
                saveData(BACKUP_TO_DESKTOP);
            }
        });
        backup.add(backupTpDesktop);
        backupTpCloud = new JMenuItem("备份/覆盖至服务器");
        backupTpCloud.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                var ftp = new FTPUtil();
                if (ftp.loginFtp()) {
                    TemporaryDialog.showLoadingCircleDialog("生成备份文件\n并上传中", MAIN_WINDOW_HEIGHT, MAIN_WINDOW_WIDTH, true, MAIN_WINDOW);
                    saveData(BACKUP_TO_CLOUD);
                    System.out.println("local:" + tempFolder);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    new Thread(() -> new TemporaryDialog("后台上传中", 1000, MainWindow.MAIN_WINDOW)).start();
                    new Thread(() -> {
                        System.out.println("/GRADE.bak");
                        ftp.uploadFile("/bak", "GRADE.bak", tempFolder + "/GRADE.bak");
                        ftp.uploadFile("/bak", "STUDENT.bak", tempFolder + "/STUDENT.bak");
                        ftp.uploadFile("/bak", "system.ini", tempFolder + "/system.ini");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        new TemporaryDialog("云端备份成功", 500, MainWindow.MAIN_WINDOW);
                    }).start();

                } else {
                    new TemporaryDialog("无法连接到服务器!", MAIN_WINDOW);
                }
            }
        });
        backup.add(backupTpCloud);
        pullCloudBak = new JMenuItem("取回云端备份并覆盖至本地");
        pullCloudBak.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                var ftp = new FTPUtil();
                if (ftp.loginFtp()) {
                    new TemporaryDialog("后台下载中", MAIN_WINDOW);
                    if (!ftp.downloadFile("/bak", "GRADE.bak", System.getProperty("user.dir") + "/src/MediaAsset/GRADE.data")) {
                        new TemporaryDialog("下载失败！", MAIN_WINDOW);
                    } else if (!ftp.downloadFile("/bak", "STUDENT.bak", System.getProperty("user.dir") + "/src/MediaAsset/STUDENT.data")) {
                        new TemporaryDialog("下载失败！", MAIN_WINDOW);
                    } else {
                        new TemporaryDialog("覆盖成功！请手动重启！", MAIN_WINDOW);
                        {
                            new Thread(() -> {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException interruptedException) {
                                    interruptedException.printStackTrace();
                                }
                                System.exit(0);
                            }).start();
                        }
                    }
                } else {
                    new TemporaryDialog("无法访问服务器！", MAIN_WINDOW);
                }
            }
        });
        backup.add(pullCloudBak);
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
//////////////////////////////////////////////////////////
        //右半边
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
        c_majorP = new QuickPanelWithLabelAndText("  专业班级:", 14);
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
                if (currentUser.getTag() == User.SUPER_ADMIN_USER) {
                    Student toBeAdd = switch (degree[cDegreeCB.getSelectedIndex()]) {
                        case "本科" -> new Bachelor(c_nameP.getFilteredText(), c_idP.getFilteredText(),
                                c_genderP.getFilteredText(), c_majorP.getFilteredText(),
                                c_dormP.getFilteredText());
                        case "硕士" -> new Master(c_nameP.getFilteredText(), c_idP.getFilteredText(),
                                c_genderP.getFilteredText(), c_majorP.getFilteredText(),
                                c_dormP.getFilteredText(), c_tutorP.getFilteredText());
                        case "博士" -> new Doctor(c_nameP.getFilteredText(), c_idP.getFilteredText(),
                                c_genderP.getFilteredText(), c_majorP.getFilteredText(),
                                c_dormP.getFilteredText(), c_tutorP.getFilteredText(), c_labP.getFilteredText());
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
                } else {
                    showNoAccess();
                    //admin only
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
                    if (gradeSearch.getFilteredText().equals(g.getCourseID()) || gradeSearch.getFilteredText().equals(g.getCourseName())) {
                        courseNameP.setSecondLabelText(g.getCourseName());
                        courseIDP.setSecondLabelText(g.getCourseID());
                        courseCreditP.setSecondLabelText(String.valueOf(g.getCredit()));
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
        //修改成绩面板
        var Grade_cPanel = new JPanel(new BorderLayout());
        topTab.add("修改成绩", Grade_cPanel);
        gradeChangeSearchP = new StandardSearchPanel("输入课程代码或名称:");
        Grade_cPanel.add(gradeChangeSearchP, BorderLayout.PAGE_START);
        pGradePanelChangeP = new JPanel(new GridLayout(8, 1));
        Grade_cPanel.add(pGradePanelChangeP, BorderLayout.CENTER);
        courseName_cP = new QuickPanelWithLabelAndText("课程名称:", "...");
        pGradePanelChangeP.add(courseName_cP);
        courseName_cP.setTextEnabled(false); //学科固有属性不可更改
        courseID_cP = new QuickPanelWithLabelAndText("课程代码:", "...");
        pGradePanelChangeP.add(courseID_cP);
        courseID_cP.setTextEnabled(false);//学科固有属性不可更改
        courseCredit_cP = new QuickPanelWithLabelAndText("课程学分:", "...");
        pGradePanelChangeP.add(courseCredit_cP);
        courseCredit_cP.setTextEnabled(false);//学科固有属性不可更改
        courseRegG_cP = new QuickPanelWithLabelAndText("平时成绩:", "0");
        pGradePanelChangeP.add(courseRegG_cP);
        courseMidG_cP = new QuickPanelWithLabelAndText("期中成绩:", "0");
        pGradePanelChangeP.add(courseMidG_cP);
        courseFinG_cP = new QuickPanelWithLabelAndText("期末成绩:", "0");
        pGradePanelChangeP.add(courseFinG_cP);
        courseAvg_cP = new QuickPanelWithLabelAndText("综合成绩:", "0");
        courseAvg_cP.setEnabled(false);//自动计算，不能手动输入
        //修改成绩查询
        gradeChangeSearchP.b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                var foundOrNot = false;
                for (var g : currentStudent.gradeArrayList) {
                    if (gradeChangeSearchP.getFilteredText().equals(g.getCourseID()) || gradeChangeSearchP.getFilteredText().equals(g.getCourseName())) {
                        courseName_cP.setText(g.getCourseName());
                        courseID_cP.setText(g.getCourseID());
                        courseCredit_cP.setText(String.valueOf(g.getCredit()));
                        courseRegG_cP.setText(String.valueOf(g.getReg()));
                        courseMidG_cP.setText(String.valueOf(g.getMid()));
                        courseFinG_cP.setText(String.valueOf(g.getFin()));
                        courseAvg_cP.setText(String.valueOf(calculateAvgGrade(g.getReg(), g.getMid(), g.getFin())));
                        foundOrNot = true;
                        currentGrade = g;
                        gradeChangeSearchP.t.setEnabled(false);
                        gradeChangeSearchP.b.setEnabled(false);
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
                        if (StringUtil.verifyGradeEditLegit(courseRegG_cP)) {
                            courseAvg_cP.text.setText(String.valueOf(calculateAvgGrade(Integer.parseInt(courseRegG_cP.getFilteredText()), Integer.parseInt(courseMidG_cP.getFilteredText()), Integer.parseInt(courseFinG_cP.getFilteredText()))));
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
                        if (StringUtil.verifyGradeEditLegit(courseMidG_cP)) {
                            courseAvg_cP.text.setText(String.valueOf(calculateAvgGrade(Integer.parseInt(courseRegG_cP.getFilteredText()), Integer.parseInt(courseMidG_cP.getFilteredText()), Integer.parseInt(courseFinG_cP.getFilteredText()))));

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
                        if (StringUtil.verifyGradeEditLegit(courseFinG_cP)) {
                            courseAvg_cP.text.setText(String.valueOf(calculateAvgGrade(Integer.parseInt(courseRegG_cP.getFilteredText()), Integer.parseInt(courseMidG_cP.getFilteredText()), Integer.parseInt(courseFinG_cP.getFilteredText()))));

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
        var deleteGradeButton = new JButton("删除此科");
        changeConfirmPanel.add(changeConfirmButton);
        changeConfirmPanel.add(deleteGradeButton);
        //修改成绩按钮监听
        changeConfirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (!(currentUser.getTag() == User.STUDENT_USER)) {
                    if (currentGrade != null) {
                        currentGrade.setGrade(
                                Integer.parseInt(courseRegG_cP.getFilteredText()),
                                Integer.parseInt(courseMidG_cP.getFilteredText()),
                                Integer.parseInt(courseFinG_cP.getFilteredText()));
                        switchStu(currentStudent);
                        gradeChangeSearchP.t.setEnabled(true);
                        gradeChangeSearchP.b.setEnabled(true);
                        new Thread(() -> new TemporaryDialog("修改成功", MAIN_WINDOW)).start();
                    } else {
                        new TemporaryDialog("请确定一门科目！", MAIN_WINDOW);
                    }
                } else {
                    showNoAccess();
                    //teacher and admin
                }
            }
        });
        deleteGradeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (!(currentUser.getTag() == User.STUDENT_USER)) {
                    if (currentGrade == null) {
                        new TemporaryDialog("请确定一门科目！", MAIN_WINDOW);
                    } else {
                        try {
                            currentStudent.gradeArrayList.remove(currentGrade);
                            gradeChangeSearchP.t.setEnabled(true);
                            gradeChangeSearchP.b.setEnabled(true);
                            new Thread(() -> new TemporaryDialog("删除成功", MAIN_WINDOW)).start();
                        } catch (NullPointerException ex) {
                            new TemporaryDialog("删除失败", MAIN_WINDOW);
                        } finally {
                            currentGrade = null;
                            switchStu(currentStudent);
                        }
                    }
                } else {
                    showNoAccess();
                    //teacher and admin
                }
            }
        });
        switchRandStu();
    }

    public static void switchRandStu() {
        //随机展示一个学生
        try {
            var randStu = studentArrayList.get((int) (Math.random() * 100000) % studentArrayList.size());
            switchStu(randStu);
        } catch (Exception e) {
            new TemporaryDialog("加载错误！", 5000, MAIN_WINDOW);
        }
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
            case Student.BA -> {
                tutorP.setSecondLabelText("--不可用--");
                labP.setSecondLabelText("--不可用--");
                cDegreeCB.setSelectedIndex(0);
            }
            case Student.MA -> {
                tutorP.setSecondLabelText(currentStu.getTutor());
                labP.setSecondLabelText("--不可用--");
                cDegreeCB.setSelectedIndex(1);
                c_tutorP.text.setText(currentStu.getTutor());
            }
            case Student.DO -> {
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
        var icon = new File(LaunchWindow.MEDIA_ASSET_FOLDER + "/profile/" + currentStu.getID() + ".jpg");
        if (icon.exists()) {
            var realIcon = new ImageIcon(icon.getPath());
            var img = realIcon.getImage();
            img = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            realIcon.setImage(img);
            iconLabel.setIcon(realIcon);
        } else {
            var defaultIcon = new ImageIcon(LaunchWindow.MEDIA_ASSET_FOLDER + "/profile.jpeg");
            var img = defaultIcon.getImage();
            img = img.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
            defaultIcon.setImage(img);
            iconLabel.setIcon(defaultIcon);
        }
        gradeChangeSearchP.t.setText("");
        courseName_cP.setText("");
        courseID_cP.setText("");
        courseCredit_cP.setText("");
        courseRegG_cP.setText("");
        courseMidG_cP.setText("");
        courseFinG_cP.setText("");
        courseAvg_cP.setText("");
        MotionUtil.upAndDown_B(MAIN_WINDOW, MAIN_WINDOW_HEIGHT);
    }

    static int calculateAvgGrade(int reg, int mid, int fin) {
        return (int) (reg * REG_PER + mid * MID_PER + fin * FIN_PER);
    }

    /**
     * 从本地MediaAsset加载数据。
     *
     * @return 返回包含学生数据的ArrayList
     */
    ArrayList<Student> initializeStudent() {
        return initializeStudent("读取数据中");
    }

    /**
     * 调用 ArrayList<Student> initializeStudent() 从本地MediaAsset加载数据。
     *
     * @param text 信息框要显示的内容
     * @return 返回包含学生数据的ArrayList(需要调用上级)
     */
    ArrayList<Student> initializeStudent(String text) {
        var studentArrayList = new ArrayList<Student>();
        new Thread(() -> TemporaryDialog.showLoadingCircleDialog(text, (int) (MAIN_WINDOW_HEIGHT * 1.5), (int) (MAIN_WINDOW_WIDTH * 1.5), true, MAIN_WINDOW)).start();
        try {
            var path = LaunchWindow.MEDIA_ASSET_FOLDER + "/STUDENT.data";
            var filename = new File(path);
            var reader = new InputStreamReader(new FileInputStream(filename));
            var bufferedReader = new BufferedReader(reader);
            String line;
            line = bufferedReader.readLine();
            while (line != null) {
                //line格式如：黄京源 14408010120 男 软件工程1401 博士 史晓楠 科创 9#232
                var infoLine = line.split("@");//分割字符串
                if (infoLine[4].equals("本科"))
                    studentArrayList.add(new Bachelor(infoLine[0], infoLine[1], infoLine[2], infoLine[3], infoLine[7]));
                if (infoLine[4].equals("硕士"))
                    studentArrayList.add(new Master(infoLine[0], infoLine[1], infoLine[2], infoLine[3], infoLine[7], infoLine[5]));
                if (infoLine[4].equals("博士"))
                    studentArrayList.add(new Doctor(infoLine[0], infoLine[1], infoLine[2], infoLine[3], infoLine[7], infoLine[5], infoLine[6]));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            new TemporaryDialog("数据Asset出现严重错误，请联系管理员！", 5000, MAIN_WINDOW);
        }
        //加载整个成绩单
        var wholeGradeData = new ArrayList<Grade>();
        try {
            var path = LaunchWindow.MEDIA_ASSET_FOLDER + "/GRADE.data";
            var filename = new File(path);
            var reader = new InputStreamReader(new FileInputStream(filename));
            var bufferedReader = new BufferedReader(reader);
            var courseLine = bufferedReader.readLine();

            while (courseLine != null) {
                var gradeLineArr = courseLine.split(",");//分割字符串
                //csNum = (gradeLineArr.length - 1) / 6;
                for (var i = 1; i < gradeLineArr.length - 1; i += 6) {
                    wholeGradeData.add(new Grade(gradeLineArr[0], gradeLineArr[i + 1], gradeLineArr[i],
                            Byte.parseByte(gradeLineArr[i + 2]), Integer.parseInt(gradeLineArr[i + 3]),
                            Integer.parseInt(gradeLineArr[i + 4]), Integer.parseInt(gradeLineArr[i + 5])));
                }

                courseLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            new TemporaryDialog("数据Asset出现严重错误，请联系管理员！", 5000, MAIN_WINDOW);
        }

        /*
          学习来源：https://blog.csdn.net/shenziheng1/article/details/100110816
          将每个Grade成员赋给相应的Student
         */
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

    public static void setDefaultClosingMotion(JFrame w, CompoundJFrame anotherToShow, int motionType, boolean shutDownJVMOrNot) {
        w.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        switch (motionType) {
            case IN_WARD -> w.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    MotionUtil.exitMotion(w, null);
                    if (shutDownJVMOrNot) {
                        System.exit(0);
                    }
                    if (anotherToShow != null) {
                        MotionUtil.openMotion(anotherToShow.father, anotherToShow.width, anotherToShow.height, anotherToShow.frame);
                    }
                }
            });
            case TO_EDGE -> w.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    MotionUtil.exitToEdge(w);
                    if (shutDownJVMOrNot) {
                        System.exit(0);
                    }
                    if (anotherToShow != null) {
                        MotionUtil.openMotion(anotherToShow.father, anotherToShow.width, anotherToShow.height, anotherToShow.frame);
                    }
                }
            });
        }
    }

    public static void setDefaultClosingMotion(JFrame w, int motionType, boolean shutDownJVMOrNot) {
        setDefaultClosingMotion(w, null, motionType, shutDownJVMOrNot);
    }

    /**
     * Save data to file, the detailed action depends on backupMode.
     *
     * @param backupMode BACKUP_TO_BIN = 0, BACKUP_TO_DESKTOP = 1, BACKUP_TO_CLOUD = 2;
     */
    public static void saveData(int backupMode) {
        String studentFileName = null, gradeFileName = null, systemInfoFileName = null;
        switch (backupMode) {
            case BACKUP_TO_BIN -> {
                studentFileName = LaunchWindow.MEDIA_ASSET_FOLDER + "/STUDENT.data";
                gradeFileName = LaunchWindow.MEDIA_ASSET_FOLDER + "/GRADE.data";
            }
            case BACKUP_TO_DESKTOP -> {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                String dateStringParse = sdf.format(date);
                studentFileName = LaunchWindow.OUTPUT_FOLDER + "/SIMS-backup/" + dateStringParse + "/STUDENT.bak";
                gradeFileName = LaunchWindow.OUTPUT_FOLDER + "/SIMS-backup/" + dateStringParse + "/GRADE.bak";
            }
            case BACKUP_TO_CLOUD -> {
                studentFileName = tempFolder + "/STUDENT.bak";
                gradeFileName = tempFolder + "/GRADE.bak";
                systemInfoFileName = tempFolder + "/system.ini";
            }
        }
        try {
            assert studentFileName != null;
            File file = new File(studentFileName);
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
            } catch (Exception e) {
                new TemporaryDialog("文件目录权限不足！", MAIN_WINDOW);
            }
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Student s : studentArrayList) {
                bw.write(s.getName() + "@" + s.getID() + "@" + s.getGender() + "@" + s.getMajor() + "@" + s.getVali() + "@");
                switch (s.getTag()) {
                    case Student.BA -> bw.write("null@null@" + s.getDorm() + "\n");
                    case Student.MA -> bw.write(s.getTutor() + "@null@" + s.getDorm() + "\n");
                    case Student.DO -> bw.write(s.getTutor() + "@" + s.getLab() + "@" + s.getDorm() + "\n");
                }
            }
            bw.close();
            System.out.println("finish");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File file = new File(gradeFileName);
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
            } catch (Exception e) {
                new TemporaryDialog("文件目录权限不足！", MAIN_WINDOW);
            }
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Student s : studentArrayList) {
                bw.write(s.getID() + ",");
                for (Grade g : s.gradeArrayList) {
                    bw.write(g.getCourseID() + "," + g.getCourseName() + "," +
                            g.getCredit() + "," + g.getReg() + "," + g.getMid() + "," + g.getFin() + ",");
                }
                bw.write("\b\n");
            }
            bw.close();
            System.out.println("finish");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (backupMode == BACKUP_TO_CLOUD) {
            try {
                File file = new File(systemInfoFileName);
                try {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                } catch (Exception e) {
                    new TemporaryDialog("文件目录权限不足！", MAIN_WINDOW);
                }
                if (file.exists()) {
                    file.delete();
                } else {
                    file.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fileWriter);
                var sysProperty = System.getProperties(); //系统属性
                var keySet = sysProperty.keySet();
                for (Object object : keySet) {
                    String property = sysProperty.getProperty(object.toString());
                    bw.write(object + " : " + property + "\n");
                }
                bw.close();
                System.out.println("finish");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Simple set MAIN_WINDOW invisible instead of let it dispose.
     */
    public static void hide_MAIN_WINDOW() {
        MotionUtil.exitMotion(MAIN_WINDOW, null, true);
    }

    public static void show_MAIN_WINDOW() {
        MotionUtil.openMotion(MAIN_WINDOW, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT, null);
    }

    public static void show_MAIN_WINDOW(JFrame j) {
        MotionUtil.openMotion(MAIN_WINDOW, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT, j);
    }

    public static Student getCurrentStudent() {
        return currentStudent;
    }

    public static Grade getCurrentGrade() {
        return currentGrade;
    }

    public static void showNoAccess() {
        new TemporaryDialog("您没有该权限!", MAIN_WINDOW);
    }
}
