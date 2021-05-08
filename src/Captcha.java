import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Captcha extends JPanel
{
    String captcha = "";

    Captcha()
    {
        setRandomCaptcha();
    }

    void setRandomCaptcha()
    {
        captcha = "";
        for (int cnt = 0; cnt < 5; cnt++)
        {
            Integer random = (int) (Math.random() * 10000) % 76;
            if (random <= 50)
            {
                random%=10;
                captcha += (random.toString());
            }
            if (random > 50) captcha = captcha.concat(Character.toString('A' + random - 50));

            //这里为了使数次的概率更高一些，降低验证码的输入难度
        }
    }

    public String getCaptcha()
    {
        return captcha;
    }
    public Color getRandomColor()
    {
        //获得随机颜色
        Random random = new Random();
        int R=random.nextInt(255),G=random.nextInt(255),B=random.nextInt(255);
        return new Color(R,G,B);
    }


    public static void main(String[] args)
    {
        Captcha test = new Captcha();

        System.out.println(test.getCaptcha());
    }



}