package Tests; /**
 * This is a simple test case for the toner and low toner alert
 */

import Objects.Toner;

import java.util.Arrays;
import java.util.List;

public class TonerAlertTest {

    public static void main(String[] args) {
        String[] printers = {"123dr", "345drd", "ert45"};
        List<String> compatiblePrinters = Arrays.asList(printers);


        Toner toner = new Toner(
                compatiblePrinters,
                "Yellow",
                "Dell",
                "DE2323x",
                5,
                6,
                'n',
                10);

        toner.deductOne();

    }
}
