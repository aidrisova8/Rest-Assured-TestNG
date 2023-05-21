package calls;

import org.testng.annotations.DataProvider;

public class DataStorage {
    @DataProvider(name="PostAnOrderPositive")
    public Object[][]dataOrder() {
        return new Object[][]{
                {5, "John Doe"},
                {1, "Patel"},
                {3, "Elizabeth Clark"},
                {4,"Aigul"}
        };

    }

    @DataProvider(name="PostNegativeValues")
    public Object[][] dataForInvalid(){
        return new Object[][]{
                {8,"////"},
                {0,"John Doe"},
                {10000,","}
        };
    }
}
