package com.Justin;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static String tableToManipulate = "";


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
                        System.out.println("Select a table:");
                        dbRead();
                        dbCreate();
                        break;
                    case "r":
                        dbRead();
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
                        System.out.println("Goodbye...");
                        done = true;
                        break;
                }
            }
        }




    }

    public static void dbRead(){
        //output tables/columns
        //get table to read
        //get columns to read

        ResultSet tableList = dbQuery("show tables", "Read");

        try{

            int count = 0;
            while (tableList.next()){
                System.out.println(++count + ") Table: " + tableList.getString("Tables_In_JavaTest"));
            }
        } catch (SQLException e){
            System.out.println("ERROR: Null result set");
            e.printStackTrace();
            System.exit(0);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Which table would you like to read?");
        String in = scanner.nextLine();
        String qry = "Select * from " + in;
        tableToManipulate = in;


        ResultSet res = dbQuery(qry, "Read");
        try{
            for(int n = 1; n <= res.getMetaData().getColumnCount(); n++){
                System.out.print(res.getMetaData().getColumnName(n) + "\t");
            }
            System.out.println();

            while (res.next()){

                for(int n = 1; n <= res.getMetaData().getColumnCount(); n++){
                    System.out.print(res.getString(n) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e){
            System.out.println("ERROR: Null result set");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void dbCreate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new row in the format col1val, col2val, col3val, ...");
        String in = scanner.nextLine().trim();

        //Get columns

        String qry = "Select * from " + tableToManipulate; //Lazy way

        ResultSet r = dbQuery(qry, "Read");
        String columnSet = "(";
        try{
            for(int n = 1; n <= r.getMetaData().getColumnCount(); n++){
                columnSet += r.getMetaData().getColumnName(n);

                if (n < r.getMetaData().getColumnCount()){
                    columnSet += ",";
                }
            }
            columnSet += ")";
        } catch (SQLException e){
            System.out.println("ERROR: No columns");
            e.printStackTrace();
            System.exit(0);
        }

        dbQuery("insert into " + tableToManipulate + columnSet + " values (" + in + ")", "Exec");


    }

    public static ResultSet dbQuery(String q, String type){


        DBProp connProperties = new DBProp();
        String db = connProperties.db;
        String user = connProperties.user;
        String pw = connProperties.pw;

        if(type == "Read") {
            try {
                Connection conn = DriverManager.getConnection(db, user, pw);
                Statement qry = conn.createStatement();
                ResultSet rs = qry.executeQuery(q);
                return rs;


            } catch (SQLException e) {
                System.out.println("Query Error. Query: " + q);
                e.printStackTrace();
            }

        } else if(type == "Exec"){
            try {
                Connection conn = DriverManager.getConnection(db, user, pw);
                Statement qry = conn.createStatement();
                qry.executeUpdate(q);
                return null;


            } catch (SQLException e) {
                System.out.println("Query Error. Query: " + q);
                e.printStackTrace();
            }

        }


        return null;
    }



}
