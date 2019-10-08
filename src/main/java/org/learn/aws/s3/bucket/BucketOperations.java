package org.learn.aws.s3.bucket;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteBucketResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

public class BucketOperations {
	
	private static final Logger logger = LoggerFactory.getLogger(BucketOperations.class);
	
	/**
	 * Create a bucket if name is unique
	 * @param bucketName
	 * 
	 * @return true if operation is successful
	 */
	public static boolean createBucket(String bucketName) {
		boolean isSuccessful = false;
		
		try (final S3Client s3Client = S3Client.create()) {

			CreateBucketRequest request = CreateBucketRequest.builder().bucket(bucketName).build();

			CreateBucketResponse response = s3Client.createBucket(request);
			
			if (response != null) {
				isSuccessful = response.sdkHttpResponse().isSuccessful();
				logger.warn("Response : {}", response);
			}
			
		} catch (SdkException e) {
			logger.error("Error while trying to get buckets {}", e.getLocalizedMessage());
		}
		return isSuccessful;
	}

	/**
	 * Get the list of buckets for user
	 * 
	 * @return list of buckets
	 */
	public static List<Bucket> getBuckets() {
		List<Bucket> buckets = null;
		
		try(final S3Client s3Client = S3Client.create()) {
			
			ListBucketsResponse response = s3Client.listBuckets();
			
			if(response != null) {
				logger.warn("Response : {}", response);
				buckets = response.buckets();
			}			
		}
		catch(SdkException e) {
			logger.error("Error while trying to get buckets {}", e.getLocalizedMessage());
		}
		
		return buckets;
	}
	
	/**
	 * 
	 * @param bucketName
	 * 
	 * @return true if operation is successful
	 */
	public static boolean deleteBucket(String bucketName) {
		boolean isSuccessful = false;
		
		try (final S3Client s3Client = S3Client.create()) {

			DeleteBucketRequest request = DeleteBucketRequest.builder().bucket(bucketName).build();

			DeleteBucketResponse response = s3Client.deleteBucket(request);
			isSuccessful = response.sdkHttpResponse().isSuccessful();						
			logger.warn("Response : {}", response);

		} catch (SdkException e) {
			logger.error("Error while trying to delete bucket {}", e.getLocalizedMessage());
		}
		return isSuccessful;
	}

}
