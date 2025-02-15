/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.model;

import org.apache.poi.xslf.model.ParagraphPropertyFetcher;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public abstract class CharacterPropertyFetcher<T>
extends ParagraphPropertyFetcher<T> {
    public CharacterPropertyFetcher(int level) {
        super(level);
    }

    @Override
    public boolean fetch(CTTextParagraphProperties props) {
        if (props != null && props.isSetDefRPr()) {
            return this.fetch(props.getDefRPr());
        }
        return false;
    }

    public abstract boolean fetch(CTTextCharacterProperties var1);
}

