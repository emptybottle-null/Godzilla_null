/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.postprocessors;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.validator.postprocessors.PostProcessors;

public class Java10PostProcessor
extends PostProcessors {
    protected final ParseResult.PostProcessor varNodeCreator = (result, configuration) -> result.getResult().ifPresent(node -> node.findAll(ClassOrInterfaceType.class).forEach(n -> {
        if (n.getNameAsString().equals("var")) {
            n.replace(new VarType(n.getTokenRange().orElse(null)));
        }
    }));

    public Java10PostProcessor() {
        super(new ParseResult.PostProcessor[0]);
        this.add(this.varNodeCreator);
    }
}

