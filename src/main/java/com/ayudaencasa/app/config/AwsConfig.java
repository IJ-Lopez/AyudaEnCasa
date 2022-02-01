package com.ayudaencasa.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfig {

	@Value("${AWS.ACCESS_KEY_ID}")
	private String accessKeyId;

	@Value("${AWS.SECTRET_ACCESS_KEY}")
	private String accessSecretKey;

	@Value("${AWS.S3.REGION}")
	private String region;

	@Bean
	public AmazonS3 getS3Client() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessSecretKey);
		return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	}

}

