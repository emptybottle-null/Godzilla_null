/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package galaxy.jsp.encounter.xml;

import galaxy.jsp.encounter.util.Const;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;

public class Options {
    public List<Pair<String, Map<String, String>>> extTags = new ArrayList<Pair<String, Map<String, String>>>();
    public String fileType = ".jsp";
    public boolean attributeEscape = true;
    public String namespace = "jsp";
    public String xmlVersion = "1.0";
    public String jspVersion = "1.2";
    public String charsetMagic = null;
    public boolean removeBom = false;
    public boolean charsetDeclareLater = false;
    public String charsetDeclare = "utf-8";
    public String codeEscapeType;
    public boolean removeJspRootTag = false;
    public String charsetDeclareAttr = Const.DeclareAttr.pageEncoding.name();
    public String attrEscapeType = Const.EscapeType.entityNormal.name();
    public String txtEscapeType = Const.EscapeType.entityNormal.name();
    public Map<String, String> controllerSpaceReplace = null;
    public int entityEscapeType = 10;
    public int cdataWrapCap = -1;
    public boolean tagSetPropertyOn = false;
    public boolean tagUseBeanOn = false;

    public String toString() {
        return "Options{extTags=" + this.extTags + ", fileType='" + this.fileType + '\'' + ", attributeEscape=" + this.attributeEscape + ", namespace='" + this.namespace + '\'' + ", xmlVersion='" + this.xmlVersion + '\'' + ", jspVersion='" + this.jspVersion + '\'' + ", charsetMagic='" + this.charsetMagic + '\'' + ", removeBom=" + this.removeBom + ", charsetDeclareLater=" + this.charsetDeclareLater + ", charsetDeclare='" + this.charsetDeclare + '\'' + ", codeEscapeType='" + this.codeEscapeType + '\'' + ", removeJspRootTag=" + this.removeJspRootTag + ", charsetDeclareAttr='" + this.charsetDeclareAttr + '\'' + ", attrEscapeType='" + this.attrEscapeType + '\'' + ", txtEscapeType='" + this.txtEscapeType + '\'' + ", controllerSpaceReplace=" + this.controllerSpaceReplace + ", entityEscapeType=" + this.entityEscapeType + ", cdataWrapCap=" + this.cdataWrapCap + ", tagSetPropertyOn=" + this.tagSetPropertyOn + ", tagUseBeanOn=" + this.tagUseBeanOn + '}';
    }
}

