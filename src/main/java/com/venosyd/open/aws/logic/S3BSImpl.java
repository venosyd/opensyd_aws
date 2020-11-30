package com.venosyd.open.aws.logic;

import java.util.Base64;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.venosyd.open.aws.lib.AWSUtil;
import com.venosyd.open.commons.log.Debuggable;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
public class S3BSImpl implements S3BS, Debuggable {

    @Override
    public String upload(String bucket, String key, String file) {
        byte[] data = (file.contains(",")) ? Base64.getDecoder().decode(file.substring(file.indexOf(",") + 1))
                : Base64.getDecoder().decode(file);

        try {
            Regions clientRegion = Regions.fromName(AWSUtil.getValue("region"));

            AWSCredentials credentials = new BasicAWSCredentials(AWSUtil.getValue("accesskey"),
                    AWSUtil.getValue("secretkey"));

            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(clientRegion).build();

            s3Client.putObject(bucket, key, new String(data));
            return "SUCCESS";
        }
        //
        catch (AmazonServiceException e) {
            err.exception("AmazonServiceException", e);
        }
        //
        catch (SdkClientException e) {
            err.exception("SdkClientException", e);
        }

        return "FAILURE";
    }

}