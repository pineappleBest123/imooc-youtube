package com.imooc;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImoocYoutubeApp {
    public static void main(String[] args){
        ApplicationContext app = SpringApplication.run(ImoocYoutubeApp.class, args);
    }
}
