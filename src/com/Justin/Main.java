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

        DBProp connProperties = new DBProp();
        String db = connProperties.db;
        String user = connProperties.user;
        String pw = connProperties.pw;

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

                        //output tables/columns
                        //get table to read from
                        //Get columns to read
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
        try {
            Connection conn = DriverManager.getConnection(db, user, pw);
            Statement qry = conn.createStatement();
            String sql = "select * from FoodGroups";
            ResultSet rs = qry.executeQuery(sql);

            while(rs.next()){
                System.out.println(rs.getString("idFoodGroups") + " " + rs.getString("GroupName"));
            }

        } catch (SQLException e){
            System.out.println("Error: ");
            e.printStackTrace();
        }
        */

    }

}
