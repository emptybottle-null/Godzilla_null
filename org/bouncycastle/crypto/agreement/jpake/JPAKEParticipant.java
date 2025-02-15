/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroup;
import org.bouncycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroups;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound1Payload;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound2Payload;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound3Payload;
import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.util.Arrays;

public class JPAKEParticipant {
    public static final int STATE_INITIALIZED = 0;
    public static final int STATE_ROUND_1_CREATED = 10;
    public static final int STATE_ROUND_1_VALIDATED = 20;
    public static final int STATE_ROUND_2_CREATED = 30;
    public static final int STATE_ROUND_2_VALIDATED = 40;
    public static final int STATE_KEY_CALCULATED = 50;
    public static final int STATE_ROUND_3_CREATED = 60;
    public static final int STATE_ROUND_3_VALIDATED = 70;
    private final String participantId;
    private char[] password;
    private final Digest digest;
    private final SecureRandom random;
    private final BigInteger p;
    private final BigInteger q;
    private final BigInteger g;
    private String partnerParticipantId;
    private BigInteger x1;
    private BigInteger x2;
    private BigInteger gx1;
    private BigInteger gx2;
    private BigInteger gx3;
    private BigInteger gx4;
    private BigInteger b;
    private int state;

    public JPAKEParticipant(String string, char[] cArray) {
        this(string, cArray, JPAKEPrimeOrderGroups.NIST_3072);
    }

    public JPAKEParticipant(String string, char[] cArray, JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup) {
        this(string, cArray, jPAKEPrimeOrderGroup, new SHA256Digest(), new SecureRandom());
    }

    public JPAKEParticipant(String string, char[] cArray, JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup, Digest digest, SecureRandom secureRandom) {
        JPAKEUtil.validateNotNull(string, "participantId");
        JPAKEUtil.validateNotNull(cArray, "password");
        JPAKEUtil.validateNotNull(jPAKEPrimeOrderGroup, "p");
        JPAKEUtil.validateNotNull(digest, "digest");
        JPAKEUtil.validateNotNull(secureRandom, "random");
        if (cArray.length == 0) {
            throw new IllegalArgumentException("Password must not be empty.");
        }
        this.participantId = string;
        this.password = Arrays.copyOf(cArray, cArray.length);
        this.p = jPAKEPrimeOrderGroup.getP();
        this.q = jPAKEPrimeOrderGroup.getQ();
        this.g = jPAKEPrimeOrderGroup.getG();
        this.digest = digest;
        this.random = secureRandom;
        this.state = 0;
    }

    public int getState() {
        return this.state;
    }

    public JPAKERound1Payload createRound1PayloadToSend() {
        if (this.state >= 10) {
            throw new IllegalStateException("Round1 payload already created for " + this.participantId);
        }
        this.x1 = JPAKEUtil.generateX1(this.q, this.random);
        this.x2 = JPAKEUtil.generateX2(this.q, this.random);
        this.gx1 = JPAKEUtil.calculateGx(this.p, this.g, this.x1);
        this.gx2 = JPAKEUtil.calculateGx(this.p, this.g, this.x2);
        BigInteger[] bigIntegerArray = JPAKEUtil.calculateZeroKnowledgeProof(this.p, this.q, this.g, this.gx1, this.x1, this.participantId, this.digest, this.random);
        BigInteger[] bigIntegerArray2 = JPAKEUtil.calculateZeroKnowledgeProof(this.p, this.q, this.g, this.gx2, this.x2, this.participantId, this.digest, this.random);
        this.state = 10;
        return new JPAKERound1Payload(this.participantId, this.gx1, this.gx2, bigIntegerArray, bigIntegerArray2);
    }

    public void validateRound1PayloadReceived(JPAKERound1Payload jPAKERound1Payload) throws CryptoException {
        if (this.state >= 20) {
            throw new IllegalStateException("Validation already attempted for round1 payload for" + this.participantId);
        }
        this.partnerParticipantId = jPAKERound1Payload.getParticipantId();
        this.gx3 = jPAKERound1Payload.getGx1();
        this.gx4 = jPAKERound1Payload.getGx2();
        BigInteger[] bigIntegerArray = jPAKERound1Payload.getKnowledgeProofForX1();
        BigInteger[] bigIntegerArray2 = jPAKERound1Payload.getKnowledgeProofForX2();
        JPAKEUtil.validateParticipantIdsDiffer(this.participantId, jPAKERound1Payload.getParticipantId());
        JPAKEUtil.validateGx4(this.gx4);
        JPAKEUtil.validateZeroKnowledgeProof(this.p, this.q, this.g, this.gx3, bigIntegerArray, jPAKERound1Payload.getParticipantId(), this.digest);
        JPAKEUtil.validateZeroKnowledgeProof(this.p, this.q, this.g, this.gx4, bigIntegerArray2, jPAKERound1Payload.getParticipantId(), this.digest);
        this.state = 20;
    }

    public JPAKERound2Payload createRound2PayloadToSend() {
        if (this.state >= 30) {
            throw new IllegalStateException("Round2 payload already created for " + this.participantId);
        }
        if (this.state < 20) {
            throw new IllegalStateException("Round1 payload must be validated prior to creating Round2 payload for " + this.participantId);
        }
        BigInteger bigInteger = JPAKEUtil.calculateGA(this.p, this.gx1, this.gx3, this.gx4);
        BigInteger bigInteger2 = JPAKEUtil.calculateS(this.password);
        BigInteger bigInteger3 = JPAKEUtil.calculateX2s(this.q, this.x2, bigInteger2);
        BigInteger bigInteger4 = JPAKEUtil.calculateA(this.p, this.q, bigInteger, bigInteger3);
        BigInteger[] bigIntegerArray = JPAKEUtil.calculateZeroKnowledgeProof(this.p, this.q, bigInteger, bigInteger4, bigInteger3, this.participantId, this.digest, this.random);
        this.state = 30;
        return new JPAKERound2Payload(this.participantId, bigInteger4, bigIntegerArray);
    }

    public void validateRound2PayloadReceived(JPAKERound2Payload jPAKERound2Payload) throws CryptoException {
        if (this.state >= 40) {
            throw new IllegalStateException("Validation already attempted for round2 payload for" + this.participantId);
        }
        if (this.state < 20) {
            throw new IllegalStateException("Round1 payload must be validated prior to validating Round2 payload for " + this.participantId);
        }
        BigInteger bigInteger = JPAKEUtil.calculateGA(this.p, this.gx3, this.gx1, this.gx2);
        this.b = jPAKERound2Payload.getA();
        BigInteger[] bigIntegerArray = jPAKERound2Payload.getKnowledgeProofForX2s();
        JPAKEUtil.validateParticipantIdsDiffer(this.participantId, jPAKERound2Payload.getParticipantId());
        JPAKEUtil.validateParticipantIdsEqual(this.partnerParticipantId, jPAKERound2Payload.getParticipantId());
        JPAKEUtil.validateGa(bigInteger);
        JPAKEUtil.validateZeroKnowledgeProof(this.p, this.q, bigInteger, this.b, bigIntegerArray, jPAKERound2Payload.getParticipantId(), this.digest);
        this.state = 40;
    }

    public BigInteger calculateKeyingMaterial() {
        if (this.state >= 50) {
            throw new IllegalStateException("Key already calculated for " + this.participantId);
        }
        if (this.state < 40) {
            throw new IllegalStateException("Round2 payload must be validated prior to creating key for " + this.participantId);
        }
        BigInteger bigInteger = JPAKEUtil.calculateS(this.password);
        Arrays.fill(this.password, '\u0000');
        this.password = null;
        BigInteger bigInteger2 = JPAKEUtil.calculateKeyingMaterial(this.p, this.q, this.gx4, this.x2, bigInteger, this.b);
        this.x1 = null;
        this.x2 = null;
        this.b = null;
        this.state = 50;
        return bigInteger2;
    }

    public JPAKERound3Payload createRound3PayloadToSend(BigInteger bigInteger) {
        if (this.state >= 60) {
            throw new IllegalStateException("Round3 payload already created for " + this.participantId);
        }
        if (this.state < 50) {
            throw new IllegalStateException("Keying material must be calculated prior to creating Round3 payload for " + this.participantId);
        }
        BigInteger bigInteger2 = JPAKEUtil.calculateMacTag(this.participantId, this.partnerParticipantId, this.gx1, this.gx2, this.gx3, this.gx4, bigInteger, this.digest);
        this.state = 60;
        return new JPAKERound3Payload(this.participantId, bigInteger2);
    }

    public void validateRound3PayloadReceived(JPAKERound3Payload jPAKERound3Payload, BigInteger bigInteger) throws CryptoException {
        if (this.state >= 70) {
            throw new IllegalStateException("Validation already attempted for round3 payload for" + this.participantId);
        }
        if (this.state < 50) {
            throw new IllegalStateException("Keying material must be calculated validated prior to validating Round3 payload for " + this.participantId);
        }
        JPAKEUtil.validateParticipantIdsDiffer(this.participantId, jPAKERound3Payload.getParticipantId());
        JPAKEUtil.validateParticipantIdsEqual(this.partnerParticipantId, jPAKERound3Payload.getParticipantId());
        JPAKEUtil.validateMacTag(this.participantId, this.partnerParticipantId, this.gx1, this.gx2, this.gx3, this.gx4, bigInteger, this.digest, jPAKERound3Payload.getMacTag());
        this.gx1 = null;
        this.gx2 = null;
        this.gx3 = null;
        this.gx4 = null;
        this.state = 70;
    }
}

