import java.sql.Connection;
import java.sql.PreparedStatement;
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
                PreparedStatement sqlStatement = null;
                try
                {
                    dbConnection = DatabaseUtil.getConnection();
                    sqlStatement = dbConnection.prepareStatement("update users set hitcount=hitcount+1 where name=?");
                    for (int i = 0; i < names.length; i++)
                    {
                        sqlStatement.setString(1, names[i]);
                        sqlStatement.addBatch();
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
                        System.err.println("Error closing PreparedStatement");
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
