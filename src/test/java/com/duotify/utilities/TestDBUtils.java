package com.duotify.utilities;

import java.util.List;
import java.util.Map;

public class TestDBUtils {

    public static void main(String[] args) {


        DBUtils.createConnection();

        List<List<Object>> result = DBUtils.getQueryResultAsListOfLists("select *  from albums");

        for (List<Object> eachRow : result) {
            System.out.println(eachRow.get(4));
        }


        System.out.println("_______________________________________________");
        List<Map<String, Object>> queryResultListOfMaps = DBUtils.getQueryResultAsListOfMaps("select *  from albums");

        for (Map<String, Object> eachRow : queryResultListOfMaps) {
            System.out.println(eachRow.get("artworkPath"));
        }


        List<String> columnNames = DBUtils.getColumnNames("select *  from albums");

        System.out.println(columnNames);







    }
}
