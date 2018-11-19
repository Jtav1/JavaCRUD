package com.Justin;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //dbprop format
        /*public class DBProp {
            String db = "jdbc:mysql://mysql db location/tablename";
            String user = "db username";
            String pw = "db password";
        }*/



        Boolean done = false;

        String in = "";
        Scanner scanner = new Scanner(System.in);

        while (!done){
            System.out.println("C/c, R/r, U/u, or D/d (or anything else for exit)");
            in = scanner.nextLine().toLowerCase();

            if(in.length() < 1){
                done = true;

            } else {
                switch(in.substring(0,1)){
                    case "c":
                        System.out.println("You want to create");

                        //output tables/columns
                        //Get table name
                        //Get input record
                        //call create
                        break;
                    case "r":
                        System.out.println("You want to read");
                        dbRead();
                        done = true;
                        break;
                    case "u":
                        System.out.println("You want to update");

                        //output tables/columns
                        //get table to update
                        //get columns to update
                        break;
                    case "d":
                        System.out.println("You want to delete");

                        //output tables/columns
                        //get table to delete from
                        //get columns from table
                        //get row to delete
                        break;
                    default:
                        System.out.println("We're done, then.");

                        done = true;
                        break;
                }
            }
        }


        /*

        */

    }

    public static void dbRead(){
        //output tables/columns
        //get table to read
        //get columns to read

        System.out.println("We will read now");

        ResultSet tableList = dbquery("show tables");

        try{
            while (tableList.next()){
                System.out.println("Table: " + tableList.getString("Tables_In_JavaTest"));
            }
        } catch (SQLException e){
            System.out.println("ERROR: Null result set");
            e.printStackTrace();
            System.exit(0);
        }






    }






    public static ResultSet dbquery(String q){

        DBProp connProperties = new DBProp();
        String db = connProperties.db;
        String user = connProperties.user;
        String pw = connProperties.pw;

        try {
            Connection conn = DriverManager.getConnection(db, user, pw);
            Statement qry = conn.createStatement();
            ResultSet rs = qry.executeQuery(q);

            return rs;


        } catch (SQLException e){
            System.out.println("Query Error. Query: " + q);
            e.printStackTrace();
        }

        return null;

    }



}
