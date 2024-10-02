package com.order.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class LocalStorageUtil {

    private String location;
    private String baseUrl;

    /**
     * 文件上传
     *
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) {
        // 生成目标文件的完整路径
        String fullPath = Paths.get(location, objectName).toString();
        File targetFile = new File(fullPath);

        // 如果目标文件所在的目录不存在，则创建该目录
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(targetFile)) {
            // 将字节数组写入目标文件
            fos.write(bytes);
            fos.flush();
            log.info("File uploaded successfully to {}", fullPath);
        } catch (IOException e) {
            log.error("Failed to upload file to {}", fullPath, e);
            throw new RuntimeException("Failed to upload file", e);
        }

        // 返回文件的 HTTP URL
        return baseUrl + "/" + objectName;
    }
}
