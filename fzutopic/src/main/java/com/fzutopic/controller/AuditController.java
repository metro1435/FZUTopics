package com.fzutopic.controller;

import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.service.AuditServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@RestController
public class AuditController {
    @Resource(name = "auditServiceImpl")
    AuditServiceImpl auditService;

    //文本检测，调用百度的api实现，221701401负责
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/audit/text/{text}")
    public @ResponseBody Map textAudit(@PathVariable(name="text") String text) {
        return auditService.textAudit(text);
    }

}
