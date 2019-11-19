package com.demo.interceptor;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.*;
import java.io.IOException;

@Provider
@Slf4j
public class myInterceptor implements WriterInterceptor, ReaderInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext context)
            throws IOException, WebApplicationException {
        log.info("进入WriterInterceptor");
        Object entity = context.getEntity();
        System.out.println("entity = " + entity);
        context.getHeaders().forEach((k, v) -> {
            System.out.println("v = " + v);
        });
        context.proceed();
    }

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        log.info("进入ReaderInterceptor");
        context.getHeaders().forEach((k, v) -> {
            System.out.println("v = " + v);
        });
        Object proceed = context.proceed();
        System.out.println("proceed = " + proceed);
        return proceed;
    }
}

