/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheHierarchies
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedItems
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMembers
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDimensions
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMeasureDimensionMaps
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMeasureGroups
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPCDKPIs
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTupleCache
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheHierarchies;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheSource;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMembers;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDimensions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMeasureDimensionMaps;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMeasureGroups;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPCDKPIs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCacheDefinition;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTupleCache;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;

public class CTPivotCacheDefinitionImpl
extends XmlComplexContentImpl
implements CTPivotCacheDefinition {
    private static final long serialVersionUID = 1L;
    private static final QName CACHESOURCE$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "cacheSource");
    private static final QName CACHEFIELDS$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "cacheFields");
    private static final QName CACHEHIERARCHIES$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "cacheHierarchies");
    private static final QName KPIS$6 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "kpis");
    private static final QName TUPLECACHE$8 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "tupleCache");
    private static final QName CALCULATEDITEMS$10 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "calculatedItems");
    private static final QName CALCULATEDMEMBERS$12 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "calculatedMembers");
    private static final QName DIMENSIONS$14 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "dimensions");
    private static final QName MEASUREGROUPS$16 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "measureGroups");
    private static final QName MAPS$18 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "maps");
    private static final QName EXTLST$20 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");
    private static final QName ID$22 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");
    private static final QName INVALID$24 = new QName("", "invalid");
    private static final QName SAVEDATA$26 = new QName("", "saveData");
    private static final QName REFRESHONLOAD$28 = new QName("", "refreshOnLoad");
    private static final QName OPTIMIZEMEMORY$30 = new QName("", "optimizeMemory");
    private static final QName ENABLEREFRESH$32 = new QName("", "enableRefresh");
    private static final QName REFRESHEDBY$34 = new QName("", "refreshedBy");
    private static final QName REFRESHEDDATE$36 = new QName("", "refreshedDate");
    private static final QName BACKGROUNDQUERY$38 = new QName("", "backgroundQuery");
    private static final QName MISSINGITEMSLIMIT$40 = new QName("", "missingItemsLimit");
    private static final QName CREATEDVERSION$42 = new QName("", "createdVersion");
    private static final QName REFRESHEDVERSION$44 = new QName("", "refreshedVersion");
    private static final QName MINREFRESHABLEVERSION$46 = new QName("", "minRefreshableVersion");
    private static final QName RECORDCOUNT$48 = new QName("", "recordCount");
    private static final QName UPGRADEONREFRESH$50 = new QName("", "upgradeOnRefresh");
    private static final QName TUPLECACHE2$52 = new QName("", "tupleCache");
    private static final QName SUPPORTSUBQUERY$54 = new QName("", "supportSubquery");
    private static final QName SUPPORTADVANCEDDRILL$56 = new QName("", "supportAdvancedDrill");

    public CTPivotCacheDefinitionImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCacheSource getCacheSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCacheSource cTCacheSource = null;
            cTCacheSource = (CTCacheSource)((Object)this.get_store().find_element_user(CACHESOURCE$0, 0));
            if (cTCacheSource == null) {
                return null;
            }
            return cTCacheSource;
        }
    }

    @Override
    public void setCacheSource(CTCacheSource cTCacheSource) {
        this.generatedSetterHelperImpl(cTCacheSource, CACHESOURCE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCacheSource addNewCacheSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCacheSource cTCacheSource = null;
            cTCacheSource = (CTCacheSource)((Object)this.get_store().add_element_user(CACHESOURCE$0));
            return cTCacheSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCacheFields getCacheFields() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCacheFields cTCacheFields = null;
            cTCacheFields = (CTCacheFields)((Object)this.get_store().find_element_user(CACHEFIELDS$2, 0));
            if (cTCacheFields == null) {
                return null;
            }
            return cTCacheFields;
        }
    }

    @Override
    public void setCacheFields(CTCacheFields cTCacheFields) {
        this.generatedSetterHelperImpl(cTCacheFields, CACHEFIELDS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCacheFields addNewCacheFields() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCacheFields cTCacheFields = null;
            cTCacheFields = (CTCacheFields)((Object)this.get_store().add_element_user(CACHEFIELDS$2));
            return cTCacheFields;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCacheHierarchies getCacheHierarchies() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCacheHierarchies cTCacheHierarchies = null;
            cTCacheHierarchies = (CTCacheHierarchies)this.get_store().find_element_user(CACHEHIERARCHIES$4, 0);
            if (cTCacheHierarchies == null) {
                return null;
            }
            return cTCacheHierarchies;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCacheHierarchies() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CACHEHIERARCHIES$4) != 0;
        }
    }

    @Override
    public void setCacheHierarchies(CTCacheHierarchies cTCacheHierarchies) {
        this.generatedSetterHelperImpl((XmlObject)cTCacheHierarchies, CACHEHIERARCHIES$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCacheHierarchies addNewCacheHierarchies() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCacheHierarchies cTCacheHierarchies = null;
            cTCacheHierarchies = (CTCacheHierarchies)this.get_store().add_element_user(CACHEHIERARCHIES$4);
            return cTCacheHierarchies;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCacheHierarchies() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CACHEHIERARCHIES$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPCDKPIs getKpis() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPCDKPIs cTPCDKPIs = null;
            cTPCDKPIs = (CTPCDKPIs)this.get_store().find_element_user(KPIS$6, 0);
            if (cTPCDKPIs == null) {
                return null;
            }
            return cTPCDKPIs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetKpis() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(KPIS$6) != 0;
        }
    }

    @Override
    public void setKpis(CTPCDKPIs cTPCDKPIs) {
        this.generatedSetterHelperImpl((XmlObject)cTPCDKPIs, KPIS$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPCDKPIs addNewKpis() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPCDKPIs cTPCDKPIs = null;
            cTPCDKPIs = (CTPCDKPIs)this.get_store().add_element_user(KPIS$6);
            return cTPCDKPIs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetKpis() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(KPIS$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTupleCache getTupleCache() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTupleCache cTTupleCache = null;
            cTTupleCache = (CTTupleCache)this.get_store().find_element_user(TUPLECACHE$8, 0);
            if (cTTupleCache == null) {
                return null;
            }
            return cTTupleCache;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTupleCache() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TUPLECACHE$8) != 0;
        }
    }

    @Override
    public void setTupleCache(CTTupleCache cTTupleCache) {
        this.generatedSetterHelperImpl((XmlObject)cTTupleCache, TUPLECACHE$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTupleCache addNewTupleCache() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTupleCache cTTupleCache = null;
            cTTupleCache = (CTTupleCache)this.get_store().add_element_user(TUPLECACHE$8);
            return cTTupleCache;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTupleCache() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TUPLECACHE$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCalculatedItems getCalculatedItems() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCalculatedItems cTCalculatedItems = null;
            cTCalculatedItems = (CTCalculatedItems)this.get_store().find_element_user(CALCULATEDITEMS$10, 0);
            if (cTCalculatedItems == null) {
                return null;
            }
            return cTCalculatedItems;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCalculatedItems() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CALCULATEDITEMS$10) != 0;
        }
    }

    @Override
    public void setCalculatedItems(CTCalculatedItems cTCalculatedItems) {
        this.generatedSetterHelperImpl((XmlObject)cTCalculatedItems, CALCULATEDITEMS$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCalculatedItems addNewCalculatedItems() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCalculatedItems cTCalculatedItems = null;
            cTCalculatedItems = (CTCalculatedItems)this.get_store().add_element_user(CALCULATEDITEMS$10);
            return cTCalculatedItems;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCalculatedItems() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CALCULATEDITEMS$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCalculatedMembers getCalculatedMembers() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCalculatedMembers cTCalculatedMembers = null;
            cTCalculatedMembers = (CTCalculatedMembers)this.get_store().find_element_user(CALCULATEDMEMBERS$12, 0);
            if (cTCalculatedMembers == null) {
                return null;
            }
            return cTCalculatedMembers;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCalculatedMembers() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CALCULATEDMEMBERS$12) != 0;
        }
    }

    @Override
    public void setCalculatedMembers(CTCalculatedMembers cTCalculatedMembers) {
        this.generatedSetterHelperImpl((XmlObject)cTCalculatedMembers, CALCULATEDMEMBERS$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCalculatedMembers addNewCalculatedMembers() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCalculatedMembers cTCalculatedMembers = null;
            cTCalculatedMembers = (CTCalculatedMembers)this.get_store().add_element_user(CALCULATEDMEMBERS$12);
            return cTCalculatedMembers;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCalculatedMembers() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CALCULATEDMEMBERS$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDimensions getDimensions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDimensions cTDimensions = null;
            cTDimensions = (CTDimensions)this.get_store().find_element_user(DIMENSIONS$14, 0);
            if (cTDimensions == null) {
                return null;
            }
            return cTDimensions;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDimensions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DIMENSIONS$14) != 0;
        }
    }

    @Override
    public void setDimensions(CTDimensions cTDimensions) {
        this.generatedSetterHelperImpl((XmlObject)cTDimensions, DIMENSIONS$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDimensions addNewDimensions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDimensions cTDimensions = null;
            cTDimensions = (CTDimensions)this.get_store().add_element_user(DIMENSIONS$14);
            return cTDimensions;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDimensions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DIMENSIONS$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMeasureGroups getMeasureGroups() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMeasureGroups cTMeasureGroups = null;
            cTMeasureGroups = (CTMeasureGroups)this.get_store().find_element_user(MEASUREGROUPS$16, 0);
            if (cTMeasureGroups == null) {
                return null;
            }
            return cTMeasureGroups;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMeasureGroups() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MEASUREGROUPS$16) != 0;
        }
    }

    @Override
    public void setMeasureGroups(CTMeasureGroups cTMeasureGroups) {
        this.generatedSetterHelperImpl((XmlObject)cTMeasureGroups, MEASUREGROUPS$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMeasureGroups addNewMeasureGroups() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMeasureGroups cTMeasureGroups = null;
            cTMeasureGroups = (CTMeasureGroups)this.get_store().add_element_user(MEASUREGROUPS$16);
            return cTMeasureGroups;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMeasureGroups() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MEASUREGROUPS$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMeasureDimensionMaps getMaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMeasureDimensionMaps cTMeasureDimensionMaps = null;
            cTMeasureDimensionMaps = (CTMeasureDimensionMaps)this.get_store().find_element_user(MAPS$18, 0);
            if (cTMeasureDimensionMaps == null) {
                return null;
            }
            return cTMeasureDimensionMaps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAPS$18) != 0;
        }
    }

    @Override
    public void setMaps(CTMeasureDimensionMaps cTMeasureDimensionMaps) {
        this.generatedSetterHelperImpl((XmlObject)cTMeasureDimensionMaps, MAPS$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMeasureDimensionMaps addNewMaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMeasureDimensionMaps cTMeasureDimensionMaps = null;
            cTMeasureDimensionMaps = (CTMeasureDimensionMaps)this.get_store().add_element_user(MAPS$18);
            return cTMeasureDimensionMaps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAPS$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$20, 0);
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
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
            return this.get_store().count_elements(EXTLST$20) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$20);
            return cTExtensionList;
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
            this.get_store().remove_element(EXTLST$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$22));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STRelationshipId xgetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRelationshipId sTRelationshipId = null;
            sTRelationshipId = (STRelationshipId)((Object)this.get_store().find_attribute_user(ID$22));
            return sTRelationshipId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ID$22) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setId(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$22));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$22));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetId(STRelationshipId sTRelationshipId) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRelationshipId sTRelationshipId2 = null;
            sTRelationshipId2 = (STRelationshipId)((Object)this.get_store().find_attribute_user(ID$22));
            if (sTRelationshipId2 == null) {
                sTRelationshipId2 = (STRelationshipId)((Object)this.get_store().add_attribute_user(ID$22));
            }
            sTRelationshipId2.set(sTRelationshipId);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ID$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getInvalid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INVALID$24));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(INVALID$24));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetInvalid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(INVALID$24));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(INVALID$24);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetInvalid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(INVALID$24) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setInvalid(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INVALID$24));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(INVALID$24));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetInvalid(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(INVALID$24));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(INVALID$24));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetInvalid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(INVALID$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getSaveData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SAVEDATA$26));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(SAVEDATA$26));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetSaveData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(SAVEDATA$26));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(SAVEDATA$26);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSaveData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SAVEDATA$26) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSaveData(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SAVEDATA$26));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SAVEDATA$26));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSaveData(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(SAVEDATA$26));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(SAVEDATA$26));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSaveData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SAVEDATA$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getRefreshOnLoad() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REFRESHONLOAD$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(REFRESHONLOAD$28));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetRefreshOnLoad() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(REFRESHONLOAD$28));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(REFRESHONLOAD$28);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRefreshOnLoad() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(REFRESHONLOAD$28) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRefreshOnLoad(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REFRESHONLOAD$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REFRESHONLOAD$28));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRefreshOnLoad(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(REFRESHONLOAD$28));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(REFRESHONLOAD$28));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRefreshOnLoad() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(REFRESHONLOAD$28);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getOptimizeMemory() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OPTIMIZEMEMORY$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(OPTIMIZEMEMORY$30));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetOptimizeMemory() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(OPTIMIZEMEMORY$30));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(OPTIMIZEMEMORY$30);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOptimizeMemory() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(OPTIMIZEMEMORY$30) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOptimizeMemory(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OPTIMIZEMEMORY$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(OPTIMIZEMEMORY$30));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOptimizeMemory(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(OPTIMIZEMEMORY$30));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(OPTIMIZEMEMORY$30));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOptimizeMemory() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(OPTIMIZEMEMORY$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getEnableRefresh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENABLEREFRESH$32));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(ENABLEREFRESH$32));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetEnableRefresh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(ENABLEREFRESH$32));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(ENABLEREFRESH$32);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEnableRefresh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ENABLEREFRESH$32) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEnableRefresh(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENABLEREFRESH$32));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ENABLEREFRESH$32));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEnableRefresh(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(ENABLEREFRESH$32));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(ENABLEREFRESH$32));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEnableRefresh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ENABLEREFRESH$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getRefreshedBy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REFRESHEDBY$34));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STXstring xgetRefreshedBy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring = null;
            sTXstring = (STXstring)((Object)this.get_store().find_attribute_user(REFRESHEDBY$34));
            return sTXstring;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRefreshedBy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(REFRESHEDBY$34) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRefreshedBy(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REFRESHEDBY$34));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REFRESHEDBY$34));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRefreshedBy(STXstring sTXstring) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring2 = null;
            sTXstring2 = (STXstring)((Object)this.get_store().find_attribute_user(REFRESHEDBY$34));
            if (sTXstring2 == null) {
                sTXstring2 = (STXstring)((Object)this.get_store().add_attribute_user(REFRESHEDBY$34));
            }
            sTXstring2.set(sTXstring);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRefreshedBy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(REFRESHEDBY$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getRefreshedDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REFRESHEDDATE$36));
            if (simpleValue == null) {
                return 0.0;
            }
            return simpleValue.getDoubleValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDouble xgetRefreshedDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDouble xmlDouble = null;
            xmlDouble = (XmlDouble)((Object)this.get_store().find_attribute_user(REFRESHEDDATE$36));
            return xmlDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRefreshedDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(REFRESHEDDATE$36) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRefreshedDate(double d) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REFRESHEDDATE$36));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REFRESHEDDATE$36));
            }
            simpleValue.setDoubleValue(d);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRefreshedDate(XmlDouble xmlDouble) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlDouble xmlDouble2 = null;
            xmlDouble2 = (XmlDouble)((Object)this.get_store().find_attribute_user(REFRESHEDDATE$36));
            if (xmlDouble2 == null) {
                xmlDouble2 = (XmlDouble)((Object)this.get_store().add_attribute_user(REFRESHEDDATE$36));
            }
            xmlDouble2.set(xmlDouble);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRefreshedDate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(REFRESHEDDATE$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getBackgroundQuery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BACKGROUNDQUERY$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(BACKGROUNDQUERY$38));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetBackgroundQuery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(BACKGROUNDQUERY$38));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(BACKGROUNDQUERY$38);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBackgroundQuery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BACKGROUNDQUERY$38) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBackgroundQuery(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BACKGROUNDQUERY$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BACKGROUNDQUERY$38));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBackgroundQuery(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(BACKGROUNDQUERY$38));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(BACKGROUNDQUERY$38));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBackgroundQuery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BACKGROUNDQUERY$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getMissingItemsLimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MISSINGITEMSLIMIT$40));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetMissingItemsLimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(MISSINGITEMSLIMIT$40));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMissingItemsLimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MISSINGITEMSLIMIT$40) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMissingItemsLimit(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MISSINGITEMSLIMIT$40));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MISSINGITEMSLIMIT$40));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMissingItemsLimit(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(MISSINGITEMSLIMIT$40));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(MISSINGITEMSLIMIT$40));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMissingItemsLimit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MISSINGITEMSLIMIT$40);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short getCreatedVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CREATEDVERSION$42));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(CREATEDVERSION$42));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getShortValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedByte xgetCreatedVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte = null;
            xmlUnsignedByte = (XmlUnsignedByte)((Object)this.get_store().find_attribute_user(CREATEDVERSION$42));
            if (xmlUnsignedByte == null) {
                xmlUnsignedByte = (XmlUnsignedByte)this.get_default_attribute_value(CREATEDVERSION$42);
            }
            return xmlUnsignedByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCreatedVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CREATEDVERSION$42) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCreatedVersion(short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CREATEDVERSION$42));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CREATEDVERSION$42));
            }
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCreatedVersion(XmlUnsignedByte xmlUnsignedByte) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte2 = null;
            xmlUnsignedByte2 = (XmlUnsignedByte)((Object)this.get_store().find_attribute_user(CREATEDVERSION$42));
            if (xmlUnsignedByte2 == null) {
                xmlUnsignedByte2 = (XmlUnsignedByte)((Object)this.get_store().add_attribute_user(CREATEDVERSION$42));
            }
            xmlUnsignedByte2.set(xmlUnsignedByte);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCreatedVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CREATEDVERSION$42);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short getRefreshedVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REFRESHEDVERSION$44));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(REFRESHEDVERSION$44));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getShortValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedByte xgetRefreshedVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte = null;
            xmlUnsignedByte = (XmlUnsignedByte)((Object)this.get_store().find_attribute_user(REFRESHEDVERSION$44));
            if (xmlUnsignedByte == null) {
                xmlUnsignedByte = (XmlUnsignedByte)this.get_default_attribute_value(REFRESHEDVERSION$44);
            }
            return xmlUnsignedByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRefreshedVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(REFRESHEDVERSION$44) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRefreshedVersion(short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REFRESHEDVERSION$44));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REFRESHEDVERSION$44));
            }
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRefreshedVersion(XmlUnsignedByte xmlUnsignedByte) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte2 = null;
            xmlUnsignedByte2 = (XmlUnsignedByte)((Object)this.get_store().find_attribute_user(REFRESHEDVERSION$44));
            if (xmlUnsignedByte2 == null) {
                xmlUnsignedByte2 = (XmlUnsignedByte)((Object)this.get_store().add_attribute_user(REFRESHEDVERSION$44));
            }
            xmlUnsignedByte2.set(xmlUnsignedByte);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRefreshedVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(REFRESHEDVERSION$44);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short getMinRefreshableVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MINREFRESHABLEVERSION$46));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(MINREFRESHABLEVERSION$46));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getShortValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedByte xgetMinRefreshableVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte = null;
            xmlUnsignedByte = (XmlUnsignedByte)((Object)this.get_store().find_attribute_user(MINREFRESHABLEVERSION$46));
            if (xmlUnsignedByte == null) {
                xmlUnsignedByte = (XmlUnsignedByte)this.get_default_attribute_value(MINREFRESHABLEVERSION$46);
            }
            return xmlUnsignedByte;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMinRefreshableVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MINREFRESHABLEVERSION$46) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMinRefreshableVersion(short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MINREFRESHABLEVERSION$46));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MINREFRESHABLEVERSION$46));
            }
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMinRefreshableVersion(XmlUnsignedByte xmlUnsignedByte) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedByte xmlUnsignedByte2 = null;
            xmlUnsignedByte2 = (XmlUnsignedByte)((Object)this.get_store().find_attribute_user(MINREFRESHABLEVERSION$46));
            if (xmlUnsignedByte2 == null) {
                xmlUnsignedByte2 = (XmlUnsignedByte)((Object)this.get_store().add_attribute_user(MINREFRESHABLEVERSION$46));
            }
            xmlUnsignedByte2.set(xmlUnsignedByte);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMinRefreshableVersion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MINREFRESHABLEVERSION$46);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getRecordCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RECORDCOUNT$48));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetRecordCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(RECORDCOUNT$48));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRecordCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RECORDCOUNT$48) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRecordCount(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RECORDCOUNT$48));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RECORDCOUNT$48));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRecordCount(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(RECORDCOUNT$48));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(RECORDCOUNT$48));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRecordCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RECORDCOUNT$48);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getUpgradeOnRefresh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(UPGRADEONREFRESH$50));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(UPGRADEONREFRESH$50));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetUpgradeOnRefresh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(UPGRADEONREFRESH$50));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(UPGRADEONREFRESH$50);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUpgradeOnRefresh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(UPGRADEONREFRESH$50) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUpgradeOnRefresh(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(UPGRADEONREFRESH$50));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(UPGRADEONREFRESH$50));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUpgradeOnRefresh(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(UPGRADEONREFRESH$50));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(UPGRADEONREFRESH$50));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUpgradeOnRefresh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(UPGRADEONREFRESH$50);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getTupleCache2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TUPLECACHE2$52));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(TUPLECACHE2$52));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetTupleCache2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(TUPLECACHE2$52));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(TUPLECACHE2$52);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTupleCache2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TUPLECACHE2$52) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTupleCache2(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TUPLECACHE2$52));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TUPLECACHE2$52));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTupleCache2(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(TUPLECACHE2$52));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(TUPLECACHE2$52));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTupleCache2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TUPLECACHE2$52);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getSupportSubquery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SUPPORTSUBQUERY$54));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(SUPPORTSUBQUERY$54));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetSupportSubquery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(SUPPORTSUBQUERY$54));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(SUPPORTSUBQUERY$54);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSupportSubquery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SUPPORTSUBQUERY$54) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSupportSubquery(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SUPPORTSUBQUERY$54));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SUPPORTSUBQUERY$54));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSupportSubquery(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(SUPPORTSUBQUERY$54));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(SUPPORTSUBQUERY$54));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSupportSubquery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SUPPORTSUBQUERY$54);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getSupportAdvancedDrill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SUPPORTADVANCEDDRILL$56));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(SUPPORTADVANCEDDRILL$56));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetSupportAdvancedDrill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(SUPPORTADVANCEDDRILL$56));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(SUPPORTADVANCEDDRILL$56);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSupportAdvancedDrill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SUPPORTADVANCEDDRILL$56) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSupportAdvancedDrill(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SUPPORTADVANCEDDRILL$56));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SUPPORTADVANCEDDRILL$56));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSupportAdvancedDrill(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(SUPPORTADVANCEDDRILL$56));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(SUPPORTADVANCEDDRILL$56));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSupportAdvancedDrill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SUPPORTADVANCEDDRILL$56);
        }
    }
}

