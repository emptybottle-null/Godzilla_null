/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig.services;

public interface SignaturePolicyService {
    public String getSignaturePolicyIdentifier();

    public String getSignaturePolicyDescription();

    public String getSignaturePolicyDownloadUrl();

    public byte[] getSignaturePolicyDocument();
}

