/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.completion;

import java.util.ArrayList;
import java.util.Iterator;
import org.fife.rsta.ac.java.JarManager;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.MethodInfo;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.rsta.ac.java.rjc.ast.FormalParameter;
import org.fife.rsta.ac.java.rjc.ast.Member;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.ui.autocomplete.CompletionProvider;

public class JSMethodData {
    private MethodInfo info;
    private JarManager jarManager;
    private ArrayList<String> paramNames;

    public JSMethodData(MethodInfo info, JarManager jarManager) {
        this.info = info;
        this.jarManager = jarManager;
    }

    public String getParameterName(int index) {
        String name = this.info.getParameterName(index);
        Method method = this.getMethod();
        if (method != null) {
            name = method.getParameter(index).getName();
        }
        if (name == null) {
            if (this.paramNames == null) {
                this.paramNames = new ArrayList(1);
                int offs = 0;
                String rawSummary = this.getSummary();
                if (rawSummary != null && rawSummary.startsWith("/**")) {
                    int nextParam;
                    int summaryLen = rawSummary.length();
                    while ((nextParam = rawSummary.indexOf("@param", offs)) > -1) {
                        int end;
                        int temp;
                        for (temp = nextParam + "@param".length() + 1; temp < summaryLen && !Character.isJavaIdentifierPart(rawSummary.charAt(temp)) || Character.isWhitespace(rawSummary.charAt(temp)); ++temp) {
                        }
                        if (temp >= summaryLen) break;
                        int start = temp;
                        for (end = start + 1; end < summaryLen && Character.isJavaIdentifierPart(rawSummary.charAt(end)); ++end) {
                        }
                        this.paramNames.add(rawSummary.substring(start, end));
                        offs = end;
                    }
                }
            }
            if (index < this.paramNames.size()) {
                name = this.paramNames.get(index);
            }
        }
        if (name == null) {
            name = "arg" + index;
        }
        return name;
    }

    public String getParameterType(String[] paramTypes, int index, CompletionProvider provider) {
        if (paramTypes != null && index < paramTypes.length) {
            return ((SourceCompletionProvider)provider).getTypesFactory().convertJavaScriptType(paramTypes[index], true);
        }
        return null;
    }

    public String getSummary() {
        ClassFile cf = this.info.getClassFile();
        SourceLocation loc = this.jarManager.getSourceLocForClass(cf.getClassName(true));
        String summary = null;
        if (loc != null) {
            summary = this.getSummaryFromSourceLoc(loc, cf);
        }
        if (summary == null) {
            this.info.getReturnTypeString(true);
            summary = this.info.getSignature();
        }
        return summary;
    }

    public Method getMethod() {
        ClassFile cf = this.info.getClassFile();
        SourceLocation loc = this.jarManager.getSourceLocForClass(cf.getClassName(true));
        return this.getMethodFromSourceLoc(loc, cf);
    }

    private String getSummaryFromSourceLoc(SourceLocation loc, ClassFile cf) {
        Method method = this.getMethodFromSourceLoc(loc, cf);
        return method != null ? method.getDocComment() : null;
    }

    private Method getMethodFromSourceLoc(SourceLocation loc, ClassFile cf) {
        Method res = null;
        CompilationUnit cu = Util.getCompilationUnitFromDisk(loc, cf);
        if (cu != null) {
            Iterator<TypeDeclaration> i = cu.getTypeDeclarationIterator();
            block0: while (i.hasNext()) {
                TypeDeclaration td = i.next();
                String typeName = td.getName();
                if (!typeName.equals(cf.getClassName(false))) continue;
                ArrayList<Method> contenders = null;
                Iterator<Member> j = td.getMemberIterator();
                while (j.hasNext()) {
                    Method m2;
                    Member member = j.next();
                    if (!(member instanceof Method) || !member.getName().equals(this.info.getName()) || (m2 = (Method)member).getParameterCount() != this.info.getParameterCount()) continue;
                    if (contenders == null) {
                        contenders = new ArrayList<Method>(1);
                    }
                    contenders.add(m2);
                }
                if (contenders == null) break;
                if (contenders.size() == 1) {
                    res = (Method)contenders.get(0);
                    break;
                }
                for (int j2 = 0; j2 < contenders.size(); ++j2) {
                    boolean match = true;
                    Method method = (Method)contenders.get(j2);
                    for (int p = 0; p < this.info.getParameterCount(); ++p) {
                        FormalParameter fp;
                        String type2;
                        String type1 = this.info.getParameterType(p, false);
                        if (type1.equals(type2 = (fp = method.getParameter(p)).getType().toString())) continue;
                        match = false;
                        break;
                    }
                    if (!match) continue;
                    res = method;
                    break block0;
                }
            }
        }
        return res;
    }

    public MethodInfo getMethodInfo() {
        return this.info;
    }

    public String getType(boolean qualified) {
        return this.info.getReturnTypeString(qualified);
    }

    public int getParameterCount() {
        return this.info.getParameterCount();
    }

    public boolean isStatic() {
        return this.info.isStatic();
    }

    public String getEnclosingClassName(boolean fullyQualified) {
        return this.info.getClassFile().getClassName(fullyQualified);
    }
}

