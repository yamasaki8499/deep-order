package com.deepbar.dao.impl;

import com.deepbar.dao.UserDao;
import com.deepbar.entity.User;
import com.deepbar.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);
}
