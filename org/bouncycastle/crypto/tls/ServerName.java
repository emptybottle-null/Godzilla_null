/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsUtils;

public class ServerName {
    protected short nameType;
    protected Object name;

    public ServerName(short s, Object object) {
        if (!ServerName.isCorrectType(s, object)) {
            throw new IllegalArgumentException("'name' is not an instance of the correct type");
        }
        this.nameType = s;
        this.name = object;
    }

    public short getNameType() {
        return this.nameType;
    }

    public Object getName() {
        return this.name;
    }

    public String getHostName() {
        if (!ServerName.isCorrectType((short)0, this.name)) {
            throw new IllegalStateException("'name' is not a HostName string");
        }
        return (String)this.name;
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.nameType, outputStream);
        switch (this.nameType) {
            case 0: {
                byte[] byArray = ((String)this.name).getBytes("ASCII");
                if (byArray.length < 1) {
                    throw new TlsFatalAlert(80);
                }
                TlsUtils.writeOpaque16(byArray, outputStream);
                break;
            }
            default: {
                throw new TlsFatalAlert(80);
            }
        }
    }

    public static ServerName parse(InputStream inputStream) throws IOException {
        String string;
        short s = TlsUtils.readUint8(inputStream);
        switch (s) {
            case 0: {
                byte[] byArray = TlsUtils.readOpaque16(inputStream);
                if (byArray.length < 1) {
                    throw new TlsFatalAlert(50);
                }
                string = new String(byArray, "ASCII");
                break;
            }
            default: {
                throw new TlsFatalAlert(50);
            }
        }
        return new ServerName(s, string);
    }

    protected static boolean isCorrectType(short s, Object object) {
        switch (s) {
            case 0: {
                return object instanceof String;
            }
        }
        throw new IllegalArgumentException("'nameType' is an unsupported NameType");
    }
}

