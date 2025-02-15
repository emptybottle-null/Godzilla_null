/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.lw.LayoutSerializer;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import com.intellij.uiDesigner.lw.LwXmlReader;
import java.awt.Insets;
import org.jdom.Element;

public class GridLayoutSerializer
extends LayoutSerializer {
    public static GridLayoutSerializer INSTANCE = new GridLayoutSerializer();

    protected GridLayoutSerializer() {
    }

    void readLayout(Element element, LwContainer container) {
        int rowCount = LwXmlReader.getRequiredInt(element, "row-count");
        int columnCount = LwXmlReader.getRequiredInt(element, "column-count");
        int hGap = LwXmlReader.getOptionalInt(element, "hgap", -1);
        int vGap = LwXmlReader.getOptionalInt(element, "vgap", -1);
        boolean sameSizeHorizontally = LwXmlReader.getOptionalBoolean(element, "same-size-horizontally", false);
        boolean sameSizeVertically = LwXmlReader.getOptionalBoolean(element, "same-size-vertically", false);
        Element marginElement = LwXmlReader.getRequiredChild(element, "margin");
        Insets margin = new Insets(LwXmlReader.getRequiredInt(marginElement, "top"), LwXmlReader.getRequiredInt(marginElement, "left"), LwXmlReader.getRequiredInt(marginElement, "bottom"), LwXmlReader.getRequiredInt(marginElement, "right"));
        GridLayoutManager layout = new GridLayoutManager(rowCount, columnCount);
        layout.setMargin(margin);
        layout.setVGap(vGap);
        layout.setHGap(hGap);
        layout.setSameSizeHorizontally(sameSizeHorizontally);
        layout.setSameSizeVertically(sameSizeVertically);
        container.setLayout(layout);
    }

    void readChildConstraints(Element constraintsElement, LwComponent component) {
        Element gridElement = LwXmlReader.getChild(constraintsElement, "grid");
        if (gridElement != null) {
            Element maxSizeElement;
            Element prefSizeElement;
            GridConstraints constraints = new GridConstraints();
            constraints.setRow(LwXmlReader.getRequiredInt(gridElement, "row"));
            constraints.setColumn(LwXmlReader.getRequiredInt(gridElement, "column"));
            constraints.setRowSpan(LwXmlReader.getRequiredInt(gridElement, "row-span"));
            constraints.setColSpan(LwXmlReader.getRequiredInt(gridElement, "col-span"));
            constraints.setVSizePolicy(LwXmlReader.getRequiredInt(gridElement, "vsize-policy"));
            constraints.setHSizePolicy(LwXmlReader.getRequiredInt(gridElement, "hsize-policy"));
            constraints.setAnchor(LwXmlReader.getRequiredInt(gridElement, "anchor"));
            constraints.setFill(LwXmlReader.getRequiredInt(gridElement, "fill"));
            constraints.setIndent(LwXmlReader.getOptionalInt(gridElement, "indent", 0));
            constraints.setUseParentLayout(LwXmlReader.getOptionalBoolean(gridElement, "use-parent-layout", false));
            Element minSizeElement = LwXmlReader.getChild(gridElement, "minimum-size");
            if (minSizeElement != null) {
                constraints.myMinimumSize.width = LwXmlReader.getRequiredInt(minSizeElement, "width");
                constraints.myMinimumSize.height = LwXmlReader.getRequiredInt(minSizeElement, "height");
            }
            if ((prefSizeElement = LwXmlReader.getChild(gridElement, "preferred-size")) != null) {
                constraints.myPreferredSize.width = LwXmlReader.getRequiredInt(prefSizeElement, "width");
                constraints.myPreferredSize.height = LwXmlReader.getRequiredInt(prefSizeElement, "height");
            }
            if ((maxSizeElement = LwXmlReader.getChild(gridElement, "maximum-size")) != null) {
                constraints.myMaximumSize.width = LwXmlReader.getRequiredInt(maxSizeElement, "width");
                constraints.myMaximumSize.height = LwXmlReader.getRequiredInt(maxSizeElement, "height");
            }
            component.getConstraints().restore(constraints);
        }
    }
}

