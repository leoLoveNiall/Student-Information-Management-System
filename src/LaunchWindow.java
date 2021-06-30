
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LaunchWindow {
    static final String ASSET_FOLDER = System.getProperty("user.dir") + "/src/asset/";
    static JFrame LAUNCH_WINDOW = new JFrame("西安科技大学·学生管理系统");
    //static final String ASSET_FOLDER = "/Users/kongweirui/Desktop/Java/Student-Inform" +
    //        "ation-Management-System/out/production/Student-Information-Management-System";

    static final String userNameMD5 = "0F759DD1EA6C4C76CEDC299039CA4F23";
    static final String userPasswordMD5 = "202CB962AC59075B964B07152D234B70";
    static final int LAUNCH_WINDOW_WIDTH = 500;
    static final int LAUNCH_WINDOW_HEIGHT = 330;

    //核心组件
    static JLabel nameLabel = new JLabel("用户名：");
    static JTextField nameText = new JTextField("leo", 10);
    static JLabel keyLabel = new JLabel("  密码：");
    static JTextField keyText = new JPasswordField("123", 10);
    static JButton loginButton = new JButton("登录");


    //ps: Mac系统下文件系统使用右斜杠，Windows系统需要改进
    static void createMainWindow() {
        SimpleMotion.exitMotion(LAUNCH_WINDOW,null);
        new MainWindow();
    }

    public static void main(String[] args) {

        LAUNCH_WINDOW.setLayout(new GridLayout(6, 1));
        LAUNCH_WINDOW.setSize(LAUNCH_WINDOW_WIDTH, LAUNCH_WINDOW_HEIGHT);
        LAUNCH_WINDOW.setResizable(false);
        LAUNCH_WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //LAUNCH_WINDOW.setLocation(600, 600);
//      设置图标panel和标签
        var iconPanel = new JPanel();
        var iconLabel = new JLabel();
        var icon = new ImageIcon(ASSET_FOLDER + "//icon.jpeg");
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
        userPanel.add(nameLabel, nameText);
        keyPanel.add(keyLabel, keyText);
        buttonPanel.add(loginButton);

//      创建窗口
        SimpleMotion.centerize(LAUNCH_WINDOW);
        SimpleMotion.openMotion(LAUNCH_WINDOW, LAUNCH_WINDOW_WIDTH, LAUNCH_WINDOW_HEIGHT, null);
//      登陆


        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (verifyUserValid()) {
                    if (captcha.isAuthorized()) createMainWindow();
                    else new TemporaryDialog("Captcha 错误！", LAUNCH_WINDOW);
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
                        else new TemporaryDialog("Captcha 错误！", LAUNCH_WINDOW);
                    } else {
                        new TemporaryDialog("账户错误！", LAUNCH_WINDOW);
                    }
                }
            }

        };
        LAUNCH_WINDOW.addKeyListener(enterToEnter);
        nameText.addKeyListener(enterToEnter);
        keyText.addKeyListener(enterToEnter);

        System.out.println("YEE");
    }

    static boolean verifyUserValid() {
        return MD5.getMD5(nameText.getText()).equals(userNameMD5) &&
                MD5.getMD5(keyText.getText()).equals(userPasswordMD5);
    }
}


//以下MD5加密代码来自网络
/*      作者：cxm
        链接：https://zhuanlan.zhihu.com/p/269031563
        来源：知乎
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/
class MD5 {
    public static String getMD5(String src) {
        //计时
        var starTime = System.currentTimeMillis();
        // 需要加密的字符串
        try {
            // 加密对象，指定加密方式
            var md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = src.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[]{'0', '1', '2', '3', '4', '5',
                    '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            var sb = new StringBuilder();
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
            // 打印加密后的字符串

            //计时
            var endTime = System.currentTimeMillis();
            var Time = endTime - starTime;
            System.out.println("MD5计算用时：" + Time + "ms");


            return String.valueOf(sb);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}