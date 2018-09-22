package com.yr.log.controller;

import com.yr.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LogController {

    @Autowired
    private LogService logServiceImpl;

}
