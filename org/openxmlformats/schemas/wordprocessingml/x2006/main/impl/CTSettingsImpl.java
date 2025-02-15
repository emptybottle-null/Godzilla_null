/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTMathPr
 *  org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchemaLibrary
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaptions
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCharacterSpacing
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColorSchemeMapping
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCompat
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocRsids
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocType
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocVars
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnDocProps
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnDocProps
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTKinsoku
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMailMerge
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProof
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSaveThroughXslt
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShapeDefaults
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShortHexNumber
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagType
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTwipsMeasure
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTView
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWriteProtection
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMathPr;
import org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchemaLibrary;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCharacterSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColorSchemeMapping;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCompat;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocRsids;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocVars;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnDocProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnDocProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTKinsoku;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMailMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProof;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSaveThroughXslt;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShapeDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShortHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTView;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWriteProtection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom;

public class CTSettingsImpl
extends XmlComplexContentImpl
implements CTSettings {
    private static final long serialVersionUID = 1L;
    private static final QName WRITEPROTECTION$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "writeProtection");
    private static final QName VIEW$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "view");
    private static final QName ZOOM$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "zoom");
    private static final QName REMOVEPERSONALINFORMATION$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "removePersonalInformation");
    private static final QName REMOVEDATEANDTIME$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "removeDateAndTime");
    private static final QName DONOTDISPLAYPAGEBOUNDARIES$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "doNotDisplayPageBoundaries");
    private static final QName DISPLAYBACKGROUNDSHAPE$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "displayBackgroundShape");
    private static final QName PRINTPOSTSCRIPTOVERTEXT$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "printPostScriptOverText");
    private static final QName PRINTFRACTIONALCHARACTERWIDTH$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "printFractionalCharacterWidth");
    private static final QName PRINTFORMSDATA$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "printFormsData");
    private static final QName EMBEDTRUETYPEFONTS$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "embedTrueTypeFonts");
    private static final QName EMBEDSYSTEMFONTS$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "embedSystemFonts");
    private static final QName SAVESUBSETFONTS$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "saveSubsetFonts");
    private static final QName SAVEFORMSDATA$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "saveFormsData");
    private static final QName MIRRORMARGINS$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "mirrorMargins");
    private static final QName ALIGNBORDERSANDEDGES$30 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "alignBordersAndEdges");
    private static final QName BORDERSDONOTSURROUNDHEADER$32 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bordersDoNotSurroundHeader");
    private static final QName BORDERSDONOTSURROUNDFOOTER$34 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bordersDoNotSurroundFooter");
    private static final QName GUTTERATTOP$36 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "gutterAtTop");
    private static final QName HIDESPELLINGERRORS$38 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hideSpellingErrors");
    private static final QName HIDEGRAMMATICALERRORS$40 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hideGrammaticalErrors");
    private static final QName ACTIVEWRITINGSTYLE$42 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "activeWritingStyle");
    private static final QName PROOFSTATE$44 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "proofState");
    private static final QName FORMSDESIGN$46 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "formsDesign");
    private static final QName ATTACHEDTEMPLATE$48 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "attachedTemplate");
    private static final QName LINKSTYLES$50 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "linkStyles");
    private static final QName STYLEPANEFORMATFILTER$52 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "stylePaneFormatFilter");
    private static final QName STYLEPANESORTMETHOD$54 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "stylePaneSortMethod");
    private static final QName DOCUMENTTYPE$56 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "documentType");
    private static final QName MAILMERGE$58 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "mailMerge");
    private static final QName REVISIONVIEW$60 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "revisionView");
    private static final QName TRACKREVISIONS$62 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "trackRevisions");
    private static final QName DONOTTRACKMOVES$64 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "doNotTrackMoves");
    private static final QName DONOTTRACKFORMATTING$66 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "doNotTrackFormatting");
    private static final QName DOCUMENTPROTECTION$68 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "documentProtection");
    private static final QName AUTOFORMATOVERRIDE$70 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "autoFormatOverride");
    private static final QName STYLELOCKTHEME$72 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "styleLockTheme");
    private static final QName STYLELOCKQFSET$74 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "styleLockQFSet");
    private static final QName DEFAULTTABSTOP$76 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "defaultTabStop");
    private static final QName AUTOHYPHENATION$78 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "autoHyphenation");
    private static final QName CONSECUTIVEHYPHENLIMIT$80 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "consecutiveHyphenLimit");
    private static final QName HYPHENATIONZONE$82 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hyphenationZone");
    private static final QName DONOTHYPHENATECAPS$84 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "doNotHyphenateCaps");
    private static final QName SHOWENVELOPE$86 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "showEnvelope");
    private static final QName SUMMARYLENGTH$88 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "summaryLength");
    private static final QName CLICKANDTYPESTYLE$90 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "clickAndTypeStyle");
    private static final QName DEFAULTTABLESTYLE$92 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "defaultTableStyle");
    private static final QName EVENANDODDHEADERS$94 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "evenAndOddHeaders");
    private static final QName BOOKFOLDREVPRINTING$96 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookFoldRevPrinting");
    private static final QName BOOKFOLDPRINTING$98 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookFoldPrinting");
    private static final QName BOOKFOLDPRINTINGSHEETS$100 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookFoldPrintingSheets");
    private static final QName DRAWINGGRIDHORIZONTALSPACING$102 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "drawingGridHorizontalSpacing");
    private static final QName DRAWINGGRIDVERTICALSPACING$104 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "drawingGridVerticalSpacing");
    private static final QName DISPLAYHORIZONTALDRAWINGGRIDEVERY$106 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "displayHorizontalDrawingGridEvery");
    private static final QName DISPLAYVERTICALDRAWINGGRIDEVERY$108 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "displayVerticalDrawingGridEvery");
    private static final QName DONOTUSEMARGINSFORDRAWINGGRIDORIGIN$110 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "doNotUseMarginsForDrawingGridOrigin");
    private static final QName DRAWINGGRIDHORIZONTALORIGIN$112 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "drawingGridHorizontalOrigin");
    private static final QName DRAWINGGRIDVERTICALORIGIN$114 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "drawingGridVerticalOrigin");
    private static final QName DONOTSHADEFORMDATA$116 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "doNotShadeFormData");
    private static final QName NOPUNCTUATIONKERNING$118 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "noPunctuationKerning");
    private static final QName CHARACTERSPACINGCONTROL$120 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "characterSpacingControl");
    private static final QName PRINTTWOONONE$122 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "printTwoOnOne");
    private static final QName STRICTFIRSTANDLASTCHARS$124 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "strictFirstAndLastChars");
    private static final QName NOLINEBREAKSAFTER$126 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "noLineBreaksAfter");
    private static final QName NOLINEBREAKSBEFORE$128 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "noLineBreaksBefore");
    private static final QName SAVEPREVIEWPICTURE$130 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "savePreviewPicture");
    private static final QName DONOTVALIDATEAGAINSTSCHEMA$132 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "doNotValidateAgainstSchema");
    private static final QName SAVEINVALIDXML$134 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "saveInvalidXml");
    private static final QName IGNOREMIXEDCONTENT$136 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ignoreMixedContent");
    private static final QName ALWAYSSHOWPLACEHOLDERTEXT$138 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "alwaysShowPlaceholderText");
    private static final QName DONOTDEMARCATEINVALIDXML$140 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "doNotDemarcateInvalidXml");
    private static final QName SAVEXMLDATAONLY$142 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "saveXmlDataOnly");
    private static final QName USEXSLTWHENSAVING$144 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "useXSLTWhenSaving");
    private static final QName SAVETHROUGHXSLT$146 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "saveThroughXslt");
    private static final QName SHOWXMLTAGS$148 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "showXMLTags");
    private static final QName ALWAYSMERGEEMPTYNAMESPACE$150 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "alwaysMergeEmptyNamespace");
    private static final QName UPDATEFIELDS$152 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "updateFields");
    private static final QName HDRSHAPEDEFAULTS$154 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hdrShapeDefaults");
    private static final QName FOOTNOTEPR$156 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footnotePr");
    private static final QName ENDNOTEPR$158 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "endnotePr");
    private static final QName COMPAT$160 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "compat");
    private static final QName DOCVARS$162 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docVars");
    private static final QName RSIDS$164 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsids");
    private static final QName MATHPR$166 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mathPr");
    private static final QName UICOMPAT97TO2003$168 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "uiCompat97To2003");
    private static final QName ATTACHEDSCHEMA$170 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "attachedSchema");
    private static final QName THEMEFONTLANG$172 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "themeFontLang");
    private static final QName CLRSCHEMEMAPPING$174 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "clrSchemeMapping");
    private static final QName DONOTINCLUDESUBDOCSINSTATS$176 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "doNotIncludeSubdocsInStats");
    private static final QName DONOTAUTOCOMPRESSPICTURES$178 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "doNotAutoCompressPictures");
    private static final QName FORCEUPGRADE$180 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "forceUpgrade");
    private static final QName CAPTIONS$182 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "captions");
    private static final QName READMODEINKLOCKDOWN$184 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "readModeInkLockDown");
    private static final QName SMARTTAGTYPE$186 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "smartTagType");
    private static final QName SCHEMALIBRARY$188 = new QName("http://schemas.openxmlformats.org/schemaLibrary/2006/main", "schemaLibrary");
    private static final QName SHAPEDEFAULTS$190 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shapeDefaults");
    private static final QName DONOTEMBEDSMARTTAGS$192 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "doNotEmbedSmartTags");
    private static final QName DECIMALSYMBOL$194 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "decimalSymbol");
    private static final QName LISTSEPARATOR$196 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "listSeparator");

    public CTSettingsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWriteProtection getWriteProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWriteProtection cTWriteProtection = null;
            cTWriteProtection = (CTWriteProtection)this.get_store().find_element_user(WRITEPROTECTION$0, 0);
            if (cTWriteProtection == null) {
                return null;
            }
            return cTWriteProtection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWriteProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WRITEPROTECTION$0) != 0;
        }
    }

    @Override
    public void setWriteProtection(CTWriteProtection cTWriteProtection) {
        this.generatedSetterHelperImpl((XmlObject)cTWriteProtection, WRITEPROTECTION$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWriteProtection addNewWriteProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWriteProtection cTWriteProtection = null;
            cTWriteProtection = (CTWriteProtection)this.get_store().add_element_user(WRITEPROTECTION$0);
            return cTWriteProtection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWriteProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WRITEPROTECTION$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTView getView() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTView cTView = null;
            cTView = (CTView)this.get_store().find_element_user(VIEW$2, 0);
            if (cTView == null) {
                return null;
            }
            return cTView;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetView() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VIEW$2) != 0;
        }
    }

    @Override
    public void setView(CTView cTView) {
        this.generatedSetterHelperImpl((XmlObject)cTView, VIEW$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTView addNewView() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTView cTView = null;
            cTView = (CTView)this.get_store().add_element_user(VIEW$2);
            return cTView;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetView() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VIEW$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTZoom getZoom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTZoom cTZoom = null;
            cTZoom = (CTZoom)((Object)this.get_store().find_element_user(ZOOM$4, 0));
            if (cTZoom == null) {
                return null;
            }
            return cTZoom;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetZoom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ZOOM$4) != 0;
        }
    }

    @Override
    public void setZoom(CTZoom cTZoom) {
        this.generatedSetterHelperImpl(cTZoom, ZOOM$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTZoom addNewZoom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTZoom cTZoom = null;
            cTZoom = (CTZoom)((Object)this.get_store().add_element_user(ZOOM$4));
            return cTZoom;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetZoom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ZOOM$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getRemovePersonalInformation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(REMOVEPERSONALINFORMATION$6, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRemovePersonalInformation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REMOVEPERSONALINFORMATION$6) != 0;
        }
    }

    @Override
    public void setRemovePersonalInformation(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, REMOVEPERSONALINFORMATION$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewRemovePersonalInformation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(REMOVEPERSONALINFORMATION$6));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRemovePersonalInformation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REMOVEPERSONALINFORMATION$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getRemoveDateAndTime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(REMOVEDATEANDTIME$8, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRemoveDateAndTime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REMOVEDATEANDTIME$8) != 0;
        }
    }

    @Override
    public void setRemoveDateAndTime(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, REMOVEDATEANDTIME$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewRemoveDateAndTime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(REMOVEDATEANDTIME$8));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRemoveDateAndTime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REMOVEDATEANDTIME$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDoNotDisplayPageBoundaries() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DONOTDISPLAYPAGEBOUNDARIES$10, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoNotDisplayPageBoundaries() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DONOTDISPLAYPAGEBOUNDARIES$10) != 0;
        }
    }

    @Override
    public void setDoNotDisplayPageBoundaries(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DONOTDISPLAYPAGEBOUNDARIES$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDoNotDisplayPageBoundaries() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DONOTDISPLAYPAGEBOUNDARIES$10));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoNotDisplayPageBoundaries() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DONOTDISPLAYPAGEBOUNDARIES$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDisplayBackgroundShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DISPLAYBACKGROUNDSHAPE$12, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDisplayBackgroundShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DISPLAYBACKGROUNDSHAPE$12) != 0;
        }
    }

    @Override
    public void setDisplayBackgroundShape(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DISPLAYBACKGROUNDSHAPE$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDisplayBackgroundShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DISPLAYBACKGROUNDSHAPE$12));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDisplayBackgroundShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DISPLAYBACKGROUNDSHAPE$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getPrintPostScriptOverText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(PRINTPOSTSCRIPTOVERTEXT$14, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrintPostScriptOverText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRINTPOSTSCRIPTOVERTEXT$14) != 0;
        }
    }

    @Override
    public void setPrintPostScriptOverText(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, PRINTPOSTSCRIPTOVERTEXT$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewPrintPostScriptOverText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(PRINTPOSTSCRIPTOVERTEXT$14));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrintPostScriptOverText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRINTPOSTSCRIPTOVERTEXT$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getPrintFractionalCharacterWidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(PRINTFRACTIONALCHARACTERWIDTH$16, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrintFractionalCharacterWidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRINTFRACTIONALCHARACTERWIDTH$16) != 0;
        }
    }

    @Override
    public void setPrintFractionalCharacterWidth(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, PRINTFRACTIONALCHARACTERWIDTH$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewPrintFractionalCharacterWidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(PRINTFRACTIONALCHARACTERWIDTH$16));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrintFractionalCharacterWidth() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRINTFRACTIONALCHARACTERWIDTH$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getPrintFormsData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(PRINTFORMSDATA$18, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrintFormsData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRINTFORMSDATA$18) != 0;
        }
    }

    @Override
    public void setPrintFormsData(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, PRINTFORMSDATA$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewPrintFormsData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(PRINTFORMSDATA$18));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrintFormsData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRINTFORMSDATA$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getEmbedTrueTypeFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(EMBEDTRUETYPEFONTS$20, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEmbedTrueTypeFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EMBEDTRUETYPEFONTS$20) != 0;
        }
    }

    @Override
    public void setEmbedTrueTypeFonts(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, EMBEDTRUETYPEFONTS$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewEmbedTrueTypeFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(EMBEDTRUETYPEFONTS$20));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEmbedTrueTypeFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EMBEDTRUETYPEFONTS$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getEmbedSystemFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(EMBEDSYSTEMFONTS$22, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEmbedSystemFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EMBEDSYSTEMFONTS$22) != 0;
        }
    }

    @Override
    public void setEmbedSystemFonts(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, EMBEDSYSTEMFONTS$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewEmbedSystemFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(EMBEDSYSTEMFONTS$22));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEmbedSystemFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EMBEDSYSTEMFONTS$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSaveSubsetFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SAVESUBSETFONTS$24, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSaveSubsetFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SAVESUBSETFONTS$24) != 0;
        }
    }

    @Override
    public void setSaveSubsetFonts(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SAVESUBSETFONTS$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSaveSubsetFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SAVESUBSETFONTS$24));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSaveSubsetFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SAVESUBSETFONTS$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSaveFormsData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SAVEFORMSDATA$26, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSaveFormsData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SAVEFORMSDATA$26) != 0;
        }
    }

    @Override
    public void setSaveFormsData(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SAVEFORMSDATA$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSaveFormsData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SAVEFORMSDATA$26));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSaveFormsData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SAVEFORMSDATA$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getMirrorMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(MIRRORMARGINS$28, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMirrorMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MIRRORMARGINS$28) != 0;
        }
    }

    @Override
    public void setMirrorMargins(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, MIRRORMARGINS$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewMirrorMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(MIRRORMARGINS$28));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMirrorMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MIRRORMARGINS$28, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getAlignBordersAndEdges() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(ALIGNBORDERSANDEDGES$30, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlignBordersAndEdges() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALIGNBORDERSANDEDGES$30) != 0;
        }
    }

    @Override
    public void setAlignBordersAndEdges(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, ALIGNBORDERSANDEDGES$30, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewAlignBordersAndEdges() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(ALIGNBORDERSANDEDGES$30));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlignBordersAndEdges() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALIGNBORDERSANDEDGES$30, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getBordersDoNotSurroundHeader() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(BORDERSDONOTSURROUNDHEADER$32, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBordersDoNotSurroundHeader() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BORDERSDONOTSURROUNDHEADER$32) != 0;
        }
    }

    @Override
    public void setBordersDoNotSurroundHeader(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, BORDERSDONOTSURROUNDHEADER$32, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewBordersDoNotSurroundHeader() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(BORDERSDONOTSURROUNDHEADER$32));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBordersDoNotSurroundHeader() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BORDERSDONOTSURROUNDHEADER$32, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getBordersDoNotSurroundFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(BORDERSDONOTSURROUNDFOOTER$34, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBordersDoNotSurroundFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BORDERSDONOTSURROUNDFOOTER$34) != 0;
        }
    }

    @Override
    public void setBordersDoNotSurroundFooter(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, BORDERSDONOTSURROUNDFOOTER$34, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewBordersDoNotSurroundFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(BORDERSDONOTSURROUNDFOOTER$34));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBordersDoNotSurroundFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BORDERSDONOTSURROUNDFOOTER$34, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getGutterAtTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(GUTTERATTOP$36, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGutterAtTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GUTTERATTOP$36) != 0;
        }
    }

    @Override
    public void setGutterAtTop(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, GUTTERATTOP$36, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewGutterAtTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(GUTTERATTOP$36));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGutterAtTop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GUTTERATTOP$36, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getHideSpellingErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(HIDESPELLINGERRORS$38, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHideSpellingErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HIDESPELLINGERRORS$38) != 0;
        }
    }

    @Override
    public void setHideSpellingErrors(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, HIDESPELLINGERRORS$38, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewHideSpellingErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(HIDESPELLINGERRORS$38));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHideSpellingErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HIDESPELLINGERRORS$38, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getHideGrammaticalErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(HIDEGRAMMATICALERRORS$40, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHideGrammaticalErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HIDEGRAMMATICALERRORS$40) != 0;
        }
    }

    @Override
    public void setHideGrammaticalErrors(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, HIDEGRAMMATICALERRORS$40, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewHideGrammaticalErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(HIDEGRAMMATICALERRORS$40));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHideGrammaticalErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HIDEGRAMMATICALERRORS$40, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTWritingStyle> getActiveWritingStyleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ActiveWritingStyleList
            extends AbstractList<CTWritingStyle> {
                ActiveWritingStyleList() {
                }

                @Override
                public CTWritingStyle get(int n) {
                    return CTSettingsImpl.this.getActiveWritingStyleArray(n);
                }

                @Override
                public CTWritingStyle set(int n, CTWritingStyle cTWritingStyle) {
                    CTWritingStyle cTWritingStyle2 = CTSettingsImpl.this.getActiveWritingStyleArray(n);
                    CTSettingsImpl.this.setActiveWritingStyleArray(n, cTWritingStyle);
                    return cTWritingStyle2;
                }

                @Override
                public void add(int n, CTWritingStyle cTWritingStyle) {
                    CTSettingsImpl.this.insertNewActiveWritingStyle(n).set((XmlObject)cTWritingStyle);
                }

                @Override
                public CTWritingStyle remove(int n) {
                    CTWritingStyle cTWritingStyle = CTSettingsImpl.this.getActiveWritingStyleArray(n);
                    CTSettingsImpl.this.removeActiveWritingStyle(n);
                    return cTWritingStyle;
                }

                @Override
                public int size() {
                    return CTSettingsImpl.this.sizeOfActiveWritingStyleArray();
                }
            }
            return new ActiveWritingStyleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTWritingStyle[] getActiveWritingStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ACTIVEWRITINGSTYLE$42, arrayList);
            CTWritingStyle[] cTWritingStyleArray = new CTWritingStyle[arrayList.size()];
            arrayList.toArray(cTWritingStyleArray);
            return cTWritingStyleArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWritingStyle getActiveWritingStyleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWritingStyle cTWritingStyle = null;
            cTWritingStyle = (CTWritingStyle)this.get_store().find_element_user(ACTIVEWRITINGSTYLE$42, n);
            if (cTWritingStyle == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTWritingStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfActiveWritingStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ACTIVEWRITINGSTYLE$42);
        }
    }

    @Override
    public void setActiveWritingStyleArray(CTWritingStyle[] cTWritingStyleArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTWritingStyleArray, ACTIVEWRITINGSTYLE$42);
    }

    @Override
    public void setActiveWritingStyleArray(int n, CTWritingStyle cTWritingStyle) {
        this.generatedSetterHelperImpl((XmlObject)cTWritingStyle, ACTIVEWRITINGSTYLE$42, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWritingStyle insertNewActiveWritingStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWritingStyle cTWritingStyle = null;
            cTWritingStyle = (CTWritingStyle)this.get_store().insert_element_user(ACTIVEWRITINGSTYLE$42, n);
            return cTWritingStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWritingStyle addNewActiveWritingStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWritingStyle cTWritingStyle = null;
            cTWritingStyle = (CTWritingStyle)this.get_store().add_element_user(ACTIVEWRITINGSTYLE$42);
            return cTWritingStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeActiveWritingStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ACTIVEWRITINGSTYLE$42, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProof getProofState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProof cTProof = null;
            cTProof = (CTProof)this.get_store().find_element_user(PROOFSTATE$44, 0);
            if (cTProof == null) {
                return null;
            }
            return cTProof;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetProofState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PROOFSTATE$44) != 0;
        }
    }

    @Override
    public void setProofState(CTProof cTProof) {
        this.generatedSetterHelperImpl((XmlObject)cTProof, PROOFSTATE$44, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProof addNewProofState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProof cTProof = null;
            cTProof = (CTProof)this.get_store().add_element_user(PROOFSTATE$44);
            return cTProof;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetProofState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PROOFSTATE$44, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getFormsDesign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(FORMSDESIGN$46, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFormsDesign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FORMSDESIGN$46) != 0;
        }
    }

    @Override
    public void setFormsDesign(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, FORMSDESIGN$46, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewFormsDesign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(FORMSDESIGN$46));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFormsDesign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FORMSDESIGN$46, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel getAttachedTemplate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)((Object)this.get_store().find_element_user(ATTACHEDTEMPLATE$48, 0));
            if (cTRel == null) {
                return null;
            }
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAttachedTemplate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTACHEDTEMPLATE$48) != 0;
        }
    }

    @Override
    public void setAttachedTemplate(CTRel cTRel) {
        this.generatedSetterHelperImpl(cTRel, ATTACHEDTEMPLATE$48, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel addNewAttachedTemplate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)((Object)this.get_store().add_element_user(ATTACHEDTEMPLATE$48));
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAttachedTemplate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTACHEDTEMPLATE$48, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getLinkStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(LINKSTYLES$50, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLinkStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LINKSTYLES$50) != 0;
        }
    }

    @Override
    public void setLinkStyles(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, LINKSTYLES$50, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewLinkStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(LINKSTYLES$50));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLinkStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LINKSTYLES$50, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShortHexNumber getStylePaneFormatFilter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShortHexNumber cTShortHexNumber = null;
            cTShortHexNumber = (CTShortHexNumber)this.get_store().find_element_user(STYLEPANEFORMATFILTER$52, 0);
            if (cTShortHexNumber == null) {
                return null;
            }
            return cTShortHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStylePaneFormatFilter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STYLEPANEFORMATFILTER$52) != 0;
        }
    }

    @Override
    public void setStylePaneFormatFilter(CTShortHexNumber cTShortHexNumber) {
        this.generatedSetterHelperImpl((XmlObject)cTShortHexNumber, STYLEPANEFORMATFILTER$52, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShortHexNumber addNewStylePaneFormatFilter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShortHexNumber cTShortHexNumber = null;
            cTShortHexNumber = (CTShortHexNumber)this.get_store().add_element_user(STYLEPANEFORMATFILTER$52);
            return cTShortHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStylePaneFormatFilter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STYLEPANEFORMATFILTER$52, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShortHexNumber getStylePaneSortMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShortHexNumber cTShortHexNumber = null;
            cTShortHexNumber = (CTShortHexNumber)this.get_store().find_element_user(STYLEPANESORTMETHOD$54, 0);
            if (cTShortHexNumber == null) {
                return null;
            }
            return cTShortHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStylePaneSortMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STYLEPANESORTMETHOD$54) != 0;
        }
    }

    @Override
    public void setStylePaneSortMethod(CTShortHexNumber cTShortHexNumber) {
        this.generatedSetterHelperImpl((XmlObject)cTShortHexNumber, STYLEPANESORTMETHOD$54, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShortHexNumber addNewStylePaneSortMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShortHexNumber cTShortHexNumber = null;
            cTShortHexNumber = (CTShortHexNumber)this.get_store().add_element_user(STYLEPANESORTMETHOD$54);
            return cTShortHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStylePaneSortMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STYLEPANESORTMETHOD$54, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocType getDocumentType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocType cTDocType = null;
            cTDocType = (CTDocType)this.get_store().find_element_user(DOCUMENTTYPE$56, 0);
            if (cTDocType == null) {
                return null;
            }
            return cTDocType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDocumentType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCUMENTTYPE$56) != 0;
        }
    }

    @Override
    public void setDocumentType(CTDocType cTDocType) {
        this.generatedSetterHelperImpl((XmlObject)cTDocType, DOCUMENTTYPE$56, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocType addNewDocumentType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocType cTDocType = null;
            cTDocType = (CTDocType)this.get_store().add_element_user(DOCUMENTTYPE$56);
            return cTDocType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDocumentType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCUMENTTYPE$56, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMailMerge getMailMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMailMerge cTMailMerge = null;
            cTMailMerge = (CTMailMerge)this.get_store().find_element_user(MAILMERGE$58, 0);
            if (cTMailMerge == null) {
                return null;
            }
            return cTMailMerge;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMailMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAILMERGE$58) != 0;
        }
    }

    @Override
    public void setMailMerge(CTMailMerge cTMailMerge) {
        this.generatedSetterHelperImpl((XmlObject)cTMailMerge, MAILMERGE$58, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMailMerge addNewMailMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMailMerge cTMailMerge = null;
            cTMailMerge = (CTMailMerge)this.get_store().add_element_user(MAILMERGE$58);
            return cTMailMerge;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMailMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAILMERGE$58, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChangesView getRevisionView() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChangesView cTTrackChangesView = null;
            cTTrackChangesView = (CTTrackChangesView)this.get_store().find_element_user(REVISIONVIEW$60, 0);
            if (cTTrackChangesView == null) {
                return null;
            }
            return cTTrackChangesView;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRevisionView() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REVISIONVIEW$60) != 0;
        }
    }

    @Override
    public void setRevisionView(CTTrackChangesView cTTrackChangesView) {
        this.generatedSetterHelperImpl((XmlObject)cTTrackChangesView, REVISIONVIEW$60, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChangesView addNewRevisionView() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChangesView cTTrackChangesView = null;
            cTTrackChangesView = (CTTrackChangesView)this.get_store().add_element_user(REVISIONVIEW$60);
            return cTTrackChangesView;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRevisionView() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REVISIONVIEW$60, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getTrackRevisions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(TRACKREVISIONS$62, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTrackRevisions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TRACKREVISIONS$62) != 0;
        }
    }

    @Override
    public void setTrackRevisions(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, TRACKREVISIONS$62, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewTrackRevisions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(TRACKREVISIONS$62));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTrackRevisions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TRACKREVISIONS$62, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDoNotTrackMoves() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DONOTTRACKMOVES$64, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoNotTrackMoves() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DONOTTRACKMOVES$64) != 0;
        }
    }

    @Override
    public void setDoNotTrackMoves(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DONOTTRACKMOVES$64, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDoNotTrackMoves() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DONOTTRACKMOVES$64));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoNotTrackMoves() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DONOTTRACKMOVES$64, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDoNotTrackFormatting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DONOTTRACKFORMATTING$66, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoNotTrackFormatting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DONOTTRACKFORMATTING$66) != 0;
        }
    }

    @Override
    public void setDoNotTrackFormatting(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DONOTTRACKFORMATTING$66, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDoNotTrackFormatting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DONOTTRACKFORMATTING$66));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoNotTrackFormatting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DONOTTRACKFORMATTING$66, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocProtect getDocumentProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocProtect cTDocProtect = null;
            cTDocProtect = (CTDocProtect)((Object)this.get_store().find_element_user(DOCUMENTPROTECTION$68, 0));
            if (cTDocProtect == null) {
                return null;
            }
            return cTDocProtect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDocumentProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCUMENTPROTECTION$68) != 0;
        }
    }

    @Override
    public void setDocumentProtection(CTDocProtect cTDocProtect) {
        this.generatedSetterHelperImpl(cTDocProtect, DOCUMENTPROTECTION$68, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocProtect addNewDocumentProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocProtect cTDocProtect = null;
            cTDocProtect = (CTDocProtect)((Object)this.get_store().add_element_user(DOCUMENTPROTECTION$68));
            return cTDocProtect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDocumentProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCUMENTPROTECTION$68, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getAutoFormatOverride() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(AUTOFORMATOVERRIDE$70, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAutoFormatOverride() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOFORMATOVERRIDE$70) != 0;
        }
    }

    @Override
    public void setAutoFormatOverride(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, AUTOFORMATOVERRIDE$70, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewAutoFormatOverride() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(AUTOFORMATOVERRIDE$70));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAutoFormatOverride() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOFORMATOVERRIDE$70, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getStyleLockTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(STYLELOCKTHEME$72, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStyleLockTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STYLELOCKTHEME$72) != 0;
        }
    }

    @Override
    public void setStyleLockTheme(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, STYLELOCKTHEME$72, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewStyleLockTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(STYLELOCKTHEME$72));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStyleLockTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STYLELOCKTHEME$72, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getStyleLockQFSet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(STYLELOCKQFSET$74, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStyleLockQFSet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STYLELOCKQFSET$74) != 0;
        }
    }

    @Override
    public void setStyleLockQFSet(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, STYLELOCKQFSET$74, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewStyleLockQFSet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(STYLELOCKQFSET$74));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStyleLockQFSet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STYLELOCKQFSET$74, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure getDefaultTabStop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().find_element_user(DEFAULTTABSTOP$76, 0);
            if (cTTwipsMeasure == null) {
                return null;
            }
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefaultTabStop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEFAULTTABSTOP$76) != 0;
        }
    }

    @Override
    public void setDefaultTabStop(CTTwipsMeasure cTTwipsMeasure) {
        this.generatedSetterHelperImpl((XmlObject)cTTwipsMeasure, DEFAULTTABSTOP$76, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure addNewDefaultTabStop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().add_element_user(DEFAULTTABSTOP$76);
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefaultTabStop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEFAULTTABSTOP$76, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getAutoHyphenation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(AUTOHYPHENATION$78, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAutoHyphenation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOHYPHENATION$78) != 0;
        }
    }

    @Override
    public void setAutoHyphenation(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, AUTOHYPHENATION$78, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewAutoHyphenation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(AUTOHYPHENATION$78));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAutoHyphenation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOHYPHENATION$78, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getConsecutiveHyphenLimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(CONSECUTIVEHYPHENLIMIT$80, 0));
            if (cTDecimalNumber == null) {
                return null;
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetConsecutiveHyphenLimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONSECUTIVEHYPHENLIMIT$80) != 0;
        }
    }

    @Override
    public void setConsecutiveHyphenLimit(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, CONSECUTIVEHYPHENLIMIT$80, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewConsecutiveHyphenLimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(CONSECUTIVEHYPHENLIMIT$80));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetConsecutiveHyphenLimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONSECUTIVEHYPHENLIMIT$80, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure getHyphenationZone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().find_element_user(HYPHENATIONZONE$82, 0);
            if (cTTwipsMeasure == null) {
                return null;
            }
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHyphenationZone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HYPHENATIONZONE$82) != 0;
        }
    }

    @Override
    public void setHyphenationZone(CTTwipsMeasure cTTwipsMeasure) {
        this.generatedSetterHelperImpl((XmlObject)cTTwipsMeasure, HYPHENATIONZONE$82, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure addNewHyphenationZone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().add_element_user(HYPHENATIONZONE$82);
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHyphenationZone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HYPHENATIONZONE$82, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDoNotHyphenateCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DONOTHYPHENATECAPS$84, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoNotHyphenateCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DONOTHYPHENATECAPS$84) != 0;
        }
    }

    @Override
    public void setDoNotHyphenateCaps(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DONOTHYPHENATECAPS$84, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDoNotHyphenateCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DONOTHYPHENATECAPS$84));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoNotHyphenateCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DONOTHYPHENATECAPS$84, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getShowEnvelope() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SHOWENVELOPE$86, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetShowEnvelope() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHOWENVELOPE$86) != 0;
        }
    }

    @Override
    public void setShowEnvelope(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SHOWENVELOPE$86, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewShowEnvelope() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SHOWENVELOPE$86));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowEnvelope() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHOWENVELOPE$86, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getSummaryLength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(SUMMARYLENGTH$88, 0));
            if (cTDecimalNumber == null) {
                return null;
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSummaryLength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SUMMARYLENGTH$88) != 0;
        }
    }

    @Override
    public void setSummaryLength(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, SUMMARYLENGTH$88, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewSummaryLength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(SUMMARYLENGTH$88));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSummaryLength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SUMMARYLENGTH$88, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getClickAndTypeStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(CLICKANDTYPESTYLE$90, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetClickAndTypeStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLICKANDTYPESTYLE$90) != 0;
        }
    }

    @Override
    public void setClickAndTypeStyle(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, CLICKANDTYPESTYLE$90, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewClickAndTypeStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(CLICKANDTYPESTYLE$90));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetClickAndTypeStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLICKANDTYPESTYLE$90, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getDefaultTableStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(DEFAULTTABLESTYLE$92, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefaultTableStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEFAULTTABLESTYLE$92) != 0;
        }
    }

    @Override
    public void setDefaultTableStyle(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, DEFAULTTABLESTYLE$92, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewDefaultTableStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(DEFAULTTABLESTYLE$92));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefaultTableStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEFAULTTABLESTYLE$92, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getEvenAndOddHeaders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(EVENANDODDHEADERS$94, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEvenAndOddHeaders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EVENANDODDHEADERS$94) != 0;
        }
    }

    @Override
    public void setEvenAndOddHeaders(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, EVENANDODDHEADERS$94, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewEvenAndOddHeaders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(EVENANDODDHEADERS$94));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEvenAndOddHeaders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EVENANDODDHEADERS$94, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getBookFoldRevPrinting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(BOOKFOLDREVPRINTING$96, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBookFoldRevPrinting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOOKFOLDREVPRINTING$96) != 0;
        }
    }

    @Override
    public void setBookFoldRevPrinting(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, BOOKFOLDREVPRINTING$96, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewBookFoldRevPrinting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(BOOKFOLDREVPRINTING$96));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBookFoldRevPrinting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOOKFOLDREVPRINTING$96, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getBookFoldPrinting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(BOOKFOLDPRINTING$98, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBookFoldPrinting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOOKFOLDPRINTING$98) != 0;
        }
    }

    @Override
    public void setBookFoldPrinting(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, BOOKFOLDPRINTING$98, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewBookFoldPrinting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(BOOKFOLDPRINTING$98));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBookFoldPrinting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOOKFOLDPRINTING$98, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getBookFoldPrintingSheets() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(BOOKFOLDPRINTINGSHEETS$100, 0));
            if (cTDecimalNumber == null) {
                return null;
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBookFoldPrintingSheets() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BOOKFOLDPRINTINGSHEETS$100) != 0;
        }
    }

    @Override
    public void setBookFoldPrintingSheets(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, BOOKFOLDPRINTINGSHEETS$100, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewBookFoldPrintingSheets() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(BOOKFOLDPRINTINGSHEETS$100));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBookFoldPrintingSheets() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BOOKFOLDPRINTINGSHEETS$100, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure getDrawingGridHorizontalSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().find_element_user(DRAWINGGRIDHORIZONTALSPACING$102, 0);
            if (cTTwipsMeasure == null) {
                return null;
            }
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDrawingGridHorizontalSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DRAWINGGRIDHORIZONTALSPACING$102) != 0;
        }
    }

    @Override
    public void setDrawingGridHorizontalSpacing(CTTwipsMeasure cTTwipsMeasure) {
        this.generatedSetterHelperImpl((XmlObject)cTTwipsMeasure, DRAWINGGRIDHORIZONTALSPACING$102, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure addNewDrawingGridHorizontalSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().add_element_user(DRAWINGGRIDHORIZONTALSPACING$102);
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDrawingGridHorizontalSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DRAWINGGRIDHORIZONTALSPACING$102, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure getDrawingGridVerticalSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().find_element_user(DRAWINGGRIDVERTICALSPACING$104, 0);
            if (cTTwipsMeasure == null) {
                return null;
            }
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDrawingGridVerticalSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DRAWINGGRIDVERTICALSPACING$104) != 0;
        }
    }

    @Override
    public void setDrawingGridVerticalSpacing(CTTwipsMeasure cTTwipsMeasure) {
        this.generatedSetterHelperImpl((XmlObject)cTTwipsMeasure, DRAWINGGRIDVERTICALSPACING$104, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure addNewDrawingGridVerticalSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().add_element_user(DRAWINGGRIDVERTICALSPACING$104);
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDrawingGridVerticalSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DRAWINGGRIDVERTICALSPACING$104, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getDisplayHorizontalDrawingGridEvery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(DISPLAYHORIZONTALDRAWINGGRIDEVERY$106, 0));
            if (cTDecimalNumber == null) {
                return null;
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDisplayHorizontalDrawingGridEvery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DISPLAYHORIZONTALDRAWINGGRIDEVERY$106) != 0;
        }
    }

    @Override
    public void setDisplayHorizontalDrawingGridEvery(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, DISPLAYHORIZONTALDRAWINGGRIDEVERY$106, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewDisplayHorizontalDrawingGridEvery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(DISPLAYHORIZONTALDRAWINGGRIDEVERY$106));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDisplayHorizontalDrawingGridEvery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DISPLAYHORIZONTALDRAWINGGRIDEVERY$106, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getDisplayVerticalDrawingGridEvery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(DISPLAYVERTICALDRAWINGGRIDEVERY$108, 0));
            if (cTDecimalNumber == null) {
                return null;
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDisplayVerticalDrawingGridEvery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DISPLAYVERTICALDRAWINGGRIDEVERY$108) != 0;
        }
    }

    @Override
    public void setDisplayVerticalDrawingGridEvery(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, DISPLAYVERTICALDRAWINGGRIDEVERY$108, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewDisplayVerticalDrawingGridEvery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(DISPLAYVERTICALDRAWINGGRIDEVERY$108));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDisplayVerticalDrawingGridEvery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DISPLAYVERTICALDRAWINGGRIDEVERY$108, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDoNotUseMarginsForDrawingGridOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DONOTUSEMARGINSFORDRAWINGGRIDORIGIN$110, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoNotUseMarginsForDrawingGridOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DONOTUSEMARGINSFORDRAWINGGRIDORIGIN$110) != 0;
        }
    }

    @Override
    public void setDoNotUseMarginsForDrawingGridOrigin(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DONOTUSEMARGINSFORDRAWINGGRIDORIGIN$110, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDoNotUseMarginsForDrawingGridOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DONOTUSEMARGINSFORDRAWINGGRIDORIGIN$110));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoNotUseMarginsForDrawingGridOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DONOTUSEMARGINSFORDRAWINGGRIDORIGIN$110, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure getDrawingGridHorizontalOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().find_element_user(DRAWINGGRIDHORIZONTALORIGIN$112, 0);
            if (cTTwipsMeasure == null) {
                return null;
            }
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDrawingGridHorizontalOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DRAWINGGRIDHORIZONTALORIGIN$112) != 0;
        }
    }

    @Override
    public void setDrawingGridHorizontalOrigin(CTTwipsMeasure cTTwipsMeasure) {
        this.generatedSetterHelperImpl((XmlObject)cTTwipsMeasure, DRAWINGGRIDHORIZONTALORIGIN$112, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure addNewDrawingGridHorizontalOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().add_element_user(DRAWINGGRIDHORIZONTALORIGIN$112);
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDrawingGridHorizontalOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DRAWINGGRIDHORIZONTALORIGIN$112, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure getDrawingGridVerticalOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().find_element_user(DRAWINGGRIDVERTICALORIGIN$114, 0);
            if (cTTwipsMeasure == null) {
                return null;
            }
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDrawingGridVerticalOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DRAWINGGRIDVERTICALORIGIN$114) != 0;
        }
    }

    @Override
    public void setDrawingGridVerticalOrigin(CTTwipsMeasure cTTwipsMeasure) {
        this.generatedSetterHelperImpl((XmlObject)cTTwipsMeasure, DRAWINGGRIDVERTICALORIGIN$114, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwipsMeasure addNewDrawingGridVerticalOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwipsMeasure cTTwipsMeasure = null;
            cTTwipsMeasure = (CTTwipsMeasure)this.get_store().add_element_user(DRAWINGGRIDVERTICALORIGIN$114);
            return cTTwipsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDrawingGridVerticalOrigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DRAWINGGRIDVERTICALORIGIN$114, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDoNotShadeFormData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DONOTSHADEFORMDATA$116, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoNotShadeFormData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DONOTSHADEFORMDATA$116) != 0;
        }
    }

    @Override
    public void setDoNotShadeFormData(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DONOTSHADEFORMDATA$116, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDoNotShadeFormData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DONOTSHADEFORMDATA$116));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoNotShadeFormData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DONOTSHADEFORMDATA$116, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getNoPunctuationKerning() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(NOPUNCTUATIONKERNING$118, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNoPunctuationKerning() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOPUNCTUATIONKERNING$118) != 0;
        }
    }

    @Override
    public void setNoPunctuationKerning(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, NOPUNCTUATIONKERNING$118, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewNoPunctuationKerning() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(NOPUNCTUATIONKERNING$118));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNoPunctuationKerning() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOPUNCTUATIONKERNING$118, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCharacterSpacing getCharacterSpacingControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCharacterSpacing cTCharacterSpacing = null;
            cTCharacterSpacing = (CTCharacterSpacing)this.get_store().find_element_user(CHARACTERSPACINGCONTROL$120, 0);
            if (cTCharacterSpacing == null) {
                return null;
            }
            return cTCharacterSpacing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCharacterSpacingControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CHARACTERSPACINGCONTROL$120) != 0;
        }
    }

    @Override
    public void setCharacterSpacingControl(CTCharacterSpacing cTCharacterSpacing) {
        this.generatedSetterHelperImpl((XmlObject)cTCharacterSpacing, CHARACTERSPACINGCONTROL$120, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCharacterSpacing addNewCharacterSpacingControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCharacterSpacing cTCharacterSpacing = null;
            cTCharacterSpacing = (CTCharacterSpacing)this.get_store().add_element_user(CHARACTERSPACINGCONTROL$120);
            return cTCharacterSpacing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCharacterSpacingControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CHARACTERSPACINGCONTROL$120, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getPrintTwoOnOne() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(PRINTTWOONONE$122, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrintTwoOnOne() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRINTTWOONONE$122) != 0;
        }
    }

    @Override
    public void setPrintTwoOnOne(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, PRINTTWOONONE$122, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewPrintTwoOnOne() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(PRINTTWOONONE$122));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrintTwoOnOne() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRINTTWOONONE$122, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getStrictFirstAndLastChars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(STRICTFIRSTANDLASTCHARS$124, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStrictFirstAndLastChars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STRICTFIRSTANDLASTCHARS$124) != 0;
        }
    }

    @Override
    public void setStrictFirstAndLastChars(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, STRICTFIRSTANDLASTCHARS$124, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewStrictFirstAndLastChars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(STRICTFIRSTANDLASTCHARS$124));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStrictFirstAndLastChars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STRICTFIRSTANDLASTCHARS$124, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKinsoku getNoLineBreaksAfter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKinsoku cTKinsoku = null;
            cTKinsoku = (CTKinsoku)this.get_store().find_element_user(NOLINEBREAKSAFTER$126, 0);
            if (cTKinsoku == null) {
                return null;
            }
            return cTKinsoku;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNoLineBreaksAfter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOLINEBREAKSAFTER$126) != 0;
        }
    }

    @Override
    public void setNoLineBreaksAfter(CTKinsoku cTKinsoku) {
        this.generatedSetterHelperImpl((XmlObject)cTKinsoku, NOLINEBREAKSAFTER$126, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKinsoku addNewNoLineBreaksAfter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKinsoku cTKinsoku = null;
            cTKinsoku = (CTKinsoku)this.get_store().add_element_user(NOLINEBREAKSAFTER$126);
            return cTKinsoku;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNoLineBreaksAfter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOLINEBREAKSAFTER$126, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKinsoku getNoLineBreaksBefore() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKinsoku cTKinsoku = null;
            cTKinsoku = (CTKinsoku)this.get_store().find_element_user(NOLINEBREAKSBEFORE$128, 0);
            if (cTKinsoku == null) {
                return null;
            }
            return cTKinsoku;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNoLineBreaksBefore() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOLINEBREAKSBEFORE$128) != 0;
        }
    }

    @Override
    public void setNoLineBreaksBefore(CTKinsoku cTKinsoku) {
        this.generatedSetterHelperImpl((XmlObject)cTKinsoku, NOLINEBREAKSBEFORE$128, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKinsoku addNewNoLineBreaksBefore() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKinsoku cTKinsoku = null;
            cTKinsoku = (CTKinsoku)this.get_store().add_element_user(NOLINEBREAKSBEFORE$128);
            return cTKinsoku;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNoLineBreaksBefore() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOLINEBREAKSBEFORE$128, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSavePreviewPicture() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SAVEPREVIEWPICTURE$130, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSavePreviewPicture() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SAVEPREVIEWPICTURE$130) != 0;
        }
    }

    @Override
    public void setSavePreviewPicture(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SAVEPREVIEWPICTURE$130, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSavePreviewPicture() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SAVEPREVIEWPICTURE$130));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSavePreviewPicture() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SAVEPREVIEWPICTURE$130, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDoNotValidateAgainstSchema() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DONOTVALIDATEAGAINSTSCHEMA$132, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoNotValidateAgainstSchema() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DONOTVALIDATEAGAINSTSCHEMA$132) != 0;
        }
    }

    @Override
    public void setDoNotValidateAgainstSchema(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DONOTVALIDATEAGAINSTSCHEMA$132, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDoNotValidateAgainstSchema() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DONOTVALIDATEAGAINSTSCHEMA$132));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoNotValidateAgainstSchema() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DONOTVALIDATEAGAINSTSCHEMA$132, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSaveInvalidXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SAVEINVALIDXML$134, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSaveInvalidXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SAVEINVALIDXML$134) != 0;
        }
    }

    @Override
    public void setSaveInvalidXml(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SAVEINVALIDXML$134, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSaveInvalidXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SAVEINVALIDXML$134));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSaveInvalidXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SAVEINVALIDXML$134, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getIgnoreMixedContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(IGNOREMIXEDCONTENT$136, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetIgnoreMixedContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(IGNOREMIXEDCONTENT$136) != 0;
        }
    }

    @Override
    public void setIgnoreMixedContent(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, IGNOREMIXEDCONTENT$136, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewIgnoreMixedContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(IGNOREMIXEDCONTENT$136));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetIgnoreMixedContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(IGNOREMIXEDCONTENT$136, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getAlwaysShowPlaceholderText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(ALWAYSSHOWPLACEHOLDERTEXT$138, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlwaysShowPlaceholderText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALWAYSSHOWPLACEHOLDERTEXT$138) != 0;
        }
    }

    @Override
    public void setAlwaysShowPlaceholderText(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, ALWAYSSHOWPLACEHOLDERTEXT$138, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewAlwaysShowPlaceholderText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(ALWAYSSHOWPLACEHOLDERTEXT$138));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlwaysShowPlaceholderText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALWAYSSHOWPLACEHOLDERTEXT$138, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDoNotDemarcateInvalidXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DONOTDEMARCATEINVALIDXML$140, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoNotDemarcateInvalidXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DONOTDEMARCATEINVALIDXML$140) != 0;
        }
    }

    @Override
    public void setDoNotDemarcateInvalidXml(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DONOTDEMARCATEINVALIDXML$140, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDoNotDemarcateInvalidXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DONOTDEMARCATEINVALIDXML$140));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoNotDemarcateInvalidXml() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DONOTDEMARCATEINVALIDXML$140, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSaveXmlDataOnly() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SAVEXMLDATAONLY$142, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSaveXmlDataOnly() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SAVEXMLDATAONLY$142) != 0;
        }
    }

    @Override
    public void setSaveXmlDataOnly(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SAVEXMLDATAONLY$142, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSaveXmlDataOnly() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SAVEXMLDATAONLY$142));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSaveXmlDataOnly() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SAVEXMLDATAONLY$142, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getUseXSLTWhenSaving() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(USEXSLTWHENSAVING$144, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUseXSLTWhenSaving() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(USEXSLTWHENSAVING$144) != 0;
        }
    }

    @Override
    public void setUseXSLTWhenSaving(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, USEXSLTWHENSAVING$144, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewUseXSLTWhenSaving() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(USEXSLTWHENSAVING$144));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUseXSLTWhenSaving() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(USEXSLTWHENSAVING$144, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSaveThroughXslt getSaveThroughXslt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSaveThroughXslt cTSaveThroughXslt = null;
            cTSaveThroughXslt = (CTSaveThroughXslt)this.get_store().find_element_user(SAVETHROUGHXSLT$146, 0);
            if (cTSaveThroughXslt == null) {
                return null;
            }
            return cTSaveThroughXslt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSaveThroughXslt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SAVETHROUGHXSLT$146) != 0;
        }
    }

    @Override
    public void setSaveThroughXslt(CTSaveThroughXslt cTSaveThroughXslt) {
        this.generatedSetterHelperImpl((XmlObject)cTSaveThroughXslt, SAVETHROUGHXSLT$146, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSaveThroughXslt addNewSaveThroughXslt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSaveThroughXslt cTSaveThroughXslt = null;
            cTSaveThroughXslt = (CTSaveThroughXslt)this.get_store().add_element_user(SAVETHROUGHXSLT$146);
            return cTSaveThroughXslt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSaveThroughXslt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SAVETHROUGHXSLT$146, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getShowXMLTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SHOWXMLTAGS$148, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetShowXMLTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHOWXMLTAGS$148) != 0;
        }
    }

    @Override
    public void setShowXMLTags(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SHOWXMLTAGS$148, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewShowXMLTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SHOWXMLTAGS$148));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowXMLTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHOWXMLTAGS$148, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getAlwaysMergeEmptyNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(ALWAYSMERGEEMPTYNAMESPACE$150, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlwaysMergeEmptyNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALWAYSMERGEEMPTYNAMESPACE$150) != 0;
        }
    }

    @Override
    public void setAlwaysMergeEmptyNamespace(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, ALWAYSMERGEEMPTYNAMESPACE$150, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewAlwaysMergeEmptyNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(ALWAYSMERGEEMPTYNAMESPACE$150));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlwaysMergeEmptyNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALWAYSMERGEEMPTYNAMESPACE$150, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getUpdateFields() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(UPDATEFIELDS$152, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUpdateFields() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UPDATEFIELDS$152) != 0;
        }
    }

    @Override
    public void setUpdateFields(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, UPDATEFIELDS$152, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewUpdateFields() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(UPDATEFIELDS$152));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUpdateFields() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UPDATEFIELDS$152, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeDefaults getHdrShapeDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeDefaults cTShapeDefaults = null;
            cTShapeDefaults = (CTShapeDefaults)this.get_store().find_element_user(HDRSHAPEDEFAULTS$154, 0);
            if (cTShapeDefaults == null) {
                return null;
            }
            return cTShapeDefaults;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHdrShapeDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HDRSHAPEDEFAULTS$154) != 0;
        }
    }

    @Override
    public void setHdrShapeDefaults(CTShapeDefaults cTShapeDefaults) {
        this.generatedSetterHelperImpl((XmlObject)cTShapeDefaults, HDRSHAPEDEFAULTS$154, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeDefaults addNewHdrShapeDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeDefaults cTShapeDefaults = null;
            cTShapeDefaults = (CTShapeDefaults)this.get_store().add_element_user(HDRSHAPEDEFAULTS$154);
            return cTShapeDefaults;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHdrShapeDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HDRSHAPEDEFAULTS$154, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnDocProps getFootnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnDocProps cTFtnDocProps = null;
            cTFtnDocProps = (CTFtnDocProps)this.get_store().find_element_user(FOOTNOTEPR$156, 0);
            if (cTFtnDocProps == null) {
                return null;
            }
            return cTFtnDocProps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFootnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FOOTNOTEPR$156) != 0;
        }
    }

    @Override
    public void setFootnotePr(CTFtnDocProps cTFtnDocProps) {
        this.generatedSetterHelperImpl((XmlObject)cTFtnDocProps, FOOTNOTEPR$156, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFtnDocProps addNewFootnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFtnDocProps cTFtnDocProps = null;
            cTFtnDocProps = (CTFtnDocProps)this.get_store().add_element_user(FOOTNOTEPR$156);
            return cTFtnDocProps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFootnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FOOTNOTEPR$156, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEdnDocProps getEndnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEdnDocProps cTEdnDocProps = null;
            cTEdnDocProps = (CTEdnDocProps)this.get_store().find_element_user(ENDNOTEPR$158, 0);
            if (cTEdnDocProps == null) {
                return null;
            }
            return cTEdnDocProps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEndnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENDNOTEPR$158) != 0;
        }
    }

    @Override
    public void setEndnotePr(CTEdnDocProps cTEdnDocProps) {
        this.generatedSetterHelperImpl((XmlObject)cTEdnDocProps, ENDNOTEPR$158, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEdnDocProps addNewEndnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEdnDocProps cTEdnDocProps = null;
            cTEdnDocProps = (CTEdnDocProps)this.get_store().add_element_user(ENDNOTEPR$158);
            return cTEdnDocProps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEndnotePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENDNOTEPR$158, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCompat getCompat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCompat cTCompat = null;
            cTCompat = (CTCompat)this.get_store().find_element_user(COMPAT$160, 0);
            if (cTCompat == null) {
                return null;
            }
            return cTCompat;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCompat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMPAT$160) != 0;
        }
    }

    @Override
    public void setCompat(CTCompat cTCompat) {
        this.generatedSetterHelperImpl((XmlObject)cTCompat, COMPAT$160, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCompat addNewCompat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCompat cTCompat = null;
            cTCompat = (CTCompat)this.get_store().add_element_user(COMPAT$160);
            return cTCompat;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCompat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMPAT$160, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocVars getDocVars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocVars cTDocVars = null;
            cTDocVars = (CTDocVars)this.get_store().find_element_user(DOCVARS$162, 0);
            if (cTDocVars == null) {
                return null;
            }
            return cTDocVars;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDocVars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCVARS$162) != 0;
        }
    }

    @Override
    public void setDocVars(CTDocVars cTDocVars) {
        this.generatedSetterHelperImpl((XmlObject)cTDocVars, DOCVARS$162, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocVars addNewDocVars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocVars cTDocVars = null;
            cTDocVars = (CTDocVars)this.get_store().add_element_user(DOCVARS$162);
            return cTDocVars;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDocVars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCVARS$162, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocRsids getRsids() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocRsids cTDocRsids = null;
            cTDocRsids = (CTDocRsids)this.get_store().find_element_user(RSIDS$164, 0);
            if (cTDocRsids == null) {
                return null;
            }
            return cTDocRsids;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRsids() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RSIDS$164) != 0;
        }
    }

    @Override
    public void setRsids(CTDocRsids cTDocRsids) {
        this.generatedSetterHelperImpl((XmlObject)cTDocRsids, RSIDS$164, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocRsids addNewRsids() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocRsids cTDocRsids = null;
            cTDocRsids = (CTDocRsids)this.get_store().add_element_user(RSIDS$164);
            return cTDocRsids;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRsids() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RSIDS$164, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMathPr getMathPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMathPr cTMathPr = null;
            cTMathPr = (CTMathPr)this.get_store().find_element_user(MATHPR$166, 0);
            if (cTMathPr == null) {
                return null;
            }
            return cTMathPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMathPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MATHPR$166) != 0;
        }
    }

    @Override
    public void setMathPr(CTMathPr cTMathPr) {
        this.generatedSetterHelperImpl((XmlObject)cTMathPr, MATHPR$166, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMathPr addNewMathPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMathPr cTMathPr = null;
            cTMathPr = (CTMathPr)this.get_store().add_element_user(MATHPR$166);
            return cTMathPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMathPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MATHPR$166, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getUiCompat97To2003() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(UICOMPAT97TO2003$168, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUiCompat97To2003() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UICOMPAT97TO2003$168) != 0;
        }
    }

    @Override
    public void setUiCompat97To2003(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, UICOMPAT97TO2003$168, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewUiCompat97To2003() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(UICOMPAT97TO2003$168));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUiCompat97To2003() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UICOMPAT97TO2003$168, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTString> getAttachedSchemaList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AttachedSchemaList
            extends AbstractList<CTString> {
                AttachedSchemaList() {
                }

                @Override
                public CTString get(int n) {
                    return CTSettingsImpl.this.getAttachedSchemaArray(n);
                }

                @Override
                public CTString set(int n, CTString cTString) {
                    CTString cTString2 = CTSettingsImpl.this.getAttachedSchemaArray(n);
                    CTSettingsImpl.this.setAttachedSchemaArray(n, cTString);
                    return cTString2;
                }

                @Override
                public void add(int n, CTString cTString) {
                    CTSettingsImpl.this.insertNewAttachedSchema(n).set(cTString);
                }

                @Override
                public CTString remove(int n) {
                    CTString cTString = CTSettingsImpl.this.getAttachedSchemaArray(n);
                    CTSettingsImpl.this.removeAttachedSchema(n);
                    return cTString;
                }

                @Override
                public int size() {
                    return CTSettingsImpl.this.sizeOfAttachedSchemaArray();
                }
            }
            return new AttachedSchemaList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTString[] getAttachedSchemaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ATTACHEDSCHEMA$170, arrayList);
            CTString[] cTStringArray = new CTString[arrayList.size()];
            arrayList.toArray(cTStringArray);
            return cTStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getAttachedSchemaArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(ATTACHEDSCHEMA$170, n));
            if (cTString == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAttachedSchemaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTACHEDSCHEMA$170);
        }
    }

    @Override
    public void setAttachedSchemaArray(CTString[] cTStringArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTStringArray, ATTACHEDSCHEMA$170);
    }

    @Override
    public void setAttachedSchemaArray(int n, CTString cTString) {
        this.generatedSetterHelperImpl(cTString, ATTACHEDSCHEMA$170, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString insertNewAttachedSchema(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().insert_element_user(ATTACHEDSCHEMA$170, n));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewAttachedSchema() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(ATTACHEDSCHEMA$170));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAttachedSchema(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTACHEDSCHEMA$170, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLanguage getThemeFontLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLanguage cTLanguage = null;
            cTLanguage = (CTLanguage)((Object)this.get_store().find_element_user(THEMEFONTLANG$172, 0));
            if (cTLanguage == null) {
                return null;
            }
            return cTLanguage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetThemeFontLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(THEMEFONTLANG$172) != 0;
        }
    }

    @Override
    public void setThemeFontLang(CTLanguage cTLanguage) {
        this.generatedSetterHelperImpl(cTLanguage, THEMEFONTLANG$172, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLanguage addNewThemeFontLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLanguage cTLanguage = null;
            cTLanguage = (CTLanguage)((Object)this.get_store().add_element_user(THEMEFONTLANG$172));
            return cTLanguage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetThemeFontLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(THEMEFONTLANG$172, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorSchemeMapping getClrSchemeMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorSchemeMapping cTColorSchemeMapping = null;
            cTColorSchemeMapping = (CTColorSchemeMapping)this.get_store().find_element_user(CLRSCHEMEMAPPING$174, 0);
            if (cTColorSchemeMapping == null) {
                return null;
            }
            return cTColorSchemeMapping;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetClrSchemeMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLRSCHEMEMAPPING$174) != 0;
        }
    }

    @Override
    public void setClrSchemeMapping(CTColorSchemeMapping cTColorSchemeMapping) {
        this.generatedSetterHelperImpl((XmlObject)cTColorSchemeMapping, CLRSCHEMEMAPPING$174, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorSchemeMapping addNewClrSchemeMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorSchemeMapping cTColorSchemeMapping = null;
            cTColorSchemeMapping = (CTColorSchemeMapping)this.get_store().add_element_user(CLRSCHEMEMAPPING$174);
            return cTColorSchemeMapping;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetClrSchemeMapping() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLRSCHEMEMAPPING$174, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDoNotIncludeSubdocsInStats() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DONOTINCLUDESUBDOCSINSTATS$176, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoNotIncludeSubdocsInStats() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DONOTINCLUDESUBDOCSINSTATS$176) != 0;
        }
    }

    @Override
    public void setDoNotIncludeSubdocsInStats(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DONOTINCLUDESUBDOCSINSTATS$176, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDoNotIncludeSubdocsInStats() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DONOTINCLUDESUBDOCSINSTATS$176));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoNotIncludeSubdocsInStats() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DONOTINCLUDESUBDOCSINSTATS$176, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDoNotAutoCompressPictures() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DONOTAUTOCOMPRESSPICTURES$178, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoNotAutoCompressPictures() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DONOTAUTOCOMPRESSPICTURES$178) != 0;
        }
    }

    @Override
    public void setDoNotAutoCompressPictures(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DONOTAUTOCOMPRESSPICTURES$178, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDoNotAutoCompressPictures() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DONOTAUTOCOMPRESSPICTURES$178));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoNotAutoCompressPictures() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DONOTAUTOCOMPRESSPICTURES$178, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty getForceUpgrade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().find_element_user(FORCEUPGRADE$180, 0));
            if (cTEmpty == null) {
                return null;
            }
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetForceUpgrade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FORCEUPGRADE$180) != 0;
        }
    }

    @Override
    public void setForceUpgrade(CTEmpty cTEmpty) {
        this.generatedSetterHelperImpl(cTEmpty, FORCEUPGRADE$180, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmpty addNewForceUpgrade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmpty cTEmpty = null;
            cTEmpty = (CTEmpty)((Object)this.get_store().add_element_user(FORCEUPGRADE$180));
            return cTEmpty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetForceUpgrade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FORCEUPGRADE$180, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCaptions getCaptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCaptions cTCaptions = null;
            cTCaptions = (CTCaptions)this.get_store().find_element_user(CAPTIONS$182, 0);
            if (cTCaptions == null) {
                return null;
            }
            return cTCaptions;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCaptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CAPTIONS$182) != 0;
        }
    }

    @Override
    public void setCaptions(CTCaptions cTCaptions) {
        this.generatedSetterHelperImpl((XmlObject)cTCaptions, CAPTIONS$182, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCaptions addNewCaptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCaptions cTCaptions = null;
            cTCaptions = (CTCaptions)this.get_store().add_element_user(CAPTIONS$182);
            return cTCaptions;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCaptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CAPTIONS$182, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTReadingModeInkLockDown getReadModeInkLockDown() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTReadingModeInkLockDown cTReadingModeInkLockDown = null;
            cTReadingModeInkLockDown = (CTReadingModeInkLockDown)this.get_store().find_element_user(READMODEINKLOCKDOWN$184, 0);
            if (cTReadingModeInkLockDown == null) {
                return null;
            }
            return cTReadingModeInkLockDown;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetReadModeInkLockDown() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(READMODEINKLOCKDOWN$184) != 0;
        }
    }

    @Override
    public void setReadModeInkLockDown(CTReadingModeInkLockDown cTReadingModeInkLockDown) {
        this.generatedSetterHelperImpl((XmlObject)cTReadingModeInkLockDown, READMODEINKLOCKDOWN$184, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTReadingModeInkLockDown addNewReadModeInkLockDown() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTReadingModeInkLockDown cTReadingModeInkLockDown = null;
            cTReadingModeInkLockDown = (CTReadingModeInkLockDown)this.get_store().add_element_user(READMODEINKLOCKDOWN$184);
            return cTReadingModeInkLockDown;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetReadModeInkLockDown() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(READMODEINKLOCKDOWN$184, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSmartTagType> getSmartTagTypeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SmartTagTypeList
            extends AbstractList<CTSmartTagType> {
                SmartTagTypeList() {
                }

                @Override
                public CTSmartTagType get(int n) {
                    return CTSettingsImpl.this.getSmartTagTypeArray(n);
                }

                @Override
                public CTSmartTagType set(int n, CTSmartTagType cTSmartTagType) {
                    CTSmartTagType cTSmartTagType2 = CTSettingsImpl.this.getSmartTagTypeArray(n);
                    CTSettingsImpl.this.setSmartTagTypeArray(n, cTSmartTagType);
                    return cTSmartTagType2;
                }

                @Override
                public void add(int n, CTSmartTagType cTSmartTagType) {
                    CTSettingsImpl.this.insertNewSmartTagType(n).set((XmlObject)cTSmartTagType);
                }

                @Override
                public CTSmartTagType remove(int n) {
                    CTSmartTagType cTSmartTagType = CTSettingsImpl.this.getSmartTagTypeArray(n);
                    CTSettingsImpl.this.removeSmartTagType(n);
                    return cTSmartTagType;
                }

                @Override
                public int size() {
                    return CTSettingsImpl.this.sizeOfSmartTagTypeArray();
                }
            }
            return new SmartTagTypeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSmartTagType[] getSmartTagTypeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SMARTTAGTYPE$186, arrayList);
            CTSmartTagType[] cTSmartTagTypeArray = new CTSmartTagType[arrayList.size()];
            arrayList.toArray(cTSmartTagTypeArray);
            return cTSmartTagTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTagType getSmartTagTypeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTagType cTSmartTagType = null;
            cTSmartTagType = (CTSmartTagType)this.get_store().find_element_user(SMARTTAGTYPE$186, n);
            if (cTSmartTagType == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSmartTagType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSmartTagTypeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SMARTTAGTYPE$186);
        }
    }

    @Override
    public void setSmartTagTypeArray(CTSmartTagType[] cTSmartTagTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSmartTagTypeArray, SMARTTAGTYPE$186);
    }

    @Override
    public void setSmartTagTypeArray(int n, CTSmartTagType cTSmartTagType) {
        this.generatedSetterHelperImpl((XmlObject)cTSmartTagType, SMARTTAGTYPE$186, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTagType insertNewSmartTagType(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTagType cTSmartTagType = null;
            cTSmartTagType = (CTSmartTagType)this.get_store().insert_element_user(SMARTTAGTYPE$186, n);
            return cTSmartTagType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTagType addNewSmartTagType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTagType cTSmartTagType = null;
            cTSmartTagType = (CTSmartTagType)this.get_store().add_element_user(SMARTTAGTYPE$186);
            return cTSmartTagType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSmartTagType(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SMARTTAGTYPE$186, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchemaLibrary getSchemaLibrary() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchemaLibrary cTSchemaLibrary = null;
            cTSchemaLibrary = (CTSchemaLibrary)this.get_store().find_element_user(SCHEMALIBRARY$188, 0);
            if (cTSchemaLibrary == null) {
                return null;
            }
            return cTSchemaLibrary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSchemaLibrary() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCHEMALIBRARY$188) != 0;
        }
    }

    @Override
    public void setSchemaLibrary(CTSchemaLibrary cTSchemaLibrary) {
        this.generatedSetterHelperImpl((XmlObject)cTSchemaLibrary, SCHEMALIBRARY$188, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSchemaLibrary addNewSchemaLibrary() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSchemaLibrary cTSchemaLibrary = null;
            cTSchemaLibrary = (CTSchemaLibrary)this.get_store().add_element_user(SCHEMALIBRARY$188);
            return cTSchemaLibrary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSchemaLibrary() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCHEMALIBRARY$188, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeDefaults getShapeDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeDefaults cTShapeDefaults = null;
            cTShapeDefaults = (CTShapeDefaults)this.get_store().find_element_user(SHAPEDEFAULTS$190, 0);
            if (cTShapeDefaults == null) {
                return null;
            }
            return cTShapeDefaults;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetShapeDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHAPEDEFAULTS$190) != 0;
        }
    }

    @Override
    public void setShapeDefaults(CTShapeDefaults cTShapeDefaults) {
        this.generatedSetterHelperImpl((XmlObject)cTShapeDefaults, SHAPEDEFAULTS$190, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeDefaults addNewShapeDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeDefaults cTShapeDefaults = null;
            cTShapeDefaults = (CTShapeDefaults)this.get_store().add_element_user(SHAPEDEFAULTS$190);
            return cTShapeDefaults;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShapeDefaults() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHAPEDEFAULTS$190, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDoNotEmbedSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DONOTEMBEDSMARTTAGS$192, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoNotEmbedSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DONOTEMBEDSMARTTAGS$192) != 0;
        }
    }

    @Override
    public void setDoNotEmbedSmartTags(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DONOTEMBEDSMARTTAGS$192, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDoNotEmbedSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DONOTEMBEDSMARTTAGS$192));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoNotEmbedSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DONOTEMBEDSMARTTAGS$192, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getDecimalSymbol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(DECIMALSYMBOL$194, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDecimalSymbol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DECIMALSYMBOL$194) != 0;
        }
    }

    @Override
    public void setDecimalSymbol(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, DECIMALSYMBOL$194, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewDecimalSymbol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(DECIMALSYMBOL$194));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDecimalSymbol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DECIMALSYMBOL$194, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getListSeparator() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(LISTSEPARATOR$196, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetListSeparator() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LISTSEPARATOR$196) != 0;
        }
    }

    @Override
    public void setListSeparator(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, LISTSEPARATOR$196, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewListSeparator() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(LISTSEPARATOR$196));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetListSeparator() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LISTSEPARATOR$196, 0);
        }
    }
}

