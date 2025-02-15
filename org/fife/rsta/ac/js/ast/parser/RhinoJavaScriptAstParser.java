/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.parser;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import org.fife.rsta.ac.js.JavaScriptHelper;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.CodeBlock;
import org.fife.rsta.ac.js.ast.TypeDeclarationOptions;
import org.fife.rsta.ac.js.ast.jsType.JavaScriptTypesFactory;
import org.fife.rsta.ac.js.ast.jsType.RhinoJavaScriptTypesFactory;
import org.fife.rsta.ac.js.ast.parser.JavaScriptAstParser;
import org.fife.ui.autocomplete.Completion;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;

public class RhinoJavaScriptAstParser
extends JavaScriptAstParser {
    public static final String PACKAGES = "Packages.";
    private LinkedHashSet<String> importClasses = new LinkedHashSet();
    private LinkedHashSet<String> importPackages = new LinkedHashSet();

    public RhinoJavaScriptAstParser(SourceCompletionProvider provider, int dot, TypeDeclarationOptions options) {
        super(provider, dot, options);
    }

    public void clearImportCache(SourceCompletionProvider provider) {
        JavaScriptTypesFactory typesFactory = provider.getJavaScriptTypesFactory();
        if (typesFactory instanceof RhinoJavaScriptTypesFactory) {
            ((RhinoJavaScriptTypesFactory)typesFactory).clearImportCache();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CodeBlock convertAstNodeToCodeBlock(AstRoot root, Set<Completion> set, String entered) {
        try {
            CodeBlock codeBlock = super.convertAstNodeToCodeBlock(root, set, entered);
            return codeBlock;
        } finally {
            this.mergeImportCache(this.importPackages, this.importClasses);
            this.importClasses.clear();
            this.importPackages.clear();
        }
    }

    private void mergeImportCache(HashSet<String> packages, HashSet<String> classes) {
        JavaScriptTypesFactory typesFactory = this.provider.getJavaScriptTypesFactory();
        if (typesFactory instanceof RhinoJavaScriptTypesFactory) {
            ((RhinoJavaScriptTypesFactory)typesFactory).mergeImports(packages, classes);
        }
    }

    @Override
    protected void iterateNode(AstNode child, Set<Completion> set, String entered, CodeBlock block, int offset) {
        switch (child.getType()) {
            case 134: {
                boolean importFound = this.processImportNode(child, set, entered, block, offset);
                if (!importFound) break;
                return;
            }
        }
        super.iterateNode(child, set, entered, block, offset);
    }

    private boolean processImportNode(AstNode child, Set<Completion> set, String entered, CodeBlock block, int offset) {
        String src = JavaScriptHelper.convertNodeToSource(child);
        if (src != null) {
            if (src.startsWith("importPackage")) {
                this.processImportPackage(src);
                return true;
            }
            if (src.startsWith("importClass")) {
                this.processImportClass(src);
                return true;
            }
        }
        return false;
    }

    public static String removePackages(String src) {
        String pkg;
        if (src.startsWith(PACKAGES) && (pkg = src.substring(PACKAGES.length())) != null) {
            StringBuilder sb = new StringBuilder();
            char[] chars = pkg.toCharArray();
            for (int i = 0; i < chars.length; ++i) {
                char ch = chars[i];
                if (!Character.isJavaIdentifierPart(ch) && ch != '.') continue;
                sb.append(ch);
            }
            if (sb.length() > 0) {
                return sb.toString();
            }
        }
        return src;
    }

    private String extractNameFromSrc(String src) {
        int startIndex = src.indexOf("(");
        int endIndex = src.indexOf(")");
        if (startIndex != -1 && endIndex != -1) {
            return RhinoJavaScriptAstParser.removePackages(src.substring(startIndex + 1, endIndex));
        }
        return RhinoJavaScriptAstParser.removePackages(src);
    }

    private void processImportPackage(String src) {
        String pkg = this.extractNameFromSrc(src);
        this.importPackages.add(pkg);
    }

    private void processImportClass(String src) {
        String cls = this.extractNameFromSrc(src);
        this.importClasses.add(cls);
    }
}

