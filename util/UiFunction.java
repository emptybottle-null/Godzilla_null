/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package util;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.ToolTipManager;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class UiFunction {
    public static String setSyntaxEditingStyle(RSyntaxTextArea textArea, String fileName) {
        String style = null;
        if ((fileName = fileName.toLowerCase()).endsWith(".as")) {
            style = "text/actionscript";
        } else if (fileName.endsWith(".asm")) {
            style = "text/asm";
        } else if (fileName.endsWith(".c")) {
            style = "text/c";
        } else if (fileName.endsWith(".clj")) {
            style = "text/clojure";
        } else if (fileName.endsWith(".cpp") || fileName.endsWith("cc")) {
            style = "text/cpp";
        } else if (fileName.endsWith(".cs") || fileName.endsWith(".aspx") || fileName.endsWith(".ashx") || fileName.endsWith(".asmx")) {
            style = "text/cs";
        } else if (fileName.endsWith(".css")) {
            style = "text/css";
        } else if (fileName.endsWith(".d")) {
            style = "text/d";
        } else if (fileName.equals("dockfile")) {
            style = "text/dockerfile";
        } else if (fileName.endsWith(".dart")) {
            style = "text/dart";
        } else if (fileName.endsWith(".dpr") | fileName.endsWith(".dfm") | fileName.endsWith(".pas")) {
            style = "text/delphi";
        } else if (fileName.endsWith(".dtd")) {
            style = "text/dtd";
        } else if (fileName.endsWith(".f") | fileName.endsWith(".f90")) {
            style = "text/fortran";
        } else if (fileName.endsWith(".groovy")) {
            style = "text/groovy";
        } else if (fileName.equals("hosts")) {
            style = "text/hosts";
        } else if (fileName.equals(".htaccess")) {
            style = "text/htaccess";
        } else if (fileName.endsWith(".htm") | fileName.endsWith(".html")) {
            style = "text/html";
        } else if (fileName.endsWith(".ini")) {
            style = "text/ini";
        } else if (fileName.endsWith(".java") | fileName.endsWith(".class")) {
            style = "text/java";
        } else if (fileName.endsWith(".js")) {
            style = "text/javascript";
        } else if (fileName.endsWith(".json")) {
            style = "text/json";
        } else if (fileName.equals(".jshintrc")) {
            style = "text/jshintrc";
        } else if (fileName.endsWith(".jsp") || fileName.endsWith(".jspx")) {
            style = "text/jsp";
        } else if (fileName.endsWith(".tex")) {
            style = "text/latex";
        } else if (fileName.endsWith(".less")) {
            style = "text/less";
        } else if (fileName.endsWith(".lsp")) {
            style = "text/lisp";
        } else if (fileName.endsWith(".lua")) {
            style = "text/lua";
        } else if (fileName.equals("makefile")) {
            style = "text/makefile";
        } else if (fileName.endsWith(".mxml")) {
            style = "text/mxml";
        } else if (fileName.endsWith(".nsi")) {
            style = "text/nsis";
        } else if (fileName.endsWith(".pl") | fileName.endsWith(".perl")) {
            style = "text/perl";
        } else if (fileName.endsWith(".php") || fileName.endsWith(".phtml") || fileName.endsWith(".php4") || fileName.endsWith(".php3") || fileName.endsWith(".php5")) {
            style = "text/php";
        } else if (fileName.endsWith(".properties")) {
            style = "text/properties";
        } else if (fileName.endsWith(".py") | fileName.endsWith(".pyc")) {
            style = "text/python";
        } else if (fileName.endsWith(".rb") | fileName.endsWith(".rwb")) {
            style = "text/ruby";
        } else if (fileName.endsWith(".sas")) {
            style = "text/sas";
        } else if (fileName.endsWith(".scala")) {
            style = "text/scala";
        } else if (fileName.endsWith(".sql")) {
            style = "text/sql";
        } else if (fileName.endsWith(".tcl")) {
            style = "text/tcl";
        } else if (fileName.endsWith(".ts") | fileName.endsWith(".tsx")) {
            style = "text/typescript";
        } else if (fileName.endsWith(".sh")) {
            style = "text/unix";
        } else if (fileName.endsWith(".vb")) {
            style = "text/vb";
        } else if (fileName.endsWith(".bat")) {
            style = "text/bat";
        } else if (fileName.endsWith(".xml")) {
            style = "text/xml";
        } else if (fileName.endsWith(".yaml")) {
            style = "text/yaml";
        } else if (fileName.endsWith(".go")) {
            style = "text/golang";
        } else if (fileName.endsWith(".asp")) {
            style = "text/javascript";
        }
        if (style == null) {
            style = "text/plain";
        } else {
            LanguageSupportFactory.get().register(textArea);
            textArea.setCaretPosition(0);
            textArea.requestFocusInWindow();
            textArea.setMarkOccurrences(true);
            textArea.setCodeFoldingEnabled(true);
            textArea.setTabsEmulated(true);
            textArea.setTabSize(3);
            textArea.setUseFocusableTips(false);
            ToolTipManager.sharedInstance().registerComponent(textArea);
        }
        textArea.setSyntaxEditingStyle(style);
        textArea.registerReplaceDialog();
        textArea.registerGoToDialog();
        return style;
    }

    public static Frame getParentFrame(Container container) {
        while ((container = container.getParent()) != null) {
            if (!Frame.class.isAssignableFrom(container.getClass())) continue;
            return (Frame)container;
        }
        return null;
    }

    public static Dialog getParentDialog(Container container) {
        while ((container = container.getParent()) != null) {
            if (!Dialog.class.isAssignableFrom(container.getClass())) continue;
            return (Dialog)container;
        }
        return null;
    }

    public static Window getParentWindow(Container container) {
        while ((container = container.getParent()) != null) {
            if (!Window.class.isAssignableFrom(container.getClass())) continue;
            return (Window)container;
        }
        return null;
    }

    public static String getFontType(Font font) {
        if (font.isBold()) {
            return "Bold".toUpperCase();
        }
        if (font.isItalic()) {
            return "Italic".toUpperCase();
        }
        if (font.isPlain()) {
            return "Plain".toUpperCase();
        }
        return "Plain";
    }

    public static int getFontType(String fontType) {
        switch (fontType.toUpperCase()) {
            case "BOLD": {
                return 1;
            }
            case "ITALIC": {
                return 2;
            }
            case "PLAIN": {
                return 0;
            }
        }
        return 0;
    }

    public static String[] getAllFontName() {
        Font[] fonts;
        ArrayList<String> arrayList = new ArrayList<String>();
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (Font font : fonts = e.getAllFonts()) {
            arrayList.add(font.getFontName());
        }
        return arrayList.toArray(new String[0]);
    }

    public static String[] getAllFontType() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("BOLD");
        arrayList.add("ITALIC");
        arrayList.add("PLAIN");
        return arrayList.toArray(new String[0]);
    }

    public static String[] getAllFontSize() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 8; i < 48; ++i) {
            arrayList.add(Integer.toString(i));
        }
        return arrayList.toArray(new String[0]);
    }
}

