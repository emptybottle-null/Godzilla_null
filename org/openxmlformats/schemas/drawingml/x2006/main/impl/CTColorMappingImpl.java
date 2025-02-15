/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.STColorSchemeIndex;

public class CTColorMappingImpl
extends XmlComplexContentImpl
implements CTColorMapping {
    private static final long serialVersionUID = 1L;
    private static final QName EXTLST$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName BG1$2 = new QName("", "bg1");
    private static final QName TX1$4 = new QName("", "tx1");
    private static final QName BG2$6 = new QName("", "bg2");
    private static final QName TX2$8 = new QName("", "tx2");
    private static final QName ACCENT1$10 = new QName("", "accent1");
    private static final QName ACCENT2$12 = new QName("", "accent2");
    private static final QName ACCENT3$14 = new QName("", "accent3");
    private static final QName ACCENT4$16 = new QName("", "accent4");
    private static final QName ACCENT5$18 = new QName("", "accent5");
    private static final QName ACCENT6$20 = new QName("", "accent6");
    private static final QName HLINK$22 = new QName("", "hlink");
    private static final QName FOLHLINK$24 = new QName("", "folHlink");

    public CTColorMappingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$0, 0));
            if (cTOfficeArtExtensionList == null) {
                return null;
            }
            return cTOfficeArtExtensionList;
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
            return this.get_store().count_elements(EXTLST$0) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$0));
            return cTOfficeArtExtensionList;
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
            this.get_store().remove_element(EXTLST$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getBg1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BG1$2));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetBg1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(BG1$2));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBg1(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BG1$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BG1$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBg1(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(BG1$2));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(BG1$2));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getTx1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TX1$4));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetTx1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(TX1$4));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTx1(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TX1$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TX1$4));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTx1(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(TX1$4));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(TX1$4));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getBg2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BG2$6));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetBg2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(BG2$6));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBg2(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BG2$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BG2$6));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBg2(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(BG2$6));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(BG2$6));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getTx2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TX2$8));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetTx2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(TX2$8));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTx2(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TX2$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TX2$8));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTx2(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(TX2$8));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(TX2$8));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getAccent1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT1$10));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetAccent1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT1$10));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAccent1(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT1$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ACCENT1$10));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAccent1(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT1$10));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(ACCENT1$10));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getAccent2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT2$12));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetAccent2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT2$12));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAccent2(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT2$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ACCENT2$12));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAccent2(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT2$12));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(ACCENT2$12));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getAccent3() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT3$14));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetAccent3() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT3$14));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAccent3(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT3$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ACCENT3$14));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAccent3(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT3$14));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(ACCENT3$14));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getAccent4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT4$16));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetAccent4() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT4$16));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAccent4(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT4$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ACCENT4$16));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAccent4(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT4$16));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(ACCENT4$16));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getAccent5() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT5$18));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetAccent5() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT5$18));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAccent5(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT5$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ACCENT5$18));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAccent5(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT5$18));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(ACCENT5$18));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getAccent6() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT6$20));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetAccent6() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT6$20));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAccent6(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ACCENT6$20));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ACCENT6$20));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAccent6(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(ACCENT6$20));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(ACCENT6$20));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getHlink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HLINK$22));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetHlink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(HLINK$22));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHlink(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HLINK$22));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HLINK$22));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHlink(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(HLINK$22));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(HLINK$22));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex.Enum getFolHlink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FOLHLINK$24));
            if (simpleValue == null) {
                return null;
            }
            return (STColorSchemeIndex.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorSchemeIndex xgetFolHlink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex = null;
            sTColorSchemeIndex = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(FOLHLINK$24));
            return sTColorSchemeIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFolHlink(STColorSchemeIndex.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FOLHLINK$24));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FOLHLINK$24));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFolHlink(STColorSchemeIndex sTColorSchemeIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorSchemeIndex sTColorSchemeIndex2 = null;
            sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().find_attribute_user(FOLHLINK$24));
            if (sTColorSchemeIndex2 == null) {
                sTColorSchemeIndex2 = (STColorSchemeIndex)((Object)this.get_store().add_attribute_user(FOLHLINK$24));
            }
            sTColorSchemeIndex2.set(sTColorSchemeIndex);
        }
    }
}

