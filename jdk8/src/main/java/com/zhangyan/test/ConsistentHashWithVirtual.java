package com.zhangyan.test;
import java.util.SortedMap;
import java.util.TreeMap;
public class ConsistentHashWithVirtual {
    public static void main(String[] args) {
        //step1 初始化：把服务器节点IP的哈希值对应到哈希环上
        // 定义服务器ip
        String[] tomcatServers = new String[]{"123.111.0.0","123.101.3.1","111.20.35.2","123.98.26.3"};
        SortedMap<Integer,String> hashServerMap = new TreeMap<>();
        // 定义针对每个真实服务器虚拟出来⼏个节点
        int virtaulCount = 3;
        for(String tomcatServer: tomcatServers) {
            // 求出每⼀个ip的hash值，对应到hash环上，存储hash值与ip的对应关系
            int serverHash = Math.abs(tomcatServer.hashCode());
            // 存储hash值与ip的对应关系
            hashServerMap.put(serverHash,tomcatServer);
            // 处理虚拟节点
            for(int i = 0; i < virtaulCount; i++) {
                int virtualHash = Math.abs((tomcatServer + "#" + i).hashCode());
                hashServerMap.put(virtualHash,"----由虚拟节点"+ i + "映射过来的请求："+ tomcatServer);
            }
        }
        //step2 针对客户端IP求出hash值
        // 定义客户端IP
        String[] clients = new String[]
                {"10.78.12.3","113.25.63.1","126.12.3.8"};
        for(String client : clients) {
            int clientHash = Math.abs(client.hashCode());
            //step3 针对客户端,找到能够处理当前客户端请求的服务器（哈希环上顺时针最近）
            // 根据客户端ip的哈希值去找出哪⼀个服务器节点能够处理（）
            SortedMap<Integer, String> integerStringSortedMap =
                    hashServerMap.tailMap(clientHash);
            if(integerStringSortedMap.isEmpty()) {
                // 取哈希环上的顺时针第⼀台服务器
                Integer firstKey = hashServerMap.firstKey();
                System.out.println("==========>>>>客户端：" + client
                        + " 被路由到服务器：" + hashServerMap.get(firstKey));
            }else{
                Integer firstKey = integerStringSortedMap.firstKey();
                System.out.println("==========>>>>客户端：" + client
                        + " 被路由到服务器：" + hashServerMap.get(firstKey));
            }
        }
    }
}