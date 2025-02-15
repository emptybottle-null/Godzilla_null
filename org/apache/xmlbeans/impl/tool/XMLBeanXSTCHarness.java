/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.tool;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.tool.XSTCTester;

public class XMLBeanXSTCHarness
implements XSTCTester.Harness {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void runTestCase(XSTCTester.TestCaseResult result) {
        boolean instanceValid;
        ArrayList errors;
        block14: {
            StringWriter sw;
            SchemaTypeLoader loader;
            boolean schemaValid;
            XSTCTester.TestCase testCase;
            block13: {
                testCase = result.getTestCase();
                errors = new ArrayList();
                schemaValid = true;
                instanceValid = true;
                if (testCase.getSchemaFile() == null) {
                    return;
                }
                loader = null;
                try {
                    XmlObject[] xmlObjectArray;
                    XmlObject schema = XmlObject.Factory.parse(testCase.getSchemaFile(), new XmlOptions().setErrorListener(errors).setLoadLineNumbers());
                    XmlObject schema2 = null;
                    if (testCase.getResourceFile() != null) {
                        schema2 = XmlObject.Factory.parse(testCase.getResourceFile(), new XmlOptions().setErrorListener(errors).setLoadLineNumbers());
                    }
                    if (schema2 == null) {
                        XmlObject[] xmlObjectArray2 = new XmlObject[1];
                        xmlObjectArray = xmlObjectArray2;
                        xmlObjectArray2[0] = schema;
                    } else {
                        XmlObject[] xmlObjectArray3 = new XmlObject[2];
                        xmlObjectArray3[0] = schema;
                        xmlObjectArray = xmlObjectArray3;
                        xmlObjectArray3[1] = schema2;
                    }
                    XmlObject[] schemas = xmlObjectArray;
                    SchemaTypeSystem system = XmlBeans.compileXsd(schemas, XmlBeans.getBuiltinTypeSystem(), new XmlOptions().setErrorListener(errors));
                    loader = XmlBeans.typeLoaderUnion(new SchemaTypeLoader[]{system, XmlBeans.getBuiltinTypeSystem()});
                } catch (Exception e) {
                    schemaValid = false;
                    if (e instanceof XmlException && !errors.isEmpty()) break block13;
                    result.setCrash(true);
                    sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    result.addSvMessages(Collections.singleton(sw.toString()));
                }
            }
            result.addSvMessages(errors);
            result.setSvActual(schemaValid);
            errors.clear();
            if (loader == null) {
                return;
            }
            if (testCase.getInstanceFile() == null) {
                return;
            }
            try {
                XmlObject instance = loader.parse(testCase.getInstanceFile(), null, new XmlOptions().setErrorListener(errors).setLoadLineNumbers());
                if (!instance.validate(new XmlOptions().setErrorListener(errors))) {
                    instanceValid = false;
                }
            } catch (Exception e) {
                instanceValid = false;
                if (e instanceof XmlException && !errors.isEmpty()) break block14;
                result.setCrash(true);
                sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                result.addIvMessages(Collections.singleton(sw.toString()));
            }
        }
        result.addIvMessages(errors);
        result.setIvActual(instanceValid);
    }
}

