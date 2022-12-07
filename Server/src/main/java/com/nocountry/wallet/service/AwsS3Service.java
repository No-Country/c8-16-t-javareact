package com.nocountry.wallet.service;

import org.springframework.web.multipart.MultipartFile;

public interface AwsS3Service {

    Void uploadFile (MultipartFile file);
}
