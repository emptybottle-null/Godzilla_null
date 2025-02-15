/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.bouncycastle.crypto.tls.NameType;
import org.bouncycastle.crypto.tls.ServerName;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;

public class ServerNameList {
    protected Vector serverNameList;

    public ServerNameList(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("'serverNameList' must not be null");
        }
        this.serverNameList = vector;
    }

    public Vector getServerNameList() {
        return this.serverNameList;
    }

    public void encode(OutputStream outputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        short[] sArray = new short[]{};
        for (int i = 0; i < this.serverNameList.size(); ++i) {
            ServerName serverName = (ServerName)this.serverNameList.elementAt(i);
            if ((sArray = ServerNameList.checkNameType(sArray, serverName.getNameType())) == null) {
                throw new TlsFatalAlert(80);
            }
            serverName.encode(byteArrayOutputStream);
        }
        TlsUtils.checkUint16(byteArrayOutputStream.size());
        TlsUtils.writeUint16(byteArrayOutputStream.size(), outputStream);
        Streams.writeBufTo(byteArrayOutputStream, outputStream);
    }

    public static ServerNameList parse(InputStream inputStream) throws IOException {
        int n = TlsUtils.readUint16(inputStream);
        if (n < 1) {
            throw new TlsFatalAlert(50);
        }
        byte[] byArray = TlsUtils.readFully(n, inputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        short[] sArray = new short[]{};
        Vector<ServerName> vector = new Vector<ServerName>();
        while (byteArrayInputStream.available() > 0) {
            ServerName serverName = ServerName.parse(byteArrayInputStream);
            if ((sArray = ServerNameList.checkNameType(sArray, serverName.getNameType())) == null) {
                throw new TlsFatalAlert(47);
            }
            vector.addElement(serverName);
        }
        return new ServerNameList(vector);
    }

    private static short[] checkNameType(short[] sArray, short s) {
        if (!NameType.isValid(s) || Arrays.contains(sArray, s)) {
            return null;
        }
        return Arrays.append(sArray, s);
    }
}

