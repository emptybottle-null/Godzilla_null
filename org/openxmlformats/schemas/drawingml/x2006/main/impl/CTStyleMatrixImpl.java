/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBackgroundFillStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix;

public class CTStyleMatrixImpl
extends XmlComplexContentImpl
implements CTStyleMatrix {
    private static final long serialVersionUID = 1L;
    private static final QName FILLSTYLELST$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fillStyleLst");
    private static final QName LNSTYLELST$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lnStyleLst");
    private static final QName EFFECTSTYLELST$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "effectStyleLst");
    private static final QName BGFILLSTYLELST$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "bgFillStyleLst");
    private static final QName NAME$8 = new QName("", "name");

    public CTStyleMatrixImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFillStyleList getFillStyleLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFillStyleList cTFillStyleList = null;
            cTFillStyleList = (CTFillStyleList)((Object)this.get_store().find_element_user(FILLSTYLELST$0, 0));
            if (cTFillStyleList == null) {
                return null;
            }
            return cTFillStyleList;
        }
    }

    @Override
    public void setFillStyleLst(CTFillStyleList cTFillStyleList) {
        this.generatedSetterHelperImpl(cTFillStyleList, FILLSTYLELST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFillStyleList addNewFillStyleLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFillStyleList cTFillStyleList = null;
            cTFillStyleList = (CTFillStyleList)((Object)this.get_store().add_element_user(FILLSTYLELST$0));
            return cTFillStyleList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineStyleList getLnStyleLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineStyleList cTLineStyleList = null;
            cTLineStyleList = (CTLineStyleList)((Object)this.get_store().find_element_user(LNSTYLELST$2, 0));
            if (cTLineStyleList == null) {
                return null;
            }
            return cTLineStyleList;
        }
    }

    @Override
    public void setLnStyleLst(CTLineStyleList cTLineStyleList) {
        this.generatedSetterHelperImpl(cTLineStyleList, LNSTYLELST$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineStyleList addNewLnStyleLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineStyleList cTLineStyleList = null;
            cTLineStyleList = (CTLineStyleList)((Object)this.get_store().add_element_user(LNSTYLELST$2));
            return cTLineStyleList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectStyleList getEffectStyleLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectStyleList cTEffectStyleList = null;
            cTEffectStyleList = (CTEffectStyleList)((Object)this.get_store().find_element_user(EFFECTSTYLELST$4, 0));
            if (cTEffectStyleList == null) {
                return null;
            }
            return cTEffectStyleList;
        }
    }

    @Override
    public void setEffectStyleLst(CTEffectStyleList cTEffectStyleList) {
        this.generatedSetterHelperImpl(cTEffectStyleList, EFFECTSTYLELST$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectStyleList addNewEffectStyleLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectStyleList cTEffectStyleList = null;
            cTEffectStyleList = (CTEffectStyleList)((Object)this.get_store().add_element_user(EFFECTSTYLELST$4));
            return cTEffectStyleList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBackgroundFillStyleList getBgFillStyleLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBackgroundFillStyleList cTBackgroundFillStyleList = null;
            cTBackgroundFillStyleList = (CTBackgroundFillStyleList)((Object)this.get_store().find_element_user(BGFILLSTYLELST$6, 0));
            if (cTBackgroundFillStyleList == null) {
                return null;
            }
            return cTBackgroundFillStyleList;
        }
    }

    @Override
    public void setBgFillStyleLst(CTBackgroundFillStyleList cTBackgroundFillStyleList) {
        this.generatedSetterHelperImpl(cTBackgroundFillStyleList, BGFILLSTYLELST$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBackgroundFillStyleList addNewBgFillStyleLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBackgroundFillStyleList cTBackgroundFillStyleList = null;
            cTBackgroundFillStyleList = (CTBackgroundFillStyleList)((Object)this.get_store().add_element_user(BGFILLSTYLELST$6));
            return cTBackgroundFillStyleList;
        }
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(NAME$8));
            }
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
    public XmlString xgetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(NAME$8));
            if (xmlString == null) {
                xmlString = (XmlString)this.get_default_attribute_value(NAME$8);
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(NAME$8) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$8));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetName(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(NAME$8));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(NAME$8));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(NAME$8);
        }
    }
}

