/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaTypeSystem;

public interface SchemaComponent {
    public static final int TYPE = 0;
    public static final int ELEMENT = 1;
    public static final int ATTRIBUTE = 3;
    public static final int ATTRIBUTE_GROUP = 4;
    public static final int IDENTITY_CONSTRAINT = 5;
    public static final int MODEL_GROUP = 6;
    public static final int NOTATION = 7;
    public static final int ANNOTATION = 8;

    public int getComponentType();

    public SchemaTypeSystem getTypeSystem();

    public QName getName();

    public String getSourceName();

    public Ref getComponentRef();

    public static abstract class Ref {
        private volatile SchemaComponent _schemaComponent;
        private SchemaTypeSystem _schemaTypeSystem;
        public String _handle;

        protected Ref(SchemaComponent schemaComponent) {
            this._schemaComponent = schemaComponent;
        }

        protected Ref(SchemaTypeSystem schemaTypeSystem, String handle) {
            assert (handle != null);
            this._schemaTypeSystem = schemaTypeSystem;
            this._handle = handle;
        }

        public abstract int getComponentType();

        public final SchemaTypeSystem getTypeSystem() {
            return this._schemaTypeSystem;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public final SchemaComponent getComponent() {
            if (this._schemaComponent == null && this._handle != null) {
                Ref ref = this;
                synchronized (ref) {
                    if (this._schemaComponent == null && this._handle != null) {
                        this._schemaComponent = this._schemaTypeSystem.resolveHandle(this._handle);
                        this._schemaTypeSystem = null;
                    }
                }
            }
            return this._schemaComponent;
        }
    }
}

