package com.jurin_n.jax_rs.representation;

import com.jurin_n.domain.model.practice.record.PracticeRecord;
import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;

public class PracticeRecordRepresentation implements BaseJsonMarshaller {
	private String id;
	private String planId;
	
	public PracticeRecordRepresentation(){
		super();
	}

	public PracticeRecordRepresentation(PracticeRecord aRecord) {
		this.id = aRecord.getPracticeRecordId().getId();
		this.planId = aRecord.getPracticePlanId().getId();
	}

	public String getId() {
		return this.id;
	}

	public String getPlanId() {
		return this.planId;
	}
}
