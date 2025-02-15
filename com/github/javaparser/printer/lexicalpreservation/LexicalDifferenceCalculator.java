/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.TokenTypes;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.TextBlockLiteralExpr;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.printer.ConcreteSyntaxModel;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.Stringable;
import com.github.javaparser.printer.concretesyntaxmodel.CsmAttribute;
import com.github.javaparser.printer.concretesyntaxmodel.CsmChar;
import com.github.javaparser.printer.concretesyntaxmodel.CsmComment;
import com.github.javaparser.printer.concretesyntaxmodel.CsmConditional;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.printer.concretesyntaxmodel.CsmIndent;
import com.github.javaparser.printer.concretesyntaxmodel.CsmList;
import com.github.javaparser.printer.concretesyntaxmodel.CsmMix;
import com.github.javaparser.printer.concretesyntaxmodel.CsmNone;
import com.github.javaparser.printer.concretesyntaxmodel.CsmOrphanCommentsEnding;
import com.github.javaparser.printer.concretesyntaxmodel.CsmSequence;
import com.github.javaparser.printer.concretesyntaxmodel.CsmSingleReference;
import com.github.javaparser.printer.concretesyntaxmodel.CsmString;
import com.github.javaparser.printer.concretesyntaxmodel.CsmToken;
import com.github.javaparser.printer.concretesyntaxmodel.CsmUnindent;
import com.github.javaparser.printer.lexicalpreservation.Added;
import com.github.javaparser.printer.lexicalpreservation.Difference;
import com.github.javaparser.printer.lexicalpreservation.DifferenceElement;
import com.github.javaparser.printer.lexicalpreservation.DifferenceElementCalculator;
import com.github.javaparser.printer.lexicalpreservation.NodeText;
import com.github.javaparser.printer.lexicalpreservation.changes.Change;
import com.github.javaparser.printer.lexicalpreservation.changes.ListAdditionChange;
import com.github.javaparser.printer.lexicalpreservation.changes.ListRemovalChange;
import com.github.javaparser.printer.lexicalpreservation.changes.ListReplacementChange;
import com.github.javaparser.printer.lexicalpreservation.changes.NoChange;
import com.github.javaparser.printer.lexicalpreservation.changes.PropertyChange;
import com.github.javaparser.utils.LineSeparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class LexicalDifferenceCalculator {
    LexicalDifferenceCalculator() {
    }

    List<DifferenceElement> calculateListRemovalDifference(ObservableProperty observableProperty, NodeList<?> nodeList, int index) {
        Node container = nodeList.getParentNodeForChildren();
        CsmElement element = ConcreteSyntaxModel.forClass(container.getClass());
        CalculatedSyntaxModel original = this.calculatedSyntaxModelForNode(element, container);
        CalculatedSyntaxModel after = this.calculatedSyntaxModelAfterListRemoval(element, observableProperty, nodeList, index);
        return DifferenceElementCalculator.calculate(original, after);
    }

    List<DifferenceElement> calculateListAdditionDifference(ObservableProperty observableProperty, NodeList<?> nodeList, int index, Node nodeAdded) {
        Node container = nodeList.getParentNodeForChildren();
        CsmElement element = ConcreteSyntaxModel.forClass(container.getClass());
        CalculatedSyntaxModel original = this.calculatedSyntaxModelForNode(element, container);
        CalculatedSyntaxModel after = this.calculatedSyntaxModelAfterListAddition(element, observableProperty, nodeList, index, nodeAdded);
        List<DifferenceElement> differenceElements = DifferenceElementCalculator.calculate(original, after);
        LineSeparator lineSeparator = container.getLineEndingStyleOrDefault(LineSeparator.SYSTEM);
        this.replaceEolTokens(differenceElements, lineSeparator);
        return differenceElements;
    }

    private void replaceEolTokens(List<DifferenceElement> differenceElements, LineSeparator lineSeparator) {
        for (int i = 0; i < differenceElements.size(); ++i) {
            boolean isWhitespaceToken;
            DifferenceElement differenceElement = differenceElements.get(i);
            if (!differenceElement.isAdded()) continue;
            CsmElement element = differenceElement.getElement();
            boolean bl = isWhitespaceToken = element instanceof CsmToken && ((CsmToken)element).isNewLine();
            if (!isWhitespaceToken) continue;
            differenceElements.set(i, new Added(CsmElement.newline(lineSeparator)));
        }
    }

    List<DifferenceElement> calculateListReplacementDifference(ObservableProperty observableProperty, NodeList<?> nodeList, int index, Node newValue) {
        Node container = nodeList.getParentNodeForChildren();
        CsmElement element = ConcreteSyntaxModel.forClass(container.getClass());
        CalculatedSyntaxModel original = this.calculatedSyntaxModelForNode(element, container);
        CalculatedSyntaxModel after = this.calculatedSyntaxModelAfterListReplacement(element, observableProperty, nodeList, index, newValue);
        return DifferenceElementCalculator.calculate(original, after);
    }

    void calculatePropertyChange(NodeText nodeText, Node observedNode, ObservableProperty property, Object oldValue, Object newValue) {
        if (nodeText == null) {
            throw new NullPointerException();
        }
        CsmElement element = ConcreteSyntaxModel.forClass(observedNode.getClass());
        CalculatedSyntaxModel original = this.calculatedSyntaxModelForNode(element, observedNode);
        CalculatedSyntaxModel after = this.calculatedSyntaxModelAfterPropertyChange(element, observedNode, property, oldValue, newValue);
        List<DifferenceElement> differenceElements = DifferenceElementCalculator.calculate(original, after);
        Difference difference = new Difference(differenceElements, nodeText, observedNode);
        difference.apply();
    }

    CalculatedSyntaxModel calculatedSyntaxModelForNode(CsmElement csm, Node node) {
        LinkedList<CsmElement> elements = new LinkedList<CsmElement>();
        this.calculatedSyntaxModelForNode(csm, node, elements, new NoChange());
        return new CalculatedSyntaxModel(elements);
    }

    CalculatedSyntaxModel calculatedSyntaxModelForNode(Node node) {
        return this.calculatedSyntaxModelForNode(ConcreteSyntaxModel.forClass(node.getClass()), node);
    }

    private void calculatedSyntaxModelForNode(CsmElement csm, Node node, List<CsmElement> elements, Change change) {
        if (csm instanceof CsmSequence) {
            CsmSequence csmSequence = (CsmSequence)csm;
            csmSequence.getElements().forEach(e -> this.calculatedSyntaxModelForNode((CsmElement)e, node, elements, change));
        } else if (!(csm instanceof CsmComment)) {
            if (csm instanceof CsmSingleReference) {
                CsmSingleReference csmSingleReference = (CsmSingleReference)csm;
                Node child = change instanceof PropertyChange && ((PropertyChange)change).getProperty() == csmSingleReference.getProperty() ? (Node)((PropertyChange)change).getNewValue() : csmSingleReference.getProperty().getValueAsSingleReference(node);
                if (child != null) {
                    if (node.getComment().isPresent() && node instanceof ExpressionStmt) {
                        LineSeparator lineSeparator = node.getLineEndingStyleOrDefault(LineSeparator.SYSTEM);
                        elements.add(new CsmChild(node.getComment().get()));
                        elements.add(new CsmToken(TokenTypes.eolTokenKind(lineSeparator), lineSeparator.asRawString()));
                    }
                    elements.add(new CsmChild(child));
                }
            } else if (!(csm instanceof CsmNone)) {
                if (csm instanceof CsmToken) {
                    elements.add(csm);
                } else if (!(csm instanceof CsmOrphanCommentsEnding)) {
                    if (csm instanceof CsmList) {
                        CsmList csmList = (CsmList)csm;
                        if (csmList.getProperty().isAboutNodes()) {
                            NodeList nodeList;
                            Object rawValue = change.getValue(csmList.getProperty(), node);
                            if (rawValue instanceof Optional) {
                                Optional optional = (Optional)rawValue;
                                if (optional.isPresent()) {
                                    if (!(optional.get() instanceof NodeList)) {
                                        throw new IllegalStateException("Expected NodeList, found " + optional.get().getClass().getCanonicalName());
                                    }
                                    nodeList = (NodeList)optional.get();
                                } else {
                                    nodeList = new NodeList();
                                }
                            } else {
                                if (!(rawValue instanceof NodeList)) {
                                    throw new IllegalStateException("Expected NodeList, found " + rawValue.getClass().getCanonicalName());
                                }
                                nodeList = (NodeList)rawValue;
                            }
                            if (!nodeList.isEmpty()) {
                                this.calculatedSyntaxModelForNode(csmList.getPreceeding(), node, elements, change);
                                for (int i = 0; i < nodeList.size(); ++i) {
                                    if (i != 0) {
                                        this.calculatedSyntaxModelForNode(csmList.getSeparatorPre(), node, elements, change);
                                    }
                                    elements.add(new CsmChild((Node)nodeList.get(i)));
                                    if (i == nodeList.size() - 1) continue;
                                    this.calculatedSyntaxModelForNode(csmList.getSeparatorPost(), node, elements, change);
                                }
                                this.calculatedSyntaxModelForNode(csmList.getFollowing(), node, elements, change);
                            }
                        } else {
                            Collection collection = (Collection)change.getValue(csmList.getProperty(), node);
                            if (!collection.isEmpty()) {
                                this.calculatedSyntaxModelForNode(csmList.getPreceeding(), node, elements, change);
                                boolean first = true;
                                Iterator it = collection.iterator();
                                while (it.hasNext()) {
                                    Object value;
                                    if (!first) {
                                        this.calculatedSyntaxModelForNode(csmList.getSeparatorPre(), node, elements, change);
                                    }
                                    if (!((value = it.next()) instanceof Modifier)) {
                                        throw new UnsupportedOperationException(it.next().getClass().getSimpleName());
                                    }
                                    Modifier modifier = (Modifier)value;
                                    elements.add(new CsmToken(LexicalDifferenceCalculator.toToken(modifier)));
                                    if (it.hasNext()) {
                                        this.calculatedSyntaxModelForNode(csmList.getSeparatorPost(), node, elements, change);
                                    }
                                    first = false;
                                }
                                this.calculatedSyntaxModelForNode(csmList.getFollowing(), node, elements, change);
                            }
                        }
                    } else if (csm instanceof CsmConditional) {
                        CsmConditional csmConditional = (CsmConditional)csm;
                        boolean satisfied = change.evaluate(csmConditional, node);
                        if (satisfied) {
                            this.calculatedSyntaxModelForNode(csmConditional.getThenElement(), node, elements, change);
                        } else {
                            this.calculatedSyntaxModelForNode(csmConditional.getElseElement(), node, elements, change);
                        }
                    } else if (csm instanceof CsmIndent) {
                        elements.add(csm);
                    } else if (csm instanceof CsmUnindent) {
                        elements.add(csm);
                    } else if (csm instanceof CsmAttribute) {
                        CsmAttribute csmAttribute = (CsmAttribute)csm;
                        Object value = change.getValue(csmAttribute.getProperty(), node);
                        String text = value.toString();
                        if (value instanceof Stringable) {
                            text = ((Stringable)value).asString();
                        }
                        elements.add(new CsmToken(csmAttribute.getTokenType(node, value.toString(), text), text));
                    } else if (csm instanceof CsmString && node instanceof StringLiteralExpr) {
                        if (change instanceof PropertyChange) {
                            elements.add(new CsmToken(89, "\"" + ((PropertyChange)change).getNewValue() + "\""));
                        } else {
                            elements.add(new CsmToken(89, "\"" + ((StringLiteralExpr)node).getValue() + "\""));
                        }
                    } else if (csm instanceof CsmString && node instanceof TextBlockLiteralExpr) {
                        if (change instanceof PropertyChange) {
                            elements.add(new CsmToken(91, "\"\"\"" + ((PropertyChange)change).getNewValue() + "\"\"\""));
                        } else {
                            elements.add(new CsmToken(91, "\"\"\"" + ((TextBlockLiteralExpr)node).getValue() + "\"\"\""));
                        }
                    } else if (csm instanceof CsmChar && node instanceof CharLiteralExpr) {
                        if (change instanceof PropertyChange) {
                            elements.add(new CsmToken(18, "'" + ((PropertyChange)change).getNewValue() + "'"));
                        } else {
                            elements.add(new CsmToken(18, "'" + ((CharLiteralExpr)node).getValue() + "'"));
                        }
                    } else if (csm instanceof CsmMix) {
                        CsmMix csmMix = (CsmMix)csm;
                        LinkedList<CsmElement> mixElements = new LinkedList<CsmElement>();
                        csmMix.getElements().forEach(e -> this.calculatedSyntaxModelForNode((CsmElement)e, node, (List<CsmElement>)mixElements, change));
                        elements.add(new CsmMix(mixElements));
                    } else if (csm instanceof CsmChild) {
                        elements.add(csm);
                    } else {
                        throw new UnsupportedOperationException(csm.getClass().getSimpleName() + " " + csm);
                    }
                }
            }
        }
    }

    public static int toToken(Modifier modifier) {
        switch (modifier.getKeyword()) {
            case PUBLIC: {
                return 47;
            }
            case PRIVATE: {
                return 45;
            }
            case PROTECTED: {
                return 46;
            }
            case STATIC: {
                return 50;
            }
            case FINAL: {
                return 29;
            }
            case ABSTRACT: {
                return 11;
            }
            case TRANSIENT: {
                return 58;
            }
            case SYNCHRONIZED: {
                return 54;
            }
            case VOLATILE: {
                return 62;
            }
            case NATIVE: {
                return 41;
            }
            case STRICTFP: {
                return 51;
            }
            case TRANSITIVE: {
                return 74;
            }
        }
        throw new UnsupportedOperationException(modifier.getKeyword().name());
    }

    CalculatedSyntaxModel calculatedSyntaxModelAfterPropertyChange(Node node, ObservableProperty property, Object oldValue, Object newValue) {
        return this.calculatedSyntaxModelAfterPropertyChange(ConcreteSyntaxModel.forClass(node.getClass()), node, property, oldValue, newValue);
    }

    CalculatedSyntaxModel calculatedSyntaxModelAfterPropertyChange(CsmElement csm, Node node, ObservableProperty property, Object oldValue, Object newValue) {
        LinkedList<CsmElement> elements = new LinkedList<CsmElement>();
        this.calculatedSyntaxModelForNode(csm, node, elements, new PropertyChange(property, oldValue, newValue));
        return new CalculatedSyntaxModel(elements);
    }

    CalculatedSyntaxModel calculatedSyntaxModelAfterListRemoval(CsmElement csm, ObservableProperty observableProperty, NodeList<?> nodeList, int index) {
        LinkedList<CsmElement> elements = new LinkedList<CsmElement>();
        Node container = nodeList.getParentNodeForChildren();
        this.calculatedSyntaxModelForNode(csm, container, elements, new ListRemovalChange(observableProperty, index));
        return new CalculatedSyntaxModel(elements);
    }

    CalculatedSyntaxModel calculatedSyntaxModelAfterListAddition(CsmElement csm, ObservableProperty observableProperty, NodeList<?> nodeList, int index, Node nodeAdded) {
        LinkedList<CsmElement> elements = new LinkedList<CsmElement>();
        Node container = nodeList.getParentNodeForChildren();
        this.calculatedSyntaxModelForNode(csm, container, elements, new ListAdditionChange(observableProperty, index, nodeAdded));
        return new CalculatedSyntaxModel(elements);
    }

    CalculatedSyntaxModel calculatedSyntaxModelAfterListAddition(Node container, ObservableProperty observableProperty, int index, Node nodeAdded) {
        CsmElement csm = ConcreteSyntaxModel.forClass(container.getClass());
        Object rawValue = observableProperty.getRawValue(container);
        if (!(rawValue instanceof NodeList)) {
            throw new IllegalStateException("Expected NodeList, found " + rawValue.getClass().getCanonicalName());
        }
        NodeList nodeList = (NodeList)rawValue;
        return this.calculatedSyntaxModelAfterListAddition(csm, observableProperty, nodeList, index, nodeAdded);
    }

    CalculatedSyntaxModel calculatedSyntaxModelAfterListRemoval(Node container, ObservableProperty observableProperty, int index) {
        CsmElement csm = ConcreteSyntaxModel.forClass(container.getClass());
        Object rawValue = observableProperty.getRawValue(container);
        if (!(rawValue instanceof NodeList)) {
            throw new IllegalStateException("Expected NodeList, found " + rawValue.getClass().getCanonicalName());
        }
        NodeList nodeList = (NodeList)rawValue;
        return this.calculatedSyntaxModelAfterListRemoval(csm, observableProperty, nodeList, index);
    }

    private CalculatedSyntaxModel calculatedSyntaxModelAfterListReplacement(CsmElement csm, ObservableProperty observableProperty, NodeList<?> nodeList, int index, Node newValue) {
        LinkedList<CsmElement> elements = new LinkedList<CsmElement>();
        Node container = nodeList.getParentNodeForChildren();
        this.calculatedSyntaxModelForNode(csm, container, elements, new ListReplacementChange(observableProperty, index, newValue));
        return new CalculatedSyntaxModel(elements);
    }

    static class CsmChild
    implements CsmElement {
        private final Node child;

        public Node getChild() {
            return this.child;
        }

        CsmChild(Node child) {
            this.child = child;
        }

        @Override
        public void prettyPrint(Node node, SourcePrinter printer) {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return "child(" + this.child.getClass().getSimpleName() + ")";
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            CsmChild csmChild = (CsmChild)o;
            return this.child.equals(csmChild.child);
        }

        public int hashCode() {
            return this.child.hashCode();
        }
    }

    static class CalculatedSyntaxModel {
        final List<CsmElement> elements;

        CalculatedSyntaxModel(List<CsmElement> elements) {
            this.elements = elements;
        }

        public CalculatedSyntaxModel from(int index) {
            return new CalculatedSyntaxModel(new ArrayList<CsmElement>(this.elements.subList(index, this.elements.size())));
        }

        public String toString() {
            return "CalculatedSyntaxModel{elements=" + this.elements + '}';
        }

        CalculatedSyntaxModel sub(int start, int end) {
            return new CalculatedSyntaxModel(this.elements.subList(start, end));
        }

        void removeIndentationElements() {
            this.elements.removeIf(el -> el instanceof CsmIndent || el instanceof CsmUnindent);
        }
    }
}

