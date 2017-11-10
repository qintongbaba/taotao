package com.wuqinghua.taotao.common.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wuqinghua on 17/10/25.
 * ftp上传和下载工具
 */
public class FTPUtil {

    /**
     * @param host        ftp服务器的地址
     * @param port        ftp服务器的端口
     * @param username    ftp用户名
     * @param password    ftp密码
     * @param uploadDir   上传目录
     * @param filePath    上传子目录  如"2017/10/25"
     * @param fileName    文件名称
     * @param inputStream 文件输入流
     * @return
     */
    public static boolean uploadFile(String host, int port, String username, String password, String
            uploadDir, String filePath, String fileName, InputStream inputStream) {
        boolean result = true;
        //1.创建FTP客户端
        FTPClient ftpClient = new FTPClient();


        try {
            //2.连接ftp
            ftpClient.connect(host, port);

            //3.登录
            boolean login = ftpClient.login(username, password);
            if (!login) {
                ftpClient.disconnect();
                return result;
            }

            //4.切换到上传目录
            boolean b = ftpClient.changeWorkingDirectory(uploadDir + "/" + filePath);
            if (!b) { //没有切换成功,创建目录
                String[] dirs = filePath.split("/");
                String tempDir = uploadDir;
                for (String dir : dirs) {
                    if (dir == null || "".equals(dir)) continue;
                    tempDir = tempDir + "/" + dir;
                    b = ftpClient.changeWorkingDirectory(tempDir);
                    if (!b) {
                        b = ftpClient.makeDirectory(tempDir);  //创建目录
                        if (!b) {
                            return result;
                        } else {
                            ftpClient.changeWorkingDirectory(tempDir);
                        }
                    }
                }
            }

            //5.设置上传文件的类型
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            //6.上传文件
            result = ftpClient.storeFile(fileName, inputStream);

            inputStream.close();
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

}
