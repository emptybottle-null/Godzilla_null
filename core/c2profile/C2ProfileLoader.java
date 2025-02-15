/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile;

import core.c2profile.C2ProfileCheck;
import core.c2profile.cryption.CryptionContext;
import java.io.ByteArrayInputStream;

public final class C2ProfileLoader {
    public static CryptionContext loadC2Profile(String yaml) {
        C2ProfileCheck.check(new ByteArrayInputStream(yaml.getBytes()));
        CryptionContext cryptionContext = new CryptionContext();
        return cryptionContext;
    }
}

