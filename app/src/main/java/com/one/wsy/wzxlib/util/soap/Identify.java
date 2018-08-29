package com.one.wsy.wzxlib.util.soap;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "Identify", strict = false)
@Namespace(reference = "http://tempuri.org/")
public class Identify {

    @Element(name = "userName", required = false)
    private String userName;
    @Element(name = "passWord", required = false)
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
