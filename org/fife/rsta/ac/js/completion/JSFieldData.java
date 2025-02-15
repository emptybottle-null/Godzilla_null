/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.completion;

import java.util.Iterator;
import org.fife.rsta.ac.java.JarManager;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.FieldInfo;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.rsta.ac.java.rjc.ast.Field;
import org.fife.rsta.ac.java.rjc.ast.Member;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;

public class JSFieldData {
    private FieldInfo info;
    private JarManager jarManager;

    public JSFieldData(FieldInfo info, JarManager jarManager) {
        this.info = info;
        this.jarManager = jarManager;
    }

    public Field getField() {
        ClassFile cf = this.info.getClassFile();
        SourceLocation loc = this.jarManager.getSourceLocForClass(cf.getClassName(true));
        return this.getFieldFromSourceLoc(loc, cf);
    }

    private Field getFieldFromSourceLoc(SourceLocation loc, ClassFile cf) {
        CompilationUnit cu = Util.getCompilationUnitFromDisk(loc, cf);
        if (cu != null) {
            Iterator<TypeDeclaration> i = cu.getTypeDeclarationIterator();
            while (i.hasNext()) {
                TypeDeclaration td = i.next();
                String typeName = td.getName();
                if (!typeName.equals(cf.getClassName(false))) continue;
                Iterator<Member> j = td.getMemberIterator();
                while (j.hasNext()) {
                    Member member = j.next();
                    if (!(member instanceof Field) || !member.getName().equals(this.info.getName())) continue;
                    return (Field)member;
                }
            }
        }
        return null;
    }

    public String getType(boolean qualified) {
        return this.info.getTypeString(qualified);
    }

    public boolean isStatic() {
        return this.info.isStatic();
    }

    public boolean isPublic() {
        int access = this.info.getAccessFlags();
        return org.fife.rsta.ac.java.classreader.Util.isPublic(access);
    }

    public String getEnclosingClassName(boolean fullyQualified) {
        return this.info.getClassFile().getClassName(fullyQualified);
    }
}

