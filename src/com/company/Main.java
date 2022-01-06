package com.company;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        // write your code here
        CreateServer();
    }

    public static void CreateServer() {
        try {
            DatagramSocket ds = new DatagramSocket(9090);
            System.out.println("服务端连接已经建立...");
            byte[] buf = new byte[16];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            System.out.println("创建数据包完成...");
            ds.receive(dp);
            System.out.println("已经接收数据包...");
            String data = convertEncoding_ByteArr(dp.getData(), "GBK");
            String host = dp.getAddress().getHostAddress();
            System.out.println(host);
            int port = dp.getPort();
            System.out.println("data:\t" + data + "  host:\t" + host + "  port:\t" + port);
            byte data2[] = "hhh".getBytes(StandardCharsets.UTF_8);
            DatagramPacket sendPacket = new DatagramPacket(data2, data2.length, InetAddress.getByName("192.168.12.66"), port);
            ds.send(sendPacket);
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertEncoding_Str(String src, String srcCharset, String destCharet)
            throws UnsupportedEncodingException {
        byte[] bts = src.getBytes(destCharet);
        return new String(bts, destCharet);
    }

    /**
     * @param src        接受到的数据
     * @param srcCharset 接收到的数据格式
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String convertEncoding_ByteArr(byte[] src, String srcCharset)
            throws UnsupportedEncodingException {
        String s = new String(src, srcCharset);
        System.out.println(s + "====");
        return s;
    }
}
