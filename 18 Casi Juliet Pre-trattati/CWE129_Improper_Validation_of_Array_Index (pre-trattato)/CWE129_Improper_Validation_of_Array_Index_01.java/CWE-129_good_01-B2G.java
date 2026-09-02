public class ArrayAccessor
{
    public void run() throws Throwable
    {
        int data;

        data = Integer.MIN_VALUE;

        {
            String stringNumber = System.getenv("ADD");
            if (stringNumber != null)
            {
                try
                {
                    data = Integer.parseInt(stringNumber.trim());
                }
                catch (NumberFormatException exceptNumberFormat)
                {
                    System.err.println("Number format exception parsing data from string");
                }
            }
        }

        int array[] = { 0, 1, 2, 3, 4 };

        if (data >= 0 && data < array.length)
        {
            System.out.println(array[data]);
        }
        else
        {
            System.out.println("Array index out of bounds");
        }
    }
}
