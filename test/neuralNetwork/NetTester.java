package neuralNetwork;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class NetTester
{
    private final static List<Integer> s_TOPOLOGY_1 = Arrays.asList(3, 5, 7, 8,  1);

    @Test
    public void testInitialize()
    {
        // Create net:
        NeuralNetwork net = new NeuralNetwork();
        net.initialize(s_TOPOLOGY_1, 0.4, 0.8);

        // Get layers:
        List<Layer> layers = net.getLayers();

        // Number of layers must be equals to size of topology list:
        Assert.assertEquals(s_TOPOLOGY_1.size(), layers.size());

        for(int i = 0; i < layers.size(); i++)
        {
            Layer layer = layers.get(i);

            // Get neurons of layer:
            List<Neuron> neurons = layer.getNeurons();

            // Number of neurons in the layer must be equals to the value in the topology list + 1 => the +1 is the bias one (except on the output layer)
            if (i == layers.size() - 1)
                Assert.assertEquals((int) s_TOPOLOGY_1.get(i), neurons.size());
            else
            {
                Assert.assertEquals(s_TOPOLOGY_1.get(i) + 1, neurons.size());
                Assert.assertEquals(1.0, neurons.get(neurons.size() - 1).getOutputValue(), 0.0000000000000000000001);
            }

            for (int j = 0; j < neurons.size(); j++)
            {
                Neuron neuron = neurons.get(j);

                // Get connections of neuron:
                List<Connection> nextLayerConnections = neuron.getNextLayerConnections();
                List<Connection> previousLayerConnections = neuron.getPreviousLayerConnections();

                // Check previous connections:
                if (i == 0 || (j == neurons.size() - 1 && i != layers.size() - 1))
                    Assert.assertEquals(0, previousLayerConnections.size());
                else
                    Assert.assertEquals((int) s_TOPOLOGY_1.get(i - 1) + 1, previousLayerConnections.size());

                // Check next connections:
                if (i == layers.size() - 1)
                    Assert.assertEquals(0, nextLayerConnections.size());
                else
                    Assert.assertEquals((int) s_TOPOLOGY_1.get(i + 1), nextLayerConnections.size());
            }
        }
    }
}
