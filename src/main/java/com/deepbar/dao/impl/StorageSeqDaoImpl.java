package com.deepbar.dao.impl;

import com.deeporder.bm.dao.StorageSeqDao;
import com.deeporder.bm.entity.StorageSeq;
import com.deeporder.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by Ray on 2016/11/9.
 */
@Repository
public class StorageSeqDaoImpl extends BaseDaoImpl<StorageSeq> implements StorageSeqDao {
    private static Logger logger = LogManager.getLogger(StorageSeqDaoImpl.class);
}
