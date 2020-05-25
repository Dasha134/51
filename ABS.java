package lab5;
import java.util.Random;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
public class ABS
{
    Random rand = new Random();
    // тест когда число положительное
    @Test(dataProvider = "PositiveIntAbs")
    public void PositiveIntAbsTest(int a)
    {
        // если число положительно, то возвращает это число
        Assert.assertEquals(Math.abs(a), a);
    }
    // DataProvider положительного числа
    @DataProvider(name = "PositiveIntAbs")
    public Object[][] PositiveInt()
    {
        return new Object[][]
        {
            // случайное число от 2 до 100
            {rand.nextInt(100) + rand.nextInt(2)}
        };
    }
    // тест когда число отрицательное
    @Test(dataProvider = "NegativeIntAbs")
    public void NegativeIntAbsTest(int a)
    {
        // если число отрицательно, то возвращает это число
        Assert.assertEquals(Math.abs(a), -a);
    }
    // DataProvider отрицательного числа
    @DataProvider(name = "NegativeIntAbs")
    public Object[][] NegativeInt()
    {
        return new Object[][]
        {
            // случайное число от -100 до -2
            {-1 * (rand.nextInt(100) + rand.nextInt(2))}
        };
    }
    // тест когда int число = min_value (-2 147 483 648)
    @Test(dataProvider = "MinValueIntAbs")
    public void MinValueIntAbsTest(int a)
    {
        // если число а = int min_value, то возвращает это число
        Assert.assertEquals(Math.abs(a), a);
    }
    // DataProvider int min_value
    @DataProvider(name = "MinValueIntAbs")
    public Object[][] MinValueInt()
    {
        return new Object[][]
        {
            {Integer.MIN_VALUE}
        };
    }
}
