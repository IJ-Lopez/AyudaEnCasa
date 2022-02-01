package com.ayudaencasa.app.services;


import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.InputStream;
import java.util.List;
public interface S3Service {

public File UploadFile(final MultipartFile file);
public S3ObjectInputStream findByName(String fileName);
public String save(final MultipartFile multipartFile);
List<String> getObjectsFromS3();
InputStream downloadFile(String key);
}
