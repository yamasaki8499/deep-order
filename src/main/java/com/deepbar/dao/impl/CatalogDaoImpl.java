package com.deepbar.dao.impl;

import com.deepbar.dao.CatalogDao;
import com.deepbar.entity.Catalog;
import com.deepbar.framework.dao.impl.BaseDaoImpl;
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
