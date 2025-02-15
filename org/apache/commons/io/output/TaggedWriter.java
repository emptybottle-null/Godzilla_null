/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.UUID;
import org.apache.commons.io.TaggedIOException;
import org.apache.commons.io.output.ProxyWriter;

public class TaggedWriter
extends ProxyWriter {
    private final Serializable tag = UUID.randomUUID();

    public TaggedWriter(Writer proxy) {
        super(proxy);
    }

    public boolean isCauseOf(Exception exception) {
        return TaggedIOException.isTaggedWith(exception, this.tag);
    }

    public void throwIfCauseOf(Exception exception) throws IOException {
        TaggedIOException.throwCauseIfTaggedWith(exception, this.tag);
    }

    @Override
    protected void handleIOException(IOException e) throws IOException {
        throw new TaggedIOException(e, this.tag);
    }
}

