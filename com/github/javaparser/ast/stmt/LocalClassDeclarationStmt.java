/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.LocalClassDeclarationStmtMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class LocalClassDeclarationStmt
extends Statement {
    private ClassOrInterfaceDeclaration classDeclaration;

    public LocalClassDeclarationStmt() {
        this(null, new ClassOrInterfaceDeclaration());
    }

    @AllFieldsConstructor
    public LocalClassDeclarationStmt(ClassOrInterfaceDeclaration classDeclaration) {
        this(null, classDeclaration);
    }

    public LocalClassDeclarationStmt(TokenRange tokenRange, ClassOrInterfaceDeclaration classDeclaration) {
        super(tokenRange);
        this.setClassDeclaration(classDeclaration);
        this.customInitialization();
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public ClassOrInterfaceDeclaration getClassDeclaration() {
        return this.classDeclaration;
    }

    public LocalClassDeclarationStmt setClassDeclaration(ClassOrInterfaceDeclaration classDeclaration) {
        Utils.assertNotNull(classDeclaration);
        if (classDeclaration == this.classDeclaration) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.CLASS_DECLARATION, this.classDeclaration, classDeclaration);
        if (this.classDeclaration != null) {
            this.classDeclaration.setParentNode(null);
        }
        this.classDeclaration = classDeclaration;
        this.setAsParentNodeOf(classDeclaration);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public LocalClassDeclarationStmt clone() {
        return (LocalClassDeclarationStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public LocalClassDeclarationStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.localClassDeclarationStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.classDeclaration) {
            this.setClassDeclaration((ClassOrInterfaceDeclaration)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isLocalClassDeclarationStmt() {
        return true;
    }

    @Override
    public LocalClassDeclarationStmt asLocalClassDeclarationStmt() {
        return this;
    }

    @Override
    public void ifLocalClassDeclarationStmt(Consumer<LocalClassDeclarationStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<LocalClassDeclarationStmt> toLocalClassDeclarationStmt() {
        return Optional.of(this);
    }
}

