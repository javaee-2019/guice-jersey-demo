package com.demo.rest;

import com.demo.model.GetSnapshotV2Vo;
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
import java.util.Date;
import java.util.UUID;

/**
 * @author hewen
 * @date 2019/11/19 15:23
 */
@Path("/")
public class IndexController {

    @GET
    @Path("")
    @Produces(value = MediaType.APPLICATION_JSON)
    public String helloWorld(@QueryParam("msg") String msg) {
        return "hello world!" + msg;
    }

}
