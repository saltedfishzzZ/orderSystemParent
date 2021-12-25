package com.wu.ordersystem.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UploadFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileUtil.class);

    private static final String endpoint = "https://oss-cn-beijing.aliyuncs.com";

    private static final String bucketName = "order-system-saltedfish";
    private static final String fileUrl = bucketName + ".oss-cn-beijing.aliyuncs.com/";

    public static String uploadFile(File file) throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = LocalDate.now().format(dateTimeFormatter);
        // 设置文件名
        String fileName =
                UUID.randomUUID() + "-" + now + "-" + file.getName();
        OSS client = new OSSClientBuilder()
                .build(endpoint, accessKeyId, accessKeySecret);
        // 判断容器是否存在
        try {
            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            // 上传文件
            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, fileName, file));
            if (null != result) {
                logger.info(LocalDateTime.now() + "---上传文件成功, 文件地址链接为：" +
                        fileUrl + fileName);
            }
        } catch (OSSException ossException) {
            logger.error(ossException.getMessage(), ossException);
        } catch (ClientException clientException) {
            logger.error(clientException.getMessage(), clientException);
        } finally {
            if (null != client) {
                client.shutdown();
            }
        }
        return fileUrl + fileName;
    }
}
