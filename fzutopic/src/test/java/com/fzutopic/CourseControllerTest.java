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
public class CourseControllerTest {
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
    }

    /**
     * 根据课程名查询测试
     * 需满足数据库中有相关数据
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void getByCourseTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/course/1/sort/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].teacherid").value("h010101"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].teacherid").value("h010102"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 根据教师名查询测试
     * 需满足数据库中有相关数据
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void getByTeacherTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/course/1/sort/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].courseid").value("c010101"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].courseid").value("c010103"))
                .andDo(MockMvcResultHandlers.print());
    }

}
