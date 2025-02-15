/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;

public class CTGroupShapeImpl
extends XmlComplexContentImpl
implements CTGroupShape {
    private static final long serialVersionUID = 1L;
    private static final QName NVGRPSPPR$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvGrpSpPr");
    private static final QName GRPSPPR$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "grpSpPr");
    private static final QName SP$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sp");
    private static final QName GRPSP$6 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "grpSp");
    private static final QName GRAPHICFRAME$8 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "graphicFrame");
    private static final QName CXNSP$10 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cxnSp");
    private static final QName PIC$12 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "pic");
    private static final QName EXTLST$14 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");

    public CTGroupShapeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupShapeNonVisual getNvGrpSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupShapeNonVisual cTGroupShapeNonVisual = null;
            cTGroupShapeNonVisual = (CTGroupShapeNonVisual)((Object)this.get_store().find_element_user(NVGRPSPPR$0, 0));
            if (cTGroupShapeNonVisual == null) {
                return null;
            }
            return cTGroupShapeNonVisual;
        }
    }

    @Override
    public void setNvGrpSpPr(CTGroupShapeNonVisual cTGroupShapeNonVisual) {
        this.generatedSetterHelperImpl(cTGroupShapeNonVisual, NVGRPSPPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupShapeNonVisual addNewNvGrpSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupShapeNonVisual cTGroupShapeNonVisual = null;
            cTGroupShapeNonVisual = (CTGroupShapeNonVisual)((Object)this.get_store().add_element_user(NVGRPSPPR$0));
            return cTGroupShapeNonVisual;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupShapeProperties getGrpSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupShapeProperties cTGroupShapeProperties = null;
            cTGroupShapeProperties = (CTGroupShapeProperties)((Object)this.get_store().find_element_user(GRPSPPR$2, 0));
            if (cTGroupShapeProperties == null) {
                return null;
            }
            return cTGroupShapeProperties;
        }
    }

    @Override
    public void setGrpSpPr(CTGroupShapeProperties cTGroupShapeProperties) {
        this.generatedSetterHelperImpl(cTGroupShapeProperties, GRPSPPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupShapeProperties addNewGrpSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupShapeProperties cTGroupShapeProperties = null;
            cTGroupShapeProperties = (CTGroupShapeProperties)((Object)this.get_store().add_element_user(GRPSPPR$2));
            return cTGroupShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTShape> getSpList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SpList
            extends AbstractList<CTShape> {
                SpList() {
                }

                @Override
                public CTShape get(int n) {
                    return CTGroupShapeImpl.this.getSpArray(n);
                }

                @Override
                public CTShape set(int n, CTShape cTShape) {
                    CTShape cTShape2 = CTGroupShapeImpl.this.getSpArray(n);
                    CTGroupShapeImpl.this.setSpArray(n, cTShape);
                    return cTShape2;
                }

                @Override
                public void add(int n, CTShape cTShape) {
                    CTGroupShapeImpl.this.insertNewSp(n).set(cTShape);
                }

                @Override
                public CTShape remove(int n) {
                    CTShape cTShape = CTGroupShapeImpl.this.getSpArray(n);
                    CTGroupShapeImpl.this.removeSp(n);
                    return cTShape;
                }

                @Override
                public int size() {
                    return CTGroupShapeImpl.this.sizeOfSpArray();
                }
            }
            return new SpList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTShape[] getSpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SP$4, arrayList);
            CTShape[] cTShapeArray = new CTShape[arrayList.size()];
            arrayList.toArray(cTShapeArray);
            return cTShapeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape getSpArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape cTShape = null;
            cTShape = (CTShape)((Object)this.get_store().find_element_user(SP$4, n));
            if (cTShape == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SP$4);
        }
    }

    @Override
    public void setSpArray(CTShape[] cTShapeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTShapeArray, SP$4);
    }

    @Override
    public void setSpArray(int n, CTShape cTShape) {
        this.generatedSetterHelperImpl(cTShape, SP$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape insertNewSp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape cTShape = null;
            cTShape = (CTShape)((Object)this.get_store().insert_element_user(SP$4, n));
            return cTShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape addNewSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape cTShape = null;
            cTShape = (CTShape)((Object)this.get_store().add_element_user(SP$4));
            return cTShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SP$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGroupShape> getGrpSpList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GrpSpList
            extends AbstractList<CTGroupShape> {
                GrpSpList() {
                }

                @Override
                public CTGroupShape get(int n) {
                    return CTGroupShapeImpl.this.getGrpSpArray(n);
                }

                @Override
                public CTGroupShape set(int n, CTGroupShape cTGroupShape) {
                    CTGroupShape cTGroupShape2 = CTGroupShapeImpl.this.getGrpSpArray(n);
                    CTGroupShapeImpl.this.setGrpSpArray(n, cTGroupShape);
                    return cTGroupShape2;
                }

                @Override
                public void add(int n, CTGroupShape cTGroupShape) {
                    CTGroupShapeImpl.this.insertNewGrpSp(n).set(cTGroupShape);
                }

                @Override
                public CTGroupShape remove(int n) {
                    CTGroupShape cTGroupShape = CTGroupShapeImpl.this.getGrpSpArray(n);
                    CTGroupShapeImpl.this.removeGrpSp(n);
                    return cTGroupShape;
                }

                @Override
                public int size() {
                    return CTGroupShapeImpl.this.sizeOfGrpSpArray();
                }
            }
            return new GrpSpList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGroupShape[] getGrpSpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GRPSP$6, arrayList);
            CTGroupShape[] cTGroupShapeArray = new CTGroupShape[arrayList.size()];
            arrayList.toArray(cTGroupShapeArray);
            return cTGroupShapeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupShape getGrpSpArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupShape cTGroupShape = null;
            cTGroupShape = (CTGroupShape)((Object)this.get_store().find_element_user(GRPSP$6, n));
            if (cTGroupShape == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGroupShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGrpSpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRPSP$6);
        }
    }

    @Override
    public void setGrpSpArray(CTGroupShape[] cTGroupShapeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTGroupShapeArray, GRPSP$6);
    }

    @Override
    public void setGrpSpArray(int n, CTGroupShape cTGroupShape) {
        this.generatedSetterHelperImpl(cTGroupShape, GRPSP$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupShape insertNewGrpSp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupShape cTGroupShape = null;
            cTGroupShape = (CTGroupShape)((Object)this.get_store().insert_element_user(GRPSP$6, n));
            return cTGroupShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupShape addNewGrpSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupShape cTGroupShape = null;
            cTGroupShape = (CTGroupShape)((Object)this.get_store().add_element_user(GRPSP$6));
            return cTGroupShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGrpSp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRPSP$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGraphicalObjectFrame> getGraphicFrameList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GraphicFrameList
            extends AbstractList<CTGraphicalObjectFrame> {
                GraphicFrameList() {
                }

                @Override
                public CTGraphicalObjectFrame get(int n) {
                    return CTGroupShapeImpl.this.getGraphicFrameArray(n);
                }

                @Override
                public CTGraphicalObjectFrame set(int n, CTGraphicalObjectFrame cTGraphicalObjectFrame) {
                    CTGraphicalObjectFrame cTGraphicalObjectFrame2 = CTGroupShapeImpl.this.getGraphicFrameArray(n);
                    CTGroupShapeImpl.this.setGraphicFrameArray(n, cTGraphicalObjectFrame);
                    return cTGraphicalObjectFrame2;
                }

                @Override
                public void add(int n, CTGraphicalObjectFrame cTGraphicalObjectFrame) {
                    CTGroupShapeImpl.this.insertNewGraphicFrame(n).set(cTGraphicalObjectFrame);
                }

                @Override
                public CTGraphicalObjectFrame remove(int n) {
                    CTGraphicalObjectFrame cTGraphicalObjectFrame = CTGroupShapeImpl.this.getGraphicFrameArray(n);
                    CTGroupShapeImpl.this.removeGraphicFrame(n);
                    return cTGraphicalObjectFrame;
                }

                @Override
                public int size() {
                    return CTGroupShapeImpl.this.sizeOfGraphicFrameArray();
                }
            }
            return new GraphicFrameList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGraphicalObjectFrame[] getGraphicFrameArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GRAPHICFRAME$8, arrayList);
            CTGraphicalObjectFrame[] cTGraphicalObjectFrameArray = new CTGraphicalObjectFrame[arrayList.size()];
            arrayList.toArray(cTGraphicalObjectFrameArray);
            return cTGraphicalObjectFrameArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObjectFrame getGraphicFrameArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObjectFrame cTGraphicalObjectFrame = null;
            cTGraphicalObjectFrame = (CTGraphicalObjectFrame)((Object)this.get_store().find_element_user(GRAPHICFRAME$8, n));
            if (cTGraphicalObjectFrame == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGraphicalObjectFrame;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGraphicFrameArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRAPHICFRAME$8);
        }
    }

    @Override
    public void setGraphicFrameArray(CTGraphicalObjectFrame[] cTGraphicalObjectFrameArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTGraphicalObjectFrameArray, GRAPHICFRAME$8);
    }

    @Override
    public void setGraphicFrameArray(int n, CTGraphicalObjectFrame cTGraphicalObjectFrame) {
        this.generatedSetterHelperImpl(cTGraphicalObjectFrame, GRAPHICFRAME$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObjectFrame insertNewGraphicFrame(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObjectFrame cTGraphicalObjectFrame = null;
            cTGraphicalObjectFrame = (CTGraphicalObjectFrame)((Object)this.get_store().insert_element_user(GRAPHICFRAME$8, n));
            return cTGraphicalObjectFrame;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObjectFrame addNewGraphicFrame() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObjectFrame cTGraphicalObjectFrame = null;
            cTGraphicalObjectFrame = (CTGraphicalObjectFrame)((Object)this.get_store().add_element_user(GRAPHICFRAME$8));
            return cTGraphicalObjectFrame;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGraphicFrame(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRAPHICFRAME$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTConnector> getCxnSpList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CxnSpList
            extends AbstractList<CTConnector> {
                CxnSpList() {
                }

                @Override
                public CTConnector get(int n) {
                    return CTGroupShapeImpl.this.getCxnSpArray(n);
                }

                @Override
                public CTConnector set(int n, CTConnector cTConnector) {
                    CTConnector cTConnector2 = CTGroupShapeImpl.this.getCxnSpArray(n);
                    CTGroupShapeImpl.this.setCxnSpArray(n, cTConnector);
                    return cTConnector2;
                }

                @Override
                public void add(int n, CTConnector cTConnector) {
                    CTGroupShapeImpl.this.insertNewCxnSp(n).set(cTConnector);
                }

                @Override
                public CTConnector remove(int n) {
                    CTConnector cTConnector = CTGroupShapeImpl.this.getCxnSpArray(n);
                    CTGroupShapeImpl.this.removeCxnSp(n);
                    return cTConnector;
                }

                @Override
                public int size() {
                    return CTGroupShapeImpl.this.sizeOfCxnSpArray();
                }
            }
            return new CxnSpList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTConnector[] getCxnSpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CXNSP$10, arrayList);
            CTConnector[] cTConnectorArray = new CTConnector[arrayList.size()];
            arrayList.toArray(cTConnectorArray);
            return cTConnectorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnector getCxnSpArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnector cTConnector = null;
            cTConnector = (CTConnector)((Object)this.get_store().find_element_user(CXNSP$10, n));
            if (cTConnector == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTConnector;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCxnSpArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CXNSP$10);
        }
    }

    @Override
    public void setCxnSpArray(CTConnector[] cTConnectorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTConnectorArray, CXNSP$10);
    }

    @Override
    public void setCxnSpArray(int n, CTConnector cTConnector) {
        this.generatedSetterHelperImpl(cTConnector, CXNSP$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnector insertNewCxnSp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnector cTConnector = null;
            cTConnector = (CTConnector)((Object)this.get_store().insert_element_user(CXNSP$10, n));
            return cTConnector;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnector addNewCxnSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnector cTConnector = null;
            cTConnector = (CTConnector)((Object)this.get_store().add_element_user(CXNSP$10));
            return cTConnector;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCxnSp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CXNSP$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPicture> getPicList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PicList
            extends AbstractList<CTPicture> {
                PicList() {
                }

                @Override
                public CTPicture get(int n) {
                    return CTGroupShapeImpl.this.getPicArray(n);
                }

                @Override
                public CTPicture set(int n, CTPicture cTPicture) {
                    CTPicture cTPicture2 = CTGroupShapeImpl.this.getPicArray(n);
                    CTGroupShapeImpl.this.setPicArray(n, cTPicture);
                    return cTPicture2;
                }

                @Override
                public void add(int n, CTPicture cTPicture) {
                    CTGroupShapeImpl.this.insertNewPic(n).set(cTPicture);
                }

                @Override
                public CTPicture remove(int n) {
                    CTPicture cTPicture = CTGroupShapeImpl.this.getPicArray(n);
                    CTGroupShapeImpl.this.removePic(n);
                    return cTPicture;
                }

                @Override
                public int size() {
                    return CTGroupShapeImpl.this.sizeOfPicArray();
                }
            }
            return new PicList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPicture[] getPicArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PIC$12, arrayList);
            CTPicture[] cTPictureArray = new CTPicture[arrayList.size()];
            arrayList.toArray(cTPictureArray);
            return cTPictureArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPicture getPicArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPicture cTPicture = null;
            cTPicture = (CTPicture)((Object)this.get_store().find_element_user(PIC$12, n));
            if (cTPicture == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPicture;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPicArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PIC$12);
        }
    }

    @Override
    public void setPicArray(CTPicture[] cTPictureArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPictureArray, PIC$12);
    }

    @Override
    public void setPicArray(int n, CTPicture cTPicture) {
        this.generatedSetterHelperImpl(cTPicture, PIC$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPicture insertNewPic(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPicture cTPicture = null;
            cTPicture = (CTPicture)((Object)this.get_store().insert_element_user(PIC$12, n));
            return cTPicture;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPicture addNewPic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPicture cTPicture = null;
            cTPicture = (CTPicture)((Object)this.get_store().add_element_user(PIC$12));
            return cTPicture;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePic(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PIC$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionListModify getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionListModify cTExtensionListModify = null;
            cTExtensionListModify = (CTExtensionListModify)this.get_store().find_element_user(EXTLST$14, 0);
            if (cTExtensionListModify == null) {
                return null;
            }
            return cTExtensionListModify;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$14) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionListModify cTExtensionListModify) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionListModify, EXTLST$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionListModify addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionListModify cTExtensionListModify = null;
            cTExtensionListModify = (CTExtensionListModify)this.get_store().add_element_user(EXTLST$14);
            return cTExtensionListModify;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$14, 0);
        }
    }
}

