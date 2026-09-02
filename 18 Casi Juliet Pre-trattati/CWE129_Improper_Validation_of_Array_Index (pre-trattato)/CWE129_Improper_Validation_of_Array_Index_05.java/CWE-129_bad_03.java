import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ArrayAccessor
{
    private boolean enabled = true;

    public void run() throws Throwable
    {
        int data;
        if (enabled)
        {
            data = Integer.MIN_VALUE;
            {
                InputStreamReader readerInputStream = null;
                BufferedReader readerBuffered = null;
                try
                {
                    readerInputStream = new InputStreamReader(System.in, "UTF-8");
                    readerBuffered = new BufferedReader(readerInputStream);
                    String stringNumber = readerBuffered.readLine();
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
                catch (IOException exceptIO)
                {
                    System.err.println("Error with stream reading");
                }
                finally
                {
                    try
                    {
                        if (readerBuffered != null)
                        {
                            readerBuffered.close();
                        }
                    }
                    catch (IOException exceptIO)
                    {
                        System.err.println("Error closing BufferedReader");
                    }

                    try
                    {
                        if (readerInputStream != null)
                        {
                            readerInputStream.close();
                        }
                    }
                    catch (IOException exceptIO)
                    {
                        System.err.println("Error closing InputStreamReader");
                    }
                }
            }
        }
        else
        {
            data = 0;
        }

        if (enabled)
        {
            int array[] = { 0, 1, 2, 3, 4 };
            if (data >= 0)
            {
                System.out.println(array[data]);
            }
            else
            {
                System.out.println("Array index out of bounds");
            }
        }
    }
}
