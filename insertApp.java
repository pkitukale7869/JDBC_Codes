package com.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class insertApp {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = jdbcUtil.getJdbcConnection();

			if (connection != null)
				statement = connection.createStatement();

			if (statement != null)
				resultSet = statement.executeQuery("select sid,sname,sphone,scity from students");

			if (resultSet != null) {
				System.out.printf("%-2s%14s%12s%15s","SID","SNAME","SPHONE","SCITY");
				System.out.println();
				while (resultSet.next()) {
					System.out.printf("%2d%15s%12d%15s", resultSet.getInt(1), resultSet.getString(2),
							resultSet.getInt(3), resultSet.getString(4));
					System.out.println();
				}
			}
		}catch(IOException ie) {
			ie.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jdbcUtil.cleanUp(connection, statement, resultSet);
				System.out.println("closing the resources...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
