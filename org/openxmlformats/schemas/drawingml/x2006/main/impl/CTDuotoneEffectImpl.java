/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;

public class CTDuotoneEffectImpl
extends XmlComplexContentImpl
implements CTDuotoneEffect {
    private static final long serialVersionUID = 1L;
    private static final QName SCRGBCLR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "scrgbClr");
    private static final QName SRGBCLR$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "srgbClr");
    private static final QName HSLCLR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "hslClr");
    private static final QName SYSCLR$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "sysClr");
    private static final QName SCHEMECLR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "schemeClr");
    private static final QName PRSTCLR$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "prstClr");

    public CTDuotoneEffectImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTScRgbColor> getScrgbClrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ScrgbClrList
            extends AbstractList<CTScRgbColor> {
                ScrgbClrList() {
                }

                @Override
                public CTScRgbColor get(int n) {
                    return CTDuotoneEffectImpl.this.getScrgbClrArray(n);
                }

                @Override
                public CTScRgbColor set(int n, CTScRgbColor cTScRgbColor) {
                    CTScRgbColor cTScRgbColor2 = CTDuotoneEffectImpl.this.getScrgbClrArray(n);
                    CTDuotoneEffectImpl.this.setScrgbClrArray(n, cTScRgbColor);
                    return cTScRgbColor2;
                }

                @Override
                public void add(int n, CTScRgbColor cTScRgbColor) {
                    CTDuotoneEffectImpl.this.insertNewScrgbClr(n).set(cTScRgbColor);
                }

                @Override
                public CTScRgbColor remove(int n) {
                    CTScRgbColor cTScRgbColor = CTDuotoneEffectImpl.this.getScrgbClrArray(n);
                    CTDuotoneEffectImpl.this.removeScrgbClr(n);
                    return cTScRgbColor;
                }

                @Override
                public int size() {
                    return CTDuotoneEffectImpl.this.sizeOfScrgbClrArray();
                }
            }
            return new ScrgbClrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTScRgbColor[] getScrgbClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCRGBCLR$0, arrayList);
            CTScRgbColor[] cTScRgbColorArray = new CTScRgbColor[arrayList.size()];
            arrayList.toArray(cTScRgbColorArray);
            return cTScRgbColorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScRgbColor getScrgbClrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScRgbColor cTScRgbColor = null;
            cTScRgbColor = (CTScRgbColor)((Object)this.get_store().find_element_user(SCRGBCLR$0, n));
            if (cTScRgbColor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTScRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfScrgbClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCRGBCLR$0);
        }
    }

    @Override
    public void setScrgbClrArray(CTScRgbColor[] cTScRgbColorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTScRgbColorArray, SCRGBCLR$0);
    }

    @Override
    public void setScrgbClrArray(int n, CTScRgbColor cTScRgbColor) {
        this.generatedSetterHelperImpl(cTScRgbColor, SCRGBCLR$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScRgbColor insertNewScrgbClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScRgbColor cTScRgbColor = null;
            cTScRgbColor = (CTScRgbColor)((Object)this.get_store().insert_element_user(SCRGBCLR$0, n));
            return cTScRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScRgbColor addNewScrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScRgbColor cTScRgbColor = null;
            cTScRgbColor = (CTScRgbColor)((Object)this.get_store().add_element_user(SCRGBCLR$0));
            return cTScRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeScrgbClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCRGBCLR$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSRgbColor> getSrgbClrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SrgbClrList
            extends AbstractList<CTSRgbColor> {
                SrgbClrList() {
                }

                @Override
                public CTSRgbColor get(int n) {
                    return CTDuotoneEffectImpl.this.getSrgbClrArray(n);
                }

                @Override
                public CTSRgbColor set(int n, CTSRgbColor cTSRgbColor) {
                    CTSRgbColor cTSRgbColor2 = CTDuotoneEffectImpl.this.getSrgbClrArray(n);
                    CTDuotoneEffectImpl.this.setSrgbClrArray(n, cTSRgbColor);
                    return cTSRgbColor2;
                }

                @Override
                public void add(int n, CTSRgbColor cTSRgbColor) {
                    CTDuotoneEffectImpl.this.insertNewSrgbClr(n).set(cTSRgbColor);
                }

                @Override
                public CTSRgbColor remove(int n) {
                    CTSRgbColor cTSRgbColor = CTDuotoneEffectImpl.this.getSrgbClrArray(n);
                    CTDuotoneEffectImpl.this.removeSrgbClr(n);
                    return cTSRgbColor;
                }

                @Override
                public int size() {
                    return CTDuotoneEffectImpl.this.sizeOfSrgbClrArray();
                }
            }
            return new SrgbClrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSRgbColor[] getSrgbClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SRGBCLR$2, arrayList);
            CTSRgbColor[] cTSRgbColorArray = new CTSRgbColor[arrayList.size()];
            arrayList.toArray(cTSRgbColorArray);
            return cTSRgbColorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSRgbColor getSrgbClrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSRgbColor cTSRgbColor = null;
            cTSRgbColor = (CTSRgbColor)((Object)this.get_store().find_element_user(SRGBCLR$2, n));
            if (cTSRgbColor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSrgbClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SRGBCLR$2);
        }
    }

    @Override
    public void setSrgbClrArray(CTSRgbColor[] cTSRgbColorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSRgbColorArray, SRGBCLR$2);
    }

    @Override
    public void setSrgbClrArray(int n, CTSRgbColor cTSRgbColor) {
        this.generatedSetterHelperImpl(cTSRgbColor, SRGBCLR$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSRgbColor insertNewSrgbClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSRgbColor cTSRgbColor = null;
            cTSRgbColor = (CTSRgbColor)((Object)this.get_store().insert_element_user(SRGBCLR$2, n));
            return cTSRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSRgbColor addNewSrgbClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSRgbColor cTSRgbColor = null;
            cTSRgbColor = (CTSRgbColor)((Object)this.get_store().add_element_user(SRGBCLR$2));
            return cTSRgbColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSrgbClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SRGBCLR$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTHslColor> getHslClrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HslClrList
            extends AbstractList<CTHslColor> {
                HslClrList() {
                }

                @Override
                public CTHslColor get(int n) {
                    return CTDuotoneEffectImpl.this.getHslClrArray(n);
                }

                @Override
                public CTHslColor set(int n, CTHslColor cTHslColor) {
                    CTHslColor cTHslColor2 = CTDuotoneEffectImpl.this.getHslClrArray(n);
                    CTDuotoneEffectImpl.this.setHslClrArray(n, cTHslColor);
                    return cTHslColor2;
                }

                @Override
                public void add(int n, CTHslColor cTHslColor) {
                    CTDuotoneEffectImpl.this.insertNewHslClr(n).set(cTHslColor);
                }

                @Override
                public CTHslColor remove(int n) {
                    CTHslColor cTHslColor = CTDuotoneEffectImpl.this.getHslClrArray(n);
                    CTDuotoneEffectImpl.this.removeHslClr(n);
                    return cTHslColor;
                }

                @Override
                public int size() {
                    return CTDuotoneEffectImpl.this.sizeOfHslClrArray();
                }
            }
            return new HslClrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTHslColor[] getHslClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HSLCLR$4, arrayList);
            CTHslColor[] cTHslColorArray = new CTHslColor[arrayList.size()];
            arrayList.toArray(cTHslColorArray);
            return cTHslColorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHslColor getHslClrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHslColor cTHslColor = null;
            cTHslColor = (CTHslColor)((Object)this.get_store().find_element_user(HSLCLR$4, n));
            if (cTHslColor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTHslColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHslClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HSLCLR$4);
        }
    }

    @Override
    public void setHslClrArray(CTHslColor[] cTHslColorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTHslColorArray, HSLCLR$4);
    }

    @Override
    public void setHslClrArray(int n, CTHslColor cTHslColor) {
        this.generatedSetterHelperImpl(cTHslColor, HSLCLR$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHslColor insertNewHslClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHslColor cTHslColor = null;
            cTHslColor = (CTHslColor)((Object)this.get_store().insert_element_user(HSLCLR$4, n));
            return cTHslColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHslColor addNewHslClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHslColor cTHslColor = null;
            cTHslColor = (CTHslColor)((Object)this.get_store().add_element_user(HSLCLR$4));
            return cTHslColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHslClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HSLCLR$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSystemColor> getSysClrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SysClrList
            extends AbstractList<CTSystemColor> {
                SysClrList() {
                }

                @Override
                public CTSystemColor get(int n) {
                    return CTDuotoneEffectImpl.this.getSysClrArray(n);
                }

                @Override
                public CTSystemColor set(int n, CTSystemColor cTSystemColor) {
                    CTSystemColor cTSystemColor2 = CTDuotoneEffectImpl.this.getSysClrArray(n);
                    CTDuotoneEffectImpl.this.setSysClrArray(n, cTSystemColor);
                    return cTSystemColor2;
                }

                @Override
                public void add(int n, CTSystemColor cTSystemColor) {
                    CTDuotoneEffectImpl.this.insertNewSysClr(n).set(cTSystemColor);
                }

                @Override
                public CTSystemColor remove(int n) {
                    CTSystemColor cTSystemColor = CTDuotoneEffectImpl.this.getSysClrArray(n);
                    CTDuotoneEffectImpl.this.removeSysClr(n);
                    return cTSystemColor;
                }

                @Override
                public int size() {
                    return CTDuotoneEffectImpl.this.sizeOfSysClrArray();
                }
            }
            return new SysClrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSystemColor[] getSysClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SYSCLR$6, arrayList);
            CTSystemColor[] cTSystemColorArray = new CTSystemColor[arrayList.size()];
            arrayList.toArray(cTSystemColorArray);
            return cTSystemColorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSystemColor getSysClrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSystemColor cTSystemColor = null;
            cTSystemColor = (CTSystemColor)((Object)this.get_store().find_element_user(SYSCLR$6, n));
            if (cTSystemColor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSystemColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSysClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SYSCLR$6);
        }
    }

    @Override
    public void setSysClrArray(CTSystemColor[] cTSystemColorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSystemColorArray, SYSCLR$6);
    }

    @Override
    public void setSysClrArray(int n, CTSystemColor cTSystemColor) {
        this.generatedSetterHelperImpl(cTSystemColor, SYSCLR$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSystemColor insertNewSysClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSystemColor cTSystemColor = null;
            cTSystemColor = (CTSystemColor)((Object)this.get_store().insert_element_user(SYSCLR$6, n));
            return cTSystemColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSystemColor addNewSysClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSystemColor cTSystemColor = null;
            cTSystemColor = (CTSystemColor)((Object)this.get_store().add_element_user(SYSCLR$6));
            return cTSystemColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSysClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SYSCLR$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSchemeColor> getSchemeClrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SchemeClrList
            extends AbstractList<CTSchemeColor> {
                SchemeClrList() {
                }

                @Override
                public CTSchemeColor get(int n) {
                    return CTDuotoneEffectImpl.this.getSchemeClrArray(n);
                }

                @Override
                public CTSchemeColor set(int n, CTSchemeColor cTSchemeColor) {
                    CTSchemeColor cTSchemeColor2 = CTDuotoneEffectImpl.this.getSchemeClrArray(n);
                    CTDuotoneEffectImpl.this.setSchemeClrArray(n, cTSchemeColor);
                    return cTSchemeColor2;
                }

                @Override
                public void add(int n, CTSchemeColor cTSchemeColor) {
                    CTDuotoneEffectImpl.this.insertNewSchemeClr(n).set(cTSchemeColor);
                }

                @Override
                public CTSchemeColor remove(int n) {
                    CTSchemeColor cTSchemeColor = CTDuotoneEffectImpl.this.getSchemeClrArray(n);
                    CTDuotoneEffectImpl.this.removeSchemeClr(n);
                    return cTSchemeColor;
                }

                @Override
                public int size() {
                    return CTDuotoneEffectImpl.this.sizeOfSchemeClrArray();
                }
            }
            return new SchemeClrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSchemeColor[] getSchemeClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCHEMECLR$8, arrayList);
            CTSchemeColor[] cTSchemeColorArray = new CTSchemeColor[arrayList.size()];
            arrayList.toArray(cTSchemeColorArray);
            return cTSchemeColorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchemeColor getSchemeClrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchemeColor cTSchemeColor = null;
            cTSchemeColor = (CTSchemeColor)((Object)this.get_store().find_element_user(SCHEMECLR$8, n));
            if (cTSchemeColor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSchemeColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSchemeClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCHEMECLR$8);
        }
    }

    @Override
    public void setSchemeClrArray(CTSchemeColor[] cTSchemeColorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSchemeColorArray, SCHEMECLR$8);
    }

    @Override
    public void setSchemeClrArray(int n, CTSchemeColor cTSchemeColor) {
        this.generatedSetterHelperImpl(cTSchemeColor, SCHEMECLR$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchemeColor insertNewSchemeClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchemeColor cTSchemeColor = null;
            cTSchemeColor = (CTSchemeColor)((Object)this.get_store().insert_element_user(SCHEMECLR$8, n));
            return cTSchemeColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchemeColor addNewSchemeClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchemeColor cTSchemeColor = null;
            cTSchemeColor = (CTSchemeColor)((Object)this.get_store().add_element_user(SCHEMECLR$8));
            return cTSchemeColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSchemeClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCHEMECLR$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPresetColor> getPrstClrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PrstClrList
            extends AbstractList<CTPresetColor> {
                PrstClrList() {
                }

                @Override
                public CTPresetColor get(int n) {
                    return CTDuotoneEffectImpl.this.getPrstClrArray(n);
                }

                @Override
                public CTPresetColor set(int n, CTPresetColor cTPresetColor) {
                    CTPresetColor cTPresetColor2 = CTDuotoneEffectImpl.this.getPrstClrArray(n);
                    CTDuotoneEffectImpl.this.setPrstClrArray(n, cTPresetColor);
                    return cTPresetColor2;
                }

                @Override
                public void add(int n, CTPresetColor cTPresetColor) {
                    CTDuotoneEffectImpl.this.insertNewPrstClr(n).set(cTPresetColor);
                }

                @Override
                public CTPresetColor remove(int n) {
                    CTPresetColor cTPresetColor = CTDuotoneEffectImpl.this.getPrstClrArray(n);
                    CTDuotoneEffectImpl.this.removePrstClr(n);
                    return cTPresetColor;
                }

                @Override
                public int size() {
                    return CTDuotoneEffectImpl.this.sizeOfPrstClrArray();
                }
            }
            return new PrstClrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPresetColor[] getPrstClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PRSTCLR$10, arrayList);
            CTPresetColor[] cTPresetColorArray = new CTPresetColor[arrayList.size()];
            arrayList.toArray(cTPresetColorArray);
            return cTPresetColorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetColor getPrstClrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetColor cTPresetColor = null;
            cTPresetColor = (CTPresetColor)((Object)this.get_store().find_element_user(PRSTCLR$10, n));
            if (cTPresetColor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPresetColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPrstClrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRSTCLR$10);
        }
    }

    @Override
    public void setPrstClrArray(CTPresetColor[] cTPresetColorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPresetColorArray, PRSTCLR$10);
    }

    @Override
    public void setPrstClrArray(int n, CTPresetColor cTPresetColor) {
        this.generatedSetterHelperImpl(cTPresetColor, PRSTCLR$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetColor insertNewPrstClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetColor cTPresetColor = null;
            cTPresetColor = (CTPresetColor)((Object)this.get_store().insert_element_user(PRSTCLR$10, n));
            return cTPresetColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetColor addNewPrstClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetColor cTPresetColor = null;
            cTPresetColor = (CTPresetColor)((Object)this.get_store().add_element_user(PRSTCLR$10));
            return cTPresetColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePrstClr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRSTCLR$10, n);
        }
    }
}

