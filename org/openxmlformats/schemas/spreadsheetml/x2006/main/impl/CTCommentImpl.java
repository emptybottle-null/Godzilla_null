/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STGuid
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STGuid;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;

public class CTCommentImpl
extends XmlComplexContentImpl
implements CTComment {
    private static final long serialVersionUID = 1L;
    private static final QName TEXT$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "text");
    private static final QName REF$2 = new QName("", "ref");
    private static final QName AUTHORID$4 = new QName("", "authorId");
    private static final QName GUID$6 = new QName("", "guid");

    public CTCommentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRst getText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRst cTRst = null;
            cTRst = (CTRst)((Object)this.get_store().find_element_user(TEXT$0, 0));
            if (cTRst == null) {
                return null;
            }
            return cTRst;
        }
    }

    @Override
    public void setText(CTRst cTRst) {
        this.generatedSetterHelperImpl(cTRst, TEXT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRst addNewText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRst cTRst = null;
            cTRst = (CTRst)((Object)this.get_store().add_element_user(TEXT$0));
            return cTRst;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$2));
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
    public STRef xgetRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRef sTRef = null;
            sTRef = (STRef)((Object)this.get_store().find_attribute_user(REF$2));
            return sTRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRef(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REF$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRef(STRef sTRef) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRef sTRef2 = null;
            sTRef2 = (STRef)((Object)this.get_store().find_attribute_user(REF$2));
            if (sTRef2 == null) {
                sTRef2 = (STRef)((Object)this.get_store().add_attribute_user(REF$2));
            }
            sTRef2.set(sTRef);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(AUTHORID$4));
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
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(AUTHORID$4));
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(AUTHORID$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(AUTHORID$4));
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
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(AUTHORID$4));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(AUTHORID$4));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getGuid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(GUID$6));
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
    public STGuid xgetGuid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGuid sTGuid = null;
            sTGuid = (STGuid)this.get_store().find_attribute_user(GUID$6);
            return sTGuid;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGuid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(GUID$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setGuid(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(GUID$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(GUID$6));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetGuid(STGuid sTGuid) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGuid sTGuid2 = null;
            sTGuid2 = (STGuid)this.get_store().find_attribute_user(GUID$6);
            if (sTGuid2 == null) {
                sTGuid2 = (STGuid)this.get_store().add_attribute_user(GUID$6);
            }
            sTGuid2.set((XmlObject)sTGuid);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGuid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(GUID$6);
        }
    }
}

