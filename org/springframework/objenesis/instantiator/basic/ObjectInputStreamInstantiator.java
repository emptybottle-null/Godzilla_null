/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.instantiator.basic;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.objenesis.instantiator.ObjectInstantiator;
import org.springframework.objenesis.instantiator.annotations.Instantiator;
import org.springframework.objenesis.instantiator.annotations.Typology;

@Instantiator(value=Typology.SERIALIZATION)
public class ObjectInputStreamInstantiator<T>
implements ObjectInstantiator<T> {
    private final ObjectInputStream inputStream;

    public ObjectInputStreamInstantiator(Class<T> clazz) {
        if (Serializable.class.isAssignableFrom(clazz)) {
            try {
                this.inputStream = new ObjectInputStream(new MockStream(clazz));
            } catch (IOException e) {
                throw new Error("IOException: " + e.getMessage());
            }
        } else {
            throw new ObjenesisException(new NotSerializableException(clazz + " not serializable"));
        }
    }

    @Override
    public T newInstance() {
        try {
            return (T)this.inputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new Error("ClassNotFoundException: " + e.getMessage());
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }

    private static class MockStream
    extends InputStream {
        private int pointer = 0;
        private byte[] data = HEADER;
        private int sequence = 0;
        private static final int[] NEXT = new int[]{1, 2, 2};
        private final byte[][] buffers;
        private static byte[] HEADER;
        private static byte[] REPEATING_DATA;

        private static void initialize() {
            try {
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                DataOutputStream dout = new DataOutputStream(byteOut);
                dout.writeShort(-21267);
                dout.writeShort(5);
                HEADER = byteOut.toByteArray();
                byteOut = new ByteArrayOutputStream();
                dout = new DataOutputStream(byteOut);
                dout.writeByte(115);
                dout.writeByte(113);
                dout.writeInt(0x7E0000);
                REPEATING_DATA = byteOut.toByteArray();
            } catch (IOException e) {
                throw new Error("IOException: " + e.getMessage());
            }
        }

        public MockStream(Class<?> clazz) {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            DataOutputStream dout = new DataOutputStream(byteOut);
            try {
                dout.writeByte(115);
                dout.writeByte(114);
                dout.writeUTF(clazz.getName());
                dout.writeLong(ObjectStreamClass.lookup(clazz).getSerialVersionUID());
                dout.writeByte(2);
                dout.writeShort(0);
                dout.writeByte(120);
                dout.writeByte(112);
            } catch (IOException e) {
                throw new Error("IOException: " + e.getMessage());
            }
            byte[] firstData = byteOut.toByteArray();
            this.buffers = new byte[][]{HEADER, firstData, REPEATING_DATA};
        }

        private void advanceBuffer() {
            this.pointer = 0;
            this.sequence = NEXT[this.sequence];
            this.data = this.buffers[this.sequence];
        }

        @Override
        public int read() {
            byte result = this.data[this.pointer++];
            if (this.pointer >= this.data.length) {
                this.advanceBuffer();
            }
            return result;
        }

        @Override
        public int available() {
            return Integer.MAX_VALUE;
        }

        @Override
        public int read(byte[] b, int off, int len) {
            int left;
            int remaining = this.data.length - this.pointer;
            for (left = len; remaining <= left; left -= remaining) {
                System.arraycopy(this.data, this.pointer, b, off, remaining);
                off += remaining;
                this.advanceBuffer();
                remaining = this.data.length - this.pointer;
            }
            if (left > 0) {
                System.arraycopy(this.data, this.pointer, b, off, left);
                this.pointer += left;
            }
            return len;
        }

        static {
            MockStream.initialize();
        }
    }
}

