/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTAcc
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTBar
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTBorderBox
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTBox
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTD
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArr
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTF
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTFunc
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTGroupChr
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTLimLow
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTLimUpp
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTM
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTNary
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTPhant
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTR
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTRad
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTSPre
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubSup
 *  org.openxmlformats.schemas.officeDocument.x2006.math.CTSSup
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlRun
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart
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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTAcc;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTBar;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTBorderBox;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTBox;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTD;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTF;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTFunc;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTGroupChr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTLimLow;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTLimUpp;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTM;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTNary;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTPhant;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTRad;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSPre;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubSup;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.w3c.dom.Node;

public interface CTRunTrackChange
extends CTTrackChange {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRunTrackChange.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctruntrackchangea458type");

    public List<CTCustomXmlRun> getCustomXmlList();

    @Deprecated
    public CTCustomXmlRun[] getCustomXmlArray();

    public CTCustomXmlRun getCustomXmlArray(int var1);

    public int sizeOfCustomXmlArray();

    public void setCustomXmlArray(CTCustomXmlRun[] var1);

    public void setCustomXmlArray(int var1, CTCustomXmlRun var2);

    public CTCustomXmlRun insertNewCustomXml(int var1);

    public CTCustomXmlRun addNewCustomXml();

    public void removeCustomXml(int var1);

    public List<CTSmartTagRun> getSmartTagList();

    @Deprecated
    public CTSmartTagRun[] getSmartTagArray();

    public CTSmartTagRun getSmartTagArray(int var1);

    public int sizeOfSmartTagArray();

    public void setSmartTagArray(CTSmartTagRun[] var1);

    public void setSmartTagArray(int var1, CTSmartTagRun var2);

    public CTSmartTagRun insertNewSmartTag(int var1);

    public CTSmartTagRun addNewSmartTag();

    public void removeSmartTag(int var1);

    public List<CTSdtRun> getSdtList();

    @Deprecated
    public CTSdtRun[] getSdtArray();

    public CTSdtRun getSdtArray(int var1);

    public int sizeOfSdtArray();

    public void setSdtArray(CTSdtRun[] var1);

    public void setSdtArray(int var1, CTSdtRun var2);

    public CTSdtRun insertNewSdt(int var1);

    public CTSdtRun addNewSdt();

    public void removeSdt(int var1);

    public List<CTR> getRList();

    @Deprecated
    public CTR[] getRArray();

    public CTR getRArray(int var1);

    public int sizeOfRArray();

    public void setRArray(CTR[] var1);

    public void setRArray(int var1, CTR var2);

    public CTR insertNewR(int var1);

    public CTR addNewR();

    public void removeR(int var1);

    public List<CTProofErr> getProofErrList();

    @Deprecated
    public CTProofErr[] getProofErrArray();

    public CTProofErr getProofErrArray(int var1);

    public int sizeOfProofErrArray();

    public void setProofErrArray(CTProofErr[] var1);

    public void setProofErrArray(int var1, CTProofErr var2);

    public CTProofErr insertNewProofErr(int var1);

    public CTProofErr addNewProofErr();

    public void removeProofErr(int var1);

    public List<CTPermStart> getPermStartList();

    @Deprecated
    public CTPermStart[] getPermStartArray();

    public CTPermStart getPermStartArray(int var1);

    public int sizeOfPermStartArray();

    public void setPermStartArray(CTPermStart[] var1);

    public void setPermStartArray(int var1, CTPermStart var2);

    public CTPermStart insertNewPermStart(int var1);

    public CTPermStart addNewPermStart();

    public void removePermStart(int var1);

    public List<CTPerm> getPermEndList();

    @Deprecated
    public CTPerm[] getPermEndArray();

    public CTPerm getPermEndArray(int var1);

    public int sizeOfPermEndArray();

    public void setPermEndArray(CTPerm[] var1);

    public void setPermEndArray(int var1, CTPerm var2);

    public CTPerm insertNewPermEnd(int var1);

    public CTPerm addNewPermEnd();

    public void removePermEnd(int var1);

    public List<CTBookmark> getBookmarkStartList();

    @Deprecated
    public CTBookmark[] getBookmarkStartArray();

    public CTBookmark getBookmarkStartArray(int var1);

    public int sizeOfBookmarkStartArray();

    public void setBookmarkStartArray(CTBookmark[] var1);

    public void setBookmarkStartArray(int var1, CTBookmark var2);

    public CTBookmark insertNewBookmarkStart(int var1);

    public CTBookmark addNewBookmarkStart();

    public void removeBookmarkStart(int var1);

    public List<CTMarkupRange> getBookmarkEndList();

    @Deprecated
    public CTMarkupRange[] getBookmarkEndArray();

    public CTMarkupRange getBookmarkEndArray(int var1);

    public int sizeOfBookmarkEndArray();

    public void setBookmarkEndArray(CTMarkupRange[] var1);

    public void setBookmarkEndArray(int var1, CTMarkupRange var2);

    public CTMarkupRange insertNewBookmarkEnd(int var1);

    public CTMarkupRange addNewBookmarkEnd();

    public void removeBookmarkEnd(int var1);

    public List<CTMoveBookmark> getMoveFromRangeStartList();

    @Deprecated
    public CTMoveBookmark[] getMoveFromRangeStartArray();

    public CTMoveBookmark getMoveFromRangeStartArray(int var1);

    public int sizeOfMoveFromRangeStartArray();

    public void setMoveFromRangeStartArray(CTMoveBookmark[] var1);

    public void setMoveFromRangeStartArray(int var1, CTMoveBookmark var2);

    public CTMoveBookmark insertNewMoveFromRangeStart(int var1);

    public CTMoveBookmark addNewMoveFromRangeStart();

    public void removeMoveFromRangeStart(int var1);

    public List<CTMarkupRange> getMoveFromRangeEndList();

    @Deprecated
    public CTMarkupRange[] getMoveFromRangeEndArray();

    public CTMarkupRange getMoveFromRangeEndArray(int var1);

    public int sizeOfMoveFromRangeEndArray();

    public void setMoveFromRangeEndArray(CTMarkupRange[] var1);

    public void setMoveFromRangeEndArray(int var1, CTMarkupRange var2);

    public CTMarkupRange insertNewMoveFromRangeEnd(int var1);

    public CTMarkupRange addNewMoveFromRangeEnd();

    public void removeMoveFromRangeEnd(int var1);

    public List<CTMoveBookmark> getMoveToRangeStartList();

    @Deprecated
    public CTMoveBookmark[] getMoveToRangeStartArray();

    public CTMoveBookmark getMoveToRangeStartArray(int var1);

    public int sizeOfMoveToRangeStartArray();

    public void setMoveToRangeStartArray(CTMoveBookmark[] var1);

    public void setMoveToRangeStartArray(int var1, CTMoveBookmark var2);

    public CTMoveBookmark insertNewMoveToRangeStart(int var1);

    public CTMoveBookmark addNewMoveToRangeStart();

    public void removeMoveToRangeStart(int var1);

    public List<CTMarkupRange> getMoveToRangeEndList();

    @Deprecated
    public CTMarkupRange[] getMoveToRangeEndArray();

    public CTMarkupRange getMoveToRangeEndArray(int var1);

    public int sizeOfMoveToRangeEndArray();

    public void setMoveToRangeEndArray(CTMarkupRange[] var1);

    public void setMoveToRangeEndArray(int var1, CTMarkupRange var2);

    public CTMarkupRange insertNewMoveToRangeEnd(int var1);

    public CTMarkupRange addNewMoveToRangeEnd();

    public void removeMoveToRangeEnd(int var1);

    public List<CTMarkupRange> getCommentRangeStartList();

    @Deprecated
    public CTMarkupRange[] getCommentRangeStartArray();

    public CTMarkupRange getCommentRangeStartArray(int var1);

    public int sizeOfCommentRangeStartArray();

    public void setCommentRangeStartArray(CTMarkupRange[] var1);

    public void setCommentRangeStartArray(int var1, CTMarkupRange var2);

    public CTMarkupRange insertNewCommentRangeStart(int var1);

    public CTMarkupRange addNewCommentRangeStart();

    public void removeCommentRangeStart(int var1);

    public List<CTMarkupRange> getCommentRangeEndList();

    @Deprecated
    public CTMarkupRange[] getCommentRangeEndArray();

    public CTMarkupRange getCommentRangeEndArray(int var1);

    public int sizeOfCommentRangeEndArray();

    public void setCommentRangeEndArray(CTMarkupRange[] var1);

    public void setCommentRangeEndArray(int var1, CTMarkupRange var2);

    public CTMarkupRange insertNewCommentRangeEnd(int var1);

    public CTMarkupRange addNewCommentRangeEnd();

    public void removeCommentRangeEnd(int var1);

    public List<CTTrackChange> getCustomXmlInsRangeStartList();

    @Deprecated
    public CTTrackChange[] getCustomXmlInsRangeStartArray();

    public CTTrackChange getCustomXmlInsRangeStartArray(int var1);

    public int sizeOfCustomXmlInsRangeStartArray();

    public void setCustomXmlInsRangeStartArray(CTTrackChange[] var1);

    public void setCustomXmlInsRangeStartArray(int var1, CTTrackChange var2);

    public CTTrackChange insertNewCustomXmlInsRangeStart(int var1);

    public CTTrackChange addNewCustomXmlInsRangeStart();

    public void removeCustomXmlInsRangeStart(int var1);

    public List<CTMarkup> getCustomXmlInsRangeEndList();

    @Deprecated
    public CTMarkup[] getCustomXmlInsRangeEndArray();

    public CTMarkup getCustomXmlInsRangeEndArray(int var1);

    public int sizeOfCustomXmlInsRangeEndArray();

    public void setCustomXmlInsRangeEndArray(CTMarkup[] var1);

    public void setCustomXmlInsRangeEndArray(int var1, CTMarkup var2);

    public CTMarkup insertNewCustomXmlInsRangeEnd(int var1);

    public CTMarkup addNewCustomXmlInsRangeEnd();

    public void removeCustomXmlInsRangeEnd(int var1);

    public List<CTTrackChange> getCustomXmlDelRangeStartList();

    @Deprecated
    public CTTrackChange[] getCustomXmlDelRangeStartArray();

    public CTTrackChange getCustomXmlDelRangeStartArray(int var1);

    public int sizeOfCustomXmlDelRangeStartArray();

    public void setCustomXmlDelRangeStartArray(CTTrackChange[] var1);

    public void setCustomXmlDelRangeStartArray(int var1, CTTrackChange var2);

    public CTTrackChange insertNewCustomXmlDelRangeStart(int var1);

    public CTTrackChange addNewCustomXmlDelRangeStart();

    public void removeCustomXmlDelRangeStart(int var1);

    public List<CTMarkup> getCustomXmlDelRangeEndList();

    @Deprecated
    public CTMarkup[] getCustomXmlDelRangeEndArray();

    public CTMarkup getCustomXmlDelRangeEndArray(int var1);

    public int sizeOfCustomXmlDelRangeEndArray();

    public void setCustomXmlDelRangeEndArray(CTMarkup[] var1);

    public void setCustomXmlDelRangeEndArray(int var1, CTMarkup var2);

    public CTMarkup insertNewCustomXmlDelRangeEnd(int var1);

    public CTMarkup addNewCustomXmlDelRangeEnd();

    public void removeCustomXmlDelRangeEnd(int var1);

    public List<CTTrackChange> getCustomXmlMoveFromRangeStartList();

    @Deprecated
    public CTTrackChange[] getCustomXmlMoveFromRangeStartArray();

    public CTTrackChange getCustomXmlMoveFromRangeStartArray(int var1);

    public int sizeOfCustomXmlMoveFromRangeStartArray();

    public void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] var1);

    public void setCustomXmlMoveFromRangeStartArray(int var1, CTTrackChange var2);

    public CTTrackChange insertNewCustomXmlMoveFromRangeStart(int var1);

    public CTTrackChange addNewCustomXmlMoveFromRangeStart();

    public void removeCustomXmlMoveFromRangeStart(int var1);

    public List<CTMarkup> getCustomXmlMoveFromRangeEndList();

    @Deprecated
    public CTMarkup[] getCustomXmlMoveFromRangeEndArray();

    public CTMarkup getCustomXmlMoveFromRangeEndArray(int var1);

    public int sizeOfCustomXmlMoveFromRangeEndArray();

    public void setCustomXmlMoveFromRangeEndArray(CTMarkup[] var1);

    public void setCustomXmlMoveFromRangeEndArray(int var1, CTMarkup var2);

    public CTMarkup insertNewCustomXmlMoveFromRangeEnd(int var1);

    public CTMarkup addNewCustomXmlMoveFromRangeEnd();

    public void removeCustomXmlMoveFromRangeEnd(int var1);

    public List<CTTrackChange> getCustomXmlMoveToRangeStartList();

    @Deprecated
    public CTTrackChange[] getCustomXmlMoveToRangeStartArray();

    public CTTrackChange getCustomXmlMoveToRangeStartArray(int var1);

    public int sizeOfCustomXmlMoveToRangeStartArray();

    public void setCustomXmlMoveToRangeStartArray(CTTrackChange[] var1);

    public void setCustomXmlMoveToRangeStartArray(int var1, CTTrackChange var2);

    public CTTrackChange insertNewCustomXmlMoveToRangeStart(int var1);

    public CTTrackChange addNewCustomXmlMoveToRangeStart();

    public void removeCustomXmlMoveToRangeStart(int var1);

    public List<CTMarkup> getCustomXmlMoveToRangeEndList();

    @Deprecated
    public CTMarkup[] getCustomXmlMoveToRangeEndArray();

    public CTMarkup getCustomXmlMoveToRangeEndArray(int var1);

    public int sizeOfCustomXmlMoveToRangeEndArray();

    public void setCustomXmlMoveToRangeEndArray(CTMarkup[] var1);

    public void setCustomXmlMoveToRangeEndArray(int var1, CTMarkup var2);

    public CTMarkup insertNewCustomXmlMoveToRangeEnd(int var1);

    public CTMarkup addNewCustomXmlMoveToRangeEnd();

    public void removeCustomXmlMoveToRangeEnd(int var1);

    public List<CTRunTrackChange> getInsList();

    @Deprecated
    public CTRunTrackChange[] getInsArray();

    public CTRunTrackChange getInsArray(int var1);

    public int sizeOfInsArray();

    public void setInsArray(CTRunTrackChange[] var1);

    public void setInsArray(int var1, CTRunTrackChange var2);

    public CTRunTrackChange insertNewIns(int var1);

    public CTRunTrackChange addNewIns();

    public void removeIns(int var1);

    public List<CTRunTrackChange> getDelList();

    @Deprecated
    public CTRunTrackChange[] getDelArray();

    public CTRunTrackChange getDelArray(int var1);

    public int sizeOfDelArray();

    public void setDelArray(CTRunTrackChange[] var1);

    public void setDelArray(int var1, CTRunTrackChange var2);

    public CTRunTrackChange insertNewDel(int var1);

    public CTRunTrackChange addNewDel();

    public void removeDel(int var1);

    public List<CTRunTrackChange> getMoveFromList();

    @Deprecated
    public CTRunTrackChange[] getMoveFromArray();

    public CTRunTrackChange getMoveFromArray(int var1);

    public int sizeOfMoveFromArray();

    public void setMoveFromArray(CTRunTrackChange[] var1);

    public void setMoveFromArray(int var1, CTRunTrackChange var2);

    public CTRunTrackChange insertNewMoveFrom(int var1);

    public CTRunTrackChange addNewMoveFrom();

    public void removeMoveFrom(int var1);

    public List<CTRunTrackChange> getMoveToList();

    @Deprecated
    public CTRunTrackChange[] getMoveToArray();

    public CTRunTrackChange getMoveToArray(int var1);

    public int sizeOfMoveToArray();

    public void setMoveToArray(CTRunTrackChange[] var1);

    public void setMoveToArray(int var1, CTRunTrackChange var2);

    public CTRunTrackChange insertNewMoveTo(int var1);

    public CTRunTrackChange addNewMoveTo();

    public void removeMoveTo(int var1);

    public List<CTOMathPara> getOMathParaList();

    @Deprecated
    public CTOMathPara[] getOMathParaArray();

    public CTOMathPara getOMathParaArray(int var1);

    public int sizeOfOMathParaArray();

    public void setOMathParaArray(CTOMathPara[] var1);

    public void setOMathParaArray(int var1, CTOMathPara var2);

    public CTOMathPara insertNewOMathPara(int var1);

    public CTOMathPara addNewOMathPara();

    public void removeOMathPara(int var1);

    public List<CTOMath> getOMathList();

    @Deprecated
    public CTOMath[] getOMathArray();

    public CTOMath getOMathArray(int var1);

    public int sizeOfOMathArray();

    public void setOMathArray(CTOMath[] var1);

    public void setOMathArray(int var1, CTOMath var2);

    public CTOMath insertNewOMath(int var1);

    public CTOMath addNewOMath();

    public void removeOMath(int var1);

    public List<CTAcc> getAccList();

    @Deprecated
    public CTAcc[] getAccArray();

    public CTAcc getAccArray(int var1);

    public int sizeOfAccArray();

    public void setAccArray(CTAcc[] var1);

    public void setAccArray(int var1, CTAcc var2);

    public CTAcc insertNewAcc(int var1);

    public CTAcc addNewAcc();

    public void removeAcc(int var1);

    public List<CTBar> getBarList();

    @Deprecated
    public CTBar[] getBarArray();

    public CTBar getBarArray(int var1);

    public int sizeOfBarArray();

    public void setBarArray(CTBar[] var1);

    public void setBarArray(int var1, CTBar var2);

    public CTBar insertNewBar(int var1);

    public CTBar addNewBar();

    public void removeBar(int var1);

    public List<CTBox> getBoxList();

    @Deprecated
    public CTBox[] getBoxArray();

    public CTBox getBoxArray(int var1);

    public int sizeOfBoxArray();

    public void setBoxArray(CTBox[] var1);

    public void setBoxArray(int var1, CTBox var2);

    public CTBox insertNewBox(int var1);

    public CTBox addNewBox();

    public void removeBox(int var1);

    public List<CTBorderBox> getBorderBoxList();

    @Deprecated
    public CTBorderBox[] getBorderBoxArray();

    public CTBorderBox getBorderBoxArray(int var1);

    public int sizeOfBorderBoxArray();

    public void setBorderBoxArray(CTBorderBox[] var1);

    public void setBorderBoxArray(int var1, CTBorderBox var2);

    public CTBorderBox insertNewBorderBox(int var1);

    public CTBorderBox addNewBorderBox();

    public void removeBorderBox(int var1);

    public List<CTD> getDList();

    @Deprecated
    public CTD[] getDArray();

    public CTD getDArray(int var1);

    public int sizeOfDArray();

    public void setDArray(CTD[] var1);

    public void setDArray(int var1, CTD var2);

    public CTD insertNewD(int var1);

    public CTD addNewD();

    public void removeD(int var1);

    public List<CTEqArr> getEqArrList();

    @Deprecated
    public CTEqArr[] getEqArrArray();

    public CTEqArr getEqArrArray(int var1);

    public int sizeOfEqArrArray();

    public void setEqArrArray(CTEqArr[] var1);

    public void setEqArrArray(int var1, CTEqArr var2);

    public CTEqArr insertNewEqArr(int var1);

    public CTEqArr addNewEqArr();

    public void removeEqArr(int var1);

    public List<CTF> getFList();

    @Deprecated
    public CTF[] getFArray();

    public CTF getFArray(int var1);

    public int sizeOfFArray();

    public void setFArray(CTF[] var1);

    public void setFArray(int var1, CTF var2);

    public CTF insertNewF(int var1);

    public CTF addNewF();

    public void removeF(int var1);

    public List<CTFunc> getFuncList();

    @Deprecated
    public CTFunc[] getFuncArray();

    public CTFunc getFuncArray(int var1);

    public int sizeOfFuncArray();

    public void setFuncArray(CTFunc[] var1);

    public void setFuncArray(int var1, CTFunc var2);

    public CTFunc insertNewFunc(int var1);

    public CTFunc addNewFunc();

    public void removeFunc(int var1);

    public List<CTGroupChr> getGroupChrList();

    @Deprecated
    public CTGroupChr[] getGroupChrArray();

    public CTGroupChr getGroupChrArray(int var1);

    public int sizeOfGroupChrArray();

    public void setGroupChrArray(CTGroupChr[] var1);

    public void setGroupChrArray(int var1, CTGroupChr var2);

    public CTGroupChr insertNewGroupChr(int var1);

    public CTGroupChr addNewGroupChr();

    public void removeGroupChr(int var1);

    public List<CTLimLow> getLimLowList();

    @Deprecated
    public CTLimLow[] getLimLowArray();

    public CTLimLow getLimLowArray(int var1);

    public int sizeOfLimLowArray();

    public void setLimLowArray(CTLimLow[] var1);

    public void setLimLowArray(int var1, CTLimLow var2);

    public CTLimLow insertNewLimLow(int var1);

    public CTLimLow addNewLimLow();

    public void removeLimLow(int var1);

    public List<CTLimUpp> getLimUppList();

    @Deprecated
    public CTLimUpp[] getLimUppArray();

    public CTLimUpp getLimUppArray(int var1);

    public int sizeOfLimUppArray();

    public void setLimUppArray(CTLimUpp[] var1);

    public void setLimUppArray(int var1, CTLimUpp var2);

    public CTLimUpp insertNewLimUpp(int var1);

    public CTLimUpp addNewLimUpp();

    public void removeLimUpp(int var1);

    public List<CTM> getMList();

    @Deprecated
    public CTM[] getMArray();

    public CTM getMArray(int var1);

    public int sizeOfMArray();

    public void setMArray(CTM[] var1);

    public void setMArray(int var1, CTM var2);

    public CTM insertNewM(int var1);

    public CTM addNewM();

    public void removeM(int var1);

    public List<CTNary> getNaryList();

    @Deprecated
    public CTNary[] getNaryArray();

    public CTNary getNaryArray(int var1);

    public int sizeOfNaryArray();

    public void setNaryArray(CTNary[] var1);

    public void setNaryArray(int var1, CTNary var2);

    public CTNary insertNewNary(int var1);

    public CTNary addNewNary();

    public void removeNary(int var1);

    public List<CTPhant> getPhantList();

    @Deprecated
    public CTPhant[] getPhantArray();

    public CTPhant getPhantArray(int var1);

    public int sizeOfPhantArray();

    public void setPhantArray(CTPhant[] var1);

    public void setPhantArray(int var1, CTPhant var2);

    public CTPhant insertNewPhant(int var1);

    public CTPhant addNewPhant();

    public void removePhant(int var1);

    public List<CTRad> getRadList();

    @Deprecated
    public CTRad[] getRadArray();

    public CTRad getRadArray(int var1);

    public int sizeOfRadArray();

    public void setRadArray(CTRad[] var1);

    public void setRadArray(int var1, CTRad var2);

    public CTRad insertNewRad(int var1);

    public CTRad addNewRad();

    public void removeRad(int var1);

    public List<CTSPre> getSPreList();

    @Deprecated
    public CTSPre[] getSPreArray();

    public CTSPre getSPreArray(int var1);

    public int sizeOfSPreArray();

    public void setSPreArray(CTSPre[] var1);

    public void setSPreArray(int var1, CTSPre var2);

    public CTSPre insertNewSPre(int var1);

    public CTSPre addNewSPre();

    public void removeSPre(int var1);

    public List<CTSSub> getSSubList();

    @Deprecated
    public CTSSub[] getSSubArray();

    public CTSSub getSSubArray(int var1);

    public int sizeOfSSubArray();

    public void setSSubArray(CTSSub[] var1);

    public void setSSubArray(int var1, CTSSub var2);

    public CTSSub insertNewSSub(int var1);

    public CTSSub addNewSSub();

    public void removeSSub(int var1);

    public List<CTSSubSup> getSSubSupList();

    @Deprecated
    public CTSSubSup[] getSSubSupArray();

    public CTSSubSup getSSubSupArray(int var1);

    public int sizeOfSSubSupArray();

    public void setSSubSupArray(CTSSubSup[] var1);

    public void setSSubSupArray(int var1, CTSSubSup var2);

    public CTSSubSup insertNewSSubSup(int var1);

    public CTSSubSup addNewSSubSup();

    public void removeSSubSup(int var1);

    public List<CTSSup> getSSupList();

    @Deprecated
    public CTSSup[] getSSupArray();

    public CTSSup getSSupArray(int var1);

    public int sizeOfSSupArray();

    public void setSSupArray(CTSSup[] var1);

    public void setSSupArray(int var1, CTSSup var2);

    public CTSSup insertNewSSup(int var1);

    public CTSSup addNewSSup();

    public void removeSSup(int var1);

    public List<org.openxmlformats.schemas.officeDocument.x2006.math.CTR> getR2List();

    @Deprecated
    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR[] getR2Array();

    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR getR2Array(int var1);

    public int sizeOfR2Array();

    public void setR2Array(org.openxmlformats.schemas.officeDocument.x2006.math.CTR[] var1);

    public void setR2Array(int var1, org.openxmlformats.schemas.officeDocument.x2006.math.CTR var2);

    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR insertNewR2(int var1);

    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR addNewR2();

    public void removeR2(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRunTrackChange.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRunTrackChange newInstance() {
            return (CTRunTrackChange)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRunTrackChange newInstance(XmlOptions xmlOptions) {
            return (CTRunTrackChange)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRunTrackChange parse(String string) throws XmlException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRunTrackChange parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRunTrackChange parse(File file) throws XmlException, IOException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRunTrackChange parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRunTrackChange parse(URL uRL) throws XmlException, IOException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRunTrackChange parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRunTrackChange parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRunTrackChange parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRunTrackChange parse(Reader reader) throws XmlException, IOException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRunTrackChange parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRunTrackChange parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRunTrackChange parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRunTrackChange parse(Node node) throws XmlException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRunTrackChange parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRunTrackChange parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRunTrackChange parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRunTrackChange)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

