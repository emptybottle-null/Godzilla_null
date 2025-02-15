/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerData
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerDataList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTagsData;

public class CTCustomerDataListImpl
extends XmlComplexContentImpl
implements CTCustomerDataList {
    private static final long serialVersionUID = 1L;
    private static final QName CUSTDATA$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "custData");
    private static final QName TAGS$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "tags");

    public CTCustomerDataListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCustomerData> getCustDataList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CustDataList
            extends AbstractList<CTCustomerData> {
                CustDataList() {
                }

                @Override
                public CTCustomerData get(int n) {
                    return CTCustomerDataListImpl.this.getCustDataArray(n);
                }

                @Override
                public CTCustomerData set(int n, CTCustomerData cTCustomerData) {
                    CTCustomerData cTCustomerData2 = CTCustomerDataListImpl.this.getCustDataArray(n);
                    CTCustomerDataListImpl.this.setCustDataArray(n, cTCustomerData);
                    return cTCustomerData2;
                }

                @Override
                public void add(int n, CTCustomerData cTCustomerData) {
                    CTCustomerDataListImpl.this.insertNewCustData(n).set((XmlObject)cTCustomerData);
                }

                @Override
                public CTCustomerData remove(int n) {
                    CTCustomerData cTCustomerData = CTCustomerDataListImpl.this.getCustDataArray(n);
                    CTCustomerDataListImpl.this.removeCustData(n);
                    return cTCustomerData;
                }

                @Override
                public int size() {
                    return CTCustomerDataListImpl.this.sizeOfCustDataArray();
                }
            }
            return new CustDataList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCustomerData[] getCustDataArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUSTDATA$0, arrayList);
            CTCustomerData[] cTCustomerDataArray = new CTCustomerData[arrayList.size()];
            arrayList.toArray(cTCustomerDataArray);
            return cTCustomerDataArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomerData getCustDataArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomerData cTCustomerData = null;
            cTCustomerData = (CTCustomerData)this.get_store().find_element_user(CUSTDATA$0, n);
            if (cTCustomerData == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCustomerData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCustDataArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTDATA$0);
        }
    }

    @Override
    public void setCustDataArray(CTCustomerData[] cTCustomerDataArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTCustomerDataArray, CUSTDATA$0);
    }

    @Override
    public void setCustDataArray(int n, CTCustomerData cTCustomerData) {
        this.generatedSetterHelperImpl((XmlObject)cTCustomerData, CUSTDATA$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomerData insertNewCustData(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomerData cTCustomerData = null;
            cTCustomerData = (CTCustomerData)this.get_store().insert_element_user(CUSTDATA$0, n);
            return cTCustomerData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomerData addNewCustData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomerData cTCustomerData = null;
            cTCustomerData = (CTCustomerData)this.get_store().add_element_user(CUSTDATA$0);
            return cTCustomerData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCustData(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTDATA$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTagsData getTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTagsData cTTagsData = null;
            cTTagsData = (CTTagsData)((Object)this.get_store().find_element_user(TAGS$2, 0));
            if (cTTagsData == null) {
                return null;
            }
            return cTTagsData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TAGS$2) != 0;
        }
    }

    @Override
    public void setTags(CTTagsData cTTagsData) {
        this.generatedSetterHelperImpl(cTTagsData, TAGS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTagsData addNewTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTagsData cTTagsData = null;
            cTTagsData = (CTTagsData)((Object)this.get_store().add_element_user(TAGS$2));
            return cTTagsData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TAGS$2, 0);
        }
    }
}

