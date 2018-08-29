package com.one.wsy.wzxlib.util.soap;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "ADInquiry", strict = false)
@Namespace(reference = "http://tempuri.org/")
public class ADInquiry {

    @Element(name = "srt", required = false)
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
