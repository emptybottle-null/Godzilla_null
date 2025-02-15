/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.ChartsheetDocument;

public class ChartsheetDocumentImpl
extends XmlComplexContentImpl
implements ChartsheetDocument {
    private static final long serialVersionUID = 1L;
    private static final QName CHARTSHEET$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "chartsheet");

    public ChartsheetDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartsheet getChartsheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartsheet cTChartsheet = null;
            cTChartsheet = (CTChartsheet)((Object)this.get_store().find_element_user(CHARTSHEET$0, 0));
            if (cTChartsheet == null) {
                return null;
            }
            return cTChartsheet;
        }
    }

    @Override
    public void setChartsheet(CTChartsheet cTChartsheet) {
        this.generatedSetterHelperImpl(cTChartsheet, CHARTSHEET$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartsheet addNewChartsheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartsheet cTChartsheet = null;
            cTChartsheet = (CTChartsheet)((Object)this.get_store().add_element_user(CHARTSHEET$0));
            return cTChartsheet;
        }
    }
}

