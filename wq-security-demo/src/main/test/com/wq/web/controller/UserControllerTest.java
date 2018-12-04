package com.wq.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by qwu on 2018/12/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        //模拟请求
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
              .param("username","wuqingvika")
                .param("age","24")
                .param("ageTo","28")
                //.param("nickname","wuqingvika")
                /*.param("size","15")//每页15条
                .param("page","3")//三页
                .param("sort","age,desc")*///排序按年齡降序
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
        //jsonPath github搜 有说明 $代表传来的整个json根 因为我们usercontroller返回的是json数组所以用length();
        //具体见https://github.com/json-path/JsonPath
    }
}
