package com.example.intel.testapplicationhackthon;

import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Intel on 3/2/2019.
 */


public class GetData {

    public static String resultdata ;
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    public List<Map<String,String>> getData(){
        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String, String>>();

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connections();
            if(connect == null){
                ConnectionResult = "Check Your Internet Access!";
            }
            else{
                Statement stmt1 = connect.createStatement();
                String _sql = "insert into Student (Std_id,Std_name,addr,ParentName,EmailAddr,ClassId,RFID) values('112424','Vaishwi Patel' ,'Ahmedabad','kuyrutry',1,'12353245');";

                stmt1.execute(_sql);
                System.out.print("-----------------yyyyyyyyyyeeeeeeeeeeeesssssssss------------------");
                String query = "select * from student;";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);

               resultdata = rs.toString();

               System.out.print("Vaishwi//////"+"\n"+resultdata);

                while (rs.next()){
                    Map<String,String> datanum = new HashMap<String, String>();
                    datanum.put("ID",rs.getString("Std_id:"));
                    datanum.put("Name",rs.getString("Std_name:"));
                    datanum.put("Address",rs.getString("Addr:"));
                    data.add(datanum);
                    System.out.println(rs.getString("std_id")+":"+rs.getString("std_name"));
                }

                ConnectionResult = "Successfull";
                isSuccess = true;
                connect.close();
            }
        } catch (Exception ex){
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }

        return  data;

    }
}
