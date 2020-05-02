package com.fzutopic;

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
public class CommentControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    private String token;

    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    /**
     * 获取topicid对应的评论列表，按时间倒序
     * 选取比对结果总数及其commentid进行测试
     * 需满足数据库中有相关数据
     * 221701401负责
     * @throws Exception
     */
    @Test
    public void getCommentsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/topic/t12345678920200501121932/comment")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.total").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[0].commentid").value("12345678920200501140127"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[1].commentid").value("12345678120200501130809"))
                .andDo(MockMvcResultHandlers.print());
    }

}
