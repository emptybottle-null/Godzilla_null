/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.TriggerType
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import com.microsoft.schemas.office.visio.x2012.main.TriggerType;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class SectionTypeImpl
extends XmlComplexContentImpl
implements SectionType {
    private static final long serialVersionUID = 1L;
    private static final QName CELL$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Cell");
    private static final QName TRIGGER$2 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Trigger");
    private static final QName ROW$4 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Row");
    private static final QName N$6 = new QName("", "N");
    private static final QName DEL$8 = new QName("", "Del");
    private static final QName IX$10 = new QName("", "IX");

    public SectionTypeImpl(SchemaType schemaType) {
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
                    return SectionTypeImpl.this.getCellArray(n);
                }

                @Override
                public CellType set(int n, CellType cellType) {
                    CellType cellType2 = SectionTypeImpl.this.getCellArray(n);
                    SectionTypeImpl.this.setCellArray(n, cellType);
                    return cellType2;
                }

                @Override
                public void add(int n, CellType cellType) {
                    SectionTypeImpl.this.insertNewCell(n).set(cellType);
                }

                @Override
                public CellType remove(int n) {
                    CellType cellType = SectionTypeImpl.this.getCellArray(n);
                    SectionTypeImpl.this.removeCell(n);
                    return cellType;
                }

                @Override
                public int size() {
                    return SectionTypeImpl.this.sizeOfCellArray();
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
                    return SectionTypeImpl.this.getTriggerArray(n);
                }

                @Override
                public TriggerType set(int n, TriggerType triggerType) {
                    TriggerType triggerType2 = SectionTypeImpl.this.getTriggerArray(n);
                    SectionTypeImpl.this.setTriggerArray(n, triggerType);
                    return triggerType2;
                }

                @Override
                public void add(int n, TriggerType triggerType) {
                    SectionTypeImpl.this.insertNewTrigger(n).set((XmlObject)triggerType);
                }

                @Override
                public TriggerType remove(int n) {
                    TriggerType triggerType = SectionTypeImpl.this.getTriggerArray(n);
                    SectionTypeImpl.this.removeTrigger(n);
                    return triggerType;
                }

                @Override
                public int size() {
                    return SectionTypeImpl.this.sizeOfTriggerArray();
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
    public List<RowType> getRowList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RowList
            extends AbstractList<RowType> {
                RowList() {
                }

                @Override
                public RowType get(int n) {
                    return SectionTypeImpl.this.getRowArray(n);
                }

                @Override
                public RowType set(int n, RowType rowType) {
                    RowType rowType2 = SectionTypeImpl.this.getRowArray(n);
                    SectionTypeImpl.this.setRowArray(n, rowType);
                    return rowType2;
                }

                @Override
                public void add(int n, RowType rowType) {
                    SectionTypeImpl.this.insertNewRow(n).set(rowType);
                }

                @Override
                public RowType remove(int n) {
                    RowType rowType = SectionTypeImpl.this.getRowArray(n);
                    SectionTypeImpl.this.removeRow(n);
                    return rowType;
                }

                @Override
                public int size() {
                    return SectionTypeImpl.this.sizeOfRowArray();
                }
            }
            return new RowList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public RowType[] getRowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ROW$4, arrayList);
            RowType[] rowTypeArray = new RowType[arrayList.size()];
            arrayList.toArray(rowTypeArray);
            return rowTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RowType getRowArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RowType rowType = null;
            rowType = (RowType)((Object)this.get_store().find_element_user(ROW$4, n));
            if (rowType == null) {
                throw new IndexOutOfBoundsException();
            }
            return rowType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ROW$4);
        }
    }

    @Override
    public void setRowArray(RowType[] rowTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(rowTypeArray, ROW$4);
    }

    @Override
    public void setRowArray(int n, RowType rowType) {
        this.generatedSetterHelperImpl(rowType, ROW$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RowType insertNewRow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RowType rowType = null;
            rowType = (RowType)((Object)this.get_store().insert_element_user(ROW$4, n));
            return rowType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RowType addNewRow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RowType rowType = null;
            rowType = (RowType)((Object)this.get_store().add_element_user(ROW$4));
            return rowType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ROW$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getN() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(N$6));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetN() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(N$6));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setN(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(N$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(N$6));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetN(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(N$6));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(N$6));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEL$8));
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(DEL$8));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DEL$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDel(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEL$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DEL$8));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDel(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(DEL$8));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(DEL$8));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DEL$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getIX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IX$10));
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
    public XmlUnsignedInt xgetIX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(IX$10));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetIX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(IX$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setIX(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IX$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(IX$10));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetIX(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(IX$10));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(IX$10));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetIX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(IX$10);
        }
    }
}

