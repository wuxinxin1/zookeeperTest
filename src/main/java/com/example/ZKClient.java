package com.example;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/2/002.
 */
public class ZKClient {

    private ZooKeeper connection;
    
    public static void main(String[] args) throws Exception {
        ZKClient zkClient = new ZKClient();

        //获得连接
        zkClient.connection();
        //获取上线列表
        zkClient.getOnlineNodeList();

        Thread.sleep(Long.MAX_VALUE);
    }

    private void getOnlineNodeList() throws KeeperException, InterruptedException {
        //获得子节点
        List<String> children = connection.getChildren("/servers", true);

        List<String> ips=new ArrayList<String>();

        for (String s:children
             ) {
            //获得节点数据
            byte[] data = connection.getData("/servers/" + s, false, null);
            ips.add(new String(data));
        }

        System.out.println("可用ip:"+ips);
    }


    public void connection() throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper(ZKUtils.getIp()+":2181", 2000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                try {
                    getOnlineNodeList();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        this.connection=zooKeeper;
    }
    
}
