package com.ccsu.jsn.util;

import org.apache.commons.net.ftp.FTP;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-10-31 22:12
 */
//@Component
public class FTPUtil {

    private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private static String ip = "192.168.6.170";

    private static String user = "ftpuser";

    private static String password = "123456";

    private static int port = 21;

//    FTPUtil(String ip,int port,String user,String password){
//        FTPUtil.ip = ip;
//        FTPUtil.port = port;
//        FTPUtil.user = user;
//        FTPUtil.password = password;
//        logger.info("在构造方法中ip:{},user:{},password:{}",ip,user,password);
//    }

    public static boolean  uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil();
        logger.info("开始连接FTP服务器");
        //把异常抛给service层，不在此处理
        boolean result = ftpUtil.uploadFile("img",fileList);
        logger.info("开始连接FTP服务器，结束上传，上传结果{}",result);
        return result;
    }

    private boolean uploadFile(String remotePath,List<File> fileList){
        boolean uploaded = true;
        FileInputStream fis = null;
//        连接FTP
        logger.info("ip:{},user:{},password:{}",ip,user,password);
        if (connectServer(ip,user,password)){
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(102400);
                ftpClient.setConnectTimeout(9000);
                System.out.println("超时时间"+ftpClient.getDefaultTimeout());
                ftpClient.setControlEncoding("UTF-8");
                //设置成二进制格式可以防止乱码
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                //被动模式存储
                ftpClient.enterLocalPassiveMode();
                //遍历文件存储
                for(File fileItem : fileList){
                    //把文件转成文件流
                    fis = new FileInputStream(fileItem);
                    //调用storeFile方法存储
                    ftpClient.storeFile(fileItem.getName(),fis);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return uploaded;
    }

    private FTPClient ftpClient ;


    public boolean connectServer(String ip, String user,String password ){
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try{
            ftpClient.connect(ip);
            isSuccess =  ftpClient.login(user,password);
        }catch (IOException e){
            logger.error("FTP服务器连接失败",e);
        }
        return isSuccess;
    }


//    public static String getIp() {
//        return ip;
//    }
//    @Value("${ftp.server.ip}")
//    public void setIp(String ip) {
//        FTPUtil.ip = ip;
//    }
//
//    public static String getUser() {
//        return user;
//    }
//
//    @Value("${ftp.server.user}")
//    public void setUser(String user) {
//        FTPUtil.user = user;
//    }
//
//    public static String getPassword() {
//        return password;
//    }
//    @Value("${ftp.server.password}")
//    public void setPassword(String password) {
//        FTPUtil.password = password;
//    }
//
//    public static int getPort() {
//        return port;
//    }
//
//    @Value("${ftp.server.port}")
//    public void setPort(int port) {
//        FTPUtil.port = port;
//    }

    //    public static void main(String[] args) {
//        System.out.println(new FTPUtil().connectServer("192.168.6.170","ftpuser","123456"));
//    }
}
