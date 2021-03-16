package io.github.mat3e.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;

/**
 * Introduction handling
 */

@RestController
@RequestMapping("/api")
class HelloServlet {
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    private HelloService service;


    HelloServlet(HelloService service){
        this.service = service;
    }

    @GetMapping
    public void writeHello(@RequestParam String name, @RequestParam String lang, HttpServletResponse resp) throws IOException {
        logger.info("Hello request got :)");
        resp.getWriter().write(service.prepareGreeting(name,lang));
    }


}
