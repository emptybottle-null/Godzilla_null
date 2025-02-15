/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfRule;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSqref;

public class CTConditionalFormattingImpl
extends XmlComplexContentImpl
implements CTConditionalFormatting {
    private static final long serialVersionUID = 1L;
    private static final QName CFRULE$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "cfRule");
    private static final QName EXTLST$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");
    private static final QName PIVOT$4 = new QName("", "pivot");
    private static final QName SQREF$6 = new QName("", "sqref");

    public CTConditionalFormattingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCfRule> getCfRuleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CfRuleList
            extends AbstractList<CTCfRule> {
                CfRuleList() {
                }

                @Override
                public CTCfRule get(int n) {
                    return CTConditionalFormattingImpl.this.getCfRuleArray(n);
                }

                @Override
                public CTCfRule set(int n, CTCfRule cTCfRule) {
                    CTCfRule cTCfRule2 = CTConditionalFormattingImpl.this.getCfRuleArray(n);
                    CTConditionalFormattingImpl.this.setCfRuleArray(n, cTCfRule);
                    return cTCfRule2;
                }

                @Override
                public void add(int n, CTCfRule cTCfRule) {
                    CTConditionalFormattingImpl.this.insertNewCfRule(n).set(cTCfRule);
                }

                @Override
                public CTCfRule remove(int n) {
                    CTCfRule cTCfRule = CTConditionalFormattingImpl.this.getCfRuleArray(n);
                    CTConditionalFormattingImpl.this.removeCfRule(n);
                    return cTCfRule;
                }

                @Override
                public int size() {
                    return CTConditionalFormattingImpl.this.sizeOfCfRuleArray();
                }
            }
            return new CfRuleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCfRule[] getCfRuleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CFRULE$0, arrayList);
            CTCfRule[] cTCfRuleArray = new CTCfRule[arrayList.size()];
            arrayList.toArray(cTCfRuleArray);
            return cTCfRuleArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCfRule getCfRuleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCfRule cTCfRule = null;
            cTCfRule = (CTCfRule)((Object)this.get_store().find_element_user(CFRULE$0, n));
            if (cTCfRule == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCfRule;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCfRuleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CFRULE$0);
        }
    }

    @Override
    public void setCfRuleArray(CTCfRule[] cTCfRuleArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTCfRuleArray, CFRULE$0);
    }

    @Override
    public void setCfRuleArray(int n, CTCfRule cTCfRule) {
        this.generatedSetterHelperImpl(cTCfRule, CFRULE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCfRule insertNewCfRule(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCfRule cTCfRule = null;
            cTCfRule = (CTCfRule)((Object)this.get_store().insert_element_user(CFRULE$0, n));
            return cTCfRule;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCfRule addNewCfRule() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCfRule cTCfRule = null;
            cTCfRule = (CTCfRule)((Object)this.get_store().add_element_user(CFRULE$0));
            return cTCfRule;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCfRule(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CFRULE$0, n);
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
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$2, 0);
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
            return this.get_store().count_elements(EXTLST$2) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$2, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$2);
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
            this.get_store().remove_element(EXTLST$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getPivot() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PIVOT$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(PIVOT$4));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetPivot() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(PIVOT$4));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(PIVOT$4);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPivot() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(PIVOT$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPivot(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PIVOT$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PIVOT$4));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPivot(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(PIVOT$4));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(PIVOT$4));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPivot() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(PIVOT$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List getSqref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SQREF$6));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getListValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STSqref xgetSqref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSqref sTSqref = null;
            sTSqref = (STSqref)((Object)this.get_store().find_attribute_user(SQREF$6));
            return sTSqref;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSqref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SQREF$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSqref(List list) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SQREF$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SQREF$6));
            }
            simpleValue.setListValue(list);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSqref(STSqref sTSqref) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSqref sTSqref2 = null;
            sTSqref2 = (STSqref)((Object)this.get_store().find_attribute_user(SQREF$6));
            if (sTSqref2 == null) {
                sTSqref2 = (STSqref)((Object)this.get_store().add_attribute_user(SQREF$6));
            }
            sTSqref2.set(sTSqref);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSqref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SQREF$6);
        }
    }
}

