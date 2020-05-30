package com.example.vishal.saltnpepper;

public class itemrest {

    private String head;
    private String desc;
    private String off;
    private String rat;

    public itemrest(String head, String desc, String off, String rat) {
        this.head = head;
        this.desc = desc;
        this.off = off;
        this.rat = rat;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getOff() {
        return off;
    }

    public String getRat() {
        return rat;
    }
}
