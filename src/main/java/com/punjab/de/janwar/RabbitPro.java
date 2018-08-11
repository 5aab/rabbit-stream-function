package com.punjab.de.janwar;

import com.punjab.de.janwar.config.CommonSettings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@Slf4j
@AllArgsConstructor
@ComponentScan(basePackages = {
        "com.punjab.de.janwar.config","com.punjab.de.janwar.domain","com.punjab.de.janwar.producer"
})
public class RabbitPro {

    private CommonSettings commonSettings;

    @RequestMapping("/test1")
    @ResponseBody
    String home() {
        log.info(commonSettings.getEnabled());
        return "Hello World How r u!";
    }

    public static void main(String[] args) throws Exception {
        log.info("Hello World 2 3 4 ");
        SpringApplication.run(RabbitPro.class, args);
    }
}
