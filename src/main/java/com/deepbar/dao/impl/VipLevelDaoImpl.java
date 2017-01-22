package com.deepbar.dao.impl;

import com.deepbar.dao.VipLevelDao;
import com.deepbar.entity.VipLevel;
import com.deepbar.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Repository
public class VipLevelDaoImpl extends BaseDaoImpl<VipLevel> implements VipLevelDao {
    private static Logger logger = LogManager.getLogger(VipLevelDaoImpl.class);
}
