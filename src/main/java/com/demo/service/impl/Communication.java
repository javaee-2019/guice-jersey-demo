package com.demo.service.impl;

import com.demo.config.BasicModule;
import com.demo.service.Communicator;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.RequiredArgsConstructor;

import java.util.Date;

public class Communication {

    private Communicator communicator;

    @Inject
    public Communication(Communicator communicator) {
        this.communicator = communicator;
    }

    public Communication(Boolean keepRecords) {
        if (keepRecords) {
            System.out.println("Message logging enabled");
        }
    }

    public boolean sendMessage(String message) {
        communicator.sendMessage(message);
        return true;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BasicModule());

        Communication comms = injector.getInstance(Communication.class);

        comms.sendMessage("hello world");

        new Communication(true).sendMessage(new Date().toLocaleString());
    }
}
