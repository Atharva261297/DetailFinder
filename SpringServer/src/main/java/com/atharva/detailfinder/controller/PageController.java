package com.atharva.detailfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/page/{page}")
    public String getPage(@PathVariable(name = "page") String pageName) {
        return pageName;
    }

}
