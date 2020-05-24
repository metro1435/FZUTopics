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
public class TagControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    private String token;

    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        String json = "{\n" +
                "\t\"userid\":\"admin0001\",\n" +
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
     * 获取所有标签，按使用频次倒序
     * 选取比对使用次数进行测试
     * 需满足数据库中有相关数据
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void getTagTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tag")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].times").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].times").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].times").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[3].times").value("0"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 获取所有标签
     * 选取比对tagid进行测试
     * 需满足数据库中有相关数据
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void getAllTagTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/allTag")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].tagid").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].tagid").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].tagid").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[3].tagid").value("4"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 新增标签
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void postTagTest() throws Exception {
        String json ="[\n" +
                "        {\n" +
                "        \t\"tagid\":\"2\",\n" +
                "            \"topicid\": \"t12345678920200501121932\"\n" +
                "        },\n" +
                "        {\n" +
                "           \"tagid\":\"3\",\n" +
                "            \"topicid\": \"t12345678920200501121932\"\n" +
                "        \t\n" +
                "        }\n" +
                "]";
        mvc.perform(MockMvcRequestBuilders.post("/topic/tag")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 新增标签
     *  221701416负责
     * @throws Exception
     */
    @Test
    public void adminTagBynameTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put("/admin/tag/name?tagname=12")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}


