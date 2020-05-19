package com.fzutopic;

import com.fzutopic.dao.UserDao;
import com.fzutopic.model.User;
import com.fzutopic.service.UserService;
import com.fzutopic.service.UserServiceImpl;
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

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    private String token;
    private UserDao userDao;

    @Before
    public void setupMockMvc() throws Exception {
        //初始化MockMvc对象
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
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
     * 获取个人信息
     *  221701402负责
     * @throws Exception
     */
    @Test
    public void getInfoTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/setting")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    /**
     * 修改个人信息
     * 返回码200
     *  221701402负责
     * @throws Exception
     */
    @Test
    public void updateNicknameIcon() throws Exception {
        String json =" {\n" +
                "        \t\"nickname\":\"你才傻\"\n" +
                "        }";
        mvc.perform(MockMvcRequestBuilders.put("/user/setting")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .header("token", token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }
    /**
     * 通过userid获取匿名
     * 返回码200
     *  221701402负责
     * @throws Exception
     */
    @Test
    public void getNameByUserIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/nickname/123456780")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }
    /**
     * 通过College获取用户列表
     * 返回码200
     *  221701416测试
     * @throws Exception
     */
    @Test
    public void getUserListByCollegeTest() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/user/list/数学与计算机科学学院")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print());
    }
}
