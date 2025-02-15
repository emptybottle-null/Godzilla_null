/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.TriggerType
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import com.microsoft.schemas.office.visio.x2012.main.SheetType;
import com.microsoft.schemas.office.visio.x2012.main.TriggerType;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class SheetTypeImpl
extends XmlComplexContentImpl
implements SheetType {
    private static final long serialVersionUID = 1L;
    private static final QName CELL$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Cell");
    private static final QName TRIGGER$2 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Trigger");
    private static final QName SECTION$4 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Section");
    private static final QName LINESTYLE$6 = new QName("", "LineStyle");
    private static final QName FILLSTYLE$8 = new QName("", "FillStyle");
    private static final QName TEXTSTYLE$10 = new QName("", "TextStyle");

    public SheetTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CellType> getCellList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CellList
            extends AbstractList<CellType> {
                CellList() {
                }

                @Override
                public CellType get(int n) {
                    return SheetTypeImpl.this.getCellArray(n);
                }

                @Override
                public CellType set(int n, CellType cellType) {
                    CellType cellType2 = SheetTypeImpl.this.getCellArray(n);
                    SheetTypeImpl.this.setCellArray(n, cellType);
                    return cellType2;
                }

                @Override
                public void add(int n, CellType cellType) {
                    SheetTypeImpl.this.insertNewCell(n).set(cellType);
                }

                @Override
                public CellType remove(int n) {
                    CellType cellType = SheetTypeImpl.this.getCellArray(n);
                    SheetTypeImpl.this.removeCell(n);
                    return cellType;
                }

                @Override
                public int size() {
                    return SheetTypeImpl.this.sizeOfCellArray();
                }
            }
            return new CellList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CellType[] getCellArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CELL$0, arrayList);
            CellType[] cellTypeArray = new CellType[arrayList.size()];
            arrayList.toArray(cellTypeArray);
            return cellTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CellType getCellArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CellType cellType = null;
            cellType = (CellType)((Object)this.get_store().find_element_user(CELL$0, n));
            if (cellType == null) {
                throw new IndexOutOfBoundsException();
            }
            return cellType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCellArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CELL$0);
        }
    }

    @Override
    public void setCellArray(CellType[] cellTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cellTypeArray, CELL$0);
    }

    @Override
    public void setCellArray(int n, CellType cellType) {
        this.generatedSetterHelperImpl(cellType, CELL$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CellType insertNewCell(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CellType cellType = null;
            cellType = (CellType)((Object)this.get_store().insert_element_user(CELL$0, n));
            return cellType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CellType addNewCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CellType cellType = null;
            cellType = (CellType)((Object)this.get_store().add_element_user(CELL$0));
            return cellType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCell(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CELL$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<TriggerType> getTriggerList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TriggerList
            extends AbstractList<TriggerType> {
                TriggerList() {
                }

                @Override
                public TriggerType get(int n) {
                    return SheetTypeImpl.this.getTriggerArray(n);
                }

                @Override
                public TriggerType set(int n, TriggerType triggerType) {
                    TriggerType triggerType2 = SheetTypeImpl.this.getTriggerArray(n);
                    SheetTypeImpl.this.setTriggerArray(n, triggerType);
                    return triggerType2;
                }

                @Override
                public void add(int n, TriggerType triggerType) {
                    SheetTypeImpl.this.insertNewTrigger(n).set((XmlObject)triggerType);
                }

                @Override
                public TriggerType remove(int n) {
                    TriggerType triggerType = SheetTypeImpl.this.getTriggerArray(n);
                    SheetTypeImpl.this.removeTrigger(n);
                    return triggerType;
                }

                @Override
                public int size() {
                    return SheetTypeImpl.this.sizeOfTriggerArray();
                }
            }
            return new TriggerList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public TriggerType[] getTriggerArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TRIGGER$2, arrayList);
            TriggerType[] triggerTypeArray = new TriggerType[arrayList.size()];
            arrayList.toArray(triggerTypeArray);
            return triggerTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TriggerType getTriggerArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TriggerType triggerType = null;
            triggerType = (TriggerType)this.get_store().find_element_user(TRIGGER$2, n);
            if (triggerType == null) {
                throw new IndexOutOfBoundsException();
            }
            return triggerType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTriggerArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TRIGGER$2);
        }
    }

    @Override
    public void setTriggerArray(TriggerType[] triggerTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])triggerTypeArray, TRIGGER$2);
    }

    @Override
    public void setTriggerArray(int n, TriggerType triggerType) {
        this.generatedSetterHelperImpl((XmlObject)triggerType, TRIGGER$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TriggerType insertNewTrigger(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TriggerType triggerType = null;
            triggerType = (TriggerType)this.get_store().insert_element_user(TRIGGER$2, n);
            return triggerType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TriggerType addNewTrigger() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TriggerType triggerType = null;
            triggerType = (TriggerType)this.get_store().add_element_user(TRIGGER$2);
            return triggerType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTrigger(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TRIGGER$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<SectionType> getSectionList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SectionList
            extends AbstractList<SectionType> {
                SectionList() {
                }

                @Override
                public SectionType get(int n) {
                    return SheetTypeImpl.this.getSectionArray(n);
                }

                @Override
                public SectionType set(int n, SectionType sectionType) {
                    SectionType sectionType2 = SheetTypeImpl.this.getSectionArray(n);
                    SheetTypeImpl.this.setSectionArray(n, sectionType);
                    return sectionType2;
                }

                @Override
                public void add(int n, SectionType sectionType) {
                    SheetTypeImpl.this.insertNewSection(n).set(sectionType);
                }

                @Override
                public SectionType remove(int n) {
                    SectionType sectionType = SheetTypeImpl.this.getSectionArray(n);
                    SheetTypeImpl.this.removeSection(n);
                    return sectionType;
                }

                @Override
                public int size() {
                    return SheetTypeImpl.this.sizeOfSectionArray();
                }
            }
            return new SectionList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public SectionType[] getSectionArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SECTION$4, arrayList);
            SectionType[] sectionTypeArray = new SectionType[arrayList.size()];
            arrayList.toArray(sectionTypeArray);
            return sectionTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SectionType getSectionArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SectionType sectionType = null;
            sectionType = (SectionType)((Object)this.get_store().find_element_user(SECTION$4, n));
            if (sectionType == null) {
                throw new IndexOutOfBoundsException();
            }
            return sectionType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSectionArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SECTION$4);
        }
    }

    @Override
    public void setSectionArray(SectionType[] sectionTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(sectionTypeArray, SECTION$4);
    }

    @Override
    public void setSectionArray(int n, SectionType sectionType) {
        this.generatedSetterHelperImpl(sectionType, SECTION$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SectionType insertNewSection(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SectionType sectionType = null;
            sectionType = (SectionType)((Object)this.get_store().insert_element_user(SECTION$4, n));
            return sectionType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SectionType addNewSection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SectionType sectionType = null;
            sectionType = (SectionType)((Object)this.get_store().add_element_user(SECTION$4));
            return sectionType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSection(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SECTION$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getLineStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LINESTYLE$6));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetLineStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(LINESTYLE$6));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLineStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LINESTYLE$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLineStyle(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LINESTYLE$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LINESTYLE$6));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLineStyle(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(LINESTYLE$6));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(LINESTYLE$6));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLineStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LINESTYLE$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getFillStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLSTYLE$8));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetFillStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(FILLSTYLE$8));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFillStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FILLSTYLE$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFillStyle(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLSTYLE$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FILLSTYLE$8));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFillStyle(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(FILLSTYLE$8));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(FILLSTYLE$8));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFillStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FILLSTYLE$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TEXTSTYLE$10));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(TEXTSTYLE$10));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TEXTSTYLE$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTextStyle(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TEXTSTYLE$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TEXTSTYLE$10));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTextStyle(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(TEXTSTYLE$10));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(TEXTSTYLE$10));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TEXTSTYLE$10);
        }
    }
}

