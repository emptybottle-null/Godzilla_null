/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package galaxy.jsp.encounter.jsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JspBean {
    List<Map<String, String>> pages;
    List<Map<String, String>> include;
    List<String> declarations;
    List<String> scriptlets;
    List<String> expressions;

    public String toString() {
        return "JspBean{pages=" + this.pages + ", include=" + this.include + ", declarations=" + this.declarations + ", expressions=" + this.expressions + ", scriptlets=" + this.scriptlets + '}';
    }

    public void setExpressions(List<String> expressions) {
        this.expressions = expressions;
    }

    public List<String> getExpressions() {
        return this.expressions;
    }

    public void addPage(Map<String, String> page) {
        if (this.pages == null) {
            this.pages = new ArrayList<Map<String, String>>();
        }
        this.pages.add(page);
    }

    public List<Map<String, String>> getPages() {
        return this.pages;
    }

    public List<String> getScriptlets() {
        return this.scriptlets;
    }

    public void addsScriptlets(String dec) {
        if (this.scriptlets == null) {
            this.scriptlets = new ArrayList<String>();
        }
        this.scriptlets.add(dec);
    }

    public void setScriptlets(List<String> scriptlets) {
        this.scriptlets = scriptlets;
    }

    public void setDeclarations(List<String> declarations) {
        this.declarations = declarations;
    }

    public List<String> getDeclarations() {
        return this.declarations;
    }

    public void addDeclaration(String dec) {
        if (this.declarations == null) {
            this.declarations = new ArrayList<String>();
        }
        this.declarations.add(dec);
    }
}

