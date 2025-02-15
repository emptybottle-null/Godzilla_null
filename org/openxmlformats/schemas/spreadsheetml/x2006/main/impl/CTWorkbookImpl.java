/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileRecoveryPr
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileSharing
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleSize
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagPr
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagTypes
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishObjects
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishing
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomWorkbookViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileRecoveryPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileSharing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleSize;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTagTypes;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookProtection;

public class CTWorkbookImpl
extends XmlComplexContentImpl
implements CTWorkbook {
    private static final long serialVersionUID = 1L;
    private static final QName FILEVERSION$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "fileVersion");
    private static final QName FILESHARING$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "fileSharing");
    private static final QName WORKBOOKPR$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "workbookPr");
    private static final QName WORKBOOKPROTECTION$6 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "workbookProtection");
    private static final QName BOOKVIEWS$8 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "bookViews");
    private static final QName SHEETS$10 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheets");
    private static final QName FUNCTIONGROUPS$12 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "functionGroups");
    private static final QName EXTERNALREFERENCES$14 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "externalReferences");
    private static final QName DEFINEDNAMES$16 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "definedNames");
    private static final QName CALCPR$18 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "calcPr");
    private static final QName OLESIZE$20 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "oleSize");
    private static final QName CUSTOMWORKBOOKVIEWS$22 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "customWorkbookViews");
    private static final QName PIVOTCACHES$24 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "pivotCaches");
    private static final QName SMARTTAGPR$26 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "smartTagPr");
    private static final QName SMARTTAGTYPES$28 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "smartTagTypes");
    private static final QName WEBPUBLISHING$30 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "webPublishing");
    private static final QName FILERECOVERYPR$32 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "fileRecoveryPr");
    private static final QName WEBPUBLISHOBJECTS$34 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "webPublishObjects");
    private static final QName EXTLST$36 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");

    public CTWorkbookImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFileVersion getFileVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFileVersion cTFileVersion = null;
            cTFileVersion = (CTFileVersion)this.get_store().find_element_user(FILEVERSION$0, 0);
            if (cTFileVersion == null) {
                return null;
            }
            return cTFileVersion;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFileVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILEVERSION$0) != 0;
        }
    }

    @Override
    public void setFileVersion(CTFileVersion cTFileVersion) {
        this.generatedSetterHelperImpl((XmlObject)cTFileVersion, FILEVERSION$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFileVersion addNewFileVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFileVersion cTFileVersion = null;
            cTFileVersion = (CTFileVersion)this.get_store().add_element_user(FILEVERSION$0);
            return cTFileVersion;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFileVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILEVERSION$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFileSharing getFileSharing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFileSharing cTFileSharing = null;
            cTFileSharing = (CTFileSharing)this.get_store().find_element_user(FILESHARING$2, 0);
            if (cTFileSharing == null) {
                return null;
            }
            return cTFileSharing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFileSharing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILESHARING$2) != 0;
        }
    }

    @Override
    public void setFileSharing(CTFileSharing cTFileSharing) {
        this.generatedSetterHelperImpl((XmlObject)cTFileSharing, FILESHARING$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFileSharing addNewFileSharing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFileSharing cTFileSharing = null;
            cTFileSharing = (CTFileSharing)this.get_store().add_element_user(FILESHARING$2);
            return cTFileSharing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFileSharing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILESHARING$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWorkbookPr getWorkbookPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWorkbookPr cTWorkbookPr = null;
            cTWorkbookPr = (CTWorkbookPr)((Object)this.get_store().find_element_user(WORKBOOKPR$4, 0));
            if (cTWorkbookPr == null) {
                return null;
            }
            return cTWorkbookPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWorkbookPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WORKBOOKPR$4) != 0;
        }
    }

    @Override
    public void setWorkbookPr(CTWorkbookPr cTWorkbookPr) {
        this.generatedSetterHelperImpl(cTWorkbookPr, WORKBOOKPR$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWorkbookPr addNewWorkbookPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWorkbookPr cTWorkbookPr = null;
            cTWorkbookPr = (CTWorkbookPr)((Object)this.get_store().add_element_user(WORKBOOKPR$4));
            return cTWorkbookPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWorkbookPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WORKBOOKPR$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWorkbookProtection getWorkbookProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWorkbookProtection cTWorkbookProtection = null;
            cTWorkbookProtection = (CTWorkbookProtection)((Object)this.get_store().find_element_user(WORKBOOKPROTECTION$6, 0));
            if (cTWorkbookProtection == null) {
                return null;
            }
            return cTWorkbookProtection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWorkbookProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WORKBOOKPROTECTION$6) != 0;
        }
    }

    @Override
    public void setWorkbookProtection(CTWorkbookProtection cTWorkbookProtection) {
        this.generatedSetterHelperImpl(cTWorkbookProtection, WORKBOOKPROTECTION$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWorkbookProtection addNewWorkbookProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWorkbookProtection cTWorkbookProtection = null;
            cTWorkbookProtection = (CTWorkbookProtection)((Object)this.get_store().add_element_user(WORKBOOKPROTECTION$6));
            return cTWorkbookProtection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWorkbookProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WORKBOOKPROTECTION$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBookViews getBookViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBookViews cTBookViews = null;
            cTBookViews = (CTBookViews)((Object)this.get_store().find_element_user(BOOKVIEWS$8, 0));
            if (cTBookViews == null) {
                return null;
            }
            return cTBookViews;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBookViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOOKVIEWS$8) != 0;
        }
    }

    @Override
    public void setBookViews(CTBookViews cTBookViews) {
        this.generatedSetterHelperImpl(cTBookViews, BOOKVIEWS$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBookViews addNewBookViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBookViews cTBookViews = null;
            cTBookViews = (CTBookViews)((Object)this.get_store().add_element_user(BOOKVIEWS$8));
            return cTBookViews;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBookViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOOKVIEWS$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheets getSheets() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheets cTSheets = null;
            cTSheets = (CTSheets)((Object)this.get_store().find_element_user(SHEETS$10, 0));
            if (cTSheets == null) {
                return null;
            }
            return cTSheets;
        }
    }

    @Override
    public void setSheets(CTSheets cTSheets) {
        this.generatedSetterHelperImpl(cTSheets, SHEETS$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheets addNewSheets() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheets cTSheets = null;
            cTSheets = (CTSheets)((Object)this.get_store().add_element_user(SHEETS$10));
            return cTSheets;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFunctionGroups getFunctionGroups() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFunctionGroups cTFunctionGroups = null;
            cTFunctionGroups = (CTFunctionGroups)this.get_store().find_element_user(FUNCTIONGROUPS$12, 0);
            if (cTFunctionGroups == null) {
                return null;
            }
            return cTFunctionGroups;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFunctionGroups() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FUNCTIONGROUPS$12) != 0;
        }
    }

    @Override
    public void setFunctionGroups(CTFunctionGroups cTFunctionGroups) {
        this.generatedSetterHelperImpl((XmlObject)cTFunctionGroups, FUNCTIONGROUPS$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFunctionGroups addNewFunctionGroups() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFunctionGroups cTFunctionGroups = null;
            cTFunctionGroups = (CTFunctionGroups)this.get_store().add_element_user(FUNCTIONGROUPS$12);
            return cTFunctionGroups;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFunctionGroups() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FUNCTIONGROUPS$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalReferences getExternalReferences() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalReferences cTExternalReferences = null;
            cTExternalReferences = (CTExternalReferences)((Object)this.get_store().find_element_user(EXTERNALREFERENCES$14, 0));
            if (cTExternalReferences == null) {
                return null;
            }
            return cTExternalReferences;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExternalReferences() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTERNALREFERENCES$14) != 0;
        }
    }

    @Override
    public void setExternalReferences(CTExternalReferences cTExternalReferences) {
        this.generatedSetterHelperImpl(cTExternalReferences, EXTERNALREFERENCES$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalReferences addNewExternalReferences() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalReferences cTExternalReferences = null;
            cTExternalReferences = (CTExternalReferences)((Object)this.get_store().add_element_user(EXTERNALREFERENCES$14));
            return cTExternalReferences;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExternalReferences() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTERNALREFERENCES$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDefinedNames getDefinedNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDefinedNames cTDefinedNames = null;
            cTDefinedNames = (CTDefinedNames)((Object)this.get_store().find_element_user(DEFINEDNAMES$16, 0));
            if (cTDefinedNames == null) {
                return null;
            }
            return cTDefinedNames;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefinedNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEFINEDNAMES$16) != 0;
        }
    }

    @Override
    public void setDefinedNames(CTDefinedNames cTDefinedNames) {
        this.generatedSetterHelperImpl(cTDefinedNames, DEFINEDNAMES$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDefinedNames addNewDefinedNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDefinedNames cTDefinedNames = null;
            cTDefinedNames = (CTDefinedNames)((Object)this.get_store().add_element_user(DEFINEDNAMES$16));
            return cTDefinedNames;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefinedNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEFINEDNAMES$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCalcPr getCalcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCalcPr cTCalcPr = null;
            cTCalcPr = (CTCalcPr)((Object)this.get_store().find_element_user(CALCPR$18, 0));
            if (cTCalcPr == null) {
                return null;
            }
            return cTCalcPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCalcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CALCPR$18) != 0;
        }
    }

    @Override
    public void setCalcPr(CTCalcPr cTCalcPr) {
        this.generatedSetterHelperImpl(cTCalcPr, CALCPR$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCalcPr addNewCalcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCalcPr cTCalcPr = null;
            cTCalcPr = (CTCalcPr)((Object)this.get_store().add_element_user(CALCPR$18));
            return cTCalcPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCalcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CALCPR$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOleSize getOleSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOleSize cTOleSize = null;
            cTOleSize = (CTOleSize)this.get_store().find_element_user(OLESIZE$20, 0);
            if (cTOleSize == null) {
                return null;
            }
            return cTOleSize;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOleSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OLESIZE$20) != 0;
        }
    }

    @Override
    public void setOleSize(CTOleSize cTOleSize) {
        this.generatedSetterHelperImpl((XmlObject)cTOleSize, OLESIZE$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOleSize addNewOleSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOleSize cTOleSize = null;
            cTOleSize = (CTOleSize)this.get_store().add_element_user(OLESIZE$20);
            return cTOleSize;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOleSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OLESIZE$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomWorkbookViews getCustomWorkbookViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomWorkbookViews cTCustomWorkbookViews = null;
            cTCustomWorkbookViews = (CTCustomWorkbookViews)this.get_store().find_element_user(CUSTOMWORKBOOKVIEWS$22, 0);
            if (cTCustomWorkbookViews == null) {
                return null;
            }
            return cTCustomWorkbookViews;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCustomWorkbookViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMWORKBOOKVIEWS$22) != 0;
        }
    }

    @Override
    public void setCustomWorkbookViews(CTCustomWorkbookViews cTCustomWorkbookViews) {
        this.generatedSetterHelperImpl((XmlObject)cTCustomWorkbookViews, CUSTOMWORKBOOKVIEWS$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomWorkbookViews addNewCustomWorkbookViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomWorkbookViews cTCustomWorkbookViews = null;
            cTCustomWorkbookViews = (CTCustomWorkbookViews)this.get_store().add_element_user(CUSTOMWORKBOOKVIEWS$22);
            return cTCustomWorkbookViews;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCustomWorkbookViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMWORKBOOKVIEWS$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotCaches getPivotCaches() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotCaches cTPivotCaches = null;
            cTPivotCaches = (CTPivotCaches)((Object)this.get_store().find_element_user(PIVOTCACHES$24, 0));
            if (cTPivotCaches == null) {
                return null;
            }
            return cTPivotCaches;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPivotCaches() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PIVOTCACHES$24) != 0;
        }
    }

    @Override
    public void setPivotCaches(CTPivotCaches cTPivotCaches) {
        this.generatedSetterHelperImpl(cTPivotCaches, PIVOTCACHES$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotCaches addNewPivotCaches() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotCaches cTPivotCaches = null;
            cTPivotCaches = (CTPivotCaches)((Object)this.get_store().add_element_user(PIVOTCACHES$24));
            return cTPivotCaches;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPivotCaches() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PIVOTCACHES$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTagPr getSmartTagPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTagPr cTSmartTagPr = null;
            cTSmartTagPr = (CTSmartTagPr)this.get_store().find_element_user(SMARTTAGPR$26, 0);
            if (cTSmartTagPr == null) {
                return null;
            }
            return cTSmartTagPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSmartTagPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SMARTTAGPR$26) != 0;
        }
    }

    @Override
    public void setSmartTagPr(CTSmartTagPr cTSmartTagPr) {
        this.generatedSetterHelperImpl((XmlObject)cTSmartTagPr, SMARTTAGPR$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTagPr addNewSmartTagPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTagPr cTSmartTagPr = null;
            cTSmartTagPr = (CTSmartTagPr)this.get_store().add_element_user(SMARTTAGPR$26);
            return cTSmartTagPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSmartTagPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SMARTTAGPR$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTagTypes getSmartTagTypes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTagTypes cTSmartTagTypes = null;
            cTSmartTagTypes = (CTSmartTagTypes)this.get_store().find_element_user(SMARTTAGTYPES$28, 0);
            if (cTSmartTagTypes == null) {
                return null;
            }
            return cTSmartTagTypes;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSmartTagTypes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SMARTTAGTYPES$28) != 0;
        }
    }

    @Override
    public void setSmartTagTypes(CTSmartTagTypes cTSmartTagTypes) {
        this.generatedSetterHelperImpl((XmlObject)cTSmartTagTypes, SMARTTAGTYPES$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTagTypes addNewSmartTagTypes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTagTypes cTSmartTagTypes = null;
            cTSmartTagTypes = (CTSmartTagTypes)this.get_store().add_element_user(SMARTTAGTYPES$28);
            return cTSmartTagTypes;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSmartTagTypes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SMARTTAGTYPES$28, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWebPublishing getWebPublishing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWebPublishing cTWebPublishing = null;
            cTWebPublishing = (CTWebPublishing)this.get_store().find_element_user(WEBPUBLISHING$30, 0);
            if (cTWebPublishing == null) {
                return null;
            }
            return cTWebPublishing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWebPublishing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WEBPUBLISHING$30) != 0;
        }
    }

    @Override
    public void setWebPublishing(CTWebPublishing cTWebPublishing) {
        this.generatedSetterHelperImpl((XmlObject)cTWebPublishing, WEBPUBLISHING$30, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWebPublishing addNewWebPublishing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWebPublishing cTWebPublishing = null;
            cTWebPublishing = (CTWebPublishing)this.get_store().add_element_user(WEBPUBLISHING$30);
            return cTWebPublishing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWebPublishing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WEBPUBLISHING$30, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFileRecoveryPr> getFileRecoveryPrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FileRecoveryPrList
            extends AbstractList<CTFileRecoveryPr> {
                FileRecoveryPrList() {
                }

                @Override
                public CTFileRecoveryPr get(int n) {
                    return CTWorkbookImpl.this.getFileRecoveryPrArray(n);
                }

                @Override
                public CTFileRecoveryPr set(int n, CTFileRecoveryPr cTFileRecoveryPr) {
                    CTFileRecoveryPr cTFileRecoveryPr2 = CTWorkbookImpl.this.getFileRecoveryPrArray(n);
                    CTWorkbookImpl.this.setFileRecoveryPrArray(n, cTFileRecoveryPr);
                    return cTFileRecoveryPr2;
                }

                @Override
                public void add(int n, CTFileRecoveryPr cTFileRecoveryPr) {
                    CTWorkbookImpl.this.insertNewFileRecoveryPr(n).set((XmlObject)cTFileRecoveryPr);
                }

                @Override
                public CTFileRecoveryPr remove(int n) {
                    CTFileRecoveryPr cTFileRecoveryPr = CTWorkbookImpl.this.getFileRecoveryPrArray(n);
                    CTWorkbookImpl.this.removeFileRecoveryPr(n);
                    return cTFileRecoveryPr;
                }

                @Override
                public int size() {
                    return CTWorkbookImpl.this.sizeOfFileRecoveryPrArray();
                }
            }
            return new FileRecoveryPrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFileRecoveryPr[] getFileRecoveryPrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FILERECOVERYPR$32, arrayList);
            CTFileRecoveryPr[] cTFileRecoveryPrArray = new CTFileRecoveryPr[arrayList.size()];
            arrayList.toArray(cTFileRecoveryPrArray);
            return cTFileRecoveryPrArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFileRecoveryPr getFileRecoveryPrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFileRecoveryPr cTFileRecoveryPr = null;
            cTFileRecoveryPr = (CTFileRecoveryPr)this.get_store().find_element_user(FILERECOVERYPR$32, n);
            if (cTFileRecoveryPr == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFileRecoveryPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFileRecoveryPrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILERECOVERYPR$32);
        }
    }

    @Override
    public void setFileRecoveryPrArray(CTFileRecoveryPr[] cTFileRecoveryPrArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFileRecoveryPrArray, FILERECOVERYPR$32);
    }

    @Override
    public void setFileRecoveryPrArray(int n, CTFileRecoveryPr cTFileRecoveryPr) {
        this.generatedSetterHelperImpl((XmlObject)cTFileRecoveryPr, FILERECOVERYPR$32, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFileRecoveryPr insertNewFileRecoveryPr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFileRecoveryPr cTFileRecoveryPr = null;
            cTFileRecoveryPr = (CTFileRecoveryPr)this.get_store().insert_element_user(FILERECOVERYPR$32, n);
            return cTFileRecoveryPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFileRecoveryPr addNewFileRecoveryPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFileRecoveryPr cTFileRecoveryPr = null;
            cTFileRecoveryPr = (CTFileRecoveryPr)this.get_store().add_element_user(FILERECOVERYPR$32);
            return cTFileRecoveryPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFileRecoveryPr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILERECOVERYPR$32, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWebPublishObjects getWebPublishObjects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWebPublishObjects cTWebPublishObjects = null;
            cTWebPublishObjects = (CTWebPublishObjects)this.get_store().find_element_user(WEBPUBLISHOBJECTS$34, 0);
            if (cTWebPublishObjects == null) {
                return null;
            }
            return cTWebPublishObjects;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWebPublishObjects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WEBPUBLISHOBJECTS$34) != 0;
        }
    }

    @Override
    public void setWebPublishObjects(CTWebPublishObjects cTWebPublishObjects) {
        this.generatedSetterHelperImpl((XmlObject)cTWebPublishObjects, WEBPUBLISHOBJECTS$34, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWebPublishObjects addNewWebPublishObjects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWebPublishObjects cTWebPublishObjects = null;
            cTWebPublishObjects = (CTWebPublishObjects)this.get_store().add_element_user(WEBPUBLISHOBJECTS$34);
            return cTWebPublishObjects;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWebPublishObjects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WEBPUBLISHOBJECTS$34, 0);
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
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$36, 0);
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
            return this.get_store().count_elements(EXTLST$36) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$36, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$36);
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
            this.get_store().remove_element(EXTLST$36, 0);
        }
    }
}

