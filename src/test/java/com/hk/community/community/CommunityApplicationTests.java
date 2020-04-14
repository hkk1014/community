package com.hk.community.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {
    @Value("${web.upload-path}")
    String path;
    @Test
    void contextLoads() {

        System.out.println(path);
    }

}
