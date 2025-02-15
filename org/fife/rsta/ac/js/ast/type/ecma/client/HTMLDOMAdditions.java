/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.type.ecma.client;

import org.fife.rsta.ac.js.ast.type.ECMAAdditions;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.ast.type.ecma.TypeDeclarations;

public class HTMLDOMAdditions
implements ECMAAdditions {
    @Override
    public void addAdditionalTypes(TypeDeclarations typeDecs) {
        typeDecs.addTypeDeclaration("HTMLAnchorElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLAnchorElement", "HTMLAnchorElement", false, false));
        typeDecs.addTypeDeclaration("HTMLAppletElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLAppletElement", "HTMLAppletElement", false, false));
        typeDecs.addTypeDeclaration("HTMLAreaElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLAreaElement", "HTMLAreaElement", false, false));
        typeDecs.addTypeDeclaration("HTMLBaseElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLBaseElement", "HTMLBaseElement", false, false));
        typeDecs.addTypeDeclaration("HTMLBaseFontElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLBaseFontElement", "HTMLBaseFontElement", false, false));
        typeDecs.addTypeDeclaration("HTMLBodyElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLBodyElement", "HTMLBodyElement", false, false));
        typeDecs.addTypeDeclaration("HTMLBRElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLBRElement", "HTMLBRElement", false, false));
        typeDecs.addTypeDeclaration("HTMLButtonElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLButtonElement", "HTMLButtonElement", false, false));
        typeDecs.addTypeDeclaration("HTMLCollection", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLCollection", "HTMLCollection", false, false));
        typeDecs.addTypeDeclaration("HTMLDirectoryElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLDirectoryElement", "HTMLDirectoryElement", false, false));
        typeDecs.addTypeDeclaration("HTMLDivElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLDivElement", "HTMLDivElement", false, false));
        typeDecs.addTypeDeclaration("HTMLDListElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLDListElement", "HTMLDListElement", false, false));
        typeDecs.addTypeDeclaration("HTMLDocument", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLDocument", "HTMLDocument", false, false));
        typeDecs.addTypeDeclaration("HTMLElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLElement", "HTMLElement", false, false));
        typeDecs.addTypeDeclaration("HTMLFieldSetElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLFieldSetElement", "HTMLFieldSetElement", false, false));
        typeDecs.addTypeDeclaration("HTMLFontElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLFontElement", "HTMLFontElement", false, false));
        typeDecs.addTypeDeclaration("HTMLFormElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLFormElement", "HTMLFormElement", false, false));
        typeDecs.addTypeDeclaration("HTMLFrameElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLFrameElement", "HTMLFrameElement", false, false));
        typeDecs.addTypeDeclaration("HTMLFrameSetElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLFrameSetElement", "HTMLFrameSetElement", false, false));
        typeDecs.addTypeDeclaration("HTMLHeadElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLHeadElement", "HTMLHeadElement", false, false));
        typeDecs.addTypeDeclaration("HTMLHeadingElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLHeadingElement", "HTMLHeadingElement", false, false));
        typeDecs.addTypeDeclaration("HTMLHRElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLHRElement", "HTMLHRElement", false, false));
        typeDecs.addTypeDeclaration("HTMLHtmlElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLHtmlElement", "HTMLHtmlElement", false, false));
        typeDecs.addTypeDeclaration("HTMLIFrameElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLIFrameElement", "HTMLIFrameElement", false, false));
        typeDecs.addTypeDeclaration("HTMLImageElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLImageElement", "HTMLImageElement", false, false));
        typeDecs.addTypeDeclaration("HTMLInputElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLInputElement", "HTMLInputElement", false, false));
        typeDecs.addTypeDeclaration("HTMLIsIndexElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLIsIndexElement", "HTMLIsIndexElement", false, false));
        typeDecs.addTypeDeclaration("HTMLLabelElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLLabelElement", "HTMLLabelElement", false, false));
        typeDecs.addTypeDeclaration("HTMLLegendElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLLegendElement", "HTMLLegendElement", false, false));
        typeDecs.addTypeDeclaration("HTMLLIElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLLIElement", "HTMLLIElement", false, false));
        typeDecs.addTypeDeclaration("HTMLLinkElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLLinkElement", "HTMLLinkElement", false, false));
        typeDecs.addTypeDeclaration("HTMLMapElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLMapElement", "HTMLMapElement", false, false));
        typeDecs.addTypeDeclaration("HTMLMenuElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLMenuElement", "HTMLMenuElement", false, false));
        typeDecs.addTypeDeclaration("HTMLMetaElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLMetaElement", "HTMLMetaElement", false, false));
        typeDecs.addTypeDeclaration("HTMLModElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLModElement", "HTMLModElement", false, false));
        typeDecs.addTypeDeclaration("HTMLObjectElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLObjectElement", "HTMLObjectElement", false, false));
        typeDecs.addTypeDeclaration("HTMLOListElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLOListElement", "HTMLOListElement", false, false));
        typeDecs.addTypeDeclaration("HTMLOptGroupElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLOptGroupElement", "HTMLOptGroupElement", false, false));
        typeDecs.addTypeDeclaration("HTMLOptionElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLOptionElement", "HTMLOptionElement", false, false));
        typeDecs.addTypeDeclaration("HTMLOptionsCollection", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLOptionsCollection", "HTMLOptionsCollection", false, false));
        typeDecs.addTypeDeclaration("HTMLParagraphElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLParagraphElement", "HTMLParagraphElement", false, false));
        typeDecs.addTypeDeclaration("HTMLParamElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLParamElement", "JSHTMLParamElement", false, false));
        typeDecs.addTypeDeclaration("HTMLPreElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLPreElement", "HTMLPreElement", false, false));
        typeDecs.addTypeDeclaration("HTMLQuoteElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLQuoteElement", "HTMLQuoteElement", false, false));
        typeDecs.addTypeDeclaration("HTMLScriptElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLScriptElement", "HTMLScriptElement", false, false));
        typeDecs.addTypeDeclaration("HTMLSelectElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLSelectElement", "HTMLSelectElement", false, false));
        typeDecs.addTypeDeclaration("HTMLStyleElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLStyleElement", "HTMLStyleElement", false, false));
        typeDecs.addTypeDeclaration("HTMLTableCaptionElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLTableCaptionElement", "HTMLTableCaptionElement", false, false));
        typeDecs.addTypeDeclaration("HTMLTableCellElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLTableCellElement", "JSHTMLTableCellElement", false, false));
        typeDecs.addTypeDeclaration("HTMLTableColElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLTableColElement", "HTMLTableColElement", false, false));
        typeDecs.addTypeDeclaration("HTMLTableElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLTableElement", "HTMLTableElement", false, false));
        typeDecs.addTypeDeclaration("HTMLTableRowElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLTableRowElement", "HTMLTableRowElement", false, false));
        typeDecs.addTypeDeclaration("HTMLTableSectionElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLTableSectionElement", "HTMLTableSectionElement", false, false));
        typeDecs.addTypeDeclaration("HTMLTextAreaElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLTextAreaElement", "HTMLTextAreaElement", false, false));
        typeDecs.addTypeDeclaration("HTMLTitleElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLTitleElement", "HTMLTitleElement", false, false));
        typeDecs.addTypeDeclaration("HTMLUListElement", new TypeDeclaration("org.fife.rsta.ac.js.ecma.api.dom.html", "JSHTMLUListElement", "HTMLUListElement", false, false));
        typeDecs.addECMAObject("HTMLAnchorElement", true);
        typeDecs.addECMAObject("HTMLAppletElement", true);
        typeDecs.addECMAObject("HTMLAreaElement", true);
        typeDecs.addECMAObject("HTMLBaseElement", true);
        typeDecs.addECMAObject("HTMLBaseFontElement", true);
        typeDecs.addECMAObject("HTMLBodyElement", true);
        typeDecs.addECMAObject("HTMLBRElement", true);
        typeDecs.addECMAObject("HTMLButtonElement", true);
        typeDecs.addECMAObject("HTMLCollection", true);
        typeDecs.addECMAObject("HTMLDirectoryElement", true);
        typeDecs.addECMAObject("HTMLDivElement", true);
        typeDecs.addECMAObject("HTMLDListElement", true);
        typeDecs.addECMAObject("HTMLDocument", true);
        typeDecs.addECMAObject("HTMLElement", true);
        typeDecs.addECMAObject("HTMLFieldSetElement", true);
        typeDecs.addECMAObject("HTMLFontElement", true);
        typeDecs.addECMAObject("HTMLFormElement", true);
        typeDecs.addECMAObject("HTMLFrameElement", true);
        typeDecs.addECMAObject("HTMLFrameSetElement", true);
        typeDecs.addECMAObject("HTMLHeadElement", true);
        typeDecs.addECMAObject("HTMLHeadingElement", true);
        typeDecs.addECMAObject("HTMLHRElement", true);
        typeDecs.addECMAObject("HTMLHtmlElement", true);
        typeDecs.addECMAObject("HTMLIFrameElement", true);
        typeDecs.addECMAObject("HTMLImageElement", true);
        typeDecs.addECMAObject("HTMLInputElement", true);
        typeDecs.addECMAObject("HTMLIsIndexElement", true);
        typeDecs.addECMAObject("HTMLLabelElement", true);
        typeDecs.addECMAObject("HTMLLegendElement", true);
        typeDecs.addECMAObject("HTMLLIElement", true);
        typeDecs.addECMAObject("HTMLLinkElement", true);
        typeDecs.addECMAObject("HTMLMapElement", true);
        typeDecs.addECMAObject("HTMLMenuElement", true);
        typeDecs.addECMAObject("HTMLMetaElement", true);
        typeDecs.addECMAObject("HTMLModElement", true);
        typeDecs.addECMAObject("HTMLObjectElement", true);
        typeDecs.addECMAObject("HTMLOListElement", true);
        typeDecs.addECMAObject("HTMLOptGroupElement", true);
        typeDecs.addECMAObject("HTMLOptionElement", true);
        typeDecs.addECMAObject("HTMLOptionsCollection", true);
        typeDecs.addECMAObject("HTMLParagraphElement", true);
        typeDecs.addECMAObject("HTMLParamElement", true);
        typeDecs.addECMAObject("HTMLPreElement", true);
        typeDecs.addECMAObject("HTMLQuoteElement", true);
        typeDecs.addECMAObject("HTMLScriptElement", true);
        typeDecs.addECMAObject("HTMLSelectElement", true);
        typeDecs.addECMAObject("HTMLStyleElement", true);
        typeDecs.addECMAObject("HTMLTableCaptionElement", true);
        typeDecs.addECMAObject("HTMLTableCellElement", true);
        typeDecs.addECMAObject("HTMLTableColElement", true);
        typeDecs.addECMAObject("HTMLTableElement", true);
        typeDecs.addECMAObject("HTMLTableRowElement", true);
        typeDecs.addECMAObject("HTMLTableSectionElement", true);
        typeDecs.addECMAObject("HTMLTextAreaElement", true);
        typeDecs.addECMAObject("HTMLTitleElement", true);
        typeDecs.addECMAObject("HTMLUListElement", true);
    }
}

