/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.archivers.zip.AbstractUnicodeExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;

public class UnicodeCommentExtraField
extends AbstractUnicodeExtraField {
    public static final ZipShort UCOM_ID = new ZipShort(25461);

    public UnicodeCommentExtraField() {
    }

    public UnicodeCommentExtraField(String text, byte[] bytes, int off, int len) {
        super(text, bytes, off, len);
    }

    public UnicodeCommentExtraField(String comment, byte[] bytes) {
        super(comment, bytes);
    }

    @Override
    public ZipShort getHeaderId() {
        return UCOM_ID;
    }
}

