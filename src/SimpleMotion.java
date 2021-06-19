import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.util.stream.IntStream;

public class SimpleMotion {
    public static void exitMotion(Window window) {
        var height = window.getHeight();
        var width = window.getWidth();
        final var motionTick = 50;
        for (var i = 0; i < motionTick; i++) {

            height -= height / (motionTick - i);
            width -= width / (motionTick - i);
            window.setSize(width, height);
            window.setLocation(
                    window.getX() + width / motionTick,
                    window.getY() + height / motionTick
            );
            sleep();
        }
        window.dispose();
    }

    public static void openMotion(Window window, int width, int height) {

        window.setSize(0, 0);
        centerize(window);
        window.setVisible(true);
        final var motionTick = 40;
        var h = 0; var w = 0;
        for (var i = 0; i < motionTick; i++) {
            h += height / motionTick;
            w += width / motionTick;
            window.setSize(w, h);
            window.setLocation(
                    window.getX() - w / motionTick,
                    window.getY() - h / motionTick
            );
            sleep();
            centerize(window);
        }
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

    static void centerize(Window c) {
        var w = c.getWidth();
        var h = c.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        var sw = screenSize.width;
        var sh = screenSize.height;
        c.setLocation(sw / 2 - w / 2, sh / 2 - h / 2);
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
        var sx = c.getX();
        var sy = c.getY();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        final var sw = screenSize.width;
        final var sh = screenSize.height;
        IntStream.range(0, seq).forEach(i -> {
            c.setBounds(c.getX() + (sw - sx) / seq, c.getY() + (sh - sy) / seq
                    , c.getWidth() - width / seq, c.getHeight() - height / seq);
            sleep();
        });
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