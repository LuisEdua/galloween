package com.example.galloween2.services;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.galloween2.services.interfaces.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements IFileService {

    private AmazonS3 s3client;

    private String ENDPOINT_URL = "s3.us-west-1.amazonaws.com";

    private String BUCKET_NAME = "ttg-upch";

    private String ACCESS_KEY = "AKIAXVHVE2SGVCKCZFZK";

    private String SECRET_KEY = "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";

    @Override
    public String upload(MultipartFile multipartFile){
        String fileUrl = "";

        try {
            File file = convertMultiPartToFile(multipartFile);

            String fileName = generateFileName(multipartFile);

            fileUrl = "https://" + BUCKET_NAME + "." + ENDPOINT_URL + "/" + fileName;

            uploadFileToS3Bucket(fileName, file);

            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileToS3Bucket(String fileName, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        s3client.putObject(putObjectRequest);
    }

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_WEST_1)
                .build();
    }
}
