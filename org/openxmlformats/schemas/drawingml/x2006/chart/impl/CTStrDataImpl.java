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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrVal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public class CTStrDataImpl
extends XmlComplexContentImpl
implements CTStrData {
    private static final long serialVersionUID = 1L;
    private static final QName PTCOUNT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "ptCount");
    private static final QName PT$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "pt");
    private static final QName EXTLST$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTStrDataImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt getPtCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().find_element_user(PTCOUNT$0, 0));
            if (cTUnsignedInt == null) {
                return null;
            }
            return cTUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPtCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PTCOUNT$0) != 0;
        }
    }

    @Override
    public void setPtCount(CTUnsignedInt cTUnsignedInt) {
        this.generatedSetterHelperImpl(cTUnsignedInt, PTCOUNT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt addNewPtCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().add_element_user(PTCOUNT$0));
            return cTUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPtCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PTCOUNT$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTStrVal> getPtList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PtList
            extends AbstractList<CTStrVal> {
                PtList() {
                }

                @Override
                public CTStrVal get(int n) {
                    return CTStrDataImpl.this.getPtArray(n);
                }

                @Override
                public CTStrVal set(int n, CTStrVal cTStrVal) {
                    CTStrVal cTStrVal2 = CTStrDataImpl.this.getPtArray(n);
                    CTStrDataImpl.this.setPtArray(n, cTStrVal);
                    return cTStrVal2;
                }

                @Override
                public void add(int n, CTStrVal cTStrVal) {
                    CTStrDataImpl.this.insertNewPt(n).set(cTStrVal);
                }

                @Override
                public CTStrVal remove(int n) {
                    CTStrVal cTStrVal = CTStrDataImpl.this.getPtArray(n);
                    CTStrDataImpl.this.removePt(n);
                    return cTStrVal;
                }

                @Override
                public int size() {
                    return CTStrDataImpl.this.sizeOfPtArray();
                }
            }
            return new PtList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTStrVal[] getPtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PT$2, arrayList);
            CTStrVal[] cTStrValArray = new CTStrVal[arrayList.size()];
            arrayList.toArray(cTStrValArray);
            return cTStrValArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrVal getPtArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrVal cTStrVal = null;
            cTStrVal = (CTStrVal)((Object)this.get_store().find_element_user(PT$2, n));
            if (cTStrVal == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTStrVal;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PT$2);
        }
    }

    @Override
    public void setPtArray(CTStrVal[] cTStrValArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTStrValArray, PT$2);
    }

    @Override
    public void setPtArray(int n, CTStrVal cTStrVal) {
        this.generatedSetterHelperImpl(cTStrVal, PT$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrVal insertNewPt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrVal cTStrVal = null;
            cTStrVal = (CTStrVal)((Object)this.get_store().insert_element_user(PT$2, n));
            return cTStrVal;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrVal addNewPt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrVal cTStrVal = null;
            cTStrVal = (CTStrVal)((Object)this.get_store().add_element_user(PT$2));
            return cTStrVal;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PT$2, n);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$4, 0));
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
            return this.get_store().count_elements(EXTLST$4) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$4, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$4));
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
            this.get_store().remove_element(EXTLST$4, 0);
        }
    }
}

