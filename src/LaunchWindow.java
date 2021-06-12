
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LaunchWindow{
    //static final String WORK_FOLDER = System.getProperty("user.dir")+"/src/";
    static JFrame LAUNCH_WINDOW = new JFrame("西安科技大学·学生管理系统");
    static final String WORK_FOLDER = "/Users/kongweirui/Desktop/Java/Student-Inform" +
            "ation-Management-System/out/production/Student-Information-Management-System";

    static JLabel nameLabel = new JLabel("用户名：");
    static JTextField nameText = new JTextField("leo", 10);
    static JLabel keyLabel = new JLabel("  密码：");
    static JTextField keyText = new JPasswordField("123", 10);
    static JButton loginButton = new JButton("登录");


    //ps: Mac系统下文件系统使用右斜杠，Windows系统需要改进
    static void createMainWindow(JFrame LAUNCH_WINDOW) {
        System.out.println(System.getProperty("user.dir"));
        SimpleMotion.exitMotion(LAUNCH_WINDOW);
        LAUNCH_WINDOW.setVisible(false);

        System.gc();
        MainWindow mainWindow = new MainWindow();
        LAUNCH_WINDOW.dispose(); //销毁窗口
    }

    public static void main(String[] args) {

        LAUNCH_WINDOW.setLayout(new GridLayout(6, 1));
        LAUNCH_WINDOW.setSize(400, 300);
        LAUNCH_WINDOW.setResizable(false);
        LAUNCH_WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //LAUNCH_WINDOW.setLocation(600, 600);
//      设置图标panel和标签
        JPanel iconPanel = new JPanel();
        JLabel iconLabel = new JLabel();
        ImageIcon icon = new ImageIcon(WORK_FOLDER + "//icon.jpeg");
        Image img = icon.getImage();
        img = img.getScaledInstance(160, 60, Image.SCALE_DEFAULT);
        icon.setImage(img);
        iconLabel.setIcon(icon);

//      设置窗体panel
        DoublePanel userPanel = new DoublePanel(1, 2);
        DoublePanel keyPanel = new DoublePanel(1, 2);
        JPanel buttonPanel = new JPanel();
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel headLine = new JLabel("学生信息管理系统");
        Captcha captcha = new Captcha();
//      添加panel和img
        LAUNCH_WINDOW.add(iconPanel);
        LAUNCH_WINDOW.add(topPanel);
        LAUNCH_WINDOW.add(userPanel);
        LAUNCH_WINDOW.add(keyPanel);
        LAUNCH_WINDOW.add(captcha);
        LAUNCH_WINDOW.add(buttonPanel);

        System.out.println(MD5.getMD5(keyText.getText()));

//      放置控件
        iconPanel.add(iconLabel);
        topPanel.add(headLine);
        userPanel.add(nameLabel, nameText);
        keyPanel.add(keyLabel, keyText);
        buttonPanel.add(loginButton);

//      创建窗口
        SimpleMotion.centerize(LAUNCH_WINDOW);
        SimpleMotion.openMotion(LAUNCH_WINDOW, 400, 300);
//      登陆


        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (nameText.getText().equals("leo") &&
                        MD5.getMD5(keyText.getText()).equals("202CB962AC59075B964B07152D234B70")) {
                    createMainWindow(LAUNCH_WINDOW);

                }
            }
        });

        KeyListener enterToEnter = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyChar());
                if (e.getKeyChar() == '\n') {

                    loginButton.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        LAUNCH_WINDOW.addKeyListener(enterToEnter);
        nameLabel.addKeyListener(enterToEnter);
        keyLabel.addKeyListener(enterToEnter);

        System.out.println("YEE");
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
        // 需要加密的字符串
        try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = src.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[]{'0', '1', '2', '3', '4', '5',
                    '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuffer sb = new StringBuffer();
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
            // 打印加密后的字符串
            return String.valueOf(sb);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}