/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.example;

import galaxy.jsp.encounter.jsp.JspBean;
import galaxy.jsp.encounter.jsp.Translator;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;

public class JspParser {
    public static JspBean TranslateToBeanEx(String src) {
        Pair<Integer, List<String>> scripts;
        Pair<Integer, List<String>> exprs;
        Pair<Integer, List<String>> decs;
        JspBean bean = new JspBean();
        String regexPage = "<%\\s*@\\s*page\\s*(.*?)%>";
        String regexDeclaration = "<%!\\s*(.*?)%>";
        String regexExpr = "<%=\\s*(.*?)%>";
        String regexScriptlet = "<%(?!\\!)\\s*(.*?)%>";
        String regexAttrs = "\\s*(.*?)=[\"](.*?[^\\\\])[\"]";
        Pair<Integer, List<String>> pages = Translator.LoopMatch(src, regexPage, 32);
        if (pages != null) {
            for (String attrsTxt : pages.getRight()) {
                Map<String, String> kvM = Translator.AttrsToMap(attrsTxt, regexAttrs);
                if (kvM == null) continue;
                bean.addPage(kvM);
            }
        }
        if ((decs = Translator.LoopMatch(src, regexDeclaration, 32)) != null) {
            bean.setDeclarations(decs.getRight());
        }
        if ((exprs = Translator.LoopMatch(src, regexExpr, 32)) != null) {
            bean.setExpressions(exprs.getRight());
        }
        if ((scripts = Translator.LoopMatch(src, regexScriptlet, 32)) != null) {
            bean.setScriptlets(scripts.getRight());
        }
        return bean;
    }

    public static void main(String[] args) throws IOException {
        String src = IOUtils.toString(Translator.class.getResourceAsStream("/e1.jsp"), StandardCharsets.UTF_8);
        JspBean bean = JspParser.TranslateToBeanEx(src);
    }
}

