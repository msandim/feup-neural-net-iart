package neuralNetwork;

/**
 * Created by Miguel on 18-05-2015.
 */
public class BiasNeuron extends Neuron
{
    public BiasNeuron(Double learningRate, Double momentum)
    {
        super(learningRate, momentum);
        m_outputValue = 1.0;
    }

    @Override
    public void calculateOutputValue()
    {
    }
}
