package com.jurin_n.domain.model.practice.record;

import java.util.List;

public interface PracticeRecordRepository {
	public void add(PracticeRecord aPracticePlan);
	public List<PracticeRecord> getAll();
	public PracticeRecordId nextIdentity();
	public PracticeRecord getRecordById(PracticeRecordId practicePlanId);
}
