/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.Collection;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.x509.NoSuchStoreException;
import org.bouncycastle.x509.X509StoreParameters;
import org.bouncycastle.x509.X509StoreSpi;
import org.bouncycastle.x509.X509Util;

public class X509Store
implements Store {
    private Provider _provider;
    private X509StoreSpi _spi;

    public static X509Store getInstance(String string, X509StoreParameters x509StoreParameters) throws NoSuchStoreException {
        try {
            X509Util.Implementation implementation = X509Util.getImplementation("X509Store", string);
            return X509Store.createStore(implementation, x509StoreParameters);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new NoSuchStoreException(noSuchAlgorithmException.getMessage());
        }
    }

    public static X509Store getInstance(String string, X509StoreParameters x509StoreParameters, String string2) throws NoSuchStoreException, NoSuchProviderException {
        return X509Store.getInstance(string, x509StoreParameters, X509Util.getProvider(string2));
    }

    public static X509Store getInstance(String string, X509StoreParameters x509StoreParameters, Provider provider) throws NoSuchStoreException {
        try {
            X509Util.Implementation implementation = X509Util.getImplementation("X509Store", string, provider);
            return X509Store.createStore(implementation, x509StoreParameters);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new NoSuchStoreException(noSuchAlgorithmException.getMessage());
        }
    }

    private static X509Store createStore(X509Util.Implementation implementation, X509StoreParameters x509StoreParameters) {
        X509StoreSpi x509StoreSpi = (X509StoreSpi)implementation.getEngine();
        x509StoreSpi.engineInit(x509StoreParameters);
        return new X509Store(implementation.getProvider(), x509StoreSpi);
    }

    private X509Store(Provider provider, X509StoreSpi x509StoreSpi) {
        this._provider = provider;
        this._spi = x509StoreSpi;
    }

    public Provider getProvider() {
        return this._provider;
    }

    public Collection getMatches(Selector selector) {
        return this._spi.engineGetMatches(selector);
    }
}

