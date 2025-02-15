/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.CommentMetaModel;
import java.util.Optional;

public class LineCommentMetaModel
extends CommentMetaModel {
    LineCommentMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, LineComment.class, "LineComment", "com.github.javaparser.ast.comments", false, false);
    }
}

