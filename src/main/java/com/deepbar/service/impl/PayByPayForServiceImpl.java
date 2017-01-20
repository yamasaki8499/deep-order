package com.deepbar.service.impl;

import com.deepbar.entity.PayByPayFor;
import com.deepbar.service.PayByPayForService;
import com.deepbar.framework.service.impl.BaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Service
public class PayByPayForServiceImpl extends BaseServiceImpl<PayByPayFor> implements PayByPayForService {
    private static Logger logger = LogManager.getLogger(PayByPayForServiceImpl.class);
}
