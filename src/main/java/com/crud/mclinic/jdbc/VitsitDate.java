package com.crud.mclinic.jdbc;


import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


@Getter
public class VitsitDate{

    public  void remaindVisit() throws SQLException {

       /* DbManager dbManager = DbManager.getInstance();

        String sqlQuery = "SELECT * FROM VISIT WHERE (VISIT_DATE-NOW())<=2";
        Statement statement = dbManager.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);
        List<>list =rs.getDate("co","vv");

        int counter = 0;
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + ", " + rs.getString("FIRSTNAME") + ", " + rs.getString("LASTNAME"));
            counter++;
        }
        rs.close();
        statement.close();*/
    }
}
