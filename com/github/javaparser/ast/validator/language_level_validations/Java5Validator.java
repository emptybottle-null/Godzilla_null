/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.validator.ReservedKeywordValidator;
import com.github.javaparser.ast.validator.SingleNodeTypeValidator;
import com.github.javaparser.ast.validator.TreeVisitorValidator;
import com.github.javaparser.ast.validator.Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java1_4Validator;
import java.util.Optional;

public class Java5Validator
extends Java1_4Validator {
    final Validator genericsWithoutDiamondOperator = new TreeVisitorValidator((node, reporter) -> {
        Optional<NodeList<Type>> typeArguments;
        if (node instanceof NodeWithTypeArguments && (typeArguments = ((NodeWithTypeArguments)((Object)node)).getTypeArguments()).isPresent() && typeArguments.get().isEmpty()) {
            reporter.report(node, "The diamond operator is not supported.", new Object[0]);
        }
    });
    protected final Validator noPrimitiveGenericArguments = new TreeVisitorValidator((node, reporter) -> {
        if (node instanceof NodeWithTypeArguments) {
            Optional<NodeList<Type>> typeArguments = ((NodeWithTypeArguments)((Object)node)).getTypeArguments();
            typeArguments.ifPresent(types -> types.forEach(ty -> {
                if (ty instanceof PrimitiveType) {
                    reporter.report(node, "Type arguments may not be primitive.", new Object[0]);
                }
            }));
        }
    });
    final Validator forEachStmt = new SingleNodeTypeValidator<ForEachStmt>(ForEachStmt.class, (node, reporter) -> {
        VariableDeclarationExpr declaration = node.getVariable();
        if (declaration.getVariables().size() != 1) {
            reporter.report(node, "A foreach statement's variable declaration must have exactly one variable declarator. Given: " + declaration.getVariables().size() + ".", new Object[0]);
        }
    });
    final Validator enumNotAllowed = new ReservedKeywordValidator("enum");

    public Java5Validator() {
        this.replace(this.noGenerics, this.genericsWithoutDiamondOperator);
        this.add(this.noPrimitiveGenericArguments);
        this.add(this.enumNotAllowed);
        this.add(this.forEachStmt);
        this.remove(this.noAnnotations);
        this.remove(this.noEnums);
        this.remove(this.noVarargs);
        this.remove(this.noForEach);
        this.remove(this.noStaticImports);
    }
}

