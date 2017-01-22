package com.deepbar.dao.impl;

import com.deepbar.dao.LightSeqDao;
import com.deepbar.entity.LightSeq;
import com.deepbar.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Repository
public class LightSeqDaoImpl extends BaseDaoImpl<LightSeq> implements LightSeqDao {
    private static Logger logger = LogManager.getLogger(LightSeqDaoImpl.class);
}
