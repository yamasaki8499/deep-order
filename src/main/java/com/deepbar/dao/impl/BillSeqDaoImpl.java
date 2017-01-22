package com.deepbar.dao.impl;

import com.deepbar.dao.BillSeqDao;
import com.deepbar.entity.BillSeq;
import com.deepbar.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by RayLiu on 2016/11/1.
 */
@Repository
public class BillSeqDaoImpl extends BaseDaoImpl<BillSeq> implements BillSeqDao{
    private static Logger logger = LogManager.getLogger(BillSeqDaoImpl.class);
}
