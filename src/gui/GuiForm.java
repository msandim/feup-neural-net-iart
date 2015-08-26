package gui;

import data.DataSet;
import neuralNetwork.ClassificationReport;
import neuralNetwork.NeuralNetwork;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.List;

/**
 * Created by Miguel on 29-05-2015.
 */
public class GuiForm extends JFrame
{
    private JPanel Container;
    private JPanel rootPanel;
    private JTextField hiddenLayerTopology;
    private JPanel Network;
    private JButton trainAndTestButton;
    private JSpinner learningSPinner;
    private JSpinner momentumSpinner;
    private JCheckBox vowelACheckBox;
    private JCheckBox vowelOCheckBox;
    private JCheckBox vowelUCheckBox;
    private JCheckBox number1CheckBox;
    private JCheckBox number2CheckBox;
    private JCheckBox number3CheckBox;
    private JCheckBox number4CheckBox;
    private JCheckBox number5CheckBox;
    private JCheckBox number6CheckBox;
    private JCheckBox number7CheckBox;
    private JCheckBox number8CheckBox;
    private JCheckBox number9CheckBox;
    private JCheckBox number10CheckBox;
    private JCheckBox phrase1CheckBox;
    private JCheckBox phrase2CheckBox;
    private JCheckBox phrase3CheckBox;
    private JCheckBox phrase4CheckBox;
    private JCheckBox word1CheckBox;
    private JCheckBox word2CheckBox;
    private JCheckBox word3CheckBox;
    private JCheckBox word4CheckBox;
    private JCheckBox word5CheckBox;
    private JCheckBox word6CheckBox;
    private JCheckBox word8CheckBox;
    private JCheckBox word7CheckBox;
    private JCheckBox word9CheckBox;
    private JPanel TrainingExamples;
    private JPanel Sidebar;
    private JTextField testFile;
    private JTextField trainFile;
    private JTextArea console;
    private JSpinner errorSpinner;
    private JSpinner iterationsSpinner;
    private JCheckBox vowelATestCheckBox;
    private JCheckBox vowelOTestCheckBox;


    public GuiForm()
    {
        super("Maiguel Parkinson Detector 1.0");
        setPreferredSize(new Dimension(900, 900));
        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);

        trainAndTestButton.addActionListener(e ->
        {
            console.setText("");
            executeNetwork();
        });
    }

    private void createUIComponents()
    {
        console = new JTextArea();
        console.setLineWrap(true);
        console.setWrapStyleWord(true);

        // Redirect output:
        PrintStream printStream = new PrintStream(new GuiOutputStream(console));
        System.setOut(printStream);
        System.setErr(printStream);

        learningSPinner = new JSpinner();
        learningSPinner.setModel(new SpinnerNumberModel(0.1, 0.0, 1.0, 0.05));

        momentumSpinner = new JSpinner();
        momentumSpinner.setModel(new SpinnerNumberModel(0.7, 0.0, 1.0, 0.05));

        errorSpinner = new JSpinner();
        errorSpinner.setModel(new SpinnerNumberModel(0.0001, 0.0, 100.0, 0.00000005));
        JSpinner.NumberEditor numberEditor = new JSpinner.NumberEditor(errorSpinner,"0.000000000");
        errorSpinner.setEditor(numberEditor);

        iterationsSpinner = new JSpinner();
        iterationsSpinner.setModel(new SpinnerNumberModel(4000, 1, 100000, 1));
    }

    public void executeNetwork()
    {
        setEnabledInterface(false);

        try
        {
            // Read data:
            DataSet trainData = DataSet.parseTrainFile(trainFile.getText());
            DataSet testData = DataSet.parseTestFile(testFile.getText());

            // Normalize data:
            trainData.normalize();
            testData.normalize();

            // Treat data:
            trainData = new DataSet(trainData.filterByType(getDataTypesToTrain()));
            testData = new DataSet(testData.filterByType(getDataTypesToTest()));

            // Get network topology:
            java.util.List<Integer> topology = getHiddenLayerTopology();
            topology.add(0, trainData.getNumAttributes()); // input
            topology.add(2); // output

            System.out.println("** Information: **");
            System.out.println("Topology selected: " + Arrays.toString(topology.toArray()));
            System.out.println("Learning rate: " + learningSPinner.getValue() + " Momentum: " + momentumSpinner.getValue());
            System.out.println("Max Error: " + errorSpinner.getValue() + " Max iteration number: " + iterationsSpinner.getValue());
            System.out.println();

            NeuralNetwork network = new NeuralNetwork();
            network.initialize(topology, (Double) learningSPinner.getValue(), (Double) momentumSpinner.getValue());

            NeuralNetworkThread networkThread = new NeuralNetworkThread(network, (Double) errorSpinner.getValue(), (Integer) iterationsSpinner.getValue(), trainData, testData, this);
            networkThread.start();
        }
        catch(IllegalArgumentException e)
        {
            System.err.println("A problem was detected while parsing/running the neural network: " + e.getMessage());
            setEnabledInterface(true);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found: " + e.getMessage());
            setEnabledInterface(true);
        }
        catch(Exception e)
        {
            System.err.println("The following problem was found: " + e.getMessage());
            e.printStackTrace();
            setEnabledInterface(true);
        }
    }

    public java.util.List<DataSet.DataType> getDataTypesToTrain()
    {
        java.util.List<DataSet.DataType> returnList = new ArrayList<>();

        if (vowelACheckBox.isSelected())
            returnList.add(DataSet.DataType.VowelA);

        if (vowelOCheckBox.isSelected())
            returnList.add(DataSet.DataType.VowelO);

        if (vowelUCheckBox.isSelected())
            returnList.add(DataSet.DataType.VowelU);

        if (number1CheckBox.isSelected())
            returnList.add(DataSet.DataType.Number1);

        if (number2CheckBox.isSelected())
            returnList.add(DataSet.DataType.Number2);

        if (number3CheckBox.isSelected())
            returnList.add(DataSet.DataType.Number3);

        if (number4CheckBox.isSelected())
            returnList.add(DataSet.DataType.Number4);

        if (number5CheckBox.isSelected())
            returnList.add(DataSet.DataType.Number5);

        if (number6CheckBox.isSelected())
            returnList.add(DataSet.DataType.Number6);

        if (number7CheckBox.isSelected())
            returnList.add(DataSet.DataType.Number7);

        if (number8CheckBox.isSelected())
            returnList.add(DataSet.DataType.Number8);

        if (number9CheckBox.isSelected())
            returnList.add(DataSet.DataType.Number9);

        if (number10CheckBox.isSelected())
            returnList.add(DataSet.DataType.Number10);

        if (phrase1CheckBox.isSelected())
            returnList.add(DataSet.DataType.Phrase1);

        if (phrase2CheckBox.isSelected())
            returnList.add(DataSet.DataType.Phrase2);

        if (phrase3CheckBox.isSelected())
            returnList.add(DataSet.DataType.Phrase3);

        if (phrase4CheckBox.isSelected())
            returnList.add(DataSet.DataType.Phrase4);

        if (word1CheckBox.isSelected())
            returnList.add(DataSet.DataType.Word1);

        if (word2CheckBox.isSelected())
            returnList.add(DataSet.DataType.Word2);

        if (word3CheckBox.isSelected())
            returnList.add(DataSet.DataType.Word3);

        if (word4CheckBox.isSelected())
            returnList.add(DataSet.DataType.Word4);

        if (word5CheckBox.isSelected())
            returnList.add(DataSet.DataType.Word5);

        if (word6CheckBox.isSelected())
            returnList.add(DataSet.DataType.Word6);

        if (word7CheckBox.isSelected())
            returnList.add(DataSet.DataType.Word7);

        if (word8CheckBox.isSelected())
            returnList.add(DataSet.DataType.Word8);

        if (word9CheckBox.isSelected())
            returnList.add(DataSet.DataType.Word9);

        return returnList;
    }

    private List<DataSet.DataType> getDataTypesToTest()
    {
        java.util.List<DataSet.DataType> returnList = new ArrayList<>();

        if (vowelATestCheckBox.isSelected())
            returnList.add(DataSet.DataType.VowelA);

        if (vowelOTestCheckBox.isSelected())
            returnList.add(DataSet.DataType.VowelO);

        return returnList;
    }

    public java.util.List<Integer> getHiddenLayerTopology()
    {
        java.util.List<Integer> topologyList = new ArrayList<>();

        String topologyStr = hiddenLayerTopology.getText().trim();

        if (topologyStr.length() == 0)
            return topologyList;

        String[] topologyArray = topologyStr.split(" ");

        for (String str: topologyArray)
            topologyList.add(Integer.parseInt(str));

        return topologyList;
    }

    public void setEnabledInterface(boolean bool)
    {
        for (Component component: Sidebar.getComponents())
            component.setEnabled(bool);

        for (Component component: Network.getComponents())
            component.setEnabled(bool);

        for (Component component: TrainingExamples.getComponents())
            component.setEnabled(bool);
    }
}
