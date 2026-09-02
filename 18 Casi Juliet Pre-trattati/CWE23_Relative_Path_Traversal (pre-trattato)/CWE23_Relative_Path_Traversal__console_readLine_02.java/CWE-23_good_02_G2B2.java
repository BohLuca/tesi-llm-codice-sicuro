import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class DocumentLoader
{
    public void run() throws Throwable
    {
        String data;
        if (true)
        {
            data = "foo";
        }
        else
        {
            data = null;
        }

        String root;
        if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)
        {
            root = "C:\\uploads\\";
        }
        else
        {
            root = "/home/user/uploads/";
        }

        if (data != null)
        {
            File file = new File(root + data);
            FileInputStream streamFileInputSink = null;
            InputStreamReader readerInputStreamSink = null;
            BufferedReader readerBufferdSink = null;
            if (file.exists() && file.isFile())
            {
                try
                {
                    streamFileInputSink = new FileInputStream(file);
                    readerInputStreamSink = new InputStreamReader(streamFileInputSink, "UTF-8");
                    readerBufferdSink = new BufferedReader(readerInputStreamSink);
                    System.out.println(readerBufferdSink.readLine());
                }
                catch (IOException exceptIO)
                {
                    System.err.println("Error with stream reading");
                }
                finally
                {
                    try
                    {
                        if (readerBufferdSink != null)
                        {
                            readerBufferdSink.close();
                        }
                    }
                    catch (IOException exceptIO)
                    {
                        System.err.println("Error closing BufferedReader");
                    }

                    try
                    {
                        if (readerInputStreamSink != null)
                        {
                            readerInputStreamSink.close();
                        }
                    }
                    catch (IOException exceptIO)
                    {
                        System.err.println("Error closing InputStreamReader");
                    }

                    try
                    {
                        if (streamFileInputSink != null)
                        {
                            streamFileInputSink.close();
                        }
                    }
                    catch (IOException exceptIO)
                    {
                        System.err.println("Error closing FileInputStream");
                    }
                }
            }
        }
    }
}
