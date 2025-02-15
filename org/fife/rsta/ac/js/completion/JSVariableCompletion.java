/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.completion;

import java.util.Iterator;
import javax.swing.Icon;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.js.IconFactory;
import org.fife.rsta.ac.js.JavaScriptHelper;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.JavaScriptVariableDeclaration;
import org.fife.rsta.ac.js.completion.JSCompletionUI;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.VariableCompletion;

public class JSVariableCompletion
extends VariableCompletion
implements JSCompletionUI {
    private JavaScriptVariableDeclaration dec;
    private boolean localVariable;

    public JSVariableCompletion(CompletionProvider provider, JavaScriptVariableDeclaration dec) {
        this(provider, dec, true);
    }

    public JSVariableCompletion(CompletionProvider provider, JavaScriptVariableDeclaration dec, boolean localVariable) {
        super(provider, dec.getName(), dec.getJavaScriptTypeName());
        this.dec = dec;
        this.localVariable = localVariable;
    }

    @Override
    public String getType() {
        return this.getType(false);
    }

    public String getType(boolean qualified) {
        return ((SourceCompletionProvider)this.getProvider()).getTypesFactory().convertJavaScriptType(this.dec.getJavaScriptTypeName(), qualified);
    }

    @Override
    public String getAlreadyEntered(JTextComponent comp) {
        String temp = this.getProvider().getAlreadyEnteredText(comp);
        int lastDot = JavaScriptHelper.findLastIndexOfJavaScriptIdentifier(temp);
        if (lastDot > -1) {
            temp = temp.substring(lastDot + 1);
        }
        return temp;
    }

    @Override
    public Icon getIcon() {
        return IconFactory.getIcon(this.localVariable ? "local_variable" : "global_variable");
    }

    @Override
    public int getRelevance() {
        return this.localVariable ? 9 : 8;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VariableCompletion) {
            VariableCompletion comp = (VariableCompletion)obj;
            return this.getName().equals(comp.getName());
        }
        return super.equals(obj);
    }

    @Override
    public int compareTo(Completion c2) {
        if (c2 == this) {
            return 0;
        }
        return super.compareTo(c2);
    }

    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public String getSummary() {
        SourceCompletionProvider scp = (SourceCompletionProvider)this.getProvider();
        ClassFile cf = scp.getJavaScriptTypesFactory().getClassFile(scp.getJarManager(), JavaScriptHelper.createNewTypeDeclaration(this.getType(true)));
        if (cf != null) {
            CompilationUnit cu;
            SourceLocation loc = scp.getSourceLocForClass(cf.getClassName(true));
            if (loc != null && (cu = Util.getCompilationUnitFromDisk(loc, cf)) != null) {
                Iterator<TypeDeclaration> i = cu.getTypeDeclarationIterator();
                while (i.hasNext()) {
                    String summary;
                    TypeDeclaration td = i.next();
                    String typeName = td.getName();
                    if (!typeName.equals(cf.getClassName(false)) || (summary = td.getDocComment()) == null || !summary.startsWith("/**")) continue;
                    return Util.docCommentToHtml(summary);
                }
            }
            return cf.getClassName(true);
        }
        return super.getSummary();
    }
}

