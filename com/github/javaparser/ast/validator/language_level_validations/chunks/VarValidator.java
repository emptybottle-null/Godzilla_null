/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations.chunks;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithTokenRange;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.TypedValidator;
import java.util.Optional;

public class VarValidator
implements TypedValidator<VarType> {
    private boolean varAllowedInLambdaParameters;

    public VarValidator(boolean varAllowedInLambdaParameters) {
        this.varAllowedInLambdaParameters = varAllowedInLambdaParameters;
    }

    @Override
    public void accept(VarType node, ProblemReporter reporter) {
        Optional<VariableDeclarator> variableDeclarator = node.findAncestor(VariableDeclarator.class);
        if (!variableDeclarator.isPresent()) {
            boolean valid;
            if (this.varAllowedInLambdaParameters && (valid = node.findAncestor(Parameter.class).flatMap(Node::getParentNode).map(p -> p instanceof LambdaExpr).orElse(false).booleanValue())) {
                return;
            }
            this.reportIllegalPosition(node, reporter);
            return;
        }
        variableDeclarator.ifPresent(vd -> {
            Optional<Node> variableDeclarationExpr;
            if (vd.getType().isArrayType()) {
                reporter.report((NodeWithTokenRange<?>)vd, "\"var\" cannot have extra array brackets.", new Object[0]);
            }
            if (!(variableDeclarationExpr = vd.getParentNode()).isPresent()) {
                this.reportIllegalPosition(node, reporter);
                return;
            }
            variableDeclarationExpr.ifPresent(vdeNode -> {
                Optional<Node> container;
                if (!(vdeNode instanceof VariableDeclarationExpr)) {
                    this.reportIllegalPosition(node, reporter);
                    return;
                }
                VariableDeclarationExpr vde = (VariableDeclarationExpr)vdeNode;
                if (vde.getVariables().size() > 1) {
                    reporter.report(vde, "\"var\" only takes a single variable.", new Object[0]);
                }
                if (!(container = vdeNode.getParentNode()).isPresent()) {
                    this.reportIllegalPosition(node, reporter);
                    return;
                }
                container.ifPresent(c -> {
                    boolean positionIsFine;
                    boolean bl = positionIsFine = c instanceof ForStmt || c instanceof ForEachStmt || c instanceof ExpressionStmt || c instanceof TryStmt;
                    if (!positionIsFine) {
                        this.reportIllegalPosition(node, reporter);
                    }
                    if (c instanceof ExpressionStmt) {
                        if (!vd.getInitializer().isPresent()) {
                            reporter.report(node, "\"var\" needs an initializer.", new Object[0]);
                        }
                        vd.getInitializer().ifPresent(initializer -> {
                            if (initializer instanceof NullLiteralExpr) {
                                reporter.report(node, "\"var\" cannot infer type from just null.", new Object[0]);
                            }
                            if (initializer instanceof ArrayInitializerExpr) {
                                reporter.report(node, "\"var\" cannot infer array types.", new Object[0]);
                            }
                        });
                    }
                });
            });
        });
    }

    private void reportIllegalPosition(VarType n, ProblemReporter reporter) {
        reporter.report(n, "\"var\" is not allowed here.", new Object[0]);
    }
}

