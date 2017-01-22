package com.deepbar.dao.impl;

import com.deepbar.dao.RechargeRecordDao;
import com.deepbar.entity.RechargeRecord;
import com.deepbar.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Repository
public class RechargeRecordDaoImpl extends BaseDaoImpl<RechargeRecord> implements RechargeRecordDao{
    private static Logger logger = LogManager.getLogger(RechargeRecordDaoImpl.class);
}
