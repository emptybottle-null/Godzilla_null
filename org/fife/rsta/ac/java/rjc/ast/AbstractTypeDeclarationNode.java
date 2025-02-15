/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.fife.rsta.ac.java.rjc.ast.AbstractASTNode;
import org.fife.rsta.ac.java.rjc.ast.Field;
import org.fife.rsta.ac.java.rjc.ast.Member;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.ast.Package;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;
import org.fife.rsta.ac.java.rjc.lexer.Offset;

public abstract class AbstractTypeDeclarationNode
extends AbstractASTNode
implements TypeDeclaration {
    private Package pkg;
    private Modifiers modifiers;
    private TypeDeclaration parentType;
    private List<TypeDeclaration> childTypes;
    private Offset bodyStartOffs;
    private Offset bodyEndOffs;
    private boolean deprecated;
    private String docComment;
    private List<Member> memberList;

    public AbstractTypeDeclarationNode(String name, Offset start) {
        super(name, start);
        this.init();
    }

    public AbstractTypeDeclarationNode(String name, Offset start, Offset end) {
        super(name, start, end);
        this.init();
    }

    public void addMember(Member member) {
        member.setParentTypeDeclaration(this);
        this.memberList.add(member);
    }

    @Override
    public void addTypeDeclaration(TypeDeclaration type) {
        if (this.childTypes == null) {
            this.childTypes = new ArrayList<TypeDeclaration>(1);
        }
        type.setParentType(this);
        this.childTypes.add(type);
    }

    @Override
    public boolean getBodyContainsOffset(int offs) {
        return offs >= this.getBodyStartOffset() && offs < this.getBodyEndOffset();
    }

    @Override
    public int getBodyEndOffset() {
        return this.bodyEndOffs != null ? this.bodyEndOffs.getOffset() : Integer.MAX_VALUE;
    }

    @Override
    public int getBodyStartOffset() {
        return this.bodyStartOffs == null ? 0 : this.bodyStartOffs.getOffset();
    }

    @Override
    public TypeDeclaration getChildType(int index) {
        return this.childTypes.get(index);
    }

    @Override
    public TypeDeclaration getChildTypeAtOffset(int offs) {
        TypeDeclaration typeDec = null;
        for (int i = 0; i < this.getChildTypeCount(); ++i) {
            TypeDeclaration td = this.getChildType(i);
            if (!td.getBodyContainsOffset(offs)) continue;
            typeDec = td;
            break;
        }
        return typeDec;
    }

    @Override
    public int getChildTypeCount() {
        return this.childTypes == null ? 0 : this.childTypes.size();
    }

    @Override
    public String getDocComment() {
        return this.docComment;
    }

    @Override
    public Iterator<Field> getFieldIterator() {
        ArrayList<Field> fields = new ArrayList<Field>();
        Iterator<Member> i = this.getMemberIterator();
        while (i.hasNext()) {
            Member member = i.next();
            if (!(member instanceof Field)) continue;
            fields.add((Field)member);
        }
        return fields.iterator();
    }

    @Override
    public Member getMember(int index) {
        return this.memberList.get(index);
    }

    @Override
    public int getMemberCount() {
        return this.memberList.size();
    }

    @Override
    public Iterator<Member> getMemberIterator() {
        return this.memberList.iterator();
    }

    @Override
    public Iterator<Method> getMethodIterator() {
        ArrayList<Method> methods = new ArrayList<Method>();
        Iterator<Member> i = this.getMemberIterator();
        while (i.hasNext()) {
            Member member = i.next();
            if (!(member instanceof Method)) continue;
            methods.add((Method)member);
        }
        return methods.iterator();
    }

    @Override
    public List<Method> getMethodsByName(String name) {
        ArrayList<Method> methods = new ArrayList<Method>();
        Iterator<Member> i = this.getMemberIterator();
        while (i.hasNext()) {
            Member member = i.next();
            if (!(member instanceof Method) || !name.equals(member.getName())) continue;
            methods.add((Method)member);
        }
        return methods;
    }

    @Override
    public Modifiers getModifiers() {
        return this.modifiers;
    }

    @Override
    public String getName(boolean fullyQualified) {
        Package pkg;
        String name = this.getName();
        if (fullyQualified && (pkg = this.getPackage()) != null) {
            name = pkg.getName() + "." + name;
        }
        return name;
    }

    @Override
    public Package getPackage() {
        return this.pkg;
    }

    @Override
    public TypeDeclaration getParentType() {
        return this.parentType;
    }

    private void init() {
        this.memberList = new ArrayList<Member>();
    }

    @Override
    public boolean isDeprecated() {
        return this.deprecated;
    }

    @Override
    public boolean isStatic() {
        return this.modifiers == null ? false : this.modifiers.isStatic();
    }

    public void setBodyEndOffset(Offset end) {
        this.bodyEndOffs = end;
    }

    public void setBodyStartOffset(Offset start) {
        this.bodyStartOffs = start;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    @Override
    public void setDocComment(String comment) {
        this.docComment = comment;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = modifiers;
    }

    public void setPackage(Package pkg) {
        this.pkg = pkg;
    }

    @Override
    public void setParentType(TypeDeclaration parentType) {
        this.parentType = parentType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.modifiers != null) {
            sb.append(this.modifiers.toString()).append(' ');
        }
        sb.append(this.getTypeString()).append(' ');
        sb.append(this.getName());
        return sb.toString();
    }
}

