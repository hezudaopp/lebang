package com.youmayon.lebang.web;

import main.java.com.UpYun;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by 向麒 on 2017/1/12.
 */
@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody(required = false) MultipartFile file){
        //获取文件扩展名
        String ext_Name = file.getOriginalFilename().split("\\.")[1];
        //获取文件名
        String file_Name=file.getOriginalFilename().split("\\.")[0];

        byte[] bytes = null;
        try {
            bytes = file.getBytes(); //将文件转换成字节流形式
        } catch (IOException e) {
            e.printStackTrace();
        }
        UpYun upyun = new UpYun("xiangqitestservice","xiangqi","xiangqi123");
        upyun.setTimeout(60);
        upyun.setApiDomain(UpYun.ED_AUTO);
        String filePath = "/xiangqiTest/"+file_Name+"."+ext_Name;
        boolean result = upyun.writeFile(filePath, bytes, true);
        if (result){
            return new ResponseEntity<>(filePath,HttpStatus.MULTI_STATUS.OK);
        }

        return new ResponseEntity<>("上传失败",HttpStatus.MULTI_STATUS.OK);
    }
}
