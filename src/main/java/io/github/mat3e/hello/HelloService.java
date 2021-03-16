package io.github.mat3e.hello;

import io.github.mat3e.lang.Lang;
import io.github.mat3e.lang.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class HelloService {
    static final String FALLBACK_NAME = "servlet";
    static final Lang FALLBACK_LANG = new Lang(1, "Hello ", "en");
    LangRepository repository;
    private final Logger logger = LoggerFactory.getLogger(Service.class);


    HelloService(LangRepository repository){
        this.repository = repository;
    }

    String prepareGreeting(String name, String lang){
        String welcomeMsg = repository.findByCode(lang).orElse(FALLBACK_LANG).getWelcomeMsg();
        return welcomeMsg + Optional.ofNullable(name).orElse(FALLBACK_NAME)+ "!\n";
    }


}
