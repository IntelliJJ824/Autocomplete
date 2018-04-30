import java.awt.*;
import java.awt.event.*;
import javax.swing.GroupLayout;
import javax.swing.*;
import javax.swing.JFrame;

/**
 * This file is for designing the GUI, linking to the backEnd.
 * author zz35
 *
 */

public class runGUI implements ActionListener {
    static JCheckBox prefix, sortK;
    static JLabel weight;
    static JTextField b4, kfield;
    static JList findList;
    public String enterString;
    static String fileLocation;
    static int amount;
    static String rKey;

    /**
     * the following code is for the layout of GUI.
     * @param fileLocation find out the read file location
     * @param amount The number of words that user want to present
     */
    public runGUI(String fileLocation, String amount) {
    	this.fileLocation = fileLocation;
    	this.amount = Integer.parseInt(amount);

    	JFrame frame = new JFrame("Auto Complete");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();

        GroupLayout groupLayout =
                new GroupLayout(contentPane);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        contentPane.setLayout(groupLayout);
        // this is for creation of the elements in the window.
        prefix = new JCheckBox("sort by prefix");
        weight = new JLabel();
        sortK = new JCheckBox("sort by first k");
        JLabel b5 = new JLabel("Searching word:");
        kfield = new JTextField("K value");
        kfield.setVisible(false);
        findList = new JList();
        findList.setBackground(Color.lightGray);
        findList.setFixedCellWidth(230);
        b4 = new JTextField(15);
        // add event listener to the element
        prefix.addActionListener(this);
        sortK.addActionListener(this);
        b4.addActionListener(this);

        // group design begins with here.
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(b5)
                .addGroup(groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(b4)
                                .addComponent(findList)
                                .addComponent(weight)
                        )
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(prefix)
                                .addComponent(sortK)
                                .addComponent(kfield)
                        )

                )

        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(
                        groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(b5)
                                .addComponent(b4)
                                .addComponent(prefix)

                )
                .addComponent(sortK)

                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(kfield)
                        .addComponent(findList)
                )
                .addComponent(weight)
        );
        b4.setPreferredSize(new Dimension(100,30));
        kfield.setPreferredSize(new Dimension(10,3));
        frame.setSize(500,500);
        frame.setBackground(Color.lightGray);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    // this is the end of the designing

    //This is for the event listener.
    public void actionPerformed(ActionEvent e){
        int option = 0;

        // this is for prefix option
        if(prefix.isSelected()) {
            sortK.setEnabled(false);
            prefix.setSelected(false);
            option = 1;
        }

        /**
         * this option can be used to allow user to enter the number of k.
         * the text field will show up, if the checkbox is ticked
         */
        if(sortK.isSelected()) {
            prefix.setEnabled(false);
            sortK.setSelected(false);
            option = 2;
            kfield.setVisible(true);
            rKey = kfield.getText();
        }

        String text = b4.getText();
        Main test = new Main(text, option, fileLocation);
        //If the searching result is longer than user expected, this program will be only presented the number of user expected.
        Term[] testArr = test.getArray();
        String[] showList = new String[amount];
        int userInputNo = amount;
        try {
         if (testArr.length == 0) {
            findList.setVisible(false);
            weight.setText("No Words are found, try again!");
         }
         else {
            if (testArr.length < amount) {
                amount = testArr.length;
            }

            for (int i = 0; i < amount; i++) {
              showList[i] = testArr[i].query;
            }

            //present the data to the GUI
            findList.setListData(showList);
            amount = userInputNo;
            String result = test.getResult();
            weight.setText(result);
            findList.setVisible(true);
         }
        } catch (NullPointerException error) {
            findList.setVisible(false);
            weight.setText("Enter the query words.");
        }

    }
    // this method is to return the value of first k value.
    public static String getrKey() {
        return rKey;
    }

}
