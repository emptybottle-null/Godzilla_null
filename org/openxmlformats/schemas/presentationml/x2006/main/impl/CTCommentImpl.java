/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 *  org.openxmlformats.schemas.presentationml.x2006.main.STIndex
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.STIndex;

public class CTCommentImpl
extends XmlComplexContentImpl
implements CTComment {
    private static final long serialVersionUID = 1L;
    private static final QName POS$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "pos");
    private static final QName TEXT$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "text");
    private static final QName EXTLST$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");
    private static final QName AUTHORID$6 = new QName("", "authorId");
    private static final QName DT$8 = new QName("", "dt");
    private static final QName IDX$10 = new QName("", "idx");

    public CTCommentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPoint2D getPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPoint2D cTPoint2D = null;
            cTPoint2D = (CTPoint2D)((Object)this.get_store().find_element_user(POS$0, 0));
            if (cTPoint2D == null) {
                return null;
            }
            return cTPoint2D;
        }
    }

    @Override
    public void setPos(CTPoint2D cTPoint2D) {
        this.generatedSetterHelperImpl(cTPoint2D, POS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPoint2D addNewPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPoint2D cTPoint2D = null;
            cTPoint2D = (CTPoint2D)((Object)this.get_store().add_element_user(POS$0));
            return cTPoint2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(TEXT$2, 0));
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
    public XmlString xgetText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(TEXT$2, 0));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setText(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(TEXT$2, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(TEXT$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetText(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(TEXT$2, 0));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_element_user(TEXT$2));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionListModify getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionListModify cTExtensionListModify = null;
            cTExtensionListModify = (CTExtensionListModify)this.get_store().find_element_user(EXTLST$4, 0);
            if (cTExtensionListModify == null) {
                return null;
            }
            return cTExtensionListModify;
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
            return this.get_store().count_elements(EXTLST$4) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionListModify cTExtensionListModify) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionListModify, EXTLST$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionListModify addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionListModify cTExtensionListModify = null;
            cTExtensionListModify = (CTExtensionListModify)this.get_store().add_element_user(EXTLST$4);
            return cTExtensionListModify;
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
            this.get_store().remove_element(EXTLST$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getAuthorId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(AUTHORID$6));
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
    public XmlUnsignedInt xgetAuthorId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(AUTHORID$6));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAuthorId(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(AUTHORID$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(AUTHORID$6));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAuthorId(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(AUTHORID$6));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(AUTHORID$6));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Calendar getDt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DT$8));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getCalendarValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDateTime xgetDt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime = null;
            xmlDateTime = (XmlDateTime)((Object)this.get_store().find_attribute_user(DT$8));
            return xmlDateTime;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DT$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDt(Calendar calendar) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DT$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DT$8));
            }
            simpleValue.setCalendarValue(calendar);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDt(XmlDateTime xmlDateTime) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDateTime xmlDateTime2 = null;
            xmlDateTime2 = (XmlDateTime)((Object)this.get_store().find_attribute_user(DT$8));
            if (xmlDateTime2 == null) {
                xmlDateTime2 = (XmlDateTime)((Object)this.get_store().add_attribute_user(DT$8));
            }
            xmlDateTime2.set(xmlDateTime);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DT$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getIdx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IDX$10));
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
    public STIndex xgetIdx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STIndex sTIndex = null;
            sTIndex = (STIndex)this.get_store().find_attribute_user(IDX$10);
            return sTIndex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setIdx(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IDX$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(IDX$10));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetIdx(STIndex sTIndex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STIndex sTIndex2 = null;
            sTIndex2 = (STIndex)this.get_store().find_attribute_user(IDX$10);
            if (sTIndex2 == null) {
                sTIndex2 = (STIndex)this.get_store().add_attribute_user(IDX$10);
            }
            sTIndex2.set((XmlObject)sTIndex);
        }
    }
}

