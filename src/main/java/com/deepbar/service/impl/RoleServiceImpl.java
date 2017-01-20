package com.deepbar.service.impl;

import com.deepbar.entity.Role;
import com.deepbar.service.RoleService;
import com.deepbar.framework.service.impl.BaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    private static Logger logger = LogManager.getLogger(RoleServiceImpl.class);
}
