/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.JavaToken;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;

class RangedList<T extends Node> {
    TokenRange range = new TokenRange(JavaToken.INVALID, JavaToken.INVALID);
    NodeList<T> list;

    RangedList(NodeList<T> list) {
        this.list = list;
    }

    void beginAt(JavaToken begin) {
        this.range = this.range.withBegin(begin);
    }

    void endAt(JavaToken end) {
        this.range = this.range.withEnd(end);
    }

    void add(T t) {
        if (this.list == null) {
            this.list = new NodeList();
        }
        this.list.add(t);
    }
}

