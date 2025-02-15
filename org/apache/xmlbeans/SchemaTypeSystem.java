/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.File;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;

public interface SchemaTypeSystem
extends SchemaTypeLoader {
    public String getName();

    public SchemaType[] globalTypes();

    public SchemaType[] documentTypes();

    public SchemaType[] attributeTypes();

    public SchemaGlobalElement[] globalElements();

    public SchemaGlobalAttribute[] globalAttributes();

    public SchemaModelGroup[] modelGroups();

    public SchemaAttributeGroup[] attributeGroups();

    public SchemaAnnotation[] annotations();

    public void resolve();

    public SchemaComponent resolveHandle(String var1);

    public SchemaType typeForHandle(String var1);

    public ClassLoader getClassLoader();

    public void saveToDirectory(File var1);

    public void save(Filer var1);
}

