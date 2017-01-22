package com.deepbar.dao.impl;

import com.deepbar.dao.StorageDetailDao;
import com.deepbar.entity.StorageDetail;
import com.deepbar.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Repository
public class StorageDetailDaoImpl extends BaseDaoImpl<StorageDetail> implements StorageDetailDao {
    private static Logger logger = LogManager.getLogger(StorageDetailDaoImpl.class);
}
