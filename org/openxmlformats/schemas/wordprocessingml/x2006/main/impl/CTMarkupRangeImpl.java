/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STDisplacedByCustomXml
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDisplacedByCustomXml;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTMarkupImpl;

public class CTMarkupRangeImpl
extends CTMarkupImpl
implements CTMarkupRange {
    private static final long serialVersionUID = 1L;
    private static final QName DISPLACEDBYCUSTOMXML$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "displacedByCustomXml");

    public CTMarkupRangeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STDisplacedByCustomXml.Enum getDisplacedByCustomXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISPLACEDBYCUSTOMXML$0));
            if (simpleValue == null) {
                return null;
            }
            return (STDisplacedByCustomXml.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STDisplacedByCustomXml xgetDisplacedByCustomXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDisplacedByCustomXml sTDisplacedByCustomXml = null;
            sTDisplacedByCustomXml = (STDisplacedByCustomXml)this.get_store().find_attribute_user(DISPLACEDBYCUSTOMXML$0);
            return sTDisplacedByCustomXml;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDisplacedByCustomXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DISPLACEDBYCUSTOMXML$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDisplacedByCustomXml(STDisplacedByCustomXml.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISPLACEDBYCUSTOMXML$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DISPLACEDBYCUSTOMXML$0));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDisplacedByCustomXml(STDisplacedByCustomXml sTDisplacedByCustomXml) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDisplacedByCustomXml sTDisplacedByCustomXml2 = null;
            sTDisplacedByCustomXml2 = (STDisplacedByCustomXml)this.get_store().find_attribute_user(DISPLACEDBYCUSTOMXML$0);
            if (sTDisplacedByCustomXml2 == null) {
                sTDisplacedByCustomXml2 = (STDisplacedByCustomXml)this.get_store().add_attribute_user(DISPLACEDBYCUSTOMXML$0);
            }
            sTDisplacedByCustomXml2.set((XmlObject)sTDisplacedByCustomXml);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDisplacedByCustomXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DISPLACEDBYCUSTOMXML$0);
        }
    }
}

