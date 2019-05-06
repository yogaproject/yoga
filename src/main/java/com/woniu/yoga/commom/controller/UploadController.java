package com.woniu.yoga.commom.controller;

import com.woniu.yoga.commom.vo.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {

    @PostMapping("newFile")
    public Object fileUpload(HttpServletRequest request,MultipartFile file){
//        String rootPath = request.getServletContext().getRealPath("/resource/img/");
        String rootPath = "E:/img/";
        try {
            //得到文件的文件名
            String originalFilename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
            String res = sdf.format(new Date());
            String newFileName = res + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(rootPath + newFileName);
            if(!newFile.getParentFile().exists()) {
                //判断，如果目录不存在就创建目录
                newFile.getParentFile().mkdirs();
            }
            if(!newFile.exists()){
                //储存文件到该目录
                file.transferTo(newFile);
                String fileLocation="/img/"+newFileName;
                //返回消息
                return Result.success("上传成功", fileLocation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("上传失败");
    }
}
