package com.macro.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.dto.UpdateAdminPasswordParam;
import com.macro.mall.model.UmsRole;
import com.macro.mall.security.util.JwtTokenUtil;
import com.sun.security.auth.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
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

import java.security.Principal;

/**
 * According to coverage, modify formal test
 *
 * @author <Yan Zhang>
 * @since <pre>2�� 16, 2024</pre>
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("dev")
public class MembershipAdminTest2 {
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
        umsAdminParam.setNickName("miumiu");
        umsAdminParam.setNote("modify test");
        umsAdminParam.setPassword("1234");
        umsAdminParam.setUsername("Assignment3.1");

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
     * add a test to test when register a user whose name exists*
     * @throws Exception
     */
    @Test
    public void testExistIdRegister() throws Exception {
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

    /**
     *
     * Method: login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam)
     * Two possible situations: valid input(correct username and password), invalid(incorrect username and password)
     */
    @Test
    public void testValidLogin() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setUsername("Assignment3");
        umsAdminParam.setPassword("1234");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/login").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam))).
                andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).
                andReturn();
    }
    @Test
    public void testInvalidLogin() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setUsername("Assignment3");
        umsAdminParam.setPassword("null");

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
     * add a test to test when exists login information
     */
    @Test
    public void testGetAdminHaveInfo() throws Exception {
        Principal principal=new UserPrincipal("admin");
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/info").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(principal))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void testGetAdminNoInfo() throws Exception {
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
                        param("keyword","","pageSize","5","pageNum","1")).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     *
     * Method: getItem(@PathVariable Long id)
     * Three possible situations: negative id(invalid id),existing id, not existing id
     */
    @Test
    public void testGetExistingItem() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/1").
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
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/100").
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
        umsAdminParam.setNickName("miumiu1234");
        umsAdminParam.setNote("test valid input in register");
        umsAdminParam.setPassword("1234");
        umsAdminParam.setUsername("Assignment3");
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/37").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam)))
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
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/-1").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

    }
    @Test(expected = Exception.class)
    public void testUpdateDoesntID() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setEmail("lydiazhang233@163.com");
        umsAdminParam.setIcon("XXX");
        umsAdminParam.setNickName("miu");
        umsAdminParam.setNote("test valid input in register");
        umsAdminParam.setPassword("1234");
        umsAdminParam.setUsername("262P-software test");
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/100").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

    }

    /**
     * add a test to test new password is the same as the old password*
     * @throws Exception
     */
    @Test
    public void testUpdateSameUser() throws Exception {
        UmsAdminParam umsAdminParam=new UmsAdminParam();
        umsAdminParam.setEmail("lydiazhang233@163.com");
        umsAdminParam.setIcon("XXX");
        umsAdminParam.setNickName("miu");
        umsAdminParam.setNote("test valid input in register");
        umsAdminParam.setPassword("1234");
        umsAdminParam.setUsername("Assignment3");
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/37").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(umsAdminParam)))
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
        updateAdminPasswordParam.setUsername("Assignment3");
        updateAdminPasswordParam.setOldPassword("1234");
        updateAdminPasswordParam.setNewPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdatePasswordNoExistingUser() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-software");
        updateAdminPasswordParam.setOldPassword("1234");
        updateAdminPasswordParam.setNewPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdatePasswordInvalidOldPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P");
        updateAdminPasswordParam.setOldPassword("2222");
        updateAdminPasswordParam.setNewPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdatePasswordInvalidNewPassword() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername("262P-software test");
        updateAdminPasswordParam.setOldPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * add a test to test when input is empty*
     * @throws Exception
     */
    @Test
    public void testUpdatePasswordEmptyInput() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     *
     * Method: delete(@PathVariable Long id)
     * Two possible situations: existing id, not existing id
     */
    @Test
    public void testDelete() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/delete/5").
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
     * Three possible situations: valid input, invalid id, invalid status(only 0,1 valid status)
     */
    @Test
    public void testUpdateStatus() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateStatus/1").
                        accept(MediaType.APPLICATION_JSON).param("status","0"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test(expected = NestedServletException.class)
    public void testUpdateStatusInvalidID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateStatus/100").
                        accept(MediaType.APPLICATION_JSON).param("status","0"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdateStatusInvalidStatus() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateStatus/1").
                        accept(MediaType.APPLICATION_JSON).param("status","11"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * add a test to test when new status is the same as the old*
     * @throws Exception
     */
    @Test
    public void testUpdateStatusTheSameAsTheOld() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateStatus/3").
                        accept(MediaType.APPLICATION_JSON).param("status","1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     *
     * Method: updateRole(@RequestParam("adminId") Long adminId, @RequestParam("roleIds") List<Long> roleIds)
     * Three possible situations: valid input, invalid admitID, invalid roleID
     */
    @Test
    public void testUpdateRole() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/role/update").
                        accept(MediaType.APPLICATION_JSON).param("adminId","1").param("roleIds","1,5"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdateRoleInvalidAdminID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/role/update").
                        accept(MediaType.APPLICATION_JSON).param("adminId","100").param("roleIds","1,5"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdateRoleInvalidRoleID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/role/update").
                        accept(MediaType.APPLICATION_JSON).param("adminId","1").param("roleIds","11"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * add a test to test when no roleId*
     * @throws Exception
     */
    @Test
    public void testUpdateRoleNoRoleID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/role/update").
                        accept(MediaType.APPLICATION_JSON).param("adminId","1").
                        param("roleIds","")).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }



    /**
     *
     * Method: getRoleList(@PathVariable Long adminId)
     * Two possible situations: valid id, invalid id
     */
    @Test
    public void testGetRoleList() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/role/1").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testGetRoleListInvalidID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/admin/role/10").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }


    /*--------------------------------------test other controllers-----------------------------------*/

    /**
     *
     * Method: create(@RequestBody UmsRole role)
     * Two possible situations: valid input, invalid input(null)
     */
    @Test
    public void testCreate() throws Exception {
        UmsRole role=new UmsRole();
        role.setId(3L);
        role.setName("管理员");
        role.setAdminCount(0);
        role.setDescription("查看所有信息");
        role.setStatus(1);
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/create").
                contentType(MediaType.APPLICATION_JSON).
                content(JSONObject.toJSONString(role))).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testCreateInvalidInput() throws Exception {
        UmsRole role=new UmsRole();
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/create").
                contentType(MediaType.APPLICATION_JSON).
                content(JSONObject.toJSONString(role))).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     *
     * Method: update(@PathVariable Long id, @RequestBody UmsRole role)
     * Two possible situations: valid roleID, invalid roleId
     */
    @Test
    public void testUpdate() throws Exception {
        UmsRole role=new UmsRole();
        role.setName("添加管理员");
        role.setAdminCount(0);
        role.setDescription("查看所有信息");
        role.setStatus(1);
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/update/7").
                contentType(MediaType.APPLICATION_JSON).
                content(JSONObject.toJSONString(role))).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdateInvalidRoleID() throws Exception {
        UmsRole role=new UmsRole();
        role.setName("添加管理员");
        role.setAdminCount(0);
        role.setDescription("查看所有信息");
        role.setStatus(1);
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/update/10").
                contentType(MediaType.APPLICATION_JSON).
                content(JSONObject.toJSONString(role))).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     *
     * Method: delete(@RequestParam("ids") List<Long> ids)
     * Two possible situations: valid id, invalid id
     */
    @Test
    public void testDeleteRoleID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/delete").
                accept(MediaType.APPLICATION_JSON).
                param("ids","6,7")).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testDeleteInvalidID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/delete").
                accept(MediaType.APPLICATION_JSON).
                param("ids","10")).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     *
     * Method: listAll()
     *
     */
    @Test
    public void testListAll() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/role/listAll").
                contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     *
     * Method: list(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum)
     *
     */
    @Test
    public void testListRoleID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/role/list").
                contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     *
     * Method: updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status)
     * Two possible situations: valid id, invalid id
     */
    @Test
    public void testUpdateRoleIDStatus() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/updateStatus/1").
                accept(MediaType.APPLICATION_JSON).
                param("status","0")).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdateStatusInvalid() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/updateStatus/10").
                accept(MediaType.APPLICATION_JSON).
                param("status","0")).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     *
     * Method: list(@RequestParam("defaultStatus") Integer defaultStatus)
     * Two possible situations: valid memberLevel, invalid memberLevel
     */
    @Test
    public void testListMemberLevel() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/memberLevel/list").
                accept(MediaType.APPLICATION_JSON).
                param("defaultStatus","1")).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void testListInvalidMemberLevel() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/memberLevel/list").
                accept(MediaType.APPLICATION_JSON).
                param("defaultStatus","7")).andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print()).andReturn();
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
        updateAdminPasswordParam.setUsername("262P");
        updateAdminPasswordParam.setOldPassword("1234");
        updateAdminPasswordParam.setNewPassword("1111");

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/admin/put").
                        contentType(MediaType.APPLICATION_JSON).
                        content(JSONObject.toJSONString(updateAdminPasswordParam)))
                .andExpect(MockMvcResultMatchers.status().is(405)).andDo(MockMvcResultHandlers.print()).
                andReturn();
    }


}
