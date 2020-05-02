
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
public class CommentControllerTests {


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
     * 新增评论测试用例
     * @throws Exception
     */
    /*@Test
    public void addComment() throws Exception {
        String json = "{\n" +
                "\t\"commentid\":\"12345678020200501141106\",\n" +
                "  \"text\": \"测试\",\n" +
                "  \"likes\": \"0\",\n" +
                "  \"unlikes\": \"0\",\n" +
                "  \"time\": \"2020-04-30 11:21:22\",\n" +
                "  \"isanony\":\"0\",\n" +
                "  \"posterid\":\"123456780\",\n" +
                "  \"topicid\":\"t12345678920200501122130\",\n" +
                "  \"isreply\":\"0\",\n" +
                "  \"auditstatus\":\"1\",\n" +
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
