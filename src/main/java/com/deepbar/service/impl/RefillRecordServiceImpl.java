package com.deepbar.service.impl;

import com.deepbar.entity.RefillRecord;
import com.deepbar.service.RefillRecordService;
import com.deepbar.framework.service.impl.BaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Service
public class RefillRecordServiceImpl extends BaseServiceImpl<RefillRecord> implements RefillRecordService {
    private static Logger logger = LogManager.getLogger(RefillRecordServiceImpl.class);
}
