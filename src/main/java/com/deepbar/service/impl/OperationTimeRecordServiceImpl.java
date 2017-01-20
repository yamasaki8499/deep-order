package com.deepbar.service.impl;

import com.deepbar.dao.OperationTimeRecordDao;
import com.deepbar.entity.OperationTimeRecord;
import com.deepbar.service.OperationTimeRecordService;
import com.deepbar.framework.service.impl.BaseServiceImpl;
import com.deepbar.framework.web.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by RayLiu on 2016/11/9.
 */
@Service
public class OperationTimeRecordServiceImpl extends BaseServiceImpl<OperationTimeRecord> implements OperationTimeRecordService {

    @Autowired
    private OperationTimeRecordDao operationTimeRecordDao;

    @Override
    public ResponseWrapper save(String date, String option) {


        System.out.println("a");
        return null;
    }
}
