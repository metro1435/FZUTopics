package com.fzutopic.service;

import com.baidu.aip.contentcensor.AipContentCensor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@Service
@RestController
public class AuditServiceImpl implements AuditService{
    //分别为百度api的appid，apikey，secretkey
    private static final String APP_ID = "19705917";
    private static final String API_KEY = "YoKDaZy5xL6gFtKGUeuq3XVL";
    private static final String SECRET_KEY = "2pB1lv2vz3SXVZ9srKwv492syF1hOCMT";

    //调用百度api进行文本检测，221701401负责
    public Map textAudit(String text) {
        AipContentCensor client=new AipContentCensor(APP_ID, API_KEY,SECRET_KEY);
        JSONObject response = client.textCensorUserDefined(text);
        Map map=response.toMap();
        return map;
    }
}
