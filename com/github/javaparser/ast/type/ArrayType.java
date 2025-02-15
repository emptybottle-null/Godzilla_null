/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.type;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ArrayTypeMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.resolution.types.ResolvedArrayType;
import com.github.javaparser.utils.Pair;
import com.github.javaparser.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ArrayType
extends ReferenceType
implements NodeWithAnnotations<ArrayType> {
    private Type componentType;
    private Origin origin;

    @Override
    public ResolvedArrayType resolve() {
        return this.getSymbolResolver().toResolvedType(this, ResolvedArrayType.class);
    }

    @AllFieldsConstructor
    public ArrayType(Type componentType, Origin origin, NodeList<AnnotationExpr> annotations) {
        this(null, componentType, origin, annotations);
    }

    public ArrayType(Type type, AnnotationExpr ... annotations) {
        this(type, Origin.TYPE, NodeList.nodeList((Node[])annotations));
    }

    public ArrayType(TokenRange tokenRange, Type componentType, Origin origin, NodeList<AnnotationExpr> annotations) {
        super(tokenRange, annotations);
        this.setComponentType(componentType);
        this.setOrigin(origin);
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

    public Type getComponentType() {
        return this.componentType;
    }

    public ArrayType setComponentType(Type componentType) {
        Utils.assertNotNull(componentType);
        if (componentType == this.componentType) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.COMPONENT_TYPE, this.componentType, componentType);
        if (this.componentType != null) {
            this.componentType.setParentNode(null);
        }
        this.componentType = componentType;
        this.setAsParentNodeOf(componentType);
        return this;
    }

    @SafeVarargs
    public static Type wrapInArrayTypes(Type type, List<ArrayBracketPair> ... arrayBracketPairLists) {
        for (int i = arrayBracketPairLists.length - 1; i >= 0; --i) {
            List<ArrayBracketPair> arrayBracketPairList = arrayBracketPairLists[i];
            if (arrayBracketPairList == null) continue;
            for (int j = arrayBracketPairList.size() - 1; j >= 0; --j) {
                ArrayBracketPair pair = arrayBracketPairList.get(j);
                TokenRange tokenRange = null;
                if (type.getTokenRange().isPresent() && pair.getTokenRange().isPresent()) {
                    tokenRange = new TokenRange(type.getTokenRange().get().getBegin(), pair.getTokenRange().get().getEnd());
                }
                type = new ArrayType(tokenRange, type, pair.getOrigin(), pair.getAnnotations());
                if (tokenRange == null) continue;
                type.setRange(tokenRange.toRange().get());
            }
        }
        return type;
    }

    public static Pair<Type, List<ArrayBracketPair>> unwrapArrayTypes(Type type) {
        ArrayList<ArrayBracketPair> arrayBracketPairs = new ArrayList<ArrayBracketPair>(0);
        while (type instanceof ArrayType) {
            ArrayType arrayType = (ArrayType)type;
            arrayBracketPairs.add(new ArrayBracketPair(type.getTokenRange().orElse(null), arrayType.getOrigin(), arrayType.getAnnotations()));
            type = arrayType.getComponentType();
        }
        return new Pair<Type, List<ArrayBracketPair>>(type, arrayBracketPairs);
    }

    @Override
    public ArrayType setAnnotations(NodeList<AnnotationExpr> annotations) {
        return (ArrayType)super.setAnnotations(annotations);
    }

    public Origin getOrigin() {
        return this.origin;
    }

    public ArrayType setOrigin(Origin origin) {
        Utils.assertNotNull(origin);
        if (origin == this.origin) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ORIGIN, this.origin, origin);
        this.origin = origin;
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
    public String asString() {
        return this.componentType.asString() + "[]";
    }

    @Override
    public String toDescriptor() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(this.componentType.toDescriptor());
        return sb.toString();
    }

    @Override
    public ArrayType clone() {
        return (ArrayType)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ArrayTypeMetaModel getMetaModel() {
        return JavaParserMetaModel.arrayTypeMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.componentType) {
            this.setComponentType((Type)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isArrayType() {
        return true;
    }

    @Override
    public ArrayType asArrayType() {
        return this;
    }

    @Override
    public void ifArrayType(Consumer<ArrayType> action) {
        action.accept(this);
    }

    @Override
    public Optional<ArrayType> toArrayType() {
        return Optional.of(this);
    }

    public static class ArrayBracketPair {
        private TokenRange tokenRange;
        private NodeList<AnnotationExpr> annotations = new NodeList();
        private Origin origin;

        public ArrayBracketPair(TokenRange tokenRange, Origin origin, NodeList<AnnotationExpr> annotations) {
            this.setTokenRange(tokenRange);
            this.setAnnotations(annotations);
            this.setOrigin(origin);
        }

        public NodeList<AnnotationExpr> getAnnotations() {
            return this.annotations;
        }

        public ArrayBracketPair setAnnotations(NodeList<AnnotationExpr> annotations) {
            this.annotations = Utils.assertNotNull(annotations);
            return this;
        }

        public ArrayBracketPair setTokenRange(TokenRange range) {
            this.tokenRange = range;
            return this;
        }

        public Optional<TokenRange> getTokenRange() {
            return Optional.ofNullable(this.tokenRange);
        }

        public Origin getOrigin() {
            return this.origin;
        }

        public ArrayBracketPair setOrigin(Origin origin) {
            this.origin = Utils.assertNotNull(origin);
            return this;
        }
    }

    public static enum Origin {
        NAME,
        TYPE;

    }
}

