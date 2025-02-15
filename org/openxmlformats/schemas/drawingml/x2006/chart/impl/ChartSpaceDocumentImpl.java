/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument;

public class ChartSpaceDocumentImpl
extends XmlComplexContentImpl
implements ChartSpaceDocument {
    private static final long serialVersionUID = 1L;
    private static final QName CHARTSPACE$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "chartSpace");

    public ChartSpaceDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartSpace getChartSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartSpace cTChartSpace = null;
            cTChartSpace = (CTChartSpace)((Object)this.get_store().find_element_user(CHARTSPACE$0, 0));
            if (cTChartSpace == null) {
                return null;
            }
            return cTChartSpace;
        }
    }

    @Override
    public void setChartSpace(CTChartSpace cTChartSpace) {
        this.generatedSetterHelperImpl(cTChartSpace, CHARTSPACE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartSpace addNewChartSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartSpace cTChartSpace = null;
            cTChartSpace = (CTChartSpace)((Object)this.get_store().add_element_user(CHARTSPACE$0));
            return cTChartSpace;
        }
    }
}

