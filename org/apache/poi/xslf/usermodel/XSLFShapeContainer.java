/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFConnectorShape;
import org.apache.poi.xslf.usermodel.XSLFFreeformShape;
import org.apache.poi.xslf.usermodel.XSLFGroupShape;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;

public interface XSLFShapeContainer
extends ShapeContainer<XSLFShape, XSLFTextParagraph> {
    public XSLFAutoShape createAutoShape();

    public XSLFFreeformShape createFreeform();

    public XSLFTextBox createTextBox();

    public XSLFConnectorShape createConnector();

    public XSLFGroupShape createGroup();

    public XSLFPictureShape createPicture(PictureData var1);

    public void clear();
}

