package org.learn.aws.s3.bucket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import software.amazon.awssdk.services.s3.model.Bucket;

@TestMethodOrder(OrderAnnotation.class)
public class BucketOperationsTest {
	
	private static String bucketName;
	
	@BeforeAll
	public static void init() {
		bucketName = "temp-" + System.currentTimeMillis();
	}
	
	@Test
	@Order(1)
	public void createBucketTest() {		
		assertTrue(BucketOperations.createBucket(bucketName));
	}
	
	
	@Test
	@Order(2)
	public void getBucketsTest() {
		List<Bucket> buckets = BucketOperations.getBuckets();
		assertNotNull(buckets);
		assertEquals(1, buckets.size(), "As of now, only one bucket exist");
	}
	
	@Test
	@Order(3)
	public void deleteBucketTest() {
		assertTrue(BucketOperations.deleteBucket(bucketName));	
		// will fail as bucket does not exist to delete
		assertFalse(BucketOperations.deleteBucket(bucketName));
	}	

}
