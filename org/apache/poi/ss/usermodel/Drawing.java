/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.ObjectData;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.ShapeContainer;

public interface Drawing<T extends Shape>
extends ShapeContainer<T> {
    public Picture createPicture(ClientAnchor var1, int var2);

    public Comment createCellComment(ClientAnchor var1);

    public ClientAnchor createAnchor(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8);

    public ObjectData createObjectData(ClientAnchor var1, int var2, int var3);
}

