package com.tour.gangwon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/sub/sub11")
    public String sub11() { return "sub/sub11"; }

    @GetMapping("/sub/sub11_1")
    public String sub11_1() { return "sub/sub11_1"; }

    @GetMapping("/sub/sub11_2")
    public String sub11_2() { return "sub/sub11_2"; }

    @GetMapping("/sub/sub11_3")
    public String sub11_3() { return "sub/sub11_3"; }

    @GetMapping("/sub/sub11_4")
    public String sub11_4() { return "sub/sub11_4"; }

    @GetMapping("/sub/sub11_5")
    public String sub11_5() { return "sub/sub11_5"; }

    @GetMapping("/sub/sub11_6")
    public String sub11_6() { return "sub/sub11_6"; }

    @GetMapping("/sub/sub11_7")
    public String sub11_7() { return "sub/sub11_7"; }

    @GetMapping("/sub/sub11_8")
    public String sub11_8() { return "sub/sub11_8"; }

    @GetMapping("/sub/sub11_9")
    public String sub11_9() { return "sub/sub11_9"; }

    @GetMapping("/sub/sub11_10")
    public String sub11_10() { return "sub/sub11_10"; }

    @GetMapping("/sub/sub11_11")
    public String sub11_11() { return "sub/sub11_11"; }

    @GetMapping("/sub/sub11_12")
    public String sub11_12() { return "sub/sub11_12"; }

    @GetMapping("/sub/sub12")
    public String sub12() { return "sub/sub12"; }

    @GetMapping("/sub/sub13")
    public String sub13() { return "sub/sub13"; }

    @GetMapping("/sub/sub21")
    public String sub21() { return "sub/sub21"; }

    @GetMapping("/sub/sub22")
    public String sub22() { return "sub/sub22"; }

    @GetMapping("/sub/sub23")
    public String sub23() { return "sub/sub23"; }

    @GetMapping("/sub/sub31")
    public String sub31() { return "sub/sub31"; }

    @GetMapping("/sub/sub41")
    public String sub41() { return "sub/sub41"; }

    @GetMapping("/sub/sub42")
    public String sub42() { return "sub/sub42"; }

    @GetMapping("/sub/sub52")
    public String sub52() { return "sub/sub52"; }
}
