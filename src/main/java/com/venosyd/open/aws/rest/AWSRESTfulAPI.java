package com.venosyd.open.aws.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
@ApplicationPath("/")
public class AWSRESTfulAPI extends Application {

    public Set<Class<?>> getClasses() {
        var classes = new HashSet<Class<?>>();

        classes.add(AWSRSImpl.class);

        return classes;
    }
}
