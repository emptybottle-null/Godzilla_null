/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.metamodel.DerivedProperty;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface NodeWithVariables<N extends Node> {
    public NodeList<VariableDeclarator> getVariables();

    public N setVariables(NodeList<VariableDeclarator> var1);

    default public VariableDeclarator getVariable(int i) {
        return (VariableDeclarator)this.getVariables().get(i);
    }

    default public N setVariable(int i, VariableDeclarator variableDeclarator) {
        this.getVariables().set(i, variableDeclarator);
        return (N)((Node)((Object)this));
    }

    default public N addVariable(VariableDeclarator variableDeclarator) {
        this.getVariables().add(variableDeclarator);
        return (N)((Node)((Object)this));
    }

    default public Type getCommonType() {
        NodeList<VariableDeclarator> variables = this.getVariables();
        if (variables.isEmpty()) {
            throw new AssertionError((Object)"There is no common type since there are no variables.");
        }
        Type type = ((VariableDeclarator)variables.get(0)).getType();
        for (int i = 1; i < variables.size(); ++i) {
            if (!((VariableDeclarator)variables.get(i)).getType().equals(type)) {
                throw new AssertionError((Object)"The variables do not have a common type.");
            }
        }
        return type;
    }

    default public Type getElementType() {
        NodeList<VariableDeclarator> variables = this.getVariables();
        if (variables.isEmpty()) {
            throw new AssertionError((Object)"There is no element type since there are no variables.");
        }
        Type type = ((VariableDeclarator)variables.get(0)).getType().getElementType();
        for (int i = 1; i < variables.size(); ++i) {
            if (!((VariableDeclarator)variables.get(i)).getType().getElementType().equals(type)) {
                throw new AssertionError((Object)"The variables do not have a common type.");
            }
        }
        return type;
    }

    default public N setAllTypes(Type newType) {
        for (VariableDeclarator variable : this.getVariables()) {
            variable.setType(newType);
        }
        return (N)((Node)((Object)this));
    }

    @DerivedProperty
    default public Optional<Type> getMaximumCommonType() {
        return NodeWithVariables.calculateMaximumCommonType(this.getVariables().stream().map(v -> v.getType()).collect(Collectors.toList()));
    }

    public static Optional<Type> calculateMaximumCommonType(List<Type> types) {
        class Helper {
            Helper() {
            }

            private Optional<Type> toArrayLevel(Type type, int level) {
                if (level > type.getArrayLevel()) {
                    return Optional.empty();
                }
                for (int i = type.getArrayLevel(); i > level; --i) {
                    if (!(type instanceof ArrayType)) {
                        return Optional.empty();
                    }
                    type = ((ArrayType)type).getComponentType();
                }
                return Optional.of(type);
            }
        }
        Helper helper = new Helper();
        int level = 0;
        boolean keepGoing = true;
        while (keepGoing) {
            int currentLevel = level++;
            Object[] values = types.stream().map(v -> {
                Optional t = helper.toArrayLevel(v, currentLevel);
                return t.map(Node::toString).orElse(null);
            }).distinct().toArray();
            if (values.length == 1 && values[0] != null) continue;
            keepGoing = false;
        }
        return helper.toArrayLevel(types.get(0), --level);
    }
}

