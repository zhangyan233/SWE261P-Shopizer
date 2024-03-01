package com.macro.mall;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.common.api.ResultCode;
import com.macro.mall.controller.UmsAdminController;
import com.macro.mall.dto.UmsAdminLoginParam;
import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.dto.UpdateAdminPasswordParam;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.UmsAdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoTests {
    //Mocked object
    @Mock
    private UmsAdminService adminServiceMock;

    //Tested object
    @InjectMocks
    private UmsAdminController adminController;

    @Test
    public void testRegister_Success() {
        UmsAdminParam umsAdminParam = new UmsAdminParam();

        when(adminServiceMock.register(umsAdminParam)).thenReturn(new UmsAdmin());

        CommonResult<UmsAdmin> result = adminController.register(umsAdminParam);

        assertNotNull(result);
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertNotNull(result.getData());

    }

    @Test
    public void testRegister_Failed() {
        UmsAdminParam umsAdminParam = new UmsAdminParam();

        when(adminServiceMock.register(umsAdminParam)).thenReturn(null);

        CommonResult<UmsAdmin> result = adminController.register(umsAdminParam);

        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
        assertNull(result.getData());
    }

    @Test
    public void testLogin() {
        UmsAdminLoginParam umsAdminLoginParam = new UmsAdminLoginParam();
        umsAdminLoginParam.setUsername("ktt261p");
        umsAdminLoginParam.setPassword("1234567");


        String mockToken = "mockToken";
        when(adminServiceMock.login("ktt261p", "1234567")).thenReturn(mockToken);


        CommonResult result = adminController.login(umsAdminLoginParam);


        assertNotNull(result);
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertNotNull(result.getData());
        assertTrue(result.getData() instanceof Map);

        Map<String, String> tokenMap = (Map<String, String>) result.getData();
        assertEquals(mockToken, tokenMap.get("token"));

    }

    @Test
    public void testLogout() {
        CommonResult result = adminController.logout();

        assertNotNull(result);
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertNull(result.getData());

    }

    @Test
    public void testList() {
        List<UmsAdmin> adminList = Arrays.asList(new UmsAdmin(), new UmsAdmin());

        when(adminServiceMock.list(anyString(), anyInt(), anyInt())).thenReturn(adminList);

        CommonResult<CommonPage<UmsAdmin>> result = adminController.list("testKeyword", 5, 1);


        assertNotNull(result);
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertNotNull(result.getData());

    }

    @Test
    public void testGetItem() {
        UmsAdmin sampleAdmin = new UmsAdmin();
        sampleAdmin.setId(1L);

        when(adminServiceMock.getItem(1L)).thenReturn(sampleAdmin);


        CommonResult<UmsAdmin> result = adminController.getItem(1L);


        assertNotNull(result);
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertEquals(sampleAdmin, result.getData());

    }

    @Test
    public void testUpdate_Success() {
        UmsAdmin sampleAdmin = new UmsAdmin();
        sampleAdmin.setId(1L);

        when(adminServiceMock.update(eq(1L), any(UmsAdmin.class))).thenReturn(1); // Assuming 1 row updated

        CommonResult result = adminController.update(1L, sampleAdmin);

        assertNotNull(result);
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertEquals(1, result.getData()); // Assuming 1 row updated
    }

    @Test
    public void testUpdate_Failed() {
        UmsAdmin sampleAdmin = new UmsAdmin();
        sampleAdmin.setId(1L);

        when(adminServiceMock.update(eq(1L), any(UmsAdmin.class))).thenReturn(0);

        CommonResult result = adminController.update(1L, sampleAdmin);


        assertNotNull(result);
        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
    }

    @Test
    public void testUpdatePassword_Success() {
        UpdateAdminPasswordParam updatePasswordParam = new UpdateAdminPasswordParam();

        when(adminServiceMock.updatePassword(any(UpdateAdminPasswordParam.class)))
                .thenReturn(1);


        CommonResult result = adminController.updatePassword(updatePasswordParam);

        assertNotNull(result);
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
    }

    @Test
    public void testUpdatePassword_InvalidPara() {
        UpdateAdminPasswordParam updatePasswordParam = new UpdateAdminPasswordParam();

        when(adminServiceMock.updatePassword(any(UpdateAdminPasswordParam.class)))
                .thenReturn(-1);


        CommonResult result = adminController.updatePassword(updatePasswordParam);

        assertNotNull(result);
        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
        assertEquals("提交参数不合法", result.getMessage());
    }

    @Test
    public void testUpdatePassword_UserNotFound() {
        UpdateAdminPasswordParam updatePasswordParam = new UpdateAdminPasswordParam();

        when(adminServiceMock.updatePassword(any(UpdateAdminPasswordParam.class)))
                .thenReturn(-2);


        CommonResult result = adminController.updatePassword(updatePasswordParam);

        assertNotNull(result);
        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
        assertEquals("找不到该用户", result.getMessage());
    }

    @Test
    public void testUpdatePassword_IncorrectOldPassword() {
        UpdateAdminPasswordParam updatePasswordParam = new UpdateAdminPasswordParam();

        when(adminServiceMock.updatePassword(any(UpdateAdminPasswordParam.class)))
                .thenReturn(-3);


        CommonResult result = adminController.updatePassword(updatePasswordParam);

        assertNotNull(result);
        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
        assertEquals("旧密码错误", result.getMessage());
    }

    @Test
    public void testDeleteUser_Success() {
        long userId = 123456L;
        int expectedCount = 1;

        when(adminServiceMock.delete(userId)).thenReturn(expectedCount);

        CommonResult result = adminController.delete(userId);


        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertEquals(expectedCount, result.getData());
    }

    @Test
    public void testDeleteUser_Failed() {
        long userId = 123345L;
        int expectedCount = 0;

        when(adminServiceMock.delete(userId)).thenReturn(expectedCount);

        CommonResult result = adminController.delete(userId);


        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
        assertEquals(null, result.getData());
    }

    @Test
    public void testUpdateStatus_Success() {
        Long id = 1L;
        Integer status = 1;
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);


        when(adminServiceMock.update(eq(id), any(UmsAdmin.class))).thenReturn(1);// Assuming 1 row updated

        CommonResult result = adminController.updateStatus(id, status);


        assertNotNull(result);
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertEquals(1, result.getData());
    }

    @Test
    public void testUpdateStatus_Failed() {
        Long id = 1L;
        Integer status = 1;
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);
        int expectedCount = 0;


        when(adminServiceMock.update(eq(id), any(UmsAdmin.class))).thenReturn(expectedCount);

        CommonResult result = adminController.updateStatus(id, status);


        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
        assertNull(result.getData());
    }

    @Test
    public void testUpdateRole_Success() {
        Long adminId = 1L;
        List<Long> roleIds = Arrays.asList(1L, 2L);

        when(adminServiceMock.updateRole(eq(adminId), eq(roleIds))).thenReturn(2); // Assuming 2 roles were updated

        CommonResult result = adminController.updateRole(adminId, roleIds);


        assertNotNull(result);
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertEquals(2, result.getData());
    }

    @Test
    public void testUpdateRole_Failed() {
        Long adminId = 1L;
        List<Long> roleIds = Arrays.asList(1L, 2L);
        int expectedCount = -1;

        when(adminServiceMock.updateRole(eq(adminId), eq(roleIds))).thenReturn(expectedCount);

        CommonResult result = adminController.updateRole(adminId, roleIds);


        assertEquals(ResultCode.FAILED.getCode(), result.getCode());
        assertNull(result.getData());

    }

    @Test
    public void testGetRoleList() {
        Long adminId = 123L;
        UmsRole role = new UmsRole();
        role.setId(adminId);
        List<UmsRole> expectedRoleList = Arrays.asList(role);


        when(adminServiceMock.getRoleList(adminId)).thenReturn(expectedRoleList);

        CommonResult<List<UmsRole>> result = adminController.getRoleList(adminId);


        assertNotNull(result);
        assertEquals(ResultCode.SUCCESS.getCode(), result.getCode());
        assertEquals(expectedRoleList, result.getData());


    }



}