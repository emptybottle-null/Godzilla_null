/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.util.Iterator;
import org.fife.rsta.ac.java.MemberCompletion;
import org.fife.rsta.ac.java.SourceCompletionProvider;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.FieldInfo;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.rsta.ac.java.rjc.ast.Field;
import org.fife.rsta.ac.java.rjc.ast.Member;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;

class FieldInfoData
implements MemberCompletion.Data {
    private FieldInfo info;
    private SourceCompletionProvider provider;

    public FieldInfoData(FieldInfo info, SourceCompletionProvider provider) {
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
        String key = org.fife.rsta.ac.java.classreader.Util.isDefault(flags) ? "fieldDefaultIcon" : (org.fife.rsta.ac.java.classreader.Util.isPrivate(flags) ? "fieldPrivateIcon" : (org.fife.rsta.ac.java.classreader.Util.isProtected(flags) ? "fieldProtectedIcon" : (org.fife.rsta.ac.java.classreader.Util.isPublic(flags) ? "fieldPublicIcon" : "fieldDefaultIcon")));
        return key;
    }

    @Override
    public String getSignature() {
        return this.info.getName();
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
            summary = this.info.getName();
        }
        return summary;
    }

    private String getSummaryFromSourceLoc(SourceLocation loc, ClassFile cf) {
        String summary = null;
        CompilationUnit cu = Util.getCompilationUnitFromDisk(loc, cf);
        if (cu != null) {
            Iterator<TypeDeclaration> i = cu.getTypeDeclarationIterator();
            block0: while (i.hasNext()) {
                TypeDeclaration td = i.next();
                String typeName = td.getName();
                if (!typeName.equals(cf.getClassName(false))) continue;
                Iterator<Member> j = td.getMemberIterator();
                while (j.hasNext()) {
                    Member member = j.next();
                    if (!(member instanceof Field) || !member.getName().equals(this.info.getName())) continue;
                    Field f2 = (Field)member;
                    summary = f2.getDocComment();
                    continue block0;
                }
            }
        }
        return summary;
    }

    @Override
    public String getType() {
        return this.info.getTypeString(false);
    }

    @Override
    public boolean isAbstract() {
        return false;
    }

    @Override
    public boolean isConstructor() {
        return false;
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

