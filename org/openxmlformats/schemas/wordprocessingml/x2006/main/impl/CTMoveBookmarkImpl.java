/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTBookmarkImpl;

public class CTMoveBookmarkImpl
extends CTBookmarkImpl
implements CTMoveBookmark {
    private static final long serialVersionUID = 1L;
    private static final QName AUTHOR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "author");
    private static final QName DATE$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "date");

    public CTMoveBookmarkImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getAuthor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(AUTHOR$0));
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
    public STString xgetAuthor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(AUTHOR$0));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAuthor(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(AUTHOR$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(AUTHOR$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAuthor(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(AUTHOR$0));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(AUTHOR$0));
            }
            sTString2.set(sTString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Calendar getDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DATE$2));
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
    public STDateTime xgetDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDateTime sTDateTime = null;
            sTDateTime = (STDateTime)this.get_store().find_attribute_user(DATE$2);
            return sTDateTime;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDate(Calendar calendar) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DATE$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DATE$2));
            }
            simpleValue.setCalendarValue(calendar);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDate(STDateTime sTDateTime) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDateTime sTDateTime2 = null;
            sTDateTime2 = (STDateTime)this.get_store().find_attribute_user(DATE$2);
            if (sTDateTime2 == null) {
                sTDateTime2 = (STDateTime)this.get_store().add_attribute_user(DATE$2);
            }
            sTDateTime2.set((XmlObject)sTDateTime);
        }
    }
}

