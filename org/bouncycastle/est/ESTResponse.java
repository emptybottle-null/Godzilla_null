/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import org.bouncycastle.est.CTEBase64InputStream;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.HttpUtil;
import org.bouncycastle.est.LimitedSource;
import org.bouncycastle.est.Source;
import org.bouncycastle.util.Properties;
import org.bouncycastle.util.Strings;

public class ESTResponse {
    private final ESTRequest originalRequest;
    private final HttpUtil.Headers headers;
    private final byte[] lineBuffer;
    private final Source source;
    private String HttpVersion;
    private int statusCode;
    private String statusMessage;
    private InputStream inputStream;
    private Long contentLength;
    private long read = 0L;
    private Long absoluteReadLimit;
    private static final Long ZERO = 0L;

    public ESTResponse(ESTRequest eSTRequest, Source source) throws IOException {
        Set<String> set;
        this.originalRequest = eSTRequest;
        this.source = source;
        if (source instanceof LimitedSource) {
            this.absoluteReadLimit = ((LimitedSource)((Object)source)).getAbsoluteReadLimit();
        }
        this.inputStream = (set = Properties.asKeySet("org.bouncycastle.debug.est")).contains("input") || set.contains("all") ? new PrintingInputStream(source.getInputStream()) : source.getInputStream();
        this.headers = new HttpUtil.Headers();
        this.lineBuffer = new byte[1024];
        this.process();
    }

    private void process() throws IOException {
        this.HttpVersion = this.readStringIncluding(' ');
        this.statusCode = Integer.parseInt(this.readStringIncluding(' '));
        this.statusMessage = this.readStringIncluding('\n');
        String string = this.readStringIncluding('\n');
        while (string.length() > 0) {
            int n = string.indexOf(58);
            if (n > -1) {
                String string2 = Strings.toLowerCase(string.substring(0, n).trim());
                this.headers.add(string2, string.substring(n + 1).trim());
            }
            string = this.readStringIncluding('\n');
        }
        this.contentLength = this.getContentLength();
        if (this.statusCode == 204 || this.statusCode == 202) {
            if (this.contentLength == null) {
                this.contentLength = 0L;
            } else if (this.statusCode == 204 && this.contentLength > 0L) {
                throw new IOException("Got HTTP status 204 but Content-length > 0.");
            }
        }
        if (this.contentLength == null) {
            throw new IOException("No Content-length header.");
        }
        if (this.contentLength.equals(ZERO)) {
            this.inputStream = new InputStream(){

                public int read() throws IOException {
                    return -1;
                }
            };
        }
        if (this.contentLength != null) {
            if (this.contentLength < 0L) {
                throw new IOException("Server returned negative content length: " + this.absoluteReadLimit);
            }
            if (this.absoluteReadLimit != null && this.contentLength >= this.absoluteReadLimit) {
                throw new IOException("Content length longer than absolute read limit: " + this.absoluteReadLimit + " Content-Length: " + this.contentLength);
            }
        }
        this.inputStream = this.wrapWithCounter(this.inputStream, this.absoluteReadLimit);
        if ("base64".equalsIgnoreCase(this.getHeader("content-transfer-encoding"))) {
            this.inputStream = new CTEBase64InputStream(this.inputStream, this.getContentLength());
        }
    }

    public String getHeader(String string) {
        return this.headers.getFirstValue(string);
    }

    protected InputStream wrapWithCounter(final InputStream inputStream, final Long l) {
        return new InputStream(){

            public int read() throws IOException {
                int n = inputStream.read();
                if (n > -1) {
                    ESTResponse.this.read++;
                    if (l != null && ESTResponse.this.read >= l) {
                        throw new IOException("Absolute Read Limit exceeded: " + l);
                    }
                }
                return n;
            }

            public void close() throws IOException {
                if (ESTResponse.this.contentLength != null && ESTResponse.this.contentLength - 1L > ESTResponse.this.read) {
                    throw new IOException("Stream closed before limit fully read, Read: " + ESTResponse.this.read + " ContentLength: " + ESTResponse.this.contentLength);
                }
                if (inputStream.available() > 0) {
                    throw new IOException("Stream closed with extra content in pipe that exceeds content length.");
                }
                inputStream.close();
            }
        };
    }

    protected String readStringIncluding(char c) throws IOException {
        int n;
        int n2 = 0;
        do {
            n = this.inputStream.read();
            this.lineBuffer[n2++] = (byte)n;
            if (n2 < this.lineBuffer.length) continue;
            throw new IOException("Server sent line > " + this.lineBuffer.length);
        } while (n != c && n > -1);
        if (n == -1) {
            throw new EOFException();
        }
        return new String(this.lineBuffer, 0, n2).trim();
    }

    public ESTRequest getOriginalRequest() {
        return this.originalRequest;
    }

    public HttpUtil.Headers getHeaders() {
        return this.headers;
    }

    public String getHttpVersion() {
        return this.HttpVersion;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public Source getSource() {
        return this.source;
    }

    public Long getContentLength() {
        String string = this.headers.getFirstValue("Content-Length");
        if (string == null) {
            return null;
        }
        try {
            return Long.parseLong(string);
        } catch (RuntimeException runtimeException) {
            throw new RuntimeException("Content Length: '" + string + "' invalid. " + runtimeException.getMessage());
        }
    }

    public void close() throws IOException {
        if (this.inputStream != null) {
            this.inputStream.close();
        }
        this.source.close();
    }

    private class PrintingInputStream
    extends InputStream {
        private final InputStream src;

        private PrintingInputStream(InputStream inputStream) {
            this.src = inputStream;
        }

        public int read() throws IOException {
            int n = this.src.read();
            System.out.print(String.valueOf((char)n));
            return n;
        }

        public int available() throws IOException {
            return this.src.available();
        }

        public void close() throws IOException {
            this.src.close();
        }
    }
}

