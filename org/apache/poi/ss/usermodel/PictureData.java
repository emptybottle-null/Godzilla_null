/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public interface PictureData {
    public byte[] getData();

    public String suggestFileExtension();

    public String getMimeType();

    public int getPictureType();
}

