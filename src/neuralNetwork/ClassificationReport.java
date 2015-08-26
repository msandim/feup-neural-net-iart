package neuralNetwork;

/**
 * Created by Miguel on 27-05-2015.
 */
public class ClassificationReport
{
    private int m_truePositives=0, m_totalPositives=0, m_trueNegatives=0, m_totalNegatives=0;
    private double m_mse = 0.0;

    public void incDetectedParkinson()
    {
        m_truePositives++;
    }

    public void incTotalParkinson()
    {
        m_totalPositives++;
    }

    public void incDetectedHealthy()
    {
        m_trueNegatives++;
    }

    public void incTotalHealthy()
    {
        m_totalNegatives++;
    }

    public int getDetectedParkinson()
    {
        return m_truePositives;
    }

    public int getTotalParkinson()
    {
        return m_totalPositives;
    }

    public int getDetectedHealthy()
    {
        return m_trueNegatives;
    }

    public int getTotalHealthy()
    {
        return m_totalNegatives;
    }

    public int getTotalCases()
    {
        return m_totalPositives + m_totalNegatives;
    }

    public void setMSE(double mse)
    {
        m_mse = mse;
    }

    public double getMSE()
    {
        return m_mse;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        str.append("** Report **\n");
        str.append("MSE: " + getMSE() + "\n");
        str.append("Parkinson detection: " + getDetectedParkinson() + "/" + getTotalParkinson() + "\n");
        str.append("Healthy detection: " + getDetectedHealthy() + "/" + getTotalHealthy() + "\n");
        str.append("-------");

        return str.toString();
    }
}
