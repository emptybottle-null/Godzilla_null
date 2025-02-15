/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.GeneratedJavaParserConstants;
import com.github.javaparser.JavaToken;
import com.github.javaparser.TokenTypes;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.printer.concretesyntaxmodel.CsmIndent;
import com.github.javaparser.printer.concretesyntaxmodel.CsmMix;
import com.github.javaparser.printer.concretesyntaxmodel.CsmToken;
import com.github.javaparser.printer.concretesyntaxmodel.CsmUnindent;
import com.github.javaparser.printer.lexicalpreservation.Added;
import com.github.javaparser.printer.lexicalpreservation.ChildTextElement;
import com.github.javaparser.printer.lexicalpreservation.DifferenceElement;
import com.github.javaparser.printer.lexicalpreservation.DifferenceElementCalculator;
import com.github.javaparser.printer.lexicalpreservation.Kept;
import com.github.javaparser.printer.lexicalpreservation.LexicalDifferenceCalculator;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.printer.lexicalpreservation.NodeText;
import com.github.javaparser.printer.lexicalpreservation.Removed;
import com.github.javaparser.printer.lexicalpreservation.RemovedGroup;
import com.github.javaparser.printer.lexicalpreservation.Reshuffled;
import com.github.javaparser.printer.lexicalpreservation.TextElement;
import com.github.javaparser.printer.lexicalpreservation.TokenTextElement;
import com.github.javaparser.printer.lexicalpreservation.WrappingRangeIterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;

public class Difference {
    public static final int STANDARD_INDENTATION_SIZE = 4;
    private final NodeText nodeText;
    private final Node node;
    private final List<DifferenceElement> diffElements;
    private final List<TextElement> originalElements;
    private int originalIndex = 0;
    private int diffIndex = 0;
    private final List<TokenTextElement> indentation;
    private boolean addedIndentation = false;

    Difference(List<DifferenceElement> diffElements, NodeText nodeText, Node node) {
        if (nodeText == null) {
            throw new NullPointerException("nodeText can not be null");
        }
        this.nodeText = nodeText;
        this.node = node;
        this.diffElements = diffElements;
        this.originalElements = nodeText.getElements();
        this.indentation = LexicalPreservingPrinter.findIndentation(node);
    }

    private List<TextElement> processIndentation(List<TokenTextElement> indentation, List<TextElement> prevElements) {
        LinkedList<TextElement> res = new LinkedList<TextElement>(indentation);
        boolean afterNl = false;
        for (TextElement e : prevElements) {
            if (e.isNewline()) {
                res.clear();
                afterNl = true;
                continue;
            }
            if (afterNl && e instanceof TokenTextElement && TokenTypes.isWhitespace(((TokenTextElement)e).getTokenKind())) {
                res.add(e);
                continue;
            }
            afterNl = false;
        }
        return res;
    }

    private List<TextElement> indentationBlock() {
        LinkedList<TextElement> res = new LinkedList<TextElement>();
        res.add(new TokenTextElement(1));
        res.add(new TokenTextElement(1));
        res.add(new TokenTextElement(1));
        res.add(new TokenTextElement(1));
        return res;
    }

    private boolean isAfterLBrace(NodeText nodeText, int nodeTextIndex) {
        if (nodeTextIndex > 0 && nodeText.getElements().get(nodeTextIndex - 1).isToken(98)) {
            return true;
        }
        if (nodeTextIndex > 0 && nodeText.getElements().get(nodeTextIndex - 1).isSpaceOrTab()) {
            return this.isAfterLBrace(nodeText, nodeTextIndex - 1);
        }
        return false;
    }

    private int considerEnforcingIndentation(NodeText nodeText, int nodeTextIndex) {
        boolean hasOnlyWsBefore = true;
        for (int i = nodeTextIndex; i >= 0 && hasOnlyWsBefore && i < nodeText.getElements().size() && !nodeText.getElements().get(i).isNewline(); --i) {
            if (nodeText.getElements().get(i).isSpaceOrTab()) continue;
            hasOnlyWsBefore = false;
        }
        int res = nodeTextIndex;
        if (hasOnlyWsBefore) {
            int i = nodeTextIndex;
            while (i >= 0 && i < nodeText.getElements().size() && !nodeText.getElements().get(i).isNewline()) {
                nodeText.removeElement(i);
                res = i--;
            }
        }
        if (res < 0) {
            throw new IllegalStateException();
        }
        return res;
    }

    void apply() {
        this.extractReshuffledDiffElements(this.diffElements);
        Map<Removed, RemovedGroup> removedGroups = this.combineRemovedElementsToRemovedGroups();
        do {
            boolean isLeftOverDiffElement = this.applyLeftOverDiffElements();
            boolean isLeftOverOriginalElement = this.applyLeftOverOriginalElements();
            if (isLeftOverDiffElement || isLeftOverOriginalElement) continue;
            DifferenceElement diffElement = this.diffElements.get(this.diffIndex);
            if (diffElement instanceof Added) {
                this.applyAddedDiffElement((Added)diffElement);
                continue;
            }
            TextElement originalElement = this.originalElements.get(this.originalIndex);
            boolean originalElementIsChild = originalElement instanceof ChildTextElement;
            boolean originalElementIsToken = originalElement instanceof TokenTextElement;
            if (diffElement instanceof Kept) {
                this.applyKeptDiffElement((Kept)diffElement, originalElement, originalElementIsChild, originalElementIsToken);
                continue;
            }
            if (diffElement instanceof Removed) {
                Removed removed = (Removed)diffElement;
                this.applyRemovedDiffElement(removedGroups.get(removed), removed, originalElement, originalElementIsChild, originalElementIsToken);
                continue;
            }
            throw new UnsupportedOperationException("" + diffElement + " vs " + originalElement);
        } while (this.diffIndex < this.diffElements.size() || this.originalIndex < this.originalElements.size());
    }

    private boolean applyLeftOverOriginalElements() {
        boolean isLeftOverElement = false;
        if (this.diffIndex >= this.diffElements.size() && this.originalIndex < this.originalElements.size()) {
            TextElement originalElement = this.originalElements.get(this.originalIndex);
            if (originalElement.isWhiteSpaceOrComment()) {
                ++this.originalIndex;
            } else {
                throw new UnsupportedOperationException("NodeText: " + this.nodeText + ". Difference: " + this + " " + originalElement);
            }
            isLeftOverElement = true;
        }
        return isLeftOverElement;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean applyLeftOverDiffElements() {
        boolean isLeftOverElement = false;
        if (this.diffIndex >= this.diffElements.size()) return isLeftOverElement;
        if (this.originalIndex < this.originalElements.size()) return isLeftOverElement;
        DifferenceElement diffElement = this.diffElements.get(this.diffIndex);
        if (diffElement instanceof Kept) {
            Kept kept = (Kept)diffElement;
            if (!kept.isWhiteSpaceOrComment() && !kept.isIndent()) {
                if (!kept.isUnindent()) throw new IllegalStateException("Cannot keep element because we reached the end of nodetext: " + this.nodeText + ". Difference: " + this);
            }
            ++this.diffIndex;
            return true;
        } else {
            if (!(diffElement instanceof Added)) throw new UnsupportedOperationException(diffElement.getClass().getSimpleName());
            Added addedElement = (Added)diffElement;
            this.nodeText.addElement(this.originalIndex, addedElement.toTextElement());
            ++this.originalIndex;
            ++this.diffIndex;
        }
        return true;
    }

    private void extractReshuffledDiffElements(List<DifferenceElement> diffElements) {
        for (int index = 0; index < diffElements.size(); ++index) {
            DifferenceElement diffElement = diffElements.get(index);
            if (!(diffElement instanceof Reshuffled)) continue;
            Reshuffled reshuffled = (Reshuffled)diffElement;
            CsmMix elementsFromPreviousOrder = reshuffled.getPreviousOrder();
            CsmMix elementsFromNextOrder = reshuffled.getNextOrder();
            Map<Integer, Integer> correspondanceBetweenNextOrderAndPreviousOrder = this.getCorrespondanceBetweenNextOrderAndPreviousOrder(elementsFromPreviousOrder, elementsFromNextOrder);
            List<Integer> nodeTextIndexOfPreviousElements = this.findIndexOfCorrespondingNodeTextElement(elementsFromPreviousOrder.getElements(), this.nodeText, this.originalIndex, this.node);
            HashMap<Integer, Integer> nodeTextIndexToPreviousCSMIndex = new HashMap<Integer, Integer>();
            for (int i = 0; i < nodeTextIndexOfPreviousElements.size(); ++i) {
                int value = nodeTextIndexOfPreviousElements.get(i);
                if (value == -1) continue;
                nodeTextIndexToPreviousCSMIndex.put(value, i);
            }
            int lastNodeTextIndex = nodeTextIndexOfPreviousElements.stream().max(Integer::compareTo).orElse(-1);
            LinkedList<CsmElement> elementsToBeAddedAtTheEnd = new LinkedList<CsmElement>();
            List<CsmElement> nextOrderElements = elementsFromNextOrder.getElements();
            HashMap elementsToAddBeforeGivenOriginalCSMElement = new HashMap();
            for (int ni = 0; ni < nextOrderElements.size(); ++ni) {
                if (correspondanceBetweenNextOrderAndPreviousOrder.containsKey(ni)) continue;
                int originalCsmIndex = -1;
                for (int nj = ni + 1; nj < nextOrderElements.size() && originalCsmIndex == -1; ++nj) {
                    if (!correspondanceBetweenNextOrderAndPreviousOrder.containsKey(nj)) continue;
                    originalCsmIndex = correspondanceBetweenNextOrderAndPreviousOrder.get(nj);
                    if (!elementsToAddBeforeGivenOriginalCSMElement.containsKey(originalCsmIndex)) {
                        elementsToAddBeforeGivenOriginalCSMElement.put(originalCsmIndex, new LinkedList());
                    }
                    ((List)elementsToAddBeforeGivenOriginalCSMElement.get(originalCsmIndex)).add(nextOrderElements.get(ni));
                }
                if (originalCsmIndex != -1) continue;
                elementsToBeAddedAtTheEnd.add(nextOrderElements.get(ni));
            }
            diffElements.remove(index);
            int diffElIterator = index;
            if (lastNodeTextIndex != -1) {
                for (int ntIndex = this.originalIndex; ntIndex <= lastNodeTextIndex; ++ntIndex) {
                    if (!nodeTextIndexToPreviousCSMIndex.containsKey(ntIndex)) continue;
                    int indexOfOriginalCSMElement = (Integer)nodeTextIndexToPreviousCSMIndex.get(ntIndex);
                    if (elementsToAddBeforeGivenOriginalCSMElement.containsKey(indexOfOriginalCSMElement)) {
                        for (CsmElement elementToAdd : (List)elementsToAddBeforeGivenOriginalCSMElement.get(indexOfOriginalCSMElement)) {
                            diffElements.add(diffElIterator++, new Added(elementToAdd));
                        }
                    }
                    CsmElement originalCSMElement = elementsFromPreviousOrder.getElements().get(indexOfOriginalCSMElement);
                    boolean toBeKept = correspondanceBetweenNextOrderAndPreviousOrder.containsValue(indexOfOriginalCSMElement);
                    if (toBeKept) {
                        diffElements.add(diffElIterator++, new Kept(originalCSMElement));
                        continue;
                    }
                    diffElements.add(diffElIterator++, new Removed(originalCSMElement));
                }
            }
            for (CsmElement elementToAdd : elementsToBeAddedAtTheEnd) {
                diffElements.add(diffElIterator++, new Added(elementToAdd));
            }
        }
    }

    private Map<Removed, RemovedGroup> combineRemovedElementsToRemovedGroups() {
        Map<Integer, List<Removed>> removedElementsMap = this.groupConsecutiveRemovedElements();
        ArrayList<RemovedGroup> removedGroups = new ArrayList<RemovedGroup>();
        for (Map.Entry<Integer, List<Removed>> entry : removedElementsMap.entrySet()) {
            removedGroups.add(RemovedGroup.of(entry.getKey(), entry.getValue()));
        }
        HashMap<Removed, RemovedGroup> map = new HashMap<Removed, RemovedGroup>();
        for (RemovedGroup removedGroup : removedGroups) {
            for (Removed index : removedGroup) {
                map.put(index, removedGroup);
            }
        }
        return map;
    }

    private Map<Integer, List<Removed>> groupConsecutiveRemovedElements() {
        HashMap<Integer, List<Removed>> removedElementsMap = new HashMap<Integer, List<Removed>>();
        Integer firstElement = null;
        for (int i = 0; i < this.diffElements.size(); ++i) {
            DifferenceElement diffElement = this.diffElements.get(i);
            if (diffElement instanceof Removed) {
                if (firstElement == null) {
                    firstElement = i;
                }
                removedElementsMap.computeIfAbsent(firstElement, key -> new ArrayList()).add((Removed)diffElement);
                continue;
            }
            firstElement = null;
        }
        return removedElementsMap;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void applyRemovedDiffElement(RemovedGroup removedGroup, Removed removed, TextElement originalElement, boolean originalElementIsChild, boolean originalElementIsToken) {
        if (removed.isChild() && originalElementIsChild) {
            ChildTextElement originalElementChild = (ChildTextElement)originalElement;
            if (originalElementChild.isComment()) {
                Comment comment = (Comment)originalElementChild.getChild();
                if (!comment.isOrphan() && comment.getCommentedNode().isPresent() && comment.getCommentedNode().get().equals(removed.getChild())) {
                    this.nodeText.removeElement(this.originalIndex);
                } else {
                    ++this.originalIndex;
                }
            } else {
                this.nodeText.removeElement(this.originalIndex);
                if (!(this.diffIndex + 1 < this.diffElements.size() && this.diffElements.get(this.diffIndex + 1) instanceof Added || removedGroup.isACompleteLine())) {
                    this.originalIndex = this.considerEnforcingIndentation(this.nodeText, this.originalIndex);
                }
                if (this.originalElements.size() > this.originalIndex && this.originalIndex > 0 && this.originalElements.get(this.originalIndex).isWhiteSpace() && this.originalElements.get(this.originalIndex - 1).isWhiteSpace() && (this.diffIndex + 1 == this.diffElements.size() || this.diffElements.get(this.diffIndex + 1) instanceof Kept)) {
                    this.originalElements.remove(this.originalIndex--);
                }
                ++this.diffIndex;
            }
        } else if (removed.isToken() && originalElementIsToken && (removed.getTokenType() == ((TokenTextElement)originalElement).getTokenKind() || ((TokenTextElement)originalElement).getToken().getCategory().isEndOfLine() && removed.isNewLine())) {
            this.nodeText.removeElement(this.originalIndex);
            ++this.diffIndex;
        } else if (originalElementIsToken && originalElement.isWhiteSpaceOrComment()) {
            ++this.originalIndex;
        } else if (originalElement.isLiteral()) {
            this.nodeText.removeElement(this.originalIndex);
            ++this.diffIndex;
        } else if (removed.isPrimitiveType()) {
            if (!originalElement.isPrimitive()) throw new UnsupportedOperationException("removed " + removed.getElement() + " vs " + originalElement);
            this.nodeText.removeElement(this.originalIndex);
            ++this.diffIndex;
        } else if (removed.isWhiteSpace() || removed.getElement() instanceof CsmIndent || removed.getElement() instanceof CsmUnindent) {
            ++this.diffIndex;
        } else {
            if (!originalElement.isWhiteSpace()) throw new UnsupportedOperationException("removed " + removed.getElement() + " vs " + originalElement);
            ++this.originalIndex;
        }
        this.cleanTheLineOfLeftOverSpace(removedGroup, removed);
    }

    private void cleanTheLineOfLeftOverSpace(RemovedGroup removedGroup, Removed removed) {
        if (this.originalIndex >= this.originalElements.size()) {
            return;
        }
        if (!removedGroup.isProcessed() && removedGroup.getLastElement() == removed && removedGroup.isACompleteLine()) {
            Integer lastElementIndex = removedGroup.getLastElementIndex();
            Optional<Integer> indentation = removedGroup.getIndentation();
            if (indentation.isPresent() && !this.isReplaced(lastElementIndex)) {
                for (int i = 0; i < indentation.get(); ++i) {
                    if (this.originalElements.get(this.originalIndex).isSpaceOrTab()) {
                        this.nodeText.removeElement(this.originalIndex);
                        continue;
                    }
                    if (this.originalIndex < 1 || !this.originalElements.get(this.originalIndex - 1).isSpaceOrTab()) continue;
                    this.nodeText.removeElement(this.originalIndex - 1);
                    --this.originalIndex;
                }
            }
            removedGroup.processed();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void applyKeptDiffElement(Kept kept, TextElement originalElement, boolean originalElementIsChild, boolean originalElementIsToken) {
        if (originalElement.isComment()) {
            ++this.originalIndex;
            return;
        } else if (kept.isChild() && ((LexicalDifferenceCalculator.CsmChild)kept.getElement()).getChild() instanceof Comment) {
            ++this.diffIndex;
            return;
        } else if (kept.isChild() && originalElementIsChild) {
            ++this.diffIndex;
            ++this.originalIndex;
            return;
        } else if (kept.isChild() && originalElementIsToken) {
            if (originalElement.isWhiteSpaceOrComment()) {
                ++this.originalIndex;
                return;
            } else if (originalElement.isIdentifier() && this.isNodeWithTypeArguments(kept)) {
                ++this.diffIndex;
                int step = this.getIndexToNextTokenElement((TokenTextElement)originalElement, 0);
                this.originalIndex += step;
                ++this.originalIndex;
                return;
            } else if (originalElement.isIdentifier()) {
                ++this.originalIndex;
                ++this.diffIndex;
                return;
            } else {
                if (!kept.isPrimitiveType()) throw new UnsupportedOperationException("kept " + kept.getElement() + " vs " + originalElement);
                ++this.originalIndex;
                ++this.diffIndex;
            }
            return;
        } else if (kept.isToken() && originalElementIsToken) {
            TokenTextElement originalTextToken = (TokenTextElement)originalElement;
            if (kept.getTokenType() == originalTextToken.getTokenKind()) {
                ++this.originalIndex;
                ++this.diffIndex;
                return;
            } else if (kept.isNewLine() && originalTextToken.isNewline()) {
                ++this.originalIndex;
                ++this.diffIndex;
                return;
            } else if (kept.isNewLine() && originalTextToken.isSpaceOrTab()) {
                ++this.originalIndex;
                ++this.diffIndex;
                return;
            } else if (kept.isWhiteSpaceOrComment()) {
                ++this.diffIndex;
                return;
            } else if (originalTextToken.isWhiteSpaceOrComment()) {
                ++this.originalIndex;
                return;
            } else {
                if (kept.isNewLine() || !originalTextToken.isSeparator()) throw new UnsupportedOperationException("Csm token " + kept.getElement() + " NodeText TOKEN " + originalTextToken);
                ++this.originalIndex;
            }
            return;
        } else if (kept.isToken() && originalElementIsChild) {
            ++this.diffIndex;
            return;
        } else if (kept.isWhiteSpace()) {
            ++this.diffIndex;
            return;
        } else if (kept.isIndent()) {
            ++this.diffIndex;
            return;
        } else {
            if (!kept.isUnindent()) throw new UnsupportedOperationException("kept " + kept.getElement() + " vs " + originalElement);
            ++this.diffIndex;
            if (this.openBraceWasOnSameLine()) return;
            for (int i = 0; i < 4 && this.originalIndex >= 1 && this.nodeText.getTextElement(this.originalIndex - 1).isSpaceOrTab(); ++i) {
                this.nodeText.removeElement(--this.originalIndex);
            }
        }
    }

    private boolean isNodeWithTypeArguments(DifferenceElement element) {
        CsmElement csmElem = element.getElement();
        if (!LexicalDifferenceCalculator.CsmChild.class.isAssignableFrom(csmElem.getClass())) {
            return false;
        }
        LexicalDifferenceCalculator.CsmChild child = (LexicalDifferenceCalculator.CsmChild)csmElem;
        if (!NodeWithTypeArguments.class.isAssignableFrom(child.getChild().getClass())) {
            return false;
        }
        Optional<NodeList<Type>> typeArgs = ((NodeWithTypeArguments)((Object)child.getChild())).getTypeArguments();
        return typeArgs.isPresent() && typeArgs.get().size() > 0;
    }

    private int getIndexToNextTokenElement(TokenTextElement element, int nestedDiamondOperator) {
        int step = 0;
        Optional<JavaToken> next = element.getToken().getNextToken();
        if (!next.isPresent()) {
            return step;
        }
        ++step;
        JavaToken token = next.get();
        JavaToken.Kind kind = JavaToken.Kind.valueOf(token.getKind());
        if (this.isDiamondOperator(kind)) {
            nestedDiamondOperator = JavaToken.Kind.GT.equals((Object)kind) ? --nestedDiamondOperator : ++nestedDiamondOperator;
        }
        if (nestedDiamondOperator == 0 && !next.get().getCategory().isWhitespace()) {
            return step;
        }
        return step += this.getIndexToNextTokenElement(new TokenTextElement(token), nestedDiamondOperator);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isDiamondOperator(JavaToken.Kind kind) {
        if (JavaToken.Kind.GT.equals((Object)kind)) return true;
        if (!JavaToken.Kind.LT.equals((Object)kind)) return false;
        return true;
    }

    private boolean openBraceWasOnSameLine() {
        for (int index = this.originalIndex; index >= 0 && !this.nodeText.getTextElement(index).isNewline(); --index) {
            if (!this.nodeText.getTextElement(index).isToken(98)) continue;
            return true;
        }
        return false;
    }

    private boolean wasSpaceBetweenBraces() {
        return this.nodeText.getTextElement(this.originalIndex).isToken(99) && this.doWeHaveLeftBraceFollowedBySpace(this.originalIndex - 1) && (this.diffIndex < 2 || !this.diffElements.get(this.diffIndex - 2).isRemoved());
    }

    private boolean doWeHaveLeftBraceFollowedBySpace(int index) {
        index = this.rewindSpace(index);
        return this.nodeText.getElements().get(index).isToken(98);
    }

    private int rewindSpace(int index) {
        if (index <= 0) {
            return index;
        }
        if (this.nodeText.getElements().get(index).isWhiteSpace()) {
            return this.rewindSpace(index - 1);
        }
        return index;
    }

    private boolean nextIsRightBrace(int index) {
        List<TextElement> elements = this.originalElements.subList(index, this.originalElements.size());
        for (TextElement element : elements) {
            if (element.isSpaceOrTab()) continue;
            return element.isToken(99);
        }
        return false;
    }

    private void applyAddedDiffElement(Added added) {
        boolean nextIsRightBrace;
        boolean isPreviousElementNewline;
        if (added.isIndent()) {
            for (int i = 0; i < 4; ++i) {
                this.indentation.add(new TokenTextElement(1));
            }
            this.addedIndentation = true;
            ++this.diffIndex;
            return;
        }
        if (added.isUnindent()) {
            for (int i = 0; i < 4 && !this.indentation.isEmpty(); ++i) {
                this.indentation.remove(this.indentation.size() - 1);
            }
            this.addedIndentation = false;
            ++this.diffIndex;
            return;
        }
        TextElement addedTextElement = added.toTextElement();
        boolean used = false;
        boolean bl = isPreviousElementNewline = this.originalIndex > 0 && this.originalElements.get(this.originalIndex - 1).isNewline();
        if (isPreviousElementNewline) {
            Iterator<TextElement> elements = this.processIndentation(this.indentation, this.originalElements.subList(0, this.originalIndex - 1));
            nextIsRightBrace = this.nextIsRightBrace(this.originalIndex);
            Iterator<TextElement> iterator = elements.iterator();
            while (iterator.hasNext()) {
                TextElement e = iterator.next();
                if (!nextIsRightBrace && e instanceof TokenTextElement && this.originalElements.get(this.originalIndex).isToken(((TokenTextElement)e).getTokenKind())) {
                    ++this.originalIndex;
                    continue;
                }
                this.nodeText.addElement(this.originalIndex++, e);
            }
        } else if (this.isAfterLBrace(this.nodeText, this.originalIndex) && !this.isAReplacement(this.diffIndex)) {
            if (addedTextElement.isNewline()) {
                used = true;
            }
            this.nodeText.addElement(this.originalIndex++, new TokenTextElement(TokenTypes.eolTokenKind()));
            while (this.originalIndex >= 2 && this.originalElements.get(this.originalIndex - 2).isSpaceOrTab()) {
                this.originalElements.remove(this.originalIndex - 2);
                --this.originalIndex;
            }
            for (TextElement e : this.processIndentation(this.indentation, this.originalElements.subList(0, this.originalIndex - 1))) {
                this.nodeText.addElement(this.originalIndex++, e);
            }
            if (!this.addedIndentation) {
                for (TextElement e : this.indentationBlock()) {
                    this.nodeText.addElement(this.originalIndex++, e);
                }
            }
        }
        if (!used) {
            boolean previousIsWhiteSpace;
            boolean sufficientTokensRemainToSkip = this.nodeText.numberOfElements() > this.originalIndex + 2;
            boolean currentIsAComment = this.nodeText.getTextElement(this.originalIndex).isComment();
            boolean previousIsAComment = this.originalIndex > 0 && this.nodeText.getTextElement(this.originalIndex - 1).isComment();
            boolean currentIsNewline = this.nodeText.getTextElement(this.originalIndex).isNewline();
            boolean isFirstElement = this.originalIndex == 0;
            boolean bl2 = previousIsWhiteSpace = this.originalIndex > 0 && this.nodeText.getTextElement(this.originalIndex - 1).isWhiteSpace();
            if (sufficientTokensRemainToSkip && currentIsAComment) {
                this.originalIndex += 2;
                this.nodeText.addElement(this.originalIndex, addedTextElement);
                this.originalIndex = this.adjustIndentation(this.indentation, this.nodeText, this.originalIndex, false);
                ++this.originalIndex;
            } else if (currentIsNewline && previousIsAComment) {
                ++this.originalIndex;
                this.originalIndex = this.adjustIndentation(this.indentation, this.nodeText, this.originalIndex, false);
                this.nodeText.addElement(this.originalIndex, addedTextElement);
                ++this.originalIndex;
            } else if (currentIsNewline && addedTextElement.isChild()) {
                if (!(isPreviousElementNewline || isFirstElement || previousIsWhiteSpace)) {
                    ++this.originalIndex;
                }
                this.nodeText.addElement(this.originalIndex, addedTextElement);
                ++this.originalIndex;
            } else {
                this.nodeText.addElement(this.originalIndex, addedTextElement);
                ++this.originalIndex;
            }
        }
        if (addedTextElement.isNewline()) {
            boolean followedByUnindent = this.isFollowedByUnindent(this.diffElements, this.diffIndex);
            nextIsRightBrace = this.nextIsRightBrace(this.originalIndex);
            boolean nextIsNewLine = this.nodeText.getTextElement(this.originalIndex).isNewline();
            if (!nextIsNewLine && !nextIsRightBrace || followedByUnindent) {
                this.originalIndex = this.adjustIndentation(this.indentation, this.nodeText, this.originalIndex, followedByUnindent);
            }
        }
        ++this.diffIndex;
    }

    private String tokenDescription(int kind) {
        return GeneratedJavaParserConstants.tokenImage[kind];
    }

    private Map<Integer, Integer> getCorrespondanceBetweenNextOrderAndPreviousOrder(CsmMix elementsFromPreviousOrder, CsmMix elementsFromNextOrder) {
        HashMap<Integer, Integer> correspondanceBetweenNextOrderAndPreviousOrder = new HashMap<Integer, Integer>();
        List<CsmElement> nextOrderElements = elementsFromNextOrder.getElements();
        List<CsmElement> previousOrderElements = elementsFromPreviousOrder.getElements();
        WrappingRangeIterator piNext = new WrappingRangeIterator(previousOrderElements.size());
        for (int ni = 0; ni < nextOrderElements.size(); ++ni) {
            boolean found = false;
            CsmElement ne = nextOrderElements.get(ni);
            for (int counter = 0; counter < previousOrderElements.size() && !found; ++counter) {
                Integer pi = piNext.next();
                CsmElement pe = previousOrderElements.get(pi);
                if (correspondanceBetweenNextOrderAndPreviousOrder.values().contains(pi) || !DifferenceElementCalculator.matching(ne, pe)) continue;
                found = true;
                correspondanceBetweenNextOrderAndPreviousOrder.put(ni, pi);
            }
        }
        return correspondanceBetweenNextOrderAndPreviousOrder;
    }

    private boolean isFollowedByUnindent(List<DifferenceElement> diffElements, int diffIndex) {
        return diffIndex + 1 < diffElements.size() && diffElements.get(diffIndex + 1).isAdded() && diffElements.get(diffIndex + 1).getElement() instanceof CsmUnindent;
    }

    private List<Integer> findIndexOfCorrespondingNodeTextElement(List<CsmElement> elements, NodeText nodeText, int startIndex, Node node) {
        ArrayList<Integer> correspondingIndices = new ArrayList<Integer>();
        ListIterator<CsmElement> csmElementListIterator = elements.listIterator();
        while (csmElementListIterator.hasNext()) {
            int previousCsmElementIndex = csmElementListIterator.previousIndex();
            CsmElement csmElement = csmElementListIterator.next();
            int nextCsmElementIndex = csmElementListIterator.nextIndex();
            EnumMap<MatchClassification, Integer> potentialMatches = new EnumMap<MatchClassification, Integer>(MatchClassification.class);
            for (int i = startIndex; i < nodeText.getElements().size(); ++i) {
                if (correspondingIndices.contains(i)) continue;
                TextElement textElement = nodeText.getTextElement(i);
                boolean isCorresponding = this.isCorrespondingElement(textElement, csmElement, node);
                if (isCorresponding) {
                    boolean hasSamePreviousElement = false;
                    if (i > 0 && previousCsmElementIndex > -1) {
                        TextElement previousTextElement = nodeText.getTextElement(i - 1);
                        hasSamePreviousElement = this.isCorrespondingElement(previousTextElement, elements.get(previousCsmElementIndex), node);
                    }
                    boolean hasSameNextElement = false;
                    if (i < nodeText.getElements().size() - 1 && nextCsmElementIndex < elements.size()) {
                        TextElement nextTextElement = nodeText.getTextElement(i + 1);
                        hasSameNextElement = this.isCorrespondingElement(nextTextElement, elements.get(nextCsmElementIndex), node);
                    }
                    if (hasSamePreviousElement && hasSameNextElement) {
                        potentialMatches.putIfAbsent(MatchClassification.ALL, i);
                        continue;
                    }
                    if (hasSamePreviousElement) {
                        potentialMatches.putIfAbsent(MatchClassification.PREVIOUS_AND_SAME, i);
                        continue;
                    }
                    if (hasSameNextElement) {
                        potentialMatches.putIfAbsent(MatchClassification.NEXT_AND_SAME, i);
                        continue;
                    }
                    potentialMatches.putIfAbsent(MatchClassification.SAME_ONLY, i);
                    continue;
                }
                if (!this.isAlmostCorrespondingElement(textElement, csmElement, node)) continue;
                potentialMatches.putIfAbsent(MatchClassification.ALMOST, i);
            }
            Optional<MatchClassification> bestMatchKey = potentialMatches.keySet().stream().min(Comparator.comparing(MatchClassification::getPriority));
            if (bestMatchKey.isPresent()) {
                correspondingIndices.add((Integer)potentialMatches.get((Object)bestMatchKey.get()));
                continue;
            }
            correspondingIndices.add(-1);
        }
        return correspondingIndices;
    }

    private boolean isCorrespondingElement(TextElement textElement, CsmElement csmElement, Node node) {
        if (csmElement instanceof CsmToken) {
            CsmToken csmToken = (CsmToken)csmElement;
            if (textElement instanceof TokenTextElement) {
                TokenTextElement tokenTextElement = (TokenTextElement)textElement;
                return tokenTextElement.getTokenKind() == csmToken.getTokenType() && tokenTextElement.getText().equals(csmToken.getContent(node));
            }
        } else if (csmElement instanceof LexicalDifferenceCalculator.CsmChild) {
            LexicalDifferenceCalculator.CsmChild csmChild = (LexicalDifferenceCalculator.CsmChild)csmElement;
            if (textElement instanceof ChildTextElement) {
                ChildTextElement childTextElement = (ChildTextElement)textElement;
                return childTextElement.getChild() == csmChild.getChild();
            }
        } else {
            throw new UnsupportedOperationException();
        }
        return false;
    }

    private boolean isAlmostCorrespondingElement(TextElement textElement, CsmElement csmElement, Node node) {
        if (this.isCorrespondingElement(textElement, csmElement, node)) {
            return false;
        }
        return textElement.isWhiteSpace() && csmElement instanceof CsmToken && ((CsmToken)csmElement).isWhiteSpace();
    }

    private int adjustIndentation(List<TokenTextElement> indentation, NodeText nodeText, int nodeTextIndex, boolean followedByUnindent) {
        List<TextElement> indentationAdj = this.processIndentation(indentation, nodeText.getElements().subList(0, nodeTextIndex - 1));
        if (nodeTextIndex < nodeText.getElements().size() && nodeText.getElements().get(nodeTextIndex).isToken(99)) {
            indentationAdj = indentationAdj.subList(0, indentationAdj.size() - Math.min(4, indentationAdj.size()));
        } else if (followedByUnindent) {
            indentationAdj = indentationAdj.subList(0, Math.max(0, indentationAdj.size() - 4));
        }
        for (TextElement e : indentationAdj) {
            if (nodeTextIndex < nodeText.getElements().size() && nodeText.getElements().get(nodeTextIndex).isSpaceOrTab()) {
                ++nodeTextIndex;
                continue;
            }
            nodeText.getElements().add(nodeTextIndex++, e);
        }
        if (nodeTextIndex < 0) {
            throw new IllegalStateException();
        }
        return nodeTextIndex;
    }

    private boolean isAReplacement(int diffIndex) {
        return diffIndex > 0 && this.diffElements.get(diffIndex) instanceof Added && this.diffElements.get(diffIndex - 1) instanceof Removed;
    }

    private boolean isReplaced(int diffIndex) {
        return diffIndex < this.diffElements.size() - 1 && this.diffElements.get(diffIndex + 1) instanceof Added && this.diffElements.get(diffIndex) instanceof Removed;
    }

    public String toString() {
        return "Difference{" + this.diffElements + '}';
    }

    private static enum MatchClassification {
        ALL(1),
        PREVIOUS_AND_SAME(2),
        NEXT_AND_SAME(3),
        SAME_ONLY(4),
        ALMOST(5);

        private final int priority;

        private MatchClassification(int priority) {
            this.priority = priority;
        }

        int getPriority() {
            return this.priority;
        }
    }
}

