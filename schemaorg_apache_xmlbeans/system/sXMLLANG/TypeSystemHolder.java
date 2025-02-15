/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package schemaorg_apache_xmlbeans.system.sXMLLANG;

import org.apache.xmlbeans.SchemaTypeSystem;

public class TypeSystemHolder {
    public static final SchemaTypeSystem typeSystem = TypeSystemHolder.loadTypeSystem();

    private TypeSystemHolder() {
    }

    private static final SchemaTypeSystem loadTypeSystem() {
        try {
            return (SchemaTypeSystem)Class.forName("org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl", true, TypeSystemHolder.class.getClassLoader()).getConstructor(Class.class).newInstance(TypeSystemHolder.class);
        } catch (ClassNotFoundException classNotFoundException) {
            throw new RuntimeException("Cannot load org.apache.xmlbeans.impl.SchemaTypeSystemImpl: make sure xbean.jar is on the classpath.", classNotFoundException);
        } catch (Exception exception) {
            throw new RuntimeException("Could not instantiate SchemaTypeSystemImpl (" + exception.toString() + "): is the version of xbean.jar correct?", exception);
        }
    }
}

