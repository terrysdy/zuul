package com.github.terrysdy.zuultestserver1;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pojun
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("hello")
    public String hello(@RequestParam(value = "uid", required = false) String uid) {
        if (StringUtils.isEmpty(uid)) {
            uid = "";
        }
        return "hello" + uid;
    }
}
