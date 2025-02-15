/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.support;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePropertiesPersister;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PropertiesPersister;

public abstract class PropertiesLoaderSupport {
    protected final Log logger = LogFactory.getLog(this.getClass());
    @Nullable
    protected Properties[] localProperties;
    protected boolean localOverride = false;
    @Nullable
    private Resource[] locations;
    private boolean ignoreResourceNotFound = false;
    @Nullable
    private String fileEncoding;
    private PropertiesPersister propertiesPersister = ResourcePropertiesPersister.INSTANCE;

    public void setProperties(Properties properties) {
        this.localProperties = new Properties[]{properties};
    }

    public void setPropertiesArray(Properties ... propertiesArray) {
        this.localProperties = propertiesArray;
    }

    public void setLocation(Resource location) {
        this.locations = new Resource[]{location};
    }

    public void setLocations(Resource ... locations) {
        this.locations = locations;
    }

    public void setLocalOverride(boolean localOverride) {
        this.localOverride = localOverride;
    }

    public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
        this.ignoreResourceNotFound = ignoreResourceNotFound;
    }

    public void setFileEncoding(String encoding) {
        this.fileEncoding = encoding;
    }

    public void setPropertiesPersister(@Nullable PropertiesPersister propertiesPersister) {
        this.propertiesPersister = propertiesPersister != null ? propertiesPersister : ResourcePropertiesPersister.INSTANCE;
    }

    protected Properties mergeProperties() throws IOException {
        Properties result = new Properties();
        if (this.localOverride) {
            this.loadProperties(result);
        }
        if (this.localProperties != null) {
            for (Properties localProp : this.localProperties) {
                CollectionUtils.mergePropertiesIntoMap(localProp, result);
            }
        }
        if (!this.localOverride) {
            this.loadProperties(result);
        }
        return result;
    }

    protected void loadProperties(Properties props) throws IOException {
        if (this.locations != null) {
            for (Resource location : this.locations) {
                if (this.logger.isTraceEnabled()) {
                    this.logger.trace("Loading properties file from " + location);
                }
                try {
                    PropertiesLoaderUtils.fillProperties(props, new EncodedResource(location, this.fileEncoding), this.propertiesPersister);
                } catch (FileNotFoundException | SocketException | UnknownHostException ex) {
                    if (this.ignoreResourceNotFound) {
                        if (!this.logger.isDebugEnabled()) continue;
                        this.logger.debug("Properties resource not found: " + ex.getMessage());
                        continue;
                    }
                    throw ex;
                }
            }
        }
    }
}

