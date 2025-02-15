/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAnnotated;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaTypeSystem;

public interface SchemaModelGroup
extends SchemaComponent,
SchemaAnnotated {
    @Override
    public int getComponentType();

    @Override
    public QName getName();

    public Object getUserData();

    public static final class Ref
    extends SchemaComponent.Ref {
        public Ref(SchemaModelGroup modelGroup) {
            super(modelGroup);
        }

        public Ref(SchemaTypeSystem system, String handle) {
            super(system, handle);
        }

        @Override
        public final int getComponentType() {
            return 6;
        }

        public final SchemaModelGroup get() {
            return (SchemaModelGroup)this.getComponent();
        }
    }
}

