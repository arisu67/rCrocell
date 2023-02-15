package emu.rcrocell.server;

import emu.rcrocell.game.Account;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

@ChannelHandler.Sharable
public class PacketHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;

        var acc = byteToAccount(in);
        var clientIp = ((InetSocketAddress)ctx.channel().remoteAddress()).getAddress().getHostAddress();

        System.out.println(
                "Server received: "+ in.toString(CharsetUtil.US_ASCII));
        ctx.write(0);
    }

    public static Account byteToAccount(ByteBuf buf) {
        var account = new Account();
        var filter = "[^a-zA-Z0-9]";
        var rawStringClean = buf.toString(StandardCharsets.US_ASCII).split("\u0000");
        account.username = rawStringClean[4].replaceAll(filter, "");
        account.password = rawStringClean[16].replaceAll(filter, "");

        return account;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
