package com.venosyd.open.aws.lib;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
public abstract class AWSUtil {

    /**
     * retorna o valor de uma chave de configuracao do aws no config.yaml
     */
    public static String getValue(String key) {
        return (String) AWSConfig.INSTANCE.get(key);
    }
}