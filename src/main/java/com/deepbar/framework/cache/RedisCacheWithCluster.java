package com.deepbar.framework.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.*;

/**
 * Created by josh on 15/7/12.
 */
class RedisCacheWithCluster extends RedisCache {

    /**
     * 判断缓存是否存在
     *
     * @param key
     * @return
     */
    @Override
    public boolean existsCache(String key) {
        if (enableCache) {
            return jedisCluster.exists(key);
        }
        return false;
    }

    @Override
    public boolean existsHashCache(String key, String field) {
        if (enableCache) {
            return jedisCluster.hexists(key, field);
        }
        return false;
    }

    /**
     * 添加 string 类型的缓存
     *
     * @param key
     * @param value
     */
    @Override
    public void addCache(String key, String value) {
        if (enableCache) {
            jedisCluster.set(key, value);
        }
    }

    /**
     * 添加field的缓存
     *
     * @param key
     * @param field
     * @param value
     */
    @Override
    public void addCache(String key, String field, String value) {
        if (enableCache) {
            jedisCluster.hset(key, field, value);
        }
    }

    /**
     * 添加缓存并设置有效期
     *
     * @param key
     * @param value
     * @param seconds 单位 (秒)
     */
    @Override
    public void addCacheWithExpire(String key, String value, int seconds) {
        if (enableCache) {
            jedisCluster.setex(key, seconds, value);
        }
    }

    /**
     * 添加缓存 hashMap类型
     *
     * @param key
     * @param map
     */
    @Override
    public void addCache(String key, Map<String, Object> map) {
        if (enableCache) {
            if (map != null) {
                Map<String, String> m = new HashMap<>();
                Set<String> sets = map.keySet();
                Iterator<String> it = sets.iterator();
                while (it.hasNext()) {
                    String k = it.next();
                    Object o = map.get(k);

                    if (o != null) {
                        if (o instanceof String) {
                            m.put(k, (String) o);
                        } else if (!(o instanceof Collection)) {
                            m.put(k, String.valueOf(o));
                        } else {
                            m.put(k, null);
                        }
                    } else {
                        m.put(k, null);
                    }
                }
                jedisCluster.hmset(key, m);
            }
        }
    }

    /**
     * 获取 String 型的key
     *
     * @param key
     * @return
     */
    @Override
    public String getCache(String key) {
        if (enableCache) {
            return jedisCluster.get(key);
        }
        return null;
    }

    /**
     * 根据key名，获取field的值
     *
     * @param key
     * @param field
     * @return
     */
    @Override
    public String getCache(String key, String field) {
        if (enableCache) {
            return jedisCluster.hget(key, field);
        }
        return null;
    }

    /**
     * 根据key名，获取多个field的值
     *
     * @param key
     * @param fields
     * @return
     */
    @Override
    public List<String> getCache(String key, String... fields) {
        if (enableCache) {
            return jedisCluster.hmget(key, fields);
        }
        return null;
    }

    /**
     * 针对hash类型，获取hash类型的所有key的filed和value
     *
     * @param key
     * @return
     */
    @Override
    public Map<String, String> getHashAllFieldCache(String key) {
        if (enableCache) {
            return jedisCluster.hgetAll(key);
        }
        return null;
    }

    /**
     * 获取所有的key
     *
     * @return
     */
    @Override
    public Set<String> getAllKeys() {
        Set<String> keysSet = new HashSet<>();
        if (enableCache) {
            Collection<JedisPool> poolCollection = jedisCluster.getClusterNodes().values();
            Iterator<JedisPool> iterator = poolCollection.iterator();
            while (iterator.hasNext()) {
                JedisPool jedisPool = iterator.next();
                Jedis jedis = null;
                try {
                    jedis = jedisPool.getResource();
                    keysSet.addAll(jedis.keys("*"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    jedisPool.returnResourceObject(jedis);
                }
            }
        }
        return keysSet;
    }

    /**
     * 相加key的值
     *
     * @param key
     * @param offset
     * @return 相加后的值
     */
    @Override
    public Long increaseBy(String key, long offset) {
        if (enableCache) {
            return jedisCluster.incrBy(key, offset);
        }
        return null;
    }

    /**
     * 相减key的值
     *
     * @param key
     * @param offset
     * @return 相减后的值
     */
    @Override
    public Long decreaseBy(String key, long offset) {
        if (enableCache) {
            return jedisCluster.decrBy(key, offset);
        }
        return null;
    }

    /**
     * 递增key的值，用于计数，自动加1
     *
     * @param key
     * @return 递增后的值
     */
    @Override
    public Long increase(String key) {
        if (enableCache) {
            return jedisCluster.incr(key);
        }
        return null;
    }

    /**
     * 递减key的值，用于计数，自动减1
     *
     * @param key
     * @return 递减后的值
     */
    @Override
    public Long decrease(String key) {
        if (enableCache) {
            return jedisCluster.decr(key);
        }
        return null;
    }

    /**
     * 指定key的失效时间
     *
     * @param key
     * @param seconds
     */
    @Override
    public void expireKey(String key, int seconds) {
        if (enableCache) {
            jedisCluster.expire(key, seconds);
        }
    }

    /**
     * 清除缓存
     *
     * @param keys
     */
    @Override
    public void delCache(String... keys) {
        if (enableCache) {
            if (keys != null && keys.length > 0) {
                for (String k : keys) {
                    jedisCluster.del(k);
                }
            }
        }
    }

    /**
     * 清除缓存
     *
     * @param key
     * @param fields hash的field,支持一次删除多个field的值
     */
    @Override
    public void delHashCache(String key, String... fields) {
        if (enableCache) {
            jedisCluster.hdel(key, fields);
        }
    }

    /**
     * 关闭缓存客户端
     */
    @Override
    public void closeCacheClient() {
        if (enableCache) {
            try {
                jedisCluster.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
