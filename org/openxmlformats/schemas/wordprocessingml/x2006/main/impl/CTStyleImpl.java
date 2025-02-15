/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLongHexNumber
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblStylePr
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLongHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblStylePr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;

public class CTStyleImpl
extends XmlComplexContentImpl
implements CTStyle {
    private static final long serialVersionUID = 1L;
    private static final QName NAME$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "name");
    private static final QName ALIASES$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "aliases");
    private static final QName BASEDON$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "basedOn");
    private static final QName NEXT$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "next");
    private static final QName LINK$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "link");
    private static final QName AUTOREDEFINE$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "autoRedefine");
    private static final QName HIDDEN$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hidden");
    private static final QName UIPRIORITY$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "uiPriority");
    private static final QName SEMIHIDDEN$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "semiHidden");
    private static final QName UNHIDEWHENUSED$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "unhideWhenUsed");
    private static final QName QFORMAT$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "qFormat");
    private static final QName LOCKED$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "locked");
    private static final QName PERSONAL$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "personal");
    private static final QName PERSONALCOMPOSE$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "personalCompose");
    private static final QName PERSONALREPLY$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "personalReply");
    private static final QName RSID$30 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsid");
    private static final QName PPR$32 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pPr");
    private static final QName RPR$34 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr");
    private static final QName TBLPR$36 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblPr");
    private static final QName TRPR$38 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "trPr");
    private static final QName TCPR$40 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tcPr");
    private static final QName TBLSTYLEPR$42 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblStylePr");
    private static final QName TYPE$44 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "type");
    private static final QName STYLEID$46 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "styleId");
    private static final QName DEFAULT$48 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "default");
    private static final QName CUSTOMSTYLE$50 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customStyle");

    public CTStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(NAME$0, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
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
            return this.get_store().count_elements(NAME$0) != 0;
        }
    }

    @Override
    public void setName(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, NAME$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(NAME$0));
            return cTString;
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
            this.get_store().remove_element(NAME$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getAliases() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(ALIASES$2, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAliases() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALIASES$2) != 0;
        }
    }

    @Override
    public void setAliases(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, ALIASES$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewAliases() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(ALIASES$2));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAliases() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALIASES$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getBasedOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(BASEDON$4, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBasedOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BASEDON$4) != 0;
        }
    }

    @Override
    public void setBasedOn(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, BASEDON$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewBasedOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(BASEDON$4));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBasedOn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BASEDON$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getNext() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(NEXT$6, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNext() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NEXT$6) != 0;
        }
    }

    @Override
    public void setNext(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, NEXT$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewNext() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(NEXT$6));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNext() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NEXT$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(LINK$8, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LINK$8) != 0;
        }
    }

    @Override
    public void setLink(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, LINK$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(LINK$8));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LINK$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getAutoRedefine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(AUTOREDEFINE$10, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAutoRedefine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOREDEFINE$10) != 0;
        }
    }

    @Override
    public void setAutoRedefine(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, AUTOREDEFINE$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewAutoRedefine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(AUTOREDEFINE$10));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAutoRedefine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOREDEFINE$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(HIDDEN$12, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HIDDEN$12) != 0;
        }
    }

    @Override
    public void setHidden(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, HIDDEN$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(HIDDEN$12));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HIDDEN$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getUiPriority() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(UIPRIORITY$14, 0));
            if (cTDecimalNumber == null) {
                return null;
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUiPriority() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UIPRIORITY$14) != 0;
        }
    }

    @Override
    public void setUiPriority(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, UIPRIORITY$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewUiPriority() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(UIPRIORITY$14));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUiPriority() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UIPRIORITY$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSemiHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SEMIHIDDEN$16, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSemiHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SEMIHIDDEN$16) != 0;
        }
    }

    @Override
    public void setSemiHidden(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SEMIHIDDEN$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSemiHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SEMIHIDDEN$16));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSemiHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SEMIHIDDEN$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getUnhideWhenUsed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(UNHIDEWHENUSED$18, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUnhideWhenUsed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UNHIDEWHENUSED$18) != 0;
        }
    }

    @Override
    public void setUnhideWhenUsed(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, UNHIDEWHENUSED$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewUnhideWhenUsed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(UNHIDEWHENUSED$18));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUnhideWhenUsed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UNHIDEWHENUSED$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getQFormat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(QFORMAT$20, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetQFormat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(QFORMAT$20) != 0;
        }
    }

    @Override
    public void setQFormat(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, QFORMAT$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewQFormat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(QFORMAT$20));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetQFormat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(QFORMAT$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getLocked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(LOCKED$22, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLocked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LOCKED$22) != 0;
        }
    }

    @Override
    public void setLocked(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, LOCKED$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewLocked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(LOCKED$22));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLocked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LOCKED$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getPersonal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(PERSONAL$24, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPersonal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PERSONAL$24) != 0;
        }
    }

    @Override
    public void setPersonal(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, PERSONAL$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewPersonal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(PERSONAL$24));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPersonal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PERSONAL$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getPersonalCompose() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(PERSONALCOMPOSE$26, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPersonalCompose() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PERSONALCOMPOSE$26) != 0;
        }
    }

    @Override
    public void setPersonalCompose(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, PERSONALCOMPOSE$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewPersonalCompose() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(PERSONALCOMPOSE$26));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPersonalCompose() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PERSONALCOMPOSE$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getPersonalReply() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(PERSONALREPLY$28, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPersonalReply() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PERSONALREPLY$28) != 0;
        }
    }

    @Override
    public void setPersonalReply(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, PERSONALREPLY$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewPersonalReply() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(PERSONALREPLY$28));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPersonalReply() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PERSONALREPLY$28, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLongHexNumber getRsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLongHexNumber cTLongHexNumber = null;
            cTLongHexNumber = (CTLongHexNumber)this.get_store().find_element_user(RSID$30, 0);
            if (cTLongHexNumber == null) {
                return null;
            }
            return cTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RSID$30) != 0;
        }
    }

    @Override
    public void setRsid(CTLongHexNumber cTLongHexNumber) {
        this.generatedSetterHelperImpl((XmlObject)cTLongHexNumber, RSID$30, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLongHexNumber addNewRsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLongHexNumber cTLongHexNumber = null;
            cTLongHexNumber = (CTLongHexNumber)this.get_store().add_element_user(RSID$30);
            return cTLongHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRsid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RSID$30, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPPr getPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPPr cTPPr = null;
            cTPPr = (CTPPr)((Object)this.get_store().find_element_user(PPR$32, 0));
            if (cTPPr == null) {
                return null;
            }
            return cTPPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PPR$32) != 0;
        }
    }

    @Override
    public void setPPr(CTPPr cTPPr) {
        this.generatedSetterHelperImpl(cTPPr, PPR$32, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPPr addNewPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPPr cTPPr = null;
            cTPPr = (CTPPr)((Object)this.get_store().add_element_user(PPR$32));
            return cTPPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PPR$32, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPr getRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPr cTRPr = null;
            cTRPr = (CTRPr)((Object)this.get_store().find_element_user(RPR$34, 0));
            if (cTRPr == null) {
                return null;
            }
            return cTRPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RPR$34) != 0;
        }
    }

    @Override
    public void setRPr(CTRPr cTRPr) {
        this.generatedSetterHelperImpl(cTRPr, RPR$34, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPr addNewRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPr cTRPr = null;
            cTRPr = (CTRPr)((Object)this.get_store().add_element_user(RPR$34));
            return cTRPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RPR$34, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblPrBase getTblPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblPrBase cTTblPrBase = null;
            cTTblPrBase = (CTTblPrBase)((Object)this.get_store().find_element_user(TBLPR$36, 0));
            if (cTTblPrBase == null) {
                return null;
            }
            return cTTblPrBase;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLPR$36) != 0;
        }
    }

    @Override
    public void setTblPr(CTTblPrBase cTTblPrBase) {
        this.generatedSetterHelperImpl(cTTblPrBase, TBLPR$36, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblPrBase addNewTblPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblPrBase cTTblPrBase = null;
            cTTblPrBase = (CTTblPrBase)((Object)this.get_store().add_element_user(TBLPR$36));
            return cTTblPrBase;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLPR$36, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrPr getTrPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrPr cTTrPr = null;
            cTTrPr = (CTTrPr)((Object)this.get_store().find_element_user(TRPR$38, 0));
            if (cTTrPr == null) {
                return null;
            }
            return cTTrPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTrPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TRPR$38) != 0;
        }
    }

    @Override
    public void setTrPr(CTTrPr cTTrPr) {
        this.generatedSetterHelperImpl(cTTrPr, TRPR$38, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrPr addNewTrPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrPr cTTrPr = null;
            cTTrPr = (CTTrPr)((Object)this.get_store().add_element_user(TRPR$38));
            return cTTrPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTrPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TRPR$38, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTcPr getTcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTcPr cTTcPr = null;
            cTTcPr = (CTTcPr)((Object)this.get_store().find_element_user(TCPR$40, 0));
            if (cTTcPr == null) {
                return null;
            }
            return cTTcPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TCPR$40) != 0;
        }
    }

    @Override
    public void setTcPr(CTTcPr cTTcPr) {
        this.generatedSetterHelperImpl(cTTcPr, TCPR$40, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTcPr addNewTcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTcPr cTTcPr = null;
            cTTcPr = (CTTcPr)((Object)this.get_store().add_element_user(TCPR$40));
            return cTTcPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TCPR$40, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTblStylePr> getTblStylePrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TblStylePrList
            extends AbstractList<CTTblStylePr> {
                TblStylePrList() {
                }

                @Override
                public CTTblStylePr get(int n) {
                    return CTStyleImpl.this.getTblStylePrArray(n);
                }

                @Override
                public CTTblStylePr set(int n, CTTblStylePr cTTblStylePr) {
                    CTTblStylePr cTTblStylePr2 = CTStyleImpl.this.getTblStylePrArray(n);
                    CTStyleImpl.this.setTblStylePrArray(n, cTTblStylePr);
                    return cTTblStylePr2;
                }

                @Override
                public void add(int n, CTTblStylePr cTTblStylePr) {
                    CTStyleImpl.this.insertNewTblStylePr(n).set((XmlObject)cTTblStylePr);
                }

                @Override
                public CTTblStylePr remove(int n) {
                    CTTblStylePr cTTblStylePr = CTStyleImpl.this.getTblStylePrArray(n);
                    CTStyleImpl.this.removeTblStylePr(n);
                    return cTTblStylePr;
                }

                @Override
                public int size() {
                    return CTStyleImpl.this.sizeOfTblStylePrArray();
                }
            }
            return new TblStylePrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTblStylePr[] getTblStylePrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TBLSTYLEPR$42, arrayList);
            CTTblStylePr[] cTTblStylePrArray = new CTTblStylePr[arrayList.size()];
            arrayList.toArray(cTTblStylePrArray);
            return cTTblStylePrArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblStylePr getTblStylePrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblStylePr cTTblStylePr = null;
            cTTblStylePr = (CTTblStylePr)this.get_store().find_element_user(TBLSTYLEPR$42, n);
            if (cTTblStylePr == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTblStylePr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTblStylePrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLSTYLEPR$42);
        }
    }

    @Override
    public void setTblStylePrArray(CTTblStylePr[] cTTblStylePrArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTTblStylePrArray, TBLSTYLEPR$42);
    }

    @Override
    public void setTblStylePrArray(int n, CTTblStylePr cTTblStylePr) {
        this.generatedSetterHelperImpl((XmlObject)cTTblStylePr, TBLSTYLEPR$42, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblStylePr insertNewTblStylePr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblStylePr cTTblStylePr = null;
            cTTblStylePr = (CTTblStylePr)this.get_store().insert_element_user(TBLSTYLEPR$42, n);
            return cTTblStylePr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblStylePr addNewTblStylePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblStylePr cTTblStylePr = null;
            cTTblStylePr = (CTTblStylePr)this.get_store().add_element_user(TBLSTYLEPR$42);
            return cTTblStylePr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTblStylePr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLSTYLEPR$42, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStyleType.Enum getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$44));
            if (simpleValue == null) {
                return null;
            }
            return (STStyleType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STStyleType xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStyleType sTStyleType = null;
            sTStyleType = (STStyleType)((Object)this.get_store().find_attribute_user(TYPE$44));
            return sTStyleType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TYPE$44) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setType(STStyleType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$44));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TYPE$44));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetType(STStyleType sTStyleType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STStyleType sTStyleType2 = null;
            sTStyleType2 = (STStyleType)((Object)this.get_store().find_attribute_user(TYPE$44));
            if (sTStyleType2 == null) {
                sTStyleType2 = (STStyleType)((Object)this.get_store().add_attribute_user(TYPE$44));
            }
            sTStyleType2.set(sTStyleType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TYPE$44);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getStyleId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLEID$46));
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
    public STString xgetStyleId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString = null;
            sTString = (STString)((Object)this.get_store().find_attribute_user(STYLEID$46));
            return sTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStyleId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STYLEID$46) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStyleId(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLEID$46));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STYLEID$46));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStyleId(STString sTString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STString sTString2 = null;
            sTString2 = (STString)((Object)this.get_store().find_attribute_user(STYLEID$46));
            if (sTString2 == null) {
                sTString2 = (STString)((Object)this.get_store().add_attribute_user(STYLEID$46));
            }
            sTString2.set(sTString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStyleId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STYLEID$46);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULT$48));
            if (simpleValue == null) {
                return null;
            }
            return (STOnOff.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff xgetDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(DEFAULT$48));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DEFAULT$48) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefault(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULT$48));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DEFAULT$48));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDefault(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(DEFAULT$48));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(DEFAULT$48));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DEFAULT$48);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff.Enum getCustomStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CUSTOMSTYLE$50));
            if (simpleValue == null) {
                return null;
            }
            return (STOnOff.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STOnOff xgetCustomStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff = null;
            sTOnOff = (STOnOff)((Object)this.get_store().find_attribute_user(CUSTOMSTYLE$50));
            return sTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCustomStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CUSTOMSTYLE$50) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCustomStyle(STOnOff.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CUSTOMSTYLE$50));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CUSTOMSTYLE$50));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCustomStyle(STOnOff sTOnOff) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STOnOff sTOnOff2 = null;
            sTOnOff2 = (STOnOff)((Object)this.get_store().find_attribute_user(CUSTOMSTYLE$50));
            if (sTOnOff2 == null) {
                sTOnOff2 = (STOnOff)((Object)this.get_store().add_attribute_user(CUSTOMSTYLE$50));
            }
            sTOnOff2.set(sTOnOff);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCustomStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CUSTOMSTYLE$50);
        }
    }
}

