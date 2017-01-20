package com.deepbar.dao.impl;

import com.deeporder.bm.dao.UserDao;
import com.deeporder.bm.entity.User;
import com.deeporder.framework.dao.impl.BaseDaoImpl;
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
