package com.wuqinghua.taotao.manager.service;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by wuqinghua on 17/10/25.
 */
public interface PictureService {


    /**
     * 文件上传到FTP服务器上
     *
     * @param fileName    文件名称
     * @param inputStream 文件输入流
     */
    public Map<String, Object> upload(String fileName, InputStream inputStream);
}
