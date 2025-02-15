/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml;

public class LoaderOptions {
    private boolean allowDuplicateKeys = true;
    private boolean wrappedToRootException = false;
    private int maxAliasesForCollections = 50;
    private boolean allowRecursiveKeys = false;
    private boolean processComments = false;
    private boolean enumCaseSensitive = true;

    public boolean isAllowDuplicateKeys() {
        return this.allowDuplicateKeys;
    }

    public void setAllowDuplicateKeys(boolean allowDuplicateKeys) {
        this.allowDuplicateKeys = allowDuplicateKeys;
    }

    public boolean isWrappedToRootException() {
        return this.wrappedToRootException;
    }

    public void setWrappedToRootException(boolean wrappedToRootException) {
        this.wrappedToRootException = wrappedToRootException;
    }

    public int getMaxAliasesForCollections() {
        return this.maxAliasesForCollections;
    }

    public void setMaxAliasesForCollections(int maxAliasesForCollections) {
        this.maxAliasesForCollections = maxAliasesForCollections;
    }

    public void setAllowRecursiveKeys(boolean allowRecursiveKeys) {
        this.allowRecursiveKeys = allowRecursiveKeys;
    }

    public boolean getAllowRecursiveKeys() {
        return this.allowRecursiveKeys;
    }

    public void setProcessComments(boolean processComments) {
        this.processComments = processComments;
    }

    public boolean isProcessComments() {
        return this.processComments;
    }

    public boolean isEnumCaseSensitive() {
        return this.enumCaseSensitive;
    }

    public void setEnumCaseSensitive(boolean enumCaseSensitive) {
        this.enumCaseSensitive = enumCaseSensitive;
    }
}

