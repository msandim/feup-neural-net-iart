package neuralNetwork.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel on 17-05-2015.
 */
public class TestAssigner implements IWeightAssigner
{
    List<Double> m_weights;
    int index = 0;

    public TestAssigner(List<Double> weights)
    {
        m_weights = weights;
    }

    public Double assignWeight()
    {
        if (index >= m_weights.size())
            throw new IllegalArgumentException("TestAssigner::assignWeight() - Invalid weight assign (out of range");

        Double value = m_weights.get(index);
        index++;
        return value;
    }
}
