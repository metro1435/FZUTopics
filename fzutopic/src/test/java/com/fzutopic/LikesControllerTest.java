package com.fzutopic;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
//指定执行顺序
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@Transactional
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
                "\t\"userid\":\"123456780\",\n" +
                "\t\"password\":\"123456\"\n" +
                "}";
        String result = mvc.perform(MockMvcRequestBuilders.get("/login")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
        )
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONObject jsonObject = new JSONObject(result);
        //获取token
        token = jsonObject.getString("token");
    }


    /**
     * 新增对话题踩赞测试用例
     * 测试用户非首次踩赞
     *
     * @throws Exception
     */
    @Test
    public void a_addTopcLikes1() throws Exception {
        String json = "{\n" +
                "    \"topicid\": \"t12345678020200501121742\",\n" +
                "    \"likedstatus\": 1\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/topiclikes")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(500))
                .andDo(MockMvcResultHandlers.print());

    }

    /**
     * 新增对话题踩赞测试用例
     * 测试LikedStatus参数值非法
     *
     * @throws Exception
     */
    @Test
    public void a_addTopcLikes2() throws Exception {
        String json = "{\n" +
                "    \"topicid\": \"t12345678120200501134819\",\n" +
                "    \"likedstatus\": 3\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/topiclikes")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(500))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 新增对话题踩赞测试用例
     * 测试话题不存在
     *
     * @throws Exception
     */
    @Test
    public void a_addTopcLikes3() throws Exception {
        String json = "{\n" +
                "    \"topicid\": \"t1234567812020050113489\",\n" +
                "    \"likedstatus\": 1\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/topiclikes")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(500))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 新增对话题踩赞测试用例
     * 测试是否踩赞成功
     *
     * @throws Exception
     */
    @Test
    public void a_addTopcLikes4() throws Exception {
        String json = "{\n" +
                "    \"topicid\": \"t12345678120200501134819\",\n" +
                "    \"likedstatus\": 1\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/topiclikes")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }


    /**
     * 修改对话题踩赞测试用例
     * 用户还未对该话题踩赞过
     *
     * @throws Exception
     */
    @Test
    public void b_updateTopickLikes1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/user/topic/topiclikes?topicid=t18170014120200428111111&likes=0")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(500))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 修改对话题点赞（踩）测试用例
     * 用户并未对改踩赞进行修改
     *
     * @throws Exception
     */
    @Test
    public void b_updateTopickLikes2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/user/topic/topiclikes?topicid=t12345678020200501121742&likes=1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(500))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 修改对话题点赞（踩）测试用例
     * 测试是否修改成功
     *
     * @throws Exception
     */
    @Test
    public void b_updateTopickLikes3() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/user/topic/topiclikes?topicid=t12345678120200501134819&likes=0")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 用户取消对话题点赞（踩）测试用例
     * 测试是否取消成功
     *
     * @throws Exception
     */
    @Test
    public void c_deleteTopicLikeds() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/user/topic/topiclikes?topicid=t12345678120200501134819")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 新增对评论回复踩赞用例
     * 测试sort参数值非法
     *
     * @throws Exception
     */
    @Test
    public void a_addcommentLikes1() throws Exception {
        String json = "{\n" +
                "    \"itemid\": \"12345678020200501141319\",\n" +
                "    \"likedstatus\": 1\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/comment/commentlikes?sort=3")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(500))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 新增对评论回复踩赞用例
     * 测试likeStatus参数值非法
     *
     * @throws Exception
     */
    @Test
    public void a_addcommentLikes2() throws Exception {
        String json = "{\n" +
                "    \"itemid\": \"12345678020200501141319\",\n" +
                "    \"likedstatus\": 3\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/comment/commentlikes?sort=0")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(500))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 新增对评论回复踩赞用例
     * 测试对话题评论回复踩赞
     *
     * @throws Exception
     */
    @Test
    public void a_addcommentLikes3() throws Exception {
        String json = "{\n" +
                "    \"itemid\": \"12345678020200501141319\",\n" +
                "    \"likedstatus\": 1\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/comment/commentlikes?sort=0")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 新增对评论回复踩赞用例
     * 测试对话题评论踩赞
     *
     * @throws Exception
     */
    @Test
    public void a_addcommentLikes4() throws Exception {
        String json = "{\n" +
                "    \"itemid\": \"12345678120200501130809\",\n" +
                "    \"likedstatus\": 0\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/comment/commentlikes?sort=1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 新增对评论回复踩赞用例
     * 测试对课程（教师）评论踩赞
     *
     * @throws Exception
     */
    @Test
    public void a_addcommentLikes5() throws Exception {
        String json = "{\n" +
                "    \"itemid\": \"12345678020200501143117\",\n" +
                "    \"likedstatus\": 0\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user/topic/comment/commentlikes?sort=2")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 修改对评论踩赞测试用例
     * 测试未修改修改话题评论踩赞
     *
     * @throws Exception
     */
    @Test
    public void b_updatecommentLikes1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.
                put("/user/topic/comment/commentlikes?itemid=12345678020200501141319&likes=1&sort=0")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(500))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 修改对评论踩赞测试用例
     * 测试修改话题评论回复踩赞
     *
     * @throws Exception
     */
    @Test
    public void b_updatecommentLikes2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.
                put("/user/topic/comment/commentlikes?itemid=12345678020200501141319&likes=0&sort=0")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 修改对评论踩赞测试用例
     * 测试修改话题评论踩赞
     *
     * @throws Exception
     */
    @Test
    public void b_updatecommentLikes3() throws Exception {
        mvc.perform(MockMvcRequestBuilders.
                put("/user/topic/comment/commentlikes?itemid=12345678120200501130809&likes=1&sort=1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 修改对评论踩赞测试用例
     * 测试修改课程（教师）评论踩赞
     *
     * @throws Exception
     */
    @Test
    public void b_updatecommentLikes4() throws Exception {
        mvc.perform(MockMvcRequestBuilders.
                put("/user/topic/comment/commentlikes?itemid=12345678020200501143117&likes=1&sort=2")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 用户取消点赞（踩）测试用例
     * 取消对话题评论回复踩赞
     *
     * @throws Exception
     */
    @Test
    public void c_deletecommentLikeds1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/user/topic/comment/commentlikes?itemid=12345678020200501141319&sort=0")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 用户取消点赞（踩）测试用例
     * 取消对话题评论踩赞
     *
     * @throws Exception
     */
    @Test
    public void c_deletecommentLikeds2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/user/topic/comment/commentlikes?itemid=12345678120200501130809&sort=1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 用户取消点赞（踩）测试用例
     * 取消对课程（教师）评论踩赞
     *
     * @throws Exception
     */
    @Test
    public void c_deletecommentLikeds3() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/user/topic/comment/commentlikes?itemid=12345678020200501143117&sort=2")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }
}
