package com.deepbar.dao.impl;

import com.deeporder.bm.dao.CatalogDao;
import com.deeporder.bm.entity.Catalog;
import com.deeporder.framework.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by RayLiu on 2016/11/1.
 */
@Repository
public class CatalogDaoImpl extends BaseDaoImpl<Catalog> implements CatalogDao {
    private static Logger logger = LogManager.getLogger(CatalogDaoImpl.class);
}
