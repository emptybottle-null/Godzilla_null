/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.metamodel;

import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.utils.Utils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseNodeMetaModel {
    private final Optional<BaseNodeMetaModel> superNodeMetaModel;
    private final List<PropertyMetaModel> declaredPropertyMetaModels = new ArrayList<PropertyMetaModel>();
    private final List<PropertyMetaModel> derivedPropertyMetaModels = new ArrayList<PropertyMetaModel>();
    private final List<PropertyMetaModel> constructorParameters = new ArrayList<PropertyMetaModel>();
    private final Class<? extends Node> type;
    private final String name;
    private final String packageName;
    private final boolean isAbstract;
    private final boolean hasWildcard;

    public BaseNodeMetaModel(Optional<BaseNodeMetaModel> superNodeMetaModel, Class<? extends Node> type, String name, String packageName, boolean isAbstract, boolean hasWildcard) {
        this.superNodeMetaModel = superNodeMetaModel;
        this.type = type;
        this.name = name;
        this.packageName = packageName;
        this.isAbstract = isAbstract;
        this.hasWildcard = hasWildcard;
    }

    public boolean is(Class<? extends Node> c) {
        return this.type.equals(c);
    }

    public String getQualifiedClassName() {
        return this.packageName + "." + this.name;
    }

    public Optional<BaseNodeMetaModel> getSuperNodeMetaModel() {
        return this.superNodeMetaModel;
    }

    public List<PropertyMetaModel> getDeclaredPropertyMetaModels() {
        return this.declaredPropertyMetaModels;
    }

    public List<PropertyMetaModel> getDerivedPropertyMetaModels() {
        return this.derivedPropertyMetaModels;
    }

    public List<PropertyMetaModel> getConstructorParameters() {
        return this.constructorParameters;
    }

    public List<PropertyMetaModel> getAllPropertyMetaModels() {
        ArrayList<PropertyMetaModel> allPropertyMetaModels = new ArrayList<PropertyMetaModel>(this.getDeclaredPropertyMetaModels());
        BaseNodeMetaModel walkNode = this;
        while (walkNode.getSuperNodeMetaModel().isPresent()) {
            walkNode = walkNode.getSuperNodeMetaModel().get();
            allPropertyMetaModels.addAll(walkNode.getDeclaredPropertyMetaModels());
        }
        return allPropertyMetaModels;
    }

    public boolean isInstanceOfMetaModel(BaseNodeMetaModel baseMetaModel) {
        if (this == baseMetaModel) {
            return true;
        }
        if (this.isRootNode()) {
            return false;
        }
        return this.getSuperNodeMetaModel().get().isInstanceOfMetaModel(baseMetaModel);
    }

    public Class<? extends Node> getType() {
        return this.type;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public boolean isAbstract() {
        return this.isAbstract;
    }

    public boolean hasWildcard() {
        return this.hasWildcard;
    }

    public boolean isRootNode() {
        return !this.superNodeMetaModel.isPresent();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        BaseNodeMetaModel classMetaModel = (BaseNodeMetaModel)o;
        return this.type.equals(classMetaModel.type);
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        return this.name;
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

    public String getMetaModelFieldName() {
        return Utils.decapitalize(this.getClass().getSimpleName());
    }

    public Node construct(Map<String, Object> parameters) {
        for (Constructor<?> constructor : this.getType().getConstructors()) {
            if (constructor.getAnnotation(AllFieldsConstructor.class) == null) continue;
            try {
                Object[] paramArray = new Object[constructor.getParameterCount()];
                int i = 0;
                for (PropertyMetaModel constructorParameter : this.getConstructorParameters()) {
                    paramArray[i] = parameters.get(constructorParameter.getName());
                    if (paramArray[i] == null && constructorParameter.isRequired() && constructorParameter.isNodeList()) {
                        paramArray[i] = new NodeList();
                    }
                    ++i;
                }
                return (Node)constructor.newInstance(paramArray);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalStateException();
    }
}

