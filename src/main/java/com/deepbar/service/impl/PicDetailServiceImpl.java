package com.deepbar.service.impl;

import com.deepbar.entity.PicDetail;
import com.deepbar.service.PicDetailService;
import com.deepbar.framework.service.impl.BaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Service
public class PicDetailServiceImpl extends BaseServiceImpl<PicDetail> implements PicDetailService {
    private static Logger logger = LogManager.getLogger(PicDetailServiceImpl.class);
}
