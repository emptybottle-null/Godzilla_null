/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.STPanose
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.STPanose;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextTypeface;

public class CTTextFontImpl
extends XmlComplexContentImpl
implements CTTextFont {
    private static final long serialVersionUID = 1L;
    private static final QName TYPEFACE$0 = new QName("", "typeface");
    private static final QName PANOSE$2 = new QName("", "panose");
    private static final QName PITCHFAMILY$4 = new QName("", "pitchFamily");
    private static final QName CHARSET$6 = new QName("", "charset");

    public CTTextFontImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTypeface() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPEFACE$0));
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
    public STTextTypeface xgetTypeface() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextTypeface sTTextTypeface = null;
            sTTextTypeface = (STTextTypeface)((Object)this.get_store().find_attribute_user(TYPEFACE$0));
            return sTTextTypeface;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTypeface() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TYPEFACE$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTypeface(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPEFACE$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TYPEFACE$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTypeface(STTextTypeface sTTextTypeface) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextTypeface sTTextTypeface2 = null;
            sTTextTypeface2 = (STTextTypeface)((Object)this.get_store().find_attribute_user(TYPEFACE$0));
            if (sTTextTypeface2 == null) {
                sTTextTypeface2 = (STTextTypeface)((Object)this.get_store().add_attribute_user(TYPEFACE$0));
            }
            sTTextTypeface2.set(sTTextTypeface);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTypeface() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TYPEFACE$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getPanose() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PANOSE$2));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPanose xgetPanose() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPanose sTPanose = null;
            sTPanose = (STPanose)this.get_store().find_attribute_user(PANOSE$2);
            return sTPanose;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPanose() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(PANOSE$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPanose(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PANOSE$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PANOSE$2));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPanose(STPanose sTPanose) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPanose sTPanose2 = null;
            sTPanose2 = (STPanose)this.get_store().find_attribute_user(PANOSE$2);
            if (sTPanose2 == null) {
                sTPanose2 = (STPanose)this.get_store().add_attribute_user(PANOSE$2);
            }
            sTPanose2.set((XmlObject)sTPanose);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPanose() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(PANOSE$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte getPitchFamily() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PITCHFAMILY$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(PITCHFAMILY$4));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getByteValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlByte xgetPitchFamily() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlByte xmlByte = null;
            xmlByte = (XmlByte)((Object)this.get_store().find_attribute_user(PITCHFAMILY$4));
            if (xmlByte == null) {
                xmlByte = (XmlByte)this.get_default_attribute_value(PITCHFAMILY$4);
            }
            return xmlByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPitchFamily() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(PITCHFAMILY$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPitchFamily(byte by) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PITCHFAMILY$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PITCHFAMILY$4));
            }
            simpleValue.setByteValue(by);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPitchFamily(XmlByte xmlByte) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlByte xmlByte2 = null;
            xmlByte2 = (XmlByte)((Object)this.get_store().find_attribute_user(PITCHFAMILY$4));
            if (xmlByte2 == null) {
                xmlByte2 = (XmlByte)((Object)this.get_store().add_attribute_user(PITCHFAMILY$4));
            }
            xmlByte2.set(xmlByte);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPitchFamily() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(PITCHFAMILY$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte getCharset() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CHARSET$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(CHARSET$6));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getByteValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlByte xgetCharset() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlByte xmlByte = null;
            xmlByte = (XmlByte)((Object)this.get_store().find_attribute_user(CHARSET$6));
            if (xmlByte == null) {
                xmlByte = (XmlByte)this.get_default_attribute_value(CHARSET$6);
            }
            return xmlByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCharset() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CHARSET$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCharset(byte by) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CHARSET$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CHARSET$6));
            }
            simpleValue.setByteValue(by);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCharset(XmlByte xmlByte) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlByte xmlByte2 = null;
            xmlByte2 = (XmlByte)((Object)this.get_store().find_attribute_user(CHARSET$6));
            if (xmlByte2 == null) {
                xmlByte2 = (XmlByte)((Object)this.get_store().add_attribute_user(CHARSET$6));
            }
            xmlByte2.set(xmlByte);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCharset() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CHARSET$6);
        }
    }
}

