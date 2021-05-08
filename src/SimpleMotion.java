import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.awt.Robot;

public class SimpleMotion
{
    public static void exitMotion(JFrame window)
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

    public static void exitMotion(JDialog window)
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

    public static void openMotion(JFrame window, int width, int height)
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

    static void openMotion(JDialog dialog, int width, int height)
    {
        dialog.setVisible(true);
        centerize(dialog);
        dialog.setSize(0, 0);

        final int motionTick = 40;
        int h = 0, w = 0;
        for (int i = 0; i < motionTick; i++)
        {
            h += height / motionTick;
            w += width / motionTick;
            dialog.setSize(w, h);
            dialog.setLocation(
                    dialog.getX() - w / motionTick,
                    dialog.getY() - h / motionTick
            );
            try
            {
                Thread.currentThread().sleep(15);
            } catch (InterruptedException e)
            {
            }
            centerize(dialog);
        }
        dialog.setSize(width, height);
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

    static void centerize(JFrame frame)
    {
        int w = frame.getWidth();
        int h = frame.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int sw = screenSize.width;
        int sh = screenSize.height;
        frame.setLocation(sw / 2 - w / 2, sh / 2 - h / 2);
    }

    static void centerize(JDialog dialog)
    {
        int w = dialog.getWidth();
        int h = dialog.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int sw = screenSize.width;
        int sh = screenSize.height;
        dialog.setLocation(sw / 2 - w / 2, sh / 2 - h / 2);
    }

    public static void upAndDown_A(JDialog d, int h)
    {
        final int seq = 20;
        for (int i = 0; i < seq; i++)
        {
            d.setSize(d.getWidth(), d.getHeight() - h / seq);
            try
            {
                Thread.currentThread().sleep(15);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }
    public static void upAndDown_B(JDialog d, int h)
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

    public static void upAndDown_A(JFrame f, int h)
    {
        final int seq = 20;
        for (int i = 0; i < seq; i++)
        {
            f.setSize(f.getWidth(), f.getHeight() - h / seq);
            try
            {
                Thread.currentThread().sleep(15);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }
    public static void upAndDown_B(JFrame f , int h)
    {
        final int seq = 20;

        for (int i = 0; i < seq; i++)
        {
            f.setSize(f.getWidth(), f.getHeight() + h / seq);
            try
            {
                Thread.currentThread().sleep(15);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        f.setSize(f.getWidth(), h);

    }
}
