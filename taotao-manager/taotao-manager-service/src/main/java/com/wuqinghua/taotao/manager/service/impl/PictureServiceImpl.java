package com.wuqinghua.taotao.manager.service.impl;

import com.wuqinghua.taotao.common.utils.FTPUtil;
import com.wuqinghua.taotao.manager.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuqinghua on 17/10/25.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PictureServiceImpl implements PictureService {

    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.uploadDir}")
    private String uploadDir;
    @Value("${pic.path}")
    private String picPath;

    @Override
    public Map<String, Object> upload(String fileName, InputStream inputStream) {

        String filePath = new DateTime().toString("yyyy/MM/dd");
        boolean uploadFile = FTPUtil.uploadFile(host, port, username, password, uploadDir, filePath, fileName, inputStream);
        Map<String, Object> result = new HashMap<>();
        if (uploadFile) {
            result.put("error", 0);
            result.put("url", picPath + "/" + filePath + "/" + fileName);
        } else {
            result.put("error", 1);
            result.put("message", "文件上传失败。");
        }

        return result;
    }
}
