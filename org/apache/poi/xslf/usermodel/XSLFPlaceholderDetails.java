/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.util.function.Consumer;
import java.util.function.Function;
import javax.xml.namespace.QName;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.PlaceholderDetails;
import org.apache.poi.xslf.usermodel.XSLFNotesMaster;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.STPlaceholderSize;
import org.openxmlformats.schemas.presentationml.x2006.main.STPlaceholderType;

public class XSLFPlaceholderDetails
implements PlaceholderDetails {
    private final XSLFShape shape;
    private CTPlaceholder _ph;
    private static final QName[] NV_CONTAINER = new QName[]{new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvSpPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvCxnSpPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvGrpSpPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvPicPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvGraphicFramePr")};
    private static final QName[] NV_PROPS = new QName[]{new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvPr")};

    XSLFPlaceholderDetails(XSLFShape shape) {
        this.shape = shape;
    }

    @Override
    public Placeholder getPlaceholder() {
        CTPlaceholder ph = this.getCTPlaceholder(false);
        if (ph == null || !ph.isSetType() && !ph.isSetIdx()) {
            return null;
        }
        return Placeholder.lookupOoxml(ph.getType().intValue());
    }

    @Override
    public void setPlaceholder(Placeholder placeholder) {
        CTPlaceholder ph = this.getCTPlaceholder(placeholder != null);
        if (ph != null) {
            if (placeholder != null) {
                ph.setType(STPlaceholderType.Enum.forInt(placeholder.ooxmlId));
            } else {
                this.getNvProps().unsetPh();
            }
        }
    }

    @Override
    public boolean isVisible() {
        CTPlaceholder ph = this.getCTPlaceholder(false);
        if (ph == null || !ph.isSetType()) {
            return true;
        }
        CTHeaderFooter hf = this.getHeaderFooter(false);
        if (hf == null) {
            return false;
        }
        Placeholder pl = Placeholder.lookupOoxml(ph.getType().intValue());
        if (pl == null) {
            return true;
        }
        switch (pl) {
            case DATETIME: {
                return !hf.isSetDt() || hf.getDt();
            }
            case FOOTER: {
                return !hf.isSetFtr() || hf.getFtr();
            }
            case HEADER: {
                return !hf.isSetHdr() || hf.getHdr();
            }
            case SLIDE_NUMBER: {
                return !hf.isSetSldNum() || hf.getSldNum();
            }
        }
        return true;
    }

    @Override
    public void setVisible(boolean isVisible) {
        Function<CTHeaderFooter, Consumer> fun;
        Placeholder ph = this.getPlaceholder();
        if (ph == null) {
            return;
        }
        switch (ph) {
            case DATETIME: {
                fun = hf -> hf::setDt;
                break;
            }
            case FOOTER: {
                fun = hf -> hf::setFtr;
                break;
            }
            case HEADER: {
                fun = hf -> hf::setHdr;
                break;
            }
            case SLIDE_NUMBER: {
                fun = hf -> hf::setSldNum;
                break;
            }
            default: {
                return;
            }
        }
        CTHeaderFooter hf2 = this.getHeaderFooter(true);
        if (hf2 == null) {
            return;
        }
        fun.apply(hf2).accept(isVisible);
    }

    @Override
    public PlaceholderDetails.PlaceholderSize getSize() {
        CTPlaceholder ph = this.getCTPlaceholder(false);
        if (ph == null || !ph.isSetSz()) {
            return null;
        }
        switch (ph.getSz().intValue()) {
            case 1: {
                return PlaceholderDetails.PlaceholderSize.full;
            }
            case 2: {
                return PlaceholderDetails.PlaceholderSize.half;
            }
            case 3: {
                return PlaceholderDetails.PlaceholderSize.quarter;
            }
        }
        return null;
    }

    @Override
    public void setSize(PlaceholderDetails.PlaceholderSize size) {
        CTPlaceholder ph = this.getCTPlaceholder(false);
        if (ph == null) {
            return;
        }
        if (size == null) {
            ph.unsetSz();
            return;
        }
        switch (size) {
            case full: {
                ph.setSz(STPlaceholderSize.FULL);
                break;
            }
            case half: {
                ph.setSz(STPlaceholderSize.HALF);
                break;
            }
            case quarter: {
                ph.setSz(STPlaceholderSize.QUARTER);
            }
        }
    }

    CTPlaceholder getCTPlaceholder(boolean create) {
        if (this._ph != null) {
            return this._ph;
        }
        CTApplicationNonVisualDrawingProps nv = this.getNvProps();
        if (nv == null) {
            return null;
        }
        this._ph = nv.isSetPh() || !create ? nv.getPh() : nv.addNewPh();
        return this._ph;
    }

    private CTApplicationNonVisualDrawingProps getNvProps() {
        try {
            return this.shape.selectProperty(CTApplicationNonVisualDrawingProps.class, null, NV_CONTAINER, NV_PROPS);
        } catch (XmlException e) {
            return null;
        }
    }

    private CTHeaderFooter getHeaderFooter(boolean create) {
        XSLFSheet master;
        XSLFSheet sheet = this.shape.getSheet();
        XSLFSheet xSLFSheet = master = sheet instanceof MasterSheet && !(sheet instanceof XSLFSlideLayout) ? sheet : (XSLFSheet)((Object)sheet.getMasterSheet());
        if (master instanceof XSLFSlideMaster) {
            CTSlideMaster ct = ((XSLFSlideMaster)master).getXmlObject();
            return ct.isSetHf() || !create ? ct.getHf() : ct.addNewHf();
        }
        if (master instanceof XSLFNotesMaster) {
            CTNotesMaster ct = ((XSLFNotesMaster)master).getXmlObject();
            return ct.isSetHf() || !create ? ct.getHf() : ct.addNewHf();
        }
        return null;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void setText(String text) {
    }
}

