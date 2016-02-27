package com.jurin_n.jax_rs.resources;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.jurin_n.application.PracticeApplicationService;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.practice.plan.PracticePlan;
import com.jurin_n.domain.model.practice.plan.PracticePlanId;
import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;
import com.jurin_n.jax_rs.representation.PracticePlanRepresentation;

@Path("/practice/plan")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@DefaultProcess
public class PracticePlanResource extends BaseResource{

	@Inject PracticeApplicationService ts;
	/*
	@GET
	public Response getPracticePlanList(){
		//認可
		checkPermissions(PermissionValue.readPlan);
	
		//サービス呼び出し
		List<PracticePlan> list = ts.getPracticePlanList();
	
		//レスポンス生成
		if(list == null){
			return Response
					.status(Response.Status.NOT_FOUND)
					.build(); 	
		}
		return this.practicePlanListResponse(list);
	}
	*/
	
	/*
	 * 
	 * 前処理と後処理を親クラスで行うやり方。
	@GET
	public Response getList(){
		return process("getPracticePlanList");
	}
	
	@Permmisions(PermissionValue.readPlan)
	public Response getPracticePlanList(){
		//サービス呼び出し
		List<PracticePlan> list = ts.getPracticePlanList();

		//レスポンス生成
		if(list == null){
			return Response
					.status(Response.Status.NOT_FOUND)
					.build(); 	
		}
		return this.practicePlanListResponse(list);
	}
	*/
	
	@GET
	@Permmisions(PermissionValue.readPlan)
	public Response getList(@Context HttpHeaders headers, String json){
		//サービス呼び出し
		List<PracticePlan> list = ts.getPracticePlanList();

		//レスポンス生成
		if(list == null){
			return Response
					.status(Response.Status.NOT_FOUND)
					.build(); 	
		}
		return this.practicePlanListResponse(list);
	}
	
	private Response practicePlanListResponse(List<PracticePlan> list) {
		List<BaseJsonMarshaller> response = new ArrayList<>();
		for(PracticePlan plan : list){
			PracticePlanRepresentation res
				= new PracticePlanRepresentation(plan);
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
	public Response getPracticePlan(@PathParam("id") String id){
		//サービス
		PracticePlan plan = ts.getPracticePlan(new PracticePlanId(id));
		
		//レスポンス
		Response response = this.practicePlanResponse(
											 plan
											,Response.Status.OK);
		return response;
	}

	@POST
	public Response addPracticePlan(PracticePlanRepresentation aPlan){
		//サービス
		PracticePlan addPlan = new PracticePlan(aPlan);

		ts.addPracticePlan(addPlan);
		PracticePlan plan = ts.getPracticePlan(addPlan.getPracticePlanId());
		
		//レスポンス
		Response response = this.practicePlanResponse(
										 plan
										,Response.Status.CREATED
										);
		return response;
	}

	private Response practicePlanResponse(PracticePlan aPlan, Status status) {
		BaseJsonMarshaller res
			= new PracticePlanRepresentation(aPlan);
		
		//レスポンス
		return Response
				.status(status)
				.entity(res)
				.build();
	}

	@PUT
	@Path("{id}")
	public Response updatePracticePlan(
			 @PathParam("id") String id
			,PracticePlanRepresentation aPlan){
		//サービス
		ts.updatePracticePlan(
				 new PracticePlanId(id)
				,aPlan.getStatus()
				);
		
		//レスポンス
		return Response
				.status(Response.Status.OK)
				.build();
	}
}