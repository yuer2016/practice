package com.yuer.nio;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    @SneakyThrows
    public static void main(String[] args) {
        //打开事件监听
        Selector selector = Selector.open();
        //打开通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //绑定监听端口，通过socket
        ssc.socket().bind(new InetSocketAddress(9527));
        //设置为非阻塞
        ssc.configureBlocking(false);
        //注册连接事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int keys = selector.select(1000);
            if (keys > 0) { //有新的事件
                for (SelectionKey key : selector.selectedKeys()) {
                    if (key.isAcceptable()) {
                        //获取ServerSocketChannel
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        //获取SocketChannel
                        SocketChannel socketChannel = channel.accept();
                        if (socketChannel == null) {
                            continue;
                        }
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        //定义Buffer
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //获取SocketChannel
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        //将数据读入buffer
                        socketChannel.read(byteBuffer);
                        //将buffer转成byte数组
                        byte[] array = byteBuffer.array();
                        String msg = new String(array).trim();
                        System.out.println("Receive Msg：" + msg);
                        if ("exit".equalsIgnoreCase(msg)) {
                            socketChannel.close();
                            ssc.close();
                            selector.close();
                            System.exit(0);
                        }
                        //获取发送消息用的buffer，wrap方法
                        ByteBuffer out = ByteBuffer.wrap(msg.getBytes());
                        //写入channel
                        socketChannel.write(out);
                    }
                }
                //注意这里要移除键
                selector.selectedKeys().clear();
            }
        }
    }
}
