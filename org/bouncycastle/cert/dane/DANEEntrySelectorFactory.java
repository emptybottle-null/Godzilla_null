/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.dane;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.cert.dane.DANEEntrySelector;
import org.bouncycastle.cert.dane.DANEException;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

public class DANEEntrySelectorFactory {
    private final DigestCalculator digestCalculator;

    public DANEEntrySelectorFactory(DigestCalculator digestCalculator) {
        this.digestCalculator = digestCalculator;
    }

    public DANEEntrySelector createSelector(String string) throws DANEException {
        Object object;
        byte[] byArray = Strings.toUTF8ByteArray(string.substring(0, string.indexOf(64)));
        try {
            object = this.digestCalculator.getOutputStream();
            ((OutputStream)object).write(byArray);
            ((OutputStream)object).close();
        } catch (IOException iOException) {
            throw new DANEException("Unable to calculate digest string: " + iOException.getMessage(), iOException);
        }
        object = this.digestCalculator.getDigest();
        String string2 = Strings.fromByteArray(Hex.encode((byte[])object)) + "._smimecert." + string.substring(string.indexOf(64) + 1);
        return new DANEEntrySelector(string2);
    }
}

