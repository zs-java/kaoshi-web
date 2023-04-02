package com.xzcoder.kaoshi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
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
        SpringApplicationBuilder builder = new SpringApplicationBuilder(BootstrapApplication.class);
        builder.run(args)
                .addApplicationListener(new ApplicationPidFileWriter());
        System.out.println(
                "#############################################################\n" +
                        "┏━┓╺┳╸┏━┓┏━┓╺┳╸   ╻ ╻┏━┓   ┏━┓╻ ╻┏━╸┏━╸┏━╸┏━┓┏━┓\n" +
                        "┗━┓ ┃ ┣━┫┣┳┛ ┃    ┃ ┃┣━┛   ┗━┓┃ ┃┃  ┃  ┣╸ ┗━┓┗━┓\n" +
                        "┗━┛ ╹ ╹ ╹╹┗╸ ╹    ┗━┛╹     ┗━┛┗━┛┗━╸┗━╸┗━╸┗━┛┗━┛\n");
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login.html";
    }

}
