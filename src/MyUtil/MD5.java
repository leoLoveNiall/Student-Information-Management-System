package MyUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//以下MD5加密代码来自网络
/*      作者：cxm
        链接：https://zhuanlan.zhihu.com/p/269031563
        来源：知乎
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/
public class MD5 {
    public static String getMD5(String src) {
        //计时
        var starTime = System.currentTimeMillis();
        // 需要加密的字符串
        try {
            // 加密对象，指定加密方式
            var md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = src.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[]{'0', '1', '2', '3', '4', '5',
                    '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            var sb = new StringBuilder();
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
            // 打印加密后的字符串

            //计时
            var endTime = System.currentTimeMillis();
            var Time = endTime - starTime;
            System.out.println("MD5计算用时：" + Time + "ms");


            return String.valueOf(sb);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
