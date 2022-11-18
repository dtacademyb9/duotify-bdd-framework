package com.duotify.utilities;

import java.sql.*;

public class TestJDBC {


    public static void main(String[] args) throws SQLException {


        // Create connection

        String url = "jdbc:mysql://db-duotech.cc652zs7kmja.us-east-2.rds.amazonaws.com/employees";
        String username = "duotech";
        String password = "duotech2022";
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connection established");

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet resultSet = statement.executeQuery("select *  from employees limit 20");

        // Initially the result set has a cursor position before the first row.
        resultSet.next();//Moves the cursor forward one row from its current position

        // Indexes in JDBC is 1-based
        // You can retrieve info from the row cells by their index
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));
//        System.out.println(resultSet.getString(3));

        // You can retrieve info from the row cells by their column name
//        System.out.println(resultSet.getString("dept_no"));
//        System.out.println(resultSet.getString("dept_name"));
//        System.out.println(resultSet.getString("heloo"));


        resultSet.next();

        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));


        resultSet.absolute(8); //Moves the cursor to the given row number in this ResultSet object

        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));


        resultSet.first();  //Moves the cursor to the first row in this ResultSet object.
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));

        resultSet.last(); //Moves the cursor to the last row in this ResultSet object.
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));
//
//sout

        System.out.println("------------------------------------");
        resultSet.beforeFirst(); //Moves the cursor to the front of this ResultSet object, just before the first row.
        while(resultSet.next()){
            System.out.println(resultSet.getString(1) + " " +resultSet.getString(2));
        }

        resultSet.beforeFirst();


        // get row no for the result set
        // move to the last row and get the row no
        resultSet.last();
        int rowCount = resultSet.getRow();

        System.out.println(rowCount);

        // to get the column count get ResultSetMetaData object on result set and use its getColumnCount() method

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println(columnCount);

        resultSet.beforeFirst();

        for (int i = 1; i <=rowCount ; i++) {
            resultSet.next();
            for (int j = 1; j <=columnCount ; j++) {
                System.out.print(resultSet.getString(j) + "\t");
            }
            System.out.println();
        }


        // To create,update and delete operations you need to use executeUpdate()

       // modify existing row info
        statement.executeUpdate("UPDATE employees SET last_name='Schmo' where emp_no=10001");
        // delete existing row
        statement.executeUpdate("DELETE from employees where emp_no=2");
        // add a new row with employee info
        statement.executeUpdate("INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) values (1, '1999-03-04', 'Moe', 'Do', 'M', '2022-09-09')");

        resultSet.close();
        statement.close();
        connection.close();



    }
}
