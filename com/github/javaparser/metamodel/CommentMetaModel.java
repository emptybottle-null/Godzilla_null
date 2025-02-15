/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.PropertyMetaModel;
import java.util.Optional;

public class CommentMetaModel
extends NodeMetaModel {
    public PropertyMetaModel contentPropertyMetaModel;

    CommentMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, Comment.class, "Comment", "com.github.javaparser.ast.comments", true, false);
    }

    protected CommentMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract, boolean hasWildcard) {
        super(superNodeMetaModel, type, name, packageName, isAbstract, hasWildcard);
    }
}

