package cn.edu.hznu.dao;

import cn.edu.hznu.BaseTest;
import cn.edu.hznu.domain.LocalAuth;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wjj on 2020/5/30
 */
public class AuthTest extends BaseTest {
    @Autowired
    private IAuthDao dao;

    @Test
    public void Testlogin(){
        LocalAuth localAuth = new LocalAuth();
        localAuth.setUsername("wjj");
        localAuth.setPassword("199945");
        LocalAuth login = dao.login(localAuth);
        System.out.println(login );
    }
}
