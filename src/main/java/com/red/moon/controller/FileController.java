package com.red.moon.controller;

import com.red.moon.utils.FileUtil;
import com.red.moon.utils.Result;
import com.red.moon.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * @Author: lihui
 * @Date: 2020/2/21 9:12
 */
@RestController
@RequestMapping("file")
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private  FileUtil fileUtil;

    @RequestMapping("upload")
    public Result uploadFile(MultipartFile file){
        return ResultUtil.success(fileUtil.uploadFile(file));
    }
    @RequestMapping("download")
    public HttpServletResponse downloadFile(String fileName, HttpServletResponse response) {
        try {
            //全称路径
            String filePath = fileUtil.downloadFile(fileName);
            File file = new File(filePath);
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            logger.error("下载失败" );
            ex.printStackTrace();
        }
        return response;
    }

}
