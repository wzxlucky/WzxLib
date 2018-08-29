package com.one.wsy.wzxlib.util.soap;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "soap12:Header", strict = false)

public class Header {
    @Element(name = "Identify", required = false)
    public Identify identify;

    public Identify getIdentify() {
        return identify;
    }

    public void setIdentify(Identify identify) {
        this.identify = identify;
    }
}
