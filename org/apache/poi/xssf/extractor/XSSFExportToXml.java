/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.extractor;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFMap;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
import org.apache.poi.xssf.usermodel.helpers.XSSFSingleXmlCell;
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XSSFExportToXml
implements Comparator<String> {
    private static final POILogger LOG = POILogFactory.getLogger(XSSFExportToXml.class);
    private XSSFMap map;
    private final HashMap<String, Integer> indexMap = new HashMap();

    public XSSFExportToXml(XSSFMap map) {
        this.map = map;
    }

    public void exportToXML(OutputStream os, boolean validate) throws SAXException, TransformerException {
        this.exportToXML(os, "UTF-8", validate);
    }

    public void exportToXML(OutputStream os, String encoding, boolean validate) throws SAXException, TransformerException {
        List<XSSFSingleXmlCell> singleXMLCells = this.map.getRelatedSingleXMLCell();
        List<XSSFTable> tables = this.map.getRelatedTables();
        String rootElement = this.map.getCtMap().getRootElement();
        Document doc = DocumentHelper.createDocument();
        Element root = this.isNamespaceDeclared() ? doc.createElementNS(this.getNamespace(), rootElement) : doc.createElementNS("", rootElement);
        doc.appendChild(root);
        Vector<String> xpaths = new Vector<String>();
        HashMap<String, XSSFSingleXmlCell> singleXmlCellsMappings = new HashMap<String, XSSFSingleXmlCell>();
        HashMap<String, XSSFTable> tableMappings = new HashMap<String, XSSFTable>();
        for (XSSFSingleXmlCell simpleXmlCell : singleXMLCells) {
            xpaths.add(simpleXmlCell.getXpath());
            singleXmlCellsMappings.put(simpleXmlCell.getXpath(), simpleXmlCell);
        }
        for (XSSFTable table : tables) {
            String commonXPath = table.getCommonXpath();
            xpaths.add(commonXPath);
            tableMappings.put(commonXPath, table);
        }
        this.indexMap.clear();
        xpaths.sort(this);
        this.indexMap.clear();
        for (String xpath : xpaths) {
            XSSFCell cell;
            XSSFSingleXmlCell simpleXmlCell = (XSSFSingleXmlCell)singleXmlCellsMappings.get(xpath);
            XSSFTable table = (XSSFTable)tableMappings.get(xpath);
            if (xpath.matches(".*\\[.*")) continue;
            if (simpleXmlCell != null && (cell = simpleXmlCell.getReferencedCell()) != null) {
                Node currentNode = this.getNodeByXPath(xpath, doc.getFirstChild(), doc, false);
                this.mapCellOnNode(cell, currentNode);
                if ("".equals(currentNode.getTextContent()) && currentNode.getParentNode() != null) {
                    currentNode.getParentNode().removeChild(currentNode);
                }
            }
            if (table == null) continue;
            List<XSSFTableColumn> tableColumns = table.getColumns();
            XSSFSheet sheet = table.getXSSFSheet();
            int startRow = table.getStartCellReference().getRow() + table.getHeaderRowCount();
            int endRow = table.getEndCellReference().getRow();
            for (int i = startRow; i <= endRow; ++i) {
                XSSFRow row = sheet.getRow(i);
                Node tableRootNode = this.getNodeByXPath(table.getCommonXpath(), doc.getFirstChild(), doc, true);
                short startColumnIndex = table.getStartCellReference().getCol();
                for (XSSFTableColumn tableColumn : tableColumns) {
                    XSSFXmlColumnPr xmlColumnPr;
                    XSSFCell cell2 = row.getCell(startColumnIndex + tableColumn.getColumnIndex());
                    if (cell2 == null || (xmlColumnPr = tableColumn.getXmlColumnPr()) == null) continue;
                    String localXPath = xmlColumnPr.getLocalXPath();
                    Node currentNode = this.getNodeByXPath(localXPath, tableRootNode, doc, false);
                    this.mapCellOnNode(cell2, currentNode);
                }
            }
        }
        boolean isValid = true;
        if (validate) {
            isValid = this.isValid(doc);
        }
        if (isValid) {
            Transformer trans = XMLHelper.newTransformer();
            trans.setOutputProperty("omit-xml-declaration", "yes");
            trans.setOutputProperty("indent", "yes");
            trans.setOutputProperty("encoding", encoding);
            StreamResult result = new StreamResult(os);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
        }
    }

    private boolean isValid(Document xml) throws SAXException {
        try {
            SchemaFactory factory = XMLHelper.getSchemaFactory();
            DOMSource source = new DOMSource(this.map.getSchema());
            Schema schema = factory.newSchema(source);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(xml));
            return true;
        } catch (IOException e) {
            LOG.log(7, "document is not valid", e);
            return false;
        }
    }

    private void mapCellOnNode(XSSFCell cell, Node node) {
        String value = "";
        switch (cell.getCellType()) {
            case STRING: {
                value = cell.getStringCellValue();
                break;
            }
            case BOOLEAN: {
                value = value + cell.getBooleanCellValue();
                break;
            }
            case ERROR: {
                value = cell.getErrorCellString();
                break;
            }
            case FORMULA: {
                if (cell.getCachedFormulaResultType() == CellType.STRING) {
                    value = cell.getStringCellValue();
                    break;
                }
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = this.getFormattedDate(cell);
                    break;
                }
                value = value + cell.getNumericCellValue();
                break;
            }
            case NUMERIC: {
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = this.getFormattedDate(cell);
                    break;
                }
                value = value + cell.getRawValue();
                break;
            }
        }
        if (node instanceof Element) {
            Element currentElement = (Element)node;
            currentElement.setTextContent(value);
        } else {
            node.setNodeValue(value);
        }
    }

    private String removeNamespace(String elementName) {
        return elementName.matches(".*:.*") ? elementName.split(":")[1] : elementName;
    }

    private String getFormattedDate(XSSFCell cell) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
        sdf.setTimeZone(LocaleUtil.getUserTimeZone());
        return sdf.format(cell.getDateCellValue());
    }

    private Node getNodeByXPath(String xpath, Node rootNode, Document doc, boolean createMultipleInstances) {
        String[] xpathTokens = xpath.split("/");
        Node currentNode = rootNode;
        for (int i = 2; i < xpathTokens.length; ++i) {
            String axisName = this.removeNamespace(xpathTokens[i]);
            if (!axisName.startsWith("@")) {
                NodeList list = currentNode.getChildNodes();
                Node selectedNode = null;
                if (!createMultipleInstances || i != xpathTokens.length - 1) {
                    selectedNode = this.selectNode(axisName, list);
                }
                if (selectedNode == null) {
                    selectedNode = this.createElement(doc, currentNode, axisName);
                }
                currentNode = selectedNode;
                continue;
            }
            currentNode = this.createAttribute(doc, currentNode, axisName);
        }
        return currentNode;
    }

    private Node createAttribute(Document doc, Node currentNode, String axisName) {
        String attributeName = axisName.substring(1);
        NamedNodeMap attributesMap = currentNode.getAttributes();
        Node attribute = attributesMap.getNamedItem(attributeName);
        if (attribute == null) {
            attribute = doc.createAttributeNS("", attributeName);
            attributesMap.setNamedItem(attribute);
        }
        return attribute;
    }

    private Node createElement(Document doc, Node currentNode, String axisName) {
        Element selectedNode = this.isNamespaceDeclared() ? doc.createElementNS(this.getNamespace(), axisName) : doc.createElementNS("", axisName);
        currentNode.appendChild(selectedNode);
        return selectedNode;
    }

    private Node selectNode(String axisName, NodeList list) {
        Node selectedNode = null;
        for (int j = 0; j < list.getLength(); ++j) {
            Node node = list.item(j);
            if (!node.getNodeName().equals(axisName)) continue;
            selectedNode = node;
            break;
        }
        return selectedNode;
    }

    private boolean isNamespaceDeclared() {
        String schemaNamespace = this.getNamespace();
        return schemaNamespace != null && !schemaNamespace.isEmpty();
    }

    private String getNamespace() {
        return this.map.getCTSchema().getNamespace();
    }

    @Override
    public int compare(String leftXpath, String rightXpath) {
        Node xmlSchema = this.map.getSchema();
        String[] leftTokens = leftXpath.split("/");
        String[] rightTokens = rightXpath.split("/");
        String samePath = "";
        int minLength = Math.min(leftTokens.length, rightTokens.length);
        Node localComplexTypeRootNode = xmlSchema;
        for (int i = 1; i < minLength; ++i) {
            String leftElementName = leftTokens[i];
            String rightElementName = rightTokens[i];
            if (!leftElementName.equals(rightElementName)) {
                return this.indexOfElementInComplexType(samePath, leftElementName, rightElementName, localComplexTypeRootNode);
            }
            samePath = samePath + "/" + leftElementName;
            localComplexTypeRootNode = this.getComplexTypeForElement(leftElementName, xmlSchema, localComplexTypeRootNode);
        }
        return 0;
    }

    private int indexOfElementInComplexType(String samePath, String leftElementName, String rightElementName, Node complexType) {
        if (complexType == null) {
            return 0;
        }
        int i = 0;
        String leftWithoutNamespace = this.removeNamespace(leftElementName);
        int leftIndexOf = this.getAndStoreIndex(samePath, leftWithoutNamespace);
        String rightWithoutNamespace = this.removeNamespace(rightElementName);
        int rightIndexOf = this.getAndStoreIndex(samePath, rightWithoutNamespace);
        for (Node node = complexType.getFirstChild(); node != null && (rightIndexOf == -1 || leftIndexOf == -1); node = node.getNextSibling()) {
            if (node instanceof Element && "element".equals(node.getLocalName())) {
                String elementValue = this.getNameOrRefElement(node).getNodeValue();
                if (elementValue.equals(leftWithoutNamespace)) {
                    leftIndexOf = i;
                    this.indexMap.put(samePath + "/" + leftWithoutNamespace, leftIndexOf);
                }
                if (elementValue.equals(rightWithoutNamespace)) {
                    rightIndexOf = i;
                    this.indexMap.put(samePath + "/" + rightWithoutNamespace, rightIndexOf);
                }
            }
            ++i;
        }
        if (leftIndexOf == -1 || rightIndexOf == -1) {
            return 0;
        }
        return Integer.compare(leftIndexOf, rightIndexOf);
    }

    private int getAndStoreIndex(String samePath, String withoutNamespace) {
        String withPath = samePath + "/" + withoutNamespace;
        return this.indexMap.getOrDefault(withPath, -1);
    }

    private Node getNameOrRefElement(Node node) {
        Node returnNode = node.getAttributes().getNamedItem("ref");
        if (returnNode != null) {
            return returnNode;
        }
        return node.getAttributes().getNamedItem("name");
    }

    private Node getComplexTypeForElement(String elementName, Node xmlSchema, Node localComplexTypeRootNode) {
        String elementNameWithoutNamespace = this.removeNamespace(elementName);
        String complexTypeName = this.getComplexTypeNameFromChildren(localComplexTypeRootNode, elementNameWithoutNamespace);
        Node complexTypeNode = null;
        if (!"".equals(complexTypeName)) {
            complexTypeNode = this.getComplexTypeNodeFromSchemaChildren(xmlSchema, null, complexTypeName);
        }
        return complexTypeNode;
    }

    private String getComplexTypeNameFromChildren(Node localComplexTypeRootNode, String elementNameWithoutNamespace) {
        if (localComplexTypeRootNode == null) {
            return "";
        }
        String complexTypeName = "";
        for (Node node = localComplexTypeRootNode.getFirstChild(); node != null; node = node.getNextSibling()) {
            Node complexTypeAttribute;
            Node nameAttribute;
            if (!(node instanceof Element) || !"element".equals(node.getLocalName()) || !(nameAttribute = this.getNameOrRefElement(node)).getNodeValue().equals(elementNameWithoutNamespace) || (complexTypeAttribute = node.getAttributes().getNamedItem("type")) == null) continue;
            complexTypeName = complexTypeAttribute.getNodeValue();
            break;
        }
        return complexTypeName;
    }

    private Node getComplexTypeNodeFromSchemaChildren(Node xmlSchema, Node complexTypeNode, String complexTypeName) {
        for (Node node = xmlSchema.getFirstChild(); node != null; node = node.getNextSibling()) {
            Node nameAttribute;
            if (!(node instanceof Element) || !"complexType".equals(node.getLocalName()) || !(nameAttribute = this.getNameOrRefElement(node)).getNodeValue().equals(complexTypeName)) continue;
            for (Node sequence = node.getFirstChild(); sequence != null; sequence = sequence.getNextSibling()) {
                String localName;
                if (!(sequence instanceof Element) || !"sequence".equals(localName = sequence.getLocalName()) && !"all".equals(localName)) continue;
                complexTypeNode = sequence;
                break;
            }
            if (complexTypeNode != null) break;
        }
        return complexTypeNode;
    }

    private static void trySet(String name, SecurityFeature securityFeature) {
        try {
            securityFeature.accept(name);
        } catch (Exception e) {
            LOG.log(5, "SchemaFactory feature unsupported", name, e);
        } catch (AbstractMethodError ame) {
            LOG.log(5, "Cannot set SchemaFactory feature because outdated XML parser in classpath", name, ame);
        }
    }

    @FunctionalInterface
    private static interface SecurityFeature {
        public void accept(String var1) throws SAXException;
    }
}

