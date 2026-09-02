import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

public class ArrayAccessor
{
    public void run() throws Throwable
    {
        int data;
        if (true)
        {
            data = Integer.MIN_VALUE;
            {
                Socket socket = null;
                BufferedReader readerBuffered = null;
                InputStreamReader readerInputStream = null;
                try
                {
                    socket = new Socket("host.example.org", 39544);
                    readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
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

                    try
                    {
                        if (socket != null)
                        {
                            socket.close();
                        }
                    }
                    catch (IOException exceptIO)
                    {
                        System.err.println("Error closing Socket");
                    }
                }
            }
        }
        else
        {
            data = 0;
        }

        if (true)
        {
            int array[] = { 0, 1, 2, 3, 4 };
            System.out.println(array[data]);
        }
    }
}
