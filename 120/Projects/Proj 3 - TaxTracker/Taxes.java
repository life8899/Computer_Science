package TaxTracker;

/**
 * Utility Class to Compute Taxes
 *
 * @author Nick Alexander
 * @version December 3, 2013
 */
public class Taxes
{
    private static final double FEDERAL_TAX = 0.20;
    private static final double STATE_TAX = 0.05;
    private static final double SS_TAX = 0.124;

    /**
     * Applies Federal Tax Rate to the Given Income (Does Not Mutate the Income)
     *
     * @param income Taxable Income
     * @return Amount of Tax Taken
     */
    public static double computeFederalTax(double income)
    {
        return income * FEDERAL_TAX;
    }

    /**
     * Applies State Tax Rate to the Given Income (Does Not Mutate the Income)
     *
     * @param income Taxable Income
     * @return Amount of Tax Taken
     */
    public static double computeStateTax(double income)
    {
        return income * STATE_TAX;
    }

    /**
     * Applies Social Security Tax Rate to the Given Income (Does Not Mutate the
     * Income)
     *
     * @param income Taxable Income
     * @return Amount of Tax Taken
     */
    public static double computeSocialSecurityTax(double income)
    {
        return income * SS_TAX;
    }

    /**
     * Computes Total Amount of Tax Taken (Does Not Mutate the Income)
     *
     * @param income Taxable Income
     * @return Amount of Tax Taken
     */
    public static double computeTaxTaken(double income)
    {
        return computeSocialSecurityTax(income) + computeStateTax(income)
                + computeFederalTax(income);
    }

    /**
     * Deducts the Total Taxes from Income (DOES Mutate the Income)
     *
     * @param income Taxable Income
     * @return Income Minus Taxes
     */
    public static double deductTaxesFromIncome(double income)
    {
        return income - computeTaxTaken(income);
    }
}
