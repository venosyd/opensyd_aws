package com.venosyd.open.aws.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.venosyd.open.commons.util.JSONUtil;
import com.venosyd.open.commons.util.RESTService;
import com.venosyd.open.aws.lib.AWSConfig;
import com.venosyd.open.aws.logic.S3BS;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
@Path(AWSRS.AWS_BASE_URL)
public class AWSRSImpl implements AWSRS, RESTService {

    @Context
    private HttpHeaders headers;

    public AWSRSImpl() {

    }

    @Override
    public Response s3Upload(String body) {
        List<String> arguments = Arrays.asList("bucket", "key", "file");

        return process(_unwrap(body), getauthcode(headers), arguments, (request) -> {
            var response = new HashMap<String, String>();

            var bucket = request.get("bucket");
            var key = request.get("key");

            // heavy workload
            var file = request.get("file");

            var result = S3BS.INSTANCE.upload(bucket, key, file);

            if (!result.equals("FAILURE")) {
                response.put("status", "ok");
                response.put("payload", JSONUtil.toJSON(result));
            } else {
                response.put("status", "error");
                response.put("message", JSONUtil.toJSON(result));
            }

            return makeResponse(response);
        });
    }

    /**
     * traduz o JSON pra mapa e insere um dado importante que eh a base de dados
     * para o servidor saber direcionar o fluxo de persistencia e consulta
     * corretamente
     */
    private Map<String, String> _unwrap(String body) {
        body = unzip(body);
        var request = JSONUtil.<String, String>fromJSONToMap(body);

        String database = (String) AWSConfig.INSTANCE.get("bancodedados");
        request.put("database", database);

        return request;
    }

}
