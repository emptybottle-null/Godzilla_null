/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.language.Caverphone2;

@Deprecated
public class Caverphone
implements StringEncoder {
    private final Caverphone2 encoder = new Caverphone2();

    public String caverphone(String source) {
        return this.encoder.encode(source);
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("Parameter supplied to Caverphone encode is not of type java.lang.String");
        }
        return this.caverphone((String)obj);
    }

    @Override
    public String encode(String str) {
        return this.caverphone(str);
    }

    public boolean isCaverphoneEqual(String str1, String str2) {
        return this.caverphone(str1).equals(this.caverphone(str2));
    }
}

