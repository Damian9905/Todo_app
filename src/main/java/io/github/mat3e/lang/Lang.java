package io.github.mat3e.lang;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//data structure POJO - Plain Old Java Object
//we have only private fields, setters and getters
@Entity
@Table( name = "languages" )
public class Lang {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;
    @Column(name ="welcomemsg")
    private String welcomeMsg;
    private String code;

    /**
     * for hibernate
     */
    @SuppressWarnings("unused")
    public Lang() {
    }
    public Lang(Integer id, String welcomeMsg, String code) {
        this.id = id;
        this.welcomeMsg = welcomeMsg;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
