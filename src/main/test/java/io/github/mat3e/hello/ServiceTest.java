package io.github.mat3e.hello;

import io.github.mat3e.lang.Lang;
import io.github.mat3e.lang.LangRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServiceTest {
    //private Service SUT = new Service(); //System Under Test :)
    private final static String wMSG = new String("Hello ");
    private final static String FALLBACK_ID_WELCOME = new String("Czesc ");


    @Test
    public void test_nullName_prepareGreeting_returnsGreetingWithFallbackName() throws Exception {
        //given
        var mockRepository = getMockLangRepository();

        var SUT = new Service(mockRepository);
        //when
        var result = SUT.prepareGreeting(null,"-1");

        //then
        assertEquals(wMSG + Service.FALLBACK_NAME + "!\n",result);
    }
    @Test
    public void test_nullLang_prepareGreeting_returnsGreetingWithFallbackIdLang() throws Exception {
        //given
        var mockRepository = fallbackLangRepository();

        var SUT = new Service(mockRepository);
        //when
        var result = SUT.prepareGreeting(null,null);

        //then [check if we get expected result on output]
        assertEquals(FALLBACK_ID_WELCOME + Service.FALLBACK_NAME + "!\n",result);
    }

    @Test
    public void test_nonExistingTextLang_prepareGreeting_returnsGreetingWithFallbackIdLang() throws Exception {
        //given
        var mockRepository = fallbackLangRepository();

        var SUT = new Service(mockRepository);
        //when
        var result = SUT.prepareGreeting(null,"wrong_lang_code");

        //then [check if we get expected result on output]
        assertEquals(Service.FALLBACK_LANG.getWelcomeMsg() + Service.FALLBACK_NAME + "!\n",result);
    }
    private LangRepository fallbackLangRepository(){
        return new LangRepository(){
            @Override
            public Optional<Lang> findById(Integer id) {
                if(id.equals(Service.FALLBACK_LANG.getId()))
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                return Optional.empty();
            }
            @Override
            public Optional<Lang> findByCode(String code) {
                if(code.equals(Service.FALLBACK_LANG.getCode()))
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                return Optional.empty();
            }
        };
    }
    @Test
    public void test_nonExistingLang_prepareGreeting_returnsGreetingWithFallbackLang() throws Exception {
        //given
        var mockRepository = new LangRepository(){
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };

        var SUT = new Service(mockRepository);
        //when
        var result = SUT.prepareGreeting(null,"-1");

        //then [check if we get expected result on output]
        assertEquals(Service.FALLBACK_LANG.getWelcomeMsg() + Service.FALLBACK_NAME + "!\n",result);
    }

    @Test
    public void test_name_prepareGreeting_returnsGreetingWithName() throws Exception {
        //given
        var mockRepository = getMockLangRepository();
        var SUT = new Service(mockRepository);
        String name = "test";

        //when
        var result = SUT.prepareGreeting(name, "-1");

        //then [check if we get expected result on output]
        assertEquals(wMSG +  name + "!\n",result);
    }


    private LangRepository getMockLangRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, wMSG, null));
            }
        };
    }


}
