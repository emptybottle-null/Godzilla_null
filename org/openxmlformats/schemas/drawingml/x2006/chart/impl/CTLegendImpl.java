/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendEntry;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendPos;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class CTLegendImpl
extends XmlComplexContentImpl
implements CTLegend {
    private static final long serialVersionUID = 1L;
    private static final QName LEGENDPOS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "legendPos");
    private static final QName LEGENDENTRY$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "legendEntry");
    private static final QName LAYOUT$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "layout");
    private static final QName OVERLAY$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "overlay");
    private static final QName SPPR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "spPr");
    private static final QName TXPR$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "txPr");
    private static final QName EXTLST$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTLegendImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLegendPos getLegendPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLegendPos cTLegendPos = null;
            cTLegendPos = (CTLegendPos)((Object)this.get_store().find_element_user(LEGENDPOS$0, 0));
            if (cTLegendPos == null) {
                return null;
            }
            return cTLegendPos;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLegendPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LEGENDPOS$0) != 0;
        }
    }

    @Override
    public void setLegendPos(CTLegendPos cTLegendPos) {
        this.generatedSetterHelperImpl(cTLegendPos, LEGENDPOS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLegendPos addNewLegendPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLegendPos cTLegendPos = null;
            cTLegendPos = (CTLegendPos)((Object)this.get_store().add_element_user(LEGENDPOS$0));
            return cTLegendPos;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLegendPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LEGENDPOS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTLegendEntry> getLegendEntryList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LegendEntryList
            extends AbstractList<CTLegendEntry> {
                LegendEntryList() {
                }

                @Override
                public CTLegendEntry get(int n) {
                    return CTLegendImpl.this.getLegendEntryArray(n);
                }

                @Override
                public CTLegendEntry set(int n, CTLegendEntry cTLegendEntry) {
                    CTLegendEntry cTLegendEntry2 = CTLegendImpl.this.getLegendEntryArray(n);
                    CTLegendImpl.this.setLegendEntryArray(n, cTLegendEntry);
                    return cTLegendEntry2;
                }

                @Override
                public void add(int n, CTLegendEntry cTLegendEntry) {
                    CTLegendImpl.this.insertNewLegendEntry(n).set(cTLegendEntry);
                }

                @Override
                public CTLegendEntry remove(int n) {
                    CTLegendEntry cTLegendEntry = CTLegendImpl.this.getLegendEntryArray(n);
                    CTLegendImpl.this.removeLegendEntry(n);
                    return cTLegendEntry;
                }

                @Override
                public int size() {
                    return CTLegendImpl.this.sizeOfLegendEntryArray();
                }
            }
            return new LegendEntryList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTLegendEntry[] getLegendEntryArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LEGENDENTRY$2, arrayList);
            CTLegendEntry[] cTLegendEntryArray = new CTLegendEntry[arrayList.size()];
            arrayList.toArray(cTLegendEntryArray);
            return cTLegendEntryArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLegendEntry getLegendEntryArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLegendEntry cTLegendEntry = null;
            cTLegendEntry = (CTLegendEntry)((Object)this.get_store().find_element_user(LEGENDENTRY$2, n));
            if (cTLegendEntry == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLegendEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLegendEntryArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LEGENDENTRY$2);
        }
    }

    @Override
    public void setLegendEntryArray(CTLegendEntry[] cTLegendEntryArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTLegendEntryArray, LEGENDENTRY$2);
    }

    @Override
    public void setLegendEntryArray(int n, CTLegendEntry cTLegendEntry) {
        this.generatedSetterHelperImpl(cTLegendEntry, LEGENDENTRY$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLegendEntry insertNewLegendEntry(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLegendEntry cTLegendEntry = null;
            cTLegendEntry = (CTLegendEntry)((Object)this.get_store().insert_element_user(LEGENDENTRY$2, n));
            return cTLegendEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLegendEntry addNewLegendEntry() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLegendEntry cTLegendEntry = null;
            cTLegendEntry = (CTLegendEntry)((Object)this.get_store().add_element_user(LEGENDENTRY$2));
            return cTLegendEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLegendEntry(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LEGENDENTRY$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayout getLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayout cTLayout = null;
            cTLayout = (CTLayout)((Object)this.get_store().find_element_user(LAYOUT$4, 0));
            if (cTLayout == null) {
                return null;
            }
            return cTLayout;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LAYOUT$4) != 0;
        }
    }

    @Override
    public void setLayout(CTLayout cTLayout) {
        this.generatedSetterHelperImpl(cTLayout, LAYOUT$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLayout addNewLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLayout cTLayout = null;
            cTLayout = (CTLayout)((Object)this.get_store().add_element_user(LAYOUT$4));
            return cTLayout;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LAYOUT$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getOverlay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(OVERLAY$6, 0));
            if (cTBoolean == null) {
                return null;
            }
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOverlay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OVERLAY$6) != 0;
        }
    }

    @Override
    public void setOverlay(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, OVERLAY$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewOverlay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(OVERLAY$6));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOverlay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OVERLAY$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties getSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().find_element_user(SPPR$8, 0));
            if (cTShapeProperties == null) {
                return null;
            }
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPPR$8) != 0;
        }
    }

    @Override
    public void setSpPr(CTShapeProperties cTShapeProperties) {
        this.generatedSetterHelperImpl(cTShapeProperties, SPPR$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties addNewSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().add_element_user(SPPR$8));
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPPR$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBody getTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBody cTTextBody = null;
            cTTextBody = (CTTextBody)((Object)this.get_store().find_element_user(TXPR$10, 0));
            if (cTTextBody == null) {
                return null;
            }
            return cTTextBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TXPR$10) != 0;
        }
    }

    @Override
    public void setTxPr(CTTextBody cTTextBody) {
        this.generatedSetterHelperImpl(cTTextBody, TXPR$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBody addNewTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBody cTTextBody = null;
            cTTextBody = (CTTextBody)((Object)this.get_store().add_element_user(TXPR$10));
            return cTTextBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TXPR$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$12, 0));
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$12) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$12));
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$12, 0);
        }
    }
}

