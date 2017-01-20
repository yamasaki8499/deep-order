package com.deepbar.service;

import com.deepbar.entity.OperationTimeRecord;
import com.deepbar.framework.service.BaseService;
import com.deepbar.framework.web.response.ResponseWrapper;

/**
 * Created by RayLiu on 2016/11/9.
 */
public interface OperationTimeRecordService extends BaseService<OperationTimeRecord> {
    ResponseWrapper save(String date, String option);
}
