import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProvidersAR {

    @DataProvider
    public Iterator<Object[]> newUser(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"testQA36a@gmail.com","Qwer1234"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> reset(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"testQA36a@gmail.com","Qwer1234"});
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> newUserWithCsv() {
        List<Object[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/test/resources/AR.1.csv"))) {
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
// ADD tel-32