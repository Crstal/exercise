package com.crystal.pattern.proxy.statis;

import com.crystal.pattern.proxy.Person;

public class Son implements Person {

    @Override
    public String findLove() {
        return "儿子：要求白富美";
    }
}
