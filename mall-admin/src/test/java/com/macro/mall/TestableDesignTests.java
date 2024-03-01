package com.macro.mall;

import com.macro.mall.mapper.UmsAdminMapper;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.service.impl.UmsAdminServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class TestableDesignTests extends UmsAdminServiceImpl {

    @Test
    public void testValidUpdateLoginTimeByUsername() throws Exception{
        Method insertLoginLog = this.getClass().getSuperclass().
                getDeclaredMethod("updateLoginTimeByUsername",String.class);
        insertLoginLog.setAccessible(true);
        String username="262P";
        Object invoke = insertLoginLog.invoke(this, new Object[]{username});
        Assert.assertEquals(null,invoke);
    }

    @Test
    public void testInvalidUpdateLoginTimeByUsername() throws Exception{
        Method insertLoginLog = this.getClass().getSuperclass().
                getDeclaredMethod("updateLoginTimeByUsername",String.class);
        insertLoginLog.setAccessible(true);
        String username="Assignment 4";
        Object invoke = insertLoginLog.invoke(this, new Object[]{username});
        Assert.assertEquals(null,invoke);
    }

    @Test(expected = Exception.class)
    public void testNullUpdateLoginTimeByUsername() throws Exception{
        Method insertLoginLog = this.getClass().getSuperclass().
                getDeclaredMethod("updateLoginTimeByUsername",String.class);
        insertLoginLog.setAccessible(true);
        String username=null;
        Object invoke = insertLoginLog.invoke(this, new Object[]{username});
    }

    @Test
    public void testValidInsertLoginLog() throws Exception{
        Method insertLoginLog = this.getClass().getSuperclass().
                getDeclaredMethod("insertLoginLog",String.class);
        insertLoginLog.setAccessible(true);
        String username="262P";
        Object invoke = insertLoginLog.invoke(this, new Object[]{username});
        Assert.assertEquals(null,invoke);
    }

    @Test
    public void testInvalidInsertLoginLog() throws Exception{
        Method insertLoginLog = this.getClass().getSuperclass().
                getDeclaredMethod("insertLoginLog",String.class);
        insertLoginLog.setAccessible(true);
        String username="Assignment 4";
        Object invoke = insertLoginLog.invoke(this, new Object[]{username});
        Assert.assertEquals(null,invoke);
    }

    @Test(expected = Exception.class)
    public void testNullInsertLoginLog() throws Exception{
        Method insertLoginLog = this.getClass().getSuperclass().
                getDeclaredMethod("insertLoginLog",String.class);
        insertLoginLog.setAccessible(true);
        String username=null;
        Object invoke = insertLoginLog.invoke(this, new Object[]{username});
        Assert.assertEquals(null,invoke);
    }
}
