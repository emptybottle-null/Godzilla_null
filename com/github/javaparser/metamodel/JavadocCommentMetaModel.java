/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.metamodel.CommentMetaModel;
import java.util.Optional;

public class JavadocCommentMetaModel
extends CommentMetaModel {
    JavadocCommentMetaModel(Optional<BaseNodeMetaModel> superBaseNodeMetaModel) {
        super(superBaseNodeMetaModel, JavadocComment.class, "JavadocComment", "com.github.javaparser.ast.comments", false, false);
    }
}

