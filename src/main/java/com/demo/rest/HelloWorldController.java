package com.demo.rest;

import com.demo.service.GuiceDemo;
import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Niu Li
 * @date 2016/7/24
 */
@Path("/hello")
public class HelloWorldController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)//指定返回类型为text_plain
    public String helloWorld() {
        return "hello world!";
    }


    public void testHelloGuice() {
        Injector injector = Guice.createInjector();
        GuiceDemo helloGuice = injector.getInstance(GuiceDemo.class);
        helloGuice.say();
    }
}
