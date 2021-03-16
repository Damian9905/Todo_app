package io.github.mat3e.lang;

class LangDTO{
    private int id;
    private String code;

   LangDTO(Lang lang){
        this.id = lang.getId();
        this.code = lang.getCode();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
