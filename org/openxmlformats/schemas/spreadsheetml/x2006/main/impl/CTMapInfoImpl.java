/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMapInfo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSchema;

public class CTMapInfoImpl
extends XmlComplexContentImpl
implements CTMapInfo {
    private static final long serialVersionUID = 1L;
    private static final QName SCHEMA$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "Schema");
    private static final QName MAP$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "Map");
    private static final QName SELECTIONNAMESPACES$4 = new QName("", "SelectionNamespaces");

    public CTMapInfoImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSchema> getSchemaList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SchemaList
            extends AbstractList<CTSchema> {
                SchemaList() {
                }

                @Override
                public CTSchema get(int n) {
                    return CTMapInfoImpl.this.getSchemaArray(n);
                }

                @Override
                public CTSchema set(int n, CTSchema cTSchema) {
                    CTSchema cTSchema2 = CTMapInfoImpl.this.getSchemaArray(n);
                    CTMapInfoImpl.this.setSchemaArray(n, cTSchema);
                    return cTSchema2;
                }

                @Override
                public void add(int n, CTSchema cTSchema) {
                    CTMapInfoImpl.this.insertNewSchema(n).set(cTSchema);
                }

                @Override
                public CTSchema remove(int n) {
                    CTSchema cTSchema = CTMapInfoImpl.this.getSchemaArray(n);
                    CTMapInfoImpl.this.removeSchema(n);
                    return cTSchema;
                }

                @Override
                public int size() {
                    return CTMapInfoImpl.this.sizeOfSchemaArray();
                }
            }
            return new SchemaList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSchema[] getSchemaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCHEMA$0, arrayList);
            CTSchema[] cTSchemaArray = new CTSchema[arrayList.size()];
            arrayList.toArray(cTSchemaArray);
            return cTSchemaArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchema getSchemaArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchema cTSchema = null;
            cTSchema = (CTSchema)((Object)this.get_store().find_element_user(SCHEMA$0, n));
            if (cTSchema == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSchema;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSchemaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCHEMA$0);
        }
    }

    @Override
    public void setSchemaArray(CTSchema[] cTSchemaArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSchemaArray, SCHEMA$0);
    }

    @Override
    public void setSchemaArray(int n, CTSchema cTSchema) {
        this.generatedSetterHelperImpl(cTSchema, SCHEMA$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchema insertNewSchema(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchema cTSchema = null;
            cTSchema = (CTSchema)((Object)this.get_store().insert_element_user(SCHEMA$0, n));
            return cTSchema;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchema addNewSchema() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchema cTSchema = null;
            cTSchema = (CTSchema)((Object)this.get_store().add_element_user(SCHEMA$0));
            return cTSchema;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSchema(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCHEMA$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMap> getMapList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MapList
            extends AbstractList<CTMap> {
                MapList() {
                }

                @Override
                public CTMap get(int n) {
                    return CTMapInfoImpl.this.getMapArray(n);
                }

                @Override
                public CTMap set(int n, CTMap cTMap) {
                    CTMap cTMap2 = CTMapInfoImpl.this.getMapArray(n);
                    CTMapInfoImpl.this.setMapArray(n, cTMap);
                    return cTMap2;
                }

                @Override
                public void add(int n, CTMap cTMap) {
                    CTMapInfoImpl.this.insertNewMap(n).set(cTMap);
                }

                @Override
                public CTMap remove(int n) {
                    CTMap cTMap = CTMapInfoImpl.this.getMapArray(n);
                    CTMapInfoImpl.this.removeMap(n);
                    return cTMap;
                }

                @Override
                public int size() {
                    return CTMapInfoImpl.this.sizeOfMapArray();
                }
            }
            return new MapList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMap[] getMapArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MAP$2, arrayList);
            CTMap[] cTMapArray = new CTMap[arrayList.size()];
            arrayList.toArray(cTMapArray);
            return cTMapArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMap getMapArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMap cTMap = null;
            cTMap = (CTMap)((Object)this.get_store().find_element_user(MAP$2, n));
            if (cTMap == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMapArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAP$2);
        }
    }

    @Override
    public void setMapArray(CTMap[] cTMapArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMapArray, MAP$2);
    }

    @Override
    public void setMapArray(int n, CTMap cTMap) {
        this.generatedSetterHelperImpl(cTMap, MAP$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMap insertNewMap(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMap cTMap = null;
            cTMap = (CTMap)((Object)this.get_store().insert_element_user(MAP$2, n));
            return cTMap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMap addNewMap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMap cTMap = null;
            cTMap = (CTMap)((Object)this.get_store().add_element_user(MAP$2));
            return cTMap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMap(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAP$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSelectionNamespaces() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SELECTIONNAMESPACES$4));
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
    public XmlString xgetSelectionNamespaces() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(SELECTIONNAMESPACES$4));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSelectionNamespaces(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SELECTIONNAMESPACES$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SELECTIONNAMESPACES$4));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSelectionNamespaces(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(SELECTIONNAMESPACES$4));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(SELECTIONNAMESPACES$4));
            }
            xmlString2.set(xmlString);
        }
    }
}

