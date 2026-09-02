import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRecordUpdater
{
    public void run() throws Throwable
    {
        String data;
        if (true)
        {
            data = "";
            {
                InputStreamReader readerInputStream = null;
                BufferedReader readerBuffered = null;

                try
                {
                    readerInputStream = new InputStreamReader(System.in, "UTF-8");
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
                }
            }
        }
        else
        {
            data = null;
        }

        if (true)
        {
            Connection dbConnection = null;
            Statement sqlStatement = null;
            ResultSet resultSet = null;
            try
            {
                dbConnection = DatabaseUtil.getConnection();
                sqlStatement = dbConnection.createStatement();

                resultSet = sqlStatement.executeQuery("select * from users where name='"+data+"'");
                System.out.println(resultSet.getRow());
            }
            catch (SQLException exceptSql)
            {
                System.err.println("Error getting database connection");
            }
            finally
            {
                try
                {
                    if (resultSet != null)
                    {
                        resultSet.close();
                    }
                }
                catch (SQLException exceptSql)
                {
                    System.err.println("Error closing ResultSet");
                }

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
}
