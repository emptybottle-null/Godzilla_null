/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.examples;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.agreement.jpake.JPAKEParticipant;
import org.bouncycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroup;
import org.bouncycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroups;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound1Payload;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound2Payload;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound3Payload;
import org.bouncycastle.crypto.digests.SHA256Digest;

public class JPAKEExample {
    public static void main(String[] stringArray) throws CryptoException {
        JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup = JPAKEPrimeOrderGroups.NIST_3072;
        BigInteger bigInteger = jPAKEPrimeOrderGroup.getP();
        BigInteger bigInteger2 = jPAKEPrimeOrderGroup.getQ();
        BigInteger bigInteger3 = jPAKEPrimeOrderGroup.getG();
        String string = "password";
        String string2 = "password";
        System.out.println("********* Initialization **********");
        System.out.println("Public parameters for the cyclic group:");
        System.out.println("p (" + bigInteger.bitLength() + " bits): " + bigInteger.toString(16));
        System.out.println("q (" + bigInteger2.bitLength() + " bits): " + bigInteger2.toString(16));
        System.out.println("g (" + bigInteger.bitLength() + " bits): " + bigInteger3.toString(16));
        System.out.println("p mod q = " + bigInteger.mod(bigInteger2).toString(16));
        System.out.println("g^{q} mod p = " + bigInteger3.modPow(bigInteger2, bigInteger).toString(16));
        System.out.println("");
        System.out.println("(Secret passwords used by Alice and Bob: \"" + string + "\" and \"" + string2 + "\")\n");
        SHA256Digest sHA256Digest = new SHA256Digest();
        SecureRandom secureRandom = new SecureRandom();
        JPAKEParticipant jPAKEParticipant = new JPAKEParticipant("alice", string.toCharArray(), jPAKEPrimeOrderGroup, sHA256Digest, secureRandom);
        JPAKEParticipant jPAKEParticipant2 = new JPAKEParticipant("bob", string2.toCharArray(), jPAKEPrimeOrderGroup, sHA256Digest, secureRandom);
        JPAKERound1Payload jPAKERound1Payload = jPAKEParticipant.createRound1PayloadToSend();
        JPAKERound1Payload jPAKERound1Payload2 = jPAKEParticipant2.createRound1PayloadToSend();
        System.out.println("************ Round 1 **************");
        System.out.println("Alice sends to Bob: ");
        System.out.println("g^{x1}=" + jPAKERound1Payload.getGx1().toString(16));
        System.out.println("g^{x2}=" + jPAKERound1Payload.getGx2().toString(16));
        System.out.println("KP{x1}={" + jPAKERound1Payload.getKnowledgeProofForX1()[0].toString(16) + "};{" + jPAKERound1Payload.getKnowledgeProofForX1()[1].toString(16) + "}");
        System.out.println("KP{x2}={" + jPAKERound1Payload.getKnowledgeProofForX2()[0].toString(16) + "};{" + jPAKERound1Payload.getKnowledgeProofForX2()[1].toString(16) + "}");
        System.out.println("");
        System.out.println("Bob sends to Alice: ");
        System.out.println("g^{x3}=" + jPAKERound1Payload2.getGx1().toString(16));
        System.out.println("g^{x4}=" + jPAKERound1Payload2.getGx2().toString(16));
        System.out.println("KP{x3}={" + jPAKERound1Payload2.getKnowledgeProofForX1()[0].toString(16) + "};{" + jPAKERound1Payload2.getKnowledgeProofForX1()[1].toString(16) + "}");
        System.out.println("KP{x4}={" + jPAKERound1Payload2.getKnowledgeProofForX2()[0].toString(16) + "};{" + jPAKERound1Payload2.getKnowledgeProofForX2()[1].toString(16) + "}");
        System.out.println("");
        jPAKEParticipant.validateRound1PayloadReceived(jPAKERound1Payload2);
        System.out.println("Alice checks g^{x4}!=1: OK");
        System.out.println("Alice checks KP{x3}: OK");
        System.out.println("Alice checks KP{x4}: OK");
        System.out.println("");
        jPAKEParticipant2.validateRound1PayloadReceived(jPAKERound1Payload);
        System.out.println("Bob checks g^{x2}!=1: OK");
        System.out.println("Bob checks KP{x1},: OK");
        System.out.println("Bob checks KP{x2},: OK");
        System.out.println("");
        JPAKERound2Payload jPAKERound2Payload = jPAKEParticipant.createRound2PayloadToSend();
        JPAKERound2Payload jPAKERound2Payload2 = jPAKEParticipant2.createRound2PayloadToSend();
        System.out.println("************ Round 2 **************");
        System.out.println("Alice sends to Bob: ");
        System.out.println("A=" + jPAKERound2Payload.getA().toString(16));
        System.out.println("KP{x2*s}={" + jPAKERound2Payload.getKnowledgeProofForX2s()[0].toString(16) + "},{" + jPAKERound2Payload.getKnowledgeProofForX2s()[1].toString(16) + "}");
        System.out.println("");
        System.out.println("Bob sends to Alice");
        System.out.println("B=" + jPAKERound2Payload2.getA().toString(16));
        System.out.println("KP{x4*s}={" + jPAKERound2Payload2.getKnowledgeProofForX2s()[0].toString(16) + "},{" + jPAKERound2Payload2.getKnowledgeProofForX2s()[1].toString(16) + "}");
        System.out.println("");
        jPAKEParticipant.validateRound2PayloadReceived(jPAKERound2Payload2);
        System.out.println("Alice checks KP{x4*s}: OK\n");
        jPAKEParticipant2.validateRound2PayloadReceived(jPAKERound2Payload);
        System.out.println("Bob checks KP{x2*s}: OK\n");
        BigInteger bigInteger4 = jPAKEParticipant.calculateKeyingMaterial();
        BigInteger bigInteger5 = jPAKEParticipant2.calculateKeyingMaterial();
        System.out.println("********* After round 2 ***********");
        System.out.println("Alice computes key material \t K=" + bigInteger4.toString(16));
        System.out.println("Bob computes key material \t K=" + bigInteger5.toString(16));
        System.out.println();
        BigInteger bigInteger6 = JPAKEExample.deriveSessionKey(bigInteger4);
        BigInteger bigInteger7 = JPAKEExample.deriveSessionKey(bigInteger5);
        JPAKERound3Payload jPAKERound3Payload = jPAKEParticipant.createRound3PayloadToSend(bigInteger4);
        JPAKERound3Payload jPAKERound3Payload2 = jPAKEParticipant2.createRound3PayloadToSend(bigInteger5);
        System.out.println("************ Round 3 **************");
        System.out.println("Alice sends to Bob: ");
        System.out.println("MacTag=" + jPAKERound3Payload.getMacTag().toString(16));
        System.out.println("");
        System.out.println("Bob sends to Alice: ");
        System.out.println("MacTag=" + jPAKERound3Payload2.getMacTag().toString(16));
        System.out.println("");
        jPAKEParticipant.validateRound3PayloadReceived(jPAKERound3Payload2, bigInteger4);
        System.out.println("Alice checks MacTag: OK\n");
        jPAKEParticipant2.validateRound3PayloadReceived(jPAKERound3Payload, bigInteger5);
        System.out.println("Bob checks MacTag: OK\n");
        System.out.println();
        System.out.println("MacTags validated, therefore the keying material matches.");
    }

    private static BigInteger deriveSessionKey(BigInteger bigInteger) {
        SHA256Digest sHA256Digest = new SHA256Digest();
        byte[] byArray = bigInteger.toByteArray();
        byte[] byArray2 = new byte[sHA256Digest.getDigestSize()];
        sHA256Digest.update(byArray, 0, byArray.length);
        sHA256Digest.doFinal(byArray2, 0);
        return new BigInteger(byArray2);
    }
}

