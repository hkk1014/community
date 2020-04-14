package com.hk.community.community;

import java.util.UUID;

/**
 * 作者: hekang
 * 时间: 2020-04-13 22:33
 * 描述:
 **/

public class Test {
    public static void main(String[] args) {
        String replace = UUID.randomUUID().toString().replace("-", "");

        System.out.println(replace);
    }
}
