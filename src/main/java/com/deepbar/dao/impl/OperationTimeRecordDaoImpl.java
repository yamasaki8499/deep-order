package com.deepbar.dao.impl;

import com.deepbar.dao.OperationTimeRecordDao;
import com.deepbar.entity.OperationTimeRecord;
import com.deepbar.framework.dao.impl.BaseDaoImpl;
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
