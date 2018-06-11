//package com.nghia.tut.mss.task;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import java.util.Random;
//
//@Component
//public class HystrixCommandInvokerTask {
//
//    @Scheduled(fixedDelay=5000, initialDelay = 10000)
//    public void invoke() {
//        for (int i = 0; i < 10; i++) {
//            new SimpleControlledFailCommand(0.2, (long)new Random().nextDouble()*10000, "Hello").execute();
//        }
//    }
//}
