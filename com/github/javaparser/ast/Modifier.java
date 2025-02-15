/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.ModifierMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Arrays;

public class Modifier
extends Node {
    private Keyword keyword;

    public static Modifier publicModifier() {
        return new Modifier(Keyword.PUBLIC);
    }

    public static Modifier protectedModifier() {
        return new Modifier(Keyword.PROTECTED);
    }

    public static Modifier privateModifier() {
        return new Modifier(Keyword.PRIVATE);
    }

    public static Modifier abstractModifier() {
        return new Modifier(Keyword.ABSTRACT);
    }

    public static Modifier staticModifier() {
        return new Modifier(Keyword.STATIC);
    }

    public static Modifier finalModifier() {
        return new Modifier(Keyword.FINAL);
    }

    public static Modifier transientModifier() {
        return new Modifier(Keyword.TRANSIENT);
    }

    public static Modifier volatileModifier() {
        return new Modifier(Keyword.VOLATILE);
    }

    public static Modifier synchronizedModifier() {
        return new Modifier(Keyword.SYNCHRONIZED);
    }

    public static Modifier nativeModifier() {
        return new Modifier(Keyword.NATIVE);
    }

    public static Modifier strictfpModifier() {
        return new Modifier(Keyword.STRICTFP);
    }

    public static Modifier transitiveModifier() {
        return new Modifier(Keyword.TRANSITIVE);
    }

    public Modifier() {
        this(Keyword.PUBLIC);
    }

    @AllFieldsConstructor
    public Modifier(Keyword keyword) {
        this(null, keyword);
    }

    public Modifier(TokenRange tokenRange, Keyword keyword) {
        super(tokenRange);
        this.setKeyword(keyword);
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

    public Keyword getKeyword() {
        return this.keyword;
    }

    public Modifier setKeyword(Keyword keyword) {
        Utils.assertNotNull(keyword);
        if (keyword == this.keyword) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.KEYWORD, this.keyword, keyword);
        this.keyword = keyword;
        return this;
    }

    public static NodeList<Modifier> createModifierList(Keyword ... modifiers) {
        return Arrays.stream(modifiers).map(Modifier::new).collect(NodeList.toNodeList());
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public Modifier clone() {
        return (Modifier)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ModifierMetaModel getMetaModel() {
        return JavaParserMetaModel.modifierMetaModel;
    }

    public static enum Keyword {
        DEFAULT("default"),
        PUBLIC("public"),
        PROTECTED("protected"),
        PRIVATE("private"),
        ABSTRACT("abstract"),
        STATIC("static"),
        FINAL("final"),
        TRANSIENT("transient"),
        VOLATILE("volatile"),
        SYNCHRONIZED("synchronized"),
        NATIVE("native"),
        STRICTFP("strictfp"),
        TRANSITIVE("transitive");

        private final String codeRepresentation;

        private Keyword(String codeRepresentation) {
            this.codeRepresentation = codeRepresentation;
        }

        public String asString() {
            return this.codeRepresentation;
        }
    }
}

