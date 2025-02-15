/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.printer.lexicalpreservation.ChildTextElement;
import com.github.javaparser.printer.lexicalpreservation.NodeText;
import com.github.javaparser.printer.lexicalpreservation.TextElement;
import com.github.javaparser.printer.lexicalpreservation.TokenTextElement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class TextElementIteratorsFactory {
    TextElementIteratorsFactory() {
    }

    private static Iterator<TokenTextElement> reverseIterator(final NodeText nodeText, final int index) {
        TextElement textElement = nodeText.getTextElement(index);
        if (textElement instanceof TokenTextElement) {
            return new SingleElementIterator<TokenTextElement>((TokenTextElement)textElement){

                @Override
                public void remove() {
                    nodeText.removeElement(index);
                }
            };
        }
        if (textElement instanceof ChildTextElement) {
            ChildTextElement childTextElement = (ChildTextElement)textElement;
            NodeText textForChild = childTextElement.getNodeTextForWrappedNode();
            return TextElementIteratorsFactory.reverseIterator(textForChild);
        }
        throw new IllegalArgumentException();
    }

    public static Iterator<TokenTextElement> reverseIterator(NodeText nodeText) {
        return TextElementIteratorsFactory.partialReverseIterator(nodeText, nodeText.numberOfElements() - 1);
    }

    public static Iterator<TokenTextElement> partialReverseIterator(NodeText nodeText, int fromIndex) {
        LinkedList<Iterator<Iterator<TokenTextElement>>> elements = new LinkedList<Iterator<Iterator<TokenTextElement>>>();
        for (int i = fromIndex; i >= 0; --i) {
            elements.add(TextElementIteratorsFactory.reverseIterator(nodeText, i));
        }
        return new ComposedIterator<TokenTextElement>(elements);
    }

    static class ComposedIterator<E>
    implements Iterator<E> {
        private final List<Iterator<E>> elements;
        private int currIndex;

        ComposedIterator(List<Iterator<E>> elements) {
            this.elements = elements;
            this.currIndex = 0;
        }

        @Override
        public boolean hasNext() {
            if (this.currIndex >= this.elements.size()) {
                return false;
            }
            if (this.elements.get(this.currIndex).hasNext()) {
                return true;
            }
            ++this.currIndex;
            return this.hasNext();
        }

        @Override
        public E next() {
            if (!this.hasNext()) {
                throw new IllegalArgumentException();
            }
            return this.elements.get(this.currIndex).next();
        }

        @Override
        public void remove() {
            this.elements.get(this.currIndex).remove();
        }
    }

    private static class SingleElementIterator<E>
    implements Iterator<E> {
        private final E element;
        private boolean returned;

        SingleElementIterator(E element) {
            this.element = element;
        }

        @Override
        public boolean hasNext() {
            return !this.returned;
        }

        @Override
        public E next() {
            this.returned = true;
            return this.element;
        }

        @Override
        public void remove() {
        }
    }

    static class EmptyIterator<E>
    implements Iterator<E> {
        EmptyIterator() {
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            throw new IllegalArgumentException();
        }
    }

    static class CascadingIterator<E>
    implements Iterator<E> {
        private final Provider<E> nextProvider;
        private Iterator<E> current;
        private Iterator<E> next;
        private boolean lastReturnedFromCurrent = false;
        private boolean lastReturnedFromNext = false;

        public CascadingIterator(Iterator<E> current, Provider<E> nextProvider) {
            this.nextProvider = nextProvider;
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            if (this.current.hasNext()) {
                return true;
            }
            if (this.next == null) {
                this.next = this.nextProvider.provide();
            }
            return this.next.hasNext();
        }

        @Override
        public E next() {
            if (this.current.hasNext()) {
                this.lastReturnedFromCurrent = true;
                this.lastReturnedFromNext = false;
                return this.current.next();
            }
            if (this.next == null) {
                this.next = this.nextProvider.provide();
            }
            this.lastReturnedFromCurrent = false;
            this.lastReturnedFromNext = true;
            return this.next.next();
        }

        @Override
        public void remove() {
            if (this.lastReturnedFromCurrent) {
                this.current.remove();
                return;
            }
            if (this.lastReturnedFromNext) {
                this.next.remove();
                return;
            }
            throw new IllegalArgumentException();
        }

        static interface Provider<E> {
            public Iterator<E> provide();
        }
    }
}

