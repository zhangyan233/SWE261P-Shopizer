package com.macro.mall;

import com.alibaba.fastjson.JSONObject;
import com.macro.mall.dto.UpdateAdminPasswordParam;
import com.macro.mall.service.impl.UmsAdminServiceImpl;
import com.macro.mall.service.impl.UmsMemberLevelServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MembersipAdminFourTests {

    @Autowired
    UmsAdminServiceImpl umsAdminService;

    /**
     * update password when all inputs are valid*
     * @throws Exception
     */
    @Test
    public void testUpdateEmptyUsername() throws Exception {
        UpdateAdminPasswordParam updateAdminPasswordParam=new UpdateAdminPasswordParam();
        updateAdminPasswordParam.setUsername(null);
        updateAdminPasswordParam.setOldPassword("1234");
        updateAdminPasswordParam.setNewPassword("1111");
        int i = umsAdminService.updatePassword(updateAdminPasswordParam);
        Assert.assertEquals(-1,i);
    }
}
