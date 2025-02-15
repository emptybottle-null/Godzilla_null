/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ErrorReporter;

public class ShellContextFactory
extends ContextFactory {
    private boolean strictMode;
    private boolean warningAsError;
    private int languageVersion = 180;
    private int optimizationLevel;
    private boolean generatingDebug;
    private boolean allowReservedKeywords = true;
    private ErrorReporter errorReporter;
    private String characterEncoding;

    @Override
    protected boolean hasFeature(Context cx, int featureIndex) {
        switch (featureIndex) {
            case 8: 
            case 9: 
            case 11: {
                return this.strictMode;
            }
            case 3: {
                return this.allowReservedKeywords;
            }
            case 12: {
                return this.warningAsError;
            }
            case 10: {
                return this.generatingDebug;
            }
        }
        return super.hasFeature(cx, featureIndex);
    }

    @Override
    protected void onContextCreated(Context cx) {
        cx.setLanguageVersion(this.languageVersion);
        cx.setOptimizationLevel(this.optimizationLevel);
        if (this.errorReporter != null) {
            cx.setErrorReporter(this.errorReporter);
        }
        cx.setGeneratingDebug(this.generatingDebug);
        super.onContextCreated(cx);
    }

    public void setStrictMode(boolean flag) {
        this.checkNotSealed();
        this.strictMode = flag;
    }

    public void setWarningAsError(boolean flag) {
        this.checkNotSealed();
        this.warningAsError = flag;
    }

    public void setLanguageVersion(int version) {
        Context.checkLanguageVersion(version);
        this.checkNotSealed();
        this.languageVersion = version;
    }

    public void setOptimizationLevel(int optimizationLevel) {
        Context.checkOptimizationLevel(optimizationLevel);
        this.checkNotSealed();
        this.optimizationLevel = optimizationLevel;
    }

    public void setErrorReporter(ErrorReporter errorReporter) {
        if (errorReporter == null) {
            throw new IllegalArgumentException();
        }
        this.errorReporter = errorReporter;
    }

    public void setGeneratingDebug(boolean generatingDebug) {
        this.generatingDebug = generatingDebug;
    }

    public String getCharacterEncoding() {
        return this.characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public void setAllowReservedKeywords(boolean allowReservedKeywords) {
        this.allowReservedKeywords = allowReservedKeywords;
    }
}

