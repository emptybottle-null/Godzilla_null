/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.crypto.tls.TlsUtils;

public class NewSessionTicket {
    protected long ticketLifetimeHint;
    protected byte[] ticket;

    public NewSessionTicket(long l, byte[] byArray) {
        this.ticketLifetimeHint = l;
        this.ticket = byArray;
    }

    public long getTicketLifetimeHint() {
        return this.ticketLifetimeHint;
    }

    public byte[] getTicket() {
        return this.ticket;
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint32(this.ticketLifetimeHint, outputStream);
        TlsUtils.writeOpaque16(this.ticket, outputStream);
    }

    public static NewSessionTicket parse(InputStream inputStream) throws IOException {
        long l = TlsUtils.readUint32(inputStream);
        byte[] byArray = TlsUtils.readOpaque16(inputStream);
        return new NewSessionTicket(l, byArray);
    }
}

