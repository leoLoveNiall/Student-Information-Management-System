package MyUtil;

import Window.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;
import java.util.stream.IntStream;

/**
 * MotionUtil is a util class consists of excellent and glorious animations,
 * most of whom apply to any supportive windows(Containers).
 * Extends none.
 * All static built-in.
 *
 * @author Kong Weirui
 * @since 6.5
 */
public class MotionUtil {
    public static void exitMotion(Window window, Window f) {
        exitMotion(window, f, false);
    }

    //退出--缩小动画，默认缩小至 getPivotal() 的坐标、且对于窗口dispose()而不是setVisible(false).
    public static void exitMotion(Window window, Window f, boolean meanToHide) {
        final int motionTick = 60;
        var wList = getULAArray(window.getWidth(), motionTick, DECREASE);
        var hList = getULAArray(window.getHeight(), motionTick, DECREASE);
        var pivotalXY = new int[2];
        if (f == null) pivotalXY = getPivotalXY(window);
        for (var i = 0; i < motionTick; i++) {
            assert hList != null;
            assert wList != null;
            //尽早结束
            if (motionTick - i <= 4) break;
            window.setSize(wList.get(i), hList.get(i));
            if (i > 3) {
                if (f != null) {
                    //降低割裂感
                    centerize(window, f);
                } else {
                    centerize(window, pivotalXY[0], pivotalXY[1]);
                }
            }
            sleep();
        }
        if (!meanToHide) {
            window.dispose();
        } else {
            window.setVisible(false);
        }
    }

    public static void openMotion(Window window, int width, int height, Window f) {
        window.setSize(0, 0);
        if (f == null) {
            centerize(window);
        } else {
            centerize(window, f);
        }
        window.setVisible(true);
        final var motionTick = 60;
        var wList = getULAArray(width, motionTick, INCREASE);
        var hList = getULAArray(height, motionTick, INCREASE);
        System.out.println(wList);
        System.out.println(hList);
        int[] pivotal = getPivotalXY(window);
        IntStream.range(0, motionTick).forEach(i -> {
            assert wList != null;
            assert hList != null;
            if ((wList.get(i) >= 3 || hList.get(i) >= 3)) {
                window.setSize(wList.get(i), hList.get(i));
                if (f == null) {
                    centerize(window, pivotal[0], pivotal[1]);
                } else {
                    centerize(window, f);
                }
            }
            sleep();
        });
    }

    public static void displayErrorInfo(JTextField tf, String info) {
        tf.setText(info);
        tf.setEditable(false);
        tf.setText(info);

        var timer = new Timer();// 实例化Timer类
        timer.schedule(new TimerTask() {
            public void run() {
                tf.setEditable(true);
                tf.setText("");
                this.cancel();
            }
        }, 1000);

    }

    /**
     * To let a window is located in the center of its father.
     */
    public static void centerize(Window window, Window father) {
        var w = window.getWidth();
        var h = window.getHeight();
        window.setLocation(father.getX() + (father.getWidth() - w) / 2,
                father.getY() + (father.getHeight() - h) / 2);
    }
    /**
     * To let a window is located in the center of the screen.
     */
    public static void centerize(Window window) {
        var w = window.getWidth();
        var h = window.getHeight();
        var kit = Toolkit.getDefaultToolkit();
        var screenSize = kit.getScreenSize();
        var sw = screenSize.width;
        var sh = screenSize.height;
        window.setLocation((sw - w) / 2, (sh - h) / 2);
    }

    public static void centerize(Window window, int pivotalX, int pivotalY) {
        window.setLocation(pivotalX - window.getWidth() / 2, pivotalY - window.getHeight() / 2);
    }

    /**
     * Aim get thw central point of any given window.
     *
     * @param window aimed window
     */
    public static int[] getPivotalXY(Window window) {
        return new int[]{window.getX() + window.getWidth() / 2, window.getY() + window.getHeight() / 2};
    }

    public static void upAndDown_A(Window c, int h) {
        final var seq = 30;
        final int x = c.getX(), y = c.getY();
        var heightList = getULAArray(h, seq, DECREASE);
        IntStream.range(0, seq).forEach(i -> {
            assert heightList != null;
            c.setBounds(x, y, c.getWidth(), heightList.get(i));
            sleep();
        });
    }

    public static void upAndDown_B(Window c, int h) {
        final var seq = 30;
        final int x = c.getX(), y = c.getY();
        var heightList = getULAArray(h, seq, INCREASE);
        IntStream.range(0, seq).forEach(i -> {
            assert heightList != null;
            c.setBounds(x, y, c.getWidth(), heightList.get(i));
            sleep();
        });
    }

    public static void exitToEdge(Window c) {
        final var seq = 50;
        final var height = c.getHeight();
        final var width = c.getWidth();
        final var sx = c.getX();
        final var sy = c.getY();
        var kit = Toolkit.getDefaultToolkit();
        var screenSize = kit.getScreenSize();
        final var sw = screenSize.width;
        final var sh = screenSize.height;
        var xList = getULAArray(sw - sx, seq, INCREASE);
        var yList = getULAArray(sh - sy, seq, INCREASE);
        var wList = getULAArray(width, seq, DECREASE);
        var hList = getULAArray(height, seq, DECREASE);

        for (int i = 0; i < seq; i++) {
            assert yList != null;
            assert wList != null;
            assert xList != null;
            assert hList != null;
            c.setBounds(sx + xList.get(i), sy + yList.get(i), wList.get(i), hList.get(i));
            sleep();
        }
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


    static final int INCREASE = 0, DECREASE = 1;

    /**
     * 目标是非线性动画，接受参数是maximum和scope
     * 曲线函数使用arctan实现
     *
     * @param maximum 表示最大值，由此为基础进行非线性积分运算
     * @param scope   频率（数组长度）
     * @param gra     输出数列是顺还是倒序
     * @return 返回一个ArrayList
     */
    public static ArrayList<Integer> getULAArray(int maximum, int scope, int gra) {
        var periodLastFor = 30; // 50\100 at most
        var list = new ArrayList<Integer>();
        /*
         * y = max, x = scope;
         * using arc-tangent x function:
         */
        try {
            //  使用100是为了减少double的使用，可以减少转化
            for (var i = 0; i < scope; i++) {
                if (i * 100 / scope < periodLastFor) {
                    list.add((int) (Math.atan(Math.PI / 2 * i / scope) * 100));
                }
                if (i * 100 / scope >= periodLastFor && i * 100 / scope < 100 - periodLastFor) {
                    list.add(100);
                }
                if (i * 100 / scope >= 100 - periodLastFor) {
                    list.add(list.get(scope - i - 1));
                }
            }
        } catch (Exception e) {
        }

        var finalList = new ArrayList<Integer>();
        var sum = 0.0;
        for (int cnt : list) {
            sum += cnt;
        }

        for (var cnt = 0; cnt < list.size(); cnt++) {
            if (cnt == 0) {
                finalList.add((int) (list.get(cnt) * maximum / sum));
            } else {
                finalList.add((int) (list.get(cnt) * maximum / sum) + finalList.get(cnt - 1));
            }
        }
        //不能直接积分运算，故使用模拟近似计算，因此max不能过小

        switch (gra) {
            case INCREASE:
                return finalList;
            case DECREASE:
                var realFinalList = new ArrayList<Integer>();
                for (int tmp : finalList) {
                    realFinalList.add(maximum - tmp);
                }
                return realFinalList;
            default:
                return null;
        }
    }

    /**
     * 这里是为了减少代码量，添加一个KeyListener，当按下esc使退出
     *
     * @param Motion 动画方式，参见静态变量
     */
    public static void addEscToExist(Window w, Window f, int Motion) {
        switch (Motion) {
            case MainWindow.TO_EDGE -> w.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        MotionUtil.exitToEdge(w);
                    }
                }
            });
            case MainWindow.IN_WARD -> w.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        MotionUtil.exitMotion(w, f);
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        System.out.println(getULAArray(2000, 400, DECREASE));
    }
}