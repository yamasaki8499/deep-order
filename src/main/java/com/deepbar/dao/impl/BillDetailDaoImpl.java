package com.deepbar.dao.impl;

import com.deeporder.bm.dao.BillDetailDao;
import com.deeporder.bm.entity.BillDetail;
import com.deeporder.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 2016/10/26.
 */
@Repository
public class BillDetailDaoImpl extends BaseDaoImpl<BillDetail> implements BillDetailDao {
    private static Logger logger = LogManager.getLogger(BillDetailDaoImpl.class);
}
