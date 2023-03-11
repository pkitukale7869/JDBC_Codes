package com.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class selectApp {

	public static void main(String[] args) {
		Connection connection =null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		ResultSet resultSet = null;
		int sid= 0;

		try {
			connection = jdbcUtil.getJdbcConnection();
			String sqlSelectQuery = "Select * from students where sid=?";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);

			if (pstmt != null)
			{	
				scanner = new Scanner (System.in);
			
				System.out.println("Enter the ID of Student :: ");
				sid = scanner.nextInt();				
				
				//user precompiled query to set the values
				
				pstmt.setInt(1,sid);
				
				//Execute the Query
				
				resultSet = pstmt.executeQuery();
			}
			if(resultSet != null)
			{
				if(resultSet.next())
				{
					System.out.println("SID\tSNAME\tSPHONE\tSCITY");
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2) + "\t" + resultSet.getInt(3)
					+ "\t" + resultSet.getString(4));
				}
				else
				{
					System.out.println("Record not available for the give id :: " + sid);
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
				jdbcUtil.cleanUp(connection, pstmt, resultSet);
				System.out.println("closing the resources...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
