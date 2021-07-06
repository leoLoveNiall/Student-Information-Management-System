package Window;

import FusionUIAsset.*;
import MyUtil.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * LaunchWindow is the main entrance of SIMS.
 * The next aimed window is Main_Window.MAIN_WINDOW.
 * Extends none.
 * Most static built-in.
 *
 * @author 9.5
 */
public class LaunchWindow {
    static final String MEDIA_ASSET_FOLDER = System.getProperty("user.dir") + "/src/MediaAsset";
    static final String OUTPUT_FOLDER = System.getProperty("user.home") + "/Desktop";
    static final String userNameMD5 = "0F759DD1EA6C4C76CEDC299039CA4F23";
    static final String userPasswordMD5 = "202CB962AC59075B964B07152D234B70";
    static final int LAUNCH_WINDOW_WIDTH = 500;
    static final int LAUNCH_WINDOW_HEIGHT = 330;
    static QuickPanelWithLabelAndText name;
    static QuickPanelWithLabelAndPasswordText key;
    static JButton loginButton = new JButton("登录");
    static JFrame LAUNCH_WINDOW = new JFrame("西安科技大学·学生管理系统");

    //ps: Mac系统下文件系统使用右斜杠，Windows系统需要改进
    static void createMainWindow() {
        MotionUtil.exitMotion(LAUNCH_WINDOW, null);
        new MainWindow();
    }

    /**
     * 主函数完成登录信息验证以及控件放置，并导向 Main_Window.MAIN_WINDOW
     */
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        LAUNCH_WINDOW.setLayout(new GridLayout(6, 1));
        LAUNCH_WINDOW.setSize(LAUNCH_WINDOW_WIDTH, LAUNCH_WINDOW_HEIGHT);
        LAUNCH_WINDOW.setResizable(false);
        LAUNCH_WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //LAUNCH_WINDOW.setLocation(600, 600);
//      设置图标panel和标签
        var iconPanel = new JPanel();
        var iconLabel = new JLabel();
        var icon = new ImageIcon(MEDIA_ASSET_FOLDER + "/icon.jpeg");
        var img = icon.getImage();
        img = img.getScaledInstance(160, 60, Image.SCALE_DEFAULT);
        icon.setImage(img);
        iconLabel.setIcon(icon);
        new LoadingPanel();
//      设置窗体panel
        var userPanel = new DoublePanel(1, 2);
        var keyPanel = new DoublePanel(1, 2);
        var buttonPanel = new JPanel();
        var topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        var headLine = new JLabel("学生信息管理系统");
        var captcha = new Captcha();
//      添加panel和img
        LAUNCH_WINDOW.add(iconPanel);
        LAUNCH_WINDOW.add(topPanel);
        LAUNCH_WINDOW.add(userPanel);
        LAUNCH_WINDOW.add(keyPanel);
        LAUNCH_WINDOW.add(captcha);
        LAUNCH_WINDOW.add(buttonPanel);
//      放置控件
        iconPanel.add(iconLabel);
        topPanel.add(headLine);
        name = new QuickPanelWithLabelAndText("用户名", "leo");
        key = new QuickPanelWithLabelAndPasswordText("密码  ", "123");
        userPanel.add(name);
        keyPanel.add(key);
        buttonPanel.add(loginButton);

//      创建窗口
        MotionUtil.centerize(LAUNCH_WINDOW);
        MotionUtil.openMotion(LAUNCH_WINDOW, LAUNCH_WINDOW_WIDTH, LAUNCH_WINDOW_HEIGHT, null);
//      登陆


        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (verifyUserValid()) {
                    if (captcha.isAuthorized()) createMainWindow();
                    else new TemporaryDialog("FusionUIAsset.Captcha 错误！", LAUNCH_WINDOW);
                } else {
                    new TemporaryDialog("账户错误！", LAUNCH_WINDOW);
                }

            }

        });

        var enterToEnter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (verifyUserValid()) {
                        if (captcha.isAuthorized()) createMainWindow();
                        else new TemporaryDialog("FusionUIAsset.Captcha 错误！", LAUNCH_WINDOW);
                    } else {
                        new TemporaryDialog("账户错误！", LAUNCH_WINDOW);
                    }
                }
            }
        };
        LAUNCH_WINDOW.addKeyListener(enterToEnter);
        name.text.addKeyListener(enterToEnter);
        key.text.addKeyListener(enterToEnter);
        System.out.println("YEE");
    }

    //与默认填充的用户的MD5进行比对
    static boolean verifyUserValid() {
        return MD5.getMD5(name.getFilteredText()).equals(userNameMD5) &&
                MD5.getMD5(key.getPassword()).equals(userPasswordMD5);
    }
}


