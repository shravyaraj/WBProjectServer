package myapp.services;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import myapp.models.User;
import myapp.repositories.UserRepository;

@RestController
@CrossOrigin("*")
public class AWService {
	
	@Autowired
	UserRepository userRepository;
	
	private static final String SUFFIX = "/";
	
	static AWSCredentials credentials = new BasicAWSCredentials( "AKIAJYWJ7YMIGJ3WZRCA", "tK+sSl0DXi8yXgmX1uuJxWad8OLRKd0uwgvmB6Bc");
	
	//AmazonS3 s3client = new AmazonS3Client(credentials);

			  static AmazonS3 client = AmazonS3ClientBuilder
			  .standard()
			  .withCredentials(new AWSStaticCredentialsProvider(credentials))
			  .withRegion(Regions.US_EAST_1)
			  .build();
	
			   public String createFile(String folderName, File imgFile, int userId) {
				   System.out.println("inside AWS file upload"+ folderName);
				   String fileName = imgFile.getName();
					client.putObject(new PutObjectRequest("bookwormstest", fileName, imgFile)
						.withCannedAcl(CannedAccessControlList.PublicRead));
				return fileName;
				}
			   
			
				/*
				public static void deleteFile(AmazonS3 client, String filename){
					client.deleteObject("book-worms", filename);
				}
				*/
}


