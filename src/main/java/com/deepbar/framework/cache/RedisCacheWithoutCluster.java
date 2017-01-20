package com.deepbar.framework.cache;

import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by josh on 15/7/12.
 */
class RedisCacheWithoutCluster extends RedisCache {

    @Override
    public boolean existsCache(String key) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                return jedis.exists(key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
        return false;
    }

    @Override
    public boolean existsHashCache(String key, String field) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                return jedis.hexists(key, field);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
        return false;
    }

    @Override
    public void addCache(String key, String value) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.set(key, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
    }

    @Override
    public void addCache(String key, String field, String value) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.hset(key, field, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
    }

    @Override
    public void addCacheWithExpire(String key, String value, int seconds) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.setex(key, seconds, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
    }

    @Override
    public void addCache(String key, Map<String, Object> map) {
        if (enableCache) {
            if (map != null) {
                Jedis jedis = null;
                try {
                    jedis = pool.getResource();
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
                    jedis.hmset(key, m);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    pool.returnResourceObject(jedis);
                }
            }
        }
    }

    @Override
    public String getCache(String key) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                return jedis.get(key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
        return null;
    }

    @Override
    public String getCache(String key, String field) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                return jedis.hget(key, field);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
        return null;
    }

    @Override
    public List<String> getCache(String key, String... fields) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                return jedis.hmget(key, fields);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
        return null;
    }

    @Override
    public Map<String, String> getHashAllFieldCache(String key) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                return jedis.hgetAll(key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
        return null;
    }

    @Override
    public Set<String> getAllKeys() {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                return jedis.keys("*");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
        return new HashSet<>();
    }

    @Override
    public Long increaseBy(String key, long offset) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                return jedis.incrBy(key, offset);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
        return null;
    }

    @Override
    public Long decreaseBy(String key, long offset) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                return jedis.decrBy(key, offset);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
        return null;
    }

    @Override
    public Long increase(String key) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                return jedis.incr(key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
        return null;
    }

    @Override
    public Long decrease(String key) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                return jedis.decr(key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
        return null;
    }

    @Override
    public void expireKey(String key, int seconds) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.expire(key, seconds);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
    }

    @Override
    public void delCache(String... keys) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.del(keys);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
    }

    @Override
    public void delHashCache(String key, String... fields) {
        if (enableCache) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.hdel(key, fields);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                pool.returnResourceObject(jedis);
            }
        }
    }

    /**
     * 关闭缓存客户端
     */
    @Override
    public void closeCacheClient() {
        if (enableCache) {
            pool.close();
        }
    }
}
