import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MortgageSavingsCalculatorTest {


    @Test
    public void givenMonthlyPaymentReturnLengthOfTime() {
        MortgageSavingsCalculator mortgageSavingsCalculator = new MortgageSavingsCalculator(200000,10,0, 200);
        assertThat(mortgageSavingsCalculator.yearlyAndMonthlyPayments())
                .isEqualTo("It will take you 8 years 4 months to save the deposit needed");
    }

    @Test
    public void givenAYearReturnMonthlySavingsAmount() {
        MortgageSavingsCalculator mortgageSavingsCalculator = new MortgageSavingsCalculator(200000,10,0, "16-05-2020");
        assertThat(mortgageSavingsCalculator.depositDateNeeded())
                .isEqualTo("To have your deposit by 16-05-2020, you will need to save £1818.18 per month");
    }

    @Test
    public void givenTwoYearsAndHalfDepositReturnMonthlySavingsAmount(){
        MortgageSavingsCalculator mortgageSavingsCalculator = new MortgageSavingsCalculator(100000,20,10000,"16-05-2021");
        assertThat(mortgageSavingsCalculator.depositDateNeeded())
                .isEqualTo("To have your deposit by 16-05-2021, you will need to save £434.78 per month");
    }
}