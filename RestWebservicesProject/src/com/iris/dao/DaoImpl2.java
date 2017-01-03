/**
 * 
 */
package com.iris.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.iris.module.ChildElement;
import com.iris.module.MasterData;
import com.iris.service.DBConnection;

/**
 * @author MY
 *
 */
public class DaoImpl2 implements WebRestDAO {

	@Override
	public MasterData getMasterData(Integer masterCode, String date) {

		System.out.println("In DataBase class ...");
	
		Stack s = new Stack();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		MasterData masterData = new MasterData();
		ArrayList<ChildElement> childElementArray = new ArrayList<ChildElement>();
		List<ChildElement> childElements = new ArrayList<ChildElement>();
		masterData.setChildElement(childElements);
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

				if (path == null)
					childElements.add(childElement);
				else {

					int newParentId = 0;
					String[] idSplit = null;
					if (path != null)
						if (path.contains(":")) {
							idSplit = path.split(":");
							newParentId = Integer
									.parseInt(idSplit[idSplit.length - 1]);
						} else {

							newParentId = Integer.parseInt(path);
						}
				//	System.out.println(path + " --> " + childElement.getId()
					//		+ "  -->" + newParentId);
					for (ChildElement temp : childElements) {

						getChildElement(newParentId,temp,childElement);
						

					}
				}
			}
			//System.out.println(childElements);
			System.out.println(count);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return masterData;

	} // end of method

	private void getChildElement(int parentId, ChildElement childElement,ChildElement newChildElement) {

		if (childElement.getId() == parentId) {
			childElement.getChildElement().add(newChildElement);
		} else {
			List<ChildElement> list = childElement.getChildElement();
			if (list != null) {
				Iterator<ChildElement> it = list.iterator();
				while (it.hasNext()) {
					ChildElement element = it.next();
					if (parentId == element.getId())

							element.getChildElement().add(newChildElement);
					else
						 getChildElement(parentId, element,newChildElement);
				}
			}
		}

	}

}
