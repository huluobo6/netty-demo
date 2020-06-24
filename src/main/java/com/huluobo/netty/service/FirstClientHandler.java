package com.huluobo.netty.service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author huluobo
 * @mail hxf_life@126.com
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive (ChannelHandlerContext ctx) {
        System.out.println("客户端发送消息。。。");
        ByteBuf buffer = getByteBuf(ctx);
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf (ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        byte[] bytes = ("你好，服务端：" + new Date()).getBytes(Charset.forName("UTF-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    @Override
    public void channelRead (ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString(Charset.forName("UTF-8")));
    }
}
