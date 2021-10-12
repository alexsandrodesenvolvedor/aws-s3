package com.amazonaws.samples;

import java.io.File;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Exemplo {

	public static void main(String[] args) {
		String accessKey = "AKIAXKTTX26PSUEGCBFD";
		String secretKey = "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";

		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(Regions.SA_EAST_1).build();
		
		String bucketName = "alura-bkt-alexsandro";
		
		//Criar bucket
		//s3.createBucket(bucketName);
		
		//Listar Buckets
		List<Bucket> buckets = s3.listBuckets();
		buckets.forEach(b -> {
			System.out.println(b.getName());
		});
		
		//Upload de arquivo
		//Se existir sobrescreve
		s3.putObject(bucketName, "s3.txt", new File("s3.txt"));
		
		//Listar objetos de um bucket
		ListObjectsRequest listObjectsBucket = new ListObjectsRequest().withBucketName(bucketName);
		ObjectListing objetos = s3.listObjects(listObjectsBucket);
		for(S3ObjectSummary objeto : objetos.getObjectSummaries()) {
			System.out.println(objeto.getSize());//Tamanho arquivo
			System.out.println(objeto.getKey());//Nome do arquivo
		}
		
		s3.deleteObject(bucketName, "s3.txt");;

	}

}
