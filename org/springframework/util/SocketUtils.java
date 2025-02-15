/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.net.ServerSocketFactory;
import org.springframework.util.Assert;

public class SocketUtils {
    public static final int PORT_RANGE_MIN = 1024;
    public static final int PORT_RANGE_MAX = 65535;
    private static final Random random = new Random(System.nanoTime());

    public static int findAvailableTcpPort() {
        return SocketUtils.findAvailableTcpPort(1024);
    }

    public static int findAvailableTcpPort(int minPort) {
        return SocketUtils.findAvailableTcpPort(minPort, 65535);
    }

    public static int findAvailableTcpPort(int minPort, int maxPort) {
        return SocketType.TCP.findAvailablePort(minPort, maxPort);
    }

    public static SortedSet<Integer> findAvailableTcpPorts(int numRequested) {
        return SocketUtils.findAvailableTcpPorts(numRequested, 1024, 65535);
    }

    public static SortedSet<Integer> findAvailableTcpPorts(int numRequested, int minPort, int maxPort) {
        return SocketType.TCP.findAvailablePorts(numRequested, minPort, maxPort);
    }

    public static int findAvailableUdpPort() {
        return SocketUtils.findAvailableUdpPort(1024);
    }

    public static int findAvailableUdpPort(int minPort) {
        return SocketUtils.findAvailableUdpPort(minPort, 65535);
    }

    public static int findAvailableUdpPort(int minPort, int maxPort) {
        return SocketType.UDP.findAvailablePort(minPort, maxPort);
    }

    public static SortedSet<Integer> findAvailableUdpPorts(int numRequested) {
        return SocketUtils.findAvailableUdpPorts(numRequested, 1024, 65535);
    }

    public static SortedSet<Integer> findAvailableUdpPorts(int numRequested, int minPort, int maxPort) {
        return SocketType.UDP.findAvailablePorts(numRequested, minPort, maxPort);
    }

    private static enum SocketType {
        TCP{

            @Override
            protected boolean isPortAvailable(int port) {
                try {
                    ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port, 1, InetAddress.getByName("localhost"));
                    serverSocket.close();
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        ,
        UDP{

            @Override
            protected boolean isPortAvailable(int port) {
                try {
                    DatagramSocket socket = new DatagramSocket(port, InetAddress.getByName("localhost"));
                    socket.close();
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        };


        protected abstract boolean isPortAvailable(int var1);

        private int findRandomPort(int minPort, int maxPort) {
            int portRange = maxPort - minPort;
            return minPort + random.nextInt(portRange + 1);
        }

        int findAvailablePort(int minPort, int maxPort) {
            int candidatePort;
            Assert.isTrue(minPort > 0, "'minPort' must be greater than 0");
            Assert.isTrue(maxPort >= minPort, "'maxPort' must be greater than or equal to 'minPort'");
            Assert.isTrue(maxPort <= 65535, "'maxPort' must be less than or equal to 65535");
            int portRange = maxPort - minPort;
            int searchCounter = 0;
            do {
                if (searchCounter > portRange) {
                    throw new IllegalStateException(String.format("Could not find an available %s port in the range [%d, %d] after %d attempts", this.name(), minPort, maxPort, searchCounter));
                }
                candidatePort = this.findRandomPort(minPort, maxPort);
                ++searchCounter;
            } while (!this.isPortAvailable(candidatePort));
            return candidatePort;
        }

        SortedSet<Integer> findAvailablePorts(int numRequested, int minPort, int maxPort) {
            Assert.isTrue(minPort > 0, "'minPort' must be greater than 0");
            Assert.isTrue(maxPort > minPort, "'maxPort' must be greater than 'minPort'");
            Assert.isTrue(maxPort <= 65535, "'maxPort' must be less than or equal to 65535");
            Assert.isTrue(numRequested > 0, "'numRequested' must be greater than 0");
            Assert.isTrue(maxPort - minPort >= numRequested, "'numRequested' must not be greater than 'maxPort' - 'minPort'");
            TreeSet<Integer> availablePorts = new TreeSet<Integer>();
            int attemptCount = 0;
            while (++attemptCount <= numRequested + 100 && availablePorts.size() < numRequested) {
                availablePorts.add(this.findAvailablePort(minPort, maxPort));
            }
            if (availablePorts.size() != numRequested) {
                throw new IllegalStateException(String.format("Could not find %d available %s ports in the range [%d, %d]", numRequested, this.name(), minPort, maxPort));
            }
            return availablePorts;
        }
    }
}

