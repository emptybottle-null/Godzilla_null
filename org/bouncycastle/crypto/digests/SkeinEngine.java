/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.engines.ThreefishEngine;
import org.bouncycastle.crypto.params.SkeinParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;

public class SkeinEngine
implements Memoable {
    public static final int SKEIN_256 = 256;
    public static final int SKEIN_512 = 512;
    public static final int SKEIN_1024 = 1024;
    private static final int PARAM_TYPE_KEY = 0;
    private static final int PARAM_TYPE_CONFIG = 4;
    private static final int PARAM_TYPE_MESSAGE = 48;
    private static final int PARAM_TYPE_OUTPUT = 63;
    private static final Hashtable INITIAL_STATES = new Hashtable();
    final ThreefishEngine threefish;
    private final int outputSizeBytes;
    long[] chain;
    private long[] initialState;
    private byte[] key;
    private Parameter[] preMessageParameters;
    private Parameter[] postMessageParameters;
    private final UBI ubi;
    private final byte[] singleByte = new byte[1];

    private static void initialState(int n, int n2, long[] lArray) {
        INITIAL_STATES.put(SkeinEngine.variantIdentifier(n / 8, n2 / 8), lArray);
    }

    private static Integer variantIdentifier(int n, int n2) {
        return new Integer(n2 << 16 | n);
    }

    public SkeinEngine(int n, int n2) {
        if (n2 % 8 != 0) {
            throw new IllegalArgumentException("Output size must be a multiple of 8 bits. :" + n2);
        }
        this.outputSizeBytes = n2 / 8;
        this.threefish = new ThreefishEngine(n);
        this.ubi = new UBI(this.threefish.getBlockSize());
    }

    public SkeinEngine(SkeinEngine skeinEngine) {
        this(skeinEngine.getBlockSize() * 8, skeinEngine.getOutputSize() * 8);
        this.copyIn(skeinEngine);
    }

    private void copyIn(SkeinEngine skeinEngine) {
        this.ubi.reset(skeinEngine.ubi);
        this.chain = Arrays.clone(skeinEngine.chain, this.chain);
        this.initialState = Arrays.clone(skeinEngine.initialState, this.initialState);
        this.key = Arrays.clone(skeinEngine.key, this.key);
        this.preMessageParameters = SkeinEngine.clone(skeinEngine.preMessageParameters, this.preMessageParameters);
        this.postMessageParameters = SkeinEngine.clone(skeinEngine.postMessageParameters, this.postMessageParameters);
    }

    private static Parameter[] clone(Parameter[] parameterArray, Parameter[] parameterArray2) {
        if (parameterArray == null) {
            return null;
        }
        if (parameterArray2 == null || parameterArray2.length != parameterArray.length) {
            parameterArray2 = new Parameter[parameterArray.length];
        }
        System.arraycopy(parameterArray, 0, parameterArray2, 0, parameterArray2.length);
        return parameterArray2;
    }

    public Memoable copy() {
        return new SkeinEngine(this);
    }

    public void reset(Memoable memoable) {
        SkeinEngine skeinEngine = (SkeinEngine)memoable;
        if (this.getBlockSize() != skeinEngine.getBlockSize() || this.outputSizeBytes != skeinEngine.outputSizeBytes) {
            throw new IllegalArgumentException("Incompatible parameters in provided SkeinEngine.");
        }
        this.copyIn(skeinEngine);
    }

    public int getOutputSize() {
        return this.outputSizeBytes;
    }

    public int getBlockSize() {
        return this.threefish.getBlockSize();
    }

    public void init(SkeinParameters skeinParameters) {
        this.chain = null;
        this.key = null;
        this.preMessageParameters = null;
        this.postMessageParameters = null;
        if (skeinParameters != null) {
            byte[] byArray = skeinParameters.getKey();
            if (byArray.length < 16) {
                throw new IllegalArgumentException("Skein key must be at least 128 bits.");
            }
            this.initParams(skeinParameters.getParameters());
        }
        this.createInitialState();
        this.ubiInit(48);
    }

    private void initParams(Hashtable hashtable) {
        Enumeration enumeration = hashtable.keys();
        Vector<Parameter> vector = new Vector<Parameter>();
        Vector<Parameter> vector2 = new Vector<Parameter>();
        while (enumeration.hasMoreElements()) {
            Integer n = (Integer)enumeration.nextElement();
            byte[] byArray = (byte[])hashtable.get(n);
            if (n == 0) {
                this.key = byArray;
                continue;
            }
            if (n < 48) {
                vector.addElement(new Parameter(n, byArray));
                continue;
            }
            vector2.addElement(new Parameter(n, byArray));
        }
        this.preMessageParameters = new Parameter[vector.size()];
        vector.copyInto(this.preMessageParameters);
        SkeinEngine.sort(this.preMessageParameters);
        this.postMessageParameters = new Parameter[vector2.size()];
        vector2.copyInto(this.postMessageParameters);
        SkeinEngine.sort(this.postMessageParameters);
    }

    private static void sort(Parameter[] parameterArray) {
        if (parameterArray == null) {
            return;
        }
        for (int i = 1; i < parameterArray.length; ++i) {
            Parameter parameter = parameterArray[i];
            for (int j = i; j > 0 && parameter.getType() < parameterArray[j - 1].getType(); --j) {
                parameterArray[j] = parameterArray[j - 1];
            }
            parameterArray[j] = parameter;
        }
    }

    private void createInitialState() {
        long[] lArray = (long[])INITIAL_STATES.get(SkeinEngine.variantIdentifier(this.getBlockSize(), this.getOutputSize()));
        if (this.key == null && lArray != null) {
            this.chain = Arrays.clone(lArray);
        } else {
            this.chain = new long[this.getBlockSize() / 8];
            if (this.key != null) {
                this.ubiComplete(0, this.key);
            }
            this.ubiComplete(4, new Configuration(this.outputSizeBytes * 8).getBytes());
        }
        if (this.preMessageParameters != null) {
            for (int i = 0; i < this.preMessageParameters.length; ++i) {
                Parameter parameter = this.preMessageParameters[i];
                this.ubiComplete(parameter.getType(), parameter.getValue());
            }
        }
        this.initialState = Arrays.clone(this.chain);
    }

    public void reset() {
        System.arraycopy(this.initialState, 0, this.chain, 0, this.chain.length);
        this.ubiInit(48);
    }

    private void ubiComplete(int n, byte[] byArray) {
        this.ubiInit(n);
        this.ubi.update(byArray, 0, byArray.length, this.chain);
        this.ubiFinal();
    }

    private void ubiInit(int n) {
        this.ubi.reset(n);
    }

    private void ubiFinal() {
        this.ubi.doFinal(this.chain);
    }

    private void checkInitialised() {
        if (this.ubi == null) {
            throw new IllegalArgumentException("Skein engine is not initialised.");
        }
    }

    public void update(byte by) {
        this.singleByte[0] = by;
        this.update(this.singleByte, 0, 1);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.checkInitialised();
        this.ubi.update(byArray, n, n2, this.chain);
    }

    public int doFinal(byte[] byArray, int n) {
        int n2;
        this.checkInitialised();
        if (byArray.length < n + this.outputSizeBytes) {
            throw new OutputLengthException("Output buffer is too short to hold output");
        }
        this.ubiFinal();
        if (this.postMessageParameters != null) {
            for (n2 = 0; n2 < this.postMessageParameters.length; ++n2) {
                Parameter parameter = this.postMessageParameters[n2];
                this.ubiComplete(parameter.getType(), parameter.getValue());
            }
        }
        n2 = this.getBlockSize();
        int n3 = (this.outputSizeBytes + n2 - 1) / n2;
        for (int i = 0; i < n3; ++i) {
            int n4 = Math.min(n2, this.outputSizeBytes - i * n2);
            this.output(i, byArray, n + i * n2, n4);
        }
        this.reset();
        return this.outputSizeBytes;
    }

    private void output(long l, byte[] byArray, int n, int n2) {
        byte[] byArray2 = new byte[8];
        ThreefishEngine.wordToBytes(l, byArray2, 0);
        long[] lArray = new long[this.chain.length];
        this.ubiInit(63);
        this.ubi.update(byArray2, 0, byArray2.length, lArray);
        this.ubi.doFinal(lArray);
        int n3 = (n2 + 8 - 1) / 8;
        for (int i = 0; i < n3; ++i) {
            int n4 = Math.min(8, n2 - i * 8);
            if (n4 == 8) {
                ThreefishEngine.wordToBytes(lArray[i], byArray, n + i * 8);
                continue;
            }
            ThreefishEngine.wordToBytes(lArray[i], byArray2, 0);
            System.arraycopy(byArray2, 0, byArray, n + i * 8, n4);
        }
    }

    static {
        SkeinEngine.initialState(256, 128, new long[]{-2228972824489528736L, -8629553674646093540L, 1155188648486244218L, -3677226592081559102L});
        SkeinEngine.initialState(256, 160, new long[]{1450197650740764312L, 3081844928540042640L, -3136097061834271170L, 3301952811952417661L});
        SkeinEngine.initialState(256, 224, new long[]{-4176654842910610933L, -8688192972455077604L, -7364642305011795836L, 4056579644589979102L});
        SkeinEngine.initialState(256, 256, new long[]{-243853671043386295L, 3443677322885453875L, -5531612722399640561L, 7662005193972177513L});
        SkeinEngine.initialState(512, 128, new long[]{-6288014694233956526L, 2204638249859346602L, 3502419045458743507L, -4829063503441264548L, 983504137758028059L, 1880512238245786339L, -6715892782214108542L, 7602827311880509485L});
        SkeinEngine.initialState(512, 160, new long[]{2934123928682216849L, -4399710721982728305L, 1684584802963255058L, 5744138295201861711L, 2444857010922934358L, -2807833639722848072L, -5121587834665610502L, 118355523173251694L});
        SkeinEngine.initialState(512, 224, new long[]{-3688341020067007964L, -3772225436291745297L, -8300862168937575580L, 4146387520469897396L, 1106145742801415120L, 7455425944880474941L, -7351063101234211863L, -7048981346965512457L});
        SkeinEngine.initialState(512, 384, new long[]{-6631894876634615969L, -5692838220127733084L, -7099962856338682626L, -2911352911530754598L, 2000907093792408677L, 9140007292425499655L, 6093301768906360022L, 2769176472213098488L});
        SkeinEngine.initialState(512, 512, new long[]{5261240102383538638L, 978932832955457283L, -8083517948103779378L, -7339365279355032399L, 6752626034097301424L, -1531723821829733388L, -7417126464950782685L, -5901786942805128141L});
    }

    private static class Configuration {
        private byte[] bytes = new byte[32];

        public Configuration(long l) {
            this.bytes[0] = 83;
            this.bytes[1] = 72;
            this.bytes[2] = 65;
            this.bytes[3] = 51;
            this.bytes[4] = 1;
            this.bytes[5] = 0;
            ThreefishEngine.wordToBytes(l, this.bytes, 8);
        }

        public byte[] getBytes() {
            return this.bytes;
        }
    }

    public static class Parameter {
        private int type;
        private byte[] value;

        public Parameter(int n, byte[] byArray) {
            this.type = n;
            this.value = byArray;
        }

        public int getType() {
            return this.type;
        }

        public byte[] getValue() {
            return this.value;
        }
    }

    private class UBI {
        private final UbiTweak tweak = new UbiTweak();
        private byte[] currentBlock;
        private int currentOffset;
        private long[] message;

        public UBI(int n) {
            this.currentBlock = new byte[n];
            this.message = new long[this.currentBlock.length / 8];
        }

        public void reset(UBI uBI) {
            this.currentBlock = Arrays.clone(uBI.currentBlock, this.currentBlock);
            this.currentOffset = uBI.currentOffset;
            this.message = Arrays.clone(uBI.message, this.message);
            this.tweak.reset(uBI.tweak);
        }

        public void reset(int n) {
            this.tweak.reset();
            this.tweak.setType(n);
            this.currentOffset = 0;
        }

        public void update(byte[] byArray, int n, int n2, long[] lArray) {
            int n3 = 0;
            while (n2 > n3) {
                if (this.currentOffset == this.currentBlock.length) {
                    this.processBlock(lArray);
                    this.tweak.setFirst(false);
                    this.currentOffset = 0;
                }
                int n4 = Math.min(n2 - n3, this.currentBlock.length - this.currentOffset);
                System.arraycopy(byArray, n + n3, this.currentBlock, this.currentOffset, n4);
                n3 += n4;
                this.currentOffset += n4;
                this.tweak.advancePosition(n4);
            }
        }

        private void processBlock(long[] lArray) {
            int n;
            SkeinEngine.this.threefish.init(true, SkeinEngine.this.chain, this.tweak.getWords());
            for (n = 0; n < this.message.length; ++n) {
                this.message[n] = ThreefishEngine.bytesToWord(this.currentBlock, n * 8);
            }
            SkeinEngine.this.threefish.processBlock(this.message, lArray);
            for (n = 0; n < lArray.length; ++n) {
                int n2 = n;
                lArray[n2] = lArray[n2] ^ this.message[n];
            }
        }

        public void doFinal(long[] lArray) {
            for (int i = this.currentOffset; i < this.currentBlock.length; ++i) {
                this.currentBlock[i] = 0;
            }
            this.tweak.setFinal(true);
            this.processBlock(lArray);
        }
    }

    private static class UbiTweak {
        private static final long LOW_RANGE = 9223372034707292160L;
        private static final long T1_FINAL = Long.MIN_VALUE;
        private static final long T1_FIRST = 0x4000000000000000L;
        private long[] tweak = new long[2];
        private boolean extendedPosition;

        public UbiTweak() {
            this.reset();
        }

        public void reset(UbiTweak ubiTweak) {
            this.tweak = Arrays.clone(ubiTweak.tweak, this.tweak);
            this.extendedPosition = ubiTweak.extendedPosition;
        }

        public void reset() {
            this.tweak[0] = 0L;
            this.tweak[1] = 0L;
            this.extendedPosition = false;
            this.setFirst(true);
        }

        public void setType(int n) {
            this.tweak[1] = this.tweak[1] & 0xFFFFFFC000000000L | ((long)n & 0x3FL) << 56;
        }

        public int getType() {
            return (int)(this.tweak[1] >>> 56 & 0x3FL);
        }

        public void setFirst(boolean bl) {
            this.tweak[1] = bl ? this.tweak[1] | 0x4000000000000000L : this.tweak[1] & 0xBFFFFFFFFFFFFFFFL;
        }

        public boolean isFirst() {
            return (this.tweak[1] & 0x4000000000000000L) != 0L;
        }

        public void setFinal(boolean bl) {
            this.tweak[1] = bl ? this.tweak[1] | Long.MIN_VALUE : this.tweak[1] & Long.MAX_VALUE;
        }

        public boolean isFinal() {
            return (this.tweak[1] & Long.MIN_VALUE) != 0L;
        }

        public void advancePosition(int n) {
            if (this.extendedPosition) {
                long[] lArray = new long[]{this.tweak[0] & 0xFFFFFFFFL, this.tweak[0] >>> 32 & 0xFFFFFFFFL, this.tweak[1] & 0xFFFFFFFFL};
                long l = n;
                for (int i = 0; i < lArray.length; ++i) {
                    lArray[i] = l += lArray[i];
                    l >>>= 32;
                }
                this.tweak[0] = (lArray[1] & 0xFFFFFFFFL) << 32 | lArray[0] & 0xFFFFFFFFL;
                this.tweak[1] = this.tweak[1] & 0xFFFFFFFF00000000L | lArray[2] & 0xFFFFFFFFL;
            } else {
                long l = this.tweak[0];
                this.tweak[0] = l += (long)n;
                if (l > 9223372034707292160L) {
                    this.extendedPosition = true;
                }
            }
        }

        public long[] getWords() {
            return this.tweak;
        }

        public String toString() {
            return this.getType() + " first: " + this.isFirst() + ", final: " + this.isFinal();
        }
    }
}

