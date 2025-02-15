/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

class DefaultTokenMakerFactory
extends AbstractTokenMakerFactory
implements SyntaxConstants {
    DefaultTokenMakerFactory() {
    }

    @Override
    protected void initTokenMakerMap() {
        String pkg = "org.fife.ui.rsyntaxtextarea.modes.";
        this.putMapping("text/plain", pkg + "PlainTextTokenMaker");
        this.putMapping("text/actionscript", pkg + "ActionScriptTokenMaker");
        this.putMapping("text/asm", pkg + "AssemblerX86TokenMaker");
        this.putMapping("text/asm6502", pkg + "Assembler6502TokenMaker");
        this.putMapping("text/bbcode", pkg + "BBCodeTokenMaker");
        this.putMapping("text/c", pkg + "CTokenMaker");
        this.putMapping("text/clojure", pkg + "ClojureTokenMaker");
        this.putMapping("text/cpp", pkg + "CPlusPlusTokenMaker");
        this.putMapping("text/cs", pkg + "CSharpTokenMaker");
        this.putMapping("text/css", pkg + "CSSTokenMaker");
        this.putMapping("text/csv", pkg + "CsvTokenMaker");
        this.putMapping("text/d", pkg + "DTokenMaker");
        this.putMapping("text/dart", pkg + "DartTokenMaker");
        this.putMapping("text/delphi", pkg + "DelphiTokenMaker");
        this.putMapping("text/dockerfile", pkg + "DockerTokenMaker");
        this.putMapping("text/dtd", pkg + "DtdTokenMaker");
        this.putMapping("text/fortran", pkg + "FortranTokenMaker");
        this.putMapping("text/golang", pkg + "GoTokenMaker");
        this.putMapping("text/groovy", pkg + "GroovyTokenMaker");
        this.putMapping("text/hosts", pkg + "HostsTokenMaker");
        this.putMapping("text/htaccess", pkg + "HtaccessTokenMaker");
        this.putMapping("text/html", pkg + "HTMLTokenMaker");
        this.putMapping("text/ini", pkg + "IniTokenMaker");
        this.putMapping("text/java", pkg + "JavaTokenMaker");
        this.putMapping("text/javascript", pkg + "JavaScriptTokenMaker");
        this.putMapping("text/jshintrc", pkg + "JshintrcTokenMaker");
        this.putMapping("text/json", pkg + "JsonTokenMaker");
        this.putMapping("text/jsp", pkg + "JSPTokenMaker");
        this.putMapping("text/kotlin", pkg + "KotlinTokenMaker");
        this.putMapping("text/latex", pkg + "LatexTokenMaker");
        this.putMapping("text/less", pkg + "LessTokenMaker");
        this.putMapping("text/lisp", pkg + "LispTokenMaker");
        this.putMapping("text/lua", pkg + "LuaTokenMaker");
        this.putMapping("text/makefile", pkg + "MakefileTokenMaker");
        this.putMapping("text/markdown", pkg + "MarkdownTokenMaker");
        this.putMapping("text/mxml", pkg + "MxmlTokenMaker");
        this.putMapping("text/nsis", pkg + "NSISTokenMaker");
        this.putMapping("text/perl", pkg + "PerlTokenMaker");
        this.putMapping("text/php", pkg + "PHPTokenMaker");
        this.putMapping("text/properties", pkg + "PropertiesFileTokenMaker");
        this.putMapping("text/python", pkg + "PythonTokenMaker");
        this.putMapping("text/ruby", pkg + "RubyTokenMaker");
        this.putMapping("text/sas", pkg + "SASTokenMaker");
        this.putMapping("text/scala", pkg + "ScalaTokenMaker");
        this.putMapping("text/sql", pkg + "SQLTokenMaker");
        this.putMapping("text/tcl", pkg + "TclTokenMaker");
        this.putMapping("text/typescript", pkg + "TypeScriptTokenMaker");
        this.putMapping("text/unix", pkg + "UnixShellTokenMaker");
        this.putMapping("text/vb", pkg + "VisualBasicTokenMaker");
        this.putMapping("text/bat", pkg + "WindowsBatchTokenMaker");
        this.putMapping("text/xml", pkg + "XMLTokenMaker");
        this.putMapping("text/yaml", pkg + "YamlTokenMaker");
    }
}

