import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//在GitHub的源码的基础上做了一定修改

public class LoadingPanel extends JPanel {

    Timer timer;
    int delay;
    int startAngle;
    int arcAngle = 0;
    int orientation;
    static final int CLOCKWISE = 0;
    static final int ANTICLOCKWISE = 1;
    int miniCounter = 0;

    public LoadingPanel() {
        this.delay = 10;
        this.orientation = CLOCKWISE;
        init();
    }

    public LoadingPanel(int delay) {
        this.delay = delay;
        this.orientation = CLOCKWISE;
        init();
    }

    public LoadingPanel(int delay, int orientation) {
        this.delay = delay;
        this.orientation = orientation;
        init();
    }

    @Override
    public void show() {
        this.timer.start();
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    private void init() {
        this.timer = new Timer(delay, new ReboundListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawArc(g);
    }

    private void drawArc(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        //抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        //设置画笔颜色
        g2d.setColor(Color.WHITE);
        g2d.drawArc(width / 2 - 110 + miniCounter / 2, height / 2 - 110 + miniCounter / 2, 20 + 200 - miniCounter, 20 + 200 - miniCounter, 0, 360);
        g2d.setColor(new Color(0x3a9f4));
        g2d.fillArc(width / 2 - 110 + miniCounter / 2, height / 2 - 110 + miniCounter / 2, 20 + 200 - miniCounter, 20 + 200 - miniCounter, startAngle, arcAngle);
        g2d.setColor(Color.WHITE);
        g2d.fillArc(width / 2 - 105 + miniCounter / 2, height / 2 - 105 + miniCounter / 2, 20 + 190 - miniCounter, 20 + 190 - miniCounter, 0, 360);
        g2d.dispose();
    }

    private class ReboundListener implements ActionListener {

        private int o = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (startAngle < 360) {
                //控制每个DELAY周期旋转的角度，+ 为逆时针  - 为顺时针
                switch (orientation) {
                    case ANTICLOCKWISE -> startAngle -= 5;
                    default -> startAngle += 5;
                }
            } else {
                startAngle = 0;
            }

            if (o == 0) {
                if (arcAngle >= 355) {
                    o = 1;
                    orientation = ANTICLOCKWISE;
                } else {
                    if (orientation == CLOCKWISE) {
                        arcAngle += 5;
                    }
                }
            } else {
                if (arcAngle <= 5) {
                    o = 0;
                    orientation = CLOCKWISE;
                } else {
                    if (orientation == ANTICLOCKWISE) {
                        arcAngle -= 5;
                    }
                }
            }

            repaint();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        LoadingPanel lodingPanel = new LoadingPanel();
        lodingPanel.setBackground(Color.WHITE);
        lodingPanel.show();
        frame.add(lodingPanel);
        frame.setVisible(true);
    }
}

