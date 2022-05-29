package com.atlx.utils;

import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 上传头像处理
 */
@Component
public class UpAndDownloadFile {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Value("${server.ip}")
    private String serverIp;

    public String uploadFile(MultipartFile file,String user) throws IOException {

        String originalFilename = file.getOriginalFilename();
        File uploadFile = new File(fileUploadPath + originalFilename);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        String url = "http://" + serverIp + ":8088/"+user+"/" + originalFilename;
        file.transferTo(uploadFile);
        return url;
    }

    public void downloadFile(String originalFilename, HttpServletResponse response)throws IOException{
        File uploadFile = new File(fileUploadPath + originalFilename);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(originalFilename, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }
}
