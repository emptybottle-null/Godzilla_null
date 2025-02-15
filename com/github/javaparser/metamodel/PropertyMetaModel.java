/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.metamodel.BaseNodeMetaModel;
import com.github.javaparser.utils.CodeGenerationUtils;
import java.lang.reflect.Field;
import java.util.Optional;

public class PropertyMetaModel {
    private final BaseNodeMetaModel containingNodeMetaModel;
    private final String name;
    private final Class<?> type;
    private final Optional<BaseNodeMetaModel> nodeReference;
    private final boolean isOptional;
    private final boolean isNonEmpty;
    private final boolean isNodeList;
    private final boolean hasWildcard;

    public PropertyMetaModel(BaseNodeMetaModel containingNodeMetaModel, String name, Class<?> type, Optional<BaseNodeMetaModel> nodeReference, boolean isOptional, boolean isNonEmpty, boolean isNodeList, boolean hasWildcard) {
        this.containingNodeMetaModel = containingNodeMetaModel;
        this.name = name;
        this.type = type;
        this.nodeReference = nodeReference;
        this.isOptional = isOptional;
        this.isNonEmpty = isNonEmpty;
        this.isNodeList = isNodeList;
        this.hasWildcard = hasWildcard;
    }

    public boolean is(Class<? extends Node> c, String fieldName) {
        return this.containingNodeMetaModel.is(c) && this.name.equals(fieldName);
    }

    public boolean is(String fieldName) {
        return this.name.equals(fieldName);
    }

    public String getSetterMethodName() {
        return CodeGenerationUtils.setterName(this.name);
    }

    public String getGetterMethodName() {
        return CodeGenerationUtils.getterName(this.type, this.name);
    }

    public BaseNodeMetaModel getContainingNodeMetaModel() {
        return this.containingNodeMetaModel;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNonEmpty() {
        return this.isNonEmpty;
    }

    public Class<?> getType() {
        return this.type;
    }

    public Optional<BaseNodeMetaModel> getNodeReference() {
        return this.nodeReference;
    }

    public boolean isOptional() {
        return this.isOptional;
    }

    public boolean isRequired() {
        return !this.isOptional;
    }

    public boolean isNodeList() {
        return this.isNodeList;
    }

    public boolean hasWildcard() {
        return this.hasWildcard;
    }

    public boolean isSingular() {
        return !this.isNodeList;
    }

    public String toString() {
        return "(" + this.getTypeName() + ")\t" + this.containingNodeMetaModel + "#" + this.name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        PropertyMetaModel that = (PropertyMetaModel)o;
        if (!this.name.equals(that.name)) {
            return false;
        }
        return this.type.equals(that.type);
    }

    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.type.hashCode();
        return result;
    }

    public String getTypeNameGenerified() {
        if (this.hasWildcard) {
            return this.getTypeName() + "<?>";
        }
        return this.getTypeName();
    }

    public String getTypeName() {
        return this.type.getSimpleName();
    }

    public String getTypeNameForGetter() {
        if (this.isOptional) {
            return "Optional<" + this.getTypeNameForSetter() + ">";
        }
        return this.getTypeNameForSetter();
    }

    public String getTypeNameForSetter() {
        if (this.isNodeList) {
            return "NodeList<" + this.getTypeNameGenerified() + ">";
        }
        return this.getTypeNameGenerified();
    }

    public boolean isNode() {
        return this.getNodeReference().isPresent();
    }

    public String getMetaModelFieldName() {
        return this.getName() + "PropertyMetaModel";
    }

    public boolean isAttribute() {
        return !this.isNode();
    }

    public Object getValue(Node node) {
        try {
            for (Class<?> c = node.getClass(); c != null; c = c.getSuperclass()) {
                Field[] fields;
                for (Field classField : fields = c.getDeclaredFields()) {
                    if (!classField.getName().equals(this.getName())) continue;
                    classField.setAccessible(true);
                    return classField.get(node);
                }
            }
            throw new NoSuchFieldError(this.getName());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

