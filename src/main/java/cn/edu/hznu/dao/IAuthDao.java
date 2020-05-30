package cn.edu.hznu.dao;

import cn.edu.hznu.domain.LocalAuth;

/**
 * Created by wjj on 2020/5/30
 */
public interface IAuthDao {
    LocalAuth login(LocalAuth localAuth);
}
