
/*package com.fzutopic;

import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TopicControllerTests {


    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private String token;

    @Before
    public void setupMockMvc() throws Exception {
        //初始化MockMvc对象
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        //模拟登录，获取token
        String json = "{\n" +
                "\t\"userid\":\"181700141\",\n" +
                "\t\"password\":\"111\"\n" +
                "}";
        String result= mvc.perform(MockMvcRequestBuilders.get("/login")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
        )
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONObject jsonObject = new JSONObject(result);
        //获取token
        token=jsonObject.getString("token");
    }*/

    /**
     * 新增话题测试用例
     * @throws Exception
     */
    /*@Test
    public void addTopic() throws Exception {
        String json = "{\n" +
                "\t\"title\":\"话题测试数据3\",\n" +
                "    \"text\": \"话题内容未通过（用户选择匿名）\",\n" +
                "    \"likes\": \"0\",\n" +
                "    \"unlikes\":\"0\",\n" +
                "    \"userid\": \"123456789\",\n" +
                "    \"time\": \"2020-05-01 12:21:30\",\n" +
                "    \"isanony\": \"1\",\n" +
                "    \"view\": \"0\",\n" +
                "    \"heats\": \"1111\",\n" +
                "    \"commentcount\": \"4\",\n" +
                "    \"auditstatus\": \"0\",\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())//传json参数
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}*/