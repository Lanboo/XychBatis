package com.xych.mybatis.pojo;

public class User
{
    private String id;
    private String c_user_code;
    private String c_user_name;
    private String c_nick_name;
    private String c_user_pwd;
    private String c_dv_state;
    private String c_dv_card_type;
    private String c_card_code;
    private String c_email;
    private String c_native_place;
    private String c_living_place;
    private String c_create_time;
    private String c_user_sex;
    private String c_res_code;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getC_user_code()
    {
        return c_user_code;
    }

    public void setC_user_code(String c_user_code)
    {
        this.c_user_code = c_user_code;
    }

    public String getC_user_name()
    {
        return c_user_name;
    }

    public void setC_user_name(String c_user_name)
    {
        this.c_user_name = c_user_name;
    }

    public String getC_nick_name()
    {
        return c_nick_name;
    }

    public void setC_nick_name(String c_nick_name)
    {
        this.c_nick_name = c_nick_name;
    }

    public String getC_user_pwd()
    {
        return c_user_pwd;
    }

    public void setC_user_pwd(String c_user_pwd)
    {
        this.c_user_pwd = c_user_pwd;
    }

    public String getC_dv_state()
    {
        return c_dv_state;
    }

    public void setC_dv_state(String c_dv_state)
    {
        this.c_dv_state = c_dv_state;
    }

    public String getC_dv_card_type()
    {
        return c_dv_card_type;
    }

    public void setC_dv_card_type(String c_dv_card_type)
    {
        this.c_dv_card_type = c_dv_card_type;
    }

    public String getC_card_code()
    {
        return c_card_code;
    }

    public void setC_card_code(String c_card_code)
    {
        this.c_card_code = c_card_code;
    }

    public String getC_email()
    {
        return c_email;
    }

    public void setC_email(String c_email)
    {
        this.c_email = c_email;
    }

    public String getC_native_place()
    {
        return c_native_place;
    }

    public void setC_native_place(String c_native_place)
    {
        this.c_native_place = c_native_place;
    }

    public String getC_living_place()
    {
        return c_living_place;
    }

    public void setC_living_place(String c_living_place)
    {
        this.c_living_place = c_living_place;
    }

    public String getC_create_time()
    {
        return c_create_time;
    }

    public void setC_create_time(String c_create_time)
    {
        this.c_create_time = c_create_time;
    }

    public String getC_user_sex()
    {
        return c_user_sex;
    }

    public void setC_user_sex(String c_user_sex)
    {
        this.c_user_sex = c_user_sex;
    }

    public String getC_res_code()
    {
        return c_res_code;
    }

    public void setC_res_code(String c_res_code)
    {
        this.c_res_code = c_res_code;
    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", c_user_code=" + c_user_code + ", c_user_name=" + c_user_name + ", c_nick_name=" + c_nick_name + ", c_user_pwd=" + c_user_pwd + ", c_dv_state=" + c_dv_state + ", c_dv_card_type=" + c_dv_card_type + ", c_card_code=" + c_card_code + ", c_email=" + c_email + ", c_native_place=" + c_native_place + ", c_living_place=" + c_living_place + ", c_create_time=" + c_create_time + ", c_user_sex=" + c_user_sex + ", c_res_code=" + c_res_code + "]";
    }
}