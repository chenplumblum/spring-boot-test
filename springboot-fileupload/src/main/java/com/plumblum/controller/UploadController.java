package com.plumblum.controller;



import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: cpb
 * @Date: 2018/9/3 19:36
 * @Description:
 */
@Controller
public class UploadController {
    @RequestMapping("/")
    public String index() {
        return "upload";
    }


    @RequestMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, HttpServletRequest request) {
        // MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "result";
        }
        // 取文件格式后缀名
        // String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));

        String filename = file.getOriginalFilename();// 获取文件名
        String path = request.getServletContext().getRealPath("/upload/" + filename);// 存放位置

        File savefile = new File(path);
        //判断上传文件的保存目录是否存在
        if (!savefile.exists() && !savefile.isDirectory()) {
            System.out.println(path+"目录不存在，需要创建");
            //创建目录
            savefile.mkdir();
        }
        try {
            // FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
            FileUtils.copyInputStreamToFile(file.getInputStream(), savefile);// 复制临时文件到指定目录下
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "message";
    }
}
