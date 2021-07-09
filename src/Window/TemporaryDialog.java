package Window;

import FusionUIAsset.LoadingPanel;
import MyUtil.*;

import javax.swing.*;
import java.awt.*;

/**
 * This is a class for a temporary dialog(JFrame exactly).
 * Extends none.
 * All built-in.
 *
 * @author Kong Weirui
 * @since 6.5
 */
public class TemporaryDialog {
    JDialog dialog = new JDialog();
    int suspendTime = 1000;
    int width = 200, height = 100;
    boolean withOpeningMotion = true;
    boolean isAboutToExit = false;
    boolean showBar = true;

    public TemporaryDialog(String headline, int height, int width, int suspendTime, boolean withOpeningMotion, boolean withBar, Window f) {
        this.height = height;
        this.width = width;
        this.suspendTime = suspendTime;
        this.withOpeningMotion = withOpeningMotion;
        this.showBar = withBar;
        //考虑到简化代码的同时并保持简便的实现
        showDialog(headline, f);
    }

    public TemporaryDialog(String headline, int height, int width, int suspendTime, Window f, boolean showBar) {
        this.height = height;
        this.width = width;
        this.suspendTime = suspendTime;
        this.showBar = showBar;
        //考虑到简化代码的同时并保持简便的实现
        showDialog(headline, f);
    }

    public TemporaryDialog(String headline, int height, int width, Window f) {
        this.height = height;
        this.width = width;
        //考虑到简化代码的同时并保持简便的实现
        showDialog(headline, f);
    }

    public TemporaryDialog(String headline, int suspendTime, Window f) {
        this.suspendTime = suspendTime;
        showDialog(headline, f);
        //考虑到简化代码的同时并保持简便的实现
    }

    public TemporaryDialog(String headline, Window f, boolean showBar) {
        this.showBar = showBar;
        dialog.setAlwaysOnTop(true);
        dialog.setUndecorated(showBar);
        showDialog(headline, f);
    }

    public TemporaryDialog(String headline, Window f) {
        dialog.setAlwaysOnTop(true);
        dialog.setUndecorated(showBar);
        showDialog(headline, f);
    }

    public void showDialog(String headline, Window f) {
        //创建窗口
        dialog.setUndecorated(!showBar);
        var panel = new JPanel();
        dialog.add(panel);
        panel.add(new JLabel(headline));
        dialog.setSize(width, height);
        MotionUtil.centerize(dialog, f);
        if (withOpeningMotion) {
            MotionUtil.openMotion(dialog, width, height, f);
        } else {
            dialog.setVisible(true);
        }

        //销毁窗口
        new Thread(() -> {
            MotionUtil.sleep(suspendTime);
            isAboutToExit = true;
            MotionUtil.exitMotion(dialog, f);
        }).start();
    }

    public static void showLoadingCircleDialog(String infoText, int height, int width, boolean withOpeningMotion, Window f) {
        var loadDialog = new TemporaryDialog("", height + 30, width + 30, (int) (Math.random() * 10000) % 1000 + 2000, withOpeningMotion, false, f);
        new Thread(() -> new TemporaryDialog(infoText, MainWindow.MAIN_WINDOW, false)).start();
        var loadPanel = new LoadingPanel();
        loadDialog.dialog.add(loadPanel);
        loadPanel.setBackground(Color.WHITE);
        loadPanel.show();
        MotionUtil.centerize(loadDialog.dialog, MainWindow.MAIN_WINDOW);
        new Thread(() -> {
            while (true) {
                MotionUtil.sleep(50);//降低占用率
                if (loadDialog.isAboutToExit) break;
            }
            while (loadPanel.miniCounter < 200) {
                loadPanel.miniCounter += 10;
                MotionUtil.sleep(40);
            }
        }).start();
    }

}
