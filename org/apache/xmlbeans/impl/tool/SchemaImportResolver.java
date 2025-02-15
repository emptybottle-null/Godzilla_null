/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.tool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

public abstract class SchemaImportResolver {
    public abstract SchemaResource lookupResource(String var1, String var2);

    public abstract void reportActualNamespace(SchemaResource var1, String var2);

    /*
     * Unable to fully structure code
     */
    protected final void resolveImports(SchemaResource[] resources) {
        queueOfResources = new LinkedList<SchemaResource>(Arrays.asList(resources));
        queueOfLocators = new LinkedList<SchemaLocator>();
        seenResources = new HashSet<SchemaResource>();
        block0: while (true) {
            if (!queueOfResources.isEmpty()) {
                nextResource = queueOfResources.removeFirst();
            } else {
                if (queueOfLocators.isEmpty()) break;
                locator = (SchemaLocator)queueOfLocators.removeFirst();
                nextResource = this.lookupResource(locator.namespace, locator.schemaLocation);
                if (nextResource == null) continue;
            }
            if (seenResources.contains(nextResource)) continue;
            seenResources.add(nextResource);
            schema = nextResource.getSchema();
            if (schema == null) continue;
            actualTargetNamespace = schema.getTargetNamespace();
            if (actualTargetNamespace == null) {
                actualTargetNamespace = "";
            }
            if ((expectedTargetNamespace = nextResource.getNamespace()) == null || !actualTargetNamespace.equals(expectedTargetNamespace)) {
                this.reportActualNamespace(nextResource, actualTargetNamespace);
            }
            schemaImports = schema.getImportArray();
            for (i = 0; i < schemaImports.length; ++i) {
                queueOfLocators.add(new SchemaLocator(schemaImports[i].getNamespace() == null ? "" : schemaImports[i].getNamespace(), schemaImports[i].getSchemaLocation()));
            }
            schemaIncludes = schema.getIncludeArray();
            i = 0;
            while (true) {
                if (i < schemaIncludes.length) ** break;
                continue block0;
                queueOfLocators.add(new SchemaLocator(null, schemaIncludes[i].getSchemaLocation()));
                ++i;
            }
            break;
        }
    }

    private static class SchemaLocator {
        public final String namespace;
        public final String schemaLocation;

        public SchemaLocator(String namespace, String schemaLocation) {
            this.namespace = namespace;
            this.schemaLocation = schemaLocation;
        }
    }

    public static interface SchemaResource {
        public SchemaDocument.Schema getSchema();

        public String getNamespace();

        public String getSchemaLocation();
    }
}

