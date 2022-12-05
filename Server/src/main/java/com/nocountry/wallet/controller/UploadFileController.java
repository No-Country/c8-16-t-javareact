package com.nocountry.wallet.controller;

import com.nocountry.wallet.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
public class UploadFileController {
    @Autowired
    private AwsS3Service awsS3Service;

    @PostMapping(value = "/uploadS3File")
    public ResponseEntity<String> uploadFile(@RequestPart(value="file")MultipartFile file)  {
        awsS3Service.uploadFile(file);
        String response = "El archivo "+file.getOriginalFilename()+" fue cargado correctamente";
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}
