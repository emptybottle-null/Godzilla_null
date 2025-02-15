/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.office.CTStrokeChild
 *  com.microsoft.schemas.office.office.STRelationshipId
 *  com.microsoft.schemas.office.office.STTrueFalse
 *  com.microsoft.schemas.vml.STFillType
 *  com.microsoft.schemas.vml.STImageAspect
 *  com.microsoft.schemas.vml.STStrokeArrowLength
 *  com.microsoft.schemas.vml.STStrokeArrowType
 *  com.microsoft.schemas.vml.STStrokeArrowWidth
 *  com.microsoft.schemas.vml.STStrokeEndCap
 *  com.microsoft.schemas.vml.STStrokeLineStyle
 */
package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.office.CTStrokeChild;
import com.microsoft.schemas.office.office.STRelationshipId;
import com.microsoft.schemas.office.office.STTrueFalse;
import com.microsoft.schemas.vml.CTStroke;
import com.microsoft.schemas.vml.STColorType;
import com.microsoft.schemas.vml.STFillType;
import com.microsoft.schemas.vml.STImageAspect;
import com.microsoft.schemas.vml.STStrokeArrowLength;
import com.microsoft.schemas.vml.STStrokeArrowType;
import com.microsoft.schemas.vml.STStrokeArrowWidth;
import com.microsoft.schemas.vml.STStrokeEndCap;
import com.microsoft.schemas.vml.STStrokeJoinStyle;
import com.microsoft.schemas.vml.STStrokeLineStyle;
import com.microsoft.schemas.vml.STTrueFalse;
import java.math.BigDecimal;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTStrokeImpl
extends XmlComplexContentImpl
implements CTStroke {
    private static final long serialVersionUID = 1L;
    private static final QName LEFT$0 = new QName("urn:schemas-microsoft-com:office:office", "left");
    private static final QName TOP$2 = new QName("urn:schemas-microsoft-com:office:office", "top");
    private static final QName RIGHT$4 = new QName("urn:schemas-microsoft-com:office:office", "right");
    private static final QName BOTTOM$6 = new QName("urn:schemas-microsoft-com:office:office", "bottom");
    private static final QName COLUMN$8 = new QName("urn:schemas-microsoft-com:office:office", "column");
    private static final QName ID$10 = new QName("", "id");
    private static final QName ON$12 = new QName("", "on");
    private static final QName WEIGHT$14 = new QName("", "weight");
    private static final QName COLOR$16 = new QName("", "color");
    private static final QName OPACITY$18 = new QName("", "opacity");
    private static final QName LINESTYLE$20 = new QName("", "linestyle");
    private static final QName MITERLIMIT$22 = new QName("", "miterlimit");
    private static final QName JOINSTYLE$24 = new QName("", "joinstyle");
    private static final QName ENDCAP$26 = new QName("", "endcap");
    private static final QName DASHSTYLE$28 = new QName("", "dashstyle");
    private static final QName FILLTYPE$30 = new QName("", "filltype");
    private static final QName SRC$32 = new QName("", "src");
    private static final QName IMAGEASPECT$34 = new QName("", "imageaspect");
    private static final QName IMAGESIZE$36 = new QName("", "imagesize");
    private static final QName IMAGEALIGNSHAPE$38 = new QName("", "imagealignshape");
    private static final QName COLOR2$40 = new QName("", "color2");
    private static final QName STARTARROW$42 = new QName("", "startarrow");
    private static final QName STARTARROWWIDTH$44 = new QName("", "startarrowwidth");
    private static final QName STARTARROWLENGTH$46 = new QName("", "startarrowlength");
    private static final QName ENDARROW$48 = new QName("", "endarrow");
    private static final QName ENDARROWWIDTH$50 = new QName("", "endarrowwidth");
    private static final QName ENDARROWLENGTH$52 = new QName("", "endarrowlength");
    private static final QName HREF$54 = new QName("urn:schemas-microsoft-com:office:office", "href");
    private static final QName ALTHREF$56 = new QName("urn:schemas-microsoft-com:office:office", "althref");
    private static final QName TITLE$58 = new QName("urn:schemas-microsoft-com:office:office", "title");
    private static final QName FORCEDASH$60 = new QName("urn:schemas-microsoft-com:office:office", "forcedash");
    private static final QName ID2$62 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");
    private static final QName INSETPEN$64 = new QName("", "insetpen");
    private static final QName RELID$66 = new QName("urn:schemas-microsoft-com:office:office", "relid");

    public CTStrokeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrokeChild getLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrokeChild cTStrokeChild = null;
            cTStrokeChild = (CTStrokeChild)this.get_store().find_element_user(LEFT$0, 0);
            if (cTStrokeChild == null) {
                return null;
            }
            return cTStrokeChild;
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
    public void setLeft(CTStrokeChild cTStrokeChild) {
        this.generatedSetterHelperImpl((XmlObject)cTStrokeChild, LEFT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrokeChild addNewLeft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrokeChild cTStrokeChild = null;
            cTStrokeChild = (CTStrokeChild)this.get_store().add_element_user(LEFT$0);
            return cTStrokeChild;
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
    public CTStrokeChild getTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrokeChild cTStrokeChild = null;
            cTStrokeChild = (CTStrokeChild)this.get_store().find_element_user(TOP$2, 0);
            if (cTStrokeChild == null) {
                return null;
            }
            return cTStrokeChild;
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
            return this.get_store().count_elements(TOP$2) != 0;
        }
    }

    @Override
    public void setTop(CTStrokeChild cTStrokeChild) {
        this.generatedSetterHelperImpl((XmlObject)cTStrokeChild, TOP$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrokeChild addNewTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrokeChild cTStrokeChild = null;
            cTStrokeChild = (CTStrokeChild)this.get_store().add_element_user(TOP$2);
            return cTStrokeChild;
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
            this.get_store().remove_element(TOP$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrokeChild getRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrokeChild cTStrokeChild = null;
            cTStrokeChild = (CTStrokeChild)this.get_store().find_element_user(RIGHT$4, 0);
            if (cTStrokeChild == null) {
                return null;
            }
            return cTStrokeChild;
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
            return this.get_store().count_elements(RIGHT$4) != 0;
        }
    }

    @Override
    public void setRight(CTStrokeChild cTStrokeChild) {
        this.generatedSetterHelperImpl((XmlObject)cTStrokeChild, RIGHT$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrokeChild addNewRight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrokeChild cTStrokeChild = null;
            cTStrokeChild = (CTStrokeChild)this.get_store().add_element_user(RIGHT$4);
            return cTStrokeChild;
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
            this.get_store().remove_element(RIGHT$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrokeChild getBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrokeChild cTStrokeChild = null;
            cTStrokeChild = (CTStrokeChild)this.get_store().find_element_user(BOTTOM$6, 0);
            if (cTStrokeChild == null) {
                return null;
            }
            return cTStrokeChild;
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
    public void setBottom(CTStrokeChild cTStrokeChild) {
        this.generatedSetterHelperImpl((XmlObject)cTStrokeChild, BOTTOM$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrokeChild addNewBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrokeChild cTStrokeChild = null;
            cTStrokeChild = (CTStrokeChild)this.get_store().add_element_user(BOTTOM$6);
            return cTStrokeChild;
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
    public CTStrokeChild getColumn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrokeChild cTStrokeChild = null;
            cTStrokeChild = (CTStrokeChild)this.get_store().find_element_user(COLUMN$8, 0);
            if (cTStrokeChild == null) {
                return null;
            }
            return cTStrokeChild;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetColumn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLUMN$8) != 0;
        }
    }

    @Override
    public void setColumn(CTStrokeChild cTStrokeChild) {
        this.generatedSetterHelperImpl((XmlObject)cTStrokeChild, COLUMN$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStrokeChild addNewColumn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStrokeChild cTStrokeChild = null;
            cTStrokeChild = (CTStrokeChild)this.get_store().add_element_user(COLUMN$8);
            return cTStrokeChild;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetColumn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLUMN$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$10));
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
    public XmlString xgetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(ID$10));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ID$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setId(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$10));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetId(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(ID$10));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(ID$10));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ID$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ON$12));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse xgetOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(ON$12));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ON$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOn(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ON$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ON$12));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOn(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(ON$12));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(ON$12));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ON$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getWeight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(WEIGHT$14));
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
    public XmlString xgetWeight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(WEIGHT$14));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWeight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(WEIGHT$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setWeight(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(WEIGHT$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(WEIGHT$14));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetWeight(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(WEIGHT$14));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(WEIGHT$14));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWeight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(WEIGHT$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COLOR$16));
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
    public STColorType xgetColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorType sTColorType = null;
            sTColorType = (STColorType)((Object)this.get_store().find_attribute_user(COLOR$16));
            return sTColorType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(COLOR$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setColor(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COLOR$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COLOR$16));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetColor(STColorType sTColorType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorType sTColorType2 = null;
            sTColorType2 = (STColorType)((Object)this.get_store().find_attribute_user(COLOR$16));
            if (sTColorType2 == null) {
                sTColorType2 = (STColorType)((Object)this.get_store().add_attribute_user(COLOR$16));
            }
            sTColorType2.set(sTColorType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(COLOR$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getOpacity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OPACITY$18));
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
    public XmlString xgetOpacity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(OPACITY$18));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOpacity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(OPACITY$18) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOpacity(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OPACITY$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(OPACITY$18));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOpacity(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(OPACITY$18));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(OPACITY$18));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOpacity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(OPACITY$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeLineStyle.Enum getLinestyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LINESTYLE$20));
            if (simpleValue == null) {
                return null;
            }
            return (STStrokeLineStyle.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeLineStyle xgetLinestyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeLineStyle sTStrokeLineStyle = null;
            sTStrokeLineStyle = (STStrokeLineStyle)this.get_store().find_attribute_user(LINESTYLE$20);
            return sTStrokeLineStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLinestyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LINESTYLE$20) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLinestyle(STStrokeLineStyle.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LINESTYLE$20));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LINESTYLE$20));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLinestyle(STStrokeLineStyle sTStrokeLineStyle) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeLineStyle sTStrokeLineStyle2 = null;
            sTStrokeLineStyle2 = (STStrokeLineStyle)this.get_store().find_attribute_user(LINESTYLE$20);
            if (sTStrokeLineStyle2 == null) {
                sTStrokeLineStyle2 = (STStrokeLineStyle)this.get_store().add_attribute_user(LINESTYLE$20);
            }
            sTStrokeLineStyle2.set((XmlObject)sTStrokeLineStyle);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLinestyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LINESTYLE$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigDecimal getMiterlimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MITERLIMIT$22));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getBigDecimalValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDecimal xgetMiterlimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDecimal xmlDecimal = null;
            xmlDecimal = (XmlDecimal)((Object)this.get_store().find_attribute_user(MITERLIMIT$22));
            return xmlDecimal;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMiterlimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MITERLIMIT$22) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMiterlimit(BigDecimal bigDecimal) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MITERLIMIT$22));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MITERLIMIT$22));
            }
            simpleValue.setBigDecimalValue(bigDecimal);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMiterlimit(XmlDecimal xmlDecimal) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDecimal xmlDecimal2 = null;
            xmlDecimal2 = (XmlDecimal)((Object)this.get_store().find_attribute_user(MITERLIMIT$22));
            if (xmlDecimal2 == null) {
                xmlDecimal2 = (XmlDecimal)((Object)this.get_store().add_attribute_user(MITERLIMIT$22));
            }
            xmlDecimal2.set(xmlDecimal);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMiterlimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MITERLIMIT$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeJoinStyle.Enum getJoinstyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(JOINSTYLE$24));
            if (simpleValue == null) {
                return null;
            }
            return (STStrokeJoinStyle.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeJoinStyle xgetJoinstyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeJoinStyle sTStrokeJoinStyle = null;
            sTStrokeJoinStyle = (STStrokeJoinStyle)((Object)this.get_store().find_attribute_user(JOINSTYLE$24));
            return sTStrokeJoinStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetJoinstyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(JOINSTYLE$24) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setJoinstyle(STStrokeJoinStyle.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(JOINSTYLE$24));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(JOINSTYLE$24));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetJoinstyle(STStrokeJoinStyle sTStrokeJoinStyle) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeJoinStyle sTStrokeJoinStyle2 = null;
            sTStrokeJoinStyle2 = (STStrokeJoinStyle)((Object)this.get_store().find_attribute_user(JOINSTYLE$24));
            if (sTStrokeJoinStyle2 == null) {
                sTStrokeJoinStyle2 = (STStrokeJoinStyle)((Object)this.get_store().add_attribute_user(JOINSTYLE$24));
            }
            sTStrokeJoinStyle2.set(sTStrokeJoinStyle);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetJoinstyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(JOINSTYLE$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeEndCap.Enum getEndcap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENDCAP$26));
            if (simpleValue == null) {
                return null;
            }
            return (STStrokeEndCap.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeEndCap xgetEndcap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeEndCap sTStrokeEndCap = null;
            sTStrokeEndCap = (STStrokeEndCap)this.get_store().find_attribute_user(ENDCAP$26);
            return sTStrokeEndCap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEndcap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ENDCAP$26) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEndcap(STStrokeEndCap.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENDCAP$26));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ENDCAP$26));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEndcap(STStrokeEndCap sTStrokeEndCap) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeEndCap sTStrokeEndCap2 = null;
            sTStrokeEndCap2 = (STStrokeEndCap)this.get_store().find_attribute_user(ENDCAP$26);
            if (sTStrokeEndCap2 == null) {
                sTStrokeEndCap2 = (STStrokeEndCap)this.get_store().add_attribute_user(ENDCAP$26);
            }
            sTStrokeEndCap2.set((XmlObject)sTStrokeEndCap);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEndcap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ENDCAP$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getDashstyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DASHSTYLE$28));
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
    public XmlString xgetDashstyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(DASHSTYLE$28));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDashstyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DASHSTYLE$28) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDashstyle(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DASHSTYLE$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DASHSTYLE$28));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDashstyle(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(DASHSTYLE$28));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(DASHSTYLE$28));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDashstyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DASHSTYLE$28);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STFillType.Enum getFilltype() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLTYPE$30));
            if (simpleValue == null) {
                return null;
            }
            return (STFillType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STFillType xgetFilltype() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFillType sTFillType = null;
            sTFillType = (STFillType)this.get_store().find_attribute_user(FILLTYPE$30);
            return sTFillType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFilltype() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FILLTYPE$30) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFilltype(STFillType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLTYPE$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FILLTYPE$30));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFilltype(STFillType sTFillType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFillType sTFillType2 = null;
            sTFillType2 = (STFillType)this.get_store().find_attribute_user(FILLTYPE$30);
            if (sTFillType2 == null) {
                sTFillType2 = (STFillType)this.get_store().add_attribute_user(FILLTYPE$30);
            }
            sTFillType2.set((XmlObject)sTFillType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFilltype() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FILLTYPE$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSrc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SRC$32));
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
    public XmlString xgetSrc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(SRC$32));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSrc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SRC$32) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSrc(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SRC$32));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SRC$32));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSrc(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(SRC$32));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(SRC$32));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSrc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SRC$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STImageAspect.Enum getImageaspect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IMAGEASPECT$34));
            if (simpleValue == null) {
                return null;
            }
            return (STImageAspect.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STImageAspect xgetImageaspect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STImageAspect sTImageAspect = null;
            sTImageAspect = (STImageAspect)this.get_store().find_attribute_user(IMAGEASPECT$34);
            return sTImageAspect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetImageaspect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(IMAGEASPECT$34) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setImageaspect(STImageAspect.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IMAGEASPECT$34));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(IMAGEASPECT$34));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetImageaspect(STImageAspect sTImageAspect) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STImageAspect sTImageAspect2 = null;
            sTImageAspect2 = (STImageAspect)this.get_store().find_attribute_user(IMAGEASPECT$34);
            if (sTImageAspect2 == null) {
                sTImageAspect2 = (STImageAspect)this.get_store().add_attribute_user(IMAGEASPECT$34);
            }
            sTImageAspect2.set((XmlObject)sTImageAspect);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetImageaspect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(IMAGEASPECT$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getImagesize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IMAGESIZE$36));
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
    public XmlString xgetImagesize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(IMAGESIZE$36));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetImagesize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(IMAGESIZE$36) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setImagesize(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IMAGESIZE$36));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(IMAGESIZE$36));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetImagesize(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(IMAGESIZE$36));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(IMAGESIZE$36));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetImagesize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(IMAGESIZE$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getImagealignshape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IMAGEALIGNSHAPE$38));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse xgetImagealignshape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(IMAGEALIGNSHAPE$38));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetImagealignshape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(IMAGEALIGNSHAPE$38) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setImagealignshape(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IMAGEALIGNSHAPE$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(IMAGEALIGNSHAPE$38));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetImagealignshape(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(IMAGEALIGNSHAPE$38));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(IMAGEALIGNSHAPE$38));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetImagealignshape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(IMAGEALIGNSHAPE$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getColor2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COLOR2$40));
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
    public STColorType xgetColor2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorType sTColorType = null;
            sTColorType = (STColorType)((Object)this.get_store().find_attribute_user(COLOR2$40));
            return sTColorType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetColor2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(COLOR2$40) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setColor2(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COLOR2$40));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COLOR2$40));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetColor2(STColorType sTColorType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorType sTColorType2 = null;
            sTColorType2 = (STColorType)((Object)this.get_store().find_attribute_user(COLOR2$40));
            if (sTColorType2 == null) {
                sTColorType2 = (STColorType)((Object)this.get_store().add_attribute_user(COLOR2$40));
            }
            sTColorType2.set(sTColorType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetColor2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(COLOR2$40);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowType.Enum getStartarrow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STARTARROW$42));
            if (simpleValue == null) {
                return null;
            }
            return (STStrokeArrowType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowType xgetStartarrow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowType sTStrokeArrowType = null;
            sTStrokeArrowType = (STStrokeArrowType)this.get_store().find_attribute_user(STARTARROW$42);
            return sTStrokeArrowType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStartarrow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STARTARROW$42) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStartarrow(STStrokeArrowType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STARTARROW$42));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STARTARROW$42));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStartarrow(STStrokeArrowType sTStrokeArrowType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowType sTStrokeArrowType2 = null;
            sTStrokeArrowType2 = (STStrokeArrowType)this.get_store().find_attribute_user(STARTARROW$42);
            if (sTStrokeArrowType2 == null) {
                sTStrokeArrowType2 = (STStrokeArrowType)this.get_store().add_attribute_user(STARTARROW$42);
            }
            sTStrokeArrowType2.set((XmlObject)sTStrokeArrowType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStartarrow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STARTARROW$42);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowWidth.Enum getStartarrowwidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STARTARROWWIDTH$44));
            if (simpleValue == null) {
                return null;
            }
            return (STStrokeArrowWidth.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowWidth xgetStartarrowwidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowWidth sTStrokeArrowWidth = null;
            sTStrokeArrowWidth = (STStrokeArrowWidth)this.get_store().find_attribute_user(STARTARROWWIDTH$44);
            return sTStrokeArrowWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStartarrowwidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STARTARROWWIDTH$44) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStartarrowwidth(STStrokeArrowWidth.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STARTARROWWIDTH$44));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STARTARROWWIDTH$44));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStartarrowwidth(STStrokeArrowWidth sTStrokeArrowWidth) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowWidth sTStrokeArrowWidth2 = null;
            sTStrokeArrowWidth2 = (STStrokeArrowWidth)this.get_store().find_attribute_user(STARTARROWWIDTH$44);
            if (sTStrokeArrowWidth2 == null) {
                sTStrokeArrowWidth2 = (STStrokeArrowWidth)this.get_store().add_attribute_user(STARTARROWWIDTH$44);
            }
            sTStrokeArrowWidth2.set((XmlObject)sTStrokeArrowWidth);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStartarrowwidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STARTARROWWIDTH$44);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowLength.Enum getStartarrowlength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STARTARROWLENGTH$46));
            if (simpleValue == null) {
                return null;
            }
            return (STStrokeArrowLength.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowLength xgetStartarrowlength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowLength sTStrokeArrowLength = null;
            sTStrokeArrowLength = (STStrokeArrowLength)this.get_store().find_attribute_user(STARTARROWLENGTH$46);
            return sTStrokeArrowLength;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStartarrowlength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STARTARROWLENGTH$46) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStartarrowlength(STStrokeArrowLength.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STARTARROWLENGTH$46));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STARTARROWLENGTH$46));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStartarrowlength(STStrokeArrowLength sTStrokeArrowLength) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowLength sTStrokeArrowLength2 = null;
            sTStrokeArrowLength2 = (STStrokeArrowLength)this.get_store().find_attribute_user(STARTARROWLENGTH$46);
            if (sTStrokeArrowLength2 == null) {
                sTStrokeArrowLength2 = (STStrokeArrowLength)this.get_store().add_attribute_user(STARTARROWLENGTH$46);
            }
            sTStrokeArrowLength2.set((XmlObject)sTStrokeArrowLength);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStartarrowlength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STARTARROWLENGTH$46);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowType.Enum getEndarrow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENDARROW$48));
            if (simpleValue == null) {
                return null;
            }
            return (STStrokeArrowType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowType xgetEndarrow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowType sTStrokeArrowType = null;
            sTStrokeArrowType = (STStrokeArrowType)this.get_store().find_attribute_user(ENDARROW$48);
            return sTStrokeArrowType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEndarrow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ENDARROW$48) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEndarrow(STStrokeArrowType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENDARROW$48));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ENDARROW$48));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEndarrow(STStrokeArrowType sTStrokeArrowType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowType sTStrokeArrowType2 = null;
            sTStrokeArrowType2 = (STStrokeArrowType)this.get_store().find_attribute_user(ENDARROW$48);
            if (sTStrokeArrowType2 == null) {
                sTStrokeArrowType2 = (STStrokeArrowType)this.get_store().add_attribute_user(ENDARROW$48);
            }
            sTStrokeArrowType2.set((XmlObject)sTStrokeArrowType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEndarrow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ENDARROW$48);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowWidth.Enum getEndarrowwidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENDARROWWIDTH$50));
            if (simpleValue == null) {
                return null;
            }
            return (STStrokeArrowWidth.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowWidth xgetEndarrowwidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowWidth sTStrokeArrowWidth = null;
            sTStrokeArrowWidth = (STStrokeArrowWidth)this.get_store().find_attribute_user(ENDARROWWIDTH$50);
            return sTStrokeArrowWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEndarrowwidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ENDARROWWIDTH$50) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEndarrowwidth(STStrokeArrowWidth.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENDARROWWIDTH$50));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ENDARROWWIDTH$50));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEndarrowwidth(STStrokeArrowWidth sTStrokeArrowWidth) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowWidth sTStrokeArrowWidth2 = null;
            sTStrokeArrowWidth2 = (STStrokeArrowWidth)this.get_store().find_attribute_user(ENDARROWWIDTH$50);
            if (sTStrokeArrowWidth2 == null) {
                sTStrokeArrowWidth2 = (STStrokeArrowWidth)this.get_store().add_attribute_user(ENDARROWWIDTH$50);
            }
            sTStrokeArrowWidth2.set((XmlObject)sTStrokeArrowWidth);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEndarrowwidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ENDARROWWIDTH$50);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowLength.Enum getEndarrowlength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENDARROWLENGTH$52));
            if (simpleValue == null) {
                return null;
            }
            return (STStrokeArrowLength.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStrokeArrowLength xgetEndarrowlength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowLength sTStrokeArrowLength = null;
            sTStrokeArrowLength = (STStrokeArrowLength)this.get_store().find_attribute_user(ENDARROWLENGTH$52);
            return sTStrokeArrowLength;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEndarrowlength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ENDARROWLENGTH$52) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEndarrowlength(STStrokeArrowLength.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENDARROWLENGTH$52));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ENDARROWLENGTH$52));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEndarrowlength(STStrokeArrowLength sTStrokeArrowLength) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStrokeArrowLength sTStrokeArrowLength2 = null;
            sTStrokeArrowLength2 = (STStrokeArrowLength)this.get_store().find_attribute_user(ENDARROWLENGTH$52);
            if (sTStrokeArrowLength2 == null) {
                sTStrokeArrowLength2 = (STStrokeArrowLength)this.get_store().add_attribute_user(ENDARROWLENGTH$52);
            }
            sTStrokeArrowLength2.set((XmlObject)sTStrokeArrowLength);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEndarrowlength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ENDARROWLENGTH$52);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getHref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HREF$54));
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
    public XmlString xgetHref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(HREF$54));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HREF$54) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHref(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HREF$54));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HREF$54));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHref(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(HREF$54));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(HREF$54));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HREF$54);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getAlthref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALTHREF$56));
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
    public XmlString xgetAlthref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(ALTHREF$56));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlthref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ALTHREF$56) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlthref(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALTHREF$56));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALTHREF$56));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlthref(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(ALTHREF$56));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(ALTHREF$56));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlthref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ALTHREF$56);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TITLE$58));
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
    public XmlString xgetTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(TITLE$58));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TITLE$58) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTitle(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TITLE$58));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TITLE$58));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTitle(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(TITLE$58));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(TITLE$58));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TITLE$58);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getForcedash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FORCEDASH$60));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetForcedash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(FORCEDASH$60);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetForcedash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FORCEDASH$60) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setForcedash(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FORCEDASH$60));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FORCEDASH$60));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetForcedash(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(FORCEDASH$60);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(FORCEDASH$60);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetForcedash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FORCEDASH$60);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getId2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID2$62));
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
    public org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId xgetId2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId sTRelationshipId = null;
            sTRelationshipId = (org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId)((Object)this.get_store().find_attribute_user(ID2$62));
            return sTRelationshipId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetId2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ID2$62) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setId2(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID2$62));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID2$62));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetId2(org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId sTRelationshipId) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId sTRelationshipId2 = null;
            sTRelationshipId2 = (org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId)((Object)this.get_store().find_attribute_user(ID2$62));
            if (sTRelationshipId2 == null) {
                sTRelationshipId2 = (org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId)((Object)this.get_store().add_attribute_user(ID2$62));
            }
            sTRelationshipId2.set(sTRelationshipId);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetId2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ID2$62);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getInsetpen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INSETPEN$64));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse xgetInsetpen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(INSETPEN$64));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetInsetpen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(INSETPEN$64) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setInsetpen(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INSETPEN$64));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(INSETPEN$64));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetInsetpen(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(INSETPEN$64));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(INSETPEN$64));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetInsetpen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(INSETPEN$64);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getRelid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RELID$66));
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
    public STRelationshipId xgetRelid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRelationshipId sTRelationshipId = null;
            sTRelationshipId = (STRelationshipId)this.get_store().find_attribute_user(RELID$66);
            return sTRelationshipId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRelid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RELID$66) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRelid(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RELID$66));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RELID$66));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRelid(STRelationshipId sTRelationshipId) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRelationshipId sTRelationshipId2 = null;
            sTRelationshipId2 = (STRelationshipId)this.get_store().find_attribute_user(RELID$66);
            if (sTRelationshipId2 == null) {
                sTRelationshipId2 = (STRelationshipId)this.get_store().add_attribute_user(RELID$66);
            }
            sTRelationshipId2.set((XmlObject)sTRelationshipId);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRelid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RELID$66);
        }
    }
}

