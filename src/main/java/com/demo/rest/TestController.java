package com.demo.rest;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Niu Li
 * @date 2016/7/24
 */
@Path("/test")
public class TestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)//指定返回类型为text_plain
    public String helloWorld() {

        return "hello world!";
    }

    @GET
    @Path("/date")
    @Produces(MediaType.APPLICATION_JSON)//指定返回类型为text_plain
    public String date(@QueryParam("msg") String str,
                       @QueryParam("startTime") String startTime) throws ParseException {
        return str + new SimpleDateFormat().parse(startTime).toLocaleString();
    }

    @POST
    @Path("file")
    @Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON})  //指定接受类型
    @Produces(MediaType.APPLICATION_JSON)  //返回类型
    public Object importVehicleOwnerGb(@QueryParam("appkey") String appkey,
                                       @QueryParam("sign") String sign,
                                       @QueryParam("signt") String signt,
                                       @FormDataParam("name") String name,
                                       @FormDataParam("file") File file,
                                       @FormDataParam("file") FormDataContentDisposition fileMetaData,
                                       @FormDataParam("file1") File file1,
                                       @FormDataParam("file1") FormDataContentDisposition fileMetaData1
    ) {
        file.delete();
        file1.delete();
        return " SUCCEED: " + fileMetaData + "===" + fileMetaData1;
    }

    @POST
    @Path("/file/upload")
    @Consumes({MediaType.MULTIPART_FORM_DATA})  //指定接受类型
    @Produces(MediaType.APPLICATION_JSON)  //返回类型
    public Object uploadFile(
            @HeaderParam("userId") int userId,
            @FormDataParam("file") File file,
            @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
        //调用相应的service处理
        return fileMetaData.getName() + "====" + file.getName();
    }
}