/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.math.BigInteger;
import java.util.Vector;
import org.bouncycastle.crypto.agreement.srp.SRP6StandardGroups;
import org.bouncycastle.crypto.params.SRP6GroupParameters;
import org.bouncycastle.crypto.tls.TlsSRPGroupVerifier;

public class DefaultTlsSRPGroupVerifier
implements TlsSRPGroupVerifier {
    protected static final Vector DEFAULT_GROUPS = new Vector();
    protected Vector groups;

    public DefaultTlsSRPGroupVerifier() {
        this(DEFAULT_GROUPS);
    }

    public DefaultTlsSRPGroupVerifier(Vector vector) {
        this.groups = vector;
    }

    public boolean accept(SRP6GroupParameters sRP6GroupParameters) {
        for (int i = 0; i < this.groups.size(); ++i) {
            if (!this.areGroupsEqual(sRP6GroupParameters, (SRP6GroupParameters)this.groups.elementAt(i))) continue;
            return true;
        }
        return false;
    }

    protected boolean areGroupsEqual(SRP6GroupParameters sRP6GroupParameters, SRP6GroupParameters sRP6GroupParameters2) {
        return sRP6GroupParameters == sRP6GroupParameters2 || this.areParametersEqual(sRP6GroupParameters.getN(), sRP6GroupParameters2.getN()) && this.areParametersEqual(sRP6GroupParameters.getG(), sRP6GroupParameters2.getG());
    }

    protected boolean areParametersEqual(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger == bigInteger2 || bigInteger.equals(bigInteger2);
    }

    static {
        DEFAULT_GROUPS.addElement(SRP6StandardGroups.rfc5054_1024);
        DEFAULT_GROUPS.addElement(SRP6StandardGroups.rfc5054_1536);
        DEFAULT_GROUPS.addElement(SRP6StandardGroups.rfc5054_2048);
        DEFAULT_GROUPS.addElement(SRP6StandardGroups.rfc5054_3072);
        DEFAULT_GROUPS.addElement(SRP6StandardGroups.rfc5054_4096);
        DEFAULT_GROUPS.addElement(SRP6StandardGroups.rfc5054_6144);
        DEFAULT_GROUPS.addElement(SRP6StandardGroups.rfc5054_8192);
    }
}

