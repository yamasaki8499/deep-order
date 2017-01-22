package com.deepbar.dao.impl;

import com.deepbar.dao.PayByPayForDao;
import com.deepbar.entity.PayByPayFor;
import com.deepbar.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Repository
public class PayByPayForDaoImpl extends BaseDaoImpl<PayByPayFor> implements PayByPayForDao {
    private static Logger logger = LogManager.getLogger(PayByPayForDaoImpl.class);
}
