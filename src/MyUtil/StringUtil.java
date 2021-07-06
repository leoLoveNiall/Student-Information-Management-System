package MyUtil;

import FusionUIAsset.QuickPanelWithLabelAndText;

import java.awt.*;

public class StringUtil {
    /**
     * 业务需求判断某个字符串是否全为数字
     * @param s 输入字符串
     * @return 返回验证结果
     */
    public static boolean verifyInteger(String s) {
        char[] cs = s.toCharArray();
        for (var c : cs) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 业务需求判断某个QuickPanelWithLabelAndText.text.getText()是否全为数字且位于特定的数据范围
     * @param gradeEditPanel 传入FusionPanel
     * @param max 限定的最大值
     * @return 返回是否验证成功
     * @throws InterruptedException 忽略错误
     * @throws AWTException 忽略错误
     */
    public static boolean verifyGradeEditLegit(QuickPanelWithLabelAndText gradeEditPanel, int max) throws InterruptedException, AWTException {
        //其他字符验证
        if (!StringUtil.verifyInteger(gradeEditPanel.getFilteredText())) {
            MotionUtil.displayErrorInfo(gradeEditPanel.text, "输入了不合法的字符");
            return false;
        }

        //区间位于0～max
        if (Integer.parseInt(gradeEditPanel.getFilteredText()) > max || Integer.parseInt(gradeEditPanel.getFilteredText()) < 0) {
            MotionUtil.displayErrorInfo(gradeEditPanel.text, "须为0～" + max);
            return false;
        }
        return true;
    }

    public static boolean verifyGradeEditLegit(QuickPanelWithLabelAndText gradeEditPanel) throws InterruptedException, AWTException {
        return verifyGradeEditLegit(gradeEditPanel, 100);
    }
}
