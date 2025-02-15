/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.fife.rsta.ac.java.MemberCompletion;
import org.fife.rsta.ac.java.SourceCompletionProvider;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.MethodInfo;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.rsta.ac.java.rjc.ast.FormalParameter;
import org.fife.rsta.ac.java.rjc.ast.Member;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;

class MethodInfoData
implements MemberCompletion.Data {
    private SourceCompletionProvider provider;
    private MethodInfo info;
    private List<String> paramNames;

    public MethodInfoData(MethodInfo info, SourceCompletionProvider provider) {
        this.info = info;
        this.provider = provider;
    }

    @Override
    public String getEnclosingClassName(boolean fullyQualified) {
        return this.info.getClassFile().getClassName(fullyQualified);
    }

    @Override
    public String getIcon() {
        int flags = this.info.getAccessFlags();
        String key = org.fife.rsta.ac.java.classreader.Util.isDefault(flags) ? "methodDefaultIcon" : (org.fife.rsta.ac.java.classreader.Util.isPrivate(flags) ? "methodPrivateIcon" : (org.fife.rsta.ac.java.classreader.Util.isProtected(flags) ? "methodProtectedIcon" : (org.fife.rsta.ac.java.classreader.Util.isPublic(flags) ? "methodPublicIcon" : "methodDefaultIcon")));
        return key;
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
                for (int j = 0; j < td.getMemberCount(); ++j) {
                    Method m2;
                    Member member = td.getMember(j);
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
                for (Method method : contenders) {
                    boolean match = true;
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

    public String getParameterName(int index) {
        String name = this.info.getParameterName(index);
        if (name == null) {
            if (this.paramNames == null) {
                this.paramNames = new ArrayList<String>(1);
                int offs = 0;
                String rawSummary = this.getSummary();
                if (rawSummary != null && rawSummary.startsWith("/**")) {
                    int nextParam;
                    int summaryLen = rawSummary.length();
                    while ((nextParam = rawSummary.indexOf("@param", offs)) > -1) {
                        int end;
                        int temp;
                        for (temp = nextParam + "@param".length() + 1; temp < summaryLen && Character.isWhitespace(rawSummary.charAt(temp)); ++temp) {
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

    @Override
    public String getSignature() {
        StringBuilder sb = new StringBuilder(this.info.getName());
        sb.append('(');
        int paramCount = this.info.getParameterCount();
        for (int i = 0; i < paramCount; ++i) {
            sb.append(this.info.getParameterType(i, false));
            sb.append(' ');
            sb.append(this.getParameterName(i));
            if (i >= paramCount - 1) continue;
            sb.append(", ");
        }
        sb.append(')');
        return sb.toString();
    }

    @Override
    public String getSummary() {
        ClassFile cf = this.info.getClassFile();
        SourceLocation loc = this.provider.getSourceLocForClass(cf.getClassName(true));
        String summary = null;
        if (loc != null) {
            summary = this.getSummaryFromSourceLoc(loc, cf);
        }
        if (summary == null) {
            summary = this.info.getSignature();
        }
        return summary;
    }

    private String getSummaryFromSourceLoc(SourceLocation loc, ClassFile cf) {
        Method method = this.getMethodFromSourceLoc(loc, cf);
        return method != null ? method.getDocComment() : null;
    }

    @Override
    public String getType() {
        return this.info.getReturnTypeString(false);
    }

    @Override
    public boolean isAbstract() {
        return this.info.isAbstract();
    }

    @Override
    public boolean isConstructor() {
        return this.info.isConstructor();
    }

    @Override
    public boolean isDeprecated() {
        return this.info.isDeprecated();
    }

    @Override
    public boolean isFinal() {
        return this.info.isFinal();
    }

    @Override
    public boolean isStatic() {
        return this.info.isStatic();
    }
}

