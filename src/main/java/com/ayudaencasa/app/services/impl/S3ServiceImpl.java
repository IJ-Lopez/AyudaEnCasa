package com.ayudaencasa.app.services.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.ayudaencasa.app.services.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
 
 
/** Amazon Web Services S3 Bucket integration for uploading and downloading images.*/
@Service
public class S3ServiceImpl implements S3Service{
 
    private static final Logger LOG = LoggerFactory.getLogger(S3ServiceImpl.class);
 
    @Autowired
    private AmazonS3 amazonS3;
 
    @Value("${AWS_BUCKET}")
    private String s3BucketName;
 
    public File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            LOG.error("Error {} occurred while converting the multipart file", e.getLocalizedMessage());
        }
        return file;
    }
 
    // @Async annotation ensures that the method is executed in a different thread
 
    /**Retrieves the an imput stream containing the objects's contents.
     * @param fileName Key for the file's name in the server.
     * @return An input stream containing the contents of the object.
     */
    @Async
    @Override
    public S3ObjectInputStream findByName(String fileName) {
        LOG.info("Downloading file with name {}", fileName);
        return amazonS3.getObject(s3BucketName, fileName).getObjectContent();
    }
 
    /**Uploads the image to the S3 Bucket from Amazon Web Services.
     * @param multipartFile Image file of extension .jpg .jpeg .png .gif ONLY
     * @return The file's absolute path in the S3 Bucket server.
     */
    @Async
    @Override
    public String save(final MultipartFile multipartFile) {
        String filePath = null;
        try {
            final File file = convertMultiPartFileToFile(multipartFile);
            final String fileName = file.getName();
            LOG.info("Uploading file with name {}", fileName);
            final PutObjectRequest putObjectRequest = new PutObjectRequest(s3BucketName, fileName, file);
            amazonS3.putObject(putObjectRequest);
            Files.delete(file.toPath()); // Remove the file locally created in the project folder
            filePath = amazonS3.getBucketLocation(fileName);
        } catch (AmazonServiceException e) {
            LOG.error("Error {} occurred while uploading file", e.getLocalizedMessage());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(S3ServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filePath;
 
    }
}
