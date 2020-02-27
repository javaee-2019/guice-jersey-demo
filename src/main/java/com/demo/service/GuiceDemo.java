package com.demo.service;

import com.demo.service.impl.GuiceDemoImpl;
import com.google.inject.ImplementedBy;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@ImplementedBy(GuiceDemoImpl.class)
public interface GuiceDemo {

    void say();

    String upload(File file) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
