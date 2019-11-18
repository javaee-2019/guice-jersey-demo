package com.demo.service.impl;

import com.demo.service.GuiceDemo;
import com.google.inject.Singleton;

import java.util.Date;

@Singleton
public class GuiceDemoImpl implements GuiceDemo {

    @Override
    public void say() {
        System.out.println("new Date().toLocaleString() = " + new Date().toLocaleString());
    }
}
