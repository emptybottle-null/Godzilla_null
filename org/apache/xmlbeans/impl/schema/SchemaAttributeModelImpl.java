/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaLocalAttribute;

public class SchemaAttributeModelImpl
implements SchemaAttributeModel {
    private Map attrMap = new LinkedHashMap();
    private QNameSet wcSet;
    private int wcProcess;
    private static final SchemaLocalAttribute[] EMPTY_SLA_ARRAY = new SchemaLocalAttribute[0];

    public SchemaAttributeModelImpl() {
        this.wcSet = null;
        this.wcProcess = 0;
    }

    public SchemaAttributeModelImpl(SchemaAttributeModel sam) {
        if (sam == null) {
            this.wcSet = null;
            this.wcProcess = 0;
        } else {
            SchemaLocalAttribute[] attrs = sam.getAttributes();
            for (int i = 0; i < attrs.length; ++i) {
                this.attrMap.put(attrs[i].getName(), attrs[i]);
            }
            if (sam.getWildcardProcess() != 0) {
                this.wcSet = sam.getWildcardSet();
                this.wcProcess = sam.getWildcardProcess();
            }
        }
    }

    @Override
    public SchemaLocalAttribute[] getAttributes() {
        return this.attrMap.values().toArray(EMPTY_SLA_ARRAY);
    }

    @Override
    public SchemaLocalAttribute getAttribute(QName name) {
        return (SchemaLocalAttribute)this.attrMap.get(name);
    }

    public void addAttribute(SchemaLocalAttribute attruse) {
        this.attrMap.put(attruse.getName(), attruse);
    }

    public void removeProhibitedAttribute(QName name) {
        this.attrMap.remove(name);
    }

    @Override
    public QNameSet getWildcardSet() {
        return this.wcSet == null ? QNameSet.EMPTY : this.wcSet;
    }

    public void setWildcardSet(QNameSet set) {
        this.wcSet = set;
    }

    @Override
    public int getWildcardProcess() {
        return this.wcProcess;
    }

    public void setWildcardProcess(int proc) {
        this.wcProcess = proc;
    }
}

