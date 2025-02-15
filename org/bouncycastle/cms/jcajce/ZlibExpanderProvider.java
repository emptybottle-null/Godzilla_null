/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.InputExpander;
import org.bouncycastle.operator.InputExpanderProvider;
import org.bouncycastle.util.io.StreamOverflowException;

public class ZlibExpanderProvider
implements InputExpanderProvider {
    private final long limit;

    public ZlibExpanderProvider() {
        this.limit = -1L;
    }

    public ZlibExpanderProvider(long l) {
        this.limit = l;
    }

    public InputExpander get(final AlgorithmIdentifier algorithmIdentifier) {
        return new InputExpander(){

            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier;
            }

            public InputStream getInputStream(InputStream inputStream) {
                FilterInputStream filterInputStream = new InflaterInputStream(inputStream);
                if (ZlibExpanderProvider.this.limit >= 0L) {
                    filterInputStream = new LimitedInputStream(filterInputStream, ZlibExpanderProvider.this.limit);
                }
                return filterInputStream;
            }
        };
    }

    private static class LimitedInputStream
    extends FilterInputStream {
        private long remaining;

        public LimitedInputStream(InputStream inputStream, long l) {
            super(inputStream);
            this.remaining = l;
        }

        public int read() throws IOException {
            int n;
            if (this.remaining >= 0L && ((n = this.in.read()) < 0 || --this.remaining >= 0L)) {
                return n;
            }
            throw new StreamOverflowException("expanded byte limit exceeded");
        }

        public int read(byte[] byArray, int n, int n2) throws IOException {
            if (n2 < 1) {
                return super.read(byArray, n, n2);
            }
            if (this.remaining < 1L) {
                this.read();
                return -1;
            }
            int n3 = this.remaining > (long)n2 ? n2 : (int)this.remaining;
            int n4 = this.in.read(byArray, n, n3);
            if (n4 > 0) {
                this.remaining -= (long)n4;
            }
            return n4;
        }
    }
}

