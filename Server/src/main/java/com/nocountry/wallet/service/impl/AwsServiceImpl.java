package com.nocountry.wallet.service.impl;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nocountry.wallet.service.AwsService;
import com.nocountry.wallet.utils.AwsUtils;
import com.nocountry.wallet.utils.MultiPartFileClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class AwsServiceImpl implements AwsService {

    @Value("${spring.aws.s3.bucket}")
    private String bucket;

    @Value("${spring.aws.s3.endpoint}")
    private String endPoint;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private AwsUtils awsUtils;

    private void uploadFile2Asw3(String fileName, File file) {

        this.amazonS3.putObject(new PutObjectRequest(bucket, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

    }

    private String uploadFile(MultipartFile multipartFile) {

        File fileCreated = awsUtils.convertMultiPartToFile(multipartFile);
        String fileName = multipartFile.getOriginalFilename();
        uploadFile2Asw3(fileName, fileCreated);
        fileCreated.delete();
        String fileURL = endPoint + "/" + bucket + "/" + fileName;
        return fileURL;
    }

    @Override
    public String uploadFileFromBase64(String base64) {
        if (base64 == null)
            return null;
        if (base64.contains("data:image/")) {
            String[] parts = base64.split(",");
            String header = parts[0];
            String contents = parts[1];
            MultipartFile multipartFile = new MultiPartFileClass(header, contents);
            return uploadFile(multipartFile);
        } else
            return endPoint + "/" + bucket + "/" + base64;
    }
}
