package com.jurin_n.jax_rs.resources;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.jurin_n.application.PracticeApplicationService;
import com.jurin_n.domain.model.practice.record.PracticeRecord;
import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;
import com.jurin_n.jax_rs.representation.PracticeRecordRepresentation;

@Path("/practice/record")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PracticeRecordResource {
	@Inject
	PracticeApplicationService ts;
	
	@GET
	public Response getPracticeRecordList(){
		//サービス
		List<PracticeRecord> list = ts.getPracticeRecordList();
	
		//レスポンス
		if(list == null){
			return Response
					.status(Response.Status.NOT_FOUND)
					.build(); 	
		}
		Response response = this.practiceRecordListResponse(list);
		return response;
	}
	
	private Response practiceRecordListResponse(List<PracticeRecord> list) {
		List<BaseJsonMarshaller> response = new ArrayList<>();
		for(PracticeRecord record : list){
			PracticeRecordRepresentation res
				= new PracticeRecordRepresentation(record);
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

	@POST
	public Response addPracticeRecord(PracticeRecordRepresentation aRecord){
		//サービス
		PracticeRecord addRecord = new PracticeRecord(aRecord);

		ts.addPracticeRecord(addRecord);
		PracticeRecord record = ts.getPracticeRecord(addRecord.getPracticeRecordId());
		
		//レスポンス
		Response response = this.practiceRecordResponse(
										 record
										,Response.Status.CREATED
										);
		return response;
	}

	private Response practiceRecordResponse(PracticeRecord aRecord, Status status) {
		BaseJsonMarshaller res
			= new PracticeRecordRepresentation(aRecord);
	
		//レスポンス
		return Response
				.status(status)
				.entity(res)
				.build();
	}
}
