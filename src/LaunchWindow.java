import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class LaunchWindow
{
    static final String workFolder = "//Users//kongweirui//Desktop//Java//Student-Information-Management-System//src";

    static void createMainWindow(JFrame LaunchWindow)
    {
        SimpleMotion.exitMotion(LaunchWindow);
        LaunchWindow.setVisible(false);
        LaunchWindow = null; //销毁窗口
        System.gc();
        try
        {
            MainWindow mainWindow = new MainWindow();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    public static void main(String[] args)
    {
        JFrame LaunchWindow = new JFrame("西安科技大学·学生管理系统");
        LaunchWindow.setLayout(new GridLayout(6, 1));
        LaunchWindow.setSize(400, 300);

        LaunchWindow.setResizable(false);
        LaunchWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //LaunchWindow.setLocation(600, 600);
//      设置图标panel和标签
        JPanel iconPanel = new JPanel();
        JLabel iconLabel = new JLabel();
        ImageIcon icon = new ImageIcon(workFolder + "//icon.jpeg");
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
        LaunchWindow.add(iconPanel);
        LaunchWindow.add(topPanel);
        LaunchWindow.add(userPanel);
        LaunchWindow.add(keyPanel);
        LaunchWindow.add(captcha);
        LaunchWindow.add(buttonPanel);


        JLabel nameLabel = new JLabel("用户名：");
        JTextField nameText = new JTextField("leo", 10);
        JLabel keyLabel = new JLabel("  密码：");
        JTextField keyText = new JPasswordField("123", 10);
        JButton loginButton = new JButton("登录");

//      放置控件
        iconPanel.add(iconLabel);
        topPanel.add(headLine);
        userPanel.add(nameLabel, nameText);
        keyPanel.add(keyLabel, keyText);
        buttonPanel.add(loginButton);




//      创建窗口
        SimpleMotion.centerize(LaunchWindow);
        SimpleMotion.openMotion(LaunchWindow, 400, 300);
//      登陆


        loginButton.addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent e)
            {
                if (nameText.getText().equals("leo") && keyText.getText().equals("123"))
                {
                    createMainWindow(LaunchWindow);

                }
            }
        });


        createMainWindow(LaunchWindow);

    }
}
