/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.type;

public class TypeDeclaration {
    private String pkg;
    private String apiName;
    private String jsName;
    private boolean staticsOnly;
    private boolean supportsBeanProperties;

    public TypeDeclaration(String pkg, String apiName, String jsName, boolean staticsOnly, boolean supportsBeanProperties) {
        this.staticsOnly = staticsOnly;
        this.pkg = pkg;
        this.apiName = apiName;
        this.jsName = jsName;
        this.supportsBeanProperties = supportsBeanProperties;
    }

    public TypeDeclaration(String pkg, String apiName, String jsName, boolean staticsOnly) {
        this(pkg, apiName, jsName, staticsOnly, true);
    }

    public TypeDeclaration(String pkg, String apiName, String jsName) {
        this(pkg, apiName, jsName, false, true);
    }

    public String getPackageName() {
        return this.pkg;
    }

    public String getAPITypeName() {
        return this.apiName;
    }

    public String getJSName() {
        return this.jsName;
    }

    public String getQualifiedName() {
        return this.pkg != null && this.pkg.length() > 0 ? this.pkg + '.' + this.apiName : this.apiName;
    }

    public boolean isQualified() {
        return this.getQualifiedName().indexOf(46) != -1;
    }

    public boolean isStaticsOnly() {
        return this.staticsOnly;
    }

    public void setStaticsOnly(boolean staticsOnly) {
        this.staticsOnly = staticsOnly;
    }

    public void setSupportsBeanProperties(boolean supportsBeanProperties) {
        this.supportsBeanProperties = supportsBeanProperties;
    }

    public boolean supportsBeanProperties() {
        return this.supportsBeanProperties;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        if (obj instanceof TypeDeclaration) {
            TypeDeclaration dec = (TypeDeclaration)obj;
            return this.getQualifiedName().equals(dec.getQualifiedName()) && this.isStaticsOnly() == dec.isStaticsOnly();
        }
        return super.equals(obj);
    }

    public int hashCode() {
        int hash = 7;
        hash = 31 * Boolean.valueOf(this.staticsOnly).hashCode();
        hash = 31 * hash + this.getQualifiedName().hashCode();
        return hash;
    }
}

