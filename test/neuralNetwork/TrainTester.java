package neuralNetwork;

import neuralNetwork.utils.TestAssigner;
import org.junit.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel on 17-05-2015.
 */
public class TrainTester
{
    private final static List<Integer> s_TOPOLOGY_1 = Arrays.asList(2, 1, 2, 2);
    private final static List<Integer> s_TOPOLOGY_2 = Arrays.asList(2, 1);

    @Test
    public void testTrainNet()
    {
        // Create net (learn rate = 0.4, momentum = 0.8:
        NeuralNetwork net = new NeuralNetwork();
        net.setWeightAssigner(new TestAssigner(Arrays.asList(0.3, 0.6, 0.2, 0.7, 0.2, 0.5, 0.9, 0.6, 0.4, 0.5, 0.3, 0.1, 0.6)));
        net.initialize(s_TOPOLOGY_1, 0.4, 0.8);
        List<Neuron> intputNeurons = net.getInputLayer().getNeurons();
        List<Neuron> hiddenNeurons1 = net.getLayers().get(1).getNeurons();
        List<Neuron> hiddenNeurons2 = net.getLayers().get(2).getNeurons();
        List<Neuron> outputNeurons = net.getOutputLayer().getNeurons();

        List<Connection> connectionsNode1 = intputNeurons.get(0).getNextLayerConnections();
        List<Connection> connectionsNode2 = intputNeurons.get(1).getNextLayerConnections();
        List<Connection> connectionsNode3 = intputNeurons.get(2).getNextLayerConnections();
        List<Connection> connectionsNode4 = hiddenNeurons1.get(0).getNextLayerConnections();
        List<Connection> connectionsNode5 = hiddenNeurons1.get(1).getNextLayerConnections();
        List<Connection> connectionsNode6 = hiddenNeurons2.get(0).getNextLayerConnections();
        List<Connection> connectionsNode7 = hiddenNeurons2.get(1).getNextLayerConnections();
        List<Connection> connectionsNode8 = hiddenNeurons2.get(2).getNextLayerConnections();

        // Test feed-forward:
        net.feedForward(Arrays.asList(4.0, 5.0));
        Assert.assertEquals(0.7766082273115665, outputNeurons.get(0).getOutputValue(), 0.000000000001);
        Assert.assertEquals(0.7094846597718821, outputNeurons.get(1).getOutputValue(), 0.000000000001);

        // Test back-propagation error calculation:
        net.backPropagate(Arrays.asList(0.1, 0.2));
        Assert.assertEquals(-0.1173833327545442, outputNeurons.get(0).getGradientValue(), 0.000000000001);
        Assert.assertEquals(-0.105013030475493, outputNeurons.get(1).getGradientValue(), 0.000000000001);

        Assert.assertEquals(-0.02102230366214498, hiddenNeurons2.get(0).getGradientValue(), 0.000000000001);
        Assert.assertEquals(-0.009150605527624533, hiddenNeurons2.get(1).getGradientValue(), 0.000000000001);

        Assert.assertEquals(-2.311309392314059 * Math.pow(10, -4), hiddenNeurons1.get(0).getGradientValue(), 0.000000000001);

        // Test back-propagation weight correction:
        Assert.assertEquals(0.2996301904972297, connectionsNode1.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.5995377381215372, connectionsNode2.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.1999075476243075, connectionsNode3.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.6916930655924164, connectionsNode4.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.4963841507986336, connectionsNode4.get(1).getWeight(), 0.000000000001);
        Assert.assertEquals(0.191591078535142, connectionsNode5.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.8963397577889503, connectionsNode5.get(1).getWeight(), 0.000000000001);
        Assert.assertEquals(0.5667006161116385, connectionsNode6.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.2702098318984023, connectionsNode6.get(1).getWeight(), 0.000000000001);
        Assert.assertEquals(0.3623800586630255, connectionsNode7.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.06634459123454195, connectionsNode7.get(1).getWeight(), 0.000000000001);
        Assert.assertEquals(0.4530466668981823, connectionsNode8.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.5579947878098028, connectionsNode8.get(1).getWeight(), 0.000000000001);
    }

    @Test
    public void testTrainNetMomentum()
    {
        // Create net (learn rate = 0.4, momentum = 0.8:
        NeuralNetwork net = new NeuralNetwork();
        net.setWeightAssigner(new TestAssigner(Arrays.asList(0.8, 0.3, 0.1)));
        net.initialize(s_TOPOLOGY_2, 0.4, 0.8);
        List<Neuron> intputNeurons = net.getInputLayer().getNeurons();
        Neuron outputNeuron = net.getOutputLayer().getNeurons().get(0);

        List<Connection> connectionsNode1 = intputNeurons.get(0).getNextLayerConnections();
        List<Connection> connectionsNode2 = intputNeurons.get(1).getNextLayerConnections();
        List<Connection> connectionsNode3 = intputNeurons.get(2).getNextLayerConnections();

        // First iteration:
        net.feedForward(Arrays.asList(1.2, 0.7));
        Assert.assertEquals(0.7807427479121283, outputNeuron.getOutputValue(), 0.000000000001);
        net.backPropagate(Arrays.asList(0.2));
        Assert.assertEquals(0.7522814807834137, connectionsNode1.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.272164197123658, connectionsNode2.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.06023456731951144, connectionsNode3.get(0).getWeight(), 0.000000000001);

        // Second iteration:
        net.feedForward(Arrays.asList(1.2, 0.7));
        Assert.assertEquals(0.760147306723454, outputNeuron.getOutputValue(), 0.000000000001);
        net.backPropagate(Arrays.asList(0.2));
        Assert.assertEquals(0.6650852496069066, connectionsNode1.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.2212997289373623, connectionsNode2.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(-0.01242895866091114, connectionsNode3.get(0).getWeight(), 0.000000000001);

        // Third iteration:
        net.feedForward(Arrays.asList(1.2, 0.7));
        Assert.assertEquals(0.7192174361742039, outputNeuron.getOutputValue(), 0.000000000001);
        net.backPropagate(Arrays.asList(0.2));
        Assert.assertEquals(0.5449989694791029, connectionsNode1.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.1512493988628101, connectionsNode2.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(-0.1125008587674143, connectionsNode3.get(0).getWeight(), 0.000000000001);
    }
}
