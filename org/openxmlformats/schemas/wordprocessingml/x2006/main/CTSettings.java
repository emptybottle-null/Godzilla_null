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
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
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
import org.w3c.dom.Node;

public interface CTSettings
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSettings.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsettingsd6a5type");

    public CTWriteProtection getWriteProtection();

    public boolean isSetWriteProtection();

    public void setWriteProtection(CTWriteProtection var1);

    public CTWriteProtection addNewWriteProtection();

    public void unsetWriteProtection();

    public CTView getView();

    public boolean isSetView();

    public void setView(CTView var1);

    public CTView addNewView();

    public void unsetView();

    public CTZoom getZoom();

    public boolean isSetZoom();

    public void setZoom(CTZoom var1);

    public CTZoom addNewZoom();

    public void unsetZoom();

    public CTOnOff getRemovePersonalInformation();

    public boolean isSetRemovePersonalInformation();

    public void setRemovePersonalInformation(CTOnOff var1);

    public CTOnOff addNewRemovePersonalInformation();

    public void unsetRemovePersonalInformation();

    public CTOnOff getRemoveDateAndTime();

    public boolean isSetRemoveDateAndTime();

    public void setRemoveDateAndTime(CTOnOff var1);

    public CTOnOff addNewRemoveDateAndTime();

    public void unsetRemoveDateAndTime();

    public CTOnOff getDoNotDisplayPageBoundaries();

    public boolean isSetDoNotDisplayPageBoundaries();

    public void setDoNotDisplayPageBoundaries(CTOnOff var1);

    public CTOnOff addNewDoNotDisplayPageBoundaries();

    public void unsetDoNotDisplayPageBoundaries();

    public CTOnOff getDisplayBackgroundShape();

    public boolean isSetDisplayBackgroundShape();

    public void setDisplayBackgroundShape(CTOnOff var1);

    public CTOnOff addNewDisplayBackgroundShape();

    public void unsetDisplayBackgroundShape();

    public CTOnOff getPrintPostScriptOverText();

    public boolean isSetPrintPostScriptOverText();

    public void setPrintPostScriptOverText(CTOnOff var1);

    public CTOnOff addNewPrintPostScriptOverText();

    public void unsetPrintPostScriptOverText();

    public CTOnOff getPrintFractionalCharacterWidth();

    public boolean isSetPrintFractionalCharacterWidth();

    public void setPrintFractionalCharacterWidth(CTOnOff var1);

    public CTOnOff addNewPrintFractionalCharacterWidth();

    public void unsetPrintFractionalCharacterWidth();

    public CTOnOff getPrintFormsData();

    public boolean isSetPrintFormsData();

    public void setPrintFormsData(CTOnOff var1);

    public CTOnOff addNewPrintFormsData();

    public void unsetPrintFormsData();

    public CTOnOff getEmbedTrueTypeFonts();

    public boolean isSetEmbedTrueTypeFonts();

    public void setEmbedTrueTypeFonts(CTOnOff var1);

    public CTOnOff addNewEmbedTrueTypeFonts();

    public void unsetEmbedTrueTypeFonts();

    public CTOnOff getEmbedSystemFonts();

    public boolean isSetEmbedSystemFonts();

    public void setEmbedSystemFonts(CTOnOff var1);

    public CTOnOff addNewEmbedSystemFonts();

    public void unsetEmbedSystemFonts();

    public CTOnOff getSaveSubsetFonts();

    public boolean isSetSaveSubsetFonts();

    public void setSaveSubsetFonts(CTOnOff var1);

    public CTOnOff addNewSaveSubsetFonts();

    public void unsetSaveSubsetFonts();

    public CTOnOff getSaveFormsData();

    public boolean isSetSaveFormsData();

    public void setSaveFormsData(CTOnOff var1);

    public CTOnOff addNewSaveFormsData();

    public void unsetSaveFormsData();

    public CTOnOff getMirrorMargins();

    public boolean isSetMirrorMargins();

    public void setMirrorMargins(CTOnOff var1);

    public CTOnOff addNewMirrorMargins();

    public void unsetMirrorMargins();

    public CTOnOff getAlignBordersAndEdges();

    public boolean isSetAlignBordersAndEdges();

    public void setAlignBordersAndEdges(CTOnOff var1);

    public CTOnOff addNewAlignBordersAndEdges();

    public void unsetAlignBordersAndEdges();

    public CTOnOff getBordersDoNotSurroundHeader();

    public boolean isSetBordersDoNotSurroundHeader();

    public void setBordersDoNotSurroundHeader(CTOnOff var1);

    public CTOnOff addNewBordersDoNotSurroundHeader();

    public void unsetBordersDoNotSurroundHeader();

    public CTOnOff getBordersDoNotSurroundFooter();

    public boolean isSetBordersDoNotSurroundFooter();

    public void setBordersDoNotSurroundFooter(CTOnOff var1);

    public CTOnOff addNewBordersDoNotSurroundFooter();

    public void unsetBordersDoNotSurroundFooter();

    public CTOnOff getGutterAtTop();

    public boolean isSetGutterAtTop();

    public void setGutterAtTop(CTOnOff var1);

    public CTOnOff addNewGutterAtTop();

    public void unsetGutterAtTop();

    public CTOnOff getHideSpellingErrors();

    public boolean isSetHideSpellingErrors();

    public void setHideSpellingErrors(CTOnOff var1);

    public CTOnOff addNewHideSpellingErrors();

    public void unsetHideSpellingErrors();

    public CTOnOff getHideGrammaticalErrors();

    public boolean isSetHideGrammaticalErrors();

    public void setHideGrammaticalErrors(CTOnOff var1);

    public CTOnOff addNewHideGrammaticalErrors();

    public void unsetHideGrammaticalErrors();

    public List<CTWritingStyle> getActiveWritingStyleList();

    @Deprecated
    public CTWritingStyle[] getActiveWritingStyleArray();

    public CTWritingStyle getActiveWritingStyleArray(int var1);

    public int sizeOfActiveWritingStyleArray();

    public void setActiveWritingStyleArray(CTWritingStyle[] var1);

    public void setActiveWritingStyleArray(int var1, CTWritingStyle var2);

    public CTWritingStyle insertNewActiveWritingStyle(int var1);

    public CTWritingStyle addNewActiveWritingStyle();

    public void removeActiveWritingStyle(int var1);

    public CTProof getProofState();

    public boolean isSetProofState();

    public void setProofState(CTProof var1);

    public CTProof addNewProofState();

    public void unsetProofState();

    public CTOnOff getFormsDesign();

    public boolean isSetFormsDesign();

    public void setFormsDesign(CTOnOff var1);

    public CTOnOff addNewFormsDesign();

    public void unsetFormsDesign();

    public CTRel getAttachedTemplate();

    public boolean isSetAttachedTemplate();

    public void setAttachedTemplate(CTRel var1);

    public CTRel addNewAttachedTemplate();

    public void unsetAttachedTemplate();

    public CTOnOff getLinkStyles();

    public boolean isSetLinkStyles();

    public void setLinkStyles(CTOnOff var1);

    public CTOnOff addNewLinkStyles();

    public void unsetLinkStyles();

    public CTShortHexNumber getStylePaneFormatFilter();

    public boolean isSetStylePaneFormatFilter();

    public void setStylePaneFormatFilter(CTShortHexNumber var1);

    public CTShortHexNumber addNewStylePaneFormatFilter();

    public void unsetStylePaneFormatFilter();

    public CTShortHexNumber getStylePaneSortMethod();

    public boolean isSetStylePaneSortMethod();

    public void setStylePaneSortMethod(CTShortHexNumber var1);

    public CTShortHexNumber addNewStylePaneSortMethod();

    public void unsetStylePaneSortMethod();

    public CTDocType getDocumentType();

    public boolean isSetDocumentType();

    public void setDocumentType(CTDocType var1);

    public CTDocType addNewDocumentType();

    public void unsetDocumentType();

    public CTMailMerge getMailMerge();

    public boolean isSetMailMerge();

    public void setMailMerge(CTMailMerge var1);

    public CTMailMerge addNewMailMerge();

    public void unsetMailMerge();

    public CTTrackChangesView getRevisionView();

    public boolean isSetRevisionView();

    public void setRevisionView(CTTrackChangesView var1);

    public CTTrackChangesView addNewRevisionView();

    public void unsetRevisionView();

    public CTOnOff getTrackRevisions();

    public boolean isSetTrackRevisions();

    public void setTrackRevisions(CTOnOff var1);

    public CTOnOff addNewTrackRevisions();

    public void unsetTrackRevisions();

    public CTOnOff getDoNotTrackMoves();

    public boolean isSetDoNotTrackMoves();

    public void setDoNotTrackMoves(CTOnOff var1);

    public CTOnOff addNewDoNotTrackMoves();

    public void unsetDoNotTrackMoves();

    public CTOnOff getDoNotTrackFormatting();

    public boolean isSetDoNotTrackFormatting();

    public void setDoNotTrackFormatting(CTOnOff var1);

    public CTOnOff addNewDoNotTrackFormatting();

    public void unsetDoNotTrackFormatting();

    public CTDocProtect getDocumentProtection();

    public boolean isSetDocumentProtection();

    public void setDocumentProtection(CTDocProtect var1);

    public CTDocProtect addNewDocumentProtection();

    public void unsetDocumentProtection();

    public CTOnOff getAutoFormatOverride();

    public boolean isSetAutoFormatOverride();

    public void setAutoFormatOverride(CTOnOff var1);

    public CTOnOff addNewAutoFormatOverride();

    public void unsetAutoFormatOverride();

    public CTOnOff getStyleLockTheme();

    public boolean isSetStyleLockTheme();

    public void setStyleLockTheme(CTOnOff var1);

    public CTOnOff addNewStyleLockTheme();

    public void unsetStyleLockTheme();

    public CTOnOff getStyleLockQFSet();

    public boolean isSetStyleLockQFSet();

    public void setStyleLockQFSet(CTOnOff var1);

    public CTOnOff addNewStyleLockQFSet();

    public void unsetStyleLockQFSet();

    public CTTwipsMeasure getDefaultTabStop();

    public boolean isSetDefaultTabStop();

    public void setDefaultTabStop(CTTwipsMeasure var1);

    public CTTwipsMeasure addNewDefaultTabStop();

    public void unsetDefaultTabStop();

    public CTOnOff getAutoHyphenation();

    public boolean isSetAutoHyphenation();

    public void setAutoHyphenation(CTOnOff var1);

    public CTOnOff addNewAutoHyphenation();

    public void unsetAutoHyphenation();

    public CTDecimalNumber getConsecutiveHyphenLimit();

    public boolean isSetConsecutiveHyphenLimit();

    public void setConsecutiveHyphenLimit(CTDecimalNumber var1);

    public CTDecimalNumber addNewConsecutiveHyphenLimit();

    public void unsetConsecutiveHyphenLimit();

    public CTTwipsMeasure getHyphenationZone();

    public boolean isSetHyphenationZone();

    public void setHyphenationZone(CTTwipsMeasure var1);

    public CTTwipsMeasure addNewHyphenationZone();

    public void unsetHyphenationZone();

    public CTOnOff getDoNotHyphenateCaps();

    public boolean isSetDoNotHyphenateCaps();

    public void setDoNotHyphenateCaps(CTOnOff var1);

    public CTOnOff addNewDoNotHyphenateCaps();

    public void unsetDoNotHyphenateCaps();

    public CTOnOff getShowEnvelope();

    public boolean isSetShowEnvelope();

    public void setShowEnvelope(CTOnOff var1);

    public CTOnOff addNewShowEnvelope();

    public void unsetShowEnvelope();

    public CTDecimalNumber getSummaryLength();

    public boolean isSetSummaryLength();

    public void setSummaryLength(CTDecimalNumber var1);

    public CTDecimalNumber addNewSummaryLength();

    public void unsetSummaryLength();

    public CTString getClickAndTypeStyle();

    public boolean isSetClickAndTypeStyle();

    public void setClickAndTypeStyle(CTString var1);

    public CTString addNewClickAndTypeStyle();

    public void unsetClickAndTypeStyle();

    public CTString getDefaultTableStyle();

    public boolean isSetDefaultTableStyle();

    public void setDefaultTableStyle(CTString var1);

    public CTString addNewDefaultTableStyle();

    public void unsetDefaultTableStyle();

    public CTOnOff getEvenAndOddHeaders();

    public boolean isSetEvenAndOddHeaders();

    public void setEvenAndOddHeaders(CTOnOff var1);

    public CTOnOff addNewEvenAndOddHeaders();

    public void unsetEvenAndOddHeaders();

    public CTOnOff getBookFoldRevPrinting();

    public boolean isSetBookFoldRevPrinting();

    public void setBookFoldRevPrinting(CTOnOff var1);

    public CTOnOff addNewBookFoldRevPrinting();

    public void unsetBookFoldRevPrinting();

    public CTOnOff getBookFoldPrinting();

    public boolean isSetBookFoldPrinting();

    public void setBookFoldPrinting(CTOnOff var1);

    public CTOnOff addNewBookFoldPrinting();

    public void unsetBookFoldPrinting();

    public CTDecimalNumber getBookFoldPrintingSheets();

    public boolean isSetBookFoldPrintingSheets();

    public void setBookFoldPrintingSheets(CTDecimalNumber var1);

    public CTDecimalNumber addNewBookFoldPrintingSheets();

    public void unsetBookFoldPrintingSheets();

    public CTTwipsMeasure getDrawingGridHorizontalSpacing();

    public boolean isSetDrawingGridHorizontalSpacing();

    public void setDrawingGridHorizontalSpacing(CTTwipsMeasure var1);

    public CTTwipsMeasure addNewDrawingGridHorizontalSpacing();

    public void unsetDrawingGridHorizontalSpacing();

    public CTTwipsMeasure getDrawingGridVerticalSpacing();

    public boolean isSetDrawingGridVerticalSpacing();

    public void setDrawingGridVerticalSpacing(CTTwipsMeasure var1);

    public CTTwipsMeasure addNewDrawingGridVerticalSpacing();

    public void unsetDrawingGridVerticalSpacing();

    public CTDecimalNumber getDisplayHorizontalDrawingGridEvery();

    public boolean isSetDisplayHorizontalDrawingGridEvery();

    public void setDisplayHorizontalDrawingGridEvery(CTDecimalNumber var1);

    public CTDecimalNumber addNewDisplayHorizontalDrawingGridEvery();

    public void unsetDisplayHorizontalDrawingGridEvery();

    public CTDecimalNumber getDisplayVerticalDrawingGridEvery();

    public boolean isSetDisplayVerticalDrawingGridEvery();

    public void setDisplayVerticalDrawingGridEvery(CTDecimalNumber var1);

    public CTDecimalNumber addNewDisplayVerticalDrawingGridEvery();

    public void unsetDisplayVerticalDrawingGridEvery();

    public CTOnOff getDoNotUseMarginsForDrawingGridOrigin();

    public boolean isSetDoNotUseMarginsForDrawingGridOrigin();

    public void setDoNotUseMarginsForDrawingGridOrigin(CTOnOff var1);

    public CTOnOff addNewDoNotUseMarginsForDrawingGridOrigin();

    public void unsetDoNotUseMarginsForDrawingGridOrigin();

    public CTTwipsMeasure getDrawingGridHorizontalOrigin();

    public boolean isSetDrawingGridHorizontalOrigin();

    public void setDrawingGridHorizontalOrigin(CTTwipsMeasure var1);

    public CTTwipsMeasure addNewDrawingGridHorizontalOrigin();

    public void unsetDrawingGridHorizontalOrigin();

    public CTTwipsMeasure getDrawingGridVerticalOrigin();

    public boolean isSetDrawingGridVerticalOrigin();

    public void setDrawingGridVerticalOrigin(CTTwipsMeasure var1);

    public CTTwipsMeasure addNewDrawingGridVerticalOrigin();

    public void unsetDrawingGridVerticalOrigin();

    public CTOnOff getDoNotShadeFormData();

    public boolean isSetDoNotShadeFormData();

    public void setDoNotShadeFormData(CTOnOff var1);

    public CTOnOff addNewDoNotShadeFormData();

    public void unsetDoNotShadeFormData();

    public CTOnOff getNoPunctuationKerning();

    public boolean isSetNoPunctuationKerning();

    public void setNoPunctuationKerning(CTOnOff var1);

    public CTOnOff addNewNoPunctuationKerning();

    public void unsetNoPunctuationKerning();

    public CTCharacterSpacing getCharacterSpacingControl();

    public boolean isSetCharacterSpacingControl();

    public void setCharacterSpacingControl(CTCharacterSpacing var1);

    public CTCharacterSpacing addNewCharacterSpacingControl();

    public void unsetCharacterSpacingControl();

    public CTOnOff getPrintTwoOnOne();

    public boolean isSetPrintTwoOnOne();

    public void setPrintTwoOnOne(CTOnOff var1);

    public CTOnOff addNewPrintTwoOnOne();

    public void unsetPrintTwoOnOne();

    public CTOnOff getStrictFirstAndLastChars();

    public boolean isSetStrictFirstAndLastChars();

    public void setStrictFirstAndLastChars(CTOnOff var1);

    public CTOnOff addNewStrictFirstAndLastChars();

    public void unsetStrictFirstAndLastChars();

    public CTKinsoku getNoLineBreaksAfter();

    public boolean isSetNoLineBreaksAfter();

    public void setNoLineBreaksAfter(CTKinsoku var1);

    public CTKinsoku addNewNoLineBreaksAfter();

    public void unsetNoLineBreaksAfter();

    public CTKinsoku getNoLineBreaksBefore();

    public boolean isSetNoLineBreaksBefore();

    public void setNoLineBreaksBefore(CTKinsoku var1);

    public CTKinsoku addNewNoLineBreaksBefore();

    public void unsetNoLineBreaksBefore();

    public CTOnOff getSavePreviewPicture();

    public boolean isSetSavePreviewPicture();

    public void setSavePreviewPicture(CTOnOff var1);

    public CTOnOff addNewSavePreviewPicture();

    public void unsetSavePreviewPicture();

    public CTOnOff getDoNotValidateAgainstSchema();

    public boolean isSetDoNotValidateAgainstSchema();

    public void setDoNotValidateAgainstSchema(CTOnOff var1);

    public CTOnOff addNewDoNotValidateAgainstSchema();

    public void unsetDoNotValidateAgainstSchema();

    public CTOnOff getSaveInvalidXml();

    public boolean isSetSaveInvalidXml();

    public void setSaveInvalidXml(CTOnOff var1);

    public CTOnOff addNewSaveInvalidXml();

    public void unsetSaveInvalidXml();

    public CTOnOff getIgnoreMixedContent();

    public boolean isSetIgnoreMixedContent();

    public void setIgnoreMixedContent(CTOnOff var1);

    public CTOnOff addNewIgnoreMixedContent();

    public void unsetIgnoreMixedContent();

    public CTOnOff getAlwaysShowPlaceholderText();

    public boolean isSetAlwaysShowPlaceholderText();

    public void setAlwaysShowPlaceholderText(CTOnOff var1);

    public CTOnOff addNewAlwaysShowPlaceholderText();

    public void unsetAlwaysShowPlaceholderText();

    public CTOnOff getDoNotDemarcateInvalidXml();

    public boolean isSetDoNotDemarcateInvalidXml();

    public void setDoNotDemarcateInvalidXml(CTOnOff var1);

    public CTOnOff addNewDoNotDemarcateInvalidXml();

    public void unsetDoNotDemarcateInvalidXml();

    public CTOnOff getSaveXmlDataOnly();

    public boolean isSetSaveXmlDataOnly();

    public void setSaveXmlDataOnly(CTOnOff var1);

    public CTOnOff addNewSaveXmlDataOnly();

    public void unsetSaveXmlDataOnly();

    public CTOnOff getUseXSLTWhenSaving();

    public boolean isSetUseXSLTWhenSaving();

    public void setUseXSLTWhenSaving(CTOnOff var1);

    public CTOnOff addNewUseXSLTWhenSaving();

    public void unsetUseXSLTWhenSaving();

    public CTSaveThroughXslt getSaveThroughXslt();

    public boolean isSetSaveThroughXslt();

    public void setSaveThroughXslt(CTSaveThroughXslt var1);

    public CTSaveThroughXslt addNewSaveThroughXslt();

    public void unsetSaveThroughXslt();

    public CTOnOff getShowXMLTags();

    public boolean isSetShowXMLTags();

    public void setShowXMLTags(CTOnOff var1);

    public CTOnOff addNewShowXMLTags();

    public void unsetShowXMLTags();

    public CTOnOff getAlwaysMergeEmptyNamespace();

    public boolean isSetAlwaysMergeEmptyNamespace();

    public void setAlwaysMergeEmptyNamespace(CTOnOff var1);

    public CTOnOff addNewAlwaysMergeEmptyNamespace();

    public void unsetAlwaysMergeEmptyNamespace();

    public CTOnOff getUpdateFields();

    public boolean isSetUpdateFields();

    public void setUpdateFields(CTOnOff var1);

    public CTOnOff addNewUpdateFields();

    public void unsetUpdateFields();

    public CTShapeDefaults getHdrShapeDefaults();

    public boolean isSetHdrShapeDefaults();

    public void setHdrShapeDefaults(CTShapeDefaults var1);

    public CTShapeDefaults addNewHdrShapeDefaults();

    public void unsetHdrShapeDefaults();

    public CTFtnDocProps getFootnotePr();

    public boolean isSetFootnotePr();

    public void setFootnotePr(CTFtnDocProps var1);

    public CTFtnDocProps addNewFootnotePr();

    public void unsetFootnotePr();

    public CTEdnDocProps getEndnotePr();

    public boolean isSetEndnotePr();

    public void setEndnotePr(CTEdnDocProps var1);

    public CTEdnDocProps addNewEndnotePr();

    public void unsetEndnotePr();

    public CTCompat getCompat();

    public boolean isSetCompat();

    public void setCompat(CTCompat var1);

    public CTCompat addNewCompat();

    public void unsetCompat();

    public CTDocVars getDocVars();

    public boolean isSetDocVars();

    public void setDocVars(CTDocVars var1);

    public CTDocVars addNewDocVars();

    public void unsetDocVars();

    public CTDocRsids getRsids();

    public boolean isSetRsids();

    public void setRsids(CTDocRsids var1);

    public CTDocRsids addNewRsids();

    public void unsetRsids();

    public CTMathPr getMathPr();

    public boolean isSetMathPr();

    public void setMathPr(CTMathPr var1);

    public CTMathPr addNewMathPr();

    public void unsetMathPr();

    public CTOnOff getUiCompat97To2003();

    public boolean isSetUiCompat97To2003();

    public void setUiCompat97To2003(CTOnOff var1);

    public CTOnOff addNewUiCompat97To2003();

    public void unsetUiCompat97To2003();

    public List<CTString> getAttachedSchemaList();

    @Deprecated
    public CTString[] getAttachedSchemaArray();

    public CTString getAttachedSchemaArray(int var1);

    public int sizeOfAttachedSchemaArray();

    public void setAttachedSchemaArray(CTString[] var1);

    public void setAttachedSchemaArray(int var1, CTString var2);

    public CTString insertNewAttachedSchema(int var1);

    public CTString addNewAttachedSchema();

    public void removeAttachedSchema(int var1);

    public CTLanguage getThemeFontLang();

    public boolean isSetThemeFontLang();

    public void setThemeFontLang(CTLanguage var1);

    public CTLanguage addNewThemeFontLang();

    public void unsetThemeFontLang();

    public CTColorSchemeMapping getClrSchemeMapping();

    public boolean isSetClrSchemeMapping();

    public void setClrSchemeMapping(CTColorSchemeMapping var1);

    public CTColorSchemeMapping addNewClrSchemeMapping();

    public void unsetClrSchemeMapping();

    public CTOnOff getDoNotIncludeSubdocsInStats();

    public boolean isSetDoNotIncludeSubdocsInStats();

    public void setDoNotIncludeSubdocsInStats(CTOnOff var1);

    public CTOnOff addNewDoNotIncludeSubdocsInStats();

    public void unsetDoNotIncludeSubdocsInStats();

    public CTOnOff getDoNotAutoCompressPictures();

    public boolean isSetDoNotAutoCompressPictures();

    public void setDoNotAutoCompressPictures(CTOnOff var1);

    public CTOnOff addNewDoNotAutoCompressPictures();

    public void unsetDoNotAutoCompressPictures();

    public CTEmpty getForceUpgrade();

    public boolean isSetForceUpgrade();

    public void setForceUpgrade(CTEmpty var1);

    public CTEmpty addNewForceUpgrade();

    public void unsetForceUpgrade();

    public CTCaptions getCaptions();

    public boolean isSetCaptions();

    public void setCaptions(CTCaptions var1);

    public CTCaptions addNewCaptions();

    public void unsetCaptions();

    public CTReadingModeInkLockDown getReadModeInkLockDown();

    public boolean isSetReadModeInkLockDown();

    public void setReadModeInkLockDown(CTReadingModeInkLockDown var1);

    public CTReadingModeInkLockDown addNewReadModeInkLockDown();

    public void unsetReadModeInkLockDown();

    public List<CTSmartTagType> getSmartTagTypeList();

    @Deprecated
    public CTSmartTagType[] getSmartTagTypeArray();

    public CTSmartTagType getSmartTagTypeArray(int var1);

    public int sizeOfSmartTagTypeArray();

    public void setSmartTagTypeArray(CTSmartTagType[] var1);

    public void setSmartTagTypeArray(int var1, CTSmartTagType var2);

    public CTSmartTagType insertNewSmartTagType(int var1);

    public CTSmartTagType addNewSmartTagType();

    public void removeSmartTagType(int var1);

    public CTSchemaLibrary getSchemaLibrary();

    public boolean isSetSchemaLibrary();

    public void setSchemaLibrary(CTSchemaLibrary var1);

    public CTSchemaLibrary addNewSchemaLibrary();

    public void unsetSchemaLibrary();

    public CTShapeDefaults getShapeDefaults();

    public boolean isSetShapeDefaults();

    public void setShapeDefaults(CTShapeDefaults var1);

    public CTShapeDefaults addNewShapeDefaults();

    public void unsetShapeDefaults();

    public CTOnOff getDoNotEmbedSmartTags();

    public boolean isSetDoNotEmbedSmartTags();

    public void setDoNotEmbedSmartTags(CTOnOff var1);

    public CTOnOff addNewDoNotEmbedSmartTags();

    public void unsetDoNotEmbedSmartTags();

    public CTString getDecimalSymbol();

    public boolean isSetDecimalSymbol();

    public void setDecimalSymbol(CTString var1);

    public CTString addNewDecimalSymbol();

    public void unsetDecimalSymbol();

    public CTString getListSeparator();

    public boolean isSetListSeparator();

    public void setListSeparator(CTString var1);

    public CTString addNewListSeparator();

    public void unsetListSeparator();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSettings.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSettings newInstance() {
            return (CTSettings)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSettings newInstance(XmlOptions xmlOptions) {
            return (CTSettings)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSettings parse(String string) throws XmlException {
            return (CTSettings)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSettings parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSettings)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSettings parse(File file) throws XmlException, IOException {
            return (CTSettings)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSettings parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSettings)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSettings parse(URL uRL) throws XmlException, IOException {
            return (CTSettings)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSettings parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSettings)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSettings parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSettings)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSettings parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSettings)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSettings parse(Reader reader) throws XmlException, IOException {
            return (CTSettings)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSettings parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSettings)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSettings parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSettings)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSettings parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSettings)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSettings parse(Node node) throws XmlException {
            return (CTSettings)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSettings parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSettings)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSettings parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSettings)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSettings parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSettings)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, null);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, xmlOptions);
        }

        private Factory() {
        }
    }
}

