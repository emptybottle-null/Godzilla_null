/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.AstObserverAdapter;
import com.github.javaparser.ast.type.UnknownType;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

@Deprecated
public class PhantomNodeLogic {
    private static final int LEVELS_TO_EXPLORE = 3;
    private static final Map<Node, Boolean> isPhantomNodeCache = Collections.synchronizedMap(new IdentityHashMap());
    private static final AstObserver cacheCleaner = new AstObserverAdapter(){

        @Override
        public void parentChange(Node observedNode, Node previousParent, Node newParent) {
            isPhantomNodeCache.remove(observedNode);
        }
    };

    static boolean isPhantomNode(Node node) {
        if (isPhantomNodeCache.containsKey(node)) {
            return isPhantomNodeCache.get(node);
        }
        if (node instanceof UnknownType) {
            return true;
        }
        boolean res = node.getParentNode().isPresent() && node.getParentNode().get().hasRange() && node.hasRange() && !node.getParentNode().get().getRange().get().contains(node.getRange().get()) || PhantomNodeLogic.inPhantomNode(node, 3);
        isPhantomNodeCache.put(node, res);
        node.register(cacheCleaner);
        return res;
    }

    private static boolean inPhantomNode(Node node, int levels) {
        return node.getParentNode().isPresent() && (PhantomNodeLogic.isPhantomNode(node.getParentNode().get()) || PhantomNodeLogic.inPhantomNode(node.getParentNode().get(), levels - 1));
    }

    public static void cleanUpCache() {
        isPhantomNodeCache.clear();
    }
}

