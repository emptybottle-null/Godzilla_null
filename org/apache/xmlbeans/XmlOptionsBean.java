/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlOptionCharEscapeMap;
import org.apache.xmlbeans.XmlOptions;
import org.xml.sax.EntityResolver;

public class XmlOptionsBean
extends XmlOptions {
    public XmlOptionsBean() {
    }

    public XmlOptionsBean(XmlOptions other) {
        super(other);
    }

    public void setSaveNamespacesFirst(boolean b) {
        if (b) {
            super.setSaveNamespacesFirst();
        } else {
            this.remove("SAVE_NAMESPACES_FIRST");
        }
    }

    public boolean isSaveNamespacesFirst() {
        return this.hasOption("SAVE_NAMESPACES_FIRST");
    }

    public void setSavePrettyPrint(boolean b) {
        if (b) {
            super.setSavePrettyPrint();
        } else {
            this.remove("SAVE_PRETTY_PRINT");
        }
    }

    public boolean isSavePrettyPrint() {
        return this.hasOption("SAVE_PRETTY_PRINT");
    }

    public Integer getSavePrettyPrintIndent() {
        return (Integer)this.get("SAVE_PRETTY_PRINT_INDENT");
    }

    public Integer getSavePrettyPrintOffset() {
        return (Integer)this.get("SAVE_PRETTY_PRINT_OFFSET");
    }

    public String getCharacterEncoding() {
        return (String)this.get("CHARACTER_ENCODING");
    }

    public SchemaType getDocumentType() {
        return (SchemaType)this.get("DOCUMENT_TYPE");
    }

    public void setSaveAggressiveNamespaces(boolean b) {
        if (b) {
            super.setSaveAggressiveNamespaces();
        } else {
            this.remove("SAVE_AGGRESSIVE_NAMESPACES");
        }
    }

    public boolean isSaveAggressiveNamespaces() {
        return this.hasOption("SAVE_AGGRESSIVE_NAMESPACES");
    }

    public QName getSaveSyntheticDocumentElement() {
        return (QName)this.get("SAVE_SYNTHETIC_DOCUMENT_ELEMENT");
    }

    public void setUseDefaultNamespace(boolean b) {
        if (b) {
            super.setUseDefaultNamespace();
        } else {
            this.remove("SAVE_USE_DEFAULT_NAMESPACE");
        }
    }

    public boolean isUseDefaultNamespace() {
        return this.hasOption("SAVE_USE_DEFAULT_NAMESPACE");
    }

    public Map getSaveImplicitNamespaces() {
        return (Map)this.get("SAVE_IMPLICIT_NAMESPACES");
    }

    public Map getSaveSuggestedPrefixes() {
        return (Map)this.get("SAVE_SUGGESTED_PREFIXES");
    }

    public String getSaveFilterProcinst() {
        return (String)this.get("SAVE_FILTER_PROCINST");
    }

    public XmlOptionCharEscapeMap getSaveSubstituteCharacters() {
        return (XmlOptionCharEscapeMap)this.get("SAVE_SUBSTITUTE_CHARACTERS");
    }

    public void setSaveUseOpenFrag(boolean b) {
        if (b) {
            super.setSaveUseOpenFrag();
        } else {
            this.remove("SAVE_USE_OPEN_FRAGMENT");
        }
    }

    public boolean isSaveUseOpenFrag() {
        return this.hasOption("SAVE_USE_OPEN_FRAGMENT");
    }

    public void setSaveOuter(boolean b) {
        if (b) {
            super.setSaveOuter();
        } else {
            this.remove("SAVE_OUTER");
        }
    }

    public boolean isSaveOuter() {
        return this.hasOption("SAVE_OUTER");
    }

    public void setSaveInner(boolean b) {
        if (b) {
            super.setSaveInner();
        } else {
            this.remove("SAVE_INNER");
        }
    }

    public boolean isSaveInner() {
        return this.hasOption("SAVE_INNER");
    }

    public void setSaveNoXmlDecl(boolean b) {
        if (b) {
            super.setSaveNoXmlDecl();
        } else {
            this.remove("SAVE_NO_XML_DECL");
        }
    }

    public boolean isSaveNoXmlDecl() {
        return this.hasOption("SAVE_NO_XML_DECL");
    }

    public Integer getSaveCDataLengthThreshold() {
        return (Integer)this.get("SAVE_CDATA_LENGTH_THRESHOLD");
    }

    public Integer getSaveCDataEntityCountThreshold() {
        return (Integer)this.get("SAVE_CDATA_ENTITY_COUNT_THRESHOLD");
    }

    public void setSaveSaxNoNSDeclsInAttributes(boolean b) {
        if (b) {
            super.setSaveSaxNoNSDeclsInAttributes();
        } else {
            this.remove("SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES");
        }
    }

    public boolean isSaveSaxNoNSDeclsInAttributes() {
        return this.hasOption("SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES");
    }

    public QName getLoadReplaceDocumentElement() {
        return (QName)this.get("LOAD_REPLACE_DOCUMENT_ELEMENT");
    }

    public void setLoadStripWhitespace(boolean b) {
        if (b) {
            super.setLoadStripWhitespace();
        } else {
            this.remove("LOAD_STRIP_WHITESPACE");
        }
    }

    public boolean isSetLoadStripWhitespace() {
        return this.hasOption("LOAD_STRIP_WHITESPACE");
    }

    public void setLoadStripComments(boolean b) {
        if (b) {
            super.setLoadStripComments();
        } else {
            this.remove("LOAD_STRIP_COMMENTS");
        }
    }

    public boolean isLoadStripComments() {
        return this.hasOption("LOAD_STRIP_COMMENTS");
    }

    public void setLoadStripProcinsts(boolean b) {
        if (b) {
            super.setLoadStripProcinsts();
        } else {
            this.remove("LOAD_STRIP_PROCINSTS");
        }
    }

    public boolean isLoadStripProcinsts() {
        return this.hasOption("LOAD_STRIP_PROCINSTS");
    }

    public void setLoadLineNumbers(boolean b) {
        if (b) {
            super.setLoadLineNumbers();
        } else {
            this.remove("LOAD_LINE_NUMBERS");
        }
    }

    public boolean isLoadLineNumbers() {
        return this.hasOption("LOAD_LINE_NUMBERS");
    }

    public Map getLoadSubstituteNamespaces() {
        return (Map)this.get("LOAD_SUBSTITUTE_NAMESPACES");
    }

    public void setLoadTrimTextBuffer(boolean b) {
        if (b) {
            super.setLoadTrimTextBuffer();
        } else {
            this.remove("LOAD_TRIM_TEXT_BUFFER");
        }
    }

    public boolean isLoadTrimTextBuffer() {
        return this.hasOption("LOAD_TRIM_TEXT_BUFFER");
    }

    public Map getLoadAdditionalNamespaces() {
        return (Map)this.get("LOAD_ADDITIONAL_NAMESPACES");
    }

    public void setLoadMessageDigest(boolean b) {
        if (b) {
            super.setLoadMessageDigest();
        } else {
            this.remove("LOAD_MESSAGE_DIGEST");
        }
    }

    public boolean isLoadMessageDigest() {
        return this.hasOption("LOAD_MESSAGE_DIGEST");
    }

    public void setLoadUseDefaultResolver(boolean b) {
        if (b) {
            super.setLoadUseDefaultResolver();
        } else {
            this.remove("LOAD_USE_DEFAULT_RESOLVER");
        }
    }

    public boolean isLoadUseDefaultResolver() {
        return this.hasOption("LOAD_USE_DEFAULT_RESOLVER");
    }

    public String getXqueryCurrentNodeVar() {
        return (String)this.get("XQUERY_CURRENT_NODE_VAR");
    }

    public Map getXqueryVariables() {
        return (Map)this.get("XQUERY_VARIABLE_MAP");
    }

    public String getDocumentSourceName() {
        return (String)this.get("DOCUMENT_SOURCE_NAME");
    }

    public Map getCompileSubstituteNames() {
        return (Map)this.get("COMPILE_SUBSTITUTE_NAMES");
    }

    public void setCompileNoUpaRule(boolean b) {
        if (b) {
            super.setCompileNoUpaRule();
        } else {
            this.remove("COMPILE_NO_UPA_RULE");
        }
    }

    public boolean isCompileNoUpaRule() {
        return this.hasOption("COMPILE_NO_UPA_RULE");
    }

    public void setCompileNoPvrRule(boolean b) {
        if (b) {
            super.setCompileNoPvrRule();
        } else {
            this.remove("COMPILE_NO_PVR_RULE");
        }
    }

    public boolean isCompileNoPvrRule() {
        return this.hasOption("COMPILE_NO_PVR_RULE");
    }

    public void setCompileNoAnnotations(boolean b) {
        if (b) {
            super.setCompileNoAnnotations();
        } else {
            this.remove("COMPILE_NO_ANNOTATIONS");
        }
    }

    public boolean isCompileNoAnnotations() {
        return this.hasOption("COMPILE_NO_ANNOTATIONS");
    }

    public void setCompileDownloadUrls(boolean b) {
        if (b) {
            super.setCompileDownloadUrls();
        } else {
            this.remove("COMPILE_DOWNLOAD_URLS");
        }
    }

    public boolean isCompileDownloadUrls() {
        return this.hasOption("COMPILE_DOWNLOAD_URLS");
    }

    public Set getCompileMdefNamespaces() {
        return (Set)this.get("COMPILE_MDEF_NAMESPACES");
    }

    public void setValidateOnSet(boolean b) {
        if (b) {
            super.setValidateOnSet();
        } else {
            this.remove("VALIDATE_ON_SET");
        }
    }

    public boolean isValidateOnSet() {
        return this.hasOption("VALIDATE_ON_SET");
    }

    public void setValidateTreatLaxAsSkip(boolean b) {
        if (b) {
            super.setValidateTreatLaxAsSkip();
        } else {
            this.remove("VALIDATE_TREAT_LAX_AS_SKIP");
        }
    }

    public boolean isValidateTreatLaxAsSkip() {
        return this.hasOption("VALIDATE_TREAT_LAX_AS_SKIP");
    }

    public void setValidateStrict(boolean b) {
        if (b) {
            super.setValidateStrict();
        } else {
            this.remove("VALIDATE_STRICT");
        }
    }

    public boolean isValidateStrict() {
        return this.hasOption("VALIDATE_STRICT");
    }

    public void setUnsynchronized(boolean b) {
        if (b) {
            super.setUnsynchronized();
        } else {
            this.remove("UNSYNCHRONIZED");
        }
    }

    public boolean isUnsynchronized() {
        return this.hasOption("UNSYNCHRONIZED");
    }

    public EntityResolver getEntityResolver() {
        return (EntityResolver)this.get("ENTITY_RESOLVER");
    }

    public String getGenerateJavaVersion() {
        return (String)this.get("GENERATE_JAVA_VERSION");
    }

    public int getEntityExpansionLimit() {
        Integer limit = (Integer)this.get("ENTITY_EXPANSION_LIMIT");
        return limit == null ? 2048 : limit;
    }

    public boolean isLoadDTDGrammar() {
        Boolean flag = (Boolean)this.get("LOAD_DTD_GRAMMAR");
        return flag == null ? false : flag;
    }

    public boolean isLoadExternalDTD() {
        Boolean flag = (Boolean)this.get("LOAD_EXTERNAL_DTD");
        return flag == null ? false : flag;
    }
}

