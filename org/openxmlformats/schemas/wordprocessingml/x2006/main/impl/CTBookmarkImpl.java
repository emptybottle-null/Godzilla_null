/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTBookmarkRangeImpl;

public class CTBookmarkImpl
extends CTBookmarkRangeImpl
implements CTBookmark {
    private static final long serialVersionUID = 1L;
    private static final QName NAME$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "name");

    public CTBookmarkImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$0));
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
    public STString xgetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(NAME$0));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setName(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetName(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(NAME$0));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(NAME$0));
            }
            sTString2.set(sTString);
        }
    }
}

