/*
Filename : KRD_502_Improper_Resource_Shutdown__basic_db_Connection_0101_good.java
CWEID    : CWE404
sinkname : db_Connection
sinkline : 90,
makedate : 2013 06 20
license  : Copyright KISA.
*/

package testcases.KRD_502_Improper_Resource_Shutdown;

import testcasesupport.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import testcasesupport.AbstractTestCase;
import testcasesupport.IO;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;


import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

import java.util.logging.Logger;

public class KRD_502_Improper_Resource_Shutdown__basic_db_Connection_0101_good extends AbstractTestCase
{


    public void good() throws Throwable
    {

        goodB2G();
    }

    
    
    private void goodB2G() throws Throwable
    {
        ResultSet rs = null;
		Connection conn = null;
		PreparedStatement statement = null;

        try
        {
            int num = (new SecureRandom()).nextInt(200);

            conn = IO.getDBConnection();
            statement = conn.prepareStatement("select * from users where id=?");
            statement.setInt(1, num);
            
            rs = statement.executeQuery();
            
            if (rs.first())
            {
                IO.writeString(rs.toString());
            }
        }
		catch(SQLFeatureNotSupportedException sfnse)
        {
            IO.logger.log(Level.WARNING, "SQL feature not supported", sfnse);
        }
		catch(SQLException se)
        {
            IO.logger.log(Level.WARNING, "SQL Error", se);
        }
				
        finally
        {
            try
            {
                if(rs != null )
                {
                	/* FIX: Close DB objects in a finally block */
                    rs.close();
                }
            }
            catch(SQLException e)
            {
                IO.logger.log(Level.WARNING, "Error closing ResultSet", e);
            }

			try
			{
				if(statement != null )
				{
					statement.close();
				}
			}
			catch(SQLException e)
			{
				IO.logger.log(Level.WARNING, "Error closing PreparedStatement", e);
			}

			try
			{
				if (conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException e )
			{
				IO.logger.log(Level.WARNING, "Error closing Connection", e);
			}
        }
    }

    /* Below is the main(). It is only used when building this testcase on
       its own for testing or for building a binary to use in testing binary
       analysis tools. It is not used when compiling all the testcases as one
       application, which is how source code analysis tools are tested. */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}

