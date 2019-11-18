package com.demo.service;

import com.demo.service.impl.GuiceDemoImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy(GuiceDemoImpl.class)
public interface GuiceDemo {

    void say();
}
