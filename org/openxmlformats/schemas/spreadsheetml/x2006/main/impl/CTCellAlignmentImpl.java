/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STHorizontalAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STVerticalAlignment;

public class CTCellAlignmentImpl
extends XmlComplexContentImpl
implements CTCellAlignment {
    private static final long serialVersionUID = 1L;
    private static final QName HORIZONTAL$0 = new QName("", "horizontal");
    private static final QName VERTICAL$2 = new QName("", "vertical");
    private static final QName TEXTROTATION$4 = new QName("", "textRotation");
    private static final QName WRAPTEXT$6 = new QName("", "wrapText");
    private static final QName INDENT$8 = new QName("", "indent");
    private static final QName RELATIVEINDENT$10 = new QName("", "relativeIndent");
    private static final QName JUSTIFYLASTLINE$12 = new QName("", "justifyLastLine");
    private static final QName SHRINKTOFIT$14 = new QName("", "shrinkToFit");
    private static final QName READINGORDER$16 = new QName("", "readingOrder");

    public CTCellAlignmentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHorizontalAlignment.Enum getHorizontal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HORIZONTAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STHorizontalAlignment.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHorizontalAlignment xgetHorizontal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHorizontalAlignment sTHorizontalAlignment = null;
            sTHorizontalAlignment = (STHorizontalAlignment)((Object)this.get_store().find_attribute_user(HORIZONTAL$0));
            return sTHorizontalAlignment;
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
            return this.get_store().find_attribute_user(HORIZONTAL$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHorizontal(STHorizontalAlignment.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HORIZONTAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HORIZONTAL$0));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHorizontal(STHorizontalAlignment sTHorizontalAlignment) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHorizontalAlignment sTHorizontalAlignment2 = null;
            sTHorizontalAlignment2 = (STHorizontalAlignment)((Object)this.get_store().find_attribute_user(HORIZONTAL$0));
            if (sTHorizontalAlignment2 == null) {
                sTHorizontalAlignment2 = (STHorizontalAlignment)((Object)this.get_store().add_attribute_user(HORIZONTAL$0));
            }
            sTHorizontalAlignment2.set(sTHorizontalAlignment);
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
            this.get_store().remove_attribute(HORIZONTAL$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STVerticalAlignment.Enum getVertical() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VERTICAL$2));
            if (simpleValue == null) {
                return null;
            }
            return (STVerticalAlignment.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STVerticalAlignment xgetVertical() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVerticalAlignment sTVerticalAlignment = null;
            sTVerticalAlignment = (STVerticalAlignment)((Object)this.get_store().find_attribute_user(VERTICAL$2));
            return sTVerticalAlignment;
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
            return this.get_store().find_attribute_user(VERTICAL$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVertical(STVerticalAlignment.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VERTICAL$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VERTICAL$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVertical(STVerticalAlignment sTVerticalAlignment) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVerticalAlignment sTVerticalAlignment2 = null;
            sTVerticalAlignment2 = (STVerticalAlignment)((Object)this.get_store().find_attribute_user(VERTICAL$2));
            if (sTVerticalAlignment2 == null) {
                sTVerticalAlignment2 = (STVerticalAlignment)((Object)this.get_store().add_attribute_user(VERTICAL$2));
            }
            sTVerticalAlignment2.set(sTVerticalAlignment);
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
            this.get_store().remove_attribute(VERTICAL$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getTextRotation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TEXTROTATION$4));
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
    public XmlUnsignedInt xgetTextRotation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(TEXTROTATION$4));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTextRotation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TEXTROTATION$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTextRotation(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TEXTROTATION$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TEXTROTATION$4));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTextRotation(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(TEXTROTATION$4));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(TEXTROTATION$4));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTextRotation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TEXTROTATION$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getWrapText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(WRAPTEXT$6));
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
    public XmlBoolean xgetWrapText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(WRAPTEXT$6));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWrapText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(WRAPTEXT$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setWrapText(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(WRAPTEXT$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(WRAPTEXT$6));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetWrapText(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(WRAPTEXT$6));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(WRAPTEXT$6));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWrapText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(WRAPTEXT$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INDENT$8));
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
    public XmlUnsignedInt xgetIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(INDENT$8));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(INDENT$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setIndent(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INDENT$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(INDENT$8));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetIndent(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(INDENT$8));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(INDENT$8));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(INDENT$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getRelativeIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RELATIVEINDENT$10));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInt xgetRelativeIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt = null;
            xmlInt = (XmlInt)((Object)this.get_store().find_attribute_user(RELATIVEINDENT$10));
            return xmlInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRelativeIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RELATIVEINDENT$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRelativeIndent(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RELATIVEINDENT$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RELATIVEINDENT$10));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRelativeIndent(XmlInt xmlInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt2 = null;
            xmlInt2 = (XmlInt)((Object)this.get_store().find_attribute_user(RELATIVEINDENT$10));
            if (xmlInt2 == null) {
                xmlInt2 = (XmlInt)((Object)this.get_store().add_attribute_user(RELATIVEINDENT$10));
            }
            xmlInt2.set(xmlInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRelativeIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RELATIVEINDENT$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getJustifyLastLine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(JUSTIFYLASTLINE$12));
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
    public XmlBoolean xgetJustifyLastLine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(JUSTIFYLASTLINE$12));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetJustifyLastLine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(JUSTIFYLASTLINE$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setJustifyLastLine(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(JUSTIFYLASTLINE$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(JUSTIFYLASTLINE$12));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetJustifyLastLine(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(JUSTIFYLASTLINE$12));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(JUSTIFYLASTLINE$12));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetJustifyLastLine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(JUSTIFYLASTLINE$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getShrinkToFit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHRINKTOFIT$14));
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
    public XmlBoolean xgetShrinkToFit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(SHRINKTOFIT$14));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetShrinkToFit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SHRINKTOFIT$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setShrinkToFit(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHRINKTOFIT$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SHRINKTOFIT$14));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetShrinkToFit(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(SHRINKTOFIT$14));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(SHRINKTOFIT$14));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShrinkToFit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SHRINKTOFIT$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getReadingOrder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(READINGORDER$16));
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
    public XmlUnsignedInt xgetReadingOrder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(READINGORDER$16));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetReadingOrder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(READINGORDER$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setReadingOrder(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(READINGORDER$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(READINGORDER$16));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetReadingOrder(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(READINGORDER$16));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(READINGORDER$16));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetReadingOrder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(READINGORDER$16);
        }
    }
}

