/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.net;

import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.net.SocketNode;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RootLogger;

public class SocketServer {
    static String GENERIC = "generic";
    static String CONFIG_FILE_EXT = ".lcf";
    static Logger cat = Logger.getLogger(SocketServer.class);
    static SocketServer server;
    static int port;
    Hashtable hierarchyMap;
    LoggerRepository genericHierarchy;
    File dir;

    public static void main(String[] argv) {
        if (argv.length == 3) {
            SocketServer.init(argv[0], argv[1], argv[2]);
        } else {
            SocketServer.usage("Wrong number of arguments.");
        }
        try {
            cat.info("Listening on port " + port);
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                cat.info("Waiting to accept a new client.");
                Socket socket = serverSocket.accept();
                InetAddress inetAddress = socket.getInetAddress();
                cat.info("Connected to client at " + inetAddress);
                LoggerRepository h = (LoggerRepository)SocketServer.server.hierarchyMap.get(inetAddress);
                if (h == null) {
                    h = server.configureHierarchy(inetAddress);
                }
                cat.info("Starting new socket node.");
                new Thread(new SocketNode(socket, h)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    static void usage(String msg) {
        System.err.println(msg);
        System.err.println("Usage: java " + SocketServer.class.getName() + " port configFile directory");
        System.exit(1);
    }

    static void init(String portStr, String configFile, String dirStr) {
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            SocketServer.usage("Could not interpret port number [" + portStr + "].");
        }
        PropertyConfigurator.configure(configFile);
        File dir = new File(dirStr);
        if (!dir.isDirectory()) {
            SocketServer.usage("[" + dirStr + "] is not a directory.");
        }
        server = new SocketServer(dir);
    }

    public SocketServer(File directory) {
        this.dir = directory;
        this.hierarchyMap = new Hashtable(11);
    }

    LoggerRepository configureHierarchy(InetAddress inetAddress) {
        cat.info("Locating configuration file for " + inetAddress);
        String s = inetAddress.toString();
        int i = s.indexOf("/");
        if (i == -1) {
            cat.warn("Could not parse the inetAddress [" + inetAddress + "]. Using default hierarchy.");
            return this.genericHierarchy();
        }
        String key = s.substring(0, i);
        File configFile = new File(this.dir, key + CONFIG_FILE_EXT);
        if (configFile.exists()) {
            Hierarchy h = new Hierarchy(new RootLogger(Level.DEBUG));
            this.hierarchyMap.put(inetAddress, h);
            new PropertyConfigurator().doConfigure(configFile.getAbsolutePath(), (LoggerRepository)h);
            return h;
        }
        cat.warn("Could not find config file [" + configFile + "].");
        return this.genericHierarchy();
    }

    LoggerRepository genericHierarchy() {
        if (this.genericHierarchy == null) {
            File f = new File(this.dir, GENERIC + CONFIG_FILE_EXT);
            if (f.exists()) {
                this.genericHierarchy = new Hierarchy(new RootLogger(Level.DEBUG));
                new PropertyConfigurator().doConfigure(f.getAbsolutePath(), this.genericHierarchy);
            } else {
                cat.warn("Could not find config file [" + f + "]. Will use the default hierarchy.");
                this.genericHierarchy = LogManager.getLoggerRepository();
            }
        }
        return this.genericHierarchy;
    }
}

