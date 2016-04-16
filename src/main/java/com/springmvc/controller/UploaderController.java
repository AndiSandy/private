package com.springmvc.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by ajshu on 2016/4/16.
 */
@Controller
public class UploaderController {

    @RequestMapping(value = "/uploader",method = RequestMethod.POST)
    public JSONObject uploaderFiles(MultipartFile file){
        JSONObject jsonObject = new JSONObject();

       try {
           FileUtils.writeByteArrayToFile(new File("F:/JavaUploader/" + file.getOriginalFilename()), file.getBytes());
           jsonObject.put("url","11111111");
       }catch (IOException io){
           io.printStackTrace();
           jsonObject.put("status","0");
       }
        return jsonObject;
    }

}
