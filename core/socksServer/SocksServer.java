/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.socksServer;

import core.EasyI18N;
import core.socksServer.SocksRelayInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import util.Log;
import util.functions;

public class SocksServer {
    private ServerSocket serverSocket;

    public void start(InetSocketAddress inetSocketAddress) throws IOException {
        this.serverSocket = new ServerSocket();
        this.serverSocket.bind(inetSocketAddress, 1000);
    }

    public Socket accept() throws Exception {
        return this.serverSocket.accept();
    }

    public static SocksRelayInfo handleSocks(Socket client, int packetMaxSize, int capacity) {
        try {
            byte[] handshakePacker = new byte[404];
            InputStream inputStream = client.getInputStream();
            client.setSoTimeout(3000);
            int readNum = inputStream.read(handshakePacker);
            SocksRelayInfo socksRelayInfo = null;
            try {
                switch (handshakePacker[0]) {
                    case 4: {
                        socksRelayInfo = SocksServer.socks4(client, handshakePacker, packetMaxSize, capacity);
                        break;
                    }
                    case 5: {
                        socksRelayInfo = SocksServer.socks5(client, handshakePacker, packetMaxSize, capacity);
                        break;
                    }
                    default: {
                        Log.log("\u672a\u77e5\u7684Socks\u534f\u8bae\u6807\u5fd7 \u8bfb\u53d6\u957f\u5ea6:%d \u534f\u8bae\u6807\u5fd7:%d", readNum, handshakePacker[0]);
                        client.close();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                socksRelayInfo = null;
            }
            client.setSoTimeout(0);
            return socksRelayInfo;
        } catch (Exception e) {
            return null;
        }
    }

    public void close() throws IOException {
        if (this.serverSocket != null && !this.serverSocket.isClosed()) {
            this.serverSocket.close();
        }
    }

    private static SocksRelayInfo socks5(Socket client, byte[] handshakePacker, int packetMaxSize, int capacity) throws Exception {
        ByteBuffer buff = ByteBuffer.wrap(handshakePacker);
        OutputStream outputStream = client.getOutputStream();
        InputStream inputStream = client.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte version = buff.get();
        if (version == 5) {
            byte[] shortByteArr = new byte[2];
            shortByteArr[1] = buff.get();
            int authMethodNum = ByteBuffer.wrap(shortByteArr).getShort();
            Log.log(String.format(EasyI18N.getI18nString("address:%s port:%s \u8ba4\u8bc1\u65b9\u6cd5\u6570\u91cf: %d"), client.getInetAddress().getHostAddress(), client.getPort(), (short)authMethodNum), new Object[0]);
            byte[] authMethod = new byte[authMethodNum];
            for (int i = 0; i < authMethodNum; ++i) {
                authMethod[i] = buff.get();
            }
            if (Arrays.binarySearch(authMethod, (byte)0) > -1) {
                outputStream.write(new byte[]{version, 0});
                outputStream.flush();
                inputStream.read(handshakePacker);
                buff = ByteBuffer.wrap(handshakePacker);
                SocksRelayInfo socks = new SocksRelayInfo(packetMaxSize, capacity);
                socks.setVersion(buff.get());
                socks.setCommand(buff.get());
                buff.get();
                byte addressType = buff.get();
                byte[] addressByteArr = null;
                byte[] hostName = null;
                switch (addressType) {
                    case 1: {
                        addressByteArr = new byte[4];
                        buff.get(addressByteArr);
                        break;
                    }
                    case 3: {
                        byte[] hostNameLenByteArr = new byte[2];
                        hostNameLenByteArr[1] = buff.get();
                        short hostNameLen = ByteBuffer.wrap(hostNameLenByteArr).getShort();
                        hostName = new byte[hostNameLen];
                        buff.get(hostName);
                        break;
                    }
                    case 4: {
                        addressByteArr = new byte[16];
                        buff.get(addressByteArr);
                        break;
                    }
                    default: {
                        Log.log(String.format(EasyI18N.getI18nString("address:%s port:%s \u4e0d\u652f\u6301\u7684\u5730\u5740\u7c7b\u578b: %d"), client.getInetAddress().getHostAddress(), client.getPort(), addressType), new Object[0]);
                    }
                }
                if (addressByteArr != null) {
                    socks.setDestHost(InetAddress.getByAddress(addressByteArr).getHostAddress());
                } else if (hostName != null) {
                    socks.setDestHost(new String(hostName));
                }
                byte[] portByteArr = new byte[2];
                buff.get(portByteArr);
                socks.setDestPort(ByteBuffer.wrap(portByteArr).getShort());
                if (socks.getCommand() == 1) {
                    byteArrayOutputStream.write(version);
                    byteArrayOutputStream.write(0);
                    byteArrayOutputStream.write(1);
                    byteArrayOutputStream.write(1);
                    byteArrayOutputStream.write(client.getLocalAddress().getAddress());
                    byteArrayOutputStream.write(portByteArr);
                    socks.connectSuccessMessage = byteArrayOutputStream.toByteArray();
                    socks.setClient(client);
                    return socks;
                }
                Log.log(String.format(EasyI18N.getI18nString("address:%s port:%s \u4e0d\u652f\u6301\u7684\u547d\u4ee4 command : %d "), client.getInetAddress().getHostAddress(), client.getPort(), socks.getCommand()), new Object[0]);
            } else {
                Log.log(String.format(EasyI18N.getI18nString("address:%s port:%s \u4e0d\u652f\u6301\u7684\u8ba4\u8bc1\u65b9\u6cd5:%s"), client.getInetAddress().getHostAddress(), client.getPort(), Arrays.toString(authMethod)), new Object[0]);
            }
        } else {
            Log.log(String.format(EasyI18N.getI18nString("address:%s port:%s \u4e0d\u662fsocks5"), client.getInetAddress().getHostAddress(), client.getPort()), new Object[0]);
        }
        return null;
    }

    private static SocksRelayInfo socks4(Socket client, byte[] handshakePacker, int packetMaxSize, int capacity) throws Exception {
        InputStream inputStream = client.getInputStream();
        OutputStream outputStream = client.getOutputStream();
        SocksRelayInfo socks4 = new SocksRelayInfo(packetMaxSize, capacity);
        ByteBuffer buff = ByteBuffer.wrap(handshakePacker);
        socks4.setVersion(buff.get());
        socks4.setCommand(buff.get());
        socks4.setDestPort(buff.getShort());
        byte[] host = new byte[4];
        buff.get(host);
        socks4.setDestHost(InetAddress.getByAddress(host).getHostAddress());
        socks4.setUSERID(functions.readCString(buff));
        if (socks4.getDestHost().startsWith("0.0.0.") && buff.position() + 2 < handshakePacker.length) {
            socks4.setDestHost(functions.readCString(buff));
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(90);
        byteArrayOutputStream.write(ByteBuffer.allocate(2).putShort(socks4.getDestPort()).array());
        byteArrayOutputStream.write(host);
        socks4.connectSuccessMessage = byteArrayOutputStream.toByteArray();
        socks4.setClient(client);
        return socks4;
    }
}

