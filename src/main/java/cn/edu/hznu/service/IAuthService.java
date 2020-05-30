package cn.edu.hznu.service;

import cn.edu.hznu.domain.LocalAuth;

/**
 * Created by wjj on 2020/5/30
 */
public interface IAuthService {
    LocalAuth login(LocalAuth localAuth);
}
