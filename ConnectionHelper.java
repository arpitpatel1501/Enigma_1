package com.example.intel.testapplicationhackthon;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Created by Intel on 3/2/2019.
 */
public class ConnectionHelper {


    String IP,DB,DBUserName,DBPassword;
    @SuppressLint("NewApi")
    public Connection connections(){

        // from your local or website

        IP = "10.20.37.126";
        DB = "SDTS";
        DBUserName = "hakathon";
        DBPassword = "H@k@thon";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection = null;
        String ConnectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + IP +";databaseName="+ DB + ";user=" + DBUserName+ ";password=" + DBPassword + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
