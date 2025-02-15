/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument;

public class DownloadedSchemasDocumentImpl
extends XmlComplexContentImpl
implements DownloadedSchemasDocument {
    private static final long serialVersionUID = 1L;
    private static final QName DOWNLOADEDSCHEMAS$0 = new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "downloaded-schemas");

    public DownloadedSchemasDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DownloadedSchemasDocument.DownloadedSchemas getDownloadedSchemas() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DownloadedSchemasDocument.DownloadedSchemas target = null;
            target = (DownloadedSchemasDocument.DownloadedSchemas)((Object)this.get_store().find_element_user(DOWNLOADEDSCHEMAS$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setDownloadedSchemas(DownloadedSchemasDocument.DownloadedSchemas downloadedSchemas) {
        this.generatedSetterHelperImpl(downloadedSchemas, DOWNLOADEDSCHEMAS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DownloadedSchemasDocument.DownloadedSchemas addNewDownloadedSchemas() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DownloadedSchemasDocument.DownloadedSchemas target = null;
            target = (DownloadedSchemasDocument.DownloadedSchemas)((Object)this.get_store().add_element_user(DOWNLOADEDSCHEMAS$0));
            return target;
        }
    }

    public static class DownloadedSchemasImpl
    extends XmlComplexContentImpl
    implements DownloadedSchemasDocument.DownloadedSchemas {
        private static final long serialVersionUID = 1L;
        private static final QName ENTRY$0 = new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "entry");
        private static final QName DEFAULTDIRECTORY$2 = new QName("", "defaultDirectory");

        public DownloadedSchemasImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public DownloadedSchemaEntry[] getEntryArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList targetList = new ArrayList();
                this.get_store().find_all_element_users(ENTRY$0, targetList);
                DownloadedSchemaEntry[] result = new DownloadedSchemaEntry[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public DownloadedSchemaEntry getEntryArray(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                DownloadedSchemaEntry target = null;
                target = (DownloadedSchemaEntry)((Object)this.get_store().find_element_user(ENTRY$0, i));
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int sizeOfEntryArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(ENTRY$0);
            }
        }

        @Override
        public void setEntryArray(DownloadedSchemaEntry[] entryArray) {
            this.check_orphaned();
            this.arraySetterHelper(entryArray, ENTRY$0);
        }

        @Override
        public void setEntryArray(int i, DownloadedSchemaEntry entry) {
            this.generatedSetterHelperImpl(entry, ENTRY$0, i, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public DownloadedSchemaEntry insertNewEntry(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                DownloadedSchemaEntry target = null;
                target = (DownloadedSchemaEntry)((Object)this.get_store().insert_element_user(ENTRY$0, i));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public DownloadedSchemaEntry addNewEntry() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                DownloadedSchemaEntry target = null;
                target = (DownloadedSchemaEntry)((Object)this.get_store().add_element_user(ENTRY$0));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeEntry(int i) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(ENTRY$0, i);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getDefaultDirectory() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULTDIRECTORY$2));
                if (target == null) {
                    return null;
                }
                return target.getStringValue();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlToken xgetDefaultDirectory() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlToken target = null;
                target = (XmlToken)((Object)this.get_store().find_attribute_user(DEFAULTDIRECTORY$2));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetDefaultDirectory() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(DEFAULTDIRECTORY$2) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setDefaultDirectory(String defaultDirectory) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULTDIRECTORY$2));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(DEFAULTDIRECTORY$2));
                }
                target.setStringValue(defaultDirectory);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetDefaultDirectory(XmlToken defaultDirectory) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlToken target = null;
                target = (XmlToken)((Object)this.get_store().find_attribute_user(DEFAULTDIRECTORY$2));
                if (target == null) {
                    target = (XmlToken)((Object)this.get_store().add_attribute_user(DEFAULTDIRECTORY$2));
                }
                target.set(defaultDirectory);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetDefaultDirectory() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(DEFAULTDIRECTORY$2);
            }
        }
    }
}

