package com.wwx.teamall.controller;

import com.wwx.teamall.model.Result;
import com.wwx.teamall.utils.FileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileUploadController {

    private final String BASE_IMG_URL = "D:\\Programming\\IdeaProject\\tea-mall\\image\\";

    @PostMapping("/upload")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public Result fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        String fileName = System.currentTimeMillis()+multipartFile
                .getOriginalFilename().substring(multipartFile.getOriginalFilename().length()-4);
        log.info("文件路径为为==={}", BASE_IMG_URL);
        log.info("文件名为==={}", fileName);
        try {
            FileUpload.fileUpload(multipartFile, BASE_IMG_URL, fileName);
            return Result.success(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failed("文件上传失败");
        }
    }
}
