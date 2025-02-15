/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import org.apache.poi.xdgf.usermodel.XDGFSheet;
import org.apache.poi.xdgf.usermodel.section.XDGFSection;

public class GenericSection
extends XDGFSection {
    public GenericSection(SectionType section, XDGFSheet containingSheet) {
        super(section, containingSheet);
    }

    @Override
    public void setupMaster(XDGFSection section) {
    }
}

