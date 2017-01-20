package com.deepbar.service.impl;

import com.deepbar.entity.StorageSeq;
import com.deepbar.service.StorageSeqService;
import com.deepbar.framework.service.impl.BaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by Ray on 2016/11/9.
 */
@Service
public class StorageSeqServiceImpl extends BaseServiceImpl<StorageSeq> implements StorageSeqService {
    private static Logger logger = LogManager.getLogger(StorageSeqServiceImpl.class);
}
