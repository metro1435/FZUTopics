package com.fzutopic.service;


import java.util.Map;

public interface AuditService {
    //调用百度api进行文本检测，221701401负责
    Map textAudit (String text);
}
