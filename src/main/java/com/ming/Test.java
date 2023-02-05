package com.ming;

import org.tron.trident.core.ApiWrapper;

public class Test {
    public static void main(String[] args) {
        ApiWrapper wrapper = new ApiWrapper("https://nile.trongrid.io/", "https://nile.trongrid.io/",
                "13cba328-e4df-4c14-b5fd-77d9f92df2f7");
        System.out.println(wrapper);
    }
}
