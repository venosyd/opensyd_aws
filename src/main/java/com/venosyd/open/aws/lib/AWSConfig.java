package com.venosyd.open.aws.lib;

import com.venosyd.open.commons.util.ConfigReader;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
public class AWSConfig extends ConfigReader {

    public static final AWSConfig INSTANCE = new AWSConfig();

    private AWSConfig() {
        super("aws");
    }
}