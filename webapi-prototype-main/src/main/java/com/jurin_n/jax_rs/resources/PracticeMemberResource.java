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
import com.jurin_n.domain.model.practice.member.PracticeMember;
import com.jurin_n.domain.model.practice.member.PracticeMemberId;
import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;
import com.jurin_n.jax_rs.representation.PracticeMemberRepresentation;

@Path("/practice/member")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PracticeMemberResource {
	@Inject
	PracticeApplicationService ts;
	
	@GET
	public Response getPracticeMemberList(){
		//サービス
		List<PracticeMember> list = ts.getPracticeMemberList();
		
		//レスポンス
		Response response = this.practiceMemberListResponse(list);
		return response;
	}
	
	private Response practiceMemberListResponse(List<PracticeMember> list){
		List<BaseJsonMarshaller> response = new ArrayList<>();
		for(PracticeMember member : list){
			PracticeMemberRepresentation res
					= new PracticeMemberRepresentation(
							 member.getPracticeMemberId().getId()
							,member.getName()
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
	public Response getPracticeMember(@PathParam("id") String id){
		//サービス
		PracticeMember aMember = ts.getPracticeMember(new PracticeMemberId(id));
		
		//レスポンス
		Response response = this.practiceMemberResponse(aMember);
		return response;
	}
	
	private Response practiceMemberResponse(PracticeMember aMember){
		PracticeMemberRepresentation res
					= new PracticeMemberRepresentation(
							 aMember.getPracticeMemberId().getId()
							,aMember.getName()
							);
		
		//レスポンス
		return Response
				.status(Response.Status.OK)
				.entity(res)
				.build(); 
	}
	
	@POST
	public Response addPracticeMember(PracticeMemberRepresentation aMember){
		//サービス
		PracticeMember member = new PracticeMember(aMember);
		ts.addPracticeMember(member);
		
		//レスポンス
		Response response = this.practiceMemberResponse(
										 member
										,Response.Status.CREATED
										);
		return response;
	}
	
	@PUT
	@Path("{id}")
	public Response updatePracticeMember(
			 @PathParam("id") String id
			,PracticeMemberRepresentation aMember){
		//サービス
		PracticeMember member = new PracticeMember(
				 new PracticeMemberId(id)
				,aMember.getName()
				);
		ts.updatePracticeMember(member);
		
		//レスポンス
		Response response = this.practiceMemberResponse(
										 member
										,Response.Status.OK
										);
		return response;
	}
	
	private Response practiceMemberResponse(
			 PracticeMember aMember
			,Response.Status status
			){
		BaseJsonMarshaller res
			= new PracticeMemberRepresentation(
					 aMember.getPracticeMemberId().getId()
					,aMember.getName()
					);
		//レスポンス
		return Response
				.status(status)
				.entity(res)
				.build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletePracticeMember(@PathParam("id") String id){
		//メンバ削除
		ts.deletePracticeMember(new PracticeMemberId(id));
		
		//レスポンス
		return Response
				.status(Response.Status.OK)
				.build();
	}
}
