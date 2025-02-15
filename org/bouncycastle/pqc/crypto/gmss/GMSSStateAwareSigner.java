/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.StateAwareMessageSigner;
import org.bouncycastle.pqc.crypto.gmss.GMSSDigestProvider;
import org.bouncycastle.pqc.crypto.gmss.GMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSSigner;
import org.bouncycastle.util.Memoable;

public class GMSSStateAwareSigner
implements StateAwareMessageSigner {
    private final GMSSSigner gmssSigner;
    private GMSSPrivateKeyParameters key;

    public GMSSStateAwareSigner(Digest digest) {
        if (!(digest instanceof Memoable)) {
            throw new IllegalArgumentException("digest must implement Memoable");
        }
        final Memoable memoable = ((Memoable)((Object)digest)).copy();
        this.gmssSigner = new GMSSSigner(new GMSSDigestProvider(){

            public Digest get() {
                return (Digest)((Object)memoable.copy());
            }
        });
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (bl) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.key = (GMSSPrivateKeyParameters)parametersWithRandom.getParameters();
            } else {
                this.key = (GMSSPrivateKeyParameters)cipherParameters;
            }
        }
        this.gmssSigner.init(bl, cipherParameters);
    }

    public byte[] generateSignature(byte[] byArray) {
        if (this.key == null) {
            throw new IllegalStateException("signing key no longer usable");
        }
        byte[] byArray2 = this.gmssSigner.generateSignature(byArray);
        this.key = this.key.nextKey();
        return byArray2;
    }

    public boolean verifySignature(byte[] byArray, byte[] byArray2) {
        return this.gmssSigner.verifySignature(byArray, byArray2);
    }

    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        GMSSPrivateKeyParameters gMSSPrivateKeyParameters = this.key;
        this.key = null;
        return gMSSPrivateKeyParameters;
    }
}

