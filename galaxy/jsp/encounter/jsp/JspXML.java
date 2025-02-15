/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package galaxy.jsp.encounter.jsp;

import galaxy.jsp.encounter.charset.CharsetEncoder;
import galaxy.jsp.encounter.jsp.JspBean;
import galaxy.jsp.encounter.util.Const;
import galaxy.jsp.encounter.util.Utils;
import galaxy.jsp.encounter.xml.JspXMLWriter;
import galaxy.jsp.encounter.xml.Options;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;

public class JspXML {
    public static void BeanToXML(OutputStream outputStream, JspBean bean, Options options) throws Exception {
        if (options.charsetMagic != null) {
            String headerRaw = String.format("<?xml version=\"%s\" encoding=\"%s\"?>", options.xmlVersion, options.charsetMagic);
            byte[] header = CharsetEncoder.Encode(headerRaw, options.charsetMagic, options.removeBom);
            outputStream.write(header);
        }
        ByteArrayOutputStream bodyOutputStream = new ByteArrayOutputStream();
        JspXML.BeanToBodyXML(bodyOutputStream, bean, options);
        String bodyStr = bodyOutputStream.toString();
        if (options.removeJspRootTag) {
            String lf = "\n";
            int index1 = bodyStr.indexOf(lf, 1);
            int index2 = bodyStr.lastIndexOf(lf);
            bodyStr = bodyStr.substring(index1, index2);
        }
        String tagWord = "<asdfqwezxcv/>";
        int kIndex = bodyStr.indexOf(tagWord);
        String bodyStrPrefix = bodyStr.substring(0, kIndex);
        bodyStrPrefix = Utils.RStrip(bodyStrPrefix);
        String bodyStrSuffix = bodyStr.substring(kIndex + tagWord.length());
        String charsetTmp = options.charsetMagic != null ? options.charsetMagic : options.charsetDeclare;
        byte[] bodyPrefix = CharsetEncoder.Encode(bodyStrPrefix, charsetTmp, true);
        outputStream.write(bodyPrefix);
        if (options.controllerSpaceReplace != null) {
            byte[] marks = CharsetEncoder.Encode("\u00a7", options.charsetDeclare, true);
            byte[] bodySuffix = CharsetEncoder.Encode(bodyStrSuffix, options.charsetDeclare, true);
            Charset charset = Charset.forName(options.charsetDeclare);
            ByteArrayOutputStream tmpStream = new ByteArrayOutputStream();
            int needIgnore = 0;
            for (int bodyIndex = 0; bodyIndex < bodySuffix.length; ++bodyIndex) {
                if (needIgnore > 0) {
                    --needIgnore;
                    continue;
                }
                byte bodyChar = bodySuffix[bodyIndex];
                if (bodyIndex + marks.length < bodySuffix.length) {
                    boolean matchMarks = true;
                    for (int markIndex = 0; markIndex < marks.length; ++markIndex) {
                        if (marks[markIndex] == bodySuffix[bodyIndex + markIndex]) continue;
                        matchMarks = false;
                        break;
                    }
                    if (matchMarks) {
                        needIgnore = marks.length - 1;
                        if (charset == StandardCharsets.UTF_8) {
                            tmpStream.write(-30);
                            tmpStream.write(-128);
                            tmpStream.write(-88);
                            continue;
                        }
                        if (charset == StandardCharsets.ISO_8859_1) {
                            tmpStream.write(133);
                            continue;
                        }
                        if (charset == StandardCharsets.UTF_16 || charset == StandardCharsets.UTF_16BE) {
                            tmpStream.write(0);
                            tmpStream.write(133);
                            continue;
                        }
                        if (charset == StandardCharsets.UTF_16LE) {
                            tmpStream.write(133);
                            tmpStream.write(0);
                            continue;
                        }
                        tmpStream.write(CharsetEncoder.Encode(" ", options.charsetDeclare, true));
                        continue;
                    }
                    tmpStream.write(bodyChar);
                    continue;
                }
                tmpStream.write(bodyChar);
            }
            outputStream.write(tmpStream.toByteArray());
        } else if (options.charsetDeclareLater) {
            int ltIndex = bodyStrSuffix.lastIndexOf("<");
            String bodyStrSuffix1 = bodyStrSuffix.substring(0, ltIndex);
            String bodyStrSuffix2 = bodyStrSuffix.substring(ltIndex);
            byte[] bodySuffix1 = CharsetEncoder.Encode(bodyStrSuffix1, options.charsetDeclare, true);
            outputStream.write(bodySuffix1);
            byte[] bodySuffix2 = CharsetEncoder.Encode(bodyStrSuffix2, "utf-8", true);
            outputStream.write(bodySuffix2);
        } else {
            byte[] bodySuffix = CharsetEncoder.Encode(bodyStrSuffix, options.charsetDeclare, true);
            outputStream.write(bodySuffix);
        }
    }

    private static void addPageEncoding(List<Map<String, String>> pages, final Options options) {
        if (Const.DeclareAttr.pageEncoding.name().equals(options.charsetDeclareAttr)) {
            pages.add((Map<String, String>)new HashMap<String, String>(){
                {
                    this.put(options.charsetDeclareAttr, options.charsetDeclare);
                }
            });
        } else {
            pages.add((Map<String, String>)new HashMap<String, String>(){
                {
                    this.put(options.charsetDeclareAttr, RandomStringUtils.randomAlphabetic(10) + "charset=" + options.charsetDeclare);
                }
            });
        }
    }

    /*
     * WARNING - void declaration
     */
    private static void BeanToBodyXML(OutputStream outputStream, JspBean bean, Options options) throws Exception {
        boolean bl;
        Object ele;
        if (options.namespace != null && options.namespace.startsWith("random")) {
            int n = Integer.parseInt(options.namespace.split(",")[1]);
            options.namespace = RandomStringUtils.randomAlphabetic(n);
        }
        Namespace jspNs = new Namespace(options.namespace, "http://java.sun.com/JSP/Page");
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(new QName("root", jspNs));
        root.addAttribute("version", options.jspVersion);
        ArrayList<Map<String, String>> pages = new ArrayList<Map<String, String>>();
        String[] filters = new String[]{Const.DeclareAttr.pageEncoding.name(), Const.DeclareAttr.contentType.name()};
        for (Map<String, String> map : bean.getPages()) {
            void var10_19;
            Object var10_20 = null;
            for (String string : map.keySet()) {
                if (Arrays.stream(filters).anyMatch(f -> f.equalsIgnoreCase(string))) continue;
                if (var10_19 == null) {
                    HashMap hashMap = new HashMap();
                }
                var10_19.put(string, map.get(string));
            }
            if (var10_19 == null) continue;
            if (!options.charsetDeclareLater) {
                JspXML.addPageEncoding(pages, options);
            }
            pages.add((Map<String, String>)var10_19);
        }
        if (pages.size() == 0 && !options.charsetDeclareLater) {
            JspXML.addPageEncoding(pages, options);
        }
        int count = 0;
        for (Map map : pages) {
            ele = root.addElement(new QName("directive.page", jspNs));
            for (String string : map.keySet()) {
                ele.addAttribute(string, (String)map.get(string));
            }
            if (count == 0) {
                root.addElement("asdfqwezxcv");
            }
            ++count;
        }
        if (bean.getDeclarations() != null) {
            for (String string : bean.getDeclarations()) {
                ele = root.addElement(new QName("declaration", jspNs));
                String string2 = JspXML.codeEscape(string, options);
                ele.setText(string2);
            }
        }
        if (bean.getExpressions() != null) {
            for (String string : bean.getExpressions()) {
                ele = root.addElement(new QName("expression", jspNs));
                String string3 = JspXML.codeEscape(string, options);
                ele.setText(string3);
            }
        }
        boolean bl2 = false;
        boolean bl3 = false;
        for (Pair pair : options.extTags) {
            String string = (String)pair.getLeft();
            bl = true;
            Map attrs = (Map)pair.getRight();
            for (String val : attrs.values()) {
                var10_31 += Utils.CountChar(val, '\u00a7');
            }
        }
        if (bl) {
            void var10_31;
            StringBuilder sb = new StringBuilder();
            for (String string : bean.getScriptlets()) {
                sb.append(string);
            }
            String string = sb.toString();
            String[] stringArray = string.split("\n");
            StringBuilder[] items = new StringBuilder[var10_31];
            int sumLine = stringArray.length;
            int cap = (int)Math.ceil((float)sumLine / (float)var10_31);
            for (int i = 0; i < stringArray.length; ++i) {
                StringBuilder sbTmp = items[i / cap];
                if (sbTmp == null) {
                    items[i / cap] = sbTmp = new StringBuilder();
                }
                sbTmp.append(stringArray[i]);
            }
            Pattern p = Pattern.compile("(\u00a7\\d+)");
            for (Pair<String, Map<String, String>> extTag : options.extTags) {
                String name = extTag.getLeft();
                Map<String, String> attrs = extTag.getRight();
                Element tag = root.addElement(new QName(name, jspNs));
                for (String k : attrs.keySet()) {
                    Matcher m;
                    String v = attrs.get(k);
                    while ((m = p.matcher(v)).find()) {
                        String tmp = items[Integer.parseInt(m.group(1).substring(1)) - 1].toString();
                        tmp = tmp.replace("\"", "\\\"");
                        v = v.replace(m.group(1), tmp);
                    }
                    tag.addAttribute(k, v);
                }
            }
        }
        if (bean.getScriptlets() != null && !bl) {
            for (String string : bean.getScriptlets()) {
                Element element = root.addElement(new QName("scriptlet", jspNs));
                String string4 = JspXML.codeEscape(string, options);
                element.setText(string4);
            }
        }
        if (options.charsetDeclareLater) {
            ArrayList<Map<String, String>> pagesTmp = new ArrayList<Map<String, String>>();
            JspXML.addPageEncoding(pagesTmp, options);
            for (Map map : pagesTmp) {
                Element ele3 = root.addElement(new QName("directive.page", jspNs));
                for (String k : map.keySet()) {
                    ele3.addAttribute(k, (String)map.get(k));
                }
            }
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setSuppressDeclaration(true);
        format.setEncoding("UTF-8");
        format.setNewLineAfterDeclaration(false);
        JspXMLWriter jspXMLWriter = new JspXMLWriter(outputStream, format, options);
        jspXMLWriter.setEscapeText(true);
        jspXMLWriter.setEscapeText(false);
        jspXMLWriter.write(document);
        jspXMLWriter.close();
    }

    private static String codeEscape(String text, Options options) {
        System.out.println(options);
        if (options.codeEscapeType == null) {
            return text;
        }
        if ("unicode".equalsIgnoreCase(options.codeEscapeType)) {
            return Utils.UnicodeEscape(text);
        }
        return text;
    }
}

