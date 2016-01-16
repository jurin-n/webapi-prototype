package com.jurin_n.domain.model.practice.record;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jurin_n.domain.model.practice.member.PracticeMemberId;
import com.jurin_n.domain.model.practice.menu.PracticeMenuId;
import com.jurin_n.domain.model.practice.plan.PracticePlanId;
import com.jurin_n.jax_rs.representation.PracticeRecordRepresentation;

@Entity
@Table(name="t_PracticeRecord")
@NamedQueries({
	@NamedQuery(name = "PracticeRecord.FIND_ALL", query = "select p from PracticeRecord p")
})
public class PracticeRecord {

	@EmbeddedId
	private PracticeRecordId practiceRecordId;
	@Embedded @AttributeOverride(name="id", column=@Column(name = "planId"))
	private PracticePlanId practicePlanId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	public PracticeRecord(){
		super();
	}
	
	public PracticeRecord(PracticeRecordRepresentation aRecord) {
		Date date = new Date();
		this.practiceRecordId = new PracticeRecordId(aRecord.getId());
		this.practicePlanId = new PracticePlanId(aRecord.getPlanId());
		this.createDate = date;
	}

	public PracticeRecordId getPracticeRecordId() {
		return practiceRecordId;
	}
	
	public PracticePlanId getPracticePlanId() {
		return practicePlanId;
	}

	public void setPracticeRecordId(PracticeRecordId aPracticeRecordId) {
		this.practiceRecordId = aPracticeRecordId;
	}
}
