package com.iris.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

import com.iris.module.ChildElement;
import com.iris.module.MasterData;
import com.iris.service.DBConnection;

/**
 * @author sbali
 *
 */

public class WebRestDAOImpl implements WebRestDAO {

	@Override
	public MasterData getMasterData(Integer masterCode, String date) {

		System.out.println("In DataBase class ...");

		// logger.debug("Call getMasterData() method ");
		Stack<ChildElement> s = new Stack();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		MasterData masterData = new MasterData();
		ArrayList<ChildElement> childElementArray = new ArrayList<ChildElement>();
		
		try {
			pst = connection
					.prepareStatement("SELECT  value,orders,parent_path, id FROM tbl_master ORDER BY orders ASC");
			resultSet = pst.executeQuery();
			int count = 1;
			while (resultSet.next()) {
				count++;
				ChildElement childElement = new ChildElement();

				childElement.setLabel(resultSet.getString(1));
				childElement.setDocumentLabel(resultSet.getString(1));
				childElement.setOrder(resultSet.getInt(2));
				childElement.setPrentPath(resultSet.getString(3));

				String path = resultSet.getString(3);
				childElement.setId(resultSet.getInt(4));
	
				if (path == null) {
					if (!s.isEmpty())
						s.pop();
					s.push(childElement);
					masterData.getChildElement().add(childElement);
				} else {
					ChildElement ch=s.peek();
					System.out.println("iCrr ID="+childElement.getId()+"  parent =>"+ch.getPrentPath()+" child path =>"+childElement.getPrentPath());	
					
				 if(( ch.getPrentPath()== null && childElement.getPrentPath()!=null) || ch.getPrentPath().length()< childElement.getPrentPath().length()){
				  
						System.out.println(" less than  < condition");
				    	 ch.getChildElement().add(childElement);
						s.push(childElement);
					}
				 
				 else if(ch.getPrentPath().equals(childElement.getPrentPath())){
						System.out.println("  == condition");
						if (!s.isEmpty())
							s.pop();
					//	System.out.println("in '==' 3rd ele  ID =>"+ch.getPrentPath()+" child path =>"+childElement.getPrentPath());
						ChildElement temp1=s.peek();
						temp1.getChildElement().add(childElement);
						s.push(childElement);
						
					}
					else  {
						
						System.out.println("  > condition");
						s.pop();
						ChildElement temp2=s.peek();
						System.out.println("P - path"+temp2.getPrentPath()+ "child path= "+childElement.getPrentPath());
					   if(temp2.getPrentPath().equals(childElement.getPrentPath())){
						   s.pop();
					   }
					   
						ChildElement temp3=s.peek();
						temp3.getChildElement().add(childElement);
						s.push(childElement);
								
					}
			//	 System.out.println(s.size());

				}
								System.out.println(s.size());
			}
			// System.out.println(childElements);
			System.out.println(count);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			e.printStackTrace();
		}

		return masterData;

	} // end of method

} // end of the class
