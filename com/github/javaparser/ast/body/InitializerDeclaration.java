/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.body;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithBlockStmt;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.InitializerDeclarationMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class InitializerDeclaration
extends BodyDeclaration<InitializerDeclaration>
implements NodeWithJavadoc<InitializerDeclaration>,
NodeWithBlockStmt<InitializerDeclaration> {
    private boolean isStatic;
    private BlockStmt body;

    public InitializerDeclaration() {
        this(null, false, new BlockStmt());
    }

    @AllFieldsConstructor
    public InitializerDeclaration(boolean isStatic, BlockStmt body) {
        this(null, isStatic, body);
    }

    public InitializerDeclaration(TokenRange tokenRange, boolean isStatic, BlockStmt body) {
        super(tokenRange);
        this.setStatic(isStatic);
        this.setBody(body);
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

    @Override
    public BlockStmt getBody() {
        return this.body;
    }

    public boolean isStatic() {
        return this.isStatic;
    }

    @Override
    public InitializerDeclaration setBody(BlockStmt body) {
        Utils.assertNotNull(body);
        if (body == this.body) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.BODY, this.body, body);
        if (this.body != null) {
            this.body.setParentNode(null);
        }
        this.body = body;
        this.setAsParentNodeOf(body);
        return this;
    }

    public InitializerDeclaration setStatic(boolean isStatic) {
        if (isStatic == this.isStatic) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.STATIC, this.isStatic, isStatic);
        this.isStatic = isStatic;
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
    public InitializerDeclaration clone() {
        return (InitializerDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public InitializerDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.initializerDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.body) {
            this.setBody((BlockStmt)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isInitializerDeclaration() {
        return true;
    }

    @Override
    public InitializerDeclaration asInitializerDeclaration() {
        return this;
    }

    @Override
    public void ifInitializerDeclaration(Consumer<InitializerDeclaration> action) {
        action.accept(this);
    }

    @Override
    public Optional<InitializerDeclaration> toInitializerDeclaration() {
        return Optional.of(this);
    }
}

