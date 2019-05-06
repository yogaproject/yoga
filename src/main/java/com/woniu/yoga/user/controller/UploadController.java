package com.woniu.yoga.user.controller;

import com.woniu.yoga.commom.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {

    @Value("${fileUpload.rootSavePath}")
    private String rootPath;

    @Value("${fileUpload.virtualPath}")
    private String virtualPath;

    /**
     * 单个文件上传
     * @param request
     * @param file
     * @return
     */
    @PostMapping("newFile")
    public Result fileUpload(HttpServletRequest request,MultipartFile file){
//        String rootPath = request.getServletContext().getRealPath("/resource/img/");
        String fileLocation = executeUpload(rootPath, file);
        if(fileLocation != null){
            return Result.success("上传成功", fileLocation);
        }else {
            return Result.error("上传失败");
        }
    }

    /**
     * 多文件上传
     * @param request
     * @param file
     * @return
     */
    @PostMapping("newFiles")
    public Result filesUpload(HttpServletRequest request,MultipartFile[] file){
//        String rootPath = request.getServletContext().getRealPath("/resource/img/");
        List<String> result = new ArrayList<>();
        for(int i = 0;i<file.length;i++){
            String fileLocation = executeUpload(rootPath, file[i]);
            if(fileLocation != null){
                result.add(fileLocation);
            }
        }
        if(result.size() == file.length){
            return Result.success("上传成功",result);
        }else {
            return Result.error("上传失败，成功数量为："+result.size());
        }
    }

    /**
     * 执行上传
     * @param rootPath
     * @param file
     * @return
     */
    public String executeUpload(String rootPath, MultipartFile file){
        try {
            //得到文件的文件名
            String originalFilename = file.getOriginalFilename();
            String res = UUID.randomUUID().toString();
            String newFileName = res + originalFilename.substring(originalFilename.lastIndexOf("."));
            System.out.println(rootPath + newFileName);
            File newFile = new File(rootPath + newFileName);
            if(!newFile.getParentFile().exists()) {
                //判断，如果目录不存在就创建目录
                newFile.getParentFile().mkdirs();
            }
            if(!newFile.exists()){
                //储存文件到该目录
                file.transferTo(newFile);
                String fileLocation=virtualPath+newFileName;
                //返回图片虚拟路径
                return fileLocation;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
