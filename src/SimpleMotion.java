import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class SimpleMotion
{
    public static void exitMotion(Component window)
    {
        int height = window.getHeight();
        int width = window.getWidth();
        final int motionTick = 50;
        for (int i = 0; i < motionTick; i++)
        {

            height -= height / (motionTick - i);
            width -= width / (motionTick - i);
            window.setSize(width, height);
            window.setLocation(
                    window.getX() + width / motionTick,
                    window.getY() + height / motionTick
            );
            try
            {
                Thread.currentThread().sleep(15);
            } catch (InterruptedException e)
            {

            }
        }
    }

    public static void openMotion(Component window, int width, int height)
    {

        window.setSize(0, 0);
        window.setVisible(true);
        final int motionTick = 40;
        int h = 0, w = 0;
        for (int i = 0; i < motionTick; i++)
        {
            h += height / motionTick;
            w += width / motionTick;
            window.setSize(w, h);
            window.setLocation(
                    window.getX() - w / motionTick,
                    window.getY() - h / motionTick
            );
            try
            {
                Thread.currentThread().sleep(15);
            } catch (InterruptedException e)
            {

            }
            centerize(window);
        }
        window.setSize(width, height);
    }

    static void displayErrorInfo(JTextField tf, String info) throws InterruptedException, AWTException
    {
        tf.setText(info);
        tf.setEditable(false);
        tf.setText(info);

        Timer timer = new Timer();// 实例化Timer类
        timer.schedule(new TimerTask()
        {
            public void run()
            {

                tf.setEditable(true);
                tf.setText("");
                this.cancel();
            }
        }, 1000);
        System.out.println("延时1000");
    }

    static void centerize(Component c)
    {
        int w = c.getWidth();
        int h = c.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int sw = screenSize.width;
        int sh = screenSize.height;
        c.setLocation(sw / 2 - w / 2, sh / 2 - h / 2);
    }

    public static void upAndDown_A(Component c, int h)
    {
        final int seq = 20;
        for (int i = 0; i < seq; i++)
        {
            c.setSize(c.getWidth(), c.getHeight() - h / seq);
            try
            {
                Thread.currentThread().sleep(15);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

    public static void upAndDown_B(Component d, int h)
    {
        final int seq = 20;

        for (int i = 0; i < seq; i++)
        {
            d.setSize(d.getWidth(), d.getHeight() + h / seq);
            try
            {
                Thread.currentThread().sleep(15);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        d.setSize(d.getWidth(), h);

    }
}