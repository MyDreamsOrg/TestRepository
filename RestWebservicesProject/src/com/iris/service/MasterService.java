package com.iris.service;

/**
 * 
 */



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iris.dao.WebRestDAO;
import com.iris.dao.WebRestDAOImpl;
import com.iris.module.MasterData;

/**
 * @author sbali
 *
 */
@Path("/master")
public class MasterService {

	private WebRestDAO webRestDAO = new WebRestDAOImpl();

	@GET
	@Path("/grossBankCreditSectors/{date}")
	@Produces(MediaType.APPLICATION_XML)
	public MasterData getMasterData(@PathParam("date") String date) {


		return webRestDAO.getMasterData(103,date);
	}

}
