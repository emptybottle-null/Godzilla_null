/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.util.List;
import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.sl.usermodel.ConnectorShape;
import org.apache.poi.sl.usermodel.FreeformShape;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.ObjectShape;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface ShapeContainer<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends Iterable<S> {
    public List<S> getShapes();

    public void addShape(S var1);

    public boolean removeShape(S var1);

    public AutoShape<S, P> createAutoShape();

    public FreeformShape<S, P> createFreeform();

    public TextBox<S, P> createTextBox();

    public ConnectorShape<S, P> createConnector();

    public GroupShape<S, P> createGroup();

    public PictureShape<S, P> createPicture(PictureData var1);

    public TableShape<S, P> createTable(int var1, int var2);

    public ObjectShape<?, ?> createOleShape(PictureData var1);
}

