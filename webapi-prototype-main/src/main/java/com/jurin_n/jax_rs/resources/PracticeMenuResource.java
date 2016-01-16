package com.jurin_n.jax_rs.resources;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jurin_n.application.PracticeApplicationService;
import com.jurin_n.domain.model.practice.menu.PracticeMenu;
import com.jurin_n.domain.model.practice.menu.PracticeMenuId;
import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;
import com.jurin_n.jax_rs.representation.PracticeMenuRepresentation;

@Path("/practice/menu")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PracticeMenuResource {
	@Inject
	PracticeApplicationService ts;
	
	@GET
	public Response getPracticeMenuList(){
		//サービス
		List<PracticeMenu> list = ts.getPracticeMenuList();
		
		//レスポンス
		Response response = this.practiceMenuListResponse(list);
		return response;
	}
	
	private Response practiceMenuListResponse(List<PracticeMenu> list){
		List<BaseJsonMarshaller> response = new ArrayList<>();
		for(PracticeMenu menu : list){
			PracticeMenuRepresentation res
					= new PracticeMenuRepresentation(
							 menu.getPracticeMenuId().getId()
							,menu.getMenu()
							);
			response.add(res);
		}
		GenericEntity<List<BaseJsonMarshaller>> entity
			= new GenericEntity<List<BaseJsonMarshaller>>(response){};
		
		//レスポンス
		return Response
				.status(Response.Status.OK)
				.entity(entity)
				.build(); 
	}
	
	@GET
	@Path("{id}")
	public Response getPracticeMenu(@PathParam("id") String id){
		//サービス
		PracticeMenu aMenu = ts.getPracticeMenu(new PracticeMenuId(id));
		
		//レスポンス
		Response response = this.practiceMenuResponse(aMenu);
		return response;
	}
	
	private Response practiceMenuResponse(PracticeMenu aMenu){
		PracticeMenuRepresentation res
					= new PracticeMenuRepresentation(
							 aMenu.getPracticeMenuId().getId()
							,aMenu.getMenu()
							);
		
		//レスポンス
		return Response
				.status(Response.Status.OK)
				.entity(res)
				.build(); 
	}
	
	@POST
	public Response addPracticeMenu(PracticeMenuRepresentation aMenu){
		//サービス
		PracticeMenu Menu = new PracticeMenu(aMenu);
		ts.addPracticeMenu(Menu);
		
		//レスポンス
		Response response = this.practiceMenuResponse(
										 Menu
										,Response.Status.CREATED
										);
		return response;
	}
	
	@PUT
	@Path("{id}")
	public Response updatePracticeMenu(
			 @PathParam("id") String id
			,PracticeMenuRepresentation aMenu){
		//サービス
		PracticeMenu menu = new PracticeMenu(
				 new PracticeMenuId(id)
				,aMenu.getMenu()
				);
		ts.updatePracticeMenu(menu);
		
		//レスポンス
		Response response = this.practiceMenuResponse(
										 menu
										,Response.Status.OK
										);
		return response;
	}
	
	private Response practiceMenuResponse(
			 PracticeMenu aMenu
			,Response.Status status
			){
		BaseJsonMarshaller res
			= new PracticeMenuRepresentation(
					 aMenu.getPracticeMenuId().getId()
					,aMenu.getMenu()
					);
		//レスポンス
		return Response
				.status(status)
				.entity(res)
				.build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletePracticeMenu(@PathParam("id") String id){
		//メンバ削除
		ts.deletePracticeMenu(new PracticeMenuId(id));
		
		//レスポンス
		return Response
				.status(Response.Status.OK)
				.build();
	}
}
