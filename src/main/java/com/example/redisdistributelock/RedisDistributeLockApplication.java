package com.example.redisdistributelock;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Scanner;

@SpringBootApplication
public class RedisDistributeLockApplication {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int port = input.nextInt();
        new SpringApplicationBuilder(RedisDistributeLockApplication.class).properties("server.port=" + port).run(args);
    }

}
