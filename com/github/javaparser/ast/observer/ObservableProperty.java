/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.observer;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.utils.Utils;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public enum ObservableProperty {
    ANNOTATIONS(Type.MULTIPLE_REFERENCE),
    ANONYMOUS_CLASS_BODY(Type.MULTIPLE_REFERENCE),
    ARGUMENTS(Type.MULTIPLE_REFERENCE),
    ASTERISK(Type.SINGLE_ATTRIBUTE),
    BODY(Type.SINGLE_REFERENCE),
    CATCH_CLAUSES(Type.MULTIPLE_REFERENCE),
    CHECK(Type.SINGLE_REFERENCE),
    CLASS_BODY(Type.MULTIPLE_REFERENCE),
    CLASS_DECLARATION(Type.SINGLE_REFERENCE),
    COMMENT(Type.SINGLE_REFERENCE),
    COMPARE(Type.SINGLE_REFERENCE),
    COMPONENT_TYPE(Type.SINGLE_REFERENCE),
    CONDITION(Type.SINGLE_REFERENCE),
    CONTENT(Type.SINGLE_ATTRIBUTE),
    DEFAULT_VALUE(Type.SINGLE_REFERENCE),
    DIMENSION(Type.SINGLE_REFERENCE),
    DIRECTIVES(Type.MULTIPLE_REFERENCE),
    ELEMENTS(Type.MULTIPLE_REFERENCE),
    ELEMENT_TYPE(Type.SINGLE_REFERENCE),
    ELSE_EXPR(Type.SINGLE_REFERENCE),
    ELSE_STMT(Type.SINGLE_REFERENCE),
    ENCLOSING_PARAMETERS(Type.SINGLE_ATTRIBUTE),
    ENTRIES(Type.MULTIPLE_REFERENCE),
    EXPRESSION(Type.SINGLE_REFERENCE),
    EXTENDED_TYPE(Type.SINGLE_REFERENCE),
    EXTENDED_TYPES(Type.MULTIPLE_REFERENCE),
    FINALLY_BLOCK(Type.SINGLE_REFERENCE),
    IDENTIFIER(Type.SINGLE_ATTRIBUTE),
    IMPLEMENTED_TYPES(Type.MULTIPLE_REFERENCE),
    IMPORTS(Type.MULTIPLE_REFERENCE),
    INDEX(Type.SINGLE_REFERENCE),
    INITIALIZATION(Type.MULTIPLE_REFERENCE),
    INITIALIZER(Type.SINGLE_REFERENCE),
    INNER(Type.SINGLE_REFERENCE),
    INTERFACE(Type.SINGLE_ATTRIBUTE),
    ITERABLE(Type.SINGLE_REFERENCE),
    KEYWORD(Type.SINGLE_ATTRIBUTE),
    LABEL(Type.SINGLE_REFERENCE),
    LABELS(Type.MULTIPLE_REFERENCE),
    LEFT(Type.SINGLE_REFERENCE),
    LEVELS(Type.MULTIPLE_REFERENCE),
    MEMBERS(Type.MULTIPLE_REFERENCE),
    MEMBER_VALUE(Type.SINGLE_REFERENCE),
    MESSAGE(Type.SINGLE_REFERENCE),
    MODIFIERS(Type.MULTIPLE_REFERENCE),
    MODULE(Type.SINGLE_REFERENCE),
    MODULE_NAMES(Type.MULTIPLE_REFERENCE),
    NAME(Type.SINGLE_REFERENCE),
    OPEN(Type.SINGLE_ATTRIBUTE),
    OPERATOR(Type.SINGLE_ATTRIBUTE),
    ORIGIN(Type.SINGLE_ATTRIBUTE),
    PACKAGE_DECLARATION(Type.SINGLE_REFERENCE),
    PAIRS(Type.MULTIPLE_REFERENCE),
    PARAMETER(Type.SINGLE_REFERENCE),
    PARAMETERS(Type.MULTIPLE_REFERENCE),
    PATTERN(Type.SINGLE_REFERENCE),
    QUALIFIER(Type.SINGLE_REFERENCE),
    RECEIVER_PARAMETER(Type.SINGLE_REFERENCE),
    RESOURCES(Type.MULTIPLE_REFERENCE),
    RIGHT(Type.SINGLE_REFERENCE),
    SCOPE(Type.SINGLE_REFERENCE),
    SELECTOR(Type.SINGLE_REFERENCE),
    STATEMENT(Type.SINGLE_REFERENCE),
    STATEMENTS(Type.MULTIPLE_REFERENCE),
    STATIC(Type.SINGLE_ATTRIBUTE),
    SUPER_TYPE(Type.SINGLE_REFERENCE),
    TARGET(Type.SINGLE_REFERENCE),
    THEN_EXPR(Type.SINGLE_REFERENCE),
    THEN_STMT(Type.SINGLE_REFERENCE),
    THIS(Type.SINGLE_ATTRIBUTE),
    THROWN_EXCEPTIONS(Type.MULTIPLE_REFERENCE),
    TRY_BLOCK(Type.SINGLE_REFERENCE),
    TYPE(Type.SINGLE_REFERENCE),
    TYPES(Type.MULTIPLE_REFERENCE),
    TYPE_ARGUMENTS(Type.MULTIPLE_REFERENCE),
    TYPE_BOUND(Type.MULTIPLE_REFERENCE),
    TYPE_NAME(Type.SINGLE_REFERENCE),
    TYPE_PARAMETERS(Type.MULTIPLE_REFERENCE),
    UPDATE(Type.MULTIPLE_REFERENCE),
    VALUE(Type.SINGLE_REFERENCE),
    VALUES(Type.MULTIPLE_REFERENCE),
    VARIABLE(Type.SINGLE_REFERENCE),
    VARIABLES(Type.MULTIPLE_REFERENCE),
    VAR_ARGS(Type.SINGLE_ATTRIBUTE),
    VAR_ARGS_ANNOTATIONS(Type.MULTIPLE_REFERENCE),
    WITH(Type.MULTIPLE_REFERENCE),
    CASCADING_IF_STMT(Type.SINGLE_ATTRIBUTE, true),
    ELSE_BLOCK(Type.SINGLE_ATTRIBUTE, true),
    ELSE_BRANCH(Type.SINGLE_ATTRIBUTE, true),
    EXPRESSION_BODY(Type.SINGLE_REFERENCE, true),
    MAXIMUM_COMMON_TYPE(Type.SINGLE_REFERENCE, true),
    POSTFIX(Type.SINGLE_ATTRIBUTE, true),
    PREFIX(Type.SINGLE_ATTRIBUTE, true),
    THEN_BLOCK(Type.SINGLE_ATTRIBUTE, true),
    USING_DIAMOND_OPERATOR(Type.SINGLE_ATTRIBUTE, true),
    RANGE,
    COMMENTED_NODE;

    private Type type;
    private boolean derived;

    public static ObservableProperty fromCamelCaseName(String camelCaseName) {
        Optional<ObservableProperty> observableProperty = Arrays.stream(ObservableProperty.values()).filter(v -> v.camelCaseName().equals(camelCaseName)).findFirst();
        if (observableProperty.isPresent()) {
            return observableProperty.get();
        }
        throw new IllegalArgumentException("No property found with the given camel case name: " + camelCaseName);
    }

    private ObservableProperty(Type type) {
        this.type = type;
        this.derived = false;
    }

    private ObservableProperty(Type type, boolean derived) {
        this.type = type;
        this.derived = derived;
    }

    private ObservableProperty() {
        this(Type.SINGLE_REFERENCE, false);
    }

    public boolean isDerived() {
        return this.derived;
    }

    public boolean isAboutNodes() {
        return this.type.node;
    }

    public boolean isAboutValues() {
        return !this.isAboutNodes();
    }

    public boolean isMultiple() {
        return this.type.multiple;
    }

    public boolean isSingle() {
        return !this.isMultiple();
    }

    public String camelCaseName() {
        return Utils.screamingToCamelCase(this.name());
    }

    public Node getValueAsSingleReference(Node node) {
        Object rawValue = this.getRawValue(node);
        try {
            if (rawValue instanceof Node) {
                return (Node)rawValue;
            }
            if (rawValue instanceof Optional) {
                Optional opt = (Optional)rawValue;
                if (opt.isPresent()) {
                    return (Node)opt.get();
                }
                return null;
            }
            throw new RuntimeException(String.format("Property %s returned %s (%s)", this.name(), rawValue.toString(), rawValue.getClass().getCanonicalName()));
        } catch (ClassCastException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean hasMethod(Node node, String name) {
        try {
            node.getClass().getMethod(name, new Class[0]);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public NodeList<? extends Node> getValueAsMultipleReference(Node node) {
        Object rawValue = this.getRawValue(node);
        try {
            if (rawValue == null) {
                return null;
            }
            if (rawValue instanceof NodeList) {
                return (NodeList)rawValue;
            }
            Optional opt = (Optional)rawValue;
            if (opt.isPresent()) {
                return (NodeList)opt.get();
            }
            return null;
        } catch (ClassCastException e) {
            throw new RuntimeException("Unable to get list value for " + this.name() + " from " + node + " (class: " + node.getClass().getSimpleName() + ")", e);
        }
    }

    public Collection<?> getValueAsCollection(Node node) {
        Object rawValue = this.getRawValue(node);
        try {
            return (Collection)rawValue;
        } catch (ClassCastException e) {
            throw new RuntimeException("Unable to get list value for " + this.name() + " from " + node + " (class: " + node.getClass().getSimpleName() + ")", e);
        }
    }

    public String getValueAsStringAttribute(Node node) {
        return (String)this.getRawValue(node);
    }

    public Boolean getValueAsBooleanAttribute(Node node) {
        return (Boolean)this.getRawValue(node);
    }

    public Object getRawValue(Node node) {
        String getterName = "get" + Utils.capitalize(this.camelCaseName());
        if (!this.hasMethod(node, getterName) && !this.hasMethod(node, getterName = "is" + Utils.capitalize(this.camelCaseName()))) {
            getterName = "has" + Utils.capitalize(this.camelCaseName());
        }
        try {
            return node.getClass().getMethod(getterName, new Class[0]).invoke(node, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Unable to get value for " + this.name() + " from " + node + " (" + node.getClass().getSimpleName() + ")", e);
        }
    }

    public boolean isNull(Node node) {
        return null == this.getRawValue(node);
    }

    public boolean isNullOrNotPresent(Node node) {
        Object result = this.getRawValue(node);
        if (result == null) {
            return true;
        }
        if (result instanceof Optional) {
            return !((Optional)result).isPresent();
        }
        return false;
    }

    public boolean isNullOrEmpty(Node node) {
        return Utils.valueIsNullOrEmpty(this.getRawValue(node));
    }

    static enum Type {
        SINGLE_ATTRIBUTE(false, false),
        SINGLE_REFERENCE(false, true),
        MULTIPLE_ATTRIBUTE(true, false),
        MULTIPLE_REFERENCE(true, true);

        private boolean multiple;
        private boolean node;

        private Type(boolean multiple, boolean node) {
            this.multiple = multiple;
            this.node = node;
        }
    }
}

