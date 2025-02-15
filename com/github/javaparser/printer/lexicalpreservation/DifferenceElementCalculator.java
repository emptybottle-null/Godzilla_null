/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.printer.concretesyntaxmodel.CsmIndent;
import com.github.javaparser.printer.concretesyntaxmodel.CsmMix;
import com.github.javaparser.printer.concretesyntaxmodel.CsmToken;
import com.github.javaparser.printer.concretesyntaxmodel.CsmUnindent;
import com.github.javaparser.printer.lexicalpreservation.Added;
import com.github.javaparser.printer.lexicalpreservation.ChildTextElement;
import com.github.javaparser.printer.lexicalpreservation.DifferenceElement;
import com.github.javaparser.printer.lexicalpreservation.Kept;
import com.github.javaparser.printer.lexicalpreservation.LexicalDifferenceCalculator;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.printer.lexicalpreservation.NodeText;
import com.github.javaparser.printer.lexicalpreservation.Removed;
import com.github.javaparser.printer.lexicalpreservation.Reshuffled;
import com.github.javaparser.printer.lexicalpreservation.TextElement;
import com.github.javaparser.printer.lexicalpreservation.TokenTextElement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class DifferenceElementCalculator {
    DifferenceElementCalculator() {
    }

    static boolean matching(CsmElement a, CsmElement b) {
        if (a instanceof LexicalDifferenceCalculator.CsmChild) {
            if (b instanceof LexicalDifferenceCalculator.CsmChild) {
                LexicalDifferenceCalculator.CsmChild childA = (LexicalDifferenceCalculator.CsmChild)a;
                LexicalDifferenceCalculator.CsmChild childB = (LexicalDifferenceCalculator.CsmChild)b;
                return childA.getChild().equals(childB.getChild());
            }
            if (b instanceof CsmToken) {
                return false;
            }
            if (b instanceof CsmIndent) {
                return false;
            }
            if (b instanceof CsmUnindent) {
                return false;
            }
            throw new UnsupportedOperationException(a.getClass().getSimpleName() + " " + b.getClass().getSimpleName());
        }
        if (a instanceof CsmToken) {
            if (b instanceof CsmToken) {
                CsmToken childA = (CsmToken)a;
                CsmToken childB = (CsmToken)b;
                return childA.equals(childB);
            }
            if (b instanceof LexicalDifferenceCalculator.CsmChild) {
                return false;
            }
            if (b instanceof CsmIndent) {
                return false;
            }
            if (b instanceof CsmUnindent) {
                return false;
            }
            throw new UnsupportedOperationException(a.getClass().getSimpleName() + " " + b.getClass().getSimpleName());
        }
        if (a instanceof CsmIndent) {
            return b instanceof CsmIndent;
        }
        if (a instanceof CsmUnindent) {
            return b instanceof CsmUnindent;
        }
        throw new UnsupportedOperationException(a.getClass().getSimpleName() + " " + b.getClass().getSimpleName());
    }

    private static boolean replacement(CsmElement a, CsmElement b) {
        if (a instanceof CsmIndent || b instanceof CsmIndent || a instanceof CsmUnindent || b instanceof CsmUnindent) {
            return false;
        }
        if (a instanceof LexicalDifferenceCalculator.CsmChild) {
            if (b instanceof LexicalDifferenceCalculator.CsmChild) {
                LexicalDifferenceCalculator.CsmChild childA = (LexicalDifferenceCalculator.CsmChild)a;
                LexicalDifferenceCalculator.CsmChild childB = (LexicalDifferenceCalculator.CsmChild)b;
                return childA.getChild().getClass().equals(childB.getChild().getClass());
            }
            if (b instanceof CsmToken) {
                return false;
            }
            throw new UnsupportedOperationException(a.getClass().getSimpleName() + " " + b.getClass().getSimpleName());
        }
        if (a instanceof CsmToken) {
            if (b instanceof CsmToken) {
                CsmToken childA = (CsmToken)a;
                CsmToken childB = (CsmToken)b;
                return childA.getTokenType() == childB.getTokenType();
            }
            if (b instanceof LexicalDifferenceCalculator.CsmChild) {
                return false;
            }
        }
        throw new UnsupportedOperationException(a.getClass().getSimpleName() + " " + b.getClass().getSimpleName());
    }

    private static List<ChildPositionInfo> findChildrenPositions(LexicalDifferenceCalculator.CalculatedSyntaxModel calculatedSyntaxModel) {
        ArrayList<ChildPositionInfo> positions = new ArrayList<ChildPositionInfo>();
        for (int i = 0; i < calculatedSyntaxModel.elements.size(); ++i) {
            CsmElement element = calculatedSyntaxModel.elements.get(i);
            if (!(element instanceof LexicalDifferenceCalculator.CsmChild)) continue;
            positions.add(new ChildPositionInfo(((LexicalDifferenceCalculator.CsmChild)element).getChild(), i));
        }
        return positions;
    }

    static List<DifferenceElement> calculate(LexicalDifferenceCalculator.CalculatedSyntaxModel original, LexicalDifferenceCalculator.CalculatedSyntaxModel after) {
        List<ChildPositionInfo> childrenInOriginal = DifferenceElementCalculator.findChildrenPositions(original);
        List<ChildPositionInfo> childrenInAfter = DifferenceElementCalculator.findChildrenPositions(after);
        ArrayList<ChildPositionInfo> commonChildren = new ArrayList<ChildPositionInfo>(childrenInOriginal);
        commonChildren.retainAll(childrenInAfter);
        LinkedList<DifferenceElement> elements = new LinkedList<DifferenceElement>();
        int originalIndex = 0;
        int afterIndex = 0;
        int commonChildrenIndex = 0;
        while (commonChildrenIndex < commonChildren.size()) {
            ChildPositionInfo child = (ChildPositionInfo)commonChildren.get(commonChildrenIndex++);
            int posOfNextChildInOriginal = childrenInOriginal.stream().filter(i -> i.equals(child)).map(i -> i.position).findFirst().get();
            int posOfNextChildInAfter = childrenInAfter.stream().filter(i -> i.equals(child)).map(i -> i.position).findFirst().get();
            if (originalIndex < posOfNextChildInOriginal || afterIndex < posOfNextChildInAfter) {
                elements.addAll(DifferenceElementCalculator.calculateImpl(original.sub(originalIndex, posOfNextChildInOriginal), after.sub(afterIndex, posOfNextChildInAfter)));
            }
            elements.add(new Kept(new LexicalDifferenceCalculator.CsmChild(child.node)));
            originalIndex = posOfNextChildInOriginal + 1;
            afterIndex = posOfNextChildInAfter + 1;
        }
        if (originalIndex < original.elements.size() || afterIndex < after.elements.size()) {
            elements.addAll(DifferenceElementCalculator.calculateImpl(original.sub(originalIndex, original.elements.size()), after.sub(afterIndex, after.elements.size())));
        }
        return elements;
    }

    private static void considerRemoval(NodeText nodeTextForChild, List<DifferenceElement> elements) {
        for (TextElement el : nodeTextForChild.getElements()) {
            if (el instanceof ChildTextElement) {
                ChildTextElement cte = (ChildTextElement)el;
                DifferenceElementCalculator.considerRemoval(LexicalPreservingPrinter.getOrCreateNodeText(cte.getChild()), elements);
                continue;
            }
            if (el instanceof TokenTextElement) {
                TokenTextElement tte = (TokenTextElement)el;
                elements.add(new Removed(new CsmToken(tte.getTokenKind(), tte.getText())));
                continue;
            }
            throw new UnsupportedOperationException(el.toString());
        }
    }

    private static int considerRemoval(CsmElement removedElement, int originalIndex, List<DifferenceElement> elements) {
        LexicalDifferenceCalculator.CsmChild removedChild;
        boolean dealtWith = false;
        if (removedElement instanceof LexicalDifferenceCalculator.CsmChild && (removedChild = (LexicalDifferenceCalculator.CsmChild)removedElement).getChild() instanceof Type && removedChild.getChild().getParentNode().isPresent() && removedChild.getChild().getParentNode().get() instanceof VariableDeclarator) {
            NodeText nodeTextForChild = LexicalPreservingPrinter.getOrCreateNodeText(removedChild.getChild());
            DifferenceElementCalculator.considerRemoval(nodeTextForChild, elements);
            ++originalIndex;
            dealtWith = true;
        }
        if (!dealtWith) {
            elements.add(new Removed(removedElement));
            ++originalIndex;
        }
        return originalIndex;
    }

    private static List<DifferenceElement> calculateImpl(LexicalDifferenceCalculator.CalculatedSyntaxModel original, LexicalDifferenceCalculator.CalculatedSyntaxModel after) {
        LinkedList<DifferenceElement> elements = new LinkedList<DifferenceElement>();
        int originalIndex = 0;
        int afterIndex = 0;
        do {
            if (originalIndex < original.elements.size() && afterIndex >= after.elements.size()) {
                CsmElement removedElement = original.elements.get(originalIndex);
                originalIndex = DifferenceElementCalculator.considerRemoval(removedElement, originalIndex, elements);
                continue;
            }
            if (originalIndex >= original.elements.size() && afterIndex < after.elements.size()) {
                elements.add(new Added(after.elements.get(afterIndex)));
                ++afterIndex;
                continue;
            }
            CsmElement nextOriginal = original.elements.get(originalIndex);
            CsmElement nextAfter = after.elements.get(afterIndex);
            if (nextOriginal instanceof CsmMix && nextAfter instanceof CsmMix) {
                if (((CsmMix)nextAfter).getElements().equals(((CsmMix)nextOriginal).getElements())) {
                    ((CsmMix)nextAfter).getElements().forEach(el -> elements.add(new Kept((CsmElement)el)));
                } else {
                    elements.add(new Reshuffled((CsmMix)nextOriginal, (CsmMix)nextAfter));
                }
                ++originalIndex;
                ++afterIndex;
                continue;
            }
            if (DifferenceElementCalculator.matching(nextOriginal, nextAfter)) {
                elements.add(new Kept(nextOriginal));
                ++originalIndex;
                ++afterIndex;
                continue;
            }
            if (DifferenceElementCalculator.replacement(nextOriginal, nextAfter)) {
                originalIndex = DifferenceElementCalculator.considerRemoval(nextOriginal, originalIndex, elements);
                elements.add(new Added(nextAfter));
                ++afterIndex;
                continue;
            }
            List<DifferenceElement> addingElements = DifferenceElementCalculator.calculate(original.from(originalIndex), after.from(afterIndex + 1));
            List<DifferenceElement> removingElements = null;
            if (DifferenceElementCalculator.cost(addingElements) > 0L) {
                removingElements = DifferenceElementCalculator.calculate(original.from(originalIndex + 1), after.from(afterIndex));
            }
            if (removingElements == null || DifferenceElementCalculator.cost(removingElements) > DifferenceElementCalculator.cost(addingElements)) {
                elements.add(new Added(nextAfter));
                ++afterIndex;
                continue;
            }
            elements.add(new Removed(nextOriginal));
            ++originalIndex;
        } while (originalIndex < original.elements.size() || afterIndex < after.elements.size());
        return elements;
    }

    private static long cost(List<DifferenceElement> elements) {
        return elements.stream().filter(e -> !(e instanceof Kept)).count();
    }

    static void removeIndentationElements(List<DifferenceElement> elements) {
        elements.removeIf(el -> el.getElement() instanceof CsmIndent || el.getElement() instanceof CsmUnindent);
    }

    public static class ChildPositionInfo {
        Node node;
        Integer position;

        ChildPositionInfo(Node node, Integer position) {
            this.node = node;
            this.position = position;
        }

        public boolean equals(Object other) {
            if (other == null || !(other instanceof ChildPositionInfo)) {
                return false;
            }
            ChildPositionInfo cpi = (ChildPositionInfo)other;
            return this.node.equals(cpi.node) && this.node.getRange().isPresent() && cpi.node.getRange().isPresent() && this.node.getRange().get().contains(cpi.node.getRange().get());
        }

        public int hashCode() {
            return this.node.hashCode() + this.position.hashCode();
        }
    }
}

