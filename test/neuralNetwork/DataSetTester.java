package neuralNetwork;

import data.DataSet;
import data.Example;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel on 18-05-2015.
 */
public class DataSetTester
{
    @Test
    public void testRangeNormalization() throws IOException
    {
        DataSet trainData = DataSet.parseTrainFile("test_data/train_data.txt");
        trainData.normalize();

        for (Example example : trainData.getExamples())
            for (Double attribute : example.getAttributes())
                Assert.assertTrue(attribute <= 1.0 && attribute >= 0.0);
    }

    @Test
    public void testTargetOutput() throws IOException
    {
        DataSet trainData = DataSet.parseTrainFile("test_data/train_data.txt");

        List<Double> list1 =  trainData.getExamples().get(0).getTargetList();
        List<Double> list2 =  trainData.getExamples().get(520).getTargetList();

        Assert.assertEquals(1.0, list1.get(0), 0.00000001);
        Assert.assertEquals(0.0, list1.get(1), 0.00000001);
        Assert.assertEquals(0.0, list2.get(0), 0.00000001);
        Assert.assertEquals(1.0, list2.get(1), 0.00000001);
    }

    @Test
    public void selectOnePerType() throws IOException
    {
        DataSet testData = DataSet.parseTestFile("test_data/test_data.txt");

        testData = new DataSet(testData.selectOneSamplePerDataType());

        Assert.assertEquals(56, testData.getExamples().size());
    }

    @Test
    public void joinByType() throws IOException
    {
        DataSet testData = DataSet.parseTestFile("test_data/test_data.txt");

        DataSet testDataTemp = new DataSet(testData.selectOneSamplePerDataType());
        testData = new DataSet(testDataTemp.groupAtributesBySubjectID(Arrays.asList(DataSet.DataType.VowelA, DataSet.DataType.VowelO)));

        Assert.assertEquals(28, testData.getExamples().size());
        Assert.assertEquals(52, testData.getNumAttributes());

        Assert.assertEquals(testDataTemp.getExamples().get(0).getAttributes().get(0), testData.getExamples().get(0).getAttributes().get(0), 0.000000000001);
        Assert.assertEquals(testDataTemp.getExamples().get(1).getAttributes().get(0), testData.getExamples().get(0).getAttributes().get(26), 0.000000000001);
        Assert.assertEquals(testDataTemp.getExamples().get(1).getAttributes().get(25), testData.getExamples().get(0).getAttributes().get(51), 0.000000000001);

        Assert.assertEquals(testDataTemp.getExamples().get(4).getAttributes().get(0), testData.getExamples().get(2).getAttributes().get(0), 0.000000000001);
        Assert.assertEquals(testDataTemp.getExamples().get(5).getAttributes().get(0), testData.getExamples().get(2).getAttributes().get(26), 0.000000000001);
        Assert.assertEquals(testDataTemp.getExamples().get(5).getAttributes().get(25), testData.getExamples().get(2).getAttributes().get(51), 0.000000000001);


    }
}
