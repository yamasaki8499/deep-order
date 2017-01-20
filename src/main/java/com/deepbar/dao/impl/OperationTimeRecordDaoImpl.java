package com.deepbar.dao.impl;

import com.deeporder.bm.dao.OperationTimeRecordDao;
import com.deeporder.bm.entity.OperationTimeRecord;
import com.deeporder.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Repository
public class OperationTimeRecordDaoImpl extends BaseDaoImpl<OperationTimeRecord> implements OperationTimeRecordDao {
    private static Logger logger = LogManager.getLogger(OperationTimeRecordDaoImpl.class);

}
