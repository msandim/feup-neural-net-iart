package data;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Miguel on 18-05-2015.
 */
public class DataSet
{
    protected List<Example> m_examples = new ArrayList<>();

    public DataSet()
    {}

    public DataSet(List<Example> examples)
    {
        m_examples = examples;
    }

    public static DataSet parseTrainFile(String filePath) throws IOException
    {
        DataSet trainData = new DataSet();
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;
        int i = 0; // counter for enum

        while ((line = br.readLine()) != null)
        {
            // Parse a training example and set a training type to it:
            Example example = Example.parseTrainExample(line);
            example.setType(DataSet.DataType.intToType(i));

            trainData.m_examples.add(example);

            i++;
            i = i % 26;
        }

        return trainData;
    }

    public static DataSet parseTestFile(String filePath) throws IOException
    {
        DataSet testData = new DataSet();
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;
        int i = 0, j = 0; // counter for enum (i => VogalA or VogalO, j => how many examples of that vogal we have!)

        while ((line = br.readLine()) != null)
        {
            // Parse a training example and set a training type to it:
            Example example = Example.parseTestExample(line);
            example.setType(DataSet.DataType.intToType(i));

            testData.m_examples.add(example);

            j++;

            if (j == 3)
            {
                j = 0;
                i++;
                i = i % 2;
            }
        }

        return testData;
    }

    public void normalize()
    {
        // Find max/min value for each attribute:
        List<Double> max = new ArrayList<>(m_examples.get(0).getAttributes());
        List<Double> min = new ArrayList<>(m_examples.get(0).getAttributes());

        for (Example currentExample : m_examples)
        {
            List<Double> currentAttributes = currentExample.getAttributes();

            for (int j = 0; j < currentAttributes.size(); j++)
            {
                Double currentAttribute = currentAttributes.get(j);
                Double maxAttribute = max.get(j);
                Double minAttribute = min.get(j);

                // Update max:
                if (currentAttribute > maxAttribute)
                    max.set(j, currentAttribute);

                // Update min:
                if (currentAttribute < minAttribute)
                    min.set(j, currentAttribute);
            }
        }

        // Normalize:
        for (Example currentExample : m_examples)
        {
            List<Double> currentAttributes = currentExample.getAttributes();

            for (int j = 0; j < currentAttributes.size(); j++)
            {
                Double currentAttribute = currentAttributes.get(j);
                Double maxAttribute = max.get(j);
                Double minAttribute = min.get(j);

                Double newValue = (currentAttribute - minAttribute) / (maxAttribute - minAttribute);
                currentAttributes.set(j, newValue);
            }
        }
    }

    public List<Example> filterByType(List<DataType> types) throws IllegalAccessException, InstantiationException
    {
        List<Example> returnExamples = new ArrayList<>();

        for (DataType type : types)
            returnExamples.addAll(m_examples.stream().filter(example -> example.getDataType() == type).collect(Collectors.toList()));
        return returnExamples;
    }

    public List<Example> groupAtributesBySubjectID(List<DataType> types)
    {
        List<Example> returnExamples = new ArrayList<>();
        HashMap<Integer, DataClass> subjectIDs = new HashMap<>();

        // Get subject IDs from the examples:
        for (Example example : m_examples)
            subjectIDs.put(example.getSubjectID(), example.getDataClass());

        for(Integer subjectID: subjectIDs.keySet())
        {
            List<Double> newAtributes = new ArrayList<>();

            for(DataType type: types)
                m_examples.stream().filter(example -> example.getSubjectID() == subjectID && example.getDataType() == type).forEach(example -> newAtributes.addAll(example.getAttributes()));

            returnExamples.add(new Example(subjectID, newAtributes, subjectIDs.get(subjectID)));
        }

        return returnExamples;
    }

    public List<Example> selectOneSamplePerDataType()
    {
        List<Example> returnExamples = new ArrayList<>();
        HashMap<Integer, DataClass> subjectIDs = new HashMap<>();

        // Get subject IDs from the examples:
        for (Example example : m_examples)
            subjectIDs.put(example.getSubjectID(), example.getDataClass());

        for(Integer subjectID: subjectIDs.keySet())
        {
            for (DataType type : DataType.values())
            {
                List<Example> sameTypeExamples = m_examples.stream().filter(example -> example.getSubjectID() == subjectID && example.getDataType() == type).collect(Collectors.toList());

                if (sameTypeExamples.isEmpty())
                    break;

                // Generate random integer:
                int randomIndex = new Random().nextInt(sameTypeExamples.size());
                returnExamples.add(sameTypeExamples.get(randomIndex));
            }
        }

        return returnExamples;
    }

    /*
    public DataSet getSubsetAndLeaveRest(double percentage)
    {
        List<Example> returnExamples = new ArrayList<>();
        List<Example> newExamples = new ArrayList<>();
        List<Integer> subjectIDParkinsonStay = new ArrayList<>();
        List<Integer> subjectIDHealthyStay = new ArrayList<>();
        List<Integer> subjectIDRemove = new ArrayList<>();
        Random rnd = new Random();

        // Get subject IDs from the examples:
        for (Example example: m_examples)
        {
            if (example.getDataClass() == DataClass.PARKINSON)
            {
                if (!subjectIDParkinsonStay.contains(example.getSubjectID()))
                    subjectIDParkinsonStay.add(example.getSubjectID());
            }
            else
            {
                if (!subjectIDHealthyStay.contains(example.getSubjectID()))
                    subjectIDHealthyStay.add(example.getSubjectID());
            }
        }

        int valuePerClass = (int) (percentage * subjectIDParkinsonStay.size());

        for(int i=0; i < valuePerClass; i++)
        {
            Integer parkinsonID = subjectIDParkinsonStay.get(rnd.nextInt(subjectIDParkinsonStay.size()));
            Integer healthyID = subjectIDHealthyStay.get(rnd.nextInt(subjectIDHealthyStay.size()));

            subjectIDParkinsonStay.remove(parkinsonID);
            subjectIDHealthyStay.remove(healthyID);

            subjectIDRemove.add(parkinsonID);
            subjectIDRemove.add(healthyID);
        }

        for (Example example: m_examples)
        {
            if (subjectIDRemove.contains(example.getSubjectID()))
                returnExamples.add(example);
            else
                newExamples.add(example);
        }

        // Update current examples and return the rest:
        m_examples = newExamples;
        return new DataSet(returnExamples);
    }
    */

    public List<Example> getExamples()
    {
        return m_examples;
    }

    public int getNumAttributes()
    {
        return m_examples.get(0).getNumberAttributes();
    }

    public void toFile(String path)
    {
        try
        {
            PrintWriter out = new PrintWriter(path);

            for (int i = 0; i < m_examples.size(); i++)
            {
                String example = m_examples.get(i).toString();

                out.print(example);

                if (i != m_examples.size() - 1)
                {
                    out.print(",");
                    out.println();
                }
            }

            out.flush();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public enum DataClass
    {
        PARKINSON, HEALTHY
    }

    public enum DataType
    {
        VowelA, VowelO, VowelU,
        Number1, Number2, Number3, Number4, Number5, Number6, Number7, Number8, Number9, Number10,
        Phrase1, Phrase2, Phrase3, Phrase4,
        Word1, Word2, Word3, Word4, Word5, Word6, Word7, Word8, Word9;

        public static DataType intToType(int type)
        {
            switch(type)
            {
                case 0:
                    return VowelA;
                case 1:
                    return VowelO;
                case 2:
                    return VowelU;
                case 3:
                    return Number1;
                case 4:
                    return Number2;
                case 5:
                    return Number3;
                case 6:
                    return Number4;
                case 7:
                    return Number5;
                case 8:
                    return Number6;
                case 9:
                    return Number7;
                case 10:
                    return Number8;
                case 11:
                    return Number9;
                case 12:
                    return Number10;
                case 13:
                    return Phrase1;
                case 14:
                    return Phrase2;
                case 15:
                    return Phrase3;
                case 16:
                    return Phrase4;
                case 17:
                    return Word1;
                case 18:
                    return Word2;
                case 19:
                    return Word3;
                case 20:
                    return Word4;
                case 21:
                    return Word5;
                case 22:
                    return Word6;
                case 23:
                    return Word7;
                case 24:
                    return Word8;
                case 25:
                    return Word9;

                default:
                    throw new IllegalArgumentException("Invalid index to enum: " + type);
            }
        }
    }
}
