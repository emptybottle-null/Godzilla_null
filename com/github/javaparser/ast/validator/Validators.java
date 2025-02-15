/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validators
implements Validator {
    private final List<Validator> validators = new ArrayList<Validator>();

    public Validators(Validator ... validators) {
        this.validators.addAll(Arrays.asList(validators));
    }

    public List<Validator> getValidators() {
        return this.validators;
    }

    public Validators remove(Validator validator) {
        if (!this.validators.remove(validator)) {
            throw new AssertionError((Object)"Trying to remove a validator that isn't there.");
        }
        return this;
    }

    public Validators replace(Validator oldValidator, Validator newValidator) {
        this.remove(oldValidator);
        this.add(newValidator);
        return this;
    }

    public Validators add(Validator newValidator) {
        this.validators.add(newValidator);
        return this;
    }

    @Override
    public void accept(Node node, ProblemReporter problemReporter) {
        this.validators.forEach(v -> v.accept(node, problemReporter));
    }
}

