import data.DataSet;
import neuralNetwork.ClassificationReport;
import neuralNetwork.NeuralNetwork;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Miguel on 17-05-2015.
 */
public class Program
{
    private Scanner m_input = new Scanner(System.in).useLocale(Locale.US);

    private String m_trainPath, m_testPath;
    private Double m_learningRate, m_momentum, m_maxError;
    private Integer m_maxIterations;

    public Program(String trainPath, String testPath)
    {
        m_trainPath = trainPath;
        m_testPath = testPath;
    }

    public void readParameters()
    {
        //System.out.println("Select execution mode: ");
        //m_executionMode = m_input.nextLine();

        System.out.print("Choose a learning rate [0,1]: ");
        m_learningRate = m_input.nextDouble();
        System.out.print("Choose a momentum [0,1]: ");
        m_momentum = m_input.nextDouble();
        System.out.print("Choose minimum error wanted: ");
        m_maxError = m_input.nextDouble();
        System.out.print("Choose the maximum number of iterations: ");
        m_maxIterations = m_input.nextInt();
    }

    public void run() throws InstantiationException, IllegalAccessException, IOException
    {
        // Read data:
        DataSet trainData = DataSet.parseTrainFile(m_trainPath);
        DataSet testData = DataSet.parseTestFile(m_testPath);

        // Normalize data:
        trainData.normalize();
        testData.normalize();

        //trainData.toFile("data/new_train.txt");
        //testData.toFile("data/new_test.txt");

        // Get what we want:
        /*
        DataSet trainData2 = new DataSet(trainData.filterByType(Arrays.asList(DataSet.DataType.Number4)));
        DataSet trainData3 = new DataSet(trainData.groupAtributesBySubjectID(Arrays.asList(DataSet.DataType.VowelA, DataSet.DataType.VowelO)));
        DataSet trainData4 = new DataSet(trainData.groupAtributesBySubjectID(Arrays.asList(DataSet.DataType.VowelA, DataSet.DataType.VowelO)));
        DataSet trainData5 = new DataSet(trainData.filterByType(Arrays.asList(DataSet.DataType.VowelA, DataSet.DataType.VowelO, DataSet.DataType.VowelU)));
        DataSet testData5 = new DataSet(testData.filterByType(Arrays.asList(DataSet.DataType.VowelA, DataSet.DataType.VowelO, DataSet.DataType.VowelU)));
        DataSet testData4 = new DataSet(testData.selectOneSamplePerDataType());
        testData4 = new DataSet(testData4.groupAtributesBySubjectID(Arrays.asList(DataSet.DataType.VowelA, DataSet.DataType.VowelO)));
        */

        trainData = new DataSet(trainData.filterByType(Arrays.asList(DataSet.DataType.VowelO)));
        testData = new DataSet(testData.filterByType(Arrays.asList(DataSet.DataType.VowelO)));

        System.out.println("Initiating 2");

        NeuralNetwork network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 2, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 5");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 5, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 10");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 10, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 15");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 15, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 20");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 20, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 25");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 25, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 29");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 29, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 2:2");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 2, 2, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 5:5");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 5, 5, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 10:10");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 10, 10, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 15:15");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 15, 15, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 20:20");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 20, 20, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 25:25");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 25, 25, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        System.out.println("Initiating 29:29");

        network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), 29, 29, 2), m_learningRate, m_momentum);
        network.train(trainData, m_maxError, m_maxIterations);
        System.out.println(network.test(testData));

        /*
        network.initialize(Arrays.asList(trainData5.getNumAttributes(), 2), m_learningRate, m_momentum);

        network.train(trainData5, m_maxError, m_maxIterations);
        ClassificationReport testReport = network.test(trainData5);
        ClassificationReport testReport2 = network.test(testData5);

        System.out.println(testReport);
        System.out.println(testReport2);
        */
    }

    public static void main(String[] args)
    {
        System.out.println("Welcome to Maiguel - Parkinson detector v1.0!");

        if (args.length != 2)
            System.out.println("Usage: <train_data_path> <test_data_path>");

        try
        {
            Program detector = new Program(args[0], args[1]);
            detector.readParameters();
            detector.run();
        }
        catch(IllegalArgumentException e)
        {
            System.err.println("A problem was detected while parsing/running the neural network: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e)
        {
            System.err.println("A unknown problem was detected: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
