/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.printer.ConcreteSyntaxModel;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.printer.concretesyntaxmodel.CsmNone;
import com.github.javaparser.printer.concretesyntaxmodel.PrintingHelper;
import java.util.Collection;
import java.util.Iterator;

public class CsmList
implements CsmElement {
    private final ObservableProperty property;
    private final CsmElement separatorPost;
    private final CsmElement separatorPre;
    private final CsmElement preceeding;
    private final CsmElement following;

    public ObservableProperty getProperty() {
        return this.property;
    }

    public CsmElement getSeparatorPost() {
        return this.separatorPost;
    }

    public CsmElement getSeparatorPre() {
        return this.separatorPre;
    }

    public CsmElement getPreceeding() {
        return this.preceeding;
    }

    public CsmElement getFollowing() {
        return this.following;
    }

    public CsmList(ObservableProperty property, CsmElement separator) {
        this(property, new CsmNone(), separator, new CsmNone(), new CsmNone());
    }

    public CsmList(ObservableProperty property) {
        this(property, new CsmNone(), new CsmNone(), new CsmNone(), new CsmNone());
    }

    public CsmList(ObservableProperty property, CsmElement separatorPre, CsmElement separatorPost, CsmElement preceeding, CsmElement following) {
        this.property = property;
        this.separatorPre = separatorPre;
        this.separatorPost = separatorPost;
        this.preceeding = preceeding;
        this.following = following;
    }

    @Override
    public void prettyPrint(Node node, SourcePrinter printer) {
        if (this.property.isAboutNodes()) {
            NodeList<? extends Node> nodeList = this.property.getValueAsMultipleReference(node);
            if (nodeList == null) {
                return;
            }
            if (!nodeList.isEmpty() && this.preceeding != null) {
                this.preceeding.prettyPrint(node, printer);
            }
            for (int i = 0; i < nodeList.size(); ++i) {
                if (this.separatorPre != null && i != 0) {
                    this.separatorPre.prettyPrint(node, printer);
                }
                ConcreteSyntaxModel.genericPrettyPrint((Node)nodeList.get(i), printer);
                if (this.separatorPost == null || i == nodeList.size() - 1) continue;
                this.separatorPost.prettyPrint(node, printer);
            }
            if (!nodeList.isEmpty() && this.following != null) {
                this.following.prettyPrint(node, printer);
            }
        } else {
            Collection<?> values = this.property.getValueAsCollection(node);
            if (values == null) {
                return;
            }
            if (!values.isEmpty() && this.preceeding != null) {
                this.preceeding.prettyPrint(node, printer);
            }
            Iterator<?> it = values.iterator();
            while (it.hasNext()) {
                if (this.separatorPre != null && it.hasNext()) {
                    this.separatorPre.prettyPrint(node, printer);
                }
                printer.print(PrintingHelper.printToString(it.next()));
                if (this.separatorPost == null || !it.hasNext()) continue;
                this.separatorPost.prettyPrint(node, printer);
            }
            if (!values.isEmpty() && this.following != null) {
                this.following.prettyPrint(node, printer);
            }
        }
    }
}

