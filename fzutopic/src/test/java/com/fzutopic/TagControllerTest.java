package com.fzutopic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
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


    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
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
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].times").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].times").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].times").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[3].times").value("0"))
                .andDo(MockMvcResultHandlers.print());
    }

}
