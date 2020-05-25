package lab5;
import java.util.Random;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
public class FloorDiv
{
    Random rand = new Random();
    int[] arr = generate_ints();
    // тест с простыми a и b int числами
    @Test(dataProvider = "FloorDivInt")
    public void FloorDivIntTest(int a, int b)
    {
        double res = Math.floor(a / (b * 1.0));
        Assert.assertEquals(Math.floorDiv(a, b), (int) res);
    }
    // DataProvider простых a и b
    @DataProvider(name = "FloorDivInt")
    public Object[][] FloorDivInt()
    {
        return new Object[][]
                {
                        {arr[0], arr[1]}
                };
    }
    // тест с числами a и b, которые вызывают исключение
    @Test(dataProvider = "FloorDivIntOverflow")
    public void floorDivOverflowTest(int a, int b)
    {
        // если делимое = int min_value, а делитель = -1, то исключение
        Assert.assertEquals(Math.floorDiv(a, b), Integer.MIN_VALUE);
    }
    // DataProvider a и b, которые будут вызывать исключения
    @DataProvider(name = "FloorDivIntOverflow")
    public Object[][] floorDivIntOverflow()
    {
        return new Object[][]
                {
                        {Integer.MIN_VALUE, -1}
                };
    }
    // тест с a и b, которые будут вызывать ArithmeticException
    @Test(dataProvider = "floorDivIntArithmeticException")
    public void floorDivIntArithmeticExceptionTest(int a, int b)
    {
        // если делитель = 0
        Assert.assertThrows(ArithmeticException.class, () -> Math.floorDiv(a, b));
    }
    // DataProvider чисел a и b, которые будут вызывать ArithmeticException
    @DataProvider(name = "floorDivIntArithmeticException")
    public Object[][] floorDivIntArithmeticException()
    {
        return new Object[][]
                {
                        {rand.nextInt(), 0}
                };
    }
    // генерация таких a и b, которые не будут вызывать исключение
    int[] generate_ints()
    {
        boolean finish = false;
        int a_1 = 0, b_1 = 0;
        int[] arr_1 = new int[2];
        while(!finish)
        {
            a_1 = rand.nextInt();
            b_1 = rand.nextInt();
            // условие (a_1 == Integer.MIN_VALUE && b_1 == -1) вызывает переполнение int
            // условие (b_1 == 0) вызывает исключение ArithmeticException
            // проверка, что случайные числа не вызовут исключений
            if (!((a_1 == Integer.MIN_VALUE && b_1 == -1) || (b_1 == 0)))
                finish = true;
        }
        arr_1[0] = a_1;
        arr_1[1] = b_1;
        return arr_1;
    }
}