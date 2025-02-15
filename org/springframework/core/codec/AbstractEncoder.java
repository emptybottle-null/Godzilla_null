/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.codec;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.Encoder;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeType;

public abstract class AbstractEncoder<T>
implements Encoder<T> {
    private final List<MimeType> encodableMimeTypes;
    protected Log logger = LogFactory.getLog(this.getClass());

    protected AbstractEncoder(MimeType ... supportedMimeTypes) {
        this.encodableMimeTypes = Arrays.asList(supportedMimeTypes);
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }

    public Log getLogger() {
        return this.logger;
    }

    @Override
    public List<MimeType> getEncodableMimeTypes() {
        return this.encodableMimeTypes;
    }

    @Override
    public boolean canEncode(ResolvableType elementType, @Nullable MimeType mimeType) {
        if (mimeType == null) {
            return true;
        }
        for (MimeType candidate : this.encodableMimeTypes) {
            if (!candidate.isCompatibleWith(mimeType)) continue;
            return true;
        }
        return false;
    }
}

