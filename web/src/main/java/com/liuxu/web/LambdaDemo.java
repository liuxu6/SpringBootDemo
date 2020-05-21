package com.liuxu.web;

import java.util.function.*;

public class LambdaDemo {

    public static void main(String[] args) {

        Function<Integer, String> function = String :: valueOf;
        System.out.println(function.apply(111));

        Consumer<String> consumer = System.out :: println;
        consumer.accept("hello");

        Supplier<String> supplier = "hello" :: toUpperCase;
        System.out.println(supplier.get());

        Predicate<String> predicate = "##hello" :: startsWith;
        System.out.println(predicate.test("##"));


        Runnable runnable = () ->{ System.out.println("title"); };
        new Thread(runnable).start();
    }


}
