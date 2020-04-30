package com.fzutopic;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * 测试LikesController类
 *
 * @author 呼叫哆啦A梦
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LikesControllerTest {
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
    }

/*
    /**
     * 新增对话题点赞（踩）测试用例
     * 需满足数据库已经有该用户和话题的存在
     *
     * @throws Exception
     */
    @Test
    public void addTopcLikes() throws Exception {
        String json = "{\n" +
                "\t\"userID\":\"181700141\",\n" +
                "    \"topicid\": \"t18170014120200428111111\",\n" +
                "    \"likedstatus\": 1\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/topiclikes")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 修改对话题点赞（踩）测试用例
     * 需满足数据库已经有该条记录
     *
     * @throws Exception
     */
    @Test
    public void updateTopickLikes() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/user/topic/topiclikes?topicid=t18170014120200428111111&likes=0")
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 用户取消对话题点赞（踩）测试用例
     * 需满足数据库已经有该条记录
     * userid。。。。。。。。。。。
     *
     * @throws Exception
     */
    @Test
    public void deleteTopicLikeds() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/user/topic/topiclikes?topicid=t18170014120200428111111")
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 新增对评论点赞（踩）测试用例
     * 需满足数据库已经有该用户和评论的存在
     *
     * @throws Exception
     */
    @Test
    public void addcommentLikes() throws Exception {
        String json = "{\n" +
                "\t\"userID\":\"181700141\",\n" +
                "    \"itemid\": \"18170014120200428111111\",\n" +
                "    \"likedstatus\": 1\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/comment/commentlikes")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 修改对评论点赞（踩）测试用例
     * 需满足数据库已经有该条记录
     *
     * @throws Exception
     */
    @Test
    public void updatecommentLikes() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/user/topic/comment/commentlikes?itemid=18170014120200428111111&likes=0")
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 用户取消点赞（踩）测试用例
     * 需满足数据库已经有该条记录
     * userid。。。。。。。。。。。
     *
     * @throws Exception
     */
    @Test
    public void deletecommentLikeds() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/user/topic/comment/commentlikes?itemid=18170014120200428111111")
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
