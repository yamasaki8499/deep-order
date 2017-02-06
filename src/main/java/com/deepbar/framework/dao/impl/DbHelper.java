package com.deepbar.framework.dao.impl;

import com.deepbar.framework.model.*;
import com.deepbar.framework.orm.ColumnMetaData;
import com.deepbar.framework.orm.FieldMetaData;
import com.deepbar.framework.orm.TableMetaData;
import com.deepbar.framework.security.AbstractCurrentUser;
import com.deepbar.framework.util.ContextUtil;
import com.deepbar.framework.util.PropertyReader;
import com.deepbar.framework.util.StringUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.support.KeyHolder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by josh on 15/6/17.
 * Updated by RayL on 17/2/6
 */
public abstract class DbHelper {

    private static Logger logger = LogManager.getLogger(DbHelper.class);

    private static final Map<Class<?>, TableMetaData> tableMapCache = new ConcurrentHashMap<>();

    private static final String SELECT = " select ";

    private static final String FROM = " from ";

    private static final String SELECT_ALL_FROM = " select * from ";

    private static final String SELECT_COUNT_ALL_FROM = " select count(*) from ";

    static final String INSERT_INTO = "insert into ";

    private static final String UPDATE = "update ";

    private static final String DELETE = " delete ";

    private static final String SET = " set ";

    static final String VALUES = " values(";

    static final String LEFT_BRACKETS = "(";

    static final String RIGHT_BRACKETS = ")";

    static final String COMMA = ",";

    private static final String EQUAL = "=";

    static final String QUESTION = "?";

    protected static final String STAR = " * ";

    private static final String WHERE = " where ";

    private static final String GET_PREFIX = "get";

    private static final String SET_PREFIX = "set";

    private static final String INTEGER_CLASS = "java.lang.Integer";

    private static final String LONG_CLASS = "java.lang.Long";

    private static final String DOUBLE_CLASS = "java.lang.Double";

    private static final String FLOAT_CLASS = "java.lang.Float";

    private static final String STRING_CLASS = "java.lang.String";

    private static final String BYTE_CLASS = "java.lang.Byte";

    private static final String SHORT_CLASS = "java.lang.Short";

    private static final String BIGDECIMAL_CLASS = "java.math.BigDecimal";

    private static final String DATE_CLASS = "java.util.Date";

    private static final String BOOLEAN_CLASS = "java.lang.Boolean";

    static final String DATA_BASE_DIALECT_MYSQL = "mysql";

    static final String DATA_BASE_DIALECT_ORACLE = "oracle";

    static final String DATA_BASE_DIALECT = PropertyReader.get("database.dialect", PropertyReader.JDBC_FILE);

    private static final String SQL_DATE_CLASS = "java.sql.Date";

    private static final String SQL_TIMESTAMP_CLASS = "java.sql.Timestamp";

    static final String DELETED = "deleted";

    static final String CREATE_TIME = "createTime";

    static final String CREATE_USER = "createUser";

    static final String UPDATE_TIME = "updateTime";

    static final String UPDATE_USER = "updateUser";

    private static final String JAVA_TYPE_PREFIX = "java.";

    private static final Map<String, Object> javaBaseType = new HashMap<>();

    static AbstractCurrentUser currentUser = ContextUtil.getBean("currentUser");

    DbHelper() {
        javaBaseType.put("boolean", Boolean.class.getName());
        javaBaseType.put("byte", Byte.class.getName());
        javaBaseType.put("short", Short.class.getName());
        javaBaseType.put("int", Integer.class.getName());
        javaBaseType.put("long", Long.class.getName());
        javaBaseType.put("float", Float.class.getName());
        javaBaseType.put("double", Double.class.getName());
    }

    /**
     * 解析新增数据的insertSql
     *
     * @param tableMetaData
     */
    protected abstract void resolveInsertSql(TableMetaData tableMetaData);

    /**
     * 生成insert sql
     *
     * @param object
     * @return
     */
    protected abstract SqlHelper generateInsertSql(Object object);

    /**
     * 在原有sql上生成待分页的sql
     *
     * @param sql
     * @param startIndex
     * @param pageSize
     * @param sortName
     * @param sortDirect
     * @return
     */
    protected abstract String generatePageSql(String sql, int startIndex, int pageSize, String sortName, String sortDirect);

    /**
     * 生成行锁sql
     *
     * @param clss
     * @return
     */
    protected abstract String generateLockSql(Class clss);

    /**
     * 解析 table 标签
     *
     * @param object pojo对象
     * @return
     */
    TableMetaData resolveTable(Object object) {
        return resolveTable(object.getClass());
    }


    /**
     * 解析 table 标签
     *
     * @param cls pojo 对象的class
     * @return
     */
    TableMetaData resolveTable(Class<?> cls) {
        if (tableMapCache.containsKey(cls)) {
            return tableMapCache.get(cls);
        }

        TableMetaData tableMetaData = null;

        // table 标签
        Table table = cls.getAnnotation(Table.class);

        try {
            Method[] methods = cls.getMethods();

            if (methods == null || methods.length == 0) {
                logger.error(cls.getName() + " has not any methods");
                throw new RuntimeException(cls.getName() + " has not any methods");
            }

            tableMetaData = new TableMetaData();
            if (table != null) {
                tableMetaData.setTableName(table.scheme());
            }
            tableMetaData.setEntityClass(cls);

            List<ColumnMetaData> columnMetaDataList = new ArrayList<>();

            Map<String, ColumnMetaData> columnMetaDataMap = new HashMap<>();

            Map<String, ColumnMetaData> columnMetaDataMap2 = new HashMap<>();

            Map<String, FieldMetaData> fieldMetaDataMap = new HashMap<>();


            for (Method method : methods) {
                try {

                    // 解析table中column的列
                    ColumnMetaData columnMetaData = resolveColumn(cls, method, fieldMetaDataMap);

                    if (columnMetaData != null) {
                        if (columnMetaData.isIdentity()) {
                            tableMetaData.setHasIdentity(true);
                            tableMetaData.setIdentityKeySetMethod(columnMetaData.getColumnSetMethod());
                            tableMetaData.setIdentityKeyGetMethod(columnMetaData.getColumnGetMethod());
                        }
                        if (columnMetaData.isPrimaryKey()) {
                            tableMetaData.setHasPrimaryKey(true);
                            tableMetaData.setPrimaryKeyColumn(columnMetaData.getColumnName());
                            tableMetaData.setPrimaryKeySetMethod(columnMetaData.getColumnSetMethod());
                            tableMetaData.setPrimaryKeyGetMethod(columnMetaData.getColumnGetMethod());
                        }
                        columnMetaDataList.add(columnMetaData);
                        columnMetaDataMap.put(columnMetaData.getPropertyName(), columnMetaData);
                        columnMetaDataMap2.put(columnMetaData.getColumnName(), columnMetaData);
                    }
                } catch (Exception ex) {
                    logger.error(ExceptionUtils.getStackTrace(ex));
                }
            }
            tableMetaData.setColumnMetaDataList(columnMetaDataList);
            tableMetaData.setColumnMetaDataMap(columnMetaDataMap);
            tableMetaData.setColumnMetaDataMap2(columnMetaDataMap2);
            tableMetaData.setFieldMetaDataMapWithNoColumnAnno(fieldMetaDataMap);

            // 解析默认的insertSQl
            resolveInsertSql(tableMetaData);

            // 解析默认的updateSql
            resolveUpdateSql(tableMetaData);

            // 解析默认的deleteSql
            resolveDeleteSql(tableMetaData);

            // 解析默认逻辑删除语句
            resolveLogicDeleteSql(tableMetaData);

            // 解析是否包含BaseModel
            resolveBaseModel(tableMetaData);

            tableMapCache.put(cls, tableMetaData);
        } catch (Exception ex) {
            logger.error(ExceptionUtils.getStackTrace(ex));
        }
        return tableMetaData;
    }


    /**
     * 解析 updateSql update table_name set column1=?,column2=? where primaryKey=?
     *
     * @param tableMetaData
     */
    private void resolveUpdateSql(TableMetaData tableMetaData) {
        if (!tableMetaData.getHasPrimaryKey()) {
            return;
        }

        List<ColumnMetaData> list = tableMetaData.getColumnMetaDataList();

        if (tableMetaData.getHasPrimaryKey()) {
            StringBuilder sb = new StringBuilder();
            SqlHelper sqlHelper = new SqlHelper();
            sb.append(UPDATE).append(tableMetaData.getTableName()).append(SET);
            for (ColumnMetaData columnMetaData : list) {
                if (!columnMetaData.isPrimaryKey()) {
                    sb.append(columnMetaData.getColumnName()).append(EQUAL).append(QUESTION).append(COMMA);
                }
            }
            sb.delete(sb.lastIndexOf(COMMA), sb.length())
                    .append(WHERE).append(tableMetaData.getPrimaryKeyColumn()).append(EQUAL).append(QUESTION);
            sqlHelper.setSql(sb.toString());
            tableMetaData.setDefaultUpdateSql(sqlHelper);
        }
    }

    /**
     * 解析delete语句(物理删除)
     * physicalDelete from table_name where primaryKey=?
     *
     * @param tableMetaData
     */
    private void resolveDeleteSql(TableMetaData tableMetaData) {
        if (!tableMetaData.getHasPrimaryKey()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(DELETE).append(FROM)
                .append(tableMetaData.getTableName())
                .append(WHERE).append(tableMetaData.getPrimaryKeyColumn())
                .append(EQUAL).append(QUESTION);

        SqlHelper sqlHelper = new SqlHelper();
        sqlHelper.setSql(sb.toString());

        tableMetaData.setDefaultDeleteSql(sqlHelper);
    }

    /**
     * 解析逻辑删除语句
     * update table_name set deleted = 0 where primaryKey=?
     *
     * @param tableMetaData
     */
    private void resolveLogicDeleteSql(TableMetaData tableMetaData) {
        if (!tableMetaData.getHasPrimaryKey()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(UPDATE).append(tableMetaData.getTableName())
                .append(SET).append("deleted = 1")
                .append(WHERE).append(tableMetaData.getPrimaryKeyColumn())
                .append(EQUAL).append(QUESTION);

        SqlHelper sqlHelper = new SqlHelper();
        sqlHelper.setSql(sb.toString());

        tableMetaData.setDefaultLogicDeleteSql(sqlHelper);
    }

    /**
     * 生成update Sql  update table_name set column1=?,column2=? where primaryKey=?
     *
     * @param object
     * @return
     */
    SqlHelper generateUpdateSql(Object object) {

        TableMetaData tableMetaData = resolveTable(object);

        if (StringUtil.isBlank(tableMetaData.getTableName())) {
            throw new RuntimeException(tableMetaData.getEntityClass().getName() + " not config with @Table");
        }

        List<ColumnMetaData> list = tableMetaData.getColumnMetaDataList();

        Object primaryKey = null;
        try {
            primaryKey = tableMetaData.getPrimaryKeyGetMethod().invoke(object);
        } catch (Exception ex) {
            logger.error(ExceptionUtils.getStackTrace(ex));
        }

        if (primaryKey == null) {
            logger.error(tableMetaData.getClass().getName() + " primary key is null");
            throw new RuntimeException(tableMetaData.getClass().getName() + " primary key is null");
        }

        // 获取默认的更新数据的sql
        SqlHelper sqlHelper = tableMetaData.getDefaultUpdateSql();

        List<Object> values = new ArrayList<Object>();

        java.util.Date now = new java.util.Date();

        for (ColumnMetaData columnMetaData : list) {
            if (!columnMetaData.isPrimaryKey()) {
                Object columnValue = null;
                try {
                    switch (columnMetaData.getPropertyName()) {
                        case UPDATE_TIME:
                            columnValue = now;
                            columnMetaData.getColumnSetMethod().invoke(object, now);
                            break;
                        case UPDATE_USER:
                            columnValue = currentUser.getCurrentUserName();
                            columnMetaData.getColumnSetMethod().invoke(object, currentUser.getCurrentUserName());
                            break;
                        default:
                            columnValue = columnMetaData.getColumnGetMethod().invoke(object);
                    }
                } catch (Exception ex) {
                    logger.error(ExceptionUtils.getStackTrace(ex));
                }
                values.add(columnValue);
            }
        }
        values.add(primaryKey);
        sqlHelper.setValues(values.toArray());
        return sqlHelper;
    }

    /**
     * 生成的sql为 select * from table where primaryKey=?
     *
     * @param cls
     * @return
     */
    String generateSingleSelectSql(Class cls) {
        StringBuilder sb = new StringBuilder();
        TableMetaData tableMetaData = resolveTable(cls);

        if (StringUtil.isBlank(tableMetaData.getTableName())) {
            throw new RuntimeException(tableMetaData.getEntityClass().getName() + " not config with @Table");
        }

        sb.append(SELECT_ALL_FROM).append(tableMetaData.getTableName())
                .append(WHERE).append(tableMetaData.getPrimaryKeyColumn()).append(EQUAL).append(QUESTION);

        return sb.toString();
    }

    /**
     * 生成的sql为 select * from table
     *
     * @param cls
     * @return
     */
    String generateAllSelectSql(Class cls) {
        StringBuilder sb = new StringBuilder();
        TableMetaData tableMetaData = resolveTable(cls);

        if (StringUtil.isBlank(tableMetaData.getTableName())) {
            throw new RuntimeException(tableMetaData.getEntityClass().getName() + " not config with @Table");
        }

        sb.append(SELECT_ALL_FROM).append(tableMetaData.getTableName());
        return sb.toString();
    }

    /**
     * 生成的sql为 select column1,column2 from table where primaryKey = ?
     *
     * @param cls
     * @param propertyNames
     * @return
     */
    String generateSingleSelectSql(Class cls, String... propertyNames) {
        if (propertyNames == null || propertyNames.length == 0) {
            return generateSingleSelectSql(cls);
        }
        TableMetaData tableMetaData = resolveTable(cls);
        if (StringUtil.isBlank(tableMetaData.getTableName())) {
            throw new RuntimeException(tableMetaData.getEntityClass().getName() + " not config with @Table");
        }

        Map<String, ColumnMetaData> columnMap = tableMetaData.getColumnMetaDataMap();
        StringBuilder sb = new StringBuilder();
        sb.append(SELECT);
        boolean notMatchedAll = true;
        for (String propertyName : propertyNames) {
            if (columnMap.containsKey(propertyName)) {
                notMatchedAll = false;
                sb.append(columnMap.get(propertyName).getColumnName()).append(COMMA);
            }
        }
        if (notMatchedAll) {
            logger.error("no property matched to select");
            throw new RuntimeException("no property matched to select");
        }
        sb.delete(sb.lastIndexOf(COMMA), sb.length())
                .append(FROM).append(tableMetaData.getTableName())
                .append(WHERE).append(tableMetaData.getPrimaryKeyColumn()).append(EQUAL).append(QUESTION);
        return sb.toString();
    }

    /**
     * 生成查询总数的sql
     * 把中间的列截掉，换成 *
     *
     * @param sql
     * @return
     */
    String generateCountSql(String sql) {
        StringBuilder sb = new StringBuilder();
        sb.append(SELECT_COUNT_ALL_FROM).append(LEFT_BRACKETS).append(sql).append(RIGHT_BRACKETS).append(" tmp").append(System.currentTimeMillis());
        return sb.toString();
    }

    /**
     * 主键自增类型，新增数据后把主键值写入对象中
     * 只支持int类型和Long类型
     *
     * @param tableMetaData
     * @param o
     * @param keyHolder
     */
    void resolveIdentityKey(TableMetaData tableMetaData, Object o, KeyHolder keyHolder) {

        switch (tableMetaData.getIdentityKeyGetMethod().getReturnType().getName()) {
            case DbHelper.INTEGER_CLASS:
                try {
                    tableMetaData.getIdentityKeySetMethod().invoke(o, keyHolder.getKey().intValue());
                } catch (Exception ex) {
                    logger.error(ExceptionUtils.getStackTrace(ex));
                }
                break;
            case DbHelper.LONG_CLASS:
                try {
                    tableMetaData.getIdentityKeySetMethod().invoke(o, keyHolder.getKey().longValue());
                } catch (Exception ex) {
                    logger.error(ExceptionUtils.getStackTrace(ex));
                }
                break;
        }
    }

    /**
     * 主键自增类型，新增数据后把主键值写入对象中
     * 只支持int类型和Long类型
     *
     * @param tableMetaData
     * @param o
     * @param identityValue
     */
    void resolveIdentityKey(TableMetaData tableMetaData, Object o, Object identityValue) {

        switch (tableMetaData.getIdentityKeyGetMethod().getReturnType().getName()) {
            case DbHelper.INTEGER_CLASS:
                try {
                    tableMetaData.getIdentityKeySetMethod().invoke(o, Integer.valueOf(StringUtil.getString(identityValue)));
                } catch (Exception ex) {
                    logger.error(ExceptionUtils.getStackTrace(ex));
                }
                break;
            case DbHelper.LONG_CLASS:
                try {
                    tableMetaData.getIdentityKeySetMethod().invoke(o, Long.valueOf(StringUtil.getString(identityValue)));
                } catch (Exception ex) {
                    logger.error(ExceptionUtils.getStackTrace(ex));
                }
                break;
        }
    }

    /**
     * PrepareStatement 设值，和value的类型做匹配
     * 解析insert、update 的参数
     *
     * @param ps
     * @param values
     * @throws SQLException
     */
    void resolvePreparedStatement(PreparedStatement ps, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            Object value = values[i];
            if (value == null) {
                ps.setObject(i + 1, null);
            } else {
                switch (value.getClass().getName()) {
                    case DbHelper.BYTE_CLASS:
                        ps.setByte(i + 1, (Byte) value);
                        break;
                    case DbHelper.SHORT_CLASS:
                        ps.setShort(i + 1, (Short) value);
                        break;
                    case DbHelper.INTEGER_CLASS:
                        ps.setInt(i + 1, (Integer) value);
                        break;
                    case DbHelper.LONG_CLASS:
                        ps.setLong(i + 1, (Long) value);
                        break;
                    case DbHelper.FLOAT_CLASS:
                        ps.setFloat(i + 1, (Float) value);
                        break;
                    case DbHelper.DOUBLE_CLASS:
                        ps.setDouble(i + 1, (Double) value);
                        break;
                    case DbHelper.BIGDECIMAL_CLASS:
                        ps.setBigDecimal(i + 1, (BigDecimal) value);
                        break;
                    case DbHelper.DATE_CLASS:
                        ps.setTimestamp(i + 1, new Timestamp(((java.util.Date) value).getTime()));
                        break;
                    case DbHelper.BOOLEAN_CLASS:
                        ps.setBoolean(i + 1, (Boolean) value);
                        break;
                    case DbHelper.STRING_CLASS:
                        ps.setString(i + 1, (String) value);
                        break;
                }
            }
        }
    }

    /**
     * 查询参数类型解析
     *
     * @param parameters
     * @return
     */
    int[] resolveQueryParameter(Object... parameters) {
        List<Integer> list = new ArrayList<>();
        for (Object param : parameters) {
            if (param == null) {
                list.add(Types.NULL);
            } else {
                switch (param.getClass().getName()) {
                    case DbHelper.BYTE_CLASS:
                        list.add(Types.BIT);
                        break;
                    case DbHelper.SHORT_CLASS:
                        list.add(Types.SMALLINT);
                        break;
                    case DbHelper.INTEGER_CLASS:
                        list.add(Types.INTEGER);
                        break;
                    case DbHelper.LONG_CLASS:
                        list.add(Types.BIGINT);
                        break;
                    case DbHelper.FLOAT_CLASS:
                        list.add(Types.DECIMAL);
                        break;
                    case DbHelper.DOUBLE_CLASS:
                        list.add(Types.DOUBLE);
                        break;
                    case DbHelper.BIGDECIMAL_CLASS:
                        list.add(Types.DECIMAL);
                        break;
                    case DbHelper.DATE_CLASS:
                        list.add(Types.TIMESTAMP);
                        break;
                    case DbHelper.BOOLEAN_CLASS:
                        list.add(Types.BOOLEAN);
                        break;
                    case DbHelper.STRING_CLASS:
                        list.add(Types.VARCHAR);
                        break;
                    default:
                        list.add(Types.VARCHAR);
                }
            }
        }
        int[] ary = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ary[i] = list.get(i);
        }
        return ary;
    }

    /**
     * 把Map转为pojo类，map的key为列名或属性名
     *
     * @param cls 被转换的pojo class
     * @param map 待转换的map
     * @param <T> 返回类型
     * @return
     */
    <T> T resolveMapToBean(Class<T> cls, Map map) {
        T t = null;
        try {
            t = cls.newInstance();

            TableMetaData tableMetaData = resolveTable(cls);

            Map<String, ColumnMetaData> columnMetaDataMap = tableMetaData.getColumnMetaDataMap();

            Map<String, ColumnMetaData> columnMetaDataMap2 = tableMetaData.getColumnMetaDataMap2();

            if (map == null || map.size() == 0) {
                return t;
            }

            Set<Map.Entry> entrySet = map.entrySet();
            Iterator<Map.Entry> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                try {
                    Map.Entry entry = iterator.next();
                    String key = entry.getKey().toString();
                    Object value = entry.getValue();

                    if (value == null) {
                        continue;
                    }

                    String valueType = value.getClass().getName();

                    ColumnMetaData columnMetaData = columnMetaDataMap2.get(key);
                    if (columnMetaData == null) {
                        // 转换为小写判断
                        columnMetaData = columnMetaDataMap2.get(key.toLowerCase());
                    }
                    if (columnMetaData == null) {
                        // 转换为大写判断
                        columnMetaData = columnMetaDataMap2.get(key.toUpperCase());
                    }
                    if (columnMetaData == null) {
                        columnMetaData = columnMetaDataMap.get(key);
                    }

                    // 基础数据类型
                    if (columnMetaData != null) {
                        resolvePropertyMapToBean(t, valueType, value, columnMetaData);
                    } else {
                        if (value.getClass().getName().contains(JAVA_TYPE_PREFIX) || javaBaseType.containsKey(value.getClass().getName())) {
                            Map<String, FieldMetaData> fieldMetaDataMap = tableMetaData.getFieldMetaDataMapWithNoColumnAnno();
                            FieldMetaData fieldMetaData = fieldMetaDataMap.get(key);
                            if (fieldMetaData != null) {
                                if (valueType.equals(fieldMetaData.getFieldType().getName())
                                        || valueType.equals(javaBaseType.get(fieldMetaData.getFieldType().getName()))
                                        || fieldMetaData.getFieldType().getName().equals(javaBaseType.get(valueType))) {
                                    fieldMetaData.getFieldSetMethod().invoke(t, value);
                                } else if (valueType.equals(SQL_DATE_CLASS) && DATE_CLASS.equals(fieldMetaData.getFieldType().getName())) {
                                    java.util.Date date = new java.util.Date(((Date) value).getTime());
                                    fieldMetaData.getFieldSetMethod().invoke(t, date);
                                } else if (valueType.equals(SQL_TIMESTAMP_CLASS) && DATE_CLASS.equals(fieldMetaData.getFieldType().getName())) {
                                    java.util.Date date = new java.util.Date(((Timestamp) value).getTime());
                                    fieldMetaData.getFieldSetMethod().invoke(t, date);
                                } else if (valueType.equals(STRING_CLASS)) {
                                    fieldMetaData.getFieldSetMethod().invoke(t, value.toString());
                                }
                            }
                        } else {
                            if (key.contains("__")) {
                                String[] prefix = key.split("__");
                                Object o = t;
                                for (String aPrefix : prefix) {
                                    if (o == null) {
                                        break;
                                    }
                                    o = resolveObjectMapToBean(o, aPrefix, value);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
        } catch (Exception ex) {
            logger.error(ExceptionUtils.getStackTrace(ex));
        }
        return t;
    }

    private void resolvePropertyMapToBean(Object obj, String valueType, Object value, ColumnMetaData columnMetaData) throws Exception {
        if (valueType.equals(columnMetaData.getPropertyType())
                || valueType.equals(javaBaseType.get(columnMetaData.getPropertyType()))
                || columnMetaData.getPropertyType().equals(javaBaseType.get(valueType))) {
            columnMetaData.getColumnSetMethod().invoke(obj, value);
        } else if (valueType.equals(SQL_DATE_CLASS) && DATE_CLASS.equals(columnMetaData.getPropertyType())) {
            java.util.Date date = new java.util.Date(((Date) value).getTime());
            columnMetaData.getColumnSetMethod().invoke(obj, date);
        } else if (valueType.equals(SQL_TIMESTAMP_CLASS) && DATE_CLASS.equals(columnMetaData.getPropertyType())) {
            java.util.Date date = new java.util.Date(((Timestamp) value).getTime());
            columnMetaData.getColumnSetMethod().invoke(obj, date);
        } else if (columnMetaData.getPropertyType().equals(STRING_CLASS)) {
            columnMetaData.getColumnSetMethod().invoke(obj, value.toString());
        }
    }

    private Object resolveObjectMapToBean(Object obj, String fieldName, Object mapValue) throws Exception {
        if (obj == null) {
            return null;
        }
        TableMetaData tableMetaData = resolveTable(obj);

        Map<String, ColumnMetaData> columnMetaDataMap = tableMetaData.getColumnMetaDataMap();
        Map<String, ColumnMetaData> columnMetaDataMap2 = tableMetaData.getColumnMetaDataMap2();
        Map<String, FieldMetaData> fieldMetaDataMap = tableMetaData.getFieldMetaDataMapWithNoColumnAnno();


        if (!columnMetaDataMap.containsKey(fieldName)
                && !columnMetaDataMap2.containsKey(fieldName)
                && !columnMetaDataMap2.containsKey(fieldName.toLowerCase())
                && !columnMetaDataMap2.containsKey(fieldName.toUpperCase())
                && !fieldMetaDataMap.containsKey(fieldName)) {
            return null;
        }
        ColumnMetaData columnMetaData = columnMetaDataMap.get(fieldName);
        if (columnMetaData == null) {
            columnMetaData = columnMetaDataMap2.get(fieldName);
        }
        if (columnMetaData == null) {
            columnMetaData = columnMetaDataMap2.get(fieldName.toLowerCase());
        }
        if (columnMetaData == null) {
            columnMetaData = columnMetaDataMap2.get(fieldName.toUpperCase());
        }
        if (columnMetaData != null) {
            resolvePropertyMapToBean(obj, mapValue.getClass().getName(), mapValue, columnMetaData);
            return null;
        }

        FieldMetaData fieldMetaData = fieldMetaDataMap.get(fieldName);

        if (fieldMetaData == null) {
            return null;
        }
        Object value = fieldMetaData.getFieldGetMethod().invoke(obj);
        if (value == null) {
            value = fieldMetaData.getFieldType().newInstance();
            fieldMetaData.getFieldSetMethod().invoke(obj, value);
        }
        return value;
    }


    /**
     * 解析column标签
     *
     * @param cls
     * @param method
     * @param fieldMetaDataMap
     * @return
     * @throws Exception
     */

    private ColumnMetaData resolveColumn(Class<?> cls, Method method,
                                         Map<String, FieldMetaData> fieldMetaDataMap) throws Exception {
        // column标签
        Column column = method.getAnnotation(Column.class);

        // 自增长标签
        Identity identity = method.getAnnotation(Identity.class);

        // 自增长标签
        PrimaryKey primaryKey = method.getAnnotation(PrimaryKey.class);

        String name = method.getName();

        if (name.startsWith(GET_PREFIX)) {
            String setName = SET_PREFIX + name.substring(3);
            String fieldName = name.substring(3, 4).toLowerCase() + name.substring(4);
            Class superClass = cls;
            Field field = null;
            while (!superClass.getName().equals(Object.class.getName()) && field == null) {
                try {
                    field = superClass.getDeclaredField(fieldName);
                } catch (Exception ex) {
                } finally {
                    superClass = superClass.getSuperclass();
                }
            }

            if (field == null) {
                return null;
            }

            Method setMethod = cls.getMethod(setName, field.getType());

            if (setMethod == null) {
                return null;
            }

            if (column != null
                    && (field.getType().getName().contains(JAVA_TYPE_PREFIX) || javaBaseType.containsKey(field.getType().getName()))) {
                ColumnMetaData columnMetaData = new ColumnMetaData();
                columnMetaData.setPropertyName(field.getName());
                columnMetaData.setPropertyType(method.getReturnType().getName());
                columnMetaData.setColumnName(column.name());
                columnMetaData.setGmthod(name);
                columnMetaData.setSmthod(setName);
                columnMetaData.setColumnGetMethod(method);
                columnMetaData.setColumnSetMethod(setMethod);
                columnMetaData.setIsIdentity((identity != null));
                columnMetaData.setIsPrimaryKey((primaryKey != null));
                return columnMetaData;
            } else {
                FieldMetaData fieldMetaData = new FieldMetaData();
                fieldMetaData.setFieldName(field.getName());
                fieldMetaData.setFieldType(field.getType());
                fieldMetaData.setFieldGetMethod(method);
                fieldMetaData.setFieldSetMethod(setMethod);
                fieldMetaDataMap.put(field.getName(), fieldMetaData);
                return null;
            }
        }
        return null;
    }


    /**
     * 解析Model是否是BaseModel的子类
     */
    private static void resolveBaseModel(TableMetaData tableMetaData) {
        Class entityClass = tableMetaData.getEntityClass();
        Class superClass = entityClass.getSuperclass();

        if (superClass.equals(BaseModel.class)) {
            tableMetaData.setParentBaseModel(true);
        } else {
            while (!superClass.equals(Object.class)) {
                superClass = superClass.getSuperclass();
                if (superClass.equals(BaseModel.class)) {
                    tableMetaData.setParentBaseModel(true);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
