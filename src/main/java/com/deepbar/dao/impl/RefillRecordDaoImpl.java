package com.deepbar.dao.impl;

import com.deeporder.bm.dao.RefillRecordDao;
import com.deeporder.bm.entity.RefillRecord;
import com.deeporder.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Repository
public class RefillRecordDaoImpl extends BaseDaoImpl<RefillRecord> implements RefillRecordDao {
    private static Logger logger = LogManager.getLogger(RefillRecordDaoImpl.class);

}
