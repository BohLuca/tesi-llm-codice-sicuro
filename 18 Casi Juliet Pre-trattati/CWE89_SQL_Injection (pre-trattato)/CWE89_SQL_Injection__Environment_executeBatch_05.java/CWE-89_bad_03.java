import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class UserRecordUpdater
{
    private boolean enabled = true;

    public void run() throws Throwable
    {
        String data;
        if (enabled)
        {
            data = System.getenv("ADD");
        }
        else
        {
            data = null;
        }

        if (enabled)
        {
            if (data != null)
            {
                String names[] = data.split("-");
                int successCount = 0;
                Connection dbConnection = null;
                Statement sqlStatement = null;
                try
                {
                    dbConnection = DatabaseUtil.getConnection();
                    sqlStatement = dbConnection.createStatement();
                    for (int i = 0; i < names.length; i++)
                    {
                        sqlStatement.addBatch("update users set hitcount=hitcount+1 where name='" + names[i] + "'");
                    }
                    int resultsArray[] = sqlStatement.executeBatch();
                    for (int i = 0; i < names.length; i++)
                    {
                        if (resultsArray[i] > 0)
                        {
                            successCount++;
                        }
                    }
                    System.out.println("Succeeded in " + successCount + " out of " + names.length + " queries.");
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
    }
}
