package com.jurin_n.domain.model.practice.member;

import java.util.List;

public interface PracticeMemberRepository {
	public void add(PracticeMember aMember);
	public void update(PracticeMember aMember);
	public void remove(PracticeMember aMember);
	public PracticeMemberId nextIdentity();
	public PracticeMember getMemberById(PracticeMemberId memberId);
	public List<PracticeMember> getPracticeMemberAll();
}
