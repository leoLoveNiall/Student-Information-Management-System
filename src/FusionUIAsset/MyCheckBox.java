package FusionUIAsset;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import DataClassAsset.*;

public class MyCheckBox extends JCheckBox {
    public MyCheckBox(String s, boolean selected, boolean enabled) {
        //设定一个初始化的操作
        super(s, selected);
        super.setEnabled(enabled);
    }

    public void setMyCheckBoxStatus(boolean selected, boolean enabled) {
        super.setSelected(selected);
        super.setEnabled(enabled);
    }

    public static void quicklySetOneCheckBoxSelected(MyCheckBox c1, MyCheckBox c2, MyCheckBox c3, Student s) {
        c1.setSelected(false);
        c2.setSelected(false);
        c3.setSelected(false);
        c1.setEnabled(false);
        c2.setEnabled(false);
        c3.setEnabled(false);
        switch (s.getTag()) {
            case "BA" -> makeMyCheckBoxAbleButNotUsable(c1);
            case "MA" -> makeMyCheckBoxAbleButNotUsable(c2);
            case "DO" -> makeMyCheckBoxAbleButNotUsable(c3);
        }

    }


    public static void makeMyCheckBoxAbleButNotUsable(MyCheckBox supreme) {
        supreme.setEnabled(true);
        supreme.setSelected(true);
        supreme.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                supreme.setSelected(true);
            }
        });
    }
}
