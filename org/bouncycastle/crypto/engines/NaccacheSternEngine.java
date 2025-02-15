/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

public class NaccacheSternEngine
implements AsymmetricBlockCipher {
    private boolean forEncryption;
    private NaccacheSternKeyParameters key;
    private Vector[] lookup = null;
    private boolean debug = false;
    private static BigInteger ZERO = BigInteger.valueOf(0L);
    private static BigInteger ONE = BigInteger.valueOf(1L);

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.forEncryption = bl;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom)cipherParameters).getParameters();
        }
        this.key = (NaccacheSternKeyParameters)cipherParameters;
        if (!this.forEncryption) {
            if (this.debug) {
                System.out.println("Constructing lookup Array");
            }
            NaccacheSternPrivateKeyParameters naccacheSternPrivateKeyParameters = (NaccacheSternPrivateKeyParameters)this.key;
            Vector vector = naccacheSternPrivateKeyParameters.getSmallPrimes();
            this.lookup = new Vector[vector.size()];
            for (int i = 0; i < vector.size(); ++i) {
                BigInteger bigInteger = (BigInteger)vector.elementAt(i);
                int n = bigInteger.intValue();
                this.lookup[i] = new Vector();
                this.lookup[i].addElement(ONE);
                if (this.debug) {
                    System.out.println("Constructing lookup ArrayList for " + n);
                }
                BigInteger bigInteger2 = ZERO;
                for (int j = 1; j < n; ++j) {
                    bigInteger2 = bigInteger2.add(naccacheSternPrivateKeyParameters.getPhi_n());
                    BigInteger bigInteger3 = bigInteger2.divide(bigInteger);
                    this.lookup[i].addElement(naccacheSternPrivateKeyParameters.getG().modPow(bigInteger3, naccacheSternPrivateKeyParameters.getModulus()));
                }
            }
        }
    }

    public void setDebug(boolean bl) {
        this.debug = bl;
    }

    public int getInputBlockSize() {
        if (this.forEncryption) {
            return (this.key.getLowerSigmaBound() + 7) / 8 - 1;
        }
        return this.key.getModulus().toByteArray().length;
    }

    public int getOutputBlockSize() {
        if (this.forEncryption) {
            return this.key.getModulus().toByteArray().length;
        }
        return (this.key.getLowerSigmaBound() + 7) / 8 - 1;
    }

    public byte[] processBlock(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        byte[] byArray2;
        byte[] byArray3;
        if (this.key == null) {
            throw new IllegalStateException("NaccacheStern engine not initialised");
        }
        if (n2 > this.getInputBlockSize() + 1) {
            throw new DataLengthException("input too large for Naccache-Stern cipher.\n");
        }
        if (!this.forEncryption && n2 < this.getInputBlockSize()) {
            throw new InvalidCipherTextException("BlockLength does not match modulus for Naccache-Stern cipher.\n");
        }
        if (n != 0 || n2 != byArray.length) {
            byArray3 = new byte[n2];
            System.arraycopy(byArray, n, byArray3, 0, n2);
        } else {
            byArray3 = byArray;
        }
        BigInteger bigInteger = new BigInteger(1, byArray3);
        if (this.debug) {
            System.out.println("input as BigInteger: " + bigInteger);
        }
        if (this.forEncryption) {
            byArray2 = this.encrypt(bigInteger);
        } else {
            Vector<BigInteger> vector = new Vector<BigInteger>();
            NaccacheSternPrivateKeyParameters naccacheSternPrivateKeyParameters = (NaccacheSternPrivateKeyParameters)this.key;
            Vector vector2 = naccacheSternPrivateKeyParameters.getSmallPrimes();
            for (int i = 0; i < vector2.size(); ++i) {
                BigInteger bigInteger2 = bigInteger.modPow(naccacheSternPrivateKeyParameters.getPhi_n().divide((BigInteger)vector2.elementAt(i)), naccacheSternPrivateKeyParameters.getModulus());
                Vector vector3 = this.lookup[i];
                if (this.lookup[i].size() != ((BigInteger)vector2.elementAt(i)).intValue()) {
                    if (this.debug) {
                        System.out.println("Prime is " + vector2.elementAt(i) + ", lookup table has size " + vector3.size());
                    }
                    throw new InvalidCipherTextException("Error in lookup Array for " + ((BigInteger)vector2.elementAt(i)).intValue() + ": Size mismatch. Expected ArrayList with length " + ((BigInteger)vector2.elementAt(i)).intValue() + " but found ArrayList of length " + this.lookup[i].size());
                }
                int n3 = vector3.indexOf(bigInteger2);
                if (n3 == -1) {
                    if (this.debug) {
                        System.out.println("Actual prime is " + vector2.elementAt(i));
                        System.out.println("Decrypted value is " + bigInteger2);
                        System.out.println("LookupList for " + vector2.elementAt(i) + " with size " + this.lookup[i].size() + " is: ");
                        for (int j = 0; j < this.lookup[i].size(); ++j) {
                            System.out.println(this.lookup[i].elementAt(j));
                        }
                    }
                    throw new InvalidCipherTextException("Lookup failed");
                }
                vector.addElement(BigInteger.valueOf(n3));
            }
            BigInteger bigInteger3 = NaccacheSternEngine.chineseRemainder(vector, vector2);
            byArray2 = bigInteger3.toByteArray();
        }
        return byArray2;
    }

    public byte[] encrypt(BigInteger bigInteger) {
        byte[] byArray = this.key.getModulus().toByteArray();
        Arrays.fill(byArray, (byte)0);
        byte[] byArray2 = this.key.getG().modPow(bigInteger, this.key.getModulus()).toByteArray();
        System.arraycopy(byArray2, 0, byArray, byArray.length - byArray2.length, byArray2.length);
        if (this.debug) {
            System.out.println("Encrypted value is:  " + new BigInteger(byArray));
        }
        return byArray;
    }

    public byte[] addCryptedBlocks(byte[] byArray, byte[] byArray2) throws InvalidCipherTextException {
        if (this.forEncryption ? byArray.length > this.getOutputBlockSize() || byArray2.length > this.getOutputBlockSize() : byArray.length > this.getInputBlockSize() || byArray2.length > this.getInputBlockSize()) {
            throw new InvalidCipherTextException("BlockLength too large for simple addition.\n");
        }
        BigInteger bigInteger = new BigInteger(1, byArray);
        BigInteger bigInteger2 = new BigInteger(1, byArray2);
        BigInteger bigInteger3 = bigInteger.multiply(bigInteger2);
        bigInteger3 = bigInteger3.mod(this.key.getModulus());
        if (this.debug) {
            System.out.println("c(m1) as BigInteger:....... " + bigInteger);
            System.out.println("c(m2) as BigInteger:....... " + bigInteger2);
            System.out.println("c(m1)*c(m2)%n = c(m1+m2)%n: " + bigInteger3);
        }
        byte[] byArray3 = this.key.getModulus().toByteArray();
        Arrays.fill(byArray3, (byte)0);
        System.arraycopy(bigInteger3.toByteArray(), 0, byArray3, byArray3.length - bigInteger3.toByteArray().length, bigInteger3.toByteArray().length);
        return byArray3;
    }

    public byte[] processData(byte[] byArray) throws InvalidCipherTextException {
        if (this.debug) {
            System.out.println();
        }
        if (byArray.length > this.getInputBlockSize()) {
            byte[] byArray2;
            int n = this.getInputBlockSize();
            int n2 = this.getOutputBlockSize();
            if (this.debug) {
                System.out.println("Input blocksize is:  " + n + " bytes");
                System.out.println("Output blocksize is: " + n2 + " bytes");
                System.out.println("Data has length:.... " + byArray.length + " bytes");
            }
            int n3 = 0;
            int n4 = 0;
            byte[] byArray3 = new byte[(byArray.length / n + 1) * n2];
            while (n3 < byArray.length) {
                if (n3 + n < byArray.length) {
                    byArray2 = this.processBlock(byArray, n3, n);
                    n3 += n;
                } else {
                    byArray2 = this.processBlock(byArray, n3, byArray.length - n3);
                    n3 += byArray.length - n3;
                }
                if (this.debug) {
                    System.out.println("new datapos is " + n3);
                }
                if (byArray2 != null) {
                    System.arraycopy(byArray2, 0, byArray3, n4, byArray2.length);
                    n4 += byArray2.length;
                    continue;
                }
                if (this.debug) {
                    System.out.println("cipher returned null");
                }
                throw new InvalidCipherTextException("cipher returned null");
            }
            byArray2 = new byte[n4];
            System.arraycopy(byArray3, 0, byArray2, 0, n4);
            if (this.debug) {
                System.out.println("returning " + byArray2.length + " bytes");
            }
            return byArray2;
        }
        if (this.debug) {
            System.out.println("data size is less then input block size, processing directly");
        }
        return this.processBlock(byArray, 0, byArray.length);
    }

    private static BigInteger chineseRemainder(Vector vector, Vector vector2) {
        int n;
        BigInteger bigInteger = ZERO;
        BigInteger bigInteger2 = ONE;
        for (n = 0; n < vector2.size(); ++n) {
            bigInteger2 = bigInteger2.multiply((BigInteger)vector2.elementAt(n));
        }
        for (n = 0; n < vector2.size(); ++n) {
            BigInteger bigInteger3 = (BigInteger)vector2.elementAt(n);
            BigInteger bigInteger4 = bigInteger2.divide(bigInteger3);
            BigInteger bigInteger5 = bigInteger4.modInverse(bigInteger3);
            BigInteger bigInteger6 = bigInteger4.multiply(bigInteger5);
            bigInteger6 = bigInteger6.multiply((BigInteger)vector.elementAt(n));
            bigInteger = bigInteger.add(bigInteger6);
        }
        return bigInteger.mod(bigInteger2);
    }
}

