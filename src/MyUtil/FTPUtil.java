package MyUtil;

import org.apache.commons.net.ftp.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * FTP工具类 基于Apache commons-net-3.6.jar 实现
 *
 * @author https://blog.csdn.net/magi1201/article/details/84573798
 */
public class FTPUtil {

    // ftp服务器地址
    public String hostname = "ftp.hopontop.asia";

    // ftp登录账号
    public String username = "学生信息管理系统";

    // ftp登录密码fileName
    public String password = "Fkd@eo24j9aHs21";

    public FTPClient ftpClient = null;

    /**
     * 连接FTP服务器
     * 默认连接成功
     */
    public boolean loginFtp() {
        ftpClient = new FTPClient();

        ftpClient.setControlEncoding("UTF-8");
        try {
            System.out.println("连接ftp服务器:" + this.hostname);
            // 连接登录FTP服务器
            ftpClient.connect(hostname);
            if (ftpClient.login(username, password)) {
                System.out.println("connect successfully:" + this.hostname);
                return true;
            } else {
                System.out.println("connect failed:" + this.hostname);
                return false;
            }
            //this.port = ftpClient.getPassivePort();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 上传文件
     *
     * @param pathName       ftp服务保存地址
     * @param fileName       上传到ftp的文件名
     * @param originFileName 待上传文件的名称（绝对地址）
     * @return 成功与否
     */
    public boolean uploadFile(String pathName, String fileName, String originFileName) {
        var flag = false;
        InputStream inputStream = null;
        try {
            loginFtp();
            System.out.println("开始上传文件");
            inputStream = new FileInputStream(originFileName);

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            mkdir(pathName);
            ftpClient.makeDirectory(pathName);
            ftpClient.changeWorkingDirectory(pathName);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("上传文件成功");
        } catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 上传文件
     *
     * @param pathName    ftp服务保存地址
     * @param fileName    上传到ftp的文件名
     * @param inputStream 输入文件流
     */
    public boolean uploadFile(String pathName, String fileName,
                              InputStream inputStream) {
        boolean flag = false;
        try {
            System.out.println("开始上传文件");
            loginFtp();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            mkdir(pathName);
            ftpClient.makeDirectory(pathName);
            ftpClient.changeWorkingDirectory(pathName);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("上传文件成功");
        } catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 改变工作路径
     *
     * @param directory 工作路径
     */
    public boolean changeWorkingDirectory(String directory) {
        var flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                System.out.println("进入文件夹" + directory + " 成功！");

            } else {
                System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    /**
     * 创建目录
     * 如果FTP服务器已存在该目录，则不创建；否则，创建该目录
     *
     * @param remoteDir 远程目录
     * @return 成功与否
     */
    public boolean mkdir(String remoteDir) throws IOException {
        var directory = remoteDir + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(directory)) {
            int start, end;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            var path = "";
            var paths = "";
            do {
                String subDirectory =
                        new String(remoteDir.substring(start, end).getBytes("GBK"), StandardCharsets.ISO_8859_1);
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
            } while (end > start);
        }
        return true;
    }

    /**
     * 判断ftp服务器文件是否存在
     *
     */
    public boolean existFile(String path) throws IOException {
        var flag = false;
        var ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 创建文件目录
     *
     * @param dir 待创建目录
     * @return 成功与否
     */
    public boolean makeDirectory(String dir) {
        var flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" + dir + " 成功！");

            } else {
                System.out.println("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 下载文件
     *
     * @param pathName  FTP服务器文件目录
     * @param filename  文件名称
     * @param localFile 下载后的文件路径和名称
     */
    public boolean downloadFile(String pathName, String filename, String localFile) {
        var flag = false;
        var fileExist = false;
        OutputStream os = null;
        try {
            // 连接FTP服务
            loginFtp();

            System.out.println("开始下载文件");

            // 默认FTP登录到FTP根目录下，切换目录到待下载文件目录
            ftpClient.changeWorkingDirectory(pathName);
            var ftpFiles = ftpClient.listFiles();
            for (var file : ftpFiles) {

                // 查找待下载文件
                if (filename.equalsIgnoreCase(file.getName())) {
                    fileExist = true;
                    os = new FileOutputStream(localFile);
                    // 从远程服务将文件写回到本地流
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();

            if (fileExist) {
                flag = true;
                System.out.println("下载文件成功");
            } else {
                System.out.println("下载目录未找到该文件，文件名" + pathName + "/" + filename);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 删除文件
     *
     * @param pathName FTP服务器保存目录
     * @param filename 要删除的文件名称
     * @return 成功与否
     */
    public boolean deleteFile(String pathName, String filename) {
        var flag = false;
        try {

            loginFtp();

            System.out.println("开始删除文件");

            // 切换FTP目录
            ftpClient.changeWorkingDirectory(pathName);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
            System.out.println("删除文件成功");
        } catch (Exception e) {
            System.out.println("删除文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

}

