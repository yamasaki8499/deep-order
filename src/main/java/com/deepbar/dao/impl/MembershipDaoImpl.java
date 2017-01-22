package com.deepbar.dao.impl;

import com.deepbar.dao.MembershipDao;
import com.deepbar.entity.Membership;
import com.deepbar.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Repository
public class MembershipDaoImpl extends BaseDaoImpl<Membership> implements MembershipDao {
    private static Logger logger = LogManager.getLogger(MembershipDaoImpl.class);
}
