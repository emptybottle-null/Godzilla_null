/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;

@Immutable
@Beta
@GwtCompatible
public final class HostAndPort
implements Serializable {
    private static final int NO_PORT = -1;
    private final String host;
    private final int port;
    private final boolean hasBracketlessColons;
    private static final long serialVersionUID = 0L;

    private HostAndPort(String host, int port, boolean hasBracketlessColons) {
        this.host = host;
        this.port = port;
        this.hasBracketlessColons = hasBracketlessColons;
    }

    public String getHost() {
        return this.host;
    }

    public boolean hasPort() {
        return this.port >= 0;
    }

    public int getPort() {
        Preconditions.checkState(this.hasPort());
        return this.port;
    }

    public int getPortOrDefault(int defaultPort) {
        return this.hasPort() ? this.port : defaultPort;
    }

    public static HostAndPort fromParts(String host, int port) {
        Preconditions.checkArgument(HostAndPort.isValidPort(port), "Port out of range: %s", port);
        HostAndPort parsedHost = HostAndPort.fromString(host);
        Preconditions.checkArgument(!parsedHost.hasPort(), "Host has a port: %s", (Object)host);
        return new HostAndPort(parsedHost.host, port, parsedHost.hasBracketlessColons);
    }

    public static HostAndPort fromHost(String host) {
        HostAndPort parsedHost = HostAndPort.fromString(host);
        Preconditions.checkArgument(!parsedHost.hasPort(), "Host has a port: %s", (Object)host);
        return parsedHost;
    }

    public static HostAndPort fromString(String hostPortString) {
        String host;
        Preconditions.checkNotNull(hostPortString);
        String portString = null;
        boolean hasBracketlessColons = false;
        if (hostPortString.startsWith("[")) {
            String[] hostAndPort = HostAndPort.getHostAndPortFromBracketedHost(hostPortString);
            host = hostAndPort[0];
            portString = hostAndPort[1];
        } else {
            int colonPos = hostPortString.indexOf(58);
            if (colonPos >= 0 && hostPortString.indexOf(58, colonPos + 1) == -1) {
                host = hostPortString.substring(0, colonPos);
                portString = hostPortString.substring(colonPos + 1);
            } else {
                host = hostPortString;
                hasBracketlessColons = colonPos >= 0;
            }
        }
        int port = -1;
        if (!Strings.isNullOrEmpty(portString)) {
            Preconditions.checkArgument(!portString.startsWith("+"), "Unparseable port number: %s", (Object)hostPortString);
            try {
                port = Integer.parseInt(portString);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Unparseable port number: " + hostPortString);
            }
            Preconditions.checkArgument(HostAndPort.isValidPort(port), "Port number out of range: %s", (Object)hostPortString);
        }
        return new HostAndPort(host, port, hasBracketlessColons);
    }

    private static String[] getHostAndPortFromBracketedHost(String hostPortString) {
        int colonIndex = 0;
        int closeBracketIndex = 0;
        Preconditions.checkArgument(hostPortString.charAt(0) == '[', "Bracketed host-port string must start with a bracket: %s", (Object)hostPortString);
        colonIndex = hostPortString.indexOf(58);
        closeBracketIndex = hostPortString.lastIndexOf(93);
        Preconditions.checkArgument(colonIndex > -1 && closeBracketIndex > colonIndex, "Invalid bracketed host/port: %s", (Object)hostPortString);
        String host = hostPortString.substring(1, closeBracketIndex);
        if (closeBracketIndex + 1 == hostPortString.length()) {
            return new String[]{host, ""};
        }
        Preconditions.checkArgument(hostPortString.charAt(closeBracketIndex + 1) == ':', "Only a colon may follow a close bracket: %s", (Object)hostPortString);
        for (int i = closeBracketIndex + 2; i < hostPortString.length(); ++i) {
            Preconditions.checkArgument(Character.isDigit(hostPortString.charAt(i)), "Port must be numeric: %s", (Object)hostPortString);
        }
        return new String[]{host, hostPortString.substring(closeBracketIndex + 2)};
    }

    public HostAndPort withDefaultPort(int defaultPort) {
        Preconditions.checkArgument(HostAndPort.isValidPort(defaultPort));
        if (this.hasPort()) {
            return this;
        }
        return new HostAndPort(this.host, defaultPort, this.hasBracketlessColons);
    }

    public HostAndPort requireBracketsForIPv6() {
        Preconditions.checkArgument(!this.hasBracketlessColons, "Possible bracketless IPv6 literal: %s", (Object)this.host);
        return this;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof HostAndPort) {
            HostAndPort that = (HostAndPort)other;
            return Objects.equal(this.host, that.host) && this.port == that.port;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.host, this.port);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(this.host.length() + 8);
        if (this.host.indexOf(58) >= 0) {
            builder.append('[').append(this.host).append(']');
        } else {
            builder.append(this.host);
        }
        if (this.hasPort()) {
            builder.append(':').append(this.port);
        }
        return builder.toString();
    }

    private static boolean isValidPort(int port) {
        return port >= 0 && port <= 65535;
    }
}

