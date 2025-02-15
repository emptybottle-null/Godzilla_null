/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public final class Funnels {
    private Funnels() {
    }

    public static Funnel<byte[]> byteArrayFunnel() {
        return ByteArrayFunnel.INSTANCE;
    }

    public static Funnel<CharSequence> unencodedCharsFunnel() {
        return UnencodedCharsFunnel.INSTANCE;
    }

    public static Funnel<CharSequence> stringFunnel(Charset charset) {
        return new StringCharsetFunnel(charset);
    }

    public static Funnel<Integer> integerFunnel() {
        return IntegerFunnel.INSTANCE;
    }

    public static <E> Funnel<Iterable<? extends E>> sequentialFunnel(Funnel<E> elementFunnel) {
        return new SequentialFunnel<E>(elementFunnel);
    }

    public static Funnel<Long> longFunnel() {
        return LongFunnel.INSTANCE;
    }

    public static OutputStream asOutputStream(PrimitiveSink sink) {
        return new SinkAsStream(sink);
    }

    private static class SinkAsStream
    extends OutputStream {
        final PrimitiveSink sink;

        SinkAsStream(PrimitiveSink sink) {
            this.sink = Preconditions.checkNotNull(sink);
        }

        @Override
        public void write(int b) {
            this.sink.putByte((byte)b);
        }

        @Override
        public void write(byte[] bytes) {
            this.sink.putBytes(bytes);
        }

        @Override
        public void write(byte[] bytes, int off, int len) {
            this.sink.putBytes(bytes, off, len);
        }

        public String toString() {
            return "Funnels.asOutputStream(" + this.sink + ")";
        }
    }

    private static enum LongFunnel implements Funnel<Long>
    {
        INSTANCE;


        @Override
        public void funnel(Long from, PrimitiveSink into) {
            into.putLong(from);
        }

        public String toString() {
            return "Funnels.longFunnel()";
        }
    }

    private static class SequentialFunnel<E>
    implements Funnel<Iterable<? extends E>>,
    Serializable {
        private final Funnel<E> elementFunnel;

        SequentialFunnel(Funnel<E> elementFunnel) {
            this.elementFunnel = Preconditions.checkNotNull(elementFunnel);
        }

        @Override
        public void funnel(Iterable<? extends E> from, PrimitiveSink into) {
            for (E e : from) {
                this.elementFunnel.funnel(e, into);
            }
        }

        public String toString() {
            return "Funnels.sequentialFunnel(" + this.elementFunnel + ")";
        }

        public boolean equals(@Nullable Object o) {
            if (o instanceof SequentialFunnel) {
                SequentialFunnel funnel = (SequentialFunnel)o;
                return this.elementFunnel.equals(funnel.elementFunnel);
            }
            return false;
        }

        public int hashCode() {
            return SequentialFunnel.class.hashCode() ^ this.elementFunnel.hashCode();
        }
    }

    private static enum IntegerFunnel implements Funnel<Integer>
    {
        INSTANCE;


        @Override
        public void funnel(Integer from, PrimitiveSink into) {
            into.putInt(from);
        }

        public String toString() {
            return "Funnels.integerFunnel()";
        }
    }

    private static class StringCharsetFunnel
    implements Funnel<CharSequence>,
    Serializable {
        private final Charset charset;

        StringCharsetFunnel(Charset charset) {
            this.charset = Preconditions.checkNotNull(charset);
        }

        @Override
        public void funnel(CharSequence from, PrimitiveSink into) {
            into.putString(from, this.charset);
        }

        public String toString() {
            return "Funnels.stringFunnel(" + this.charset.name() + ")";
        }

        public boolean equals(@Nullable Object o) {
            if (o instanceof StringCharsetFunnel) {
                StringCharsetFunnel funnel = (StringCharsetFunnel)o;
                return this.charset.equals(funnel.charset);
            }
            return false;
        }

        public int hashCode() {
            return StringCharsetFunnel.class.hashCode() ^ this.charset.hashCode();
        }

        Object writeReplace() {
            return new SerializedForm(this.charset);
        }

        private static class SerializedForm
        implements Serializable {
            private final String charsetCanonicalName;
            private static final long serialVersionUID = 0L;

            SerializedForm(Charset charset) {
                this.charsetCanonicalName = charset.name();
            }

            private Object readResolve() {
                return Funnels.stringFunnel(Charset.forName(this.charsetCanonicalName));
            }
        }
    }

    private static enum UnencodedCharsFunnel implements Funnel<CharSequence>
    {
        INSTANCE;


        @Override
        public void funnel(CharSequence from, PrimitiveSink into) {
            into.putUnencodedChars(from);
        }

        public String toString() {
            return "Funnels.unencodedCharsFunnel()";
        }
    }

    private static enum ByteArrayFunnel implements Funnel<byte[]>
    {
        INSTANCE;


        @Override
        public void funnel(byte[] from, PrimitiveSink into) {
            into.putBytes(from);
        }

        public String toString() {
            return "Funnels.byteArrayFunnel()";
        }
    }
}

