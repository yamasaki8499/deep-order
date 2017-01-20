package com.deepbar.framework.dao;

import com.deeporder.framework.page.Page;
import com.deeporder.framework.page.PageList;

import java.sql.Connection;
import java.util.List;

/**
 * Created by deeporder on 15/6/17.
 */
public interface BaseDao<T> {

    void save(T t);

    void save(T t, Connection con);

    void update(T t);

    void physicalDelete(Object key);

    void delete(Object key);

    void update(T t, Connection con);

    void execUpdate(String sql, Object... params);

    Connection lock(Object key);

    void unLock(Connection connection);

    int[] batchSave(final List<T> list);

    int[] batchUpdate(final List<T> list);

    int[] batchPhysicalDelete(final List<?> keyList);

    int[] batchDelete(final List<?> keyList);

    T get(Object key);

    T get(Object key, String... propertyNames);

    boolean exists(Object key);

    List<T> getAll();

    PageList<T> getPageAll(Page page);

    PageList<T> getPageAllWithNext(Page page);

    List<T> getListBySql(String sql);

    List<T> getListBySql(String sql, Object... parameters);

    PageList<T> getPageListBySql(String sql, Page page);

    PageList<T> getPageListBySqlWithNext(String sql, Page page);

    PageList<T> getPageListBySql(String sql, Page page, Object... parameters);

    PageList<T> getPageListBySqlWithNext(String sql, Page page, Object... parameters);
}
