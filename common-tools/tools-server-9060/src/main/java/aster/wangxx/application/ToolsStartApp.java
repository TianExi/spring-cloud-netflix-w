package aster.wangxx.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ToolsApplication
 * @Description TODO
 * @Author By wangxx
 * @Date 10:24 2021/9/24
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan({"aster.wangxx"})
public class ToolsStartApp {

    public static void main (String [] args) {
        SpringApplication.run(ToolsStartApp.class, args);
    }
}
