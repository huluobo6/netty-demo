package com.huluobo.netty.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author huluobo
 * @mail hxf_life@126.com
 */
public class NettyServer {
    public static void main(String[] args) {
        /** bossGroup的作用是监听客户端请求 **/
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        /** workerGroup的作用是处理每条连接的数据读写 **/
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        /** 引导类，其对象的作用是引导服务器的启动工作 **/
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        /** .group是配置上面两个线程组的角色，也就是谁去监听谁去处理读写。只是创建了两个线程组，并没有实际使用。 **/
        /** .channel是配置服务端的IO模型，代码配置的是NIO模型。也可以配置为BIO，如OioServerSocketChannel.class。 **/
        serverBootstrap.group(boosGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel channel) {
                channel.pipeline().addLast(new FirstServerHandler());
            }
        });

        serverBootstrap.bind(8080);

    }
}
