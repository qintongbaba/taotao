package com.wuqinghua.taotao.manager.web.controller;

import com.wuqinghua.taotao.manager.service.PictureService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by wuqinghua on 17/10/25.
 * 图片上传和下载
 */
@Controller
@RequestMapping("/pic")
public class PictureController {


    @Autowired
    private PictureService pictureService;

    /**
     * <pre>
     * //成功时
     * {
     * "error" : 0,
     * "url" : "http://www.example.com/path/to/file.ext"
     * }
     * //失败时
     * {
     * "error" : 1,
     * "message" : "错误信息"
     * }
     * </pre>
     */
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("uploadFile") MultipartFile uploadFile)
            throws IOException {
        String fileName = uploadFile.getOriginalFilename();
        InputStream inputStream = uploadFile.getInputStream();
        return pictureService.upload(fileName, inputStream);
    }
}
