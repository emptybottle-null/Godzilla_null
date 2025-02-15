/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Cursor;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ac.ShorthandCompletionCache;
import org.fife.rsta.ac.java.ClassCompletion;
import org.fife.rsta.ac.java.FieldCompletion;
import org.fife.rsta.ac.java.JarManager;
import org.fife.rsta.ac.java.JavaCompletionProvider;
import org.fife.rsta.ac.java.LocalVariableCompletion;
import org.fife.rsta.ac.java.MethodCompletion;
import org.fife.rsta.ac.java.SourceParamChoicesProvider;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.buildpath.LibraryInfo;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.FieldInfo;
import org.fife.rsta.ac.java.classreader.MemberInfo;
import org.fife.rsta.ac.java.classreader.MethodInfo;
import org.fife.rsta.ac.java.rjc.ast.CodeBlock;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.rsta.ac.java.rjc.ast.Field;
import org.fife.rsta.ac.java.rjc.ast.FormalParameter;
import org.fife.rsta.ac.java.rjc.ast.ImportDeclaration;
import org.fife.rsta.ac.java.rjc.ast.LocalVariable;
import org.fife.rsta.ac.java.rjc.ast.Member;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.ast.NormalClassDeclaration;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.rsta.ac.java.rjc.lang.TypeArgument;
import org.fife.rsta.ac.java.rjc.lang.TypeParameter;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;

class SourceCompletionProvider
extends DefaultCompletionProvider {
    private JavaCompletionProvider javaProvider;
    private JarManager jarManager;
    private static final String JAVA_LANG_PACKAGE = "java.lang.*";
    private static final String THIS = "this";
    private ShorthandCompletionCache shorthandCache;

    public SourceCompletionProvider() {
        this((JarManager)null);
    }

    public SourceCompletionProvider(JarManager jarManager) {
        if (jarManager == null) {
            jarManager = new JarManager();
        }
        this.jarManager = jarManager;
        this.setParameterizedCompletionParams('(', ", ", ')');
        this.setAutoActivationRules(false, ".");
        this.setParameterChoicesProvider(new SourceParamChoicesProvider());
    }

    private void addCompletionsForStaticMembers(Set<Completion> set, CompilationUnit cu, ClassFile cf, String pkg) {
        int methodCount = cf.getMethodCount();
        for (int i = 0; i < methodCount; ++i) {
            MethodInfo info = cf.getMethodInfo(i);
            if (!this.isAccessible(info, pkg) || !info.isStatic()) continue;
            MethodCompletion mc = new MethodCompletion((CompletionProvider)this, info);
            set.add(mc);
        }
        int fieldCount = cf.getFieldCount();
        for (int i = 0; i < fieldCount; ++i) {
            FieldInfo info = cf.getFieldInfo(i);
            if (!this.isAccessible(info, pkg) || !info.isStatic()) continue;
            FieldCompletion fc = new FieldCompletion((CompletionProvider)this, info);
            set.add(fc);
        }
        ClassFile superClass = this.getClassFileFor(cu, cf.getSuperClassName(true));
        if (superClass != null) {
            this.addCompletionsForStaticMembers(set, cu, superClass, pkg);
        }
    }

    private void addCompletionsForExtendedClass(Set<Completion> set, CompilationUnit cu, ClassFile cf, String pkg, Map<String, String> typeParamMap) {
        cf.setTypeParamsToTypeArgs(typeParamMap);
        int methodCount = cf.getMethodCount();
        for (int i = 0; i < methodCount; ++i) {
            MethodInfo info = cf.getMethodInfo(i);
            if (!this.isAccessible(info, pkg) || info.isConstructor()) continue;
            MethodCompletion mc = new MethodCompletion((CompletionProvider)this, info);
            set.add(mc);
        }
        int fieldCount = cf.getFieldCount();
        for (int i = 0; i < fieldCount; ++i) {
            FieldInfo info = cf.getFieldInfo(i);
            if (!this.isAccessible(info, pkg)) continue;
            FieldCompletion fc = new FieldCompletion((CompletionProvider)this, info);
            set.add(fc);
        }
        ClassFile superClass = this.getClassFileFor(cu, cf.getSuperClassName(true));
        if (superClass != null) {
            this.addCompletionsForExtendedClass(set, cu, superClass, pkg, typeParamMap);
        }
        for (int i = 0; i < cf.getImplementedInterfaceCount(); ++i) {
            String inter = cf.getImplementedInterfaceName(i, true);
            cf = this.getClassFileFor(cu, inter);
            this.addCompletionsForExtendedClass(set, cu, cf, pkg, typeParamMap);
        }
    }

    private void addCompletionsForLocalVarsMethods(CompilationUnit cu, LocalVariable var, Set<Completion> retVal) {
        String typeStr;
        ClassFile cf;
        Type type = var.getType();
        String pkg = cu.getPackageName();
        if (type.isArray()) {
            ClassFile cf2 = this.getClassFileFor(cu, "java.lang.Object");
            this.addCompletionsForExtendedClass(retVal, cu, cf2, pkg, null);
            FieldCompletion fc = FieldCompletion.createLengthCompletion(this, type);
            retVal.add(fc);
        } else if (!type.isBasicType() && (cf = this.getClassFileFor(cu, typeStr = type.getName(true, false))) != null) {
            Map<String, String> typeParamMap = this.createTypeParamMap(type, cf);
            this.addCompletionsForExtendedClass(retVal, cu, cf, pkg, typeParamMap);
        }
    }

    private void addShorthandCompletions(Set<Completion> set) {
        if (this.shorthandCache != null) {
            set.addAll(this.shorthandCache.getShorthandCompletions());
        }
    }

    public void setShorthandCache(ShorthandCompletionCache shorthandCache) {
        this.shorthandCache = shorthandCache;
    }

    private ClassFile getClassFileFor(CompilationUnit cu, String className) {
        if (className == null) {
            return null;
        }
        ClassFile superClass = null;
        if (!Util.isFullyQualified(className)) {
            String temp;
            String pkg = cu.getPackageName();
            if (pkg != null) {
                temp = pkg + "." + className;
                superClass = this.jarManager.getClassEntry(temp);
            }
            if (superClass == null) {
                Iterator<ImportDeclaration> i = cu.getImportIterator();
                while (i.hasNext()) {
                    ImportDeclaration id = i.next();
                    String imported = id.getName();
                    if (imported.endsWith(".*")) {
                        String temp2 = imported.substring(0, imported.length() - 1) + className;
                        superClass = this.jarManager.getClassEntry(temp2);
                        if (superClass == null) continue;
                        break;
                    }
                    if (!imported.endsWith("." + className)) continue;
                    superClass = this.jarManager.getClassEntry(imported);
                    break;
                }
            }
            if (superClass == null) {
                temp = "java.lang." + className;
                superClass = this.jarManager.getClassEntry(temp);
            }
        } else {
            superClass = this.jarManager.getClassEntry(className);
        }
        return superClass;
    }

    private void addLocalVarCompletions(Set<Completion> set, Method method, int offs) {
        for (int i = 0; i < method.getParameterCount(); ++i) {
            FormalParameter param = method.getParameter(i);
            set.add(new LocalVariableCompletion((CompletionProvider)this, param));
        }
        CodeBlock body = method.getBody();
        if (body != null) {
            this.addLocalVarCompletions(set, body, offs);
        }
    }

    private void addLocalVarCompletions(Set<Completion> set, CodeBlock block, int offs) {
        LocalVariable var;
        int i;
        for (i = 0; i < block.getLocalVarCount() && (var = block.getLocalVar(i)).getNameEndOffset() <= offs; ++i) {
            set.add(new LocalVariableCompletion((CompletionProvider)this, var));
        }
        for (i = 0; i < block.getChildBlockCount(); ++i) {
            CodeBlock child = block.getChildBlock(i);
            if (child.containsOffset(offs)) {
                this.addLocalVarCompletions(set, child, offs);
                break;
            }
            if (child.getNameStartOffset() > offs) break;
        }
    }

    public void addJar(LibraryInfo info) throws IOException {
        this.jarManager.addClassFileSource(info);
    }

    private boolean checkStringLiteralMember(JTextComponent comp, String alreadyEntered, CompilationUnit cu, Set<Completion> set) {
        boolean stringLiteralMember = false;
        int offs = comp.getCaretPosition() - alreadyEntered.length() - 1;
        if (offs > 1) {
            RSyntaxTextArea textArea = (RSyntaxTextArea)comp;
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            try {
                if (doc.charAt(offs) == '\"' && doc.charAt(offs + 1) == '.') {
                    int curLine = textArea.getLineOfOffset(offs);
                    Token list = textArea.getTokenListForLine(curLine);
                    Token prevToken = RSyntaxUtilities.getTokenAtOffset(list, offs);
                    if (prevToken != null && prevToken.getType() == 13) {
                        ClassFile cf = this.getClassFileFor(cu, "java.lang.String");
                        this.addCompletionsForExtendedClass(set, cu, cf, cu.getPackageName(), null);
                        stringLiteralMember = true;
                    } else {
                        System.out.println(prevToken);
                    }
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
        return stringLiteralMember;
    }

    public void clearJars() {
        this.jarManager.clearClassFileSources();
        this.clear();
    }

    private Map<String, String> createTypeParamMap(Type type, ClassFile cf) {
        HashMap<String, String> typeParamMap = null;
        List<TypeArgument> typeArgs = type.getTypeArguments(type.getIdentifierCount() - 1);
        if (typeArgs != null) {
            typeParamMap = new HashMap<String, String>();
            List<String> paramTypes = cf.getParamTypes();
            int min = Math.min(paramTypes == null ? 0 : paramTypes.size(), typeArgs.size());
            for (int i = 0; i < min; ++i) {
                TypeArgument typeArg = typeArgs.get(i);
                typeParamMap.put(paramTypes.get(i), typeArg.toString());
            }
        }
        return typeParamMap;
    }

    @Override
    public List<Completion> getCompletionsAt(JTextComponent tc, Point p) {
        this.getCompletionsImpl(tc);
        return super.getCompletionsAt(tc, p);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected List<Completion> getCompletionsImpl(JTextComponent comp) {
        comp.setCursor(Cursor.getPredefinedCursor(3));
        try {
            int start;
            this.completions = new ArrayList();
            CompilationUnit cu = this.javaProvider.getCompilationUnit();
            if (cu == null) {
                List list = this.completions;
                return list;
            }
            TreeSet<Completion> set = new TreeSet<Completion>();
            String text = this.getAlreadyEnteredText(comp);
            boolean stringLiteralMember = this.checkStringLiteralMember(comp, text, cu, set);
            if (!stringLiteralMember) {
                if (text.indexOf(46) == -1) {
                    this.addShorthandCompletions(set);
                }
                this.loadImportCompletions(set, text, cu);
                this.jarManager.addCompletions(this, text, set);
                this.loadCompletionsForCaretPosition(cu, comp, text, set);
            }
            this.completions = new ArrayList<Completion>(set);
            Collections.sort(this.completions);
            text = text.substring(text.lastIndexOf(46) + 1);
            if (start < 0) {
                start = -(start + 1);
            } else {
                for (start = Collections.binarySearch(this.completions, text, this.comparator); start > 0 && this.comparator.compare(this.completions.get(start - 1), text) == 0; --start) {
                }
            }
            int end = Collections.binarySearch(this.completions, text + '{', this.comparator);
            end = -(end + 1);
            List<Completion> list = this.completions.subList(start, end);
            return list;
        } finally {
            comp.setCursor(Cursor.getPredefinedCursor(2));
        }
    }

    public List<LibraryInfo> getJars() {
        return this.jarManager.getClassFileSources();
    }

    public SourceLocation getSourceLocForClass(String className) {
        return this.jarManager.getSourceLocForClass(className);
    }

    private boolean isAccessible(MemberInfo info, String pkg) {
        boolean accessible = false;
        int access = info.getAccessFlags();
        if (org.fife.rsta.ac.java.classreader.Util.isPublic(access) || org.fife.rsta.ac.java.classreader.Util.isProtected(access)) {
            accessible = true;
        } else if (org.fife.rsta.ac.java.classreader.Util.isDefault(access)) {
            String pkg2 = info.getClassFile().getPackageName();
            accessible = pkg == null && pkg2 == null || pkg != null && pkg.equals(pkg2);
        }
        return accessible;
    }

    @Override
    protected boolean isValidChar(char ch) {
        return Character.isJavaIdentifierPart(ch) || ch == '.';
    }

    private void loadCompletionsForCaretPosition(CompilationUnit cu, JTextComponent comp, String alreadyEntered, Set<Completion> retVal) {
        int caret = comp.getCaretPosition();
        int lastDot = alreadyEntered.lastIndexOf(46);
        boolean qualified = lastDot > -1;
        String prefix = qualified ? alreadyEntered.substring(0, lastDot) : null;
        Iterator<TypeDeclaration> i = cu.getTypeDeclarationIterator();
        while (i.hasNext()) {
            TypeDeclaration td = i.next();
            int start = td.getBodyStartOffset();
            int end = td.getBodyEndOffset();
            if (caret > start && caret <= end) {
                this.loadCompletionsForCaretPosition(cu, comp, alreadyEntered, retVal, td, prefix, caret);
                continue;
            }
            if (caret >= start) continue;
            break;
        }
    }

    private void loadCompletionsForCaretPosition(CompilationUnit cu, JTextComponent comp, String alreadyEntered, Set<Completion> retVal, TypeDeclaration td, String prefix, int caret) {
        NormalClassDeclaration ncd;
        Type extended;
        NormalClassDeclaration ncd2;
        List<TypeParameter> typeParams;
        for (int i = 0; i < td.getChildTypeCount(); ++i) {
            TypeDeclaration childType = td.getChildType(i);
            this.loadCompletionsForCaretPosition(cu, comp, alreadyEntered, retVal, childType, prefix, caret);
        }
        Method currentMethod = null;
        HashMap<String, String> typeParamMap = new HashMap<String, String>();
        if (td instanceof NormalClassDeclaration && (typeParams = (ncd2 = (NormalClassDeclaration)td).getTypeParameters()) != null) {
            for (TypeParameter typeParam : typeParams) {
                String typeVar = typeParam.getName();
                typeParamMap.put(typeVar, typeVar);
            }
        }
        String pkg = cu.getPackageName();
        Iterator<Member> j = td.getMemberIterator();
        while (j.hasNext()) {
            Member m = j.next();
            if (m instanceof Method) {
                Method method = (Method)m;
                if (prefix == null || THIS.equals(prefix)) {
                    retVal.add(new MethodCompletion((CompletionProvider)this, method));
                }
                if (caret < method.getBodyStartOffset() || caret >= method.getBodyEndOffset()) continue;
                currentMethod = method;
                if (prefix != null) continue;
                this.addLocalVarCompletions(retVal, method, caret);
                continue;
            }
            if (!(m instanceof Field) || prefix != null && !THIS.equals(prefix)) continue;
            Field field = (Field)m;
            retVal.add(new FieldCompletion((CompletionProvider)this, field));
        }
        if ((prefix == null || THIS.equals(prefix)) && td instanceof NormalClassDeclaration && (extended = (ncd = (NormalClassDeclaration)td).getExtendedType()) != null) {
            String superClassName = extended.toString();
            ClassFile cf = this.getClassFileFor(cu, superClassName);
            if (cf != null) {
                this.addCompletionsForExtendedClass(retVal, cu, cf, pkg, null);
            } else {
                System.out.println("[DEBUG]: Couldn't find ClassFile for: " + superClassName);
            }
        }
        if (prefix != null && !THIS.equals(prefix)) {
            this.loadCompletionsForCaretPositionQualified(cu, alreadyEntered, retVal, td, currentMethod, prefix, caret);
        }
    }

    private void loadCompletionsForCaretPositionQualified(CompilationUnit cu, String alreadyEntered, Set<Completion> retVal, TypeDeclaration td, Method currentMethod, String prefix, int offs) {
        List<ImportDeclaration> imports;
        List<ClassFile> matches;
        int dot = prefix.indexOf(46);
        if (dot > -1) {
            System.out.println("[DEBUG]: Qualified non-this completions currently only go 1 level deep");
            return;
        }
        if (!prefix.matches("[A-Za-z_][A-Za-z0-9_\\$]*")) {
            System.out.println("[DEBUG]: Only identifier non-this completions are currently supported");
            return;
        }
        String pkg = cu.getPackageName();
        boolean matched = false;
        Iterator<Member> j = td.getMemberIterator();
        while (j.hasNext()) {
            String typeStr;
            ClassFile cf;
            Field field;
            Member m = j.next();
            if (!(m instanceof Field) || !(field = (Field)m).getName().equals(prefix)) continue;
            Type type = field.getType();
            if (type.isArray()) {
                ClassFile cf2 = this.getClassFileFor(cu, "java.lang.Object");
                this.addCompletionsForExtendedClass(retVal, cu, cf2, pkg, null);
                FieldCompletion fc = FieldCompletion.createLengthCompletion(this, type);
                retVal.add(fc);
            } else if (!type.isBasicType() && (cf = this.getClassFileFor(cu, typeStr = type.getName(true, false))) != null) {
                Map<String, String> typeParamMap = this.createTypeParamMap(type, cf);
                this.addCompletionsForExtendedClass(retVal, cu, cf, pkg, typeParamMap);
                for (int i = 0; i < cf.getImplementedInterfaceCount(); ++i) {
                    String inter = cf.getImplementedInterfaceName(i, true);
                    cf = this.getClassFileFor(cu, inter);
                    System.out.println(cf);
                }
            }
            matched = true;
            break;
        }
        if (currentMethod != null) {
            CodeBlock body;
            boolean found = false;
            for (int i = 0; i < currentMethod.getParameterCount(); ++i) {
                FormalParameter param = currentMethod.getParameter(i);
                String name = param.getName();
                if (!prefix.equals(name)) continue;
                this.addCompletionsForLocalVarsMethods(cu, param, retVal);
                found = true;
                break;
            }
            if (!found && (body = currentMethod.getBody()) != null) {
                this.loadCompletionsForCaretPositionQualifiedCodeBlock(cu, retVal, td, body, prefix, offs);
            }
            matched |= found;
        }
        if (!matched && (matches = this.jarManager.getClassesWithUnqualifiedName(prefix, imports = cu.getImports())) != null) {
            for (int i = 0; i < matches.size(); ++i) {
                ClassFile cf = matches.get(i);
                this.addCompletionsForStaticMembers(retVal, cu, cf, pkg);
            }
        }
    }

    private void loadCompletionsForCaretPositionQualifiedCodeBlock(CompilationUnit cu, Set<Completion> retVal, TypeDeclaration td, CodeBlock block, String prefix, int offs) {
        LocalVariable var;
        int i;
        boolean found = false;
        for (i = 0; i < block.getLocalVarCount() && (var = block.getLocalVar(i)).getNameEndOffset() <= offs; ++i) {
            if (!prefix.equals(var.getName())) continue;
            this.addCompletionsForLocalVarsMethods(cu, var, retVal);
            found = true;
            break;
        }
        if (found) {
            return;
        }
        for (i = 0; i < block.getChildBlockCount(); ++i) {
            CodeBlock child = block.getChildBlock(i);
            if (child.containsOffset(offs)) {
                this.loadCompletionsForCaretPositionQualifiedCodeBlock(cu, retVal, td, child, prefix, offs);
                break;
            }
            if (child.getNameStartOffset() > offs) break;
        }
    }

    private void loadCompletionsForImport(Set<Completion> set, String importStr, String pkgName) {
        if (importStr.endsWith(".*")) {
            String pkg = importStr.substring(0, importStr.length() - 2);
            boolean inPkg = pkg.equals(pkgName);
            List<ClassFile> classes = this.jarManager.getClassesInPackage(pkg, inPkg);
            for (ClassFile cf : classes) {
                set.add(new ClassCompletion((CompletionProvider)this, cf));
            }
        } else {
            ClassFile cf = this.jarManager.getClassEntry(importStr);
            if (cf != null) {
                set.add(new ClassCompletion((CompletionProvider)this, cf));
            }
        }
    }

    private void loadImportCompletions(Set<Completion> set, String text, CompilationUnit cu) {
        if (text.indexOf(46) > -1) {
            return;
        }
        String pkgName = cu.getPackageName();
        this.loadCompletionsForImport(set, JAVA_LANG_PACKAGE, pkgName);
        Iterator<ImportDeclaration> i = cu.getImportIterator();
        while (i.hasNext()) {
            ImportDeclaration id = i.next();
            String name = id.getName();
            if (JAVA_LANG_PACKAGE.equals(name)) continue;
            this.loadCompletionsForImport(set, name, pkgName);
        }
    }

    public boolean removeJar(File jar) {
        boolean removed = this.jarManager.removeClassFileSource(jar);
        if (removed) {
            this.clear();
        }
        return removed;
    }

    void setJavaProvider(JavaCompletionProvider javaProvider) {
        this.javaProvider = javaProvider;
    }
}

