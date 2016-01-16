package com.jurin_n.jax_rs.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jurin_n.application.TeamApplicationService;
import com.jurin_n.domain.model.team.Team;

@Path("/team")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {
	  @Inject
	  TeamApplicationService ts;

	  @GET
	  @Path("{id}")
	  public Response getTeam(@PathParam("id") String id){
		  //サービス
		  Team t = ts.getTeamById(id);

		  //レスポンス
		  return Response
				  .status(Response.Status.OK)
				  .entity(t)
				  .build();
	  }
	  
	  @POST
	  public Response createTeam(Team t){

		  //サービス
		  ts.createTeam(t);
		  
		  //レスポンス
		  return Response
				  .status(Response.Status.CREATED)
				  .build();
	  }
}
