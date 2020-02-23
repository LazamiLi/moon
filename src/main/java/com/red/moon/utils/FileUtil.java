package com.red.moon.utils;

import com.red.moon.bean.configbean.FileUploadBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;
@Component
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    @Autowired
    private  FileUploadBean fileUploadBean;
    /**
     * 上传文件
     *
     * @param file
     * @throws Exception
     */
    public  String uploadFile(MultipartFile file) {

        UUID uuid = UUID.randomUUID();
        String originalFilename = file.getOriginalFilename();
        String fileName=  fileUploadBean.getPosition()+uuid + originalFilename.substring(originalFilename.lastIndexOf("."));
        File target = new File(fileName);
        logger.info("上传文件{}",fileName);
        try {
            file.transferTo(target);
            return  fileName;
        } catch (IOException e) {
            logger.error("上传文件失败", e);
            return "";
        }
    }

    public  String downloadFile(String path) {
        String filePath = fileUploadBean.getPosition() + path;
        logger.info("下载文件文件{}",filePath);
       return filePath;
    }
}