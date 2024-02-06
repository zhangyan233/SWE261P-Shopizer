package com.macro.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.macro.mall.dto.UmsAdminParam;
import org.junit.Before;
import org.junit.Test;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class InvalidTest {
    @Autowired
    private UmsAdminController umsAdminController;

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void before(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     *
     * Method: register(@Validated @RequestBody UmsAdminParam umsAdminParam)
     * use word Existing but Not in Membership Management System*
     * e.g. order
     */
    @Test
    public void testRegister() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setEmail("lydiazhang233@163.com");
        umsAdminParam.setIcon("XXX");
        umsAdminParam.setNickName("miu");
        umsAdminParam.setNote("test valid input in register");
        umsAdminParam.setPassword("1234");
        umsAdminParam.setUsername("262P");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/order/register").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam))).
                andExpect(MockMvcResultMatchers.status().is(405)).andDo(MockMvcResultHandlers.print()).
                andReturn();
    }

    /**
     *
     * Method: register(@Validated @RequestBody UmsAdminParam umsAdminParam)
     * use word Not Existing in Back-End Management System*
     * e.g. money
     */
    @Test
    public void testRegi() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setEmail("lydiazhang233@163.com");
        umsAdminParam.setIcon("XXX");
        umsAdminParam.setNickName("miu");
        umsAdminParam.setNote("test valid input in register");
        umsAdminParam.setPassword("1234");
        umsAdminParam.setUsername("262P");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/money/register").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam))).
                andExpect(MockMvcResultMatchers.status().is(404)).andDo(MockMvcResultHandlers.print()).
                andReturn();
    }
}
