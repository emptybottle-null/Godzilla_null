/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo.intellijthemes;

import com.formdev.flatlaf.demo.intellijthemes.IJThemeInfo;
import com.formdev.flatlaf.demo.intellijthemes.IJThemesManager;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class IJThemesClassGenerator {
    private static final String CLASS_HEADER = "/*\n * Copyright 2020 FormDev Software GmbH\n *\n * Licensed under the Apache License, Version 2.0 (the \"License\");\n * you may not use this file except in compliance with the License.\n * You may obtain a copy of the License at\n *\n *     https://www.apache.org/licenses/LICENSE-2.0\n *\n * Unless required by applicable law or agreed to in writing, software\n * distributed under the License is distributed on an \"AS IS\" BASIS,\n * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n * See the License for the specific language governing permissions and\n * limitations under the License.\n */\n\npackage com.formdev.flatlaf.intellijthemes${subPackage};\n\n//\n// DO NOT MODIFY\n// Generated with com.formdev.flatlaf.demo.intellijthemes.IJThemesClassGenerator\n//\n\n";
    private static final String CLASS_TEMPLATE = "import com.formdev.flatlaf.IntelliJTheme;\n\n/**\n * @author Karl Tauber\n */\npublic class ${themeClass}\n\textends IntelliJTheme.ThemeLaf\n{\n\tpublic static final String NAME = \"${themeName}\";\n\n\tpublic static boolean install() {\n\t\ttry {\n\t\t\treturn install( new ${themeClass}() );\n\t\t} catch( RuntimeException ex ) {\n\t\t\treturn false;\n\t\t}\n\t}\n\n\tpublic static void installLafInfo() {\n\t\tinstallLafInfo( NAME, ${themeClass}.class );\n\t}\n\n\tpublic ${themeClass}() {\n\t\tsuper( Utils.loadTheme( \"${themeFile}\" ) );\n\t}\n\n\t@Override\n\tpublic String getName() {\n\t\treturn NAME;\n\t}\n}\n";
    private static final String ALL_THEMES_TEMPLATE = "import javax.swing.UIManager.LookAndFeelInfo;\n\n/**\n * @author Karl Tauber\n */\npublic class FlatAllIJThemes\n{\n\tpublic static final FlatIJLookAndFeelInfo[] INFOS = {\n${allInfos}\n\t};\n\n\t//---- class FlatIJLookAndFeelInfo ----------------------------------------\n\n\tpublic static class FlatIJLookAndFeelInfo\n\t\textends LookAndFeelInfo\n\t{\n\t\tprivate final boolean dark;\n\n\t\tpublic FlatIJLookAndFeelInfo( String name, String className, boolean dark ) {\n\t\t\tsuper( name, className );\n\t\t\tthis.dark = dark;\n\t\t}\n\n\t\tpublic boolean isDark() {\n\t\t\treturn dark;\n\t\t}\n\t}\n}\n";
    private static final String THEME_TEMPLATE = "\t\tnew FlatIJLookAndFeelInfo( \"${themeName}\", \"com.formdev.flatlaf.intellijthemes${subPackage}.${themeClass}\", ${dark} ),";

    public static void main(String[] args) {
        IJThemesManager themesManager = new IJThemesManager();
        themesManager.loadBundledThemes();
        String toPath = "../flatlaf-intellij-themes/src/main/java/com/formdev/flatlaf/intellijthemes/themes/..";
        StringBuilder allInfos = new StringBuilder();
        StringBuilder markdownTable = new StringBuilder();
        markdownTable.append("Name | Class\n");
        markdownTable.append("-----|------\n");
        for (IJThemeInfo ti : themesManager.bundledThemes) {
            if (ti.sourceCodeUrl == null || ti.sourceCodePath == null) continue;
            IJThemesClassGenerator.generateClass(ti, toPath, allInfos, markdownTable);
        }
        Path out = new File(toPath, "FlatAllIJThemes.java").toPath();
        String allThemes = "/*\n * Copyright 2020 FormDev Software GmbH\n *\n * Licensed under the Apache License, Version 2.0 (the \"License\");\n * you may not use this file except in compliance with the License.\n * You may obtain a copy of the License at\n *\n *     https://www.apache.org/licenses/LICENSE-2.0\n *\n * Unless required by applicable law or agreed to in writing, software\n * distributed under the License is distributed on an \"AS IS\" BASIS,\n * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n * See the License for the specific language governing permissions and\n * limitations under the License.\n */\n\npackage com.formdev.flatlaf.intellijthemes${subPackage};\n\n//\n// DO NOT MODIFY\n// Generated with com.formdev.flatlaf.demo.intellijthemes.IJThemesClassGenerator\n//\n\nimport javax.swing.UIManager.LookAndFeelInfo;\n\n/**\n * @author Karl Tauber\n */\npublic class FlatAllIJThemes\n{\n\tpublic static final FlatIJLookAndFeelInfo[] INFOS = {\n${allInfos}\n\t};\n\n\t//---- class FlatIJLookAndFeelInfo ----------------------------------------\n\n\tpublic static class FlatIJLookAndFeelInfo\n\t\textends LookAndFeelInfo\n\t{\n\t\tprivate final boolean dark;\n\n\t\tpublic FlatIJLookAndFeelInfo( String name, String className, boolean dark ) {\n\t\t\tsuper( name, className );\n\t\t\tthis.dark = dark;\n\t\t}\n\n\t\tpublic boolean isDark() {\n\t\t\treturn dark;\n\t\t}\n\t}\n}\n".replace("${subPackage}", "").replace("${allInfos}", allInfos);
        IJThemesClassGenerator.writeFile(out, allThemes);
        System.out.println(markdownTable);
    }

    private static void generateClass(IJThemeInfo ti, String toPath, StringBuilder allInfos, StringBuilder markdownTable) {
        String name;
        int nameSep;
        String resourceName = ti.resourceName;
        String resourcePath = null;
        int resSep = resourceName.indexOf(47);
        if (resSep >= 0) {
            resourcePath = resourceName.substring(0, resSep);
            resourceName = resourceName.substring(resSep + 1);
        }
        if ((nameSep = (name = ti.name).indexOf(47)) >= 0) {
            name = name.substring(nameSep + 1).trim();
        }
        String themeName = name;
        if ("material-theme-ui-lite".equals(resourcePath)) {
            themeName = themeName + " (Material)";
        }
        StringBuilder buf = new StringBuilder();
        for (String n : name.split(" ")) {
            if (n.length() == 0 || n.equals("-")) continue;
            if (Character.isUpperCase(n.charAt(0))) {
                buf.append(n);
                continue;
            }
            buf.append(Character.toUpperCase(n.charAt(0))).append(n.substring(1));
        }
        String subPackage = resourcePath != null ? '.' + resourcePath.replace("-", "") : "";
        String themeClass = "Flat" + buf + "IJTheme";
        String themeFile = resourceName;
        String classBody = "/*\n * Copyright 2020 FormDev Software GmbH\n *\n * Licensed under the Apache License, Version 2.0 (the \"License\");\n * you may not use this file except in compliance with the License.\n * You may obtain a copy of the License at\n *\n *     https://www.apache.org/licenses/LICENSE-2.0\n *\n * Unless required by applicable law or agreed to in writing, software\n * distributed under the License is distributed on an \"AS IS\" BASIS,\n * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n * See the License for the specific language governing permissions and\n * limitations under the License.\n */\n\npackage com.formdev.flatlaf.intellijthemes${subPackage};\n\n//\n// DO NOT MODIFY\n// Generated with com.formdev.flatlaf.demo.intellijthemes.IJThemesClassGenerator\n//\n\nimport com.formdev.flatlaf.IntelliJTheme;\n\n/**\n * @author Karl Tauber\n */\npublic class ${themeClass}\n\textends IntelliJTheme.ThemeLaf\n{\n\tpublic static final String NAME = \"${themeName}\";\n\n\tpublic static boolean install() {\n\t\ttry {\n\t\t\treturn install( new ${themeClass}() );\n\t\t} catch( RuntimeException ex ) {\n\t\t\treturn false;\n\t\t}\n\t}\n\n\tpublic static void installLafInfo() {\n\t\tinstallLafInfo( NAME, ${themeClass}.class );\n\t}\n\n\tpublic ${themeClass}() {\n\t\tsuper( Utils.loadTheme( \"${themeFile}\" ) );\n\t}\n\n\t@Override\n\tpublic String getName() {\n\t\treturn NAME;\n\t}\n}\n".replace("${subPackage}", subPackage).replace("${themeClass}", themeClass).replace("${themeFile}", themeFile).replace("${themeName}", themeName);
        File toDir = new File(toPath);
        if (resourcePath != null) {
            toDir = new File(toDir, resourcePath.replace("-", ""));
        }
        Path out = new File(toDir, themeClass + ".java").toPath();
        IJThemesClassGenerator.writeFile(out, classBody);
        if (allInfos.length() > 0) {
            allInfos.append('\n');
        }
        allInfos.append(THEME_TEMPLATE.replace("${subPackage}", subPackage).replace("${themeClass}", themeClass).replace("${themeName}", themeName).replace("${dark}", Boolean.toString(ti.dark)));
        markdownTable.append(String.format("[%s](%s) | `com.formdev.flatlaf.intellijthemes%s.%s`\n", themeName, ti.sourceCodeUrl, subPackage, themeClass));
    }

    private static void writeFile(Path out, String content) {
        try {
            Files.write(out, content.getBytes(StandardCharsets.ISO_8859_1), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

