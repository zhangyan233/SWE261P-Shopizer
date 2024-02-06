package com.macro.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.macro.mall.model.UmsRole;
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
* UmsRoleController Tester. 
* 
* @author <Authors name> 
* @since <pre>2�� 2, 2024</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UmsRoleControllerTest {

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
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/create").contentType(MediaType.APPLICATION_JSON).
                content(JSONObject.toJSONString(role))).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testCreateInvalidInput() throws Exception {
        UmsRole role=new UmsRole();
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/create").contentType(MediaType.APPLICATION_JSON).
                content(JSONObject.toJSONString(role))).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
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
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/update/7").contentType(MediaType.APPLICATION_JSON).
                content(JSONObject.toJSONString(role))).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdateInvalidID() throws Exception {
        UmsRole role=new UmsRole();
        role.setName("添加管理员");
        role.setAdminCount(0);
        role.setDescription("查看所有信息");
        role.setStatus(1);
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/update/10").contentType(MediaType.APPLICATION_JSON).
                content(JSONObject.toJSONString(role))).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: delete(@RequestParam("ids") List<Long> ids)
    * Two possible situations: valid id, invalid id
    */
    @Test
    public void testDelete() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/delete").accept(MediaType.APPLICATION_JSON).
                param("ids","6,7")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testDeleteInvalidID() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/delete").accept(MediaType.APPLICATION_JSON).
                param("ids","10")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: listAll()
    *
    */
    @Test
    public void testListAll() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/role/listAll").contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: list(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum)
    *
    */
    @Test
    public void testList() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/role/list").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
    *
    * Method: updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status)
    * Two possible situations: valid id, invalid id
    */
    @Test
    public void testUpdateStatus() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/updateStatus/1").accept(MediaType.APPLICATION_JSON).
                param("status","0")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
    public void testUpdateStatusInvalid() throws Exception {
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/role/updateStatus/10").accept(MediaType.APPLICATION_JSON).
                param("status","0")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
} 
