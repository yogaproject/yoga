package com.woniu.yoga.user.controller;

import com.woniu.yoga.pay.util.Result;
import com.woniu.yoga.user.constant.SysConstant;
import com.woniu.yoga.user.pojo.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/userApp")
@CrossOrigin
public class UploadController {

    @PostMapping("/uploadHead")
    public Object fileUpload(HttpServletRequest request, MultipartFile file, User user, HttpSession session){
//        String rootPath = request.getServletContext().getRealPath("/resource/img/");
        user= (User) session.getAttribute(SysConstant.CURRENT_USER);
        String rootPath = "C:/images/";
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
