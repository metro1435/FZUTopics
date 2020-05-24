package com.fzutopic;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
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
public class ReplyControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    private String token;

    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        String json = "{\n" +
                "\t\"userid\":\"123456789\",\n" +
                "\t\"password\":\"123456\"\n" +
                "}";
        String result= mvc.perform(MockMvcRequestBuilders.post("/login")
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
    }

    /**
     * 根据commentid找回复
     * 选取比对回复内容进行测试
     * 需满足数据库中有相关数据
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void selectReplyByCommentIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/topic/12345678920200501140127/reply")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("$.data.total").value("1"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.data.list[0].text").value("评论"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 新增回复
     * 由于auditstatus的状态不同返回的时候没区别就没做两次
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void postReplyTest() throws Exception {
        String json ="{\n" +
                "    \"replyid\":\"12345678020200501141311\",\n" +
                "    \"text\": \"测试\",\n" +
                "    \"time\": \"2020-04-30 11:21:22\",\n" +
                "    \"isanony\":\"0\",\n" +
                "    \"commentid\":\"admin000120200501135930\",\n" +
                "    \"auditstatus\":\"1\"\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/comment/reply")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}



