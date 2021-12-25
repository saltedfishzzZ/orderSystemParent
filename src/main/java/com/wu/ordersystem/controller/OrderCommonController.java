package com.wu.ordersystem.controller;

import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.utils.UploadFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping
public class OrderCommonController {

    private static final Logger logger = LoggerFactory.getLogger(OrderCommonController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult uploadFile(@RequestPart MultipartFile file) {
        if (null == file) return CommonResult.failed().message("文件为空");
        String filename = file.getOriginalFilename();
        try {
            if (!"".equals(filename.trim())) {
                File newFile = new File(filename);
                FileOutputStream outputStream = new FileOutputStream(newFile);
                outputStream.write(file.getBytes());
                outputStream.close();
                file.transferTo(newFile);
                String fileName = UploadFileUtil.uploadFile(newFile);
                return CommonResult.success().data("fileName", fileName);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return CommonResult.failed();
    }
}
