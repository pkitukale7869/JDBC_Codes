package com.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class deleteApp {

	public static void main(String[] args) {
		Connection connection =null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		ResultSet resultSet = null;
		int sid= 0;
		String scity = null;

		try {
			connection = jdbcUtil.getJdbcConnection();
			String sqlUpdateQuery = "delete from students where sid = ?";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlUpdateQuery);

			if (pstmt != null)
			{	
				scanner = new Scanner (System.in);
			
				System.out.println("Enter the ID of Student :: ");
				sid = scanner.nextInt();				
				
				//user precompiled query to set the values
				
				pstmt.setInt(1,sid);
				//Execute the Query
				
				int rowCount = pstmt.executeUpdate();
				System.out.println("No of Rows Updated :: "+rowCount);
			}
						
		}catch(IOException ie) {
			ie.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jdbcUtil.cleanUp(connection, pstmt, null);
				scanner.close();
				System.out.println("closing the resources...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
