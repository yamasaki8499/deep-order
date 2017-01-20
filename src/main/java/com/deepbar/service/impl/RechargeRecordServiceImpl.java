package com.deepbar.service.impl;

import com.deepbar.entity.RechargeRecord;
import com.deepbar.service.RechargeRecordService;
import com.deepbar.framework.service.impl.BaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Service
public class RechargeRecordServiceImpl extends BaseServiceImpl<RechargeRecord> implements RechargeRecordService {
    private static Logger logger = LogManager.getLogger(RechargeRecordServiceImpl.class);
}
