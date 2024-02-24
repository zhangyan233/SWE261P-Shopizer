package com.macro.mall;

import com.alibaba.fastjson.JSONObject;
import com.macro.mall.controller.UmsRoleController;
import com.macro.mall.dto.UpdateAdminPasswordParam;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("dev")
public class AssignmentTwoTests {
    @Autowired
    private UmsRoleController umsRoleController;

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void before(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * update password when all inputs are valid*
     * @throws Exception
     */
    @Test
    public void testUpdatePassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-Assignment 1.2");
        updateAdminPasswordParam.setOldPassword("1234");
        updateAdminPasswordParam.setNewPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when new password is empty*
     * @throws Exception
     */
    @Test
    public void testEmptyNewPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-Assignment 1.2");
        updateAdminPasswordParam.setOldPassword("1111");


        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when old password is empty*
     * @throws Exception
     */
    @Test
    public void testEmptyOldPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-Assignment 1.2");
        updateAdminPasswordParam.setNewPassword("1234");


        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when username is empty*
     * @throws Exception
     */
    @Test
    public void testEmptyUsername() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setNewPassword("1234");
        updateAdminPasswordParam.setOldPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when new password and old password are empty*
     * @throws Exception
     */
    @Test
    public void testEmptyOldPasswordAndNewPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-Assignment 1.2");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when old password and username are empty*
     * @throws Exception
     */
    @Test
    public void testEmptyOldPasswordAndUsername() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setNewPassword("1234");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when new password and username are empty*
     * @throws Exception
     */
    @Test
    public void testEmptyNewPasswordAndUsername() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setOldPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when new password, username and old password are empty*
     * @throws Exception
     */
    @Test
    public void testEmptyNewPasswordAndUsernameAndOldPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    /**
     * update password when new password is null*
     * @throws Exception
     */
    @Test
    public void testNullNewPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-Assignment 1.2");
        updateAdminPasswordParam.setOldPassword("1111");
        updateAdminPasswordParam.setOldPassword(null);


        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when old password is null*
     * @throws Exception
     */
    @Test
    public void testNullOldPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-Assignment 1.2");
        updateAdminPasswordParam.setOldPassword(null);
        updateAdminPasswordParam.setNewPassword("1234");


        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when username is null*
     * @throws Exception
     */
    @Test
    public void testNullUsername() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername(null);
        updateAdminPasswordParam.setNewPassword("1234");
        updateAdminPasswordParam.setOldPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when new password and old password are null*
     * @throws Exception
     */
    @Test
    public void testNullOldPasswordAndNewPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-Assignment 1.2");
        updateAdminPasswordParam.setNewPassword(null);
        updateAdminPasswordParam.setOldPassword(null);

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when old password and username are null*
     * @throws Exception
     */
    @Test
    public void testNullOldPasswordAndUsername() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername(null);
        updateAdminPasswordParam.setOldPassword(null);
        updateAdminPasswordParam.setNewPassword("1234");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when new password and username are null*
     * @throws Exception
     */
    @Test
    public void testNullNewPasswordAndUsername() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setOldPassword("1111");
        updateAdminPasswordParam.setUsername(null);
        updateAdminPasswordParam.setNewPassword(null);

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when new password, username and old password are null*
     * @throws Exception
     */
    @Test
    public void testNullNewPasswordAndUsernameAndOldPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setOldPassword(null);
        updateAdminPasswordParam.setUsername(null);
        updateAdminPasswordParam.setNewPassword(null);

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when old password is incorrect, right password is 1111*
     * @throws Exception
     */
    @Test
    public void testIncorrectPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-Assignment 1.2");
        updateAdminPasswordParam.setOldPassword("1234");
        updateAdminPasswordParam.setNewPassword("wrong password");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when user not found, right username is 262P-Assignment 1.2*
     * @throws Exception
     */
    @Test
    public void testUserNotFound() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-Assignment");
        updateAdminPasswordParam.setOldPassword("1111");
        updateAdminPasswordParam.setNewPassword("User not found");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * update password when the url is wrong, right url is /admin/updatePassword*
     * @throws Exception
     */
    @Test
    public void testWrongUrl() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-Assignment 1.2");
        updateAdminPasswordParam.setOldPassword("1111");
        updateAdminPasswordParam.setNewPassword("wrong url");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/Password").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().is(405)).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }



}

