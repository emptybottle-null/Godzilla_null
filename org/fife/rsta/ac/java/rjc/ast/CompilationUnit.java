/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.fife.rsta.ac.java.rjc.ast.AbstractASTNode;
import org.fife.rsta.ac.java.rjc.ast.CodeBlock;
import org.fife.rsta.ac.java.rjc.ast.ImportDeclaration;
import org.fife.rsta.ac.java.rjc.ast.Member;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.ast.NormalClassDeclaration;
import org.fife.rsta.ac.java.rjc.ast.Package;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclarationContainer;
import org.fife.rsta.ac.java.rjc.lang.Annotation;
import org.fife.rsta.ac.java.rjc.lexer.Offset;
import org.fife.rsta.ac.java.rjc.lexer.Token;
import org.fife.rsta.ac.java.rjc.notices.ParserNotice;

public class CompilationUnit
extends AbstractASTNode
implements TypeDeclarationContainer {
    private List<Annotation> annotations;
    private Package pkg;
    private List<ImportDeclaration> imports = new ArrayList<ImportDeclaration>(3);
    private List<TypeDeclaration> typeDeclarations = new ArrayList<TypeDeclaration>(1);
    private List<ParserNotice> notices;
    private static final Offset ZERO_OFFSET = new ZeroOffset();

    public CompilationUnit(String name) {
        super(name, ZERO_OFFSET);
    }

    public void addImportDeclaration(ImportDeclaration dec) {
        this.imports.add(dec);
    }

    public void addParserNotice(Token t, String msg) {
        this.addParserNotice(new ParserNotice(t, msg));
    }

    public void addParserNotice(ParserNotice notice) {
        if (this.notices == null) {
            this.notices = new ArrayList<ParserNotice>();
            this.notices.add(notice);
        }
    }

    @Override
    public void addTypeDeclaration(TypeDeclaration typeDec) {
        this.typeDeclarations.add(typeDec);
    }

    public int getAnnotationCount() {
        return this.annotations.size();
    }

    public Iterator<Annotation> getAnnotationIterator() {
        return this.annotations.iterator();
    }

    public TypeDeclaration getDeepestTypeDeclarationAtOffset(int offs) {
        TypeDeclaration td = this.getTypeDeclarationAtOffset(offs);
        if (td != null) {
            TypeDeclaration next = td.getChildTypeAtOffset(offs);
            while (next != null) {
                td = next;
                next = td.getChildTypeAtOffset(offs);
            }
        }
        return td;
    }

    public Point getEnclosingMethodRange(int offs) {
        Point range = null;
        Iterator<TypeDeclaration> i = this.getTypeDeclarationIterator();
        while (i.hasNext()) {
            TypeDeclaration td = i.next();
            int start = td.getBodyStartOffset();
            int end = td.getBodyEndOffset();
            if (offs < start || offs > end) continue;
            if (td instanceof NormalClassDeclaration) {
                NormalClassDeclaration ncd = (NormalClassDeclaration)td;
                Iterator<Member> j = ncd.getMemberIterator();
                while (j.hasNext()) {
                    Method method;
                    CodeBlock body;
                    Member m = j.next();
                    if (!(m instanceof Method) || (body = (method = (Method)m).getBody()) == null) continue;
                    int start2 = method.getNameStartOffset();
                    int end2 = body.getNameEndOffset();
                    if (offs < start2 || offs > end2) continue;
                    range = new Point(start2, end2);
                    break;
                }
            }
            if (range != null) continue;
            range = new Point(start, end);
        }
        return range;
    }

    public int getImportCount() {
        return this.imports.size();
    }

    public List<ImportDeclaration> getImports() {
        return new ArrayList<ImportDeclaration>(this.imports);
    }

    public Iterator<ImportDeclaration> getImportIterator() {
        return this.imports.iterator();
    }

    public Package getPackage() {
        return this.pkg;
    }

    public String getPackageName() {
        return this.pkg == null ? null : this.pkg.getName();
    }

    public ParserNotice getParserNotice(int index) {
        if (this.notices == null) {
            throw new IndexOutOfBoundsException("No parser notices available");
        }
        return this.notices.get(index);
    }

    public int getParserNoticeCount() {
        return this.notices == null ? 0 : this.notices.size();
    }

    public TypeDeclaration getTypeDeclaration(int index) {
        return this.typeDeclarations.get(index);
    }

    public TypeDeclaration getTypeDeclarationAtOffset(int offs) {
        TypeDeclaration typeDec = null;
        for (TypeDeclaration td : this.typeDeclarations) {
            if (!td.getBodyContainsOffset(offs)) continue;
            typeDec = td;
            break;
        }
        return typeDec;
    }

    public int getTypeDeclarationCount() {
        return this.typeDeclarations.size();
    }

    public Iterator<TypeDeclaration> getTypeDeclarationIterator() {
        return this.typeDeclarations.iterator();
    }

    public void setPackage(Package pkg) {
        this.pkg = pkg;
    }

    private static class ZeroOffset
    implements Offset {
        private ZeroOffset() {
        }

        @Override
        public int getOffset() {
            return 0;
        }
    }
}

