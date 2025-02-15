/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc;

import java.util.Date;
import java.util.Optional;

public interface PackageProperties {
    public static final String NAMESPACE_DCTERMS = "http://purl.org/dc/terms/";
    public static final String NAMESPACE_DC = "http://purl.org/dc/elements/1.1/";

    public Optional<String> getCategoryProperty();

    public void setCategoryProperty(String var1);

    public void setCategoryProperty(Optional<String> var1);

    public Optional<String> getContentStatusProperty();

    public void setContentStatusProperty(String var1);

    public void setContentStatusProperty(Optional<String> var1);

    public Optional<String> getContentTypeProperty();

    public void setContentTypeProperty(String var1);

    public void setContentTypeProperty(Optional<String> var1);

    public Optional<Date> getCreatedProperty();

    public void setCreatedProperty(String var1);

    public void setCreatedProperty(Optional<Date> var1);

    public Optional<String> getCreatorProperty();

    public void setCreatorProperty(String var1);

    public void setCreatorProperty(Optional<String> var1);

    public Optional<String> getDescriptionProperty();

    public void setDescriptionProperty(String var1);

    public void setDescriptionProperty(Optional<String> var1);

    public Optional<String> getIdentifierProperty();

    public void setIdentifierProperty(String var1);

    public void setIdentifierProperty(Optional<String> var1);

    public Optional<String> getKeywordsProperty();

    public void setKeywordsProperty(String var1);

    public void setKeywordsProperty(Optional<String> var1);

    public Optional<String> getLanguageProperty();

    public void setLanguageProperty(String var1);

    public void setLanguageProperty(Optional<String> var1);

    public Optional<String> getLastModifiedByProperty();

    public void setLastModifiedByProperty(String var1);

    public void setLastModifiedByProperty(Optional<String> var1);

    public Optional<Date> getLastPrintedProperty();

    public void setLastPrintedProperty(String var1);

    public void setLastPrintedProperty(Optional<Date> var1);

    public Optional<Date> getModifiedProperty();

    public void setModifiedProperty(String var1);

    public void setModifiedProperty(Optional<Date> var1);

    public Optional<String> getRevisionProperty();

    public void setRevisionProperty(String var1);

    public void setRevisionProperty(Optional<String> var1);

    public Optional<String> getSubjectProperty();

    public void setSubjectProperty(String var1);

    public void setSubjectProperty(Optional<String> var1);

    public Optional<String> getTitleProperty();

    public void setTitleProperty(String var1);

    public void setTitleProperty(Optional<String> var1);

    public Optional<String> getVersionProperty();

    public void setVersionProperty(String var1);

    public void setVersionProperty(Optional<String> var1);
}

