package org.learn.aws.s3.credentials;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import software.amazon.awssdk.core.exception.SdkException;

public class S3SupplyCredentialExamplesTest {

	@Test
	void clientWithJavaSystemPropertyCredentialProviderTest() {
		assertThrows(SdkException.class,
				() -> S3SupplyCredentialExamples.clientWithJavaSystemPropertyCredentialProvider());
		// If AWS access key and secret passed as an JVM system properties pass -Daws.accessKeyId=<KEY> -Daws.secretAccessKey=<SECRET>
		// assertDoesNotThrow(() -> S3SupplyCredentialExamples.clientWithJavaSystemPropertyCredentialProvider());
	}

	@Test
	void clientWithEnvironmentVariableCredentialsProviderTest() {
		// Would pass this test if environment variable are not set
		assertThrows(SdkException.class,
				() -> S3SupplyCredentialExamples.clientWithEnvironmentVariableCredentialsProvider());
		// If AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY are set this will create the client
		// assertDoesNotThrow(() -> S3SupplyCredentialExamples.clientWithEnvironmentVariableCredentialsProvider());
	}

	@Test
	void clientWithProfileCredentialsProviderTest() {
		assertThrows(SdkException.class, () -> S3SupplyCredentialExamples.clientWithProfileCredentialsProvider());
		// If profile exists, then it will not throw an error 
		// assertDoesNotThrow(() -> S3SupplyCredentialExamples.clientWithProfileCredentialsProvider());
	}
	
	@Test
	void clientWithDefaultCredentialProvider() {
		 assertDoesNotThrow(() -> S3SupplyCredentialExamples.clientWithDefaultCredentialProvider());
	}

}