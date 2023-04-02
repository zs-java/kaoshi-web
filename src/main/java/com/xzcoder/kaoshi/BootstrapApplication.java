package com.xzcoder.kaoshi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * BootstrapApplication
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@SpringBootApplication
@MapperScan("com.xzcoder.kaoshi.**.mapper")
@ImportResource("classpath:spring/*.xml")
@ServletComponentScan("com.xzcoder.kaoshi.listener")
@Controller
public class BootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login.html";
    }

}
