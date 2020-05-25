package lab5;
import java.util.Random;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
public class AddExact
{
    Random rand = new Random();
    int[] arr_1 = generate_int();
    int[] arr_overflow = generate_int_overflow();
    // тест суммы двух простых int чисел
    @Test(dataProvider = "AddExactIntSum")
    public void AddExactIntSumTest(int a, int b)
    {
        // возвращает сумму двух чисел
        Assert.assertEquals(Math.addExact(a, b), a + b);
    }
    // DataProvider простых int чисел
    @DataProvider(name = "AddExactIntSum")
    public Object[][] IntSum()
    {
        return new Object[][]
        {
            {arr_1[0], arr_1[1]}
        };
    }
    // тест, когда int a и b вызывают исключение
    @Test(dataProvider = "AddExactIntException")
    public void AddExactIntExceptionTest(int a, int b)
    {
        // если сумма чисел переполняет тип int
        Assert.assertThrows(ArithmeticException.class, () -> Math.addExact(a, b));
    }
    // DataProvider чисел, которые вызывают исключение
    @DataProvider(name = "AddExactIntException")
    public Object[][] IntException()
    {
        return new Object[][]
                {
                        {arr_overflow[0], arr_overflow[1]}
                };
    }
    // генерация двух чисел int (таких, чтобы их сумма не превышала максимально возможного числа int)
    public int[] generate_int()
    {
        int a_1 = rand.nextInt();
        int a_2;
        int min, max;
        int[] arr_2 = new int[2];
        if (a_1 < 0)
        {
            min = Integer.MIN_VALUE - a_1;
            a_2 = (rand.nextInt(Integer.MAX_VALUE) + rand.nextInt(10)) - rand.nextInt(Math.abs(min));
        }
        else if (a_1 > 0)
        {
            min = Integer.MIN_VALUE + 1;
            max = Integer.MAX_VALUE - a_1;
            a_2 = rand.nextInt(max) - rand.nextInt(min * (-1));
        }
        else
        {
            a_2 = rand.nextInt();
        }
        arr_2[0] = a_1;
        arr_2[1] = a_2;
        return arr_2;
    }
    // генерация таких чисел, что их сумма вызывает исключение
    public int[] generate_int_overflow()
    {
        int a = rand.nextInt(2);
        int[] arr_3 = new int[2];
        // если переполнение отрицательного числа int
        if (a == 0)
        {
            arr_3[0] = Integer.MIN_VALUE;
            arr_3[1] = -1 * (rand.nextInt(Integer.MAX_VALUE) + 10);
        }
        // если переполнение положительного числа int
        else
        {
            arr_3[0] = Integer.MAX_VALUE;
            arr_3[1] = rand.nextInt(Integer.MAX_VALUE) + 10;
        }
        return arr_3;
    }
}