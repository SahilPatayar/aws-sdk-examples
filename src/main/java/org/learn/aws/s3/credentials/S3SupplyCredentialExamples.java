package org.learn.aws.s3.credentials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

public class S3SupplyCredentialExamples {

    private static final Logger logger = LoggerFactory.getLogger(S3SupplyCredentialExamples.class);

    /**
     * Default credential look up chain:
     * 1. Java System property
     * 2. Environment Variable
     * 3. default credential profile from credentials file
     * 4. Amazon ECS container credentials
     * 5. Instance profile credentials
     */
    public static void clientWithDefaultCredentialProvider() {
    	try (final S3Client s3Client = S3Client.builder().build()) {
            // Operations can be performed on client here
            ListBucketsRequest request = ListBucketsRequest.builder().build();
            ListBucketsResponse response = s3Client.listBuckets(request);
            logger.warn("Bucket Size " + response.buckets().size());
        } catch (SdkException e) {
            logger.error("Error creating client with Default Credential Provider Method", e.getLocalizedMessage());
            throw e;
        }
    }

    /**
     * Credentials are being provided by System's environment variable
     * 
     * throws an exception, if not found
     */
    public static void clientWithEnvironmentVariableCredentialsProvider() {
        try (final S3Client s3Client = S3Client.builder().credentialsProvider(EnvironmentVariableCredentialsProvider.create()).build()) {
            // Operations can be performed on client here
            ListBucketsRequest request = ListBucketsRequest.builder().build();
            ListBucketsResponse response = s3Client.listBuckets(request);
            logger.warn("Bucket Size " + response.buckets().size());
        } catch (SdkException e) {
            logger.error("Error creating client with Environment Variable Credential Provider Method", e.getLocalizedMessage());
            throw e;
        }
    }

    /**
     * Credentials are being provided by jvm property aws.accessKeyId and aws.secretAccessKey
     *
     * if not found, will throw an exception
     */
    public static void clientWithJavaSystemPropertyCredentialProvider() {
        try (final S3Client s3Client = S3Client.builder().credentialsProvider(SystemPropertyCredentialsProvider.create()).build()) {
            // Operations can be performed on client here
            ListBucketsRequest request = ListBucketsRequest.builder().build();
            ListBucketsResponse response = s3Client.listBuckets(request);
            
            logger.warn("Bucket Size " + response.buckets().size());

        } catch (SdkException e) {
            logger.error("Error creating client with System Property Credential Provider Method", e.getLocalizedMessage());
            throw e;
        }
    }

    public static void clientWithProfileCredentialsProvider() {
        try (final S3Client s3Client = S3Client.builder().credentialsProvider(ProfileCredentialsProvider.create("dev")).build()) {
            // Operations can be performed on client here
            ListBucketsRequest request = ListBucketsRequest.builder().build();
            ListBucketsResponse response = s3Client.listBuckets(request);

            logger.warn("Bucket Size " + response.buckets().size());
            
        } catch (SdkException e) {
            logger.error("Error creating client with Profile Credential Provider Method", e.getLocalizedMessage());
            throw e;
        }
    }

}
