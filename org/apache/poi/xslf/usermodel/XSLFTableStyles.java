/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xslf.usermodel.XSLFTableStyle;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument;

public class XSLFTableStyles
extends POIXMLDocumentPart
implements Iterable<XSLFTableStyle> {
    private CTTableStyleList _tblStyleLst;
    private List<XSLFTableStyle> _styles;

    public XSLFTableStyles() {
    }

    public XSLFTableStyles(PackagePart part) throws IOException, XmlException {
        super(part);
        TblStyleLstDocument styleDoc;
        InputStream is = this.getPackagePart().getInputStream();
        Object object = null;
        try {
            styleDoc = TblStyleLstDocument.Factory.parse(is);
        } catch (Throwable throwable) {
            object = throwable;
            throw throwable;
        } finally {
            if (is != null) {
                if (object != null) {
                    try {
                        is.close();
                    } catch (Throwable throwable) {
                        ((Throwable)object).addSuppressed(throwable);
                    }
                } else {
                    is.close();
                }
            }
        }
        this._tblStyleLst = styleDoc.getTblStyleLst();
        List<CTTableStyle> tblStyles = this._tblStyleLst.getTblStyleList();
        this._styles = new ArrayList<XSLFTableStyle>(tblStyles.size());
        for (CTTableStyle c : tblStyles) {
            this._styles.add(new XSLFTableStyle(c));
        }
    }

    public CTTableStyleList getXmlObject() {
        return this._tblStyleLst;
    }

    @Override
    public Iterator<XSLFTableStyle> iterator() {
        return this._styles.iterator();
    }

    public List<XSLFTableStyle> getStyles() {
        return Collections.unmodifiableList(this._styles);
    }
}

