package com.venosyd.open.aws.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
public interface AWSRS {

    String AWS_BASE_URL = "/aws";

    String AWS_S3_UPLOAD = "/s3/upload";

    @POST
    @Path(AWS_S3_UPLOAD)
    @Produces({ MediaType.APPLICATION_JSON })
    Response s3Upload(String body);
}
