/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.Position;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import java.util.Comparator;
import java.util.List;

public final class PositionUtils {
    private PositionUtils() {
    }

    public static <T extends Node> void sortByBeginPosition(List<T> nodes) {
        PositionUtils.sortByBeginPosition(nodes, false);
    }

    public static <T extends Node> void sortByBeginPosition(NodeList<T> nodes) {
        PositionUtils.sortByBeginPosition(nodes, false);
    }

    public static <T extends Node> void sortByBeginPosition(List<T> nodes, boolean ignoringAnnotations) {
        nodes.sort((o1, o2) -> PositionUtils.compare(o1, o2, ignoringAnnotations));
    }

    public static boolean areInOrder(Node a, Node b) {
        return PositionUtils.areInOrder(a, b, false);
    }

    public static boolean areInOrder(Node a, Node b, boolean ignoringAnnotations) {
        return PositionUtils.compare(a, b, ignoringAnnotations) <= 0;
    }

    private static int compare(Node a, Node b, boolean ignoringAnnotations) {
        if (a.getRange().isPresent() && !b.getRange().isPresent()) {
            return -1;
        }
        if (!a.getRange().isPresent() && b.getRange().isPresent()) {
            return 1;
        }
        if (!a.getRange().isPresent() && !b.getRange().isPresent()) {
            return 0;
        }
        if (ignoringAnnotations) {
            int signLine = Integer.signum(PositionUtils.beginLineWithoutConsideringAnnotation(a) - PositionUtils.beginLineWithoutConsideringAnnotation(b));
            if (signLine == 0) {
                return Integer.signum(PositionUtils.beginColumnWithoutConsideringAnnotation(a) - PositionUtils.beginColumnWithoutConsideringAnnotation(b));
            }
            return signLine;
        }
        Position aBegin = a.getBegin().get();
        Position bBegin = b.getBegin().get();
        int signLine = Integer.signum(aBegin.line - bBegin.line);
        if (signLine == 0) {
            return Integer.signum(aBegin.column - bBegin.column);
        }
        return signLine;
    }

    public static AnnotationExpr getLastAnnotation(Node node) {
        if (node instanceof NodeWithAnnotations) {
            NodeList<AnnotationExpr> annotations = NodeList.nodeList(((NodeWithAnnotations)((Object)node)).getAnnotations());
            if (annotations.isEmpty()) {
                return null;
            }
            PositionUtils.sortByBeginPosition(annotations);
            return (AnnotationExpr)annotations.get(annotations.size() - 1);
        }
        return null;
    }

    private static int beginLineWithoutConsideringAnnotation(Node node) {
        return PositionUtils.firstNonAnnotationNode((Node)node).getRange().get().begin.line;
    }

    private static int beginColumnWithoutConsideringAnnotation(Node node) {
        return PositionUtils.firstNonAnnotationNode((Node)node).getRange().get().begin.column;
    }

    private static Node firstNonAnnotationNode(Node node) {
        if (node instanceof ClassOrInterfaceDeclaration) {
            ClassOrInterfaceDeclaration casted = (ClassOrInterfaceDeclaration)node;
            Modifier earliestModifier = casted.getModifiers().stream().filter(modifier -> modifier.getRange().isPresent()).min(Comparator.comparing(o -> o.getRange().get().begin)).orElse(null);
            if (earliestModifier == null) {
                return casted.getName();
            }
            return earliestModifier;
        }
        if (node instanceof MethodDeclaration) {
            MethodDeclaration casted = (MethodDeclaration)node;
            Modifier earliestModifier = casted.getModifiers().stream().filter(modifier -> modifier.getRange().isPresent()).min(Comparator.comparing(o -> o.getRange().get().begin)).orElse(null);
            if (earliestModifier == null) {
                return casted.getType();
            }
            return earliestModifier;
        }
        if (node instanceof FieldDeclaration) {
            FieldDeclaration casted = (FieldDeclaration)node;
            Modifier earliestModifier = casted.getModifiers().stream().filter(modifier -> modifier.getRange().isPresent()).min(Comparator.comparing(o -> o.getRange().get().begin)).orElse(null);
            if (earliestModifier == null) {
                return casted.getVariable(0).getType();
            }
            return earliestModifier;
        }
        return node;
    }

    public static boolean nodeContains(Node container, Node other, boolean ignoringAnnotations) {
        if (!container.getRange().isPresent()) {
            throw new IllegalArgumentException("Cannot compare the positions of nodes if container node does not have a range.");
        }
        if (!other.getRange().isPresent()) {
            throw new IllegalArgumentException("Cannot compare the positions of nodes if contained node does not have a range.");
        }
        boolean nodeCanHaveAnnotations = container instanceof NodeWithAnnotations;
        if (!ignoringAnnotations || PositionUtils.getLastAnnotation(container) == null) {
            return container.containsWithinRange(other);
        }
        if (!container.containsWithinRange(other)) {
            return false;
        }
        if (!nodeCanHaveAnnotations) {
            return true;
        }
        Node nodeWithoutAnnotations = PositionUtils.firstNonAnnotationNode(container);
        Range rangeWithoutAnnotations = container.getRange().get().withBegin(nodeWithoutAnnotations.getBegin().get());
        return rangeWithoutAnnotations.strictlyContains(other.getRange().get());
    }
}

