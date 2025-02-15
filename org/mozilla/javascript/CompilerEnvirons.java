/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.util.Set;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.DefaultErrorReporter;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.ast.ErrorCollector;

public class CompilerEnvirons {
    private ErrorReporter errorReporter = DefaultErrorReporter.instance;
    private int languageVersion = 0;
    private boolean generateDebugInfo = true;
    private boolean reservedKeywordAsIdentifier = true;
    private boolean allowMemberExprAsFunctionName = false;
    private boolean xmlAvailable = true;
    private int optimizationLevel = 0;
    private boolean generatingSource = true;
    private boolean strictMode = false;
    private boolean warningAsError = false;
    private boolean generateObserverCount = false;
    private boolean recordingComments;
    private boolean recordingLocalJsDocComments;
    private boolean recoverFromErrors;
    private boolean warnTrailingComma;
    private boolean ideMode;
    private boolean allowSharpComments = false;
    Set<String> activationNames;

    public void initFromContext(Context cx) {
        this.setErrorReporter(cx.getErrorReporter());
        this.languageVersion = cx.getLanguageVersion();
        this.generateDebugInfo = !cx.isGeneratingDebugChanged() || cx.isGeneratingDebug();
        this.reservedKeywordAsIdentifier = cx.hasFeature(3);
        this.allowMemberExprAsFunctionName = cx.hasFeature(2);
        this.strictMode = cx.hasFeature(11);
        this.warningAsError = cx.hasFeature(12);
        this.xmlAvailable = cx.hasFeature(6);
        this.optimizationLevel = cx.getOptimizationLevel();
        this.generatingSource = cx.isGeneratingSource();
        this.activationNames = cx.activationNames;
        this.generateObserverCount = cx.generateObserverCount;
    }

    public final ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }

    public void setErrorReporter(ErrorReporter errorReporter) {
        if (errorReporter == null) {
            throw new IllegalArgumentException();
        }
        this.errorReporter = errorReporter;
    }

    public final int getLanguageVersion() {
        return this.languageVersion;
    }

    public void setLanguageVersion(int languageVersion) {
        Context.checkLanguageVersion(languageVersion);
        this.languageVersion = languageVersion;
    }

    public final boolean isGenerateDebugInfo() {
        return this.generateDebugInfo;
    }

    public void setGenerateDebugInfo(boolean flag) {
        this.generateDebugInfo = flag;
    }

    public final boolean isReservedKeywordAsIdentifier() {
        return this.reservedKeywordAsIdentifier;
    }

    public void setReservedKeywordAsIdentifier(boolean flag) {
        this.reservedKeywordAsIdentifier = flag;
    }

    public final boolean isAllowMemberExprAsFunctionName() {
        return this.allowMemberExprAsFunctionName;
    }

    public void setAllowMemberExprAsFunctionName(boolean flag) {
        this.allowMemberExprAsFunctionName = flag;
    }

    public final boolean isXmlAvailable() {
        return this.xmlAvailable;
    }

    public void setXmlAvailable(boolean flag) {
        this.xmlAvailable = flag;
    }

    public final int getOptimizationLevel() {
        return this.optimizationLevel;
    }

    public void setOptimizationLevel(int level) {
        Context.checkOptimizationLevel(level);
        this.optimizationLevel = level;
    }

    public final boolean isGeneratingSource() {
        return this.generatingSource;
    }

    public boolean getWarnTrailingComma() {
        return this.warnTrailingComma;
    }

    public void setWarnTrailingComma(boolean warn) {
        this.warnTrailingComma = warn;
    }

    public final boolean isStrictMode() {
        return this.strictMode;
    }

    public void setStrictMode(boolean strict) {
        this.strictMode = strict;
    }

    public final boolean reportWarningAsError() {
        return this.warningAsError;
    }

    public void setGeneratingSource(boolean generatingSource) {
        this.generatingSource = generatingSource;
    }

    public boolean isGenerateObserverCount() {
        return this.generateObserverCount;
    }

    public void setGenerateObserverCount(boolean generateObserverCount) {
        this.generateObserverCount = generateObserverCount;
    }

    public boolean isRecordingComments() {
        return this.recordingComments;
    }

    public void setRecordingComments(boolean record) {
        this.recordingComments = record;
    }

    public boolean isRecordingLocalJsDocComments() {
        return this.recordingLocalJsDocComments;
    }

    public void setRecordingLocalJsDocComments(boolean record) {
        this.recordingLocalJsDocComments = record;
    }

    public void setRecoverFromErrors(boolean recover) {
        this.recoverFromErrors = recover;
    }

    public boolean recoverFromErrors() {
        return this.recoverFromErrors;
    }

    public void setIdeMode(boolean ide) {
        this.ideMode = ide;
    }

    public boolean isIdeMode() {
        return this.ideMode;
    }

    public Set<String> getActivationNames() {
        return this.activationNames;
    }

    public void setActivationNames(Set<String> activationNames) {
        this.activationNames = activationNames;
    }

    public void setAllowSharpComments(boolean allow) {
        this.allowSharpComments = allow;
    }

    public boolean getAllowSharpComments() {
        return this.allowSharpComments;
    }

    public static CompilerEnvirons ideEnvirons() {
        CompilerEnvirons env = new CompilerEnvirons();
        env.setRecoverFromErrors(true);
        env.setRecordingComments(true);
        env.setStrictMode(true);
        env.setWarnTrailingComma(true);
        env.setLanguageVersion(170);
        env.setReservedKeywordAsIdentifier(true);
        env.setIdeMode(true);
        env.setErrorReporter(new ErrorCollector());
        return env;
    }
}

