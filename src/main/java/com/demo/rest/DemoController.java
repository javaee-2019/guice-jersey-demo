package com.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Niu Li
 * @date 2016/7/24
 */
@Path("")
public class DemoController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)//指定返回类型为text_plain
    public String helloWorld(){

        return "hello world!";
    }
}