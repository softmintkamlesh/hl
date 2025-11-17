package com.hypershop.service.impl;

import com.hypershop.utils.GeneratorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3StorageService {

    private static final Logger LOGGER= LogManager.getLogger(S3StorageService.class);
    private final S3Client s3Client;
    private final String bucket;

    public S3StorageService(S3Client s3Client,
                            @Value("${aws.s3.bucket}") String bucket) {
        this.s3Client = s3Client;
        this.bucket = bucket;
    }

    public String uploadBrandLogo(MultipartFile file) {
        try {
            String ext = getExtension(file.getOriginalFilename());
            String key = "brands/" + GeneratorUtil.ulid() + (ext != null ? "." + ext : "");

            PutObjectRequest put = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(put, RequestBody.fromBytes(file.getBytes()));

            // public URL pattern – depends on bucket config (public / CloudFront etc.)
            return "https://" + bucket + ".s3.amazonaws.com/" + key;
        } catch (Exception e) {
            LOGGER.error("error in uploadBrandLogo :{}",e.getLocalizedMessage(),e);
            throw new RuntimeException("Failed to upload brand logo", e);
        }
    }

    private String getExtension(String name) {
        if (name == null) return null;
        int idx = name.lastIndexOf('.');
        return idx >= 0 ? name.substring(idx + 1) : null;
    }
}
