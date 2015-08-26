package gui;

import data.DataSet;
import neuralNetwork.ClassificationReport;
import neuralNetwork.NeuralNetwork;

/**
 * Created by Miguel on 30-05-2015.
 */
public class NeuralNetworkThread extends Thread
{
    private NeuralNetwork m_net;
    private Double m_maxError;
    private Integer m_maxIterations;
    private DataSet m_trainingData, m_testData;
    private GuiForm m_app;

    public NeuralNetworkThread(NeuralNetwork net, Double maxError, Integer maxIterations, DataSet trainingData, DataSet testData, GuiForm app)
    {
        super();
        m_net = net;
        m_maxError = maxError;
        m_maxIterations = maxIterations;
        m_trainingData = trainingData;
        m_testData = testData;
        m_app = app;
    }
    public void run()
    {
        m_net.train(m_trainingData, m_maxError, m_maxIterations);
        ClassificationReport testReport = m_net.test(m_testData);

        System.out.println(testReport);
        m_app.setEnabledInterface(true);
    }
}
