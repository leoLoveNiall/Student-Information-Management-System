package FusionUIAsset;

import javax.swing.*;

/**
 * This is a fusion class for JFrame.
 * By using CompoundFrame to transfer argument easily and succinctly.
 *
 * @author Kong Weirui
 * @since 1.0
 */
public class CompoundJFrame {
    public JFrame frame = null;
    public JFrame father = null;
    public int x = 0;
    public int y = 0;
    public int width = 0;
    public int height = 0;
    //集合类不需要私有

    public CompoundJFrame(JFrame frame, JFrame father, int width, int height) {
        this.frame = frame;
        this.father = father;
        this.width = width;
        this.height = height;
    }
}
