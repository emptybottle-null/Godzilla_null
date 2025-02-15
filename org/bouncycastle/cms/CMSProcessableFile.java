/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSReadable;
import org.bouncycastle.cms.CMSTypedData;

public class CMSProcessableFile
implements CMSTypedData,
CMSReadable {
    private static final int DEFAULT_BUF_SIZE = 32768;
    private final ASN1ObjectIdentifier type;
    private final File file;
    private final byte[] buf;

    public CMSProcessableFile(File file) {
        this(file, 32768);
    }

    public CMSProcessableFile(File file, int n) {
        this(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()), file, n);
    }

    public CMSProcessableFile(ASN1ObjectIdentifier aSN1ObjectIdentifier, File file, int n) {
        this.type = aSN1ObjectIdentifier;
        this.file = file;
        this.buf = new byte[n];
    }

    public InputStream getInputStream() throws IOException, CMSException {
        return new BufferedInputStream(new FileInputStream(this.file), 32768);
    }

    public void write(OutputStream outputStream) throws IOException, CMSException {
        int n;
        FileInputStream fileInputStream = new FileInputStream(this.file);
        while ((n = fileInputStream.read(this.buf, 0, this.buf.length)) > 0) {
            outputStream.write(this.buf, 0, n);
        }
        fileInputStream.close();
    }

    public Object getContent() {
        return this.file;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.type;
    }
}

