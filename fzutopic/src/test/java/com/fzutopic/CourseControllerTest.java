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
     * 根据课程名查询测试
     * 需满足数据库中有相关数据
     *  221701401负责
     * @throws Exception
     */
    @Test
    public void getByCourseTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/course/1/sort/1/page/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
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
        mvc.perform(MockMvcRequestBuilders.get("/course/1/sort/2/page/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试根据课程ID和教师ID获取信息
     * @throws Exception
     * @author 呼叫哆啦A梦
     */
    @Test
    public void getInfoByCTID1() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/ctinfo/c010101/h010101")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 测试根据课程ID和教师ID获取信息
     * 测试是否可用准确检测出教师与课程未存在教学关系
     * @throws Exception
     * @author 呼叫哆啦A梦
     */
    @Test
    public void getInfoByCTID2() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/ctinfo/c010101/h110101")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(500))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getAllCourseTest() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/admin/getcourse")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }


    /**
     * 新增标签
     *  221701416负责
     * @throws Exception
     */
    @Test
    public void insertcourseTest() throws Exception {
        String json = "{\n" +
                "\t\"credits\":2.5,\n" +
                "\t\"courseid\":\"c010103\",\n" +
                "\t\"coursename\":\"天文学\",\n" +
                "\t\"teacherid\":\"h010103\"\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/admin/addcourse")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
