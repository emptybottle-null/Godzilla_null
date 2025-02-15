/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocGrid
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnProps
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLineNumber
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPrChange
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectType
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLineNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;
import org.w3c.dom.Node;

public interface CTSectPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSectPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsectpr1123type");

    public List<CTHdrFtrRef> getHeaderReferenceList();

    @Deprecated
    public CTHdrFtrRef[] getHeaderReferenceArray();

    public CTHdrFtrRef getHeaderReferenceArray(int var1);

    public int sizeOfHeaderReferenceArray();

    public void setHeaderReferenceArray(CTHdrFtrRef[] var1);

    public void setHeaderReferenceArray(int var1, CTHdrFtrRef var2);

    public CTHdrFtrRef insertNewHeaderReference(int var1);

    public CTHdrFtrRef addNewHeaderReference();

    public void removeHeaderReference(int var1);

    public List<CTHdrFtrRef> getFooterReferenceList();

    @Deprecated
    public CTHdrFtrRef[] getFooterReferenceArray();

    public CTHdrFtrRef getFooterReferenceArray(int var1);

    public int sizeOfFooterReferenceArray();

    public void setFooterReferenceArray(CTHdrFtrRef[] var1);

    public void setFooterReferenceArray(int var1, CTHdrFtrRef var2);

    public CTHdrFtrRef insertNewFooterReference(int var1);

    public CTHdrFtrRef addNewFooterReference();

    public void removeFooterReference(int var1);

    public CTFtnProps getFootnotePr();

    public boolean isSetFootnotePr();

    public void setFootnotePr(CTFtnProps var1);

    public CTFtnProps addNewFootnotePr();

    public void unsetFootnotePr();

    public CTEdnProps getEndnotePr();

    public boolean isSetEndnotePr();

    public void setEndnotePr(CTEdnProps var1);

    public CTEdnProps addNewEndnotePr();

    public void unsetEndnotePr();

    public CTSectType getType();

    public boolean isSetType();

    public void setType(CTSectType var1);

    public CTSectType addNewType();

    public void unsetType();

    public CTPageSz getPgSz();

    public boolean isSetPgSz();

    public void setPgSz(CTPageSz var1);

    public CTPageSz addNewPgSz();

    public void unsetPgSz();

    public CTPageMar getPgMar();

    public boolean isSetPgMar();

    public void setPgMar(CTPageMar var1);

    public CTPageMar addNewPgMar();

    public void unsetPgMar();

    public CTPaperSource getPaperSrc();

    public boolean isSetPaperSrc();

    public void setPaperSrc(CTPaperSource var1);

    public CTPaperSource addNewPaperSrc();

    public void unsetPaperSrc();

    public CTPageBorders getPgBorders();

    public boolean isSetPgBorders();

    public void setPgBorders(CTPageBorders var1);

    public CTPageBorders addNewPgBorders();

    public void unsetPgBorders();

    public CTLineNumber getLnNumType();

    public boolean isSetLnNumType();

    public void setLnNumType(CTLineNumber var1);

    public CTLineNumber addNewLnNumType();

    public void unsetLnNumType();

    public CTPageNumber getPgNumType();

    public boolean isSetPgNumType();

    public void setPgNumType(CTPageNumber var1);

    public CTPageNumber addNewPgNumType();

    public void unsetPgNumType();

    public CTColumns getCols();

    public boolean isSetCols();

    public void setCols(CTColumns var1);

    public CTColumns addNewCols();

    public void unsetCols();

    public CTOnOff getFormProt();

    public boolean isSetFormProt();

    public void setFormProt(CTOnOff var1);

    public CTOnOff addNewFormProt();

    public void unsetFormProt();

    public CTVerticalJc getVAlign();

    public boolean isSetVAlign();

    public void setVAlign(CTVerticalJc var1);

    public CTVerticalJc addNewVAlign();

    public void unsetVAlign();

    public CTOnOff getNoEndnote();

    public boolean isSetNoEndnote();

    public void setNoEndnote(CTOnOff var1);

    public CTOnOff addNewNoEndnote();

    public void unsetNoEndnote();

    public CTOnOff getTitlePg();

    public boolean isSetTitlePg();

    public void setTitlePg(CTOnOff var1);

    public CTOnOff addNewTitlePg();

    public void unsetTitlePg();

    public CTTextDirection getTextDirection();

    public boolean isSetTextDirection();

    public void setTextDirection(CTTextDirection var1);

    public CTTextDirection addNewTextDirection();

    public void unsetTextDirection();

    public CTOnOff getBidi();

    public boolean isSetBidi();

    public void setBidi(CTOnOff var1);

    public CTOnOff addNewBidi();

    public void unsetBidi();

    public CTOnOff getRtlGutter();

    public boolean isSetRtlGutter();

    public void setRtlGutter(CTOnOff var1);

    public CTOnOff addNewRtlGutter();

    public void unsetRtlGutter();

    public CTDocGrid getDocGrid();

    public boolean isSetDocGrid();

    public void setDocGrid(CTDocGrid var1);

    public CTDocGrid addNewDocGrid();

    public void unsetDocGrid();

    public CTRel getPrinterSettings();

    public boolean isSetPrinterSettings();

    public void setPrinterSettings(CTRel var1);

    public CTRel addNewPrinterSettings();

    public void unsetPrinterSettings();

    public CTSectPrChange getSectPrChange();

    public boolean isSetSectPrChange();

    public void setSectPrChange(CTSectPrChange var1);

    public CTSectPrChange addNewSectPrChange();

    public void unsetSectPrChange();

    public byte[] getRsidRPr();

    public STLongHexNumber xgetRsidRPr();

    public boolean isSetRsidRPr();

    public void setRsidRPr(byte[] var1);

    public void xsetRsidRPr(STLongHexNumber var1);

    public void unsetRsidRPr();

    public byte[] getRsidDel();

    public STLongHexNumber xgetRsidDel();

    public boolean isSetRsidDel();

    public void setRsidDel(byte[] var1);

    public void xsetRsidDel(STLongHexNumber var1);

    public void unsetRsidDel();

    public byte[] getRsidR();

    public STLongHexNumber xgetRsidR();

    public boolean isSetRsidR();

    public void setRsidR(byte[] var1);

    public void xsetRsidR(STLongHexNumber var1);

    public void unsetRsidR();

    public byte[] getRsidSect();

    public STLongHexNumber xgetRsidSect();

    public boolean isSetRsidSect();

    public void setRsidSect(byte[] var1);

    public void xsetRsidSect(STLongHexNumber var1);

    public void unsetRsidSect();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSectPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSectPr newInstance() {
            return (CTSectPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSectPr newInstance(XmlOptions xmlOptions) {
            return (CTSectPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSectPr parse(String string) throws XmlException {
            return (CTSectPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSectPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSectPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSectPr parse(File file) throws XmlException, IOException {
            return (CTSectPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSectPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSectPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSectPr parse(URL uRL) throws XmlException, IOException {
            return (CTSectPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSectPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSectPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSectPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSectPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSectPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSectPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSectPr parse(Reader reader) throws XmlException, IOException {
            return (CTSectPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSectPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSectPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSectPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSectPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSectPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSectPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSectPr parse(Node node) throws XmlException {
            return (CTSectPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSectPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSectPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSectPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSectPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSectPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSectPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

