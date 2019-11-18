package com.demo.service.impl;

import com.demo.service.Communicator;

public class DefaultCommunicatorImpl implements Communicator {
    @Override
    public boolean sendMessage(String message) {
        System.out.println("Sending Message + " + message);
        return true;
    }
}
