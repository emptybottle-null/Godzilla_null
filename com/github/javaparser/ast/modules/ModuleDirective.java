/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.modules;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.modules.ModuleExportsDirective;
import com.github.javaparser.ast.modules.ModuleOpensDirective;
import com.github.javaparser.ast.modules.ModuleProvidesDirective;
import com.github.javaparser.ast.modules.ModuleRequiresDirective;
import com.github.javaparser.ast.modules.ModuleUsesDirective;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.ModuleDirectiveMetaModel;
import com.github.javaparser.utils.CodeGenerationUtils;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class ModuleDirective
extends Node {
    @AllFieldsConstructor
    public ModuleDirective() {
        this(null);
    }

    public ModuleDirective(TokenRange tokenRange) {
        super(tokenRange);
        this.customInitialization();
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public ModuleDirective clone() {
        return (ModuleDirective)this.accept(new CloneVisitor(), null);
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    public boolean isModuleExportsStmt() {
        return false;
    }

    public ModuleExportsDirective asModuleExportsStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not an ModuleExportsDirective", this));
    }

    public boolean isModuleOpensStmt() {
        return false;
    }

    public ModuleOpensDirective asModuleOpensStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not an ModuleOpensDirective", this));
    }

    public boolean isModuleProvidesStmt() {
        return false;
    }

    public ModuleProvidesDirective asModuleProvidesStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not an ModuleProvidesDirective", this));
    }

    public boolean isModuleRequiresStmt() {
        return false;
    }

    public ModuleRequiresDirective asModuleRequiresStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not an ModuleRequiresDirective", this));
    }

    public boolean isModuleUsesStmt() {
        return false;
    }

    public ModuleUsesDirective asModuleUsesStmt() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not an ModuleUsesDirective", this));
    }

    public void ifModuleExportsStmt(Consumer<ModuleExportsDirective> action) {
    }

    public void ifModuleOpensStmt(Consumer<ModuleOpensDirective> action) {
    }

    public void ifModuleProvidesStmt(Consumer<ModuleProvidesDirective> action) {
    }

    public void ifModuleRequiresStmt(Consumer<ModuleRequiresDirective> action) {
    }

    public void ifModuleUsesStmt(Consumer<ModuleUsesDirective> action) {
    }

    public Optional<ModuleExportsDirective> toModuleExportsStmt() {
        return Optional.empty();
    }

    public Optional<ModuleOpensDirective> toModuleOpensStmt() {
        return Optional.empty();
    }

    public Optional<ModuleProvidesDirective> toModuleProvidesStmt() {
        return Optional.empty();
    }

    public Optional<ModuleRequiresDirective> toModuleRequiresStmt() {
        return Optional.empty();
    }

    public Optional<ModuleUsesDirective> toModuleUsesStmt() {
        return Optional.empty();
    }

    public boolean isModuleExportsDirective() {
        return false;
    }

    public ModuleExportsDirective asModuleExportsDirective() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ModuleExportsDirective, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<ModuleExportsDirective> toModuleExportsDirective() {
        return Optional.empty();
    }

    public void ifModuleExportsDirective(Consumer<ModuleExportsDirective> action) {
    }

    public boolean isModuleOpensDirective() {
        return false;
    }

    public ModuleOpensDirective asModuleOpensDirective() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ModuleOpensDirective, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<ModuleOpensDirective> toModuleOpensDirective() {
        return Optional.empty();
    }

    public void ifModuleOpensDirective(Consumer<ModuleOpensDirective> action) {
    }

    public boolean isModuleProvidesDirective() {
        return false;
    }

    public ModuleProvidesDirective asModuleProvidesDirective() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ModuleProvidesDirective, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<ModuleProvidesDirective> toModuleProvidesDirective() {
        return Optional.empty();
    }

    public void ifModuleProvidesDirective(Consumer<ModuleProvidesDirective> action) {
    }

    public boolean isModuleRequiresDirective() {
        return false;
    }

    public ModuleRequiresDirective asModuleRequiresDirective() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ModuleRequiresDirective, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<ModuleRequiresDirective> toModuleRequiresDirective() {
        return Optional.empty();
    }

    public void ifModuleRequiresDirective(Consumer<ModuleRequiresDirective> action) {
    }

    public boolean isModuleUsesDirective() {
        return false;
    }

    public ModuleUsesDirective asModuleUsesDirective() {
        throw new IllegalStateException(CodeGenerationUtils.f("%s is not ModuleUsesDirective, it is %s", this, this.getClass().getSimpleName()));
    }

    public Optional<ModuleUsesDirective> toModuleUsesDirective() {
        return Optional.empty();
    }

    public void ifModuleUsesDirective(Consumer<ModuleUsesDirective> action) {
    }

    @Override
    public ModuleDirectiveMetaModel getMetaModel() {
        return JavaParserMetaModel.moduleDirectiveMetaModel;
    }
}

