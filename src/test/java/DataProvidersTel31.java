import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProvidersTel31 {
    @DataProvider
    public Iterator<Object[]> NegativescenarioSignInCSV() {
        List<Object[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/test/resources/tel31.csv"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                MyCredential myCredential = new MyCredential(data[0], data[1]);
                list.add(new Object[]{myCredential});
                line = reader.readLine();
            }
        } catch (IOException e){
            TestBase.logger.error(e.getMessage(), e);
        }
        return list.iterator();
    }
}
