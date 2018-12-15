package com.tuling.redisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 访问redis集群
 * @author 图灵-诸葛老师
 *
 */
public class RedisCluster 
{
    public static void main(String[] args) throws IOException
    {
        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("192.168.0.61", 8001));
        jedisClusterNode.add(new HostAndPort("192.168.0.62", 8002));
        jedisClusterNode.add(new HostAndPort("192.168.0.63", 8003));
        jedisClusterNode.add(new HostAndPort("192.168.0.61", 8004));
        jedisClusterNode.add(new HostAndPort("192.168.0.62", 8005));
        jedisClusterNode.add(new HostAndPort("192.168.0.63", 8006));
        
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(10);
        config.setTestOnBorrow(true);
        //connectionTimeout：指的是连接一个url的连接等待时间
        //soTimeout：指的是连接上一个url，获取response的返回等待时间
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode, 6000, 5000, 10, "zhuge", config);
        System.out.println(jedisCluster.set("student", "zhuge"));
        System.out.println(jedisCluster.set("age", "19"));
        
        System.out.println(jedisCluster.get("student"));
        System.out.println(jedisCluster.get("age"));
        System.out.println(jedisCluster.get("name"));
        
        jedisCluster.close();
    }
}
