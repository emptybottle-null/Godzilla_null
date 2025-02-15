/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.resolver;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.js.JavaScriptHelper;
import org.fife.rsta.ac.js.JavaScriptParser;
import org.fife.rsta.ac.js.Logger;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.JavaScriptFunctionDeclaration;
import org.fife.rsta.ac.js.ast.jsType.JavaScriptType;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.completion.JSCompletion;
import org.fife.rsta.ac.js.completion.JSMethodData;
import org.fife.rsta.ac.js.resolver.JavaScriptResolver;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.PropertyGet;

public class JavaScriptCompletionResolver
extends JavaScriptResolver {
    protected JavaScriptType lastJavaScriptType;
    protected String lastLookupName = null;

    public JavaScriptCompletionResolver(SourceCompletionProvider provider) {
        super(provider);
    }

    @Override
    public JavaScriptType compileText(String text) throws IOException {
        CompilerEnvirons env = JavaScriptParser.createCompilerEnvironment(new JavaScriptParser.JSErrorReporter(), this.provider.getLanguageSupport());
        String parseText = JavaScriptHelper.removeLastDotFromText(text);
        int charIndex = JavaScriptHelper.findIndexOfFirstOpeningBracket(parseText);
        env.setRecoverFromErrors(true);
        Parser parser = new Parser(env);
        StringReader r = new StringReader(parseText);
        AstRoot root = parser.parse(r, null, 0);
        CompilerNodeVisitor visitor = new CompilerNodeVisitor(charIndex == 0);
        root.visitAll(visitor);
        return this.lastJavaScriptType;
    }

    @Override
    public TypeDeclaration resolveParamNode(String text) throws IOException {
        if (text != null) {
            CompilerEnvirons env = JavaScriptParser.createCompilerEnvironment(new JavaScriptParser.JSErrorReporter(), this.provider.getLanguageSupport());
            int charIndex = JavaScriptHelper.findIndexOfFirstOpeningBracket(text);
            env.setRecoverFromErrors(true);
            Parser parser = new Parser(env);
            StringReader r = new StringReader(text);
            AstRoot root = parser.parse(r, null, 0);
            CompilerNodeVisitor visitor = new CompilerNodeVisitor(charIndex == 0);
            root.visitAll(visitor);
        }
        return this.lastJavaScriptType != null ? this.lastJavaScriptType.getType() : this.provider.getTypesFactory().getDefaultTypeDeclaration();
    }

    @Override
    public TypeDeclaration resolveNode(AstNode node) {
        if (node == null) {
            return this.provider.getTypesFactory().getDefaultTypeDeclaration();
        }
        CompilerNodeVisitor visitor = new CompilerNodeVisitor(true);
        node.visit(visitor);
        return this.lastJavaScriptType != null ? this.lastJavaScriptType.getType() : this.provider.getTypesFactory().getDefaultTypeDeclaration();
    }

    @Override
    protected TypeDeclaration resolveNativeType(AstNode node) {
        TypeDeclaration dec = JavaScriptHelper.tokenToNativeTypeDeclaration(node, this.provider);
        if (dec == null) {
            dec = this.testJavaStaticType(node);
        }
        return dec;
    }

    protected TypeDeclaration testJavaStaticType(AstNode node) {
        switch (node.getType()) {
            case 39: {
                return this.findJavaStaticType(node);
            }
        }
        return null;
    }

    protected TypeDeclaration findJavaStaticType(AstNode node) {
        ClassFile cf;
        TypeDeclaration dec;
        String testName = node.toSource();
        if (testName != null && (dec = JavaScriptHelper.getTypeDeclaration(testName, this.provider)) != null && (cf = this.provider.getJavaScriptTypesFactory().getClassFile(this.provider.getJarManager(), dec)) != null) {
            TypeDeclaration returnDec = this.provider.getJavaScriptTypesFactory().createNewTypeDeclaration(cf, true, false);
            return returnDec;
        }
        return null;
    }

    protected JavaScriptType lookupFromName(AstNode node, JavaScriptType lastJavaScriptType) {
        JavaScriptType javaScriptType = null;
        if (lastJavaScriptType != null) {
            String lookupText = null;
            switch (node.getType()) {
                case 39: {
                    lookupText = ((Name)node).getIdentifier();
                }
            }
            if (lookupText == null) {
                lookupText = node.toSource();
            }
            javaScriptType = this.lookupJavaScriptType(lastJavaScriptType, lookupText);
        }
        return javaScriptType;
    }

    protected JavaScriptType lookupFunctionCompletion(AstNode node, JavaScriptType lastJavaScriptType) {
        JavaScriptType javaScriptType = null;
        if (lastJavaScriptType != null) {
            String lookupText = JavaScriptHelper.getFunctionNameLookup(node, this.provider);
            javaScriptType = this.lookupJavaScriptType(lastJavaScriptType, lookupText);
        }
        return javaScriptType;
    }

    @Override
    public String getLookupText(JSMethodData method, String name) {
        StringBuilder sb = new StringBuilder(name);
        sb.append('(');
        int count = method.getParameterCount();
        for (int i = 0; i < count; ++i) {
            sb.append("p");
            if (i >= count - 1) continue;
            sb.append(",");
        }
        sb.append(')');
        return sb.toString();
    }

    @Override
    public String getFunctionNameLookup(FunctionCall call, SourceCompletionProvider provider) {
        if (call != null) {
            StringBuilder sb = new StringBuilder();
            if (call.getTarget() instanceof PropertyGet) {
                PropertyGet get = (PropertyGet)call.getTarget();
                sb.append(get.getProperty().getIdentifier());
            }
            sb.append("(");
            int count = call.getArguments().size();
            for (int i = 0; i < count; ++i) {
                sb.append("p");
                if (i >= count - 1) continue;
                sb.append(",");
            }
            sb.append(")");
            return sb.toString();
        }
        return null;
    }

    private JavaScriptType lookupJavaScriptType(JavaScriptType lastJavaScriptType, String lookupText) {
        String type;
        JSCompletion completion;
        JavaScriptType javaScriptType = null;
        if (lookupText != null && !lookupText.equals(this.lastLookupName) && (completion = lastJavaScriptType.getCompletion(lookupText, this.provider)) != null && (type = completion.getType(true)) != null) {
            TypeDeclaration newType = this.provider.getTypesFactory().getTypeDeclaration(type);
            javaScriptType = newType != null ? this.provider.getJavaScriptTypesFactory().getCachedType(newType, this.provider.getJarManager(), this.provider, lookupText) : this.createNewTypeDeclaration(this.provider, type, lookupText);
        }
        this.lastLookupName = lookupText;
        return javaScriptType;
    }

    private JavaScriptType createNewTypeDeclaration(SourceCompletionProvider provider, String type, String text) {
        ClassFile cf;
        if (provider.getJavaScriptTypesFactory() != null && (cf = provider.getJarManager().getClassEntry(type)) != null) {
            TypeDeclaration newType = provider.getJavaScriptTypesFactory().createNewTypeDeclaration(cf, false);
            return provider.getJavaScriptTypesFactory().getCachedType(newType, provider.getJarManager(), provider, text);
        }
        return null;
    }

    protected TypeDeclaration resolveTypeFromLastJavaScriptType(AstNode node) {
        return null;
    }

    private static class VisitorAll
    implements NodeVisitor {
        private ArrayList<AstNode> all = new ArrayList();

        private VisitorAll() {
        }

        @Override
        public boolean visit(AstNode node) {
            this.all.add(node);
            return true;
        }

        public ArrayList<AstNode> getAllNodes() {
            return this.all;
        }
    }

    private class CompilerNodeVisitor
    implements NodeVisitor {
        private boolean ignoreParams;
        private HashSet<AstNode> paramNodes = new HashSet();

        private CompilerNodeVisitor(boolean ignoreParams) {
            this.ignoreParams = ignoreParams;
        }

        @Override
        public boolean visit(AstNode node) {
            TypeDeclaration dec;
            Logger.log(JavaScriptHelper.convertNodeToSource(node));
            Logger.log(node.shortName());
            if (!this.validNode(node)) {
                JavaScriptCompletionResolver.this.lastJavaScriptType = null;
                return false;
            }
            if (this.ignore(node, this.ignoreParams)) {
                return true;
            }
            if (JavaScriptCompletionResolver.this.lastJavaScriptType == null) {
                dec = JavaScriptCompletionResolver.this.resolveNativeType(node);
                if (dec == null && node.getType() == 39) {
                    JavaScriptCompletionResolver.this.lastJavaScriptType = null;
                    return false;
                }
            } else {
                dec = JavaScriptCompletionResolver.this.resolveTypeFromLastJavaScriptType(node);
            }
            if (dec != null) {
                JavaScriptType jsType = JavaScriptCompletionResolver.this.provider.getJavaScriptTypesFactory().getCachedType(dec, JavaScriptCompletionResolver.this.provider.getJarManager(), JavaScriptCompletionResolver.this.provider, JavaScriptHelper.convertNodeToSource(node));
                if (jsType != null) {
                    JavaScriptCompletionResolver.this.lastJavaScriptType = jsType;
                    return false;
                }
            } else if (JavaScriptCompletionResolver.this.lastJavaScriptType != null) {
                if (node.getType() == 39) {
                    JavaScriptType jsType = JavaScriptCompletionResolver.this.lookupFromName(node, JavaScriptCompletionResolver.this.lastJavaScriptType);
                    if (jsType == null) {
                        jsType = JavaScriptCompletionResolver.this.lookupFunctionCompletion(node, JavaScriptCompletionResolver.this.lastJavaScriptType);
                    }
                    JavaScriptCompletionResolver.this.lastJavaScriptType = jsType;
                }
            } else if (node instanceof FunctionCall) {
                JavaScriptType jsType;
                FunctionCall fn = (FunctionCall)node;
                String lookupText = this.createLookupString(fn);
                JavaScriptFunctionDeclaration funcDec = JavaScriptCompletionResolver.this.provider.getVariableResolver().findFunctionDeclaration(lookupText);
                if (funcDec != null && (jsType = JavaScriptCompletionResolver.this.provider.getJavaScriptTypesFactory().getCachedType(funcDec.getTypeDeclaration(), JavaScriptCompletionResolver.this.provider.getJarManager(), JavaScriptCompletionResolver.this.provider, JavaScriptHelper.convertNodeToSource(node))) != null) {
                    JavaScriptCompletionResolver.this.lastJavaScriptType = jsType;
                    return false;
                }
            }
            return true;
        }

        private boolean validNode(AstNode node) {
            switch (node.getType()) {
                case 39: {
                    return ((Name)node).getIdentifier() != null && ((Name)node).getIdentifier().length() > 0;
                }
            }
            return true;
        }

        private String createLookupString(FunctionCall fn) {
            StringBuilder sb = new StringBuilder();
            String name = "";
            switch (fn.getTarget().getType()) {
                case 39: {
                    name = ((Name)fn.getTarget()).getIdentifier();
                }
            }
            sb.append(name);
            sb.append("(");
            Iterator<AstNode> i = fn.getArguments().iterator();
            while (i.hasNext()) {
                i.next();
                sb.append("p");
                if (!i.hasNext()) continue;
                sb.append(",");
            }
            sb.append(")");
            return sb.toString();
        }

        private boolean ignore(AstNode node, boolean ignoreParams) {
            switch (node.getType()) {
                case 133: 
                case 134: {
                    return ((ExpressionStatement)node).getExpression().getType() == -1;
                }
                case -1: 
                case 33: 
                case 136: {
                    return true;
                }
            }
            if (this.isParameter(node)) {
                this.collectAllNodes(node);
                return ignoreParams;
            }
            return false;
        }

        private void collectAllNodes(AstNode node) {
            if (node.getType() == 38) {
                FunctionCall call = (FunctionCall)node;
                for (AstNode arg : call.getArguments()) {
                    VisitorAll all = new VisitorAll();
                    arg.visit(all);
                    this.paramNodes.addAll(all.getAllNodes());
                }
            }
        }

        private boolean isParameter(AstNode node) {
            if (this.paramNodes.contains(node)) {
                return true;
            }
            FunctionCall fc = JavaScriptHelper.findFunctionCallFromNode(node);
            if (fc != null && node != fc) {
                this.collectAllNodes(fc);
                if (this.paramNodes.contains(node)) {
                    return true;
                }
            }
            return false;
        }
    }
}

