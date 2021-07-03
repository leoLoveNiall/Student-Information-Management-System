package FusionUIAsset;

import javax.swing.*;

public class CompoundJFrame {
    public JFrame frame = null;
    public JFrame father = null;
    public int x=0;
    public int y=0;
    public int width=0;
    public int height = 0;
    //集合类不需要私有

    public CompoundJFrame(JFrame frame, JFrame father, int width, int height) {
        this.frame = frame;
        this.father = father;
        this.width = width;
        this.height = height;
    }
}
