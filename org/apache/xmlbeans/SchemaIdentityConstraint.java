/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.util.Map;
import org.apache.xmlbeans.SchemaAnnotated;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaTypeSystem;

public interface SchemaIdentityConstraint
extends SchemaComponent,
SchemaAnnotated {
    public static final int CC_KEY = 1;
    public static final int CC_KEYREF = 2;
    public static final int CC_UNIQUE = 3;

    public String getSelector();

    public Object getSelectorPath();

    public String[] getFields();

    public Object getFieldPath(int var1);

    public Map getNSMap();

    public int getConstraintCategory();

    public SchemaIdentityConstraint getReferencedKey();

    public Object getUserData();

    public static final class Ref
    extends SchemaComponent.Ref {
        public Ref(SchemaIdentityConstraint idc) {
            super(idc);
        }

        public Ref(SchemaTypeSystem system, String handle) {
            super(system, handle);
        }

        @Override
        public final int getComponentType() {
            return 5;
        }

        public final SchemaIdentityConstraint get() {
            return (SchemaIdentityConstraint)this.getComponent();
        }
    }
}

