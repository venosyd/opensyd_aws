package com.venosyd.open.aws.logic;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
public interface S3BS {

        /**  */
        S3BS INSTANCE = new S3BSImpl();

        /**
         * faz upload de uma imagem para o S3
         */
        String upload(String bucket, String key, String file);

}