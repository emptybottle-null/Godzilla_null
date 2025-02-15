/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package galaxy.jsp.encounter.jsp;

import galaxy.jsp.encounter.cfg.Config;
import galaxy.jsp.encounter.jsp.JspBean;
import galaxy.jsp.encounter.jsp.JspXML;
import galaxy.jsp.encounter.xml.Options;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Translator {
    public static void main(String[] args) throws Exception {
        byte[] bytes = new byte[3];
        int b = 194;
        int b2 = -62;
        Translator.class.getResourceAsStream("/test.txt").read(bytes);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
        String src = IOUtils.toString(Translator.class.getResourceAsStream("/test3.jsp"), StandardCharsets.UTF_8);
        JspBean bean = Translator.TranslateToBean(src);
        FileInputStream inputStream = new FileInputStream("E:\\turn1tup\\memshell\\JspEncounter\\pkg\\matrix.xlsx");
        Options options = Config.ParseCfg(inputStream);
        FileOutputStream outputStream = new FileOutputStream("E:\\turn1tup\\program_java\\2022.11.04_jsp\\apache-tomcat-10.0.27-windows-x64\\apache-tomcat-10.0.27\\webapps\\ROOT\\result.jsp");
        JspXML.BeanToXML(outputStream, bean, options);
    }

    public static Pair<Integer, List<String>> LoopMatch(String text, String reg) {
        return Translator.LoopMatch(text, reg, 0);
    }

    public static Pair<Integer, List<String>> LoopMatch(String text, String reg, int f) {
        Matcher mTmp;
        ArrayList<String> results = null;
        int end = 0;
        while ((mTmp = Pattern.compile(reg, f).matcher(text)).find()) {
            if (results == null) {
                results = new ArrayList<String>();
            }
            for (int i = 1; i < mTmp.groupCount() + 1; ++i) {
                results.add(mTmp.group(i));
            }
            end += mTmp.end();
            text = text.substring(mTmp.end());
        }
        if (results == null) {
            return null;
        }
        return new ImmutablePair<Integer, List<String>>(end, results);
    }

    public static Map<String, String> AttrsToMap(String attrsTxt, String regexAttrs) {
        Pair<Integer, List<String>> attrs = Translator.LoopMatch(attrsTxt, regexAttrs);
        if (attrs == null) {
            return null;
        }
        List<String> kvL = attrs.getRight();
        HashMap<String, String> kvM = new HashMap<String, String>();
        for (int i = 0; i < kvL.size(); i += 2) {
            kvM.put(kvL.get(i), kvL.get(i + 1));
        }
        return kvM;
    }

    public static JspBean TranslateToBean(String src) {
        Pair<Integer, List<String>> scripts;
        Pair<Integer, List<String>> exprs;
        Pair<Integer, List<String>> decs;
        JspBean bean = new JspBean();
        String regexPage = "<%\\s*@\\s*page\\s*(.*?)%>";
        String regexDeclaration = "<%!\\s*(.*?)%>";
        String regexExpr = "<%=\\s*(.*?)%>";
        String regexScriptlet = "<%\\s*(.*?)%>";
        String regexAttrs = "\\s*(.*?)=[\"](.*?[^\\\\])[\"]";
        Pair<Integer, List<String>> pages = Translator.LoopMatch(src, regexPage, 32);
        if (pages != null) {
            src = src.substring(pages.getLeft());
            for (String attrsTxt : pages.getRight()) {
                Map<String, String> kvM = Translator.AttrsToMap(attrsTxt, regexAttrs);
                if (kvM == null) continue;
                bean.addPage(kvM);
            }
        }
        if ((decs = Translator.LoopMatch(src, regexDeclaration, 32)) != null) {
            src = src.substring(decs.getLeft());
            bean.setDeclarations(decs.getRight());
        }
        if ((exprs = Translator.LoopMatch(src, regexExpr, 32)) != null) {
            src = src.substring(exprs.getLeft());
            bean.setExpressions(exprs.getRight());
        }
        if ((scripts = Translator.LoopMatch(src, regexScriptlet, 32)) != null) {
            bean.setScriptlets(scripts.getRight());
        }
        return bean;
    }
}

