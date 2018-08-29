package com.one.wsy.wzxlib.util.soap;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "soap12:Body", strict = false)

public class Body {

    @Element(name = "ADInquiry", required = false)

    private ADInquiry aDInquiry;

    public ADInquiry getaDInquiry() {
        return aDInquiry;
    }

    public void setaDInquiry(ADInquiry aDInquiry) {
        this.aDInquiry = aDInquiry;
    }
}
