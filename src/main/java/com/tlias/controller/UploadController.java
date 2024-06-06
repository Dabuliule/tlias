package com.tlias.controller;

import com.tlias.pojo.Result;
import com.tlias.utils.MinIOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    private final MinIOUtil minIOUtil;

    @Autowired
    public UploadController(MinIOUtil minIOUtil) {
        this.minIOUtil = minIOUtil;
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("文件上传, 文件名: {}", image.getOriginalFilename());

        //调用阿里云OSS工具类进行文件上传
        String url = minIOUtil.upload(image);
        log.info("文件上传完成,文件访问的url: {}", url);

        return Result.success(url);
    }
}
