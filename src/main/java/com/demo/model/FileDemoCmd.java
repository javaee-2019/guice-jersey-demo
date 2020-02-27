package com.demo.model;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import lombok.Data;

import java.io.File;

@Data
public class FileDemoCmd {

    private String name;

    @FormDataParam("file")
    File file;

    @FormDataParam("file")
    FormDataContentDisposition fileMetaData;
}
