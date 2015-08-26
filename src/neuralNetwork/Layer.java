package neuralNetwork;

import neuralNetwork.utils.IWeightAssigner;

import java.util.ArrayList;
import java.util.List;

public class Layer
{
    private List<Neuron> m_neurons;

    // Initialize for input layer:
    public void initialize(Integer numberNeurons, Double learningRate, Double momentum)
    {
        m_neurons = new ArrayList<>(numberNeurons);

        // Generate the number of neurons desired:
        for (int i = 0; i < numberNeurons; i++)
            m_neurons.add(new Neuron(learningRate, momentum));

        // Add a bias neuron:
        m_neurons.add(new BiasNeuron(learningRate, momentum));
    }

    // Initialize for other layers:
    public void initialize(Integer numberNeurons, Layer previousLayer, IWeightAssigner initialWeightAssigner, boolean isOutputLayer, Double learningRate, Double momentum)
    {
        m_neurons = new ArrayList<>(numberNeurons);
        List<Neuron> previousNeurons = previousLayer.getNeurons();

        for(int i = 0; i < numberNeurons; i++)
        {
            Neuron currentNeuron = new Neuron(learningRate, momentum);

            // Connect to previous neurons:
            for(Neuron previousNeuron : previousNeurons)
            {
                Connection connection = new Connection();
                connection.initialize(previousNeuron, currentNeuron, initialWeightAssigner.assignWeight());

                previousNeuron.addConnection(connection);
                currentNeuron.addConnection(connection);
            }

            m_neurons.add(currentNeuron);
        }

        // If this is not the input layer, add a bias neuron:
        if (!isOutputLayer)
            m_neurons.add(new BiasNeuron(learningRate, momentum));
    }

    public void calculateOutputValues()
    {
        m_neurons.forEach(Neuron::calculateOutputValue);
    }

    public boolean setOutputValues(List<Double> outputValues)
    {
        if(outputValues.size() != m_neurons.size() - 1)
            return false;

        for(int i = 0; i < outputValues.size(); i++)
        {
            Double outputValue = outputValues.get(i);
            Neuron neuron = m_neurons.get(i);

            neuron.setOutputValue(outputValue);
        }

        return true;
    }

    public Double calculateCost(List<Double> targetValues)
    {
        Double cost = 0.0;

        for(int i = 0; i < targetValues.size(); i++)
        {
            Neuron neuron = m_neurons.get(i);
            Double targetValue = targetValues.get(i);
            Double outputValue = neuron.getOutputValue();

            Double delta = targetValue - outputValue;
            cost += delta * delta;
        }

        cost /= 2.0;

        return cost;
    }

    public Double calculateError(List<Double> targetValues)
    {
        Double cost = 0.0;

        for(int i = 0; i < targetValues.size(); i++)
        {
            Neuron neuron = m_neurons.get(i);
            Double targetValue = targetValues.get(i);
            Double outputValue = neuron.getOutputValue();

            Double delta = targetValue - outputValue;
            cost += delta * delta;
        }

        return cost;
    }

    // If its a neuron on an Output Layer:
    public void calculateGradientValues(List<Double> targetValues)
    {
        if (targetValues.size() != m_neurons.size())
            throw new IllegalArgumentException("Error in gradient calculation in output nodes: number of target values doesn't match the number of nodes in the output layer!");

        for (int i = 0; i < m_neurons.size(); i++)
        {
            Neuron neuron = m_neurons.get(i);
            Double targetValue = targetValues.get(i);

            // Calculate neuron gradient using the target value:
            neuron.calculateGradientValue(targetValue);
        }
    }

    // In the other cases:
    public void calculateGradientValues()
    {
        m_neurons.forEach(Neuron::calculateGradientValue);
    }

    public void updateConnectionsWeights()
    {
        m_neurons.forEach(Neuron::updateConnectionsWeights);
    }

    public List<Neuron> getNeurons()
    {
        return m_neurons;
    }
}
