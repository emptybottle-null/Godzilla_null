/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.net;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.CyclicBuffer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.net.ZeroConfSupport;
import org.apache.log4j.spi.LoggingEvent;

public class SocketHubAppender
extends AppenderSkeleton {
    static final int DEFAULT_PORT = 4560;
    private int port = 4560;
    private Vector oosList = new Vector();
    private ServerMonitor serverMonitor = null;
    private boolean locationInfo = false;
    private CyclicBuffer buffer = null;
    private String application;
    private boolean advertiseViaMulticastDNS;
    private ZeroConfSupport zeroConf;
    public static final String ZONE = "_log4j_obj_tcpaccept_appender.local.";
    private ServerSocket serverSocket;

    public SocketHubAppender() {
    }

    public SocketHubAppender(int _port) {
        this.port = _port;
        this.startServer();
    }

    public void activateOptions() {
        if (this.advertiseViaMulticastDNS) {
            this.zeroConf = new ZeroConfSupport(ZONE, this.port, this.getName());
            this.zeroConf.advertise();
        }
        this.startServer();
    }

    public synchronized void close() {
        if (this.closed) {
            return;
        }
        LogLog.debug("closing SocketHubAppender " + this.getName());
        this.closed = true;
        if (this.advertiseViaMulticastDNS) {
            this.zeroConf.unadvertise();
        }
        this.cleanUp();
        LogLog.debug("SocketHubAppender " + this.getName() + " closed");
    }

    public void cleanUp() {
        LogLog.debug("stopping ServerSocket");
        this.serverMonitor.stopMonitor();
        this.serverMonitor = null;
        LogLog.debug("closing client connections");
        while (this.oosList.size() != 0) {
            ObjectOutputStream oos = (ObjectOutputStream)this.oosList.elementAt(0);
            if (oos == null) continue;
            try {
                oos.close();
            } catch (InterruptedIOException e) {
                Thread.currentThread().interrupt();
                LogLog.error("could not close oos.", e);
            } catch (IOException e) {
                LogLog.error("could not close oos.", e);
            }
            this.oosList.removeElementAt(0);
        }
    }

    public void append(LoggingEvent event) {
        if (event != null) {
            if (this.locationInfo) {
                event.getLocationInformation();
            }
            if (this.application != null) {
                event.setProperty("application", this.application);
            }
            event.getNDC();
            event.getThreadName();
            event.getMDCCopy();
            event.getRenderedMessage();
            event.getThrowableStrRep();
            if (this.buffer != null) {
                this.buffer.add(event);
            }
        }
        if (event == null || this.oosList.size() == 0) {
            return;
        }
        for (int streamCount = 0; streamCount < this.oosList.size(); ++streamCount) {
            ObjectOutputStream oos = null;
            try {
                oos = (ObjectOutputStream)this.oosList.elementAt(streamCount);
            } catch (ArrayIndexOutOfBoundsException e) {
                // empty catch block
            }
            if (oos == null) break;
            try {
                oos.writeObject(event);
                oos.flush();
                oos.reset();
                continue;
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                this.oosList.removeElementAt(streamCount);
                LogLog.debug("dropped connection");
                --streamCount;
            }
        }
    }

    public boolean requiresLayout() {
        return false;
    }

    public void setPort(int _port) {
        this.port = _port;
    }

    public void setApplication(String lapp) {
        this.application = lapp;
    }

    public String getApplication() {
        return this.application;
    }

    public int getPort() {
        return this.port;
    }

    public void setBufferSize(int _bufferSize) {
        this.buffer = new CyclicBuffer(_bufferSize);
    }

    public int getBufferSize() {
        if (this.buffer == null) {
            return 0;
        }
        return this.buffer.getMaxSize();
    }

    public void setLocationInfo(boolean _locationInfo) {
        this.locationInfo = _locationInfo;
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public void setAdvertiseViaMulticastDNS(boolean advertiseViaMulticastDNS) {
        this.advertiseViaMulticastDNS = advertiseViaMulticastDNS;
    }

    public boolean isAdvertiseViaMulticastDNS() {
        return this.advertiseViaMulticastDNS;
    }

    private void startServer() {
        this.serverMonitor = new ServerMonitor(this.port, this.oosList);
    }

    protected ServerSocket createServerSocket(int socketPort) throws IOException {
        return new ServerSocket(socketPort);
    }

    private class ServerMonitor
    implements Runnable {
        private int port;
        private Vector oosList;
        private boolean keepRunning;
        private Thread monitorThread;

        public ServerMonitor(int _port, Vector _oosList) {
            this.port = _port;
            this.oosList = _oosList;
            this.keepRunning = true;
            this.monitorThread = new Thread(this);
            this.monitorThread.setDaemon(true);
            this.monitorThread.setName("SocketHubAppender-Monitor-" + this.port);
            this.monitorThread.start();
        }

        public synchronized void stopMonitor() {
            if (this.keepRunning) {
                LogLog.debug("server monitor thread shutting down");
                this.keepRunning = false;
                try {
                    if (SocketHubAppender.this.serverSocket != null) {
                        SocketHubAppender.this.serverSocket.close();
                        SocketHubAppender.this.serverSocket = null;
                    }
                } catch (IOException ioe) {
                    // empty catch block
                }
                try {
                    this.monitorThread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                this.monitorThread = null;
                LogLog.debug("server monitor thread shut down");
            }
        }

        private void sendCachedEvents(ObjectOutputStream stream) throws IOException {
            if (SocketHubAppender.this.buffer != null) {
                for (int i = 0; i < SocketHubAppender.this.buffer.length(); ++i) {
                    stream.writeObject(SocketHubAppender.this.buffer.get(i));
                }
                stream.flush();
                stream.reset();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void run() {
            SocketHubAppender.this.serverSocket = null;
            try {
                SocketHubAppender.this.serverSocket = SocketHubAppender.this.createServerSocket(this.port);
                SocketHubAppender.this.serverSocket.setSoTimeout(1000);
            } catch (Exception e) {
                if (e instanceof InterruptedIOException || e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("exception setting timeout, shutting down server socket.", e);
                this.keepRunning = false;
                return;
            }
            try {
                try {
                    SocketHubAppender.this.serverSocket.setSoTimeout(1000);
                } catch (SocketException e) {
                    LogLog.error("exception setting timeout, shutting down server socket.", e);
                    try {
                        SocketHubAppender.this.serverSocket.close();
                    } catch (InterruptedIOException e2) {
                        Thread.currentThread().interrupt();
                    } catch (IOException e3) {
                        // empty catch block
                    }
                    return;
                }
                while (this.keepRunning) {
                    Socket socket = null;
                    try {
                        socket = SocketHubAppender.this.serverSocket.accept();
                    } catch (InterruptedIOException e) {
                    } catch (SocketException e) {
                        LogLog.error("exception accepting socket, shutting down server socket.", e);
                        this.keepRunning = false;
                    } catch (IOException e) {
                        LogLog.error("exception accepting socket.", e);
                    }
                    if (socket == null) continue;
                    try {
                        InetAddress remoteAddress = socket.getInetAddress();
                        LogLog.debug("accepting connection from " + remoteAddress.getHostName() + " (" + remoteAddress.getHostAddress() + ")");
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        if (SocketHubAppender.this.buffer != null && SocketHubAppender.this.buffer.length() > 0) {
                            this.sendCachedEvents(oos);
                        }
                        this.oosList.addElement(oos);
                    } catch (IOException e) {
                        if (e instanceof InterruptedIOException) {
                            Thread.currentThread().interrupt();
                        }
                        LogLog.error("exception creating output stream on socket.", e);
                    }
                }
            } finally {
                try {
                    SocketHubAppender.this.serverSocket.close();
                } catch (InterruptedIOException e) {
                    Thread.currentThread().interrupt();
                } catch (IOException e) {}
            }
        }
    }
}

