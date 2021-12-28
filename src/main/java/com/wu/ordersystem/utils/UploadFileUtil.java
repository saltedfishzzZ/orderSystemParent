package com.wu.ordersystem.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.wu.ordersystem.config.AliyunOssProps;
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

    private final AliyunOssProps ossProps;

    private static UploadFileUtil instance;

    private UploadFileUtil(AliyunOssProps ossProps) {
        this.ossProps = ossProps;
    }

    public static UploadFileUtil getInstance(AliyunOssProps ossProps) {
        if (null == instance) {
            synchronized (UploadFileUtil.class) {
                if (null == instance) {
                    instance = new UploadFileUtil(ossProps);
                }
            }
        }
        return instance;
    }

    public String uploadFile(File file) throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = LocalDate.now().format(dateTimeFormatter);
        // 设置文件名
        String fileName =
                UUID.randomUUID() + "-" + now + "-" + file.getName();
        OSS client = new OSSClientBuilder()
                .build(instance.ossProps.getEndpoint(), instance.ossProps.getAccessKeyId(),
                        instance.ossProps.getAccessKeySecret());
        // 判断容器是否存在
        try {
            if (!client.doesBucketExist(instance.ossProps.getBucketName())) {
                client.createBucket(instance.ossProps.getBucketName());
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(instance.ossProps.getBucketName());
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            // 上传文件
            PutObjectResult result = client.putObject(new PutObjectRequest(instance.ossProps.getBucketName(), fileName, file));
            if (null != result) {
                logger.info(LocalDateTime.now() + "---上传文件成功, 文件地址链接为：" +
                       instance.ossProps.getBucketName() + instance.ossProps.getFileUrlSuffix() + fileName);
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
        return instance.ossProps.getBucketName() + instance.ossProps.getFileUrlSuffix() + fileName;
    }
}
