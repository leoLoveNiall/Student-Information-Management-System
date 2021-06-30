package FusionUIAsset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Captcha extends JPanel {
    ImageIcon icon;
    JTextArea textArea = new JTextArea(0,8);
    String code;
    public Captcha(){
        var captcha = new ImageVerifyCode();
        icon = new ImageIcon(captcha.getImage());
        code = captcha.getText();


        var imageContainer = new JLabel();
        imageContainer.setIcon(icon);

        this.add(new JLabel("验证码"));
        this.add(imageContainer);
        this.add(textArea);

        /////////
        textArea.setText(captcha.getText());
        ////////

        imageContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                imageContainer.setIcon(new ImageIcon(captcha.getImage()));
                textArea.setText(captcha.getText());
                code = captcha.getText();
            }
        });
    }

    public boolean isAuthorized() {
        String code = this.code.toLowerCase();
        String text = textArea.getText().toLowerCase(Locale.ROOT);
        System.out.println("code:"+code+" text:"+text);
        return code.equals(text);
    }
    public static void main(String[] args){
        var imageVerifyCode = new ImageVerifyCode();
        imageVerifyCode.getImage();
        System.out.println(imageVerifyCode.getText());
    }
}

//基于 https://blog.csdn.net/weixin_43625121/article/details/109180841 改进

class ImageVerifyCode {
    private final int w = 50;
    private final int h = 28;
    Random r = new Random();

    private final String[] fontNames = {"Arial", "Times New Roman","Comic Sans MS"};
    // 可选字符
    private final String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
    // 背景色
    private final Color bgColor = new Color(255, 255, 255);
    // 验证码上的文本
    private String text;

    // 生成随机的颜色
    private Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }

    // 生成随机的字体
    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];// 生成随机的字体名称
        int style = r.nextInt(4);// 生成随机的样式, 0(无样式), 1(粗体), 2(斜体), 3(粗体+斜体)
        int size = r.nextInt(5) + 20; // 生成随机字号
        return new Font(fontName, style, size);
    }

    // 画干扰线
    private void drawLine(BufferedImage image) {
        int num = 3;// 一共画3条
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {// 生成两个点的坐标，即4个值
            int x1 = r.nextInt(w);
            int y1 = r.nextInt(h);
            int x2 = r.nextInt(w);
            int y2 = r.nextInt(h);
            g2.setStroke(new BasicStroke(1.5F));
            g2.setColor(Color.BLUE); // 干扰线是蓝色
            g2.drawLine(x1, y1, x2, y2);// 画线
        }
    }

    // 随机生成一个字符
    private char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    // 创建BufferedImage
    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(bgColor);
        g2.fillRect(0, 0, w, h);
        return image;
    }

    // ！调用这个方法得到验证码
    public BufferedImage getImage() {
        BufferedImage image = createImage();// 创建图片缓冲区
        Graphics2D g2 = (Graphics2D) image.getGraphics();// 得到绘制环境
        StringBuilder sb = new StringBuilder();// 用来装载生成的验证码文本
        // 向图片中画4个字符
        for (int i = 0; i < 4; i++) {// 循环四次，每次生成一个字符
            String s = randomChar() + "";// 随机生成一个字母
            sb.append(s); // 把字母添加到sb中
            float x = i * 1.0F * w / 4; // 设置当前字符的x轴坐标
            g2.setFont(randomFont()); // 设置随机字体
            g2.setColor(randomColor()); // 设置随机颜色
            g2.drawString(s, x, h - 5); // 画图
        }
        text = sb.toString(); // 把生成的字符串赋给了this.text
        drawLine(image); // 添加干扰线
        return image;
    }

    // 返回验证码图片上的文本
    public String getText() {
        return text;
    }

    /**
     * 将BufferedImage转换为InputStream
     *
     */
    public InputStream bufferedImageToInputStream(BufferedImage image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
