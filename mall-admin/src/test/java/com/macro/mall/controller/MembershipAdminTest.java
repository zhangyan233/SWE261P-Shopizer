package com.macro.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.dto.UpdateAdminPasswordParam;
import org.junit.Test;
import org.junit.Before;
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
import org.springframework.web.util.NestedServletException;

/** 
* except the common path, first path is admin Tester.
* 
* @author <Yan Zhang>
* @since <pre>2�� 2, 2024</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MembershipAdminTest{

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
    * Two possible input: valid input, invalid input(null)
    */
    @Test
    public void testValidRegister() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setEmail("lydiazhang233@163.com");
        umsAdminParam.setIcon("XXX");
        umsAdminParam.setNickName("miu");
        umsAdminParam.setNote("test valid input in register");
        umsAdminParam.setPassword("1234");
        umsAdminParam.setUsername("262P");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/register").
                contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam))).
                andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).
                andReturn();

    }

    @Test
    public void testInvalidRegister() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/register").
                contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam))).
                andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).
                andReturn();

    }

    /**
    *
    * Method: login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam)
    * Two possible situations: valid input(correct username and password), invalid(incorrect username and password)
    */
    @Test
    public void testValidLogin() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setUsername("262P");
        umsAdminParam.setPassword("1234");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/login").
                contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam))).
                andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).
                andReturn();
    }
    @Test
    public void testInvalidLogin() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setUsername("262P");
        umsAdminParam.setPassword("2222");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/login").
                contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam))).
                andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).
                andReturn();
    }

    /**
    *
    * Method: refreshToken(HttpServletRequest request)
    *
    */
    @Test
    public void testRefreshToken() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/refreshToken").
                        contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: getAdminInfo(Principal principal)
    *
    */
    @Test
    public void testGetAdminInfo() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/info").
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: logout()
    *
    */
    @Test
    public void testLogout() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/logout").
                        contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: list(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum)
    *
    */
    @Test
    public void testList() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/list").contentType(MediaType.APPLICATION_JSON).
                param("keyword","test","pageSize","5","pageNum","2")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: getItem(@PathVariable Long id)
    * Three possible situations: negative id(invalid id),existing id, not existing id
    */
    @Test
    public void testGetExistingItem() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/12").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testGetInvalidItem() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/-1").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testGetDoesntExsitingItem() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/20").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: update(@PathVariable Long id, @RequestBody UmsAdmin admin)
    * Three possible situations: negative id(invalid id), existing id, not existing id
    */
    @Test
    public void testUpdateExistingID() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setEmail("lydiazhang233@163.com");
        umsAdminParam.setIcon("XXX");
        umsAdminParam.setNickName("miu");
        umsAdminParam.setNote("test valid input in register");
        umsAdminParam.setPassword("1234");
        umsAdminParam.setUsername("262P-software test");
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/12").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

    }
    @Test(expected = NestedServletException.class)
    public void testUpdateInvalidID() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setEmail("lydiazhang233@163.com");
        umsAdminParam.setIcon("XXX");
        umsAdminParam.setNickName("miu");
        umsAdminParam.setNote("test valid input in register");
        umsAdminParam.setPassword("1234");
        umsAdminParam.setUsername("262P-software test");
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/-1").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

    }
    @Test(expected = NestedServletException.class)
    public void testUpdateDoesntID() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setEmail("lydiazhang233@163.com");
        umsAdminParam.setIcon("XXX");
        umsAdminParam.setNickName("miu");
        umsAdminParam.setNote("test valid input in register");
        umsAdminParam.setPassword("1234");
        umsAdminParam.setUsername("262P-software test");
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/20").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

    }


    /**
    *
    * Method: updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam)
    * Four possible situations: existing user, not existing user, invalid new password, invalid old password
    */
    @Test
    public void testUpdatePassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-software test");
        updateAdminPasswordParam.setOldPassword("1234");
        updateAdminPasswordParam.setNewPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdatePasswordNoExistingUser() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P");
        updateAdminPasswordParam.setOldPassword("1234");
        updateAdminPasswordParam.setNewPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdatePasswordInvalidOldPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-software test");
        updateAdminPasswordParam.setOldPassword("1234");
        updateAdminPasswordParam.setNewPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdatePasswordInvalidNewPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-software test");
        updateAdminPasswordParam.setOldPassword("1111");


        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: delete(@PathVariable Long id)
    * Two possible situations: existing id, not existing id
    */
    @Test
    public void testDelete() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/delete/13").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testDeleteNoExistingID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/delete/10").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status)
    * Three possible situations: valid input, invalid id, invalid status
    */
    @Test
    public void testUpdateStatus() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateStatus/12").accept(MediaType.APPLICATION_JSON).param("status","0"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test(expected = NestedServletException.class)
    public void testUpdateStatusInvalidID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateStatus/10").accept(MediaType.APPLICATION_JSON).param("status","0"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdateStatusInvalidStatus() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateStatus/12").accept(MediaType.APPLICATION_JSON).param("status","5"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: updateRole(@RequestParam("adminId") Long adminId, @RequestParam("roleIds") List<Long> roleIds)
    * Three possible situations: valid input, invalid admitID, invalid roleID
    */
    @Test
    public void testUpdateRole() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/role/update").accept(MediaType.APPLICATION_JSON).param("adminId","12").param("roleIds","1,5"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdateRoleInvalidAdminID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/role/update").accept(MediaType.APPLICATION_JSON).param("adminId","10").param("roleIds","1,5"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdateRoleInvalidRoleID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/role/update").accept(MediaType.APPLICATION_JSON).param("adminId","12").param("roleIds","3"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: getRoleList(@PathVariable Long adminId)
    * Two possible situations: valid id, invalid id
    */
    @Test
    public void testGetRoleList() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/role/12").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testGetRoleListInvalidID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/role/10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /*--------------------------------------Incorrect Input Set-----------------------------------*/

    /**
     *
     * Method: update password
     * use the word not following the admin pattern
     * e.g. put
     */
    @Test
    public void testPutPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-software test");
        updateAdminPasswordParam.setOldPassword("1234");
        updateAdminPasswordParam.setNewPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/put").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().is(405)).andDo(MockMvcResultHandlers.print()).
                andReturn();
    }


} 
