/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
import org.bouncycastle.util.Arrays;

public class JPAKERound1Payload {
    private final String participantId;
    private final BigInteger gx1;
    private final BigInteger gx2;
    private final BigInteger[] knowledgeProofForX1;
    private final BigInteger[] knowledgeProofForX2;

    public JPAKERound1Payload(String string, BigInteger bigInteger, BigInteger bigInteger2, BigInteger[] bigIntegerArray, BigInteger[] bigIntegerArray2) {
        JPAKEUtil.validateNotNull(string, "participantId");
        JPAKEUtil.validateNotNull(bigInteger, "gx1");
        JPAKEUtil.validateNotNull(bigInteger2, "gx2");
        JPAKEUtil.validateNotNull(bigIntegerArray, "knowledgeProofForX1");
        JPAKEUtil.validateNotNull(bigIntegerArray2, "knowledgeProofForX2");
        this.participantId = string;
        this.gx1 = bigInteger;
        this.gx2 = bigInteger2;
        this.knowledgeProofForX1 = Arrays.copyOf(bigIntegerArray, bigIntegerArray.length);
        this.knowledgeProofForX2 = Arrays.copyOf(bigIntegerArray2, bigIntegerArray2.length);
    }

    public String getParticipantId() {
        return this.participantId;
    }

    public BigInteger getGx1() {
        return this.gx1;
    }

    public BigInteger getGx2() {
        return this.gx2;
    }

    public BigInteger[] getKnowledgeProofForX1() {
        return Arrays.copyOf(this.knowledgeProofForX1, this.knowledgeProofForX1.length);
    }

    public BigInteger[] getKnowledgeProofForX2() {
        return Arrays.copyOf(this.knowledgeProofForX2, this.knowledgeProofForX2.length);
    }
}

