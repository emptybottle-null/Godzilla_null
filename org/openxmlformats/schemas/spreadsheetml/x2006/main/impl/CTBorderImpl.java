/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr;

public class CTBorderImpl
extends XmlComplexContentImpl
implements CTBorder {
    private static final long serialVersionUID = 1L;
    private static final QName LEFT$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "left");
    private static final QName RIGHT$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "right");
    private static final QName TOP$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "top");
    private static final QName BOTTOM$6 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "bottom");
    private static final QName DIAGONAL$8 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "diagonal");
    private static final QName VERTICAL$10 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "vertical");
    private static final QName HORIZONTAL$12 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "horizontal");
    private static final QName DIAGONALUP$14 = new QName("", "diagonalUp");
    private static final QName DIAGONALDOWN$16 = new QName("", "diagonalDown");
    private static final QName OUTLINE$18 = new QName("", "outline");

    public CTBorderImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr getLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().find_element_user(LEFT$0, 0));
            if (cTBorderPr == null) {
                return null;
            }
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LEFT$0) != 0;
        }
    }

    @Override
    public void setLeft(CTBorderPr cTBorderPr) {
        this.generatedSetterHelperImpl(cTBorderPr, LEFT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr addNewLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().add_element_user(LEFT$0));
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LEFT$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr getRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().find_element_user(RIGHT$2, 0));
            if (cTBorderPr == null) {
                return null;
            }
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RIGHT$2) != 0;
        }
    }

    @Override
    public void setRight(CTBorderPr cTBorderPr) {
        this.generatedSetterHelperImpl(cTBorderPr, RIGHT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr addNewRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().add_element_user(RIGHT$2));
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RIGHT$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr getTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().find_element_user(TOP$4, 0));
            if (cTBorderPr == null) {
                return null;
            }
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TOP$4) != 0;
        }
    }

    @Override
    public void setTop(CTBorderPr cTBorderPr) {
        this.generatedSetterHelperImpl(cTBorderPr, TOP$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr addNewTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().add_element_user(TOP$4));
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TOP$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr getBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().find_element_user(BOTTOM$6, 0));
            if (cTBorderPr == null) {
                return null;
            }
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOTTOM$6) != 0;
        }
    }

    @Override
    public void setBottom(CTBorderPr cTBorderPr) {
        this.generatedSetterHelperImpl(cTBorderPr, BOTTOM$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr addNewBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().add_element_user(BOTTOM$6));
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOTTOM$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr getDiagonal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().find_element_user(DIAGONAL$8, 0));
            if (cTBorderPr == null) {
                return null;
            }
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDiagonal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DIAGONAL$8) != 0;
        }
    }

    @Override
    public void setDiagonal(CTBorderPr cTBorderPr) {
        this.generatedSetterHelperImpl(cTBorderPr, DIAGONAL$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr addNewDiagonal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().add_element_user(DIAGONAL$8));
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDiagonal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DIAGONAL$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr getVertical() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().find_element_user(VERTICAL$10, 0));
            if (cTBorderPr == null) {
                return null;
            }
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVertical() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VERTICAL$10) != 0;
        }
    }

    @Override
    public void setVertical(CTBorderPr cTBorderPr) {
        this.generatedSetterHelperImpl(cTBorderPr, VERTICAL$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr addNewVertical() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().add_element_user(VERTICAL$10));
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVertical() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VERTICAL$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr getHorizontal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().find_element_user(HORIZONTAL$12, 0));
            if (cTBorderPr == null) {
                return null;
            }
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHorizontal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HORIZONTAL$12) != 0;
        }
    }

    @Override
    public void setHorizontal(CTBorderPr cTBorderPr) {
        this.generatedSetterHelperImpl(cTBorderPr, HORIZONTAL$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorderPr addNewHorizontal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorderPr cTBorderPr = null;
            cTBorderPr = (CTBorderPr)((Object)this.get_store().add_element_user(HORIZONTAL$12));
            return cTBorderPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHorizontal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HORIZONTAL$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getDiagonalUp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIAGONALUP$14));
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
    public XmlBoolean xgetDiagonalUp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(DIAGONALUP$14));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDiagonalUp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DIAGONALUP$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDiagonalUp(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIAGONALUP$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DIAGONALUP$14));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDiagonalUp(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(DIAGONALUP$14));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(DIAGONALUP$14));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDiagonalUp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DIAGONALUP$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getDiagonalDown() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIAGONALDOWN$16));
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
    public XmlBoolean xgetDiagonalDown() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(DIAGONALDOWN$16));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDiagonalDown() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DIAGONALDOWN$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDiagonalDown(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DIAGONALDOWN$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DIAGONALDOWN$16));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDiagonalDown(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(DIAGONALDOWN$16));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(DIAGONALDOWN$16));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDiagonalDown() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DIAGONALDOWN$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getOutline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OUTLINE$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(OUTLINE$18));
            }
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
    public XmlBoolean xgetOutline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(OUTLINE$18));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(OUTLINE$18);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOutline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(OUTLINE$18) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOutline(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OUTLINE$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(OUTLINE$18));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOutline(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(OUTLINE$18));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(OUTLINE$18));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOutline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(OUTLINE$18);
        }
    }
}

