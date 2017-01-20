package com.deepbar.dao.impl;

import com.deeporder.bm.dao.BillSeqDao;
import com.deeporder.bm.entity.BillSeq;
import com.deeporder.framework.dao.impl.BaseDaoImpl;
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
