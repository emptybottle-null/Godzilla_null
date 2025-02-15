/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.filter;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.lang.Nullable;

public abstract class AbstractTypeHierarchyTraversingFilter
implements TypeFilter {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private final boolean considerInherited;
    private final boolean considerInterfaces;

    protected AbstractTypeHierarchyTraversingFilter(boolean considerInherited, boolean considerInterfaces) {
        this.considerInherited = considerInherited;
        this.considerInterfaces = considerInterfaces;
    }

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        ClassMetadata metadata;
        block15: {
            String superClassName;
            if (this.matchSelf(metadataReader)) {
                return true;
            }
            metadata = metadataReader.getClassMetadata();
            if (this.matchClassName(metadata.getClassName())) {
                return true;
            }
            if (this.considerInherited && (superClassName = metadata.getSuperClassName()) != null) {
                Boolean superClassMatch = this.matchSuperClass(superClassName);
                if (superClassMatch != null) {
                    if (superClassMatch.booleanValue()) {
                        return true;
                    }
                } else {
                    try {
                        if (this.match(metadata.getSuperClassName(), metadataReaderFactory)) {
                            return true;
                        }
                    } catch (IOException ex) {
                        if (!this.logger.isDebugEnabled()) break block15;
                        this.logger.debug("Could not read super class [" + metadata.getSuperClassName() + "] of type-filtered class [" + metadata.getClassName() + "]");
                    }
                }
            }
        }
        if (this.considerInterfaces) {
            for (String ifc : metadata.getInterfaceNames()) {
                Boolean interfaceMatch = this.matchInterface(ifc);
                if (interfaceMatch != null) {
                    if (!interfaceMatch.booleanValue()) continue;
                    return true;
                }
                try {
                    if (this.match(ifc, metadataReaderFactory)) {
                        return true;
                    }
                } catch (IOException ex) {
                    if (!this.logger.isDebugEnabled()) continue;
                    this.logger.debug("Could not read interface [" + ifc + "] for type-filtered class [" + metadata.getClassName() + "]");
                }
            }
        }
        return false;
    }

    private boolean match(String className, MetadataReaderFactory metadataReaderFactory) throws IOException {
        return this.match(metadataReaderFactory.getMetadataReader(className), metadataReaderFactory);
    }

    protected boolean matchSelf(MetadataReader metadataReader) {
        return false;
    }

    protected boolean matchClassName(String className) {
        return false;
    }

    @Nullable
    protected Boolean matchSuperClass(String superClassName) {
        return null;
    }

    @Nullable
    protected Boolean matchInterface(String interfaceName) {
        return null;
    }
}

