/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.httpProxy.server.core;

import com.httpProxy.server.CertPool;
import com.httpProxy.server.core.HttpProxyHandle;
import com.httpProxy.server.request.HttpRequest;
import com.httpProxy.server.response.HttpResponse;
import com.httpProxy.server.response.HttpResponseStatus;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLSocket;

public class HttpProxyServer {
    private int listenPort;
    private int backlog;
    private InetAddress bindAddr;
    private boolean nextSocket = true;
    private CertPool certPool;
    private ServerSocket serverSocket;
    private HttpProxyHandle proxyHandle;

    public HttpProxyServer(int listenPort, int backlog, InetAddress bindAddr, CertPool certPool, HttpProxyHandle proxyHandle) {
        this.listenPort = listenPort;
        this.backlog = backlog;
        this.bindAddr = bindAddr;
        this.certPool = certPool;
        this.proxyHandle = proxyHandle;
    }

    public boolean startup() {
        try {
            this.serverSocket = new ServerSocket(this.listenPort, this.backlog, this.bindAddr);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return this.acceptService(this.serverSocket);
    }

    public void shutdown() {
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handler(Socket socket, HttpRequest httpRequest) throws Exception {
        try {
            if (this.proxyHandle != null) {
                this.proxyHandle.handler(socket, httpRequest);
            } else {
                HttpResponse httpResponse = new HttpResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR, null, "No Input HttpProxyHandle");
                socket.getOutputStream().write(httpResponse.encode());
            }
        } catch (Exception e) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            e.printStackTrace(printStream);
            printStream.flush();
            printStream.close();
            HttpResponse httpResponse = new HttpResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR, null, byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
            socket.getOutputStream().write(httpResponse.encode());
        }
        this.closeSocket(socket);
    }

    private boolean acceptService(ServerSocket sslServerSocket) {
        new Thread(() -> {
            while (this.nextSocket) {
                Socket sslSocket;
                try {
                    sslSocket = sslServerSocket.accept();
                } catch (IOException e) {
                    return;
                }
                new Thread(() -> {
                    try {
                        sslSocket.setSoTimeout(100);
                        Socket client = sslSocket;
                        InputStream inputStream = client.getInputStream();
                        HttpRequest httpRequest = HttpRequest.Decode(inputStream);
                        if (httpRequest.isHttps()) {
                            client.getOutputStream().write(new HttpResponse(new HttpResponseStatus(200, "Connection established")).encode());
                            client = this.certPool.getSslContext(httpRequest.getHost()).getSocketFactory().createSocket(client, client.getInetAddress().getHostAddress(), client.getPort(), true);
                            ((SSLSocket)client).setUseClientMode(false);
                            inputStream = client.getInputStream();
                            httpRequest = HttpRequest.Decode(inputStream, httpRequest);
                        }
                        this.handler(client, httpRequest);
                    } catch (Exception e) {
                        this.closeSocket(sslSocket);
                    }
                    this.closeSocket(sslSocket);
                }).start();
            }
        }).start();
        return true;
    }

    protected void closeSocket(Socket socket) {
        if (socket == null && !socket.isClosed()) {
            return;
        }
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException iOException) {
            // empty catch block
        }
    }

    public HttpProxyHandle getProxyHandle() {
        return this.proxyHandle;
    }

    public void setProxyHandle(HttpProxyHandle proxyHandle) {
        this.proxyHandle = proxyHandle;
    }

    public boolean isNextSocket() {
        return this.nextSocket;
    }

    public void setNextSocket(boolean nextSocket) {
        this.nextSocket = nextSocket;
    }
}

