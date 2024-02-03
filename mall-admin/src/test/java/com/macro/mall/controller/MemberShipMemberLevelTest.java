package com.macro.mall.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/** 
* UmsMemberLevelController Tester. 
* 
* @author <Authors name> 
* @since <pre>2�� 2, 2024</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MemberShipMemberLevelTest {

    @Autowired
    private UmsMemberLevelController umsMemberLevelController;

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void before(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
    *
    * Method: list(@RequestParam("defaultStatus") Integer defaultStatus)
    * There are two situations: valid memberLever, invalid memberLevel
    */
    @Test
    public void testList() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/memberLevel/list").accept(MediaType.APPLICATION_JSON).
                param("defaultStatus","1")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void testListInvalidMemberLevel() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/memberLevel/list").accept(MediaType.APPLICATION_JSON).
                param("defaultStatus","7")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }


} 
