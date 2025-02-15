/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.type;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.PrimitiveTypeMetaModel;
import com.github.javaparser.resolution.types.ResolvedPrimitiveType;
import com.github.javaparser.utils.Utils;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Consumer;

public class PrimitiveType
extends Type
implements NodeWithAnnotations<PrimitiveType> {
    static final HashMap<String, Primitive> unboxMap = new HashMap();
    private Primitive type;

    public static PrimitiveType booleanType() {
        return new PrimitiveType(Primitive.BOOLEAN);
    }

    public static PrimitiveType charType() {
        return new PrimitiveType(Primitive.CHAR);
    }

    public static PrimitiveType byteType() {
        return new PrimitiveType(Primitive.BYTE);
    }

    public static PrimitiveType shortType() {
        return new PrimitiveType(Primitive.SHORT);
    }

    public static PrimitiveType intType() {
        return new PrimitiveType(Primitive.INT);
    }

    public static PrimitiveType longType() {
        return new PrimitiveType(Primitive.LONG);
    }

    public static PrimitiveType floatType() {
        return new PrimitiveType(Primitive.FLOAT);
    }

    public static PrimitiveType doubleType() {
        return new PrimitiveType(Primitive.DOUBLE);
    }

    public PrimitiveType() {
        this(null, Primitive.INT, new NodeList<AnnotationExpr>());
    }

    public PrimitiveType(Primitive type) {
        this(null, type, new NodeList<AnnotationExpr>());
    }

    @AllFieldsConstructor
    public PrimitiveType(Primitive type, NodeList<AnnotationExpr> annotations) {
        this(null, type, annotations);
    }

    public PrimitiveType(TokenRange tokenRange, Primitive type, NodeList<AnnotationExpr> annotations) {
        super(tokenRange, annotations);
        this.setType(type);
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

    public Primitive getType() {
        return this.type;
    }

    public ClassOrInterfaceType toBoxedType() {
        return this.type.toBoxedType();
    }

    @Override
    public String toDescriptor() {
        return this.type.descriptor;
    }

    public PrimitiveType setType(Primitive type) {
        Utils.assertNotNull(type);
        if (type == this.type) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
        this.type = type;
        return this;
    }

    @Override
    public String asString() {
        return this.type.asString();
    }

    @Override
    public PrimitiveType setAnnotations(NodeList<AnnotationExpr> annotations) {
        return (PrimitiveType)super.setAnnotations(annotations);
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public PrimitiveType clone() {
        return (PrimitiveType)this.accept(new CloneVisitor(), null);
    }

    @Override
    public PrimitiveTypeMetaModel getMetaModel() {
        return JavaParserMetaModel.primitiveTypeMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isPrimitiveType() {
        return true;
    }

    @Override
    public PrimitiveType asPrimitiveType() {
        return this;
    }

    @Override
    public void ifPrimitiveType(Consumer<PrimitiveType> action) {
        action.accept(this);
    }

    @Override
    public ResolvedPrimitiveType resolve() {
        return this.getSymbolResolver().toResolvedType(this, ResolvedPrimitiveType.class);
    }

    @Override
    public Optional<PrimitiveType> toPrimitiveType() {
        return Optional.of(this);
    }

    static {
        for (Primitive unboxedType : Primitive.values()) {
            unboxMap.put(unboxedType.nameOfBoxedType, unboxedType);
        }
    }

    public static enum Primitive {
        BOOLEAN("Boolean", "Z"),
        CHAR("Character", "C"),
        BYTE("Byte", "B"),
        SHORT("Short", "S"),
        INT("Integer", "I"),
        LONG("Long", "L"),
        FLOAT("Float", "F"),
        DOUBLE("Double", "D");

        final String nameOfBoxedType;
        final String descriptor;
        private String codeRepresentation;

        public ClassOrInterfaceType toBoxedType() {
            return StaticJavaParser.parseClassOrInterfaceType(this.nameOfBoxedType);
        }

        public String asString() {
            return this.codeRepresentation;
        }

        private Primitive(String nameOfBoxedType, String descriptor) {
            this.nameOfBoxedType = nameOfBoxedType;
            this.codeRepresentation = this.name().toLowerCase();
            this.descriptor = descriptor;
        }
    }
}

