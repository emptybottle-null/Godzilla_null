/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.CommentMetaModel;
import java.util.Optional;

public class BlockCommentMetaModel
extends CommentMetaModel {
    BlockCommentMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, BlockComment.class, "BlockComment", "com.github.javaparser.ast.comments", false, false);
    }
}

