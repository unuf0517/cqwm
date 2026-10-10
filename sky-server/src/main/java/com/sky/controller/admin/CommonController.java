package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/admin/common")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传：{}", file);
        String url = null;
        try {
            url = aliOssUtil.upload(file.getBytes(), file.getOriginalFilename());
            return Result.success(url);
        } catch (Exception e) {
            log.info("文件上传失败：{}", e.getMessage());
        }
        log.info("文件上传OSS, url: {}", url);
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
