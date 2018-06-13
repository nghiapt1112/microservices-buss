//package com.nghia.tut.mss.task;
//
//import com.netflix.hystrix.HystrixCommand;
//import com.netflix.hystrix.HystrixCommandGroupKey;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Random;
//
//public class SimpleControlledFailCommand extends HystrixCommand<String> {
//    private final double failureRate;
//    private final String message;
//    private final long delay;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleControlledFailCommand.class);
//
//    public SimpleControlledFailCommand(String message) {
//        this(0.0, 0, message);
//    }
//
//    public SimpleControlledFailCommand(double failureRate, String message) {
//        this(failureRate, 0, message);
//    }
//
//    public SimpleControlledFailCommand(double failureRate, long delay, String message) {
//        super(HystrixCommandGroupKey.Factory.asKey("default"));
//        if (failureRate > 1.0 || failureRate < 0.0) {
//            throw new IllegalStateException("failure rate has to be between 0.0 and 1.0");
//        }
//        this.failureRate = failureRate;
//        this.delay = delay;
//        this.message = message;
//    }
//
//    @Override
//    protected String run() throws Exception {
//        Random random = new Random();
//        Util.delay(this.delay);
//        if (random.nextDouble() <= this.failureRate) {
//            throw new RuntimeException("Throwing a deliberate exception - failure rate condition matched");
//        }
//        return this.message;
//    }
//
//    @Override
//    protected String getFallback() {
//        return "fallback";
//    }
//}
//
//class Util {
//    public static void delay(long millis) {
//        try {
//            Thread.sleep(millis);
//        } catch (InterruptedException e) {
//            //Ignore
//        }
//    }
//}
//
