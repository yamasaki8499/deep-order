package com.deepbar.framework.cache;

import com.deepbar.framework.util.PropertyReader;
import com.deepbar.framework.util.StringUtil;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * Created by josh on 15/7/7.
 */
public abstract class RedisCache {
    private static final String hosts = PropertyReader.get("cache.redis.host", PropertyReader.CACHE_FILE);

    private static final boolean enableCluster = "1".equals(PropertyReader.get("cache.redis.enableCluster", PropertyReader.CACHE_FILE));

    public static final boolean enableCache = "1".equals(PropertyReader.get("cache.enable", PropertyReader.CACHE_FILE));

    protected static final int maxTotal = Integer.valueOf(PropertyReader.get("cache.redis.pool.maxTotal", PropertyReader.CACHE_FILE));

    protected static final int maxIdle = Integer.valueOf(PropertyReader.get("cache.redis.pool.maxIdle", PropertyReader.CACHE_FILE));

    protected static final int maxWaitMillis = Integer.valueOf(PropertyReader.get("cache.redis.pool.maxWaitMillis", PropertyReader.CACHE_FILE));

    protected static final int maxRedirections = Integer.valueOf(PropertyReader.get("cache.redis.pool.maxRedirections", PropertyReader.CACHE_FILE));

    protected static JedisPool pool = null;

    protected static JedisCluster jedisCluster = null;

    protected static RedisCache redisCache = new RedisCacheWithCluster();

    // 初始化redis的host及端口
    static {
        if (enableCache) {

            // 是否支持启用集群
            if (enableCluster) {
                if (StringUtil.isNotBlank(hosts)) {
                    String[] hostArray = hosts.split(StringUtil.COMMA);
                    Set<HostAndPort> jedisClusterNodes = new HashSet<>();

                    JedisPoolConfig config = new JedisPoolConfig();

                    // 设置jedis的最大实例数
                    config.setMaxTotal(maxTotal);

                    // 设置最大空闲数
                    config.setMaxIdle(maxIdle);

                    // 设置获取jedis实例超时时间
                    config.setMaxWaitMillis(maxWaitMillis);

                    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
                    config.setTestOnBorrow(true);

                    for (String hostAndPort : hostArray) {
                        String[] hp = hostAndPort.split(StringUtil.COLON);
                        String host = hp[0];
                        int port = Integer.valueOf(hp[1]);
                        jedisClusterNodes.add(new HostAndPort(host, port));
                        jedisCluster = new JedisCluster(jedisClusterNodes, maxWaitMillis, maxRedirections, config);
                    }
                }
                redisCache = new RedisCacheWithCluster();

            } else {
                String[] hostArray = hosts.split(StringUtil.COMMA);

                String[] hp = hostArray[0].split(StringUtil.COLON);

                JedisPoolConfig config = new JedisPoolConfig();

                // 设置jedis的最大实例数
                config.setMaxTotal(maxTotal);

                // 设置最大空闲数
                config.setMaxIdle(maxIdle);

                // 设置获取jedis实例超时时间
                config.setMaxWaitMillis(maxWaitMillis);

                //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
                config.setTestOnBorrow(true);

                // 实例化连接池
                pool = new JedisPool(config, hp[0], Integer.valueOf(hp[1]));

                redisCache = new RedisCacheWithoutCluster();
            }
        }
    }

    /**
     * 获取 redis 实例
     *
     * @return
     */
    public static RedisCache getInstance() {
        return redisCache;
    }


    /**
     * 判断缓存是否存在
     *
     * @param key
     * @return
     */
    public abstract boolean existsCache(String key);

    /**
     * 判断hash缓存是否存在
     *
     * @param key
     * @param field
     * @return
     */
    public abstract boolean existsHashCache(String key, String field);

    /**
     * 添加 string 类型的缓存
     *
     * @param key
     * @param value
     */
    public abstract void addCache(String key, String value);

    /**
     * 添加field的缓存
     *
     * @param key
     * @param field
     * @param value
     */
    public abstract void addCache(String key, String field, String value);

    /**
     * 添加缓存并设置有效期
     *
     * @param key
     * @param value
     * @param seconds 单位 (秒)
     */
    public abstract void addCacheWithExpire(String key, String value, int seconds);

    /**
     * 添加缓存 hashMap类型
     *
     * @param key
     * @param map
     */
    public abstract void addCache(String key, Map<String, Object> map);

    /**
     * 获取 String 型的key
     *
     * @param key
     * @return
     */
    public abstract String getCache(String key);

    /**
     * 根据key名，获取field的值
     *
     * @param key
     * @param field
     * @return
     */
    public abstract String getCache(String key, String field);

    /**
     * 根据key名，获取多个field的值
     *
     * @param key
     * @param fields
     * @return
     */
    public abstract List<String> getCache(String key, String... fields);

    /**
     * 针对hash类型，获取hash类型的所有key的filed和value
     *
     * @param key
     * @return
     */
    public abstract Map<String, String> getHashAllFieldCache(String key);

    /**
     * 获取所有的key
     *
     * @return
     */
    public abstract Set<String> getAllKeys();

    /**
     * 相加key的值
     *
     * @param key
     * @param offset
     * @return 相加后的值
     */
    public abstract Long increaseBy(String key, long offset);

    /**
     * 相减key的值
     *
     * @param key
     * @param offset
     * @return 相减后的值
     */
    public abstract Long decreaseBy(String key, long offset);

    /**
     * 递增key的值，用于计数，自动加1
     *
     * @param key
     * @return 递增后的值
     */
    public abstract Long increase(String key);

    /**
     * 递减key的值，用于计数，自动减1
     *
     * @param key
     * @return 递减后的值
     */
    public abstract Long decrease(String key);

    /**
     * 指定key的失效时间
     *
     * @param key
     * @param seconds
     */
    public abstract void expireKey(String key, int seconds);

    /**
     * 清除缓存
     *
     * @param keys
     */
    public abstract void delCache(String... keys);

    /**
     * 清除缓存
     *
     * @param key
     * @param fields hash的field,支持一次删除多个field的值
     */
    public abstract void delHashCache(String key, String... fields);

    /**
     * 关闭缓存客户端
     */
    public abstract void closeCacheClient();

    public static void main(String[] args) {
        RedisCache redisCache = RedisCache.getInstance();
        Set<String> keys = redisCache.getAllKeys();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key);
            System.out.println(redisCache.getHashAllFieldCache(key));
            System.out.println();
        }
    }
}

