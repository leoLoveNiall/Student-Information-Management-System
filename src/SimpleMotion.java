
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.util.stream.IntStream;

public class SimpleMotion {
    public static void exitMotion(Window window) {
        //全部采用浮点数，使剧中效果更佳
        final double height = window.getHeight();
        final double width = window.getWidth();
        final double motionTick = 40;
        final double orgX = window.getX(), orgY = window.getY();
        int[][] settings = new int[(int) motionTick][];
        for (var i = 0; i < motionTick; i++) {
            settings[i] = new int[]{(int) (width - width / motionTick * i),
                    (int) (height - height / motionTick * i), (int) (orgX + 0.5 * width * (i / motionTick)),
                    (int) (orgY + 0.5 * height * (i / motionTick))};
        }
        //实际操作发现如果在下面的循环体中写的话会造成计算时间不如延迟时间，导致闪屏，因此，先计算后调用
        for (var i = 0; i < motionTick; i++) {
            window.setSize(settings[i][0], settings[i][1]);
            window.setLocation(settings[i][2], settings[i][3]);
            sleep(20);
        }
        window.dispose();
    }

    public static void openMotion(Window window, int width, int height, Window f) {

        window.setSize(0, 0);
        if (f == null) {
            centerize(window);
        } else {
            centerize(window, f);
        }
        window.setVisible(true);
        final var motionTick = 40;
        var h = 0;
        var w = 0;
        final var x = window.getX();
        final var y = window.getY();
        for (var i = 0; i < motionTick; i++) {
            h = height * i / motionTick;
            w = width * i / motionTick;
            window.setSize(w, h);
            window.setLocation(
                    x - w / 2,
                    y - h / 2
            );
            sleep();
        }
//        if (f == null) {
//            centerize(window);
//        } else {
//            centerize(window, f);
//        }
    }

    static void displayErrorInfo(JTextField tf, String info) {
        tf.setText(info);
        tf.setEditable(false);
        tf.setText(info);

        Timer timer = new Timer();// 实例化Timer类
        timer.schedule(new TimerTask() {
            public void run() {
                tf.setEditable(true);
                tf.setText("");
                this.cancel();
            }
        }, 1000);

        //System.out.println("延时1000");
    }

    static void centerize(Window window, Window father) {
        var w = window.getWidth();
        var h = window.getHeight();
        window.setLocation(father.getX() + (father.getWidth() - w) / 2, father.getY() + (father.getHeight() - h) / 2);
    }

    static void centerize(Window window) {
        var w = window.getWidth();
        var h = window.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        var sw = screenSize.width;
        var sh = screenSize.height;
        window.setLocation((sw - w) / 2, (sh - h) / 2);
    }

    public static void upAndDown_A(Window c, int h) {
        final var seq = 20;
        IntStream.range(0, seq).forEach(i -> {
            c.setSize(c.getWidth(), c.getHeight() - h / seq);
            sleep();
        });

    }

    public static void upAndDown_B(Window d, int h) {
        final var seq = 20;
        IntStream.range(0, seq).forEach(i -> {
            d.setSize(d.getWidth(), d.getHeight() + h / seq);
            sleep();
        });
    }

    public static void exitToEdge(Window c) {
        final var seq = 30;
        final var height = c.getHeight();
        final var width = c.getWidth();
        final var sx = c.getX();
        final var sy = c.getY();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        final var sw = screenSize.width;
        final var sh = screenSize.height;
        for (int i = 0; i < seq; i++) {
            c.setSize(c.getWidth() - width / seq, c.getHeight() - height / seq);
            c.setLocation(c.getX() + (sw - sx) / seq, c.getY() + (sh - sy) / seq);
            sleep();
        }

        //top spot (h,k)  random spot(m,n)
//        final double wX = w.getX();
//        final double wY = w.getY();
//
//        final double h = screenSize.getWidth();
//        final double k = screenSize.getHeight();
//        final double m = w.getWidth();
//        final double n = w.getHeight();
//        final double a = (k - n) / Math.pow(n - m, 2);

//        final double y1 = screenSize.width;
//        final double x1 = screenSize.height;
//        final double y2 = w.getY();
//        final double x2 = w.getX();
//        final double y3 = y2;
//        final double x3 = -y3;
//        final double m = y1 * y1 * y2 + y2 * y2 * y3 + y1 * y3 * y3 - y3 * y3 * y2 - y2 * y2 * y1 - y1 * y1 * y3;
//        final double a = (x1 * y2 + x2 * y3 + x3 * y1 - x3 * y2 - x2 * y1 - x1 * y3) / m;
//        final double b = (y1 * y1 * x2 + y2 * y2 * x3 + y3 * y3 * x1 - y3 * y3 * x2 - y2 * y2 * x1 - y1 * y1 * x3) / m;
//        final double c = (y1 * y1 * y2 * x3 + y2 * y2 * y3 * x1 + y3 * y3 * y1 * x2 - y3 * y3 * y2 * x1 - y2 * y2 * y1 * x3 - y1 * y1 * y3 * x2) / m;
//        double y = w.getX();
//        for (int i = 0; i < seq; i++) {
////            w.setBounds((int) (a * Math.pow(w.getx(), 2) - 2 * a * k * w.getx() + k * k + h),
////                    (int) (wx + i / seq * (k - wy)), (int) (m * (seq - i) / seq), (int) (n * (n - i) / seq));
//            y += (screenSize.width - y2) / seq;
//            w.setBounds((int)y,(int)(a*y*y+b*y+c),500,500);
//
//            sleep();
//        }


        c.dispose();
    }

    public static void sleep() {
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}