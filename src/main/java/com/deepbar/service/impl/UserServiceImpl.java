package com.deepbar.service.impl;

import com.deepbar.entity.User;
import com.deepbar.service.UserService;
import com.deepbar.framework.service.impl.BaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
}
