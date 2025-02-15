/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
import org.bouncycastle.util.Arrays;

public class JPAKERound2Payload {
    private final String participantId;
    private final BigInteger a;
    private final BigInteger[] knowledgeProofForX2s;

    public JPAKERound2Payload(String string, BigInteger bigInteger, BigInteger[] bigIntegerArray) {
        JPAKEUtil.validateNotNull(string, "participantId");
        JPAKEUtil.validateNotNull(bigInteger, "a");
        JPAKEUtil.validateNotNull(bigIntegerArray, "knowledgeProofForX2s");
        this.participantId = string;
        this.a = bigInteger;
        this.knowledgeProofForX2s = Arrays.copyOf(bigIntegerArray, bigIntegerArray.length);
    }

    public String getParticipantId() {
        return this.participantId;
    }

    public BigInteger getA() {
        return this.a;
    }

    public BigInteger[] getKnowledgeProofForX2s() {
        return Arrays.copyOf(this.knowledgeProofForX2s, this.knowledgeProofForX2s.length);
    }
}

