package com.nocountry.wallet.utils;


import com.nocountry.wallet.exception.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AwsUtils {

    public File convertMultiPartToFile(MultipartFile file) {

        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            throw new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "failed to try save image", e.getMessage());
        }
        return convFile;
    }
}
