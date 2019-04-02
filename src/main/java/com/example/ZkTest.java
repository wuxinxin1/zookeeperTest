package com.example;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2019/4/2/002.
 */
public class ZkTest {

    private  ZooKeeper zooKeeper;

    @Before
    public void createClient() throws IOException, InterruptedException {
        //创建zookeeper客户端
         zooKeeper = new ZooKeeper("139.10.33.130:2181", 2000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //System.out.println("hello");
            }
        });
    }

    /**
     * 获取节点的子节点,相当于ls 命令
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void ls() throws KeeperException, InterruptedException {
        String  path="/aa";
        List<String> zooKeeperChildren = zooKeeper.getChildren(path, false);
        System.out.println("=========子节点个数"+zooKeeperChildren .size()+"==========");
        for (String znode :
                zooKeeperChildren ) {
            System.out.println(znode);
        }
        System.out.println("=========子节点个数"+zooKeeperChildren .size()+"==========");
    }

    /**
     * 创建节点相当于 create path data acl
     *
     * CreateMode 指定创建节点的类型
     *
     * PERSISTENT(0, false, false),   持久的
       PERSISTENT_SEQUENTIAL(2, false, true), 持久带序列号的
       EPHEMERAL(1, true, false), 临时的
       EPHEMERAL_SEQUENTIAL(3, true, true); 临时带序列号的
     */
    @Test
    public void create() throws KeeperException, InterruptedException {
        //持久的
        /*String path="/wuxinxin";
        String data="wuxinxindata";
        String s = zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(s);*/

        //持久带序列号的
        /*String path="/liujing";
        String data="wuxinxindata";
        String s = zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println(s);*/

        //临时的，关闭客户端，那么节点数据将会消失
        /*String path="/liujing11";
        String data="wuxinxindata";
        String s = zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(s);*/

        //临时的带序列号的，关闭客户端，那么节点数据将会消失
        String path="/liujing";
        String data="wuxinxindata";
        String s = zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(s);

    }

    /**
     * 删除节点
     * delete path version
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void delete() throws KeeperException, InterruptedException {
        String path="/aa/aaa0000000001";
        zooKeeper.delete(path,1);
    }

    @Test public void getAcl() throws KeeperException, InterruptedException {
        String path="/";

        List<ACL> acl = zooKeeper.getACL(path, new Stat());

        System.out.println(acl);

    }

    @After
    public void closeClient() throws InterruptedException {
        Thread.sleep(30000);
        zooKeeper.close();
    }

}
