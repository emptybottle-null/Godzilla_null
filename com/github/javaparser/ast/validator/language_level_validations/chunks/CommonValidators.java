/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations.chunks;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithTokenRange;
import com.github.javaparser.ast.validator.SimpleValidator;
import com.github.javaparser.ast.validator.SingleNodeTypeValidator;
import com.github.javaparser.ast.validator.TreeVisitorValidator;
import com.github.javaparser.ast.validator.Validators;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.function.Consumer;

public class CommonValidators
extends Validators {
    public CommonValidators() {
        super(new SimpleValidator<ClassOrInterfaceDeclaration>(ClassOrInterfaceDeclaration.class, n -> !n.isInterface() && n.getExtendedTypes().size() > 1, (n, reporter) -> reporter.report(n.getExtendedTypes(1), "A class cannot extend more than one other class.", new Object[0])), new SimpleValidator<ClassOrInterfaceDeclaration>(ClassOrInterfaceDeclaration.class, n -> n.isInterface() && !n.getImplementedTypes().isEmpty(), (n, reporter) -> reporter.report(n.getImplementedTypes(0), "An interface cannot implement other interfaces.", new Object[0])), new SingleNodeTypeValidator<ClassOrInterfaceDeclaration>(ClassOrInterfaceDeclaration.class, (n, reporter) -> {
            if (n.isInterface()) {
                n.getMembers().forEach((Consumer<BodyDeclaration<?>>)((Consumer<BodyDeclaration>)mem -> {
                    if (mem instanceof InitializerDeclaration) {
                        reporter.report((NodeWithTokenRange<?>)mem, "An interface cannot have initializers.", new Object[0]);
                    }
                }));
            }
        }), new SingleNodeTypeValidator<AssignExpr>(AssignExpr.class, (n, reporter) -> {
            Expression target = n.getTarget();
            while (target instanceof EnclosedExpr) {
                target = ((EnclosedExpr)target).getInner();
            }
            if (target instanceof NameExpr || target instanceof ArrayAccessExpr || target instanceof FieldAccessExpr) {
                return;
            }
            reporter.report(n.getTarget(), "Illegal left hand side of an assignment.", new Object[0]);
        }), new TreeVisitorValidator((node, problemReporter) -> {
            NodeMetaModel mm = node.getMetaModel();
            for (PropertyMetaModel ppm : mm.getAllPropertyMetaModels()) {
                NodeList value;
                if (!ppm.isNonEmpty() || !ppm.isNodeList() || !(value = (NodeList)ppm.getValue(node)).isEmpty()) continue;
                problemReporter.report(node, "%s.%s can not be empty.", mm.getTypeName(), ppm.getName());
            }
        }));
    }
}

