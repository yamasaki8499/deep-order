package com.deepbar.service.impl;

import com.deepbar.entity.StorageDetail;
import com.deepbar.service.StorageDetailService;
import com.deepbar.framework.service.impl.BaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Service
public class StorageDetailServiceImpl extends BaseServiceImpl<StorageDetail> implements StorageDetailService {
    private static Logger logger = LogManager.getLogger(StorageDetailServiceImpl.class);
}
