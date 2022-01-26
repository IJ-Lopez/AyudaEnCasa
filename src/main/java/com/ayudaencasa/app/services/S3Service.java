package com.ayudaencasa.app.services;


import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
public interface S3Service {

public File convertMultiPartFileToFile(final MultipartFile multipartFile);
public S3ObjectInputStream findByName(String fileName);
public String save(final MultipartFile multipartFile);

}
