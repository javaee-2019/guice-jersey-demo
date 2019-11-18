package com.demo.config;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Niu Li
 * @date 2016/7/24
 */
public class RESTApplication extends ResourceConfig {
    public RESTApplication() {
        //给出要扫描的包,也就是上面heloword所在的包,扫描多个包使用分号隔开
        packages("com.demo");
        //打开日志,便于调试
        register(LoggingFilter.class);
    }
}
