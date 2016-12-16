package com.rainmakeross.tutorial.hdfs.springboot;

import com.rainmakeross.tutorial.hdfs.springboot.services.HdfsFileAccess;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class App {

    @Autowired
    HdfsFileAccess hdfsFileAccess;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            //hdfsFileAccess.readFile("test");
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

    @Bean
    FileSystem createFileSystem() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://192.168.0.101:9000");
        conf.set("dfs.permissions","false");
        return FileSystem.get(conf);
    }

}