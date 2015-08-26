package neuralNetwork.utils;

public class RandomAssigner implements IWeightAssigner
{
    private static final java.util.Random s_RANDOM = new java.util.Random(System.currentTimeMillis());

    // Get a random weight between 0 and 1
    public Double assignWeight()
    {
        //return s_RANDOM.nextDouble() - 0.5;
        return -1 + (1 - (-1)) * s_RANDOM.nextDouble();
        // return -0.1 + (0.1 - (-0.1)) * s_RANDOM.nextDouble();
        //return 1.0;
        //return -2 + (2 - (-2)) * s_RANDOM.nextDouble();
    }
}
