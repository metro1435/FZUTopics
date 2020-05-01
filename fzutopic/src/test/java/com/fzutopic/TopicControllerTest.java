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
public class TopicControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setupMockMvc() throws Exception {
        //初始化MockMvc对象
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    /**
     * 获取所有话题，按热度倒序，一组16个
     * 选取比对热度进行测试，auditstatus为0的不会在这
     * 需满足数据库中有相关数据
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void getTopicTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/topic")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.total").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[0].heats").value("40"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[1].heats").value("34"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[2].heats").value("0"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 按指定topicid获取话题
     * 选取比对热度及浏览量进行测试
     * 需满足数据库中有相关数据
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void getTopicByTopicIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/topic/topicid/t12345678020200501121742")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].views").value("146"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].heats").value("39"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].auditstatus").value("1"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 按指定topicid获取话题
     * 选取比对热度进行测试
     * 需满足数据库中有相关数据
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void getTopicByTagIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/topic/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[0].heats").value("40"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[1].heats").value("34"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 按title获取话题,支持模糊搜索
     * 选取比对热度及总数进行测试
     * 需满足数据库中有相关数据
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void getTopicByTitleTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/topic/title/话")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.total").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[0].heats").value("40"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[1].heats").value("34"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[2].heats").value("0"))
                .andDo(MockMvcResultHandlers.print());
    }
}
