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
public class NewsControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private String token;

    @Before
    public void setupMockMvc() throws Exception {
        //初始化MockMvc对象
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }


    /**
     * 获取已发布新闻测试用例
     * @author 221701309
     */
    @Test
    public void selectNews() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/news")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)


        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[0].title").value("新闻1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[0].time").value("2020-04-28 13:26:27"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * //获取某一新闻内容（指定id）
     * @author 221701309
     */
    @Test
    public void selectNewsById() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/news/nadmin000120200427133109")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.text").value("新闻2内容"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.time").value("2020-04-27 13:31:09"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * //搜索新闻
     * @author 221701309
     */
    @Test
    public void selectNewsByTitle() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/news/name/新")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[1].title").value("新闻2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[1].time").value("2020-04-27 13:31:09"))
                .andDo(MockMvcResultHandlers.print());
    }

}
