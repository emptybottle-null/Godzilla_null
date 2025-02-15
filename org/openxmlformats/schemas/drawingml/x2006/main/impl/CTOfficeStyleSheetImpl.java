/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTColorSchemeList
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTCustomColorList
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTObjectStyleDefaults
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorSchemeList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomColorList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTObjectStyleDefaults;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet;

public class CTOfficeStyleSheetImpl
extends XmlComplexContentImpl
implements CTOfficeStyleSheet {
    private static final long serialVersionUID = 1L;
    private static final QName THEMEELEMENTS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "themeElements");
    private static final QName OBJECTDEFAULTS$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "objectDefaults");
    private static final QName EXTRACLRSCHEMELST$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extraClrSchemeLst");
    private static final QName CUSTCLRLST$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "custClrLst");
    private static final QName EXTLST$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName NAME$10 = new QName("", "name");

    public CTOfficeStyleSheetImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBaseStyles getThemeElements() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBaseStyles cTBaseStyles = null;
            cTBaseStyles = (CTBaseStyles)((Object)this.get_store().find_element_user(THEMEELEMENTS$0, 0));
            if (cTBaseStyles == null) {
                return null;
            }
            return cTBaseStyles;
        }
    }

    @Override
    public void setThemeElements(CTBaseStyles cTBaseStyles) {
        this.generatedSetterHelperImpl(cTBaseStyles, THEMEELEMENTS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBaseStyles addNewThemeElements() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBaseStyles cTBaseStyles = null;
            cTBaseStyles = (CTBaseStyles)((Object)this.get_store().add_element_user(THEMEELEMENTS$0));
            return cTBaseStyles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTObjectStyleDefaults getObjectDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTObjectStyleDefaults cTObjectStyleDefaults = null;
            cTObjectStyleDefaults = (CTObjectStyleDefaults)this.get_store().find_element_user(OBJECTDEFAULTS$2, 0);
            if (cTObjectStyleDefaults == null) {
                return null;
            }
            return cTObjectStyleDefaults;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetObjectDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OBJECTDEFAULTS$2) != 0;
        }
    }

    @Override
    public void setObjectDefaults(CTObjectStyleDefaults cTObjectStyleDefaults) {
        this.generatedSetterHelperImpl((XmlObject)cTObjectStyleDefaults, OBJECTDEFAULTS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTObjectStyleDefaults addNewObjectDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTObjectStyleDefaults cTObjectStyleDefaults = null;
            cTObjectStyleDefaults = (CTObjectStyleDefaults)this.get_store().add_element_user(OBJECTDEFAULTS$2);
            return cTObjectStyleDefaults;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetObjectDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OBJECTDEFAULTS$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorSchemeList getExtraClrSchemeLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorSchemeList cTColorSchemeList = null;
            cTColorSchemeList = (CTColorSchemeList)this.get_store().find_element_user(EXTRACLRSCHEMELST$4, 0);
            if (cTColorSchemeList == null) {
                return null;
            }
            return cTColorSchemeList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtraClrSchemeLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTRACLRSCHEMELST$4) != 0;
        }
    }

    @Override
    public void setExtraClrSchemeLst(CTColorSchemeList cTColorSchemeList) {
        this.generatedSetterHelperImpl((XmlObject)cTColorSchemeList, EXTRACLRSCHEMELST$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorSchemeList addNewExtraClrSchemeLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorSchemeList cTColorSchemeList = null;
            cTColorSchemeList = (CTColorSchemeList)this.get_store().add_element_user(EXTRACLRSCHEMELST$4);
            return cTColorSchemeList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtraClrSchemeLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTRACLRSCHEMELST$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomColorList getCustClrLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomColorList cTCustomColorList = null;
            cTCustomColorList = (CTCustomColorList)this.get_store().find_element_user(CUSTCLRLST$6, 0);
            if (cTCustomColorList == null) {
                return null;
            }
            return cTCustomColorList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCustClrLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTCLRLST$6) != 0;
        }
    }

    @Override
    public void setCustClrLst(CTCustomColorList cTCustomColorList) {
        this.generatedSetterHelperImpl((XmlObject)cTCustomColorList, CUSTCLRLST$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomColorList addNewCustClrLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomColorList cTCustomColorList = null;
            cTCustomColorList = (CTCustomColorList)this.get_store().add_element_user(CUSTCLRLST$6);
            return cTCustomColorList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCustClrLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTCLRLST$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$8, 0));
            if (cTOfficeArtExtensionList == null) {
                return null;
            }
            return cTOfficeArtExtensionList;
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
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$8));
            return cTOfficeArtExtensionList;
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

