package com.order.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.order.constant.MessageConstant;
import com.order.result.Result;
import com.order.utils.LocalStorageUtil;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用控制器
 */
@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    @Autowired
    private LocalStorageUtil localStorageUtil;
    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ApiOperation(value = "文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传: {}", file);

        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            byte[] bytes = file.getBytes();
            String objectName = System.currentTimeMillis() + extension;
            String url = localStorageUtil.upload(bytes, objectName);
            log.info("文件上传成功: {}", url);
            return Result.success(url);
        } catch (Exception e) {
            log.error("文件上传失败: {}", e);
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }
    }
}
