import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class UserRecordUpdater
{
    public void run() throws Throwable
    {
        String data;

        data = "";

        {
            Socket socket = null;
            BufferedReader readerBuffered = null;
            InputStreamReader readerInputStream = null;

            try
            {
                socket = new Socket("host.example.org", 39544);

                readerInputStream = new InputStreamReader(socket.getInputStream(), "UTF-8");
                readerBuffered = new BufferedReader(readerInputStream);

                data = readerBuffered.readLine();
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

        Connection dbConnection = null;
        Statement sqlStatement = null;

        try
        {
            dbConnection = DatabaseUtil.getConnection();
            sqlStatement = dbConnection.createStatement();

            Boolean result = sqlStatement.execute("insert into users (status) values ('updated') where name='"+data+"'");

            if (result)
            {
                System.out.println("Name, " + data + ", updated successfully");
            }
            else
            {
                System.out.println("Unable to update records for user: " + data);
            }
        }
        catch (SQLException exceptSql)
        {
            System.err.println("Error getting database connection");
        }
        finally
        {
            try
            {
                if (sqlStatement != null)
                {
                    sqlStatement.close();
                }
            }
            catch (SQLException exceptSql)
            {
                System.err.println("Error closing Statement");
            }

            try
            {
                if (dbConnection != null)
                {
                    dbConnection.close();
                }
            }
            catch (SQLException exceptSql)
            {
                System.err.println("Error closing Connection");
            }
        }
    }
}
