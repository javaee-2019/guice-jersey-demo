package com.demo.rest;

import com.demo.model.SingleModel;
import com.demo.service.GuiceDemo;
import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author hewen
 * @date 2019/11/19 15:23
 */
@Path("/hello")
@Produces(value = MediaType.APPLICATION_JSON)
public class HelloWorldController {

    @GET
    @Path("/get")
    public String helloWorld(@QueryParam("msg") String msg) {
        return "hello world!" + msg;
    }

    @POST
    @Path("/post")
    public Object helloWorldPost(@QueryParam("msg") String msg) {
        SingleModel build = SingleModel.builder().v(msg).build();
        return build;
    }


    public void testHelloGuice() {
        Injector injector = Guice.createInjector();
        GuiceDemo helloGuice = injector.getInstance(GuiceDemo.class);
        helloGuice.say();
    }
}
