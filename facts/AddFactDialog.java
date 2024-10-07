package facts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class AddFactDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField authorInputTextField;
    private JTextField factDetailsTextField;
    private JRadioButton factRadioButton;
    private JRadioButton fallacyRadioButton;
    private ButtonGroup factOrFallacyButtonGroup;
    private FactList theList;
    private Parser theParser;

    public AddFactDialog(Parser parser) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.theParser = parser;
        theList = theParser.getFactList();


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (!authorInputTextField.getText().isEmpty() && !factDetailsTextField.getText().isEmpty()) {
            Pattern thePattern = Pattern.compile("[<>/]");
            if (thePattern.matcher(authorInputTextField.getText()).matches() || thePattern.matcher(factDetailsTextField.getText()).matches()){
                dispose();
            }
            Fact newFact = new Fact();
            newFact.setAuthor(authorInputTextField.getText());
            newFact.setText(factDetailsTextField.getText());
            if(factRadioButton.isSelected()){
                newFact.setType("Fact");
            }
            else{
                newFact.setType("Fallacy");
            }

            if (!theList.contains(newFact)){
                try {
                    theParser.writeXML(newFact);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
