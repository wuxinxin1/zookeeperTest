package com.example;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2019/4/2/002.
 */
public class ZKServer {

    private ZooKeeper connection;

    public static void main(String[] args) throws Exception {
        ZKServer zkServer = new ZKServer();
        //获取连接
        zkServer.getConnection();

        //注册
        zkServer.register(args[0]);

        Thread.sleep(Long.MAX_VALUE);
    }

    private void register(String s){
        List<String> children = null;
        try {
            String s1 = connection.create("/servers/node", s.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(s1+":"+s+"上线");
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void getConnection() throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper(ZKUtils.getIp()+":2181", 2000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //register();
            }
        });
        this.connection=zooKeeper;
    }
}
