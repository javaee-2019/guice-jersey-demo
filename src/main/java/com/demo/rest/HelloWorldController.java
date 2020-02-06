package com.demo.rest;

import com.demo.model.HelloPostCommonCommand;
import com.demo.model.SingleModel;
import com.demo.service.GuiceDemo;
import com.google.common.net.HttpHeaders;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author hewen
 * @date 2019/11/19 15:23
 */
@Path("/hello")
public class HelloWorldController {

    @GET
    @Path("/get")
    @Produces(value = MediaType.APPLICATION_JSON)
    public String helloWorld(@QueryParam("msg") String msg) {
        return "hello world!" + msg;
    }

    @POST
    @Path("/post")
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public SingleModel helloWorldPost(HelloPostCommonCommand command) {
        SingleModel<String> model = new SingleModel("jack---" + command.getMsg());
        return model;
    }

    @GET
    @Path("/file")
    public void exportFile(@Context HttpServletResponse response) throws Exception {
        //得到输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //设置下载文件头
        String contentDisposition = "attachment;filename=test.txt";
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        //返回文件
        for (int i = 0; i < 10000; i++) {
            String str = UUID.randomUUID().toString() + "\n\t";
            InputStream inputStream = getStringStream(str);
            IOUtils.copy(inputStream, outputStream);
        }
    }


    public void testHelloGuice() {
        Injector injector = Guice.createInjector();
        GuiceDemo helloGuice = injector.getInstance(GuiceDemo.class);
        helloGuice.say();
    }

    public InputStream getStringStream(String str) {
        if (str != null && !str.trim().equals("")) {
            ByteArrayInputStream stringStream = new ByteArrayInputStream(str.getBytes());
            return stringStream;
        }
        return null;
    }
}
