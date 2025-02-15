/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTControlList
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTControlList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerDataList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;

public class CTCommonSlideDataImpl
extends XmlComplexContentImpl
implements CTCommonSlideData {
    private static final long serialVersionUID = 1L;
    private static final QName BG$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "bg");
    private static final QName SPTREE$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "spTree");
    private static final QName CUSTDATALST$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "custDataLst");
    private static final QName CONTROLS$6 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "controls");
    private static final QName EXTLST$8 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");
    private static final QName NAME$10 = new QName("", "name");

    public CTCommonSlideDataImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBackground getBg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBackground cTBackground = null;
            cTBackground = (CTBackground)((Object)this.get_store().find_element_user(BG$0, 0));
            if (cTBackground == null) {
                return null;
            }
            return cTBackground;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BG$0) != 0;
        }
    }

    @Override
    public void setBg(CTBackground cTBackground) {
        this.generatedSetterHelperImpl(cTBackground, BG$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBackground addNewBg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBackground cTBackground = null;
            cTBackground = (CTBackground)((Object)this.get_store().add_element_user(BG$0));
            return cTBackground;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BG$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupShape getSpTree() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupShape cTGroupShape = null;
            cTGroupShape = (CTGroupShape)((Object)this.get_store().find_element_user(SPTREE$2, 0));
            if (cTGroupShape == null) {
                return null;
            }
            return cTGroupShape;
        }
    }

    @Override
    public void setSpTree(CTGroupShape cTGroupShape) {
        this.generatedSetterHelperImpl(cTGroupShape, SPTREE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupShape addNewSpTree() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupShape cTGroupShape = null;
            cTGroupShape = (CTGroupShape)((Object)this.get_store().add_element_user(SPTREE$2));
            return cTGroupShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomerDataList getCustDataLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomerDataList cTCustomerDataList = null;
            cTCustomerDataList = (CTCustomerDataList)((Object)this.get_store().find_element_user(CUSTDATALST$4, 0));
            if (cTCustomerDataList == null) {
                return null;
            }
            return cTCustomerDataList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCustDataLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTDATALST$4) != 0;
        }
    }

    @Override
    public void setCustDataLst(CTCustomerDataList cTCustomerDataList) {
        this.generatedSetterHelperImpl(cTCustomerDataList, CUSTDATALST$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomerDataList addNewCustDataLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomerDataList cTCustomerDataList = null;
            cTCustomerDataList = (CTCustomerDataList)((Object)this.get_store().add_element_user(CUSTDATALST$4));
            return cTCustomerDataList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCustDataLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTDATALST$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTControlList getControls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTControlList cTControlList = null;
            cTControlList = (CTControlList)this.get_store().find_element_user(CONTROLS$6, 0);
            if (cTControlList == null) {
                return null;
            }
            return cTControlList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetControls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONTROLS$6) != 0;
        }
    }

    @Override
    public void setControls(CTControlList cTControlList) {
        this.generatedSetterHelperImpl((XmlObject)cTControlList, CONTROLS$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTControlList addNewControls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTControlList cTControlList = null;
            cTControlList = (CTControlList)this.get_store().add_element_user(CONTROLS$6);
            return cTControlList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetControls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONTROLS$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$8, 0);
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
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
            return this.get_store().count_elements(EXTLST$8) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$8);
            return cTExtensionList;
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
            this.get_store().remove_element(EXTLST$8, 0);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(NAME$10));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(NAME$10));
            if (xmlString == null) {
                xmlString = (XmlString)this.get_default_attribute_value(NAME$10);
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
            return this.get_store().find_attribute_user(NAME$10) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$10));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(NAME$10));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(NAME$10));
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
            this.get_store().remove_attribute(NAME$10);
        }
    }
}

