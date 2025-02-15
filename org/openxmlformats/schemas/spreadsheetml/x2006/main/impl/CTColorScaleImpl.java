/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColorScale;

public class CTColorScaleImpl
extends XmlComplexContentImpl
implements CTColorScale {
    private static final long serialVersionUID = 1L;
    private static final QName CFVO$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "cfvo");
    private static final QName COLOR$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "color");

    public CTColorScaleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCfvo> getCfvoList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CfvoList
            extends AbstractList<CTCfvo> {
                CfvoList() {
                }

                @Override
                public CTCfvo get(int n) {
                    return CTColorScaleImpl.this.getCfvoArray(n);
                }

                @Override
                public CTCfvo set(int n, CTCfvo cTCfvo) {
                    CTCfvo cTCfvo2 = CTColorScaleImpl.this.getCfvoArray(n);
                    CTColorScaleImpl.this.setCfvoArray(n, cTCfvo);
                    return cTCfvo2;
                }

                @Override
                public void add(int n, CTCfvo cTCfvo) {
                    CTColorScaleImpl.this.insertNewCfvo(n).set(cTCfvo);
                }

                @Override
                public CTCfvo remove(int n) {
                    CTCfvo cTCfvo = CTColorScaleImpl.this.getCfvoArray(n);
                    CTColorScaleImpl.this.removeCfvo(n);
                    return cTCfvo;
                }

                @Override
                public int size() {
                    return CTColorScaleImpl.this.sizeOfCfvoArray();
                }
            }
            return new CfvoList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCfvo[] getCfvoArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CFVO$0, arrayList);
            CTCfvo[] cTCfvoArray = new CTCfvo[arrayList.size()];
            arrayList.toArray(cTCfvoArray);
            return cTCfvoArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCfvo getCfvoArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCfvo cTCfvo = null;
            cTCfvo = (CTCfvo)((Object)this.get_store().find_element_user(CFVO$0, n));
            if (cTCfvo == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCfvo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCfvoArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CFVO$0);
        }
    }

    @Override
    public void setCfvoArray(CTCfvo[] cTCfvoArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTCfvoArray, CFVO$0);
    }

    @Override
    public void setCfvoArray(int n, CTCfvo cTCfvo) {
        this.generatedSetterHelperImpl(cTCfvo, CFVO$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCfvo insertNewCfvo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCfvo cTCfvo = null;
            cTCfvo = (CTCfvo)((Object)this.get_store().insert_element_user(CFVO$0, n));
            return cTCfvo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCfvo addNewCfvo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCfvo cTCfvo = null;
            cTCfvo = (CTCfvo)((Object)this.get_store().add_element_user(CFVO$0));
            return cTCfvo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCfvo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CFVO$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTColor> getColorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ColorList
            extends AbstractList<CTColor> {
                ColorList() {
                }

                @Override
                public CTColor get(int n) {
                    return CTColorScaleImpl.this.getColorArray(n);
                }

                @Override
                public CTColor set(int n, CTColor cTColor) {
                    CTColor cTColor2 = CTColorScaleImpl.this.getColorArray(n);
                    CTColorScaleImpl.this.setColorArray(n, cTColor);
                    return cTColor2;
                }

                @Override
                public void add(int n, CTColor cTColor) {
                    CTColorScaleImpl.this.insertNewColor(n).set(cTColor);
                }

                @Override
                public CTColor remove(int n) {
                    CTColor cTColor = CTColorScaleImpl.this.getColorArray(n);
                    CTColorScaleImpl.this.removeColor(n);
                    return cTColor;
                }

                @Override
                public int size() {
                    return CTColorScaleImpl.this.sizeOfColorArray();
                }
            }
            return new ColorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTColor[] getColorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COLOR$2, arrayList);
            CTColor[] cTColorArray = new CTColor[arrayList.size()];
            arrayList.toArray(cTColorArray);
            return cTColorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor getColorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().find_element_user(COLOR$2, n));
            if (cTColor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfColorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLOR$2);
        }
    }

    @Override
    public void setColorArray(CTColor[] cTColorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTColorArray, COLOR$2);
    }

    @Override
    public void setColorArray(int n, CTColor cTColor) {
        this.generatedSetterHelperImpl(cTColor, COLOR$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor insertNewColor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().insert_element_user(COLOR$2, n));
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor addNewColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().add_element_user(COLOR$2));
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeColor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLOR$2, n);
        }
    }
}

