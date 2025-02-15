/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.excel.STCF
 */
package com.microsoft.schemas.office.excel.impl;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.excel.STCF;
import com.microsoft.schemas.office.excel.STObjectType;
import com.microsoft.schemas.office.excel.STTrueFalseBlank;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTClientDataImpl
extends XmlComplexContentImpl
implements CTClientData {
    private static final long serialVersionUID = 1L;
    private static final QName MOVEWITHCELLS$0 = new QName("urn:schemas-microsoft-com:office:excel", "MoveWithCells");
    private static final QName SIZEWITHCELLS$2 = new QName("urn:schemas-microsoft-com:office:excel", "SizeWithCells");
    private static final QName ANCHOR$4 = new QName("urn:schemas-microsoft-com:office:excel", "Anchor");
    private static final QName LOCKED$6 = new QName("urn:schemas-microsoft-com:office:excel", "Locked");
    private static final QName DEFAULTSIZE$8 = new QName("urn:schemas-microsoft-com:office:excel", "DefaultSize");
    private static final QName PRINTOBJECT$10 = new QName("urn:schemas-microsoft-com:office:excel", "PrintObject");
    private static final QName DISABLED$12 = new QName("urn:schemas-microsoft-com:office:excel", "Disabled");
    private static final QName AUTOFILL$14 = new QName("urn:schemas-microsoft-com:office:excel", "AutoFill");
    private static final QName AUTOLINE$16 = new QName("urn:schemas-microsoft-com:office:excel", "AutoLine");
    private static final QName AUTOPICT$18 = new QName("urn:schemas-microsoft-com:office:excel", "AutoPict");
    private static final QName FMLAMACRO$20 = new QName("urn:schemas-microsoft-com:office:excel", "FmlaMacro");
    private static final QName TEXTHALIGN$22 = new QName("urn:schemas-microsoft-com:office:excel", "TextHAlign");
    private static final QName TEXTVALIGN$24 = new QName("urn:schemas-microsoft-com:office:excel", "TextVAlign");
    private static final QName LOCKTEXT$26 = new QName("urn:schemas-microsoft-com:office:excel", "LockText");
    private static final QName JUSTLASTX$28 = new QName("urn:schemas-microsoft-com:office:excel", "JustLastX");
    private static final QName SECRETEDIT$30 = new QName("urn:schemas-microsoft-com:office:excel", "SecretEdit");
    private static final QName DEFAULT$32 = new QName("urn:schemas-microsoft-com:office:excel", "Default");
    private static final QName HELP$34 = new QName("urn:schemas-microsoft-com:office:excel", "Help");
    private static final QName CANCEL$36 = new QName("urn:schemas-microsoft-com:office:excel", "Cancel");
    private static final QName DISMISS$38 = new QName("urn:schemas-microsoft-com:office:excel", "Dismiss");
    private static final QName ACCEL$40 = new QName("urn:schemas-microsoft-com:office:excel", "Accel");
    private static final QName ACCEL2$42 = new QName("urn:schemas-microsoft-com:office:excel", "Accel2");
    private static final QName ROW$44 = new QName("urn:schemas-microsoft-com:office:excel", "Row");
    private static final QName COLUMN$46 = new QName("urn:schemas-microsoft-com:office:excel", "Column");
    private static final QName VISIBLE$48 = new QName("urn:schemas-microsoft-com:office:excel", "Visible");
    private static final QName ROWHIDDEN$50 = new QName("urn:schemas-microsoft-com:office:excel", "RowHidden");
    private static final QName COLHIDDEN$52 = new QName("urn:schemas-microsoft-com:office:excel", "ColHidden");
    private static final QName VTEDIT$54 = new QName("urn:schemas-microsoft-com:office:excel", "VTEdit");
    private static final QName MULTILINE$56 = new QName("urn:schemas-microsoft-com:office:excel", "MultiLine");
    private static final QName VSCROLL$58 = new QName("urn:schemas-microsoft-com:office:excel", "VScroll");
    private static final QName VALIDIDS$60 = new QName("urn:schemas-microsoft-com:office:excel", "ValidIds");
    private static final QName FMLARANGE$62 = new QName("urn:schemas-microsoft-com:office:excel", "FmlaRange");
    private static final QName WIDTHMIN$64 = new QName("urn:schemas-microsoft-com:office:excel", "WidthMin");
    private static final QName SEL$66 = new QName("urn:schemas-microsoft-com:office:excel", "Sel");
    private static final QName NOTHREED2$68 = new QName("urn:schemas-microsoft-com:office:excel", "NoThreeD2");
    private static final QName SELTYPE$70 = new QName("urn:schemas-microsoft-com:office:excel", "SelType");
    private static final QName MULTISEL$72 = new QName("urn:schemas-microsoft-com:office:excel", "MultiSel");
    private static final QName LCT$74 = new QName("urn:schemas-microsoft-com:office:excel", "LCT");
    private static final QName LISTITEM$76 = new QName("urn:schemas-microsoft-com:office:excel", "ListItem");
    private static final QName DROPSTYLE$78 = new QName("urn:schemas-microsoft-com:office:excel", "DropStyle");
    private static final QName COLORED$80 = new QName("urn:schemas-microsoft-com:office:excel", "Colored");
    private static final QName DROPLINES$82 = new QName("urn:schemas-microsoft-com:office:excel", "DropLines");
    private static final QName CHECKED$84 = new QName("urn:schemas-microsoft-com:office:excel", "Checked");
    private static final QName FMLALINK$86 = new QName("urn:schemas-microsoft-com:office:excel", "FmlaLink");
    private static final QName FMLAPICT$88 = new QName("urn:schemas-microsoft-com:office:excel", "FmlaPict");
    private static final QName NOTHREED$90 = new QName("urn:schemas-microsoft-com:office:excel", "NoThreeD");
    private static final QName FIRSTBUTTON$92 = new QName("urn:schemas-microsoft-com:office:excel", "FirstButton");
    private static final QName FMLAGROUP$94 = new QName("urn:schemas-microsoft-com:office:excel", "FmlaGroup");
    private static final QName VAL$96 = new QName("urn:schemas-microsoft-com:office:excel", "Val");
    private static final QName MIN$98 = new QName("urn:schemas-microsoft-com:office:excel", "Min");
    private static final QName MAX$100 = new QName("urn:schemas-microsoft-com:office:excel", "Max");
    private static final QName INC$102 = new QName("urn:schemas-microsoft-com:office:excel", "Inc");
    private static final QName PAGE$104 = new QName("urn:schemas-microsoft-com:office:excel", "Page");
    private static final QName HORIZ$106 = new QName("urn:schemas-microsoft-com:office:excel", "Horiz");
    private static final QName DX$108 = new QName("urn:schemas-microsoft-com:office:excel", "Dx");
    private static final QName MAPOCX$110 = new QName("urn:schemas-microsoft-com:office:excel", "MapOCX");
    private static final QName CF$112 = new QName("urn:schemas-microsoft-com:office:excel", "CF");
    private static final QName CAMERA$114 = new QName("urn:schemas-microsoft-com:office:excel", "Camera");
    private static final QName RECALCALWAYS$116 = new QName("urn:schemas-microsoft-com:office:excel", "RecalcAlways");
    private static final QName AUTOSCALE$118 = new QName("urn:schemas-microsoft-com:office:excel", "AutoScale");
    private static final QName DDE$120 = new QName("urn:schemas-microsoft-com:office:excel", "DDE");
    private static final QName UIOBJ$122 = new QName("urn:schemas-microsoft-com:office:excel", "UIObj");
    private static final QName SCRIPTTEXT$124 = new QName("urn:schemas-microsoft-com:office:excel", "ScriptText");
    private static final QName SCRIPTEXTENDED$126 = new QName("urn:schemas-microsoft-com:office:excel", "ScriptExtended");
    private static final QName SCRIPTLANGUAGE$128 = new QName("urn:schemas-microsoft-com:office:excel", "ScriptLanguage");
    private static final QName SCRIPTLOCATION$130 = new QName("urn:schemas-microsoft-com:office:excel", "ScriptLocation");
    private static final QName FMLATXBX$132 = new QName("urn:schemas-microsoft-com:office:excel", "FmlaTxbx");
    private static final QName OBJECTTYPE$134 = new QName("", "ObjectType");

    public CTClientDataImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getMoveWithCellsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MoveWithCellsList
            extends AbstractList<STTrueFalseBlank.Enum> {
                MoveWithCellsList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getMoveWithCellsArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getMoveWithCellsArray(n);
                    CTClientDataImpl.this.setMoveWithCellsArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertMoveWithCells(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getMoveWithCellsArray(n);
                    CTClientDataImpl.this.removeMoveWithCells(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMoveWithCellsArray();
                }
            }
            return new MoveWithCellsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getMoveWithCellsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MOVEWITHCELLS$0, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getMoveWithCellsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MOVEWITHCELLS$0, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetMoveWithCellsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MoveWithCellsList
            extends AbstractList<STTrueFalseBlank> {
                MoveWithCellsList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetMoveWithCellsArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetMoveWithCellsArray(n);
                    CTClientDataImpl.this.xsetMoveWithCellsArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewMoveWithCells(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetMoveWithCellsArray(n);
                    CTClientDataImpl.this.removeMoveWithCells(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMoveWithCellsArray();
                }
            }
            return new MoveWithCellsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetMoveWithCellsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MOVEWITHCELLS$0, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetMoveWithCellsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(MOVEWITHCELLS$0, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMoveWithCellsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MOVEWITHCELLS$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMoveWithCellsArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, MOVEWITHCELLS$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMoveWithCellsArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MOVEWITHCELLS$0, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMoveWithCellsArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, MOVEWITHCELLS$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMoveWithCellsArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(MOVEWITHCELLS$0, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertMoveWithCells(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(MOVEWITHCELLS$0, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addMoveWithCells(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(MOVEWITHCELLS$0));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewMoveWithCells(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(MOVEWITHCELLS$0, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewMoveWithCells() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(MOVEWITHCELLS$0));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMoveWithCells(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MOVEWITHCELLS$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getSizeWithCellsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SizeWithCellsList
            extends AbstractList<STTrueFalseBlank.Enum> {
                SizeWithCellsList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getSizeWithCellsArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getSizeWithCellsArray(n);
                    CTClientDataImpl.this.setSizeWithCellsArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertSizeWithCells(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getSizeWithCellsArray(n);
                    CTClientDataImpl.this.removeSizeWithCells(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfSizeWithCellsArray();
                }
            }
            return new SizeWithCellsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getSizeWithCellsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SIZEWITHCELLS$2, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getSizeWithCellsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIZEWITHCELLS$2, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetSizeWithCellsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SizeWithCellsList
            extends AbstractList<STTrueFalseBlank> {
                SizeWithCellsList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetSizeWithCellsArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetSizeWithCellsArray(n);
                    CTClientDataImpl.this.xsetSizeWithCellsArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewSizeWithCells(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetSizeWithCellsArray(n);
                    CTClientDataImpl.this.removeSizeWithCells(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfSizeWithCellsArray();
                }
            }
            return new SizeWithCellsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetSizeWithCellsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SIZEWITHCELLS$2, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetSizeWithCellsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(SIZEWITHCELLS$2, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSizeWithCellsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIZEWITHCELLS$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSizeWithCellsArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, SIZEWITHCELLS$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSizeWithCellsArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SIZEWITHCELLS$2, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSizeWithCellsArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, SIZEWITHCELLS$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSizeWithCellsArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(SIZEWITHCELLS$2, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertSizeWithCells(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(SIZEWITHCELLS$2, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addSizeWithCells(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SIZEWITHCELLS$2));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewSizeWithCells(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(SIZEWITHCELLS$2, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewSizeWithCells() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(SIZEWITHCELLS$2));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSizeWithCells(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIZEWITHCELLS$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getAnchorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AnchorList
            extends AbstractList<String> {
                AnchorList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getAnchorArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getAnchorArray(n);
                    CTClientDataImpl.this.setAnchorArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertAnchor(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getAnchorArray(n);
                    CTClientDataImpl.this.removeAnchor(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAnchorArray();
                }
            }
            return new AnchorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getAnchorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ANCHOR$4, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getAnchorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ANCHOR$4, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetAnchorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AnchorList
            extends AbstractList<XmlString> {
                AnchorList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetAnchorArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetAnchorArray(n);
                    CTClientDataImpl.this.xsetAnchorArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewAnchor(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetAnchorArray(n);
                    CTClientDataImpl.this.removeAnchor(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAnchorArray();
                }
            }
            return new AnchorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetAnchorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ANCHOR$4, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetAnchorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(ANCHOR$4, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAnchorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ANCHOR$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAnchorArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, ANCHOR$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAnchorArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ANCHOR$4, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAnchorArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, ANCHOR$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAnchorArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(ANCHOR$4, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAnchor(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(ANCHOR$4, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addAnchor(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(ANCHOR$4));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewAnchor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(ANCHOR$4, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewAnchor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(ANCHOR$4));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAnchor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ANCHOR$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getLockedList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LockedList
            extends AbstractList<STTrueFalseBlank.Enum> {
                LockedList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getLockedArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getLockedArray(n);
                    CTClientDataImpl.this.setLockedArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertLocked(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getLockedArray(n);
                    CTClientDataImpl.this.removeLocked(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfLockedArray();
                }
            }
            return new LockedList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getLockedArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LOCKED$6, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getLockedArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LOCKED$6, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetLockedList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LockedList
            extends AbstractList<STTrueFalseBlank> {
                LockedList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetLockedArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetLockedArray(n);
                    CTClientDataImpl.this.xsetLockedArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewLocked(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetLockedArray(n);
                    CTClientDataImpl.this.removeLocked(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfLockedArray();
                }
            }
            return new LockedList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetLockedArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LOCKED$6, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetLockedArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(LOCKED$6, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLockedArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LOCKED$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLockedArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, LOCKED$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLockedArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LOCKED$6, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLockedArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, LOCKED$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLockedArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(LOCKED$6, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertLocked(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(LOCKED$6, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addLocked(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(LOCKED$6));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewLocked(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(LOCKED$6, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewLocked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(LOCKED$6));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLocked(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LOCKED$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getDefaultSizeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DefaultSizeList
            extends AbstractList<STTrueFalseBlank.Enum> {
                DefaultSizeList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getDefaultSizeArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getDefaultSizeArray(n);
                    CTClientDataImpl.this.setDefaultSizeArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertDefaultSize(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getDefaultSizeArray(n);
                    CTClientDataImpl.this.removeDefaultSize(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDefaultSizeArray();
                }
            }
            return new DefaultSizeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getDefaultSizeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DEFAULTSIZE$8, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getDefaultSizeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DEFAULTSIZE$8, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetDefaultSizeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DefaultSizeList
            extends AbstractList<STTrueFalseBlank> {
                DefaultSizeList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetDefaultSizeArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetDefaultSizeArray(n);
                    CTClientDataImpl.this.xsetDefaultSizeArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewDefaultSize(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetDefaultSizeArray(n);
                    CTClientDataImpl.this.removeDefaultSize(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDefaultSizeArray();
                }
            }
            return new DefaultSizeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetDefaultSizeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DEFAULTSIZE$8, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetDefaultSizeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(DEFAULTSIZE$8, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDefaultSizeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEFAULTSIZE$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefaultSizeArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, DEFAULTSIZE$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefaultSizeArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DEFAULTSIZE$8, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDefaultSizeArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, DEFAULTSIZE$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDefaultSizeArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(DEFAULTSIZE$8, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertDefaultSize(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(DEFAULTSIZE$8, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addDefaultSize(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DEFAULTSIZE$8));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewDefaultSize(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(DEFAULTSIZE$8, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewDefaultSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(DEFAULTSIZE$8));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDefaultSize(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEFAULTSIZE$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getPrintObjectList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PrintObjectList
            extends AbstractList<STTrueFalseBlank.Enum> {
                PrintObjectList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getPrintObjectArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getPrintObjectArray(n);
                    CTClientDataImpl.this.setPrintObjectArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertPrintObject(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getPrintObjectArray(n);
                    CTClientDataImpl.this.removePrintObject(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfPrintObjectArray();
                }
            }
            return new PrintObjectList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getPrintObjectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PRINTOBJECT$10, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getPrintObjectArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(PRINTOBJECT$10, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetPrintObjectList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PrintObjectList
            extends AbstractList<STTrueFalseBlank> {
                PrintObjectList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetPrintObjectArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetPrintObjectArray(n);
                    CTClientDataImpl.this.xsetPrintObjectArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewPrintObject(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetPrintObjectArray(n);
                    CTClientDataImpl.this.removePrintObject(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfPrintObjectArray();
                }
            }
            return new PrintObjectList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetPrintObjectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PRINTOBJECT$10, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetPrintObjectArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(PRINTOBJECT$10, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPrintObjectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRINTOBJECT$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPrintObjectArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, PRINTOBJECT$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPrintObjectArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(PRINTOBJECT$10, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPrintObjectArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, PRINTOBJECT$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPrintObjectArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(PRINTOBJECT$10, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertPrintObject(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(PRINTOBJECT$10, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addPrintObject(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(PRINTOBJECT$10));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewPrintObject(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(PRINTOBJECT$10, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewPrintObject() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(PRINTOBJECT$10));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePrintObject(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRINTOBJECT$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getDisabledList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DisabledList
            extends AbstractList<STTrueFalseBlank.Enum> {
                DisabledList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getDisabledArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getDisabledArray(n);
                    CTClientDataImpl.this.setDisabledArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertDisabled(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getDisabledArray(n);
                    CTClientDataImpl.this.removeDisabled(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDisabledArray();
                }
            }
            return new DisabledList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getDisabledArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DISABLED$12, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getDisabledArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DISABLED$12, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetDisabledList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DisabledList
            extends AbstractList<STTrueFalseBlank> {
                DisabledList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetDisabledArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetDisabledArray(n);
                    CTClientDataImpl.this.xsetDisabledArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewDisabled(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetDisabledArray(n);
                    CTClientDataImpl.this.removeDisabled(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDisabledArray();
                }
            }
            return new DisabledList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetDisabledArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DISABLED$12, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetDisabledArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(DISABLED$12, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDisabledArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DISABLED$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDisabledArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, DISABLED$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDisabledArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DISABLED$12, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDisabledArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, DISABLED$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDisabledArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(DISABLED$12, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertDisabled(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(DISABLED$12, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addDisabled(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DISABLED$12));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewDisabled(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(DISABLED$12, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewDisabled() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(DISABLED$12));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDisabled(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DISABLED$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getAutoFillList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AutoFillList
            extends AbstractList<STTrueFalseBlank.Enum> {
                AutoFillList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getAutoFillArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getAutoFillArray(n);
                    CTClientDataImpl.this.setAutoFillArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertAutoFill(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getAutoFillArray(n);
                    CTClientDataImpl.this.removeAutoFill(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAutoFillArray();
                }
            }
            return new AutoFillList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getAutoFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AUTOFILL$14, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getAutoFillArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(AUTOFILL$14, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetAutoFillList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AutoFillList
            extends AbstractList<STTrueFalseBlank> {
                AutoFillList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetAutoFillArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetAutoFillArray(n);
                    CTClientDataImpl.this.xsetAutoFillArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewAutoFill(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetAutoFillArray(n);
                    CTClientDataImpl.this.removeAutoFill(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAutoFillArray();
                }
            }
            return new AutoFillList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetAutoFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AUTOFILL$14, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetAutoFillArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(AUTOFILL$14, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAutoFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOFILL$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAutoFillArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, AUTOFILL$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAutoFillArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(AUTOFILL$14, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAutoFillArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, AUTOFILL$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAutoFillArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(AUTOFILL$14, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAutoFill(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(AUTOFILL$14, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addAutoFill(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(AUTOFILL$14));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewAutoFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(AUTOFILL$14, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewAutoFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(AUTOFILL$14));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAutoFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOFILL$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getAutoLineList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AutoLineList
            extends AbstractList<STTrueFalseBlank.Enum> {
                AutoLineList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getAutoLineArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getAutoLineArray(n);
                    CTClientDataImpl.this.setAutoLineArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertAutoLine(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getAutoLineArray(n);
                    CTClientDataImpl.this.removeAutoLine(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAutoLineArray();
                }
            }
            return new AutoLineList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getAutoLineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AUTOLINE$16, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getAutoLineArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(AUTOLINE$16, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetAutoLineList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AutoLineList
            extends AbstractList<STTrueFalseBlank> {
                AutoLineList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetAutoLineArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetAutoLineArray(n);
                    CTClientDataImpl.this.xsetAutoLineArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewAutoLine(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetAutoLineArray(n);
                    CTClientDataImpl.this.removeAutoLine(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAutoLineArray();
                }
            }
            return new AutoLineList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetAutoLineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AUTOLINE$16, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetAutoLineArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(AUTOLINE$16, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAutoLineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOLINE$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAutoLineArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, AUTOLINE$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAutoLineArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(AUTOLINE$16, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAutoLineArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, AUTOLINE$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAutoLineArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(AUTOLINE$16, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAutoLine(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(AUTOLINE$16, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addAutoLine(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(AUTOLINE$16));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewAutoLine(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(AUTOLINE$16, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewAutoLine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(AUTOLINE$16));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAutoLine(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOLINE$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getAutoPictList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AutoPictList
            extends AbstractList<STTrueFalseBlank.Enum> {
                AutoPictList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getAutoPictArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getAutoPictArray(n);
                    CTClientDataImpl.this.setAutoPictArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertAutoPict(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getAutoPictArray(n);
                    CTClientDataImpl.this.removeAutoPict(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAutoPictArray();
                }
            }
            return new AutoPictList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getAutoPictArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AUTOPICT$18, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getAutoPictArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(AUTOPICT$18, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetAutoPictList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AutoPictList
            extends AbstractList<STTrueFalseBlank> {
                AutoPictList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetAutoPictArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetAutoPictArray(n);
                    CTClientDataImpl.this.xsetAutoPictArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewAutoPict(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetAutoPictArray(n);
                    CTClientDataImpl.this.removeAutoPict(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAutoPictArray();
                }
            }
            return new AutoPictList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetAutoPictArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AUTOPICT$18, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetAutoPictArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(AUTOPICT$18, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAutoPictArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOPICT$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAutoPictArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, AUTOPICT$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAutoPictArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(AUTOPICT$18, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAutoPictArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, AUTOPICT$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAutoPictArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(AUTOPICT$18, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAutoPict(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(AUTOPICT$18, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addAutoPict(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(AUTOPICT$18));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewAutoPict(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(AUTOPICT$18, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewAutoPict() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(AUTOPICT$18));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAutoPict(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOPICT$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getFmlaMacroList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaMacroList
            extends AbstractList<String> {
                FmlaMacroList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getFmlaMacroArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getFmlaMacroArray(n);
                    CTClientDataImpl.this.setFmlaMacroArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertFmlaMacro(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getFmlaMacroArray(n);
                    CTClientDataImpl.this.removeFmlaMacro(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaMacroArray();
                }
            }
            return new FmlaMacroList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getFmlaMacroArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLAMACRO$20, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFmlaMacroArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLAMACRO$20, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetFmlaMacroList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaMacroList
            extends AbstractList<XmlString> {
                FmlaMacroList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetFmlaMacroArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetFmlaMacroArray(n);
                    CTClientDataImpl.this.xsetFmlaMacroArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewFmlaMacro(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetFmlaMacroArray(n);
                    CTClientDataImpl.this.removeFmlaMacro(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaMacroArray();
                }
            }
            return new FmlaMacroList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetFmlaMacroArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLAMACRO$20, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetFmlaMacroArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(FMLAMACRO$20, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFmlaMacroArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FMLAMACRO$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaMacroArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, FMLAMACRO$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaMacroArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLAMACRO$20, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaMacroArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, FMLAMACRO$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaMacroArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(FMLAMACRO$20, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertFmlaMacro(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(FMLAMACRO$20, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addFmlaMacro(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(FMLAMACRO$20));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewFmlaMacro(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(FMLAMACRO$20, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewFmlaMacro() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(FMLAMACRO$20));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFmlaMacro(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FMLAMACRO$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getTextHAlignList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TextHAlignList
            extends AbstractList<String> {
                TextHAlignList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getTextHAlignArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getTextHAlignArray(n);
                    CTClientDataImpl.this.setTextHAlignArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertTextHAlign(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getTextHAlignArray(n);
                    CTClientDataImpl.this.removeTextHAlign(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfTextHAlignArray();
                }
            }
            return new TextHAlignList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getTextHAlignArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TEXTHALIGN$22, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTextHAlignArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(TEXTHALIGN$22, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetTextHAlignList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TextHAlignList
            extends AbstractList<XmlString> {
                TextHAlignList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetTextHAlignArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetTextHAlignArray(n);
                    CTClientDataImpl.this.xsetTextHAlignArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewTextHAlign(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetTextHAlignArray(n);
                    CTClientDataImpl.this.removeTextHAlign(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfTextHAlignArray();
                }
            }
            return new TextHAlignList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetTextHAlignArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TEXTHALIGN$22, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetTextHAlignArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(TEXTHALIGN$22, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTextHAlignArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEXTHALIGN$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTextHAlignArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, TEXTHALIGN$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTextHAlignArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(TEXTHALIGN$22, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTextHAlignArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, TEXTHALIGN$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTextHAlignArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(TEXTHALIGN$22, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertTextHAlign(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(TEXTHALIGN$22, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addTextHAlign(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(TEXTHALIGN$22));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewTextHAlign(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(TEXTHALIGN$22, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewTextHAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(TEXTHALIGN$22));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTextHAlign(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEXTHALIGN$22, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getTextVAlignList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TextVAlignList
            extends AbstractList<String> {
                TextVAlignList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getTextVAlignArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getTextVAlignArray(n);
                    CTClientDataImpl.this.setTextVAlignArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertTextVAlign(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getTextVAlignArray(n);
                    CTClientDataImpl.this.removeTextVAlign(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfTextVAlignArray();
                }
            }
            return new TextVAlignList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getTextVAlignArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TEXTVALIGN$24, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTextVAlignArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(TEXTVALIGN$24, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetTextVAlignList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TextVAlignList
            extends AbstractList<XmlString> {
                TextVAlignList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetTextVAlignArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetTextVAlignArray(n);
                    CTClientDataImpl.this.xsetTextVAlignArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewTextVAlign(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetTextVAlignArray(n);
                    CTClientDataImpl.this.removeTextVAlign(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfTextVAlignArray();
                }
            }
            return new TextVAlignList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetTextVAlignArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TEXTVALIGN$24, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetTextVAlignArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(TEXTVALIGN$24, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTextVAlignArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEXTVALIGN$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTextVAlignArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, TEXTVALIGN$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTextVAlignArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(TEXTVALIGN$24, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTextVAlignArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, TEXTVALIGN$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTextVAlignArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(TEXTVALIGN$24, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertTextVAlign(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(TEXTVALIGN$24, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addTextVAlign(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(TEXTVALIGN$24));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewTextVAlign(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(TEXTVALIGN$24, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewTextVAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(TEXTVALIGN$24));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTextVAlign(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEXTVALIGN$24, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getLockTextList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LockTextList
            extends AbstractList<STTrueFalseBlank.Enum> {
                LockTextList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getLockTextArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getLockTextArray(n);
                    CTClientDataImpl.this.setLockTextArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertLockText(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getLockTextArray(n);
                    CTClientDataImpl.this.removeLockText(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfLockTextArray();
                }
            }
            return new LockTextList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getLockTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LOCKTEXT$26, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getLockTextArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LOCKTEXT$26, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetLockTextList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LockTextList
            extends AbstractList<STTrueFalseBlank> {
                LockTextList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetLockTextArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetLockTextArray(n);
                    CTClientDataImpl.this.xsetLockTextArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewLockText(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetLockTextArray(n);
                    CTClientDataImpl.this.removeLockText(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfLockTextArray();
                }
            }
            return new LockTextList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetLockTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LOCKTEXT$26, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetLockTextArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(LOCKTEXT$26, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLockTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LOCKTEXT$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLockTextArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, LOCKTEXT$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLockTextArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LOCKTEXT$26, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLockTextArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, LOCKTEXT$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLockTextArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(LOCKTEXT$26, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertLockText(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(LOCKTEXT$26, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addLockText(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(LOCKTEXT$26));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewLockText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(LOCKTEXT$26, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewLockText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(LOCKTEXT$26));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLockText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LOCKTEXT$26, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getJustLastXList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class JustLastXList
            extends AbstractList<STTrueFalseBlank.Enum> {
                JustLastXList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getJustLastXArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getJustLastXArray(n);
                    CTClientDataImpl.this.setJustLastXArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertJustLastX(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getJustLastXArray(n);
                    CTClientDataImpl.this.removeJustLastX(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfJustLastXArray();
                }
            }
            return new JustLastXList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getJustLastXArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(JUSTLASTX$28, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getJustLastXArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(JUSTLASTX$28, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetJustLastXList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class JustLastXList
            extends AbstractList<STTrueFalseBlank> {
                JustLastXList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetJustLastXArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetJustLastXArray(n);
                    CTClientDataImpl.this.xsetJustLastXArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewJustLastX(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetJustLastXArray(n);
                    CTClientDataImpl.this.removeJustLastX(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfJustLastXArray();
                }
            }
            return new JustLastXList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetJustLastXArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(JUSTLASTX$28, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetJustLastXArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(JUSTLASTX$28, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfJustLastXArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(JUSTLASTX$28);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setJustLastXArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, JUSTLASTX$28);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setJustLastXArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(JUSTLASTX$28, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetJustLastXArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, JUSTLASTX$28);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetJustLastXArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(JUSTLASTX$28, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertJustLastX(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(JUSTLASTX$28, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addJustLastX(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(JUSTLASTX$28));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewJustLastX(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(JUSTLASTX$28, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewJustLastX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(JUSTLASTX$28));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeJustLastX(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(JUSTLASTX$28, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getSecretEditList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SecretEditList
            extends AbstractList<STTrueFalseBlank.Enum> {
                SecretEditList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getSecretEditArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getSecretEditArray(n);
                    CTClientDataImpl.this.setSecretEditArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertSecretEdit(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getSecretEditArray(n);
                    CTClientDataImpl.this.removeSecretEdit(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfSecretEditArray();
                }
            }
            return new SecretEditList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getSecretEditArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SECRETEDIT$30, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getSecretEditArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SECRETEDIT$30, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetSecretEditList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SecretEditList
            extends AbstractList<STTrueFalseBlank> {
                SecretEditList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetSecretEditArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetSecretEditArray(n);
                    CTClientDataImpl.this.xsetSecretEditArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewSecretEdit(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetSecretEditArray(n);
                    CTClientDataImpl.this.removeSecretEdit(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfSecretEditArray();
                }
            }
            return new SecretEditList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetSecretEditArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SECRETEDIT$30, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetSecretEditArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(SECRETEDIT$30, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSecretEditArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SECRETEDIT$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSecretEditArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, SECRETEDIT$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSecretEditArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SECRETEDIT$30, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSecretEditArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, SECRETEDIT$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSecretEditArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(SECRETEDIT$30, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertSecretEdit(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(SECRETEDIT$30, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addSecretEdit(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SECRETEDIT$30));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewSecretEdit(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(SECRETEDIT$30, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewSecretEdit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(SECRETEDIT$30));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSecretEdit(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SECRETEDIT$30, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getDefaultList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DefaultList
            extends AbstractList<STTrueFalseBlank.Enum> {
                DefaultList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getDefaultArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getDefaultArray(n);
                    CTClientDataImpl.this.setDefaultArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertDefault(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getDefaultArray(n);
                    CTClientDataImpl.this.removeDefault(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDefaultArray();
                }
            }
            return new DefaultList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getDefaultArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DEFAULT$32, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getDefaultArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DEFAULT$32, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetDefaultList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DefaultList
            extends AbstractList<STTrueFalseBlank> {
                DefaultList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetDefaultArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetDefaultArray(n);
                    CTClientDataImpl.this.xsetDefaultArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewDefault(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetDefaultArray(n);
                    CTClientDataImpl.this.removeDefault(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDefaultArray();
                }
            }
            return new DefaultList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetDefaultArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DEFAULT$32, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetDefaultArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(DEFAULT$32, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDefaultArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEFAULT$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefaultArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, DEFAULT$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefaultArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DEFAULT$32, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDefaultArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, DEFAULT$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDefaultArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(DEFAULT$32, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertDefault(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(DEFAULT$32, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addDefault(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DEFAULT$32));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewDefault(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(DEFAULT$32, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(DEFAULT$32));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDefault(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEFAULT$32, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getHelpList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HelpList
            extends AbstractList<STTrueFalseBlank.Enum> {
                HelpList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getHelpArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getHelpArray(n);
                    CTClientDataImpl.this.setHelpArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertHelp(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getHelpArray(n);
                    CTClientDataImpl.this.removeHelp(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfHelpArray();
                }
            }
            return new HelpList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getHelpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HELP$34, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getHelpArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(HELP$34, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetHelpList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HelpList
            extends AbstractList<STTrueFalseBlank> {
                HelpList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetHelpArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetHelpArray(n);
                    CTClientDataImpl.this.xsetHelpArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewHelp(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetHelpArray(n);
                    CTClientDataImpl.this.removeHelp(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfHelpArray();
                }
            }
            return new HelpList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetHelpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HELP$34, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetHelpArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(HELP$34, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHelpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HELP$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHelpArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, HELP$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHelpArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(HELP$34, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHelpArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, HELP$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHelpArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(HELP$34, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertHelp(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(HELP$34, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addHelp(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(HELP$34));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewHelp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(HELP$34, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewHelp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(HELP$34));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHelp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HELP$34, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getCancelList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CancelList
            extends AbstractList<STTrueFalseBlank.Enum> {
                CancelList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getCancelArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getCancelArray(n);
                    CTClientDataImpl.this.setCancelArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertCancel(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getCancelArray(n);
                    CTClientDataImpl.this.removeCancel(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfCancelArray();
                }
            }
            return new CancelList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getCancelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CANCEL$36, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getCancelArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CANCEL$36, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetCancelList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CancelList
            extends AbstractList<STTrueFalseBlank> {
                CancelList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetCancelArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetCancelArray(n);
                    CTClientDataImpl.this.xsetCancelArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewCancel(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetCancelArray(n);
                    CTClientDataImpl.this.removeCancel(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfCancelArray();
                }
            }
            return new CancelList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetCancelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CANCEL$36, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetCancelArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(CANCEL$36, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCancelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CANCEL$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCancelArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, CANCEL$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCancelArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CANCEL$36, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCancelArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, CANCEL$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCancelArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(CANCEL$36, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertCancel(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(CANCEL$36, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addCancel(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(CANCEL$36));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewCancel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(CANCEL$36, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewCancel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(CANCEL$36));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCancel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CANCEL$36, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getDismissList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DismissList
            extends AbstractList<STTrueFalseBlank.Enum> {
                DismissList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getDismissArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getDismissArray(n);
                    CTClientDataImpl.this.setDismissArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertDismiss(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getDismissArray(n);
                    CTClientDataImpl.this.removeDismiss(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDismissArray();
                }
            }
            return new DismissList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getDismissArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DISMISS$38, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getDismissArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DISMISS$38, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetDismissList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DismissList
            extends AbstractList<STTrueFalseBlank> {
                DismissList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetDismissArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetDismissArray(n);
                    CTClientDataImpl.this.xsetDismissArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewDismiss(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetDismissArray(n);
                    CTClientDataImpl.this.removeDismiss(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDismissArray();
                }
            }
            return new DismissList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetDismissArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DISMISS$38, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetDismissArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(DISMISS$38, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDismissArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DISMISS$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDismissArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, DISMISS$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDismissArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DISMISS$38, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDismissArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, DISMISS$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDismissArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(DISMISS$38, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertDismiss(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(DISMISS$38, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addDismiss(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DISMISS$38));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewDismiss(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(DISMISS$38, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewDismiss() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(DISMISS$38));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDismiss(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DISMISS$38, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getAccelList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AccelList
            extends AbstractList<BigInteger> {
                AccelList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getAccelArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getAccelArray(n);
                    CTClientDataImpl.this.setAccelArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertAccel(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getAccelArray(n);
                    CTClientDataImpl.this.removeAccel(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAccelArray();
                }
            }
            return new AccelList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getAccelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ACCEL$40, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getAccelArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ACCEL$40, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetAccelList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AccelList
            extends AbstractList<XmlInteger> {
                AccelList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetAccelArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetAccelArray(n);
                    CTClientDataImpl.this.xsetAccelArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewAccel(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetAccelArray(n);
                    CTClientDataImpl.this.removeAccel(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAccelArray();
                }
            }
            return new AccelList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetAccelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ACCEL$40, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetAccelArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(ACCEL$40, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAccelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ACCEL$40);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAccelArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, ACCEL$40);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAccelArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ACCEL$40, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAccelArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, ACCEL$40);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAccelArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(ACCEL$40, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAccel(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(ACCEL$40, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addAccel(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(ACCEL$40));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewAccel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(ACCEL$40, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewAccel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(ACCEL$40));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAccel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ACCEL$40, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getAccel2List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Accel2List
            extends AbstractList<BigInteger> {
                Accel2List() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getAccel2Array(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getAccel2Array(n);
                    CTClientDataImpl.this.setAccel2Array(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertAccel2(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getAccel2Array(n);
                    CTClientDataImpl.this.removeAccel2(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAccel2Array();
                }
            }
            return new Accel2List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getAccel2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ACCEL2$42, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getAccel2Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ACCEL2$42, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetAccel2List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class Accel2List
            extends AbstractList<XmlInteger> {
                Accel2List() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetAccel2Array(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetAccel2Array(n);
                    CTClientDataImpl.this.xsetAccel2Array(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewAccel2(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetAccel2Array(n);
                    CTClientDataImpl.this.removeAccel2(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAccel2Array();
                }
            }
            return new Accel2List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetAccel2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ACCEL2$42, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetAccel2Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(ACCEL2$42, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAccel2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ACCEL2$42);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAccel2Array(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, ACCEL2$42);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAccel2Array(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ACCEL2$42, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAccel2Array(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, ACCEL2$42);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAccel2Array(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(ACCEL2$42, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAccel2(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(ACCEL2$42, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addAccel2(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(ACCEL2$42));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewAccel2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(ACCEL2$42, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewAccel2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(ACCEL2$42));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAccel2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ACCEL2$42, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getRowList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RowList
            extends AbstractList<BigInteger> {
                RowList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getRowArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getRowArray(n);
                    CTClientDataImpl.this.setRowArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertRow(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getRowArray(n);
                    CTClientDataImpl.this.removeRow(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfRowArray();
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
    public BigInteger[] getRowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ROW$44, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getRowArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ROW$44, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetRowList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RowList
            extends AbstractList<XmlInteger> {
                RowList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetRowArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetRowArray(n);
                    CTClientDataImpl.this.xsetRowArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewRow(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetRowArray(n);
                    CTClientDataImpl.this.removeRow(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfRowArray();
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
    public XmlInteger[] xgetRowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ROW$44, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetRowArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(ROW$44, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
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
            return this.get_store().count_elements(ROW$44);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRowArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, ROW$44);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRowArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ROW$44, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRowArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, ROW$44);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRowArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(ROW$44, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertRow(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(ROW$44, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addRow(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(ROW$44));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewRow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(ROW$44, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewRow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(ROW$44));
            return xmlInteger;
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
            this.get_store().remove_element(ROW$44, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getColumnList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ColumnList
            extends AbstractList<BigInteger> {
                ColumnList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getColumnArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getColumnArray(n);
                    CTClientDataImpl.this.setColumnArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertColumn(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getColumnArray(n);
                    CTClientDataImpl.this.removeColumn(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfColumnArray();
                }
            }
            return new ColumnList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getColumnArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COLUMN$46, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getColumnArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(COLUMN$46, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetColumnList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ColumnList
            extends AbstractList<XmlInteger> {
                ColumnList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetColumnArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetColumnArray(n);
                    CTClientDataImpl.this.xsetColumnArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewColumn(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetColumnArray(n);
                    CTClientDataImpl.this.removeColumn(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfColumnArray();
                }
            }
            return new ColumnList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetColumnArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COLUMN$46, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetColumnArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(COLUMN$46, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfColumnArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLUMN$46);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setColumnArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, COLUMN$46);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setColumnArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(COLUMN$46, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetColumnArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, COLUMN$46);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetColumnArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(COLUMN$46, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertColumn(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(COLUMN$46, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addColumn(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(COLUMN$46));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewColumn(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(COLUMN$46, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewColumn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(COLUMN$46));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeColumn(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLUMN$46, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getVisibleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class VisibleList
            extends AbstractList<STTrueFalseBlank.Enum> {
                VisibleList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getVisibleArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getVisibleArray(n);
                    CTClientDataImpl.this.setVisibleArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertVisible(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getVisibleArray(n);
                    CTClientDataImpl.this.removeVisible(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfVisibleArray();
                }
            }
            return new VisibleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getVisibleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VISIBLE$48, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getVisibleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VISIBLE$48, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetVisibleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class VisibleList
            extends AbstractList<STTrueFalseBlank> {
                VisibleList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetVisibleArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetVisibleArray(n);
                    CTClientDataImpl.this.xsetVisibleArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewVisible(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetVisibleArray(n);
                    CTClientDataImpl.this.removeVisible(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfVisibleArray();
                }
            }
            return new VisibleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetVisibleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VISIBLE$48, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetVisibleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(VISIBLE$48, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfVisibleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VISIBLE$48);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVisibleArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, VISIBLE$48);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVisibleArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VISIBLE$48, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVisibleArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, VISIBLE$48);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVisibleArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(VISIBLE$48, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertVisible(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(VISIBLE$48, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addVisible(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(VISIBLE$48));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewVisible(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(VISIBLE$48, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewVisible() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(VISIBLE$48));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeVisible(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VISIBLE$48, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getRowHiddenList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RowHiddenList
            extends AbstractList<STTrueFalseBlank.Enum> {
                RowHiddenList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getRowHiddenArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getRowHiddenArray(n);
                    CTClientDataImpl.this.setRowHiddenArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertRowHidden(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getRowHiddenArray(n);
                    CTClientDataImpl.this.removeRowHidden(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfRowHiddenArray();
                }
            }
            return new RowHiddenList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getRowHiddenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ROWHIDDEN$50, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getRowHiddenArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ROWHIDDEN$50, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetRowHiddenList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RowHiddenList
            extends AbstractList<STTrueFalseBlank> {
                RowHiddenList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetRowHiddenArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetRowHiddenArray(n);
                    CTClientDataImpl.this.xsetRowHiddenArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewRowHidden(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetRowHiddenArray(n);
                    CTClientDataImpl.this.removeRowHidden(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfRowHiddenArray();
                }
            }
            return new RowHiddenList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetRowHiddenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ROWHIDDEN$50, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetRowHiddenArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(ROWHIDDEN$50, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRowHiddenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ROWHIDDEN$50);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRowHiddenArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, ROWHIDDEN$50);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRowHiddenArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(ROWHIDDEN$50, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRowHiddenArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, ROWHIDDEN$50);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRowHiddenArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(ROWHIDDEN$50, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertRowHidden(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(ROWHIDDEN$50, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addRowHidden(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(ROWHIDDEN$50));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewRowHidden(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(ROWHIDDEN$50, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewRowHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(ROWHIDDEN$50));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRowHidden(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ROWHIDDEN$50, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getColHiddenList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ColHiddenList
            extends AbstractList<STTrueFalseBlank.Enum> {
                ColHiddenList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getColHiddenArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getColHiddenArray(n);
                    CTClientDataImpl.this.setColHiddenArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertColHidden(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getColHiddenArray(n);
                    CTClientDataImpl.this.removeColHidden(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfColHiddenArray();
                }
            }
            return new ColHiddenList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getColHiddenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COLHIDDEN$52, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getColHiddenArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(COLHIDDEN$52, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetColHiddenList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ColHiddenList
            extends AbstractList<STTrueFalseBlank> {
                ColHiddenList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetColHiddenArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetColHiddenArray(n);
                    CTClientDataImpl.this.xsetColHiddenArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewColHidden(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetColHiddenArray(n);
                    CTClientDataImpl.this.removeColHidden(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfColHiddenArray();
                }
            }
            return new ColHiddenList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetColHiddenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COLHIDDEN$52, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetColHiddenArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(COLHIDDEN$52, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfColHiddenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLHIDDEN$52);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setColHiddenArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, COLHIDDEN$52);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setColHiddenArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(COLHIDDEN$52, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetColHiddenArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, COLHIDDEN$52);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetColHiddenArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(COLHIDDEN$52, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertColHidden(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(COLHIDDEN$52, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addColHidden(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(COLHIDDEN$52));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewColHidden(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(COLHIDDEN$52, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewColHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(COLHIDDEN$52));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeColHidden(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLHIDDEN$52, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getVTEditList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class VTEditList
            extends AbstractList<BigInteger> {
                VTEditList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getVTEditArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getVTEditArray(n);
                    CTClientDataImpl.this.setVTEditArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertVTEdit(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getVTEditArray(n);
                    CTClientDataImpl.this.removeVTEdit(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfVTEditArray();
                }
            }
            return new VTEditList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getVTEditArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VTEDIT$54, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getVTEditArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VTEDIT$54, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetVTEditList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class VTEditList
            extends AbstractList<XmlInteger> {
                VTEditList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetVTEditArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetVTEditArray(n);
                    CTClientDataImpl.this.xsetVTEditArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewVTEdit(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetVTEditArray(n);
                    CTClientDataImpl.this.removeVTEdit(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfVTEditArray();
                }
            }
            return new VTEditList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetVTEditArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VTEDIT$54, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetVTEditArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(VTEDIT$54, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfVTEditArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VTEDIT$54);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVTEditArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, VTEDIT$54);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVTEditArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VTEDIT$54, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVTEditArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, VTEDIT$54);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVTEditArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(VTEDIT$54, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertVTEdit(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(VTEDIT$54, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addVTEdit(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(VTEDIT$54));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewVTEdit(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(VTEDIT$54, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewVTEdit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(VTEDIT$54));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeVTEdit(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VTEDIT$54, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getMultiLineList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MultiLineList
            extends AbstractList<STTrueFalseBlank.Enum> {
                MultiLineList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getMultiLineArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getMultiLineArray(n);
                    CTClientDataImpl.this.setMultiLineArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertMultiLine(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getMultiLineArray(n);
                    CTClientDataImpl.this.removeMultiLine(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMultiLineArray();
                }
            }
            return new MultiLineList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getMultiLineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MULTILINE$56, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getMultiLineArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MULTILINE$56, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetMultiLineList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MultiLineList
            extends AbstractList<STTrueFalseBlank> {
                MultiLineList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetMultiLineArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetMultiLineArray(n);
                    CTClientDataImpl.this.xsetMultiLineArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewMultiLine(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetMultiLineArray(n);
                    CTClientDataImpl.this.removeMultiLine(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMultiLineArray();
                }
            }
            return new MultiLineList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetMultiLineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MULTILINE$56, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetMultiLineArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(MULTILINE$56, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMultiLineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MULTILINE$56);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMultiLineArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, MULTILINE$56);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMultiLineArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MULTILINE$56, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMultiLineArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, MULTILINE$56);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMultiLineArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(MULTILINE$56, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertMultiLine(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(MULTILINE$56, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addMultiLine(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(MULTILINE$56));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewMultiLine(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(MULTILINE$56, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewMultiLine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(MULTILINE$56));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMultiLine(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MULTILINE$56, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getVScrollList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class VScrollList
            extends AbstractList<STTrueFalseBlank.Enum> {
                VScrollList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getVScrollArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getVScrollArray(n);
                    CTClientDataImpl.this.setVScrollArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertVScroll(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getVScrollArray(n);
                    CTClientDataImpl.this.removeVScroll(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfVScrollArray();
                }
            }
            return new VScrollList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getVScrollArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VSCROLL$58, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getVScrollArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VSCROLL$58, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetVScrollList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class VScrollList
            extends AbstractList<STTrueFalseBlank> {
                VScrollList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetVScrollArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetVScrollArray(n);
                    CTClientDataImpl.this.xsetVScrollArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewVScroll(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetVScrollArray(n);
                    CTClientDataImpl.this.removeVScroll(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfVScrollArray();
                }
            }
            return new VScrollList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetVScrollArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VSCROLL$58, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetVScrollArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(VSCROLL$58, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfVScrollArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VSCROLL$58);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVScrollArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, VSCROLL$58);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVScrollArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VSCROLL$58, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVScrollArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, VSCROLL$58);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVScrollArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(VSCROLL$58, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertVScroll(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(VSCROLL$58, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addVScroll(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(VSCROLL$58));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewVScroll(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(VSCROLL$58, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewVScroll() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(VSCROLL$58));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeVScroll(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VSCROLL$58, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getValidIdsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ValidIdsList
            extends AbstractList<STTrueFalseBlank.Enum> {
                ValidIdsList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getValidIdsArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getValidIdsArray(n);
                    CTClientDataImpl.this.setValidIdsArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertValidIds(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getValidIdsArray(n);
                    CTClientDataImpl.this.removeValidIds(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfValidIdsArray();
                }
            }
            return new ValidIdsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getValidIdsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VALIDIDS$60, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getValidIdsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VALIDIDS$60, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetValidIdsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ValidIdsList
            extends AbstractList<STTrueFalseBlank> {
                ValidIdsList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetValidIdsArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetValidIdsArray(n);
                    CTClientDataImpl.this.xsetValidIdsArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewValidIds(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetValidIdsArray(n);
                    CTClientDataImpl.this.removeValidIds(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfValidIdsArray();
                }
            }
            return new ValidIdsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetValidIdsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VALIDIDS$60, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetValidIdsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(VALIDIDS$60, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfValidIdsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VALIDIDS$60);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setValidIdsArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, VALIDIDS$60);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setValidIdsArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VALIDIDS$60, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetValidIdsArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, VALIDIDS$60);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetValidIdsArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(VALIDIDS$60, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertValidIds(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(VALIDIDS$60, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addValidIds(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(VALIDIDS$60));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewValidIds(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(VALIDIDS$60, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewValidIds() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(VALIDIDS$60));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeValidIds(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VALIDIDS$60, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getFmlaRangeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaRangeList
            extends AbstractList<String> {
                FmlaRangeList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getFmlaRangeArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getFmlaRangeArray(n);
                    CTClientDataImpl.this.setFmlaRangeArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertFmlaRange(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getFmlaRangeArray(n);
                    CTClientDataImpl.this.removeFmlaRange(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaRangeArray();
                }
            }
            return new FmlaRangeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getFmlaRangeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLARANGE$62, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFmlaRangeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLARANGE$62, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetFmlaRangeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaRangeList
            extends AbstractList<XmlString> {
                FmlaRangeList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetFmlaRangeArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetFmlaRangeArray(n);
                    CTClientDataImpl.this.xsetFmlaRangeArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewFmlaRange(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetFmlaRangeArray(n);
                    CTClientDataImpl.this.removeFmlaRange(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaRangeArray();
                }
            }
            return new FmlaRangeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetFmlaRangeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLARANGE$62, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetFmlaRangeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(FMLARANGE$62, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFmlaRangeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FMLARANGE$62);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaRangeArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, FMLARANGE$62);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaRangeArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLARANGE$62, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaRangeArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, FMLARANGE$62);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaRangeArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(FMLARANGE$62, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertFmlaRange(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(FMLARANGE$62, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addFmlaRange(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(FMLARANGE$62));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewFmlaRange(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(FMLARANGE$62, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewFmlaRange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(FMLARANGE$62));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFmlaRange(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FMLARANGE$62, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getWidthMinList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class WidthMinList
            extends AbstractList<BigInteger> {
                WidthMinList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getWidthMinArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getWidthMinArray(n);
                    CTClientDataImpl.this.setWidthMinArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertWidthMin(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getWidthMinArray(n);
                    CTClientDataImpl.this.removeWidthMin(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfWidthMinArray();
                }
            }
            return new WidthMinList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getWidthMinArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(WIDTHMIN$64, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getWidthMinArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(WIDTHMIN$64, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetWidthMinList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class WidthMinList
            extends AbstractList<XmlInteger> {
                WidthMinList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetWidthMinArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetWidthMinArray(n);
                    CTClientDataImpl.this.xsetWidthMinArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewWidthMin(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetWidthMinArray(n);
                    CTClientDataImpl.this.removeWidthMin(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfWidthMinArray();
                }
            }
            return new WidthMinList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetWidthMinArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(WIDTHMIN$64, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetWidthMinArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(WIDTHMIN$64, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfWidthMinArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WIDTHMIN$64);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setWidthMinArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, WIDTHMIN$64);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setWidthMinArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(WIDTHMIN$64, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetWidthMinArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, WIDTHMIN$64);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetWidthMinArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(WIDTHMIN$64, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertWidthMin(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(WIDTHMIN$64, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addWidthMin(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(WIDTHMIN$64));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewWidthMin(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(WIDTHMIN$64, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewWidthMin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(WIDTHMIN$64));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeWidthMin(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WIDTHMIN$64, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getSelList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SelList
            extends AbstractList<BigInteger> {
                SelList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getSelArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getSelArray(n);
                    CTClientDataImpl.this.setSelArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertSel(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getSelArray(n);
                    CTClientDataImpl.this.removeSel(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfSelArray();
                }
            }
            return new SelList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getSelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SEL$66, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getSelArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SEL$66, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetSelList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SelList
            extends AbstractList<XmlInteger> {
                SelList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetSelArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetSelArray(n);
                    CTClientDataImpl.this.xsetSelArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewSel(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetSelArray(n);
                    CTClientDataImpl.this.removeSel(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfSelArray();
                }
            }
            return new SelList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetSelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SEL$66, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetSelArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(SEL$66, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SEL$66);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSelArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, SEL$66);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSelArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SEL$66, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSelArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, SEL$66);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSelArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(SEL$66, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertSel(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(SEL$66, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addSel(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SEL$66));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewSel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(SEL$66, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewSel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(SEL$66));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SEL$66, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getNoThreeD2List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NoThreeD2List
            extends AbstractList<STTrueFalseBlank.Enum> {
                NoThreeD2List() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getNoThreeD2Array(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getNoThreeD2Array(n);
                    CTClientDataImpl.this.setNoThreeD2Array(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertNoThreeD2(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getNoThreeD2Array(n);
                    CTClientDataImpl.this.removeNoThreeD2(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfNoThreeD2Array();
                }
            }
            return new NoThreeD2List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getNoThreeD2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NOTHREED2$68, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getNoThreeD2Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(NOTHREED2$68, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetNoThreeD2List() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NoThreeD2List
            extends AbstractList<STTrueFalseBlank> {
                NoThreeD2List() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetNoThreeD2Array(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetNoThreeD2Array(n);
                    CTClientDataImpl.this.xsetNoThreeD2Array(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewNoThreeD2(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetNoThreeD2Array(n);
                    CTClientDataImpl.this.removeNoThreeD2(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfNoThreeD2Array();
                }
            }
            return new NoThreeD2List();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetNoThreeD2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NOTHREED2$68, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetNoThreeD2Array(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(NOTHREED2$68, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfNoThreeD2Array() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOTHREED2$68);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNoThreeD2Array(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, NOTHREED2$68);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNoThreeD2Array(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(NOTHREED2$68, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetNoThreeD2Array(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, NOTHREED2$68);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetNoThreeD2Array(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(NOTHREED2$68, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertNoThreeD2(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(NOTHREED2$68, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addNoThreeD2(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(NOTHREED2$68));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewNoThreeD2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(NOTHREED2$68, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewNoThreeD2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(NOTHREED2$68));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeNoThreeD2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOTHREED2$68, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getSelTypeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SelTypeList
            extends AbstractList<String> {
                SelTypeList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getSelTypeArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getSelTypeArray(n);
                    CTClientDataImpl.this.setSelTypeArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertSelType(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getSelTypeArray(n);
                    CTClientDataImpl.this.removeSelType(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfSelTypeArray();
                }
            }
            return new SelTypeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getSelTypeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SELTYPE$70, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSelTypeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SELTYPE$70, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetSelTypeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SelTypeList
            extends AbstractList<XmlString> {
                SelTypeList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetSelTypeArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetSelTypeArray(n);
                    CTClientDataImpl.this.xsetSelTypeArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewSelType(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetSelTypeArray(n);
                    CTClientDataImpl.this.removeSelType(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfSelTypeArray();
                }
            }
            return new SelTypeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetSelTypeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SELTYPE$70, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetSelTypeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(SELTYPE$70, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSelTypeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SELTYPE$70);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSelTypeArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, SELTYPE$70);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSelTypeArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SELTYPE$70, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSelTypeArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, SELTYPE$70);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSelTypeArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(SELTYPE$70, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertSelType(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(SELTYPE$70, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addSelType(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SELTYPE$70));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewSelType(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(SELTYPE$70, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewSelType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(SELTYPE$70));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSelType(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SELTYPE$70, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getMultiSelList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MultiSelList
            extends AbstractList<String> {
                MultiSelList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getMultiSelArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getMultiSelArray(n);
                    CTClientDataImpl.this.setMultiSelArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertMultiSel(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getMultiSelArray(n);
                    CTClientDataImpl.this.removeMultiSel(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMultiSelArray();
                }
            }
            return new MultiSelList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getMultiSelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MULTISEL$72, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getMultiSelArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MULTISEL$72, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetMultiSelList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MultiSelList
            extends AbstractList<XmlString> {
                MultiSelList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetMultiSelArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetMultiSelArray(n);
                    CTClientDataImpl.this.xsetMultiSelArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewMultiSel(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetMultiSelArray(n);
                    CTClientDataImpl.this.removeMultiSel(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMultiSelArray();
                }
            }
            return new MultiSelList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetMultiSelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MULTISEL$72, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetMultiSelArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(MULTISEL$72, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMultiSelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MULTISEL$72);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMultiSelArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, MULTISEL$72);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMultiSelArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MULTISEL$72, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMultiSelArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, MULTISEL$72);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMultiSelArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(MULTISEL$72, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertMultiSel(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(MULTISEL$72, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addMultiSel(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(MULTISEL$72));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewMultiSel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(MULTISEL$72, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewMultiSel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(MULTISEL$72));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMultiSel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MULTISEL$72, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getLCTList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LCTList
            extends AbstractList<String> {
                LCTList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getLCTArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getLCTArray(n);
                    CTClientDataImpl.this.setLCTArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertLCT(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getLCTArray(n);
                    CTClientDataImpl.this.removeLCT(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfLCTArray();
                }
            }
            return new LCTList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getLCTArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LCT$74, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getLCTArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LCT$74, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetLCTList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LCTList
            extends AbstractList<XmlString> {
                LCTList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetLCTArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetLCTArray(n);
                    CTClientDataImpl.this.xsetLCTArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewLCT(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetLCTArray(n);
                    CTClientDataImpl.this.removeLCT(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfLCTArray();
                }
            }
            return new LCTList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetLCTArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LCT$74, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetLCTArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(LCT$74, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLCTArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LCT$74);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLCTArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, LCT$74);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLCTArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LCT$74, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLCTArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, LCT$74);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLCTArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(LCT$74, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertLCT(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(LCT$74, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addLCT(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(LCT$74));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewLCT(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(LCT$74, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewLCT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(LCT$74));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLCT(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LCT$74, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getListItemList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ListItemList
            extends AbstractList<String> {
                ListItemList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getListItemArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getListItemArray(n);
                    CTClientDataImpl.this.setListItemArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertListItem(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getListItemArray(n);
                    CTClientDataImpl.this.removeListItem(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfListItemArray();
                }
            }
            return new ListItemList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getListItemArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LISTITEM$76, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getListItemArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LISTITEM$76, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetListItemList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ListItemList
            extends AbstractList<XmlString> {
                ListItemList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetListItemArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetListItemArray(n);
                    CTClientDataImpl.this.xsetListItemArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewListItem(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetListItemArray(n);
                    CTClientDataImpl.this.removeListItem(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfListItemArray();
                }
            }
            return new ListItemList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetListItemArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LISTITEM$76, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetListItemArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(LISTITEM$76, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfListItemArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LISTITEM$76);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setListItemArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, LISTITEM$76);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setListItemArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(LISTITEM$76, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetListItemArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, LISTITEM$76);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetListItemArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(LISTITEM$76, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertListItem(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(LISTITEM$76, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addListItem(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(LISTITEM$76));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewListItem(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(LISTITEM$76, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewListItem() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(LISTITEM$76));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeListItem(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LISTITEM$76, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getDropStyleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DropStyleList
            extends AbstractList<String> {
                DropStyleList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getDropStyleArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getDropStyleArray(n);
                    CTClientDataImpl.this.setDropStyleArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertDropStyle(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getDropStyleArray(n);
                    CTClientDataImpl.this.removeDropStyle(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDropStyleArray();
                }
            }
            return new DropStyleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getDropStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DROPSTYLE$78, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getDropStyleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DROPSTYLE$78, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetDropStyleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DropStyleList
            extends AbstractList<XmlString> {
                DropStyleList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetDropStyleArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetDropStyleArray(n);
                    CTClientDataImpl.this.xsetDropStyleArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewDropStyle(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetDropStyleArray(n);
                    CTClientDataImpl.this.removeDropStyle(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDropStyleArray();
                }
            }
            return new DropStyleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetDropStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DROPSTYLE$78, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetDropStyleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(DROPSTYLE$78, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDropStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DROPSTYLE$78);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDropStyleArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, DROPSTYLE$78);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDropStyleArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DROPSTYLE$78, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDropStyleArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, DROPSTYLE$78);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDropStyleArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(DROPSTYLE$78, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertDropStyle(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(DROPSTYLE$78, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addDropStyle(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DROPSTYLE$78));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewDropStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(DROPSTYLE$78, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewDropStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(DROPSTYLE$78));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDropStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DROPSTYLE$78, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getColoredList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ColoredList
            extends AbstractList<STTrueFalseBlank.Enum> {
                ColoredList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getColoredArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getColoredArray(n);
                    CTClientDataImpl.this.setColoredArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertColored(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getColoredArray(n);
                    CTClientDataImpl.this.removeColored(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfColoredArray();
                }
            }
            return new ColoredList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getColoredArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COLORED$80, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getColoredArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(COLORED$80, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetColoredList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ColoredList
            extends AbstractList<STTrueFalseBlank> {
                ColoredList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetColoredArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetColoredArray(n);
                    CTClientDataImpl.this.xsetColoredArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewColored(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetColoredArray(n);
                    CTClientDataImpl.this.removeColored(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfColoredArray();
                }
            }
            return new ColoredList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetColoredArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COLORED$80, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetColoredArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(COLORED$80, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfColoredArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLORED$80);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setColoredArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, COLORED$80);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setColoredArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(COLORED$80, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetColoredArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, COLORED$80);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetColoredArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(COLORED$80, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertColored(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(COLORED$80, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addColored(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(COLORED$80));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewColored(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(COLORED$80, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewColored() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(COLORED$80));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeColored(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLORED$80, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getDropLinesList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DropLinesList
            extends AbstractList<BigInteger> {
                DropLinesList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getDropLinesArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getDropLinesArray(n);
                    CTClientDataImpl.this.setDropLinesArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertDropLines(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getDropLinesArray(n);
                    CTClientDataImpl.this.removeDropLines(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDropLinesArray();
                }
            }
            return new DropLinesList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getDropLinesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DROPLINES$82, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getDropLinesArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DROPLINES$82, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetDropLinesList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DropLinesList
            extends AbstractList<XmlInteger> {
                DropLinesList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetDropLinesArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetDropLinesArray(n);
                    CTClientDataImpl.this.xsetDropLinesArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewDropLines(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetDropLinesArray(n);
                    CTClientDataImpl.this.removeDropLines(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDropLinesArray();
                }
            }
            return new DropLinesList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetDropLinesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DROPLINES$82, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetDropLinesArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(DROPLINES$82, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDropLinesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DROPLINES$82);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDropLinesArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, DROPLINES$82);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDropLinesArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DROPLINES$82, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDropLinesArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, DROPLINES$82);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDropLinesArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(DROPLINES$82, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertDropLines(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(DROPLINES$82, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addDropLines(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DROPLINES$82));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewDropLines(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(DROPLINES$82, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewDropLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(DROPLINES$82));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDropLines(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DROPLINES$82, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getCheckedList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CheckedList
            extends AbstractList<BigInteger> {
                CheckedList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getCheckedArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getCheckedArray(n);
                    CTClientDataImpl.this.setCheckedArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertChecked(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getCheckedArray(n);
                    CTClientDataImpl.this.removeChecked(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfCheckedArray();
                }
            }
            return new CheckedList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getCheckedArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CHECKED$84, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getCheckedArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CHECKED$84, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetCheckedList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CheckedList
            extends AbstractList<XmlInteger> {
                CheckedList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetCheckedArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetCheckedArray(n);
                    CTClientDataImpl.this.xsetCheckedArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewChecked(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetCheckedArray(n);
                    CTClientDataImpl.this.removeChecked(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfCheckedArray();
                }
            }
            return new CheckedList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetCheckedArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CHECKED$84, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetCheckedArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(CHECKED$84, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCheckedArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CHECKED$84);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCheckedArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, CHECKED$84);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCheckedArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CHECKED$84, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCheckedArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, CHECKED$84);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCheckedArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(CHECKED$84, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertChecked(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(CHECKED$84, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addChecked(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(CHECKED$84));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewChecked(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(CHECKED$84, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewChecked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(CHECKED$84));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeChecked(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CHECKED$84, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getFmlaLinkList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaLinkList
            extends AbstractList<String> {
                FmlaLinkList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getFmlaLinkArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getFmlaLinkArray(n);
                    CTClientDataImpl.this.setFmlaLinkArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertFmlaLink(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getFmlaLinkArray(n);
                    CTClientDataImpl.this.removeFmlaLink(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaLinkArray();
                }
            }
            return new FmlaLinkList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getFmlaLinkArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLALINK$86, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFmlaLinkArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLALINK$86, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetFmlaLinkList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaLinkList
            extends AbstractList<XmlString> {
                FmlaLinkList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetFmlaLinkArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetFmlaLinkArray(n);
                    CTClientDataImpl.this.xsetFmlaLinkArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewFmlaLink(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetFmlaLinkArray(n);
                    CTClientDataImpl.this.removeFmlaLink(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaLinkArray();
                }
            }
            return new FmlaLinkList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetFmlaLinkArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLALINK$86, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetFmlaLinkArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(FMLALINK$86, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFmlaLinkArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FMLALINK$86);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaLinkArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, FMLALINK$86);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaLinkArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLALINK$86, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaLinkArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, FMLALINK$86);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaLinkArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(FMLALINK$86, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertFmlaLink(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(FMLALINK$86, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addFmlaLink(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(FMLALINK$86));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewFmlaLink(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(FMLALINK$86, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewFmlaLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(FMLALINK$86));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFmlaLink(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FMLALINK$86, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getFmlaPictList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaPictList
            extends AbstractList<String> {
                FmlaPictList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getFmlaPictArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getFmlaPictArray(n);
                    CTClientDataImpl.this.setFmlaPictArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertFmlaPict(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getFmlaPictArray(n);
                    CTClientDataImpl.this.removeFmlaPict(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaPictArray();
                }
            }
            return new FmlaPictList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getFmlaPictArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLAPICT$88, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFmlaPictArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLAPICT$88, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetFmlaPictList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaPictList
            extends AbstractList<XmlString> {
                FmlaPictList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetFmlaPictArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetFmlaPictArray(n);
                    CTClientDataImpl.this.xsetFmlaPictArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewFmlaPict(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetFmlaPictArray(n);
                    CTClientDataImpl.this.removeFmlaPict(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaPictArray();
                }
            }
            return new FmlaPictList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetFmlaPictArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLAPICT$88, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetFmlaPictArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(FMLAPICT$88, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFmlaPictArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FMLAPICT$88);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaPictArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, FMLAPICT$88);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaPictArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLAPICT$88, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaPictArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, FMLAPICT$88);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaPictArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(FMLAPICT$88, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertFmlaPict(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(FMLAPICT$88, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addFmlaPict(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(FMLAPICT$88));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewFmlaPict(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(FMLAPICT$88, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewFmlaPict() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(FMLAPICT$88));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFmlaPict(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FMLAPICT$88, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getNoThreeDList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NoThreeDList
            extends AbstractList<STTrueFalseBlank.Enum> {
                NoThreeDList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getNoThreeDArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getNoThreeDArray(n);
                    CTClientDataImpl.this.setNoThreeDArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertNoThreeD(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getNoThreeDArray(n);
                    CTClientDataImpl.this.removeNoThreeD(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfNoThreeDArray();
                }
            }
            return new NoThreeDList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getNoThreeDArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NOTHREED$90, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getNoThreeDArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(NOTHREED$90, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetNoThreeDList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NoThreeDList
            extends AbstractList<STTrueFalseBlank> {
                NoThreeDList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetNoThreeDArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetNoThreeDArray(n);
                    CTClientDataImpl.this.xsetNoThreeDArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewNoThreeD(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetNoThreeDArray(n);
                    CTClientDataImpl.this.removeNoThreeD(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfNoThreeDArray();
                }
            }
            return new NoThreeDList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetNoThreeDArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NOTHREED$90, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetNoThreeDArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(NOTHREED$90, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfNoThreeDArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOTHREED$90);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNoThreeDArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, NOTHREED$90);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNoThreeDArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(NOTHREED$90, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetNoThreeDArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, NOTHREED$90);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetNoThreeDArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(NOTHREED$90, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertNoThreeD(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(NOTHREED$90, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addNoThreeD(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(NOTHREED$90));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewNoThreeD(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(NOTHREED$90, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewNoThreeD() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(NOTHREED$90));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeNoThreeD(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOTHREED$90, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getFirstButtonList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FirstButtonList
            extends AbstractList<STTrueFalseBlank.Enum> {
                FirstButtonList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getFirstButtonArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getFirstButtonArray(n);
                    CTClientDataImpl.this.setFirstButtonArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertFirstButton(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getFirstButtonArray(n);
                    CTClientDataImpl.this.removeFirstButton(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFirstButtonArray();
                }
            }
            return new FirstButtonList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getFirstButtonArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FIRSTBUTTON$92, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getFirstButtonArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FIRSTBUTTON$92, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetFirstButtonList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FirstButtonList
            extends AbstractList<STTrueFalseBlank> {
                FirstButtonList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetFirstButtonArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetFirstButtonArray(n);
                    CTClientDataImpl.this.xsetFirstButtonArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewFirstButton(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetFirstButtonArray(n);
                    CTClientDataImpl.this.removeFirstButton(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFirstButtonArray();
                }
            }
            return new FirstButtonList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetFirstButtonArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FIRSTBUTTON$92, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetFirstButtonArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(FIRSTBUTTON$92, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFirstButtonArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FIRSTBUTTON$92);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFirstButtonArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, FIRSTBUTTON$92);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFirstButtonArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FIRSTBUTTON$92, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFirstButtonArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, FIRSTBUTTON$92);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFirstButtonArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(FIRSTBUTTON$92, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertFirstButton(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(FIRSTBUTTON$92, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addFirstButton(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(FIRSTBUTTON$92));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewFirstButton(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(FIRSTBUTTON$92, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewFirstButton() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(FIRSTBUTTON$92));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFirstButton(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FIRSTBUTTON$92, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getFmlaGroupList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaGroupList
            extends AbstractList<String> {
                FmlaGroupList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getFmlaGroupArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getFmlaGroupArray(n);
                    CTClientDataImpl.this.setFmlaGroupArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertFmlaGroup(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getFmlaGroupArray(n);
                    CTClientDataImpl.this.removeFmlaGroup(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaGroupArray();
                }
            }
            return new FmlaGroupList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getFmlaGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLAGROUP$94, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFmlaGroupArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLAGROUP$94, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetFmlaGroupList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaGroupList
            extends AbstractList<XmlString> {
                FmlaGroupList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetFmlaGroupArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetFmlaGroupArray(n);
                    CTClientDataImpl.this.xsetFmlaGroupArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewFmlaGroup(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetFmlaGroupArray(n);
                    CTClientDataImpl.this.removeFmlaGroup(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaGroupArray();
                }
            }
            return new FmlaGroupList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetFmlaGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLAGROUP$94, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetFmlaGroupArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(FMLAGROUP$94, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFmlaGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FMLAGROUP$94);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaGroupArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, FMLAGROUP$94);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaGroupArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLAGROUP$94, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaGroupArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, FMLAGROUP$94);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaGroupArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(FMLAGROUP$94, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertFmlaGroup(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(FMLAGROUP$94, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addFmlaGroup(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(FMLAGROUP$94));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewFmlaGroup(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(FMLAGROUP$94, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewFmlaGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(FMLAGROUP$94));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFmlaGroup(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FMLAGROUP$94, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getValList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ValList
            extends AbstractList<BigInteger> {
                ValList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getValArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getValArray(n);
                    CTClientDataImpl.this.setValArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertVal(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getValArray(n);
                    CTClientDataImpl.this.removeVal(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfValArray();
                }
            }
            return new ValList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getValArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VAL$96, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getValArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VAL$96, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetValList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ValList
            extends AbstractList<XmlInteger> {
                ValList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetValArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetValArray(n);
                    CTClientDataImpl.this.xsetValArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewVal(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetValArray(n);
                    CTClientDataImpl.this.removeVal(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfValArray();
                }
            }
            return new ValList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetValArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VAL$96, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetValArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(VAL$96, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfValArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VAL$96);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setValArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, VAL$96);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setValArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VAL$96, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetValArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, VAL$96);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetValArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(VAL$96, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertVal(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(VAL$96, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addVal(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(VAL$96));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewVal(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(VAL$96, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(VAL$96));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeVal(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VAL$96, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getMinList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MinList
            extends AbstractList<BigInteger> {
                MinList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getMinArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getMinArray(n);
                    CTClientDataImpl.this.setMinArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertMin(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getMinArray(n);
                    CTClientDataImpl.this.removeMin(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMinArray();
                }
            }
            return new MinList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getMinArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MIN$98, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getMinArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MIN$98, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetMinList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MinList
            extends AbstractList<XmlInteger> {
                MinList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetMinArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetMinArray(n);
                    CTClientDataImpl.this.xsetMinArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewMin(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetMinArray(n);
                    CTClientDataImpl.this.removeMin(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMinArray();
                }
            }
            return new MinList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetMinArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MIN$98, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetMinArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(MIN$98, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMinArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MIN$98);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMinArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, MIN$98);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMinArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MIN$98, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMinArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, MIN$98);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMinArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(MIN$98, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertMin(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(MIN$98, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addMin(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(MIN$98));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewMin(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(MIN$98, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewMin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(MIN$98));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMin(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MIN$98, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getMaxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MaxList
            extends AbstractList<BigInteger> {
                MaxList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getMaxArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getMaxArray(n);
                    CTClientDataImpl.this.setMaxArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertMax(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getMaxArray(n);
                    CTClientDataImpl.this.removeMax(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMaxArray();
                }
            }
            return new MaxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getMaxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MAX$100, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getMaxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MAX$100, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetMaxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MaxList
            extends AbstractList<XmlInteger> {
                MaxList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetMaxArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetMaxArray(n);
                    CTClientDataImpl.this.xsetMaxArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewMax(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetMaxArray(n);
                    CTClientDataImpl.this.removeMax(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMaxArray();
                }
            }
            return new MaxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetMaxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MAX$100, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetMaxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(MAX$100, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMaxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAX$100);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMaxArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, MAX$100);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMaxArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MAX$100, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMaxArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, MAX$100);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMaxArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(MAX$100, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertMax(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(MAX$100, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addMax(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(MAX$100));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewMax(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(MAX$100, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewMax() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(MAX$100));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMax(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAX$100, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getIncList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class IncList
            extends AbstractList<BigInteger> {
                IncList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getIncArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getIncArray(n);
                    CTClientDataImpl.this.setIncArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertInc(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getIncArray(n);
                    CTClientDataImpl.this.removeInc(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfIncArray();
                }
            }
            return new IncList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getIncArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(INC$102, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getIncArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(INC$102, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetIncList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class IncList
            extends AbstractList<XmlInteger> {
                IncList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetIncArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetIncArray(n);
                    CTClientDataImpl.this.xsetIncArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewInc(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetIncArray(n);
                    CTClientDataImpl.this.removeInc(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfIncArray();
                }
            }
            return new IncList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetIncArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(INC$102, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetIncArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(INC$102, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfIncArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INC$102);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setIncArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, INC$102);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setIncArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(INC$102, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetIncArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, INC$102);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetIncArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(INC$102, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertInc(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(INC$102, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addInc(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(INC$102));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewInc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(INC$102, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewInc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(INC$102));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeInc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INC$102, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getPageList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PageList
            extends AbstractList<BigInteger> {
                PageList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getPageArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getPageArray(n);
                    CTClientDataImpl.this.setPageArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertPage(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getPageArray(n);
                    CTClientDataImpl.this.removePage(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfPageArray();
                }
            }
            return new PageList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getPageArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PAGE$104, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getPageArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(PAGE$104, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetPageList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PageList
            extends AbstractList<XmlInteger> {
                PageList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetPageArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetPageArray(n);
                    CTClientDataImpl.this.xsetPageArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewPage(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetPageArray(n);
                    CTClientDataImpl.this.removePage(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfPageArray();
                }
            }
            return new PageList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetPageArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PAGE$104, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetPageArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(PAGE$104, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPageArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PAGE$104);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPageArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, PAGE$104);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPageArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(PAGE$104, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPageArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, PAGE$104);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPageArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(PAGE$104, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertPage(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(PAGE$104, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addPage(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(PAGE$104));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewPage(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(PAGE$104, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewPage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(PAGE$104));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePage(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PAGE$104, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getHorizList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HorizList
            extends AbstractList<STTrueFalseBlank.Enum> {
                HorizList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getHorizArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getHorizArray(n);
                    CTClientDataImpl.this.setHorizArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertHoriz(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getHorizArray(n);
                    CTClientDataImpl.this.removeHoriz(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfHorizArray();
                }
            }
            return new HorizList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getHorizArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HORIZ$106, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getHorizArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(HORIZ$106, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetHorizList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HorizList
            extends AbstractList<STTrueFalseBlank> {
                HorizList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetHorizArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetHorizArray(n);
                    CTClientDataImpl.this.xsetHorizArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewHoriz(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetHorizArray(n);
                    CTClientDataImpl.this.removeHoriz(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfHorizArray();
                }
            }
            return new HorizList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetHorizArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HORIZ$106, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetHorizArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(HORIZ$106, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHorizArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HORIZ$106);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHorizArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, HORIZ$106);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHorizArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(HORIZ$106, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHorizArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, HORIZ$106);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHorizArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(HORIZ$106, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertHoriz(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(HORIZ$106, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addHoriz(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(HORIZ$106));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewHoriz(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(HORIZ$106, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewHoriz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(HORIZ$106));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHoriz(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HORIZ$106, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getDxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DxList
            extends AbstractList<BigInteger> {
                DxList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getDxArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getDxArray(n);
                    CTClientDataImpl.this.setDxArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertDx(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getDxArray(n);
                    CTClientDataImpl.this.removeDx(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDxArray();
                }
            }
            return new DxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getDxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DX$108, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getDxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DX$108, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlInteger> xgetDxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DxList
            extends AbstractList<XmlInteger> {
                DxList() {
                }

                @Override
                public XmlInteger get(int n) {
                    return CTClientDataImpl.this.xgetDxArray(n);
                }

                @Override
                public XmlInteger set(int n, XmlInteger xmlInteger) {
                    XmlInteger xmlInteger2 = CTClientDataImpl.this.xgetDxArray(n);
                    CTClientDataImpl.this.xsetDxArray(n, xmlInteger);
                    return xmlInteger2;
                }

                @Override
                public void add(int n, XmlInteger xmlInteger) {
                    CTClientDataImpl.this.insertNewDx(n).set(xmlInteger);
                }

                @Override
                public XmlInteger remove(int n) {
                    XmlInteger xmlInteger = CTClientDataImpl.this.xgetDxArray(n);
                    CTClientDataImpl.this.removeDx(n);
                    return xmlInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDxArray();
                }
            }
            return new DxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlInteger[] xgetDxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DX$108, arrayList);
            XmlInteger[] xmlIntegerArray = new XmlInteger[arrayList.size()];
            arrayList.toArray(xmlIntegerArray);
            return xmlIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetDxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_element_user(DX$108, n));
            if (xmlInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DX$108);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDxArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, DX$108);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDxArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DX$108, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDxArray(XmlInteger[] xmlIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlIntegerArray, DX$108);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDxArray(int n, XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_element_user(DX$108, n));
            if (xmlInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertDx(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(DX$108, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addDx(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DX$108));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger insertNewDx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().insert_element_user(DX$108, n));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger addNewDx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().add_element_user(DX$108));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DX$108, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getMapOCXList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MapOCXList
            extends AbstractList<STTrueFalseBlank.Enum> {
                MapOCXList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getMapOCXArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getMapOCXArray(n);
                    CTClientDataImpl.this.setMapOCXArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertMapOCX(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getMapOCXArray(n);
                    CTClientDataImpl.this.removeMapOCX(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMapOCXArray();
                }
            }
            return new MapOCXList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getMapOCXArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MAPOCX$110, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getMapOCXArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MAPOCX$110, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetMapOCXList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MapOCXList
            extends AbstractList<STTrueFalseBlank> {
                MapOCXList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetMapOCXArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetMapOCXArray(n);
                    CTClientDataImpl.this.xsetMapOCXArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewMapOCX(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetMapOCXArray(n);
                    CTClientDataImpl.this.removeMapOCX(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfMapOCXArray();
                }
            }
            return new MapOCXList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetMapOCXArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MAPOCX$110, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetMapOCXArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(MAPOCX$110, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMapOCXArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAPOCX$110);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMapOCXArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, MAPOCX$110);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMapOCXArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(MAPOCX$110, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMapOCXArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, MAPOCX$110);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMapOCXArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(MAPOCX$110, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertMapOCX(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(MAPOCX$110, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addMapOCX(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(MAPOCX$110));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewMapOCX(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(MAPOCX$110, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewMapOCX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(MAPOCX$110));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMapOCX(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAPOCX$110, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STCF.Enum> getCFList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CFList
            extends AbstractList<STCF.Enum> {
                CFList() {
                }

                @Override
                public STCF.Enum get(int n) {
                    return CTClientDataImpl.this.getCFArray(n);
                }

                @Override
                public STCF.Enum set(int n, STCF.Enum enum_) {
                    STCF.Enum enum_2 = CTClientDataImpl.this.getCFArray(n);
                    CTClientDataImpl.this.setCFArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STCF.Enum enum_) {
                    CTClientDataImpl.this.insertCF(n, enum_);
                }

                @Override
                public STCF.Enum remove(int n) {
                    STCF.Enum enum_ = CTClientDataImpl.this.getCFArray(n);
                    CTClientDataImpl.this.removeCF(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfCFArray();
                }
            }
            return new CFList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STCF.Enum[] getCFArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CF$112, arrayList);
            STCF.Enum[] enumArray = new STCF.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STCF.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCF.Enum getCFArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CF$112, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STCF.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STCF> xgetCFList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CFList
            extends AbstractList<STCF> {
                CFList() {
                }

                @Override
                public STCF get(int n) {
                    return CTClientDataImpl.this.xgetCFArray(n);
                }

                @Override
                public STCF set(int n, STCF sTCF) {
                    STCF sTCF2 = CTClientDataImpl.this.xgetCFArray(n);
                    CTClientDataImpl.this.xsetCFArray(n, sTCF);
                    return sTCF2;
                }

                @Override
                public void add(int n, STCF sTCF) {
                    CTClientDataImpl.this.insertNewCF(n).set((XmlObject)sTCF);
                }

                @Override
                public STCF remove(int n) {
                    STCF sTCF = CTClientDataImpl.this.xgetCFArray(n);
                    CTClientDataImpl.this.removeCF(n);
                    return sTCF;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfCFArray();
                }
            }
            return new CFList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STCF[] xgetCFArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CF$112, arrayList);
            STCF[] sTCFArray = new STCF[arrayList.size()];
            arrayList.toArray(sTCFArray);
            return sTCFArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCF xgetCFArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCF sTCF = null;
            sTCF = (STCF)this.get_store().find_element_user(CF$112, n);
            if (sTCF == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTCF;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCFArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CF$112);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCFArray(STCF.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, CF$112);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCFArray(int n, STCF.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CF$112, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCFArray(STCF[] sTCFArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper((XmlObject[])sTCFArray, CF$112);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCFArray(int n, STCF sTCF) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCF sTCF2 = null;
            sTCF2 = (STCF)this.get_store().find_element_user(CF$112, n);
            if (sTCF2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTCF2.set((XmlObject)sTCF);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertCF(int n, STCF.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(CF$112, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addCF(STCF.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(CF$112));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCF insertNewCF(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCF sTCF = null;
            sTCF = (STCF)this.get_store().insert_element_user(CF$112, n);
            return sTCF;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCF addNewCF() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCF sTCF = null;
            sTCF = (STCF)this.get_store().add_element_user(CF$112);
            return sTCF;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCF(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CF$112, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getCameraList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CameraList
            extends AbstractList<STTrueFalseBlank.Enum> {
                CameraList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getCameraArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getCameraArray(n);
                    CTClientDataImpl.this.setCameraArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertCamera(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getCameraArray(n);
                    CTClientDataImpl.this.removeCamera(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfCameraArray();
                }
            }
            return new CameraList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getCameraArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CAMERA$114, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getCameraArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CAMERA$114, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetCameraList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CameraList
            extends AbstractList<STTrueFalseBlank> {
                CameraList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetCameraArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetCameraArray(n);
                    CTClientDataImpl.this.xsetCameraArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewCamera(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetCameraArray(n);
                    CTClientDataImpl.this.removeCamera(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfCameraArray();
                }
            }
            return new CameraList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetCameraArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CAMERA$114, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetCameraArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(CAMERA$114, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCameraArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CAMERA$114);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCameraArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, CAMERA$114);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCameraArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(CAMERA$114, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCameraArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, CAMERA$114);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCameraArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(CAMERA$114, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertCamera(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(CAMERA$114, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addCamera(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(CAMERA$114));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewCamera(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(CAMERA$114, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewCamera() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(CAMERA$114));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCamera(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CAMERA$114, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getRecalcAlwaysList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RecalcAlwaysList
            extends AbstractList<STTrueFalseBlank.Enum> {
                RecalcAlwaysList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getRecalcAlwaysArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getRecalcAlwaysArray(n);
                    CTClientDataImpl.this.setRecalcAlwaysArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertRecalcAlways(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getRecalcAlwaysArray(n);
                    CTClientDataImpl.this.removeRecalcAlways(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfRecalcAlwaysArray();
                }
            }
            return new RecalcAlwaysList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getRecalcAlwaysArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RECALCALWAYS$116, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getRecalcAlwaysArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(RECALCALWAYS$116, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetRecalcAlwaysList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RecalcAlwaysList
            extends AbstractList<STTrueFalseBlank> {
                RecalcAlwaysList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetRecalcAlwaysArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetRecalcAlwaysArray(n);
                    CTClientDataImpl.this.xsetRecalcAlwaysArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewRecalcAlways(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetRecalcAlwaysArray(n);
                    CTClientDataImpl.this.removeRecalcAlways(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfRecalcAlwaysArray();
                }
            }
            return new RecalcAlwaysList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetRecalcAlwaysArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RECALCALWAYS$116, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetRecalcAlwaysArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(RECALCALWAYS$116, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRecalcAlwaysArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RECALCALWAYS$116);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRecalcAlwaysArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, RECALCALWAYS$116);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRecalcAlwaysArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(RECALCALWAYS$116, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRecalcAlwaysArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, RECALCALWAYS$116);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRecalcAlwaysArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(RECALCALWAYS$116, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertRecalcAlways(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(RECALCALWAYS$116, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addRecalcAlways(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(RECALCALWAYS$116));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewRecalcAlways(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(RECALCALWAYS$116, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewRecalcAlways() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(RECALCALWAYS$116));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRecalcAlways(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RECALCALWAYS$116, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getAutoScaleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AutoScaleList
            extends AbstractList<STTrueFalseBlank.Enum> {
                AutoScaleList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getAutoScaleArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getAutoScaleArray(n);
                    CTClientDataImpl.this.setAutoScaleArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertAutoScale(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getAutoScaleArray(n);
                    CTClientDataImpl.this.removeAutoScale(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAutoScaleArray();
                }
            }
            return new AutoScaleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getAutoScaleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AUTOSCALE$118, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getAutoScaleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(AUTOSCALE$118, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetAutoScaleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AutoScaleList
            extends AbstractList<STTrueFalseBlank> {
                AutoScaleList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetAutoScaleArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetAutoScaleArray(n);
                    CTClientDataImpl.this.xsetAutoScaleArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewAutoScale(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetAutoScaleArray(n);
                    CTClientDataImpl.this.removeAutoScale(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfAutoScaleArray();
                }
            }
            return new AutoScaleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetAutoScaleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AUTOSCALE$118, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetAutoScaleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(AUTOSCALE$118, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAutoScaleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOSCALE$118);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAutoScaleArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, AUTOSCALE$118);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAutoScaleArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(AUTOSCALE$118, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAutoScaleArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, AUTOSCALE$118);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAutoScaleArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(AUTOSCALE$118, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAutoScale(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(AUTOSCALE$118, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addAutoScale(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(AUTOSCALE$118));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewAutoScale(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(AUTOSCALE$118, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewAutoScale() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(AUTOSCALE$118));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAutoScale(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOSCALE$118, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getDDEList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DDEList
            extends AbstractList<STTrueFalseBlank.Enum> {
                DDEList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getDDEArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getDDEArray(n);
                    CTClientDataImpl.this.setDDEArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertDDE(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getDDEArray(n);
                    CTClientDataImpl.this.removeDDE(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDDEArray();
                }
            }
            return new DDEList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getDDEArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DDE$120, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getDDEArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DDE$120, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetDDEList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DDEList
            extends AbstractList<STTrueFalseBlank> {
                DDEList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetDDEArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetDDEArray(n);
                    CTClientDataImpl.this.xsetDDEArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewDDE(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetDDEArray(n);
                    CTClientDataImpl.this.removeDDE(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfDDEArray();
                }
            }
            return new DDEList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetDDEArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DDE$120, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetDDEArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(DDE$120, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDDEArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DDE$120);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDDEArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, DDE$120);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDDEArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DDE$120, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDDEArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, DDE$120);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDDEArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(DDE$120, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertDDE(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(DDE$120, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addDDE(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DDE$120));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewDDE(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(DDE$120, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewDDE() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(DDE$120));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDDE(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DDE$120, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank.Enum> getUIObjList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class UIObjList
            extends AbstractList<STTrueFalseBlank.Enum> {
                UIObjList() {
                }

                @Override
                public STTrueFalseBlank.Enum get(int n) {
                    return CTClientDataImpl.this.getUIObjArray(n);
                }

                @Override
                public STTrueFalseBlank.Enum set(int n, STTrueFalseBlank.Enum enum_) {
                    STTrueFalseBlank.Enum enum_2 = CTClientDataImpl.this.getUIObjArray(n);
                    CTClientDataImpl.this.setUIObjArray(n, enum_);
                    return enum_2;
                }

                @Override
                public void add(int n, STTrueFalseBlank.Enum enum_) {
                    CTClientDataImpl.this.insertUIObj(n, enum_);
                }

                @Override
                public STTrueFalseBlank.Enum remove(int n) {
                    STTrueFalseBlank.Enum enum_ = CTClientDataImpl.this.getUIObjArray(n);
                    CTClientDataImpl.this.removeUIObj(n);
                    return enum_;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfUIObjArray();
                }
            }
            return new UIObjList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank.Enum[] getUIObjArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(UIOBJ$122, arrayList);
            STTrueFalseBlank.Enum[] enumArray = new STTrueFalseBlank.Enum[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                enumArray[i] = (STTrueFalseBlank.Enum)((SimpleValue)arrayList.get(i)).getEnumValue();
            }
            return enumArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getUIObjArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UIOBJ$122, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STTrueFalseBlank> xgetUIObjList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class UIObjList
            extends AbstractList<STTrueFalseBlank> {
                UIObjList() {
                }

                @Override
                public STTrueFalseBlank get(int n) {
                    return CTClientDataImpl.this.xgetUIObjArray(n);
                }

                @Override
                public STTrueFalseBlank set(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    STTrueFalseBlank sTTrueFalseBlank2 = CTClientDataImpl.this.xgetUIObjArray(n);
                    CTClientDataImpl.this.xsetUIObjArray(n, sTTrueFalseBlank);
                    return sTTrueFalseBlank2;
                }

                @Override
                public void add(int n, STTrueFalseBlank sTTrueFalseBlank) {
                    CTClientDataImpl.this.insertNewUIObj(n).set(sTTrueFalseBlank);
                }

                @Override
                public STTrueFalseBlank remove(int n) {
                    STTrueFalseBlank sTTrueFalseBlank = CTClientDataImpl.this.xgetUIObjArray(n);
                    CTClientDataImpl.this.removeUIObj(n);
                    return sTTrueFalseBlank;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfUIObjArray();
                }
            }
            return new UIObjList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STTrueFalseBlank[] xgetUIObjArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(UIOBJ$122, arrayList);
            STTrueFalseBlank[] sTTrueFalseBlankArray = new STTrueFalseBlank[arrayList.size()];
            arrayList.toArray(sTTrueFalseBlankArray);
            return sTTrueFalseBlankArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetUIObjArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().find_element_user(UIOBJ$122, n));
            if (sTTrueFalseBlank == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfUIObjArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UIOBJ$122);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUIObjArray(STTrueFalseBlank.Enum[] enumArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(enumArray, UIOBJ$122);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUIObjArray(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(UIOBJ$122, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUIObjArray(STTrueFalseBlank[] sTTrueFalseBlankArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTTrueFalseBlankArray, UIOBJ$122);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUIObjArray(int n, STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)((Object)this.get_store().find_element_user(UIOBJ$122, n));
            if (sTTrueFalseBlank2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTTrueFalseBlank2.set(sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertUIObj(int n, STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(UIOBJ$122, n));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addUIObj(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(UIOBJ$122));
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank insertNewUIObj(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().insert_element_user(UIOBJ$122, n));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank addNewUIObj() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)((Object)this.get_store().add_element_user(UIOBJ$122));
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeUIObj(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UIOBJ$122, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getScriptTextList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ScriptTextList
            extends AbstractList<String> {
                ScriptTextList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getScriptTextArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getScriptTextArray(n);
                    CTClientDataImpl.this.setScriptTextArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertScriptText(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getScriptTextArray(n);
                    CTClientDataImpl.this.removeScriptText(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfScriptTextArray();
                }
            }
            return new ScriptTextList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getScriptTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCRIPTTEXT$124, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getScriptTextArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SCRIPTTEXT$124, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetScriptTextList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ScriptTextList
            extends AbstractList<XmlString> {
                ScriptTextList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetScriptTextArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetScriptTextArray(n);
                    CTClientDataImpl.this.xsetScriptTextArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewScriptText(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetScriptTextArray(n);
                    CTClientDataImpl.this.removeScriptText(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfScriptTextArray();
                }
            }
            return new ScriptTextList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetScriptTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCRIPTTEXT$124, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetScriptTextArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(SCRIPTTEXT$124, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfScriptTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCRIPTTEXT$124);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setScriptTextArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, SCRIPTTEXT$124);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setScriptTextArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SCRIPTTEXT$124, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetScriptTextArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, SCRIPTTEXT$124);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetScriptTextArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(SCRIPTTEXT$124, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertScriptText(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(SCRIPTTEXT$124, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addScriptText(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SCRIPTTEXT$124));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewScriptText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(SCRIPTTEXT$124, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewScriptText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(SCRIPTTEXT$124));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeScriptText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCRIPTTEXT$124, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getScriptExtendedList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ScriptExtendedList
            extends AbstractList<String> {
                ScriptExtendedList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getScriptExtendedArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getScriptExtendedArray(n);
                    CTClientDataImpl.this.setScriptExtendedArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertScriptExtended(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getScriptExtendedArray(n);
                    CTClientDataImpl.this.removeScriptExtended(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfScriptExtendedArray();
                }
            }
            return new ScriptExtendedList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getScriptExtendedArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCRIPTEXTENDED$126, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getScriptExtendedArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SCRIPTEXTENDED$126, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetScriptExtendedList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ScriptExtendedList
            extends AbstractList<XmlString> {
                ScriptExtendedList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetScriptExtendedArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetScriptExtendedArray(n);
                    CTClientDataImpl.this.xsetScriptExtendedArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewScriptExtended(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetScriptExtendedArray(n);
                    CTClientDataImpl.this.removeScriptExtended(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfScriptExtendedArray();
                }
            }
            return new ScriptExtendedList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetScriptExtendedArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCRIPTEXTENDED$126, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetScriptExtendedArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(SCRIPTEXTENDED$126, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfScriptExtendedArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCRIPTEXTENDED$126);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setScriptExtendedArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, SCRIPTEXTENDED$126);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setScriptExtendedArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SCRIPTEXTENDED$126, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetScriptExtendedArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, SCRIPTEXTENDED$126);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetScriptExtendedArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(SCRIPTEXTENDED$126, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertScriptExtended(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(SCRIPTEXTENDED$126, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addScriptExtended(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SCRIPTEXTENDED$126));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewScriptExtended(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(SCRIPTEXTENDED$126, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewScriptExtended() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(SCRIPTEXTENDED$126));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeScriptExtended(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCRIPTEXTENDED$126, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getScriptLanguageList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ScriptLanguageList
            extends AbstractList<BigInteger> {
                ScriptLanguageList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getScriptLanguageArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getScriptLanguageArray(n);
                    CTClientDataImpl.this.setScriptLanguageArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertScriptLanguage(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getScriptLanguageArray(n);
                    CTClientDataImpl.this.removeScriptLanguage(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfScriptLanguageArray();
                }
            }
            return new ScriptLanguageList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getScriptLanguageArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCRIPTLANGUAGE$128, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getScriptLanguageArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SCRIPTLANGUAGE$128, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlNonNegativeInteger> xgetScriptLanguageList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ScriptLanguageList
            extends AbstractList<XmlNonNegativeInteger> {
                ScriptLanguageList() {
                }

                @Override
                public XmlNonNegativeInteger get(int n) {
                    return CTClientDataImpl.this.xgetScriptLanguageArray(n);
                }

                @Override
                public XmlNonNegativeInteger set(int n, XmlNonNegativeInteger xmlNonNegativeInteger) {
                    XmlNonNegativeInteger xmlNonNegativeInteger2 = CTClientDataImpl.this.xgetScriptLanguageArray(n);
                    CTClientDataImpl.this.xsetScriptLanguageArray(n, xmlNonNegativeInteger);
                    return xmlNonNegativeInteger2;
                }

                @Override
                public void add(int n, XmlNonNegativeInteger xmlNonNegativeInteger) {
                    CTClientDataImpl.this.insertNewScriptLanguage(n).set(xmlNonNegativeInteger);
                }

                @Override
                public XmlNonNegativeInteger remove(int n) {
                    XmlNonNegativeInteger xmlNonNegativeInteger = CTClientDataImpl.this.xgetScriptLanguageArray(n);
                    CTClientDataImpl.this.removeScriptLanguage(n);
                    return xmlNonNegativeInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfScriptLanguageArray();
                }
            }
            return new ScriptLanguageList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlNonNegativeInteger[] xgetScriptLanguageArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCRIPTLANGUAGE$128, arrayList);
            XmlNonNegativeInteger[] xmlNonNegativeIntegerArray = new XmlNonNegativeInteger[arrayList.size()];
            arrayList.toArray(xmlNonNegativeIntegerArray);
            return xmlNonNegativeIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlNonNegativeInteger xgetScriptLanguageArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNonNegativeInteger xmlNonNegativeInteger = null;
            xmlNonNegativeInteger = (XmlNonNegativeInteger)((Object)this.get_store().find_element_user(SCRIPTLANGUAGE$128, n));
            if (xmlNonNegativeInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlNonNegativeInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfScriptLanguageArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCRIPTLANGUAGE$128);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setScriptLanguageArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, SCRIPTLANGUAGE$128);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setScriptLanguageArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SCRIPTLANGUAGE$128, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetScriptLanguageArray(XmlNonNegativeInteger[] xmlNonNegativeIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlNonNegativeIntegerArray, SCRIPTLANGUAGE$128);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetScriptLanguageArray(int n, XmlNonNegativeInteger xmlNonNegativeInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNonNegativeInteger xmlNonNegativeInteger2 = null;
            xmlNonNegativeInteger2 = (XmlNonNegativeInteger)((Object)this.get_store().find_element_user(SCRIPTLANGUAGE$128, n));
            if (xmlNonNegativeInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlNonNegativeInteger2.set(xmlNonNegativeInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertScriptLanguage(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(SCRIPTLANGUAGE$128, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addScriptLanguage(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SCRIPTLANGUAGE$128));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlNonNegativeInteger insertNewScriptLanguage(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNonNegativeInteger xmlNonNegativeInteger = null;
            xmlNonNegativeInteger = (XmlNonNegativeInteger)((Object)this.get_store().insert_element_user(SCRIPTLANGUAGE$128, n));
            return xmlNonNegativeInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlNonNegativeInteger addNewScriptLanguage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNonNegativeInteger xmlNonNegativeInteger = null;
            xmlNonNegativeInteger = (XmlNonNegativeInteger)((Object)this.get_store().add_element_user(SCRIPTLANGUAGE$128));
            return xmlNonNegativeInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeScriptLanguage(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCRIPTLANGUAGE$128, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<BigInteger> getScriptLocationList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ScriptLocationList
            extends AbstractList<BigInteger> {
                ScriptLocationList() {
                }

                @Override
                public BigInteger get(int n) {
                    return CTClientDataImpl.this.getScriptLocationArray(n);
                }

                @Override
                public BigInteger set(int n, BigInteger bigInteger) {
                    BigInteger bigInteger2 = CTClientDataImpl.this.getScriptLocationArray(n);
                    CTClientDataImpl.this.setScriptLocationArray(n, bigInteger);
                    return bigInteger2;
                }

                @Override
                public void add(int n, BigInteger bigInteger) {
                    CTClientDataImpl.this.insertScriptLocation(n, bigInteger);
                }

                @Override
                public BigInteger remove(int n) {
                    BigInteger bigInteger = CTClientDataImpl.this.getScriptLocationArray(n);
                    CTClientDataImpl.this.removeScriptLocation(n);
                    return bigInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfScriptLocationArray();
                }
            }
            return new ScriptLocationList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public BigInteger[] getScriptLocationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCRIPTLOCATION$130, arrayList);
            BigInteger[] bigIntegerArray = new BigInteger[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                bigIntegerArray[i] = ((SimpleValue)arrayList.get(i)).getBigIntegerValue();
            }
            return bigIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getScriptLocationArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SCRIPTLOCATION$130, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlNonNegativeInteger> xgetScriptLocationList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ScriptLocationList
            extends AbstractList<XmlNonNegativeInteger> {
                ScriptLocationList() {
                }

                @Override
                public XmlNonNegativeInteger get(int n) {
                    return CTClientDataImpl.this.xgetScriptLocationArray(n);
                }

                @Override
                public XmlNonNegativeInteger set(int n, XmlNonNegativeInteger xmlNonNegativeInteger) {
                    XmlNonNegativeInteger xmlNonNegativeInteger2 = CTClientDataImpl.this.xgetScriptLocationArray(n);
                    CTClientDataImpl.this.xsetScriptLocationArray(n, xmlNonNegativeInteger);
                    return xmlNonNegativeInteger2;
                }

                @Override
                public void add(int n, XmlNonNegativeInteger xmlNonNegativeInteger) {
                    CTClientDataImpl.this.insertNewScriptLocation(n).set(xmlNonNegativeInteger);
                }

                @Override
                public XmlNonNegativeInteger remove(int n) {
                    XmlNonNegativeInteger xmlNonNegativeInteger = CTClientDataImpl.this.xgetScriptLocationArray(n);
                    CTClientDataImpl.this.removeScriptLocation(n);
                    return xmlNonNegativeInteger;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfScriptLocationArray();
                }
            }
            return new ScriptLocationList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlNonNegativeInteger[] xgetScriptLocationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCRIPTLOCATION$130, arrayList);
            XmlNonNegativeInteger[] xmlNonNegativeIntegerArray = new XmlNonNegativeInteger[arrayList.size()];
            arrayList.toArray(xmlNonNegativeIntegerArray);
            return xmlNonNegativeIntegerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlNonNegativeInteger xgetScriptLocationArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNonNegativeInteger xmlNonNegativeInteger = null;
            xmlNonNegativeInteger = (XmlNonNegativeInteger)((Object)this.get_store().find_element_user(SCRIPTLOCATION$130, n));
            if (xmlNonNegativeInteger == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlNonNegativeInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfScriptLocationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCRIPTLOCATION$130);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setScriptLocationArray(BigInteger[] bigIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(bigIntegerArray, SCRIPTLOCATION$130);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setScriptLocationArray(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(SCRIPTLOCATION$130, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetScriptLocationArray(XmlNonNegativeInteger[] xmlNonNegativeIntegerArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlNonNegativeIntegerArray, SCRIPTLOCATION$130);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetScriptLocationArray(int n, XmlNonNegativeInteger xmlNonNegativeInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNonNegativeInteger xmlNonNegativeInteger2 = null;
            xmlNonNegativeInteger2 = (XmlNonNegativeInteger)((Object)this.get_store().find_element_user(SCRIPTLOCATION$130, n));
            if (xmlNonNegativeInteger2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlNonNegativeInteger2.set(xmlNonNegativeInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertScriptLocation(int n, BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(SCRIPTLOCATION$130, n));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addScriptLocation(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(SCRIPTLOCATION$130));
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlNonNegativeInteger insertNewScriptLocation(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNonNegativeInteger xmlNonNegativeInteger = null;
            xmlNonNegativeInteger = (XmlNonNegativeInteger)((Object)this.get_store().insert_element_user(SCRIPTLOCATION$130, n));
            return xmlNonNegativeInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlNonNegativeInteger addNewScriptLocation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNonNegativeInteger xmlNonNegativeInteger = null;
            xmlNonNegativeInteger = (XmlNonNegativeInteger)((Object)this.get_store().add_element_user(SCRIPTLOCATION$130));
            return xmlNonNegativeInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeScriptLocation(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCRIPTLOCATION$130, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getFmlaTxbxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaTxbxList
            extends AbstractList<String> {
                FmlaTxbxList() {
                }

                @Override
                public String get(int n) {
                    return CTClientDataImpl.this.getFmlaTxbxArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTClientDataImpl.this.getFmlaTxbxArray(n);
                    CTClientDataImpl.this.setFmlaTxbxArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTClientDataImpl.this.insertFmlaTxbx(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTClientDataImpl.this.getFmlaTxbxArray(n);
                    CTClientDataImpl.this.removeFmlaTxbx(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaTxbxArray();
                }
            }
            return new FmlaTxbxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getFmlaTxbxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLATXBX$132, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFmlaTxbxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLATXBX$132, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetFmlaTxbxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FmlaTxbxList
            extends AbstractList<XmlString> {
                FmlaTxbxList() {
                }

                @Override
                public XmlString get(int n) {
                    return CTClientDataImpl.this.xgetFmlaTxbxArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = CTClientDataImpl.this.xgetFmlaTxbxArray(n);
                    CTClientDataImpl.this.xsetFmlaTxbxArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    CTClientDataImpl.this.insertNewFmlaTxbx(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = CTClientDataImpl.this.xgetFmlaTxbxArray(n);
                    CTClientDataImpl.this.removeFmlaTxbx(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return CTClientDataImpl.this.sizeOfFmlaTxbxArray();
                }
            }
            return new FmlaTxbxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetFmlaTxbxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FMLATXBX$132, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetFmlaTxbxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(FMLATXBX$132, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFmlaTxbxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FMLATXBX$132);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaTxbxArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, FMLATXBX$132);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmlaTxbxArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FMLATXBX$132, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaTxbxArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, FMLATXBX$132);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmlaTxbxArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(FMLATXBX$132, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertFmlaTxbx(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(FMLATXBX$132, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addFmlaTxbx(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(FMLATXBX$132));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewFmlaTxbx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(FMLATXBX$132, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewFmlaTxbx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(FMLATXBX$132));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFmlaTxbx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FMLATXBX$132, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STObjectType.Enum getObjectType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OBJECTTYPE$134));
            if (simpleValue == null) {
                return null;
            }
            return (STObjectType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STObjectType xgetObjectType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STObjectType sTObjectType = null;
            sTObjectType = (STObjectType)((Object)this.get_store().find_attribute_user(OBJECTTYPE$134));
            return sTObjectType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setObjectType(STObjectType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OBJECTTYPE$134));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(OBJECTTYPE$134));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetObjectType(STObjectType sTObjectType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STObjectType sTObjectType2 = null;
            sTObjectType2 = (STObjectType)((Object)this.get_store().find_attribute_user(OBJECTTYPE$134));
            if (sTObjectType2 == null) {
                sTObjectType2 = (STObjectType)((Object)this.get_store().add_attribute_user(OBJECTTYPE$134));
            }
            sTObjectType2.set(sTObjectType);
        }
    }
}

