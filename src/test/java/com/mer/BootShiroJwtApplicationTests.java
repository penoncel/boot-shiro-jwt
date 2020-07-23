package com.mer;

import com.mer.common.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootShiroJwtApplicationTests {

    @Test
    void contextLoads() {
//        String token = JwtUtil.createToken("15701556037");
//        System.out.println("new Token{}"+token);
//        boolean flag  = JwtUtil.verify(token);
//        System.out.println("验证Token{}"+flag);
        System.out.println("15701556037".substring(5, 11));
    }

}
