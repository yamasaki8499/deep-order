package com.deepbar.service.impl;

import com.deepbar.entity.Promotion;
import com.deepbar.service.PromotionService;
import com.deepbar.framework.service.impl.BaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Service
public class PromotionServiceImpl extends BaseServiceImpl<Promotion> implements PromotionService {
    private static Logger logger = LogManager.getLogger(PromotionServiceImpl.class);
}
