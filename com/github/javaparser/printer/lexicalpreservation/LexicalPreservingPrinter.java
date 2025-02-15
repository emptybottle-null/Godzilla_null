/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.JavaToken;
import com.github.javaparser.Range;
import com.github.javaparser.TokenTypes;
import com.github.javaparser.ast.DataKey;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.nodeTypes.NodeWithVariables;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.observer.PropagatingAstObserver;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.visitor.TreeVisitor;
import com.github.javaparser.printer.ConcreteSyntaxModel;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.printer.concretesyntaxmodel.CsmIndent;
import com.github.javaparser.printer.concretesyntaxmodel.CsmMix;
import com.github.javaparser.printer.concretesyntaxmodel.CsmToken;
import com.github.javaparser.printer.concretesyntaxmodel.CsmUnindent;
import com.github.javaparser.printer.lexicalpreservation.ChildTextElement;
import com.github.javaparser.printer.lexicalpreservation.Difference;
import com.github.javaparser.printer.lexicalpreservation.DifferenceElement;
import com.github.javaparser.printer.lexicalpreservation.LexicalDifferenceCalculator;
import com.github.javaparser.printer.lexicalpreservation.NodeText;
import com.github.javaparser.printer.lexicalpreservation.TextElement;
import com.github.javaparser.printer.lexicalpreservation.TextElementIteratorsFactory;
import com.github.javaparser.printer.lexicalpreservation.TokenTextElement;
import com.github.javaparser.utils.LineSeparator;
import com.github.javaparser.utils.Pair;
import com.github.javaparser.utils.Utils;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LexicalPreservingPrinter {
    private static AstObserver observer;
    public static final DataKey<NodeText> NODE_TEXT_DATA;
    private static final LexicalDifferenceCalculator LEXICAL_DIFFERENCE_CALCULATOR;

    public static <N extends Node> N setup(N node) {
        Utils.assertNotNull(node);
        if (observer == null) {
            observer = LexicalPreservingPrinter.createObserver();
        }
        node.getTokenRange().ifPresent(r -> {
            LexicalPreservingPrinter.storeInitialText(node);
            if (!node.isRegistered(observer)) {
                node.registerForSubtree(observer);
            }
        });
        return node;
    }

    private static AstObserver createObserver() {
        return new Observer();
    }

    private static void storeInitialText(Node root) {
        final IdentityHashMap tokensByNode = new IdentityHashMap();
        root.getTokenRange().ifPresent(rootTokenRange -> {
            for (JavaToken token : rootTokenRange) {
                Range tokenRange = token.getRange().orElseThrow(() -> new RuntimeException("Token without range: " + token));
                Node owner = LexicalPreservingPrinter.findNodeForToken(root, tokenRange).orElseThrow(() -> new RuntimeException("Token without node owning it: " + token));
                if (!tokensByNode.containsKey(owner)) {
                    tokensByNode.put(owner, new LinkedList());
                }
                ((List)tokensByNode.get(owner)).add(token);
            }
            new TreeVisitor(){

                @Override
                public void process(Node node) {
                    if (!node.isPhantom()) {
                        LexicalPreservingPrinter.storeInitialTextForOneNode(node, (List)tokensByNode.get(node));
                    }
                }
            }.visitBreadthFirst(root);
        });
    }

    private static Optional<Node> findNodeForToken(Node node, Range tokenRange) {
        if (node.isPhantom()) {
            return Optional.empty();
        }
        if (!node.getRange().isPresent()) {
            return Optional.empty();
        }
        if (!node.getRange().get().contains(tokenRange)) {
            return Optional.empty();
        }
        for (Node child : node.getChildNodes()) {
            Optional<Node> found = LexicalPreservingPrinter.findNodeForToken(child, tokenRange);
            if (!found.isPresent()) continue;
            return found;
        }
        return Optional.of(node);
    }

    private static void storeInitialTextForOneNode(Node node, List<JavaToken> nodeTokens) {
        if (nodeTokens == null) {
            nodeTokens = Collections.emptyList();
        }
        LinkedList<Pair> elements = new LinkedList<Pair>();
        for (Node child : node.getChildNodes()) {
            if (child.isPhantom()) continue;
            if (!child.getRange().isPresent()) {
                throw new RuntimeException("Range not present on node " + child);
            }
            elements.add(new Pair<Range, ChildTextElement>(child.getRange().get(), new ChildTextElement(child)));
        }
        for (JavaToken token : nodeTokens) {
            elements.add(new Pair<Range, TokenTextElement>(token.getRange().get(), new TokenTextElement(token)));
        }
        elements.sort(Comparator.comparing(e -> ((Range)e.a).begin));
        node.setData(NODE_TEXT_DATA, new NodeText(elements.stream().map(p -> (TextElement)p.b).collect(Collectors.toList())));
    }

    private static Iterator<TokenTextElement> tokensPreceeding(Node node) {
        if (!node.getParentNode().isPresent()) {
            return new TextElementIteratorsFactory.EmptyIterator<TokenTextElement>();
        }
        NodeText parentNodeText = LexicalPreservingPrinter.getOrCreateNodeText(node.getParentNode().get());
        int index = parentNodeText.tryToFindChild(node);
        if (index == -1) {
            if (node.getParentNode().get() instanceof VariableDeclarator) {
                return LexicalPreservingPrinter.tokensPreceeding(node.getParentNode().get());
            }
            throw new IllegalArgumentException(String.format("I could not find child '%s' in parent '%s'. parentNodeText: %s", node, node.getParentNode().get(), parentNodeText));
        }
        return new TextElementIteratorsFactory.CascadingIterator<TokenTextElement>(TextElementIteratorsFactory.partialReverseIterator(parentNodeText, index - 1), () -> LexicalPreservingPrinter.tokensPreceeding(node.getParentNode().get()));
    }

    public static String print(Node node) {
        StringWriter writer = new StringWriter();
        try {
            LexicalPreservingPrinter.print(node, writer);
        } catch (IOException e) {
            throw new RuntimeException("Unexpected IOException on a StringWriter", e);
        }
        return writer.toString();
    }

    public static void print(Node node, Writer writer) throws IOException {
        if (!node.containsData(NODE_TEXT_DATA)) {
            LexicalPreservingPrinter.getOrCreateNodeText(node);
        }
        NodeText text = node.getData(NODE_TEXT_DATA);
        writer.append(text.expand());
    }

    private static void prettyPrintingTextNode(Node node, NodeText nodeText) {
        if (node instanceof PrimitiveType) {
            PrimitiveType primitiveType = (PrimitiveType)node;
            switch (primitiveType.getType()) {
                case BOOLEAN: {
                    nodeText.addToken(13, node.toString());
                    break;
                }
                case CHAR: {
                    nodeText.addToken(18, node.toString());
                    break;
                }
                case BYTE: {
                    nodeText.addToken(15, node.toString());
                    break;
                }
                case SHORT: {
                    nodeText.addToken(49, node.toString());
                    break;
                }
                case INT: {
                    nodeText.addToken(38, node.toString());
                    break;
                }
                case LONG: {
                    nodeText.addToken(40, node.toString());
                    break;
                }
                case FLOAT: {
                    nodeText.addToken(31, node.toString());
                    break;
                }
                case DOUBLE: {
                    nodeText.addToken(24, node.toString());
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
            return;
        }
        if (node instanceof JavadocComment) {
            nodeText.addToken(8, "/**" + ((JavadocComment)node).getContent() + "*/");
            return;
        }
        if (node instanceof BlockComment) {
            nodeText.addToken(9, "/*" + ((BlockComment)node).getContent() + "*/");
            return;
        }
        if (node instanceof LineComment) {
            nodeText.addToken(5, "//" + ((LineComment)node).getContent());
            return;
        }
        if (node instanceof Modifier) {
            Modifier modifier = (Modifier)node;
            nodeText.addToken(LexicalDifferenceCalculator.toToken(modifier), modifier.getKeyword().asString());
            return;
        }
        LexicalPreservingPrinter.interpret(node, ConcreteSyntaxModel.forClass(node.getClass()), nodeText);
    }

    private static NodeText interpret(Node node, CsmElement csm, NodeText nodeText) {
        LexicalDifferenceCalculator.CalculatedSyntaxModel calculatedSyntaxModel = new LexicalDifferenceCalculator().calculatedSyntaxModelForNode(csm, node);
        List<TokenTextElement> indentation = LexicalPreservingPrinter.findIndentation(node);
        boolean pendingIndentation = false;
        for (CsmElement element : calculatedSyntaxModel.elements) {
            block18: {
                block19: {
                    if (element instanceof CsmIndent) {
                        int indexCurrentElement = calculatedSyntaxModel.elements.indexOf(element);
                        if (calculatedSyntaxModel.elements.size() > indexCurrentElement && !(calculatedSyntaxModel.elements.get(indexCurrentElement + 1) instanceof CsmUnindent)) {
                            for (int i = 0; i < 4; ++i) {
                                indentation.add(new TokenTextElement(1, " "));
                            }
                        }
                    } else if (element instanceof CsmUnindent) {
                        for (int i = 0; i < 4 && indentation.size() > 0; ++i) {
                            indentation.remove(indentation.size() - 1);
                        }
                    }
                    if (!pendingIndentation) break block18;
                    if (!(element instanceof CsmToken)) break block19;
                    if (((CsmToken)element).isNewLine()) break block18;
                }
                indentation.forEach(nodeText::addElement);
            }
            pendingIndentation = false;
            if (element instanceof LexicalDifferenceCalculator.CsmChild) {
                nodeText.addChild(((LexicalDifferenceCalculator.CsmChild)element).getChild());
                continue;
            }
            if (element instanceof CsmToken) {
                CsmToken csmToken = (CsmToken)element;
                nodeText.addToken(csmToken.getTokenType(), csmToken.getContent(node));
                if (!csmToken.isNewLine()) continue;
                pendingIndentation = true;
                continue;
            }
            if (element instanceof CsmMix) {
                CsmMix csmMix = (CsmMix)element;
                csmMix.getElements().forEach(e -> LexicalPreservingPrinter.interpret(node, e, nodeText));
                continue;
            }
            if (element instanceof CsmIndent || element instanceof CsmUnindent) continue;
            throw new UnsupportedOperationException(element.getClass().getSimpleName());
        }
        if (node instanceof VariableDeclarator) {
            VariableDeclarator variableDeclarator = (VariableDeclarator)node;
            variableDeclarator.getParentNode().ifPresent(parent -> ((NodeWithVariables)((Object)parent)).getMaximumCommonType().ifPresent(mct -> {
                int extraArrayLevels = variableDeclarator.getType().getArrayLevel() - mct.getArrayLevel();
                for (int i = 0; i < extraArrayLevels; ++i) {
                    nodeText.addElement(new TokenTextElement(100));
                    nodeText.addElement(new TokenTextElement(101));
                }
            }));
        }
        return nodeText;
    }

    static NodeText getOrCreateNodeText(Node node) {
        if (!node.containsData(NODE_TEXT_DATA)) {
            NodeText nodeText = new NodeText();
            node.setData(NODE_TEXT_DATA, nodeText);
            LexicalPreservingPrinter.prettyPrintingTextNode(node, nodeText);
        }
        return node.getData(NODE_TEXT_DATA);
    }

    static List<TokenTextElement> findIndentation(Node node) {
        TokenTextElement tte;
        LinkedList<TokenTextElement> followingNewlines = new LinkedList<TokenTextElement>();
        Iterator<TokenTextElement> it = LexicalPreservingPrinter.tokensPreceeding(node);
        while (it.hasNext() && (tte = it.next()).getTokenKind() != 5 && !tte.isNewline()) {
            followingNewlines.add(tte);
        }
        Collections.reverse(followingNewlines);
        for (int i = 0; i < followingNewlines.size(); ++i) {
            if (((TokenTextElement)followingNewlines.get(i)).isSpaceOrTab()) continue;
            return followingNewlines.subList(0, i);
        }
        return followingNewlines;
    }

    private static boolean isReturningOptionalNodeList(Method m) {
        if (!m.getReturnType().getCanonicalName().equals(Optional.class.getCanonicalName())) {
            return false;
        }
        if (!(m.getGenericReturnType() instanceof ParameterizedType)) {
            return false;
        }
        ParameterizedType parameterizedType = (ParameterizedType)m.getGenericReturnType();
        Type optionalArgument = parameterizedType.getActualTypeArguments()[0];
        return optionalArgument.getTypeName().startsWith(NodeList.class.getCanonicalName());
    }

    private static ObservableProperty findNodeListName(NodeList<?> nodeList) {
        Node parent = nodeList.getParentNodeForChildren();
        for (Method m : parent.getClass().getMethods()) {
            Object raw;
            if (m.getParameterCount() == 0 && m.getReturnType().getCanonicalName().equals(NodeList.class.getCanonicalName())) {
                try {
                    raw = m.invoke(parent, new Object[0]);
                    if (!(raw instanceof NodeList)) {
                        throw new IllegalStateException("Expected NodeList, found " + raw.getClass().getCanonicalName());
                    }
                    NodeList result = (NodeList)raw;
                    if (result != nodeList) continue;
                    String name = m.getName();
                    if (name.startsWith("get")) {
                        name = name.substring("get".length());
                    }
                    return ObservableProperty.fromCamelCaseName(Utils.decapitalize(name));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            if (m.getParameterCount() != 0 || !LexicalPreservingPrinter.isReturningOptionalNodeList(m)) continue;
            try {
                raw = (Optional)m.invoke(parent, new Object[0]);
                if (!((Optional)raw).isPresent() || ((Optional)raw).get() != nodeList) continue;
                String name = m.getName();
                if (name.startsWith("get")) {
                    name = name.substring("get".length());
                }
                return ObservableProperty.fromCamelCaseName(Utils.decapitalize(name));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException("Cannot find list name of NodeList of size " + nodeList.size());
    }

    static {
        NODE_TEXT_DATA = new DataKey<NodeText>(){};
        LEXICAL_DIFFERENCE_CALCULATOR = new LexicalDifferenceCalculator();
    }

    private static class Observer
    extends PropagatingAstObserver {
        private Observer() {
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public void concretePropertyChange(Node observedNode, ObservableProperty property, Object oldValue, Object newValue) {
            NodeText nodeText;
            if (oldValue == newValue) {
                return;
            }
            if (property == ObservableProperty.RANGE || property == ObservableProperty.COMMENTED_NODE) {
                return;
            }
            if (property == ObservableProperty.COMMENT) {
                Optional<Node> parentNode = observedNode.getParentNode();
                NodeText nodeText2 = parentNode.map(parent -> LexicalPreservingPrinter.getOrCreateNodeText((Node)parentNode.get())).orElse(LexicalPreservingPrinter.getOrCreateNodeText(observedNode));
                if (oldValue == null) {
                    int index = parentNode.isPresent() ? nodeText2.findChild(observedNode) : 0;
                    this.fixIndentOfMovedNode(nodeText2, index);
                    LineSeparator lineSeparator = observedNode.getLineEndingStyleOrDefault(LineSeparator.SYSTEM);
                    nodeText2.addElement(index, this.makeCommentToken((Comment)newValue));
                    nodeText2.addToken(index + 1, TokenTypes.eolTokenKind(lineSeparator), lineSeparator.asRawString());
                } else if (newValue == null) {
                    if (!(oldValue instanceof Comment)) throw new UnsupportedOperationException();
                    if (((Comment)oldValue).isOrphan()) {
                        nodeText2 = LexicalPreservingPrinter.getOrCreateNodeText(observedNode);
                    }
                    int index = this.getIndexOfComment((Comment)oldValue, nodeText2);
                    nodeText2.removeElement(index);
                    if (nodeText2.getElements().get(index).isNewline()) {
                        nodeText2.removeElement(index);
                    }
                } else {
                    List<TokenTextElement> matchingTokens = this.findTokenTextElementForComment((Comment)oldValue, nodeText2);
                    if (matchingTokens.size() != 1) {
                        throw new IllegalStateException("The matching comment to be replaced could not be found");
                    }
                    Comment newComment = (Comment)newValue;
                    TokenTextElement matchingElement = matchingTokens.get(0);
                    nodeText2.replace(matchingElement.and(matchingElement.matchByRange()), this.makeCommentToken(newComment));
                }
            }
            if ((nodeText = LexicalPreservingPrinter.getOrCreateNodeText(observedNode)) == null) {
                throw new NullPointerException(observedNode.getClass().getSimpleName());
            }
            LEXICAL_DIFFERENCE_CALCULATOR.calculatePropertyChange(nodeText, observedNode, property, oldValue, newValue);
        }

        private TokenTextElement makeCommentToken(Comment newComment) {
            if (newComment.isJavadocComment()) {
                return new TokenTextElement(8, "/**" + newComment.getContent() + "*/");
            }
            if (newComment.isLineComment()) {
                return new TokenTextElement(5, "//" + newComment.getContent());
            }
            if (newComment.isBlockComment()) {
                return new TokenTextElement(9, "/*" + newComment.getContent() + "*/");
            }
            throw new UnsupportedOperationException("Unknown type of comment: " + newComment.getClass().getSimpleName());
        }

        private int getIndexOfComment(Comment oldValue, NodeText nodeText) {
            List<TokenTextElement> matchingTokens = this.findTokenTextElementForComment(oldValue, nodeText);
            if (!matchingTokens.isEmpty()) {
                TextElement matchingElement = matchingTokens.get(0);
                return nodeText.findElement(matchingElement.and(matchingElement.matchByRange()));
            }
            List<ChildTextElement> matchingChilds = this.findChildTextElementForComment(oldValue, nodeText);
            ChildTextElement matchingChild = matchingChilds.get(0);
            return nodeText.findElement(matchingChild.and(matchingChild.matchByRange()));
        }

        private List<ChildTextElement> findChildTextElementForComment(Comment oldValue, NodeText nodeText) {
            List<ChildTextElement> matchingChildElements = nodeText.getElements().stream().filter(e -> e.isChild()).map(c -> (ChildTextElement)c).filter(c -> c.isComment()).filter(c -> ((Comment)c.getChild()).getContent().equals(oldValue.getContent())).collect(Collectors.toList());
            if (matchingChildElements.size() > 1) {
                matchingChildElements = matchingChildElements.stream().filter(t -> this.isEqualRange(t.getChild().getRange(), oldValue.getRange())).collect(Collectors.toList());
            }
            if (matchingChildElements.size() != 1) {
                throw new IllegalStateException("The matching child text element for the comment to be removed could not be found.");
            }
            return matchingChildElements;
        }

        private List<TokenTextElement> findTokenTextElementForComment(Comment oldValue, NodeText nodeText) {
            List<TokenTextElement> matchingTokens = oldValue instanceof JavadocComment ? nodeText.getElements().stream().filter(e -> e.isToken(8)).map(e -> (TokenTextElement)e).filter(t -> t.getText().equals("/**" + oldValue.getContent() + "*/")).collect(Collectors.toList()) : (oldValue instanceof BlockComment ? nodeText.getElements().stream().filter(e -> e.isToken(9)).map(e -> (TokenTextElement)e).filter(t -> t.getText().equals("/*" + oldValue.getContent() + "*/")).collect(Collectors.toList()) : nodeText.getElements().stream().filter(e -> e.isToken(5)).map(e -> (TokenTextElement)e).filter(t -> t.getText().trim().equals(("//" + oldValue.getContent()).trim())).collect(Collectors.toList()));
            if (matchingTokens.size() > 1) {
                matchingTokens = matchingTokens.stream().filter(t -> this.isEqualRange(t.getToken().getRange(), oldValue.getRange())).collect(Collectors.toList());
            }
            return matchingTokens;
        }

        private boolean isEqualRange(Optional<Range> range1, Optional<Range> range2) {
            if (range1.isPresent() && range2.isPresent()) {
                return range1.get().equals(range2.get());
            }
            return false;
        }

        private void fixIndentOfMovedNode(NodeText nodeText, int index) {
            if (index <= 0) {
                return;
            }
            for (int i = index - 1; i >= 0; --i) {
                TextElement spaceCandidate = nodeText.getTextElement(i);
                if (spaceCandidate.isSpaceOrTab()) continue;
                if (!spaceCandidate.isNewline() || i == index - 1) break;
                for (int j = 0; j < index - 1 - i; ++j) {
                    nodeText.addElement(index, new TokenTextElement(JavaToken.Kind.SPACE.getKind()));
                }
                break;
            }
        }

        @Override
        public void concreteListChange(NodeList<?> changedList, AstObserver.ListChangeType type, int index, Node nodeAddedOrRemoved) {
            List<DifferenceElement> differenceElements;
            NodeText nodeText = LexicalPreservingPrinter.getOrCreateNodeText(changedList.getParentNodeForChildren());
            if (type == AstObserver.ListChangeType.REMOVAL) {
                differenceElements = LEXICAL_DIFFERENCE_CALCULATOR.calculateListRemovalDifference(LexicalPreservingPrinter.findNodeListName(changedList), changedList, index);
            } else if (type == AstObserver.ListChangeType.ADDITION) {
                differenceElements = LEXICAL_DIFFERENCE_CALCULATOR.calculateListAdditionDifference(LexicalPreservingPrinter.findNodeListName(changedList), changedList, index, nodeAddedOrRemoved);
            } else {
                throw new UnsupportedOperationException();
            }
            Difference difference = new Difference(differenceElements, nodeText, changedList.getParentNodeForChildren());
            difference.apply();
        }

        @Override
        public void concreteListReplacement(NodeList<?> changedList, int index, Node oldValue, Node newValue) {
            NodeText nodeText = LexicalPreservingPrinter.getOrCreateNodeText(changedList.getParentNodeForChildren());
            List<DifferenceElement> differenceElements = LEXICAL_DIFFERENCE_CALCULATOR.calculateListReplacementDifference(LexicalPreservingPrinter.findNodeListName(changedList), changedList, index, newValue);
            Difference difference = new Difference(differenceElements, nodeText, changedList.getParentNodeForChildren());
            difference.apply();
        }
    }
}

