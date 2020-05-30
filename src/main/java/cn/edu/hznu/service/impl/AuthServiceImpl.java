package cn.edu.hznu.service.impl;

import cn.edu.hznu.dao.IAuthDao;
import cn.edu.hznu.domain.LocalAuth;
import cn.edu.hznu.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wjj on 2020/5/30
 */
@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private IAuthDao authDao;
    @Override
    public LocalAuth login(LocalAuth localAuth) {
        return authDao.login(localAuth);
    }
}
