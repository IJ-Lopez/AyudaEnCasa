package com.ayudaencasa.app.services.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import static org.hibernate.bytecode.BytecodeLogging.LOGGER;
 
 
/** Amazon Web Services S3 Bucket integration for uploading and downloading images.*/
@Service
public class S3ServiceImpl implements S3Service{
 
    private static final Logger LOG = LoggerFactory.getLogger(S3ServiceImpl.class);
 
    @Autowired
    private AmazonS3 amazonS3;
 
    @Value("${AWS.S3.BUCKET}")
    private String ayudaencasa;
 
    //@Override
    public File UploadFile (final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
            String newFileName = System.currentTimeMillis()+ "-" + file.getName();
           LOGGER.info("Subiendo archivo con el nombre... " + newFileName);
			PutObjectRequest request = new PutObjectRequest(ayudaencasa, newFileName, file);
			amazonS3.putObject(request);
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
        LOG.info("Downloading file with name {}" + fileName);
        return amazonS3.getObject(ayudaencasa, fileName).getObjectContent();
    }
 
    
    @Override
	public List<String> getObjectsFromS3() {
		ListObjectsV2Result result = amazonS3.listObjectsV2(ayudaencasa);
		List<S3ObjectSummary> objects = result.getObjectSummaries();
		List<String> list = objects.stream().map(item -> {
			return item.getKey();
		}).collect(Collectors.toList());
		return list;
	}

	@Override
	public InputStream downloadFile(String key) {
		S3Object object = amazonS3.getObject(ayudaencasa, key);
		return object.getObjectContent();
	}


    @Async
    @Override
    public String save(final MultipartFile multipartFile) {
        String filePath = null;
        try {
            final File file = UploadFile(multipartFile);
            final String fileName = file.getName();
            LOG.info("Uploading file with name {}", fileName);
            final PutObjectRequest putObjectRequest = new PutObjectRequest(ayudaencasa, fileName, file);
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
