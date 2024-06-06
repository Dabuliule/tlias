package com.tlias.utils;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Component
public class MinIOUtil {

    private final MinIOProperties minIOProperties;

    @Autowired
    public MinIOUtil(MinIOProperties minIOProperties) {
        this.minIOProperties = minIOProperties;
    }

    public String upload(MultipartFile file) throws Exception {
        String endpoint = minIOProperties.getEndpoint();
        String accessKey = minIOProperties.getAccessKey();
        String secretKey = minIOProperties.getSecretKey();
        String bucketName = minIOProperties.getBucketName();

        InputStream inputStream = file.getInputStream();

        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        MinioClient minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(inputStream, file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        return endpoint + "/" + bucketName + "/" + fileName;
    }
}
