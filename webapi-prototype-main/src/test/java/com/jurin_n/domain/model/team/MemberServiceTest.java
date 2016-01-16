package com.jurin_n.domain.model.team;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class MemberServiceTest {
	/*
	 * checkPossitionsForSoccerメソッドのテスト
	 * */
	@Test
	public void testFWとMFとDFとGKとCTがいるのでチェック成功() {
		Collection<Member> members = new ArrayList<>();
		members.add(new Member("FW1",Role.FW));
		members.add(new Member("MF1",Role.MF));
		members.add(new Member("DF1",Role.DF));
		members.add(new Member("GK1",Role.GK));
		members.add(new Member("CT1",Role.CT));
		
		MemberService ms = new MemberService();
		ms.checkPossitionsForSoccer(members);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testFWいないのでエラー() {
		Collection<Member> members = new ArrayList<>();
		//members.add(new Member("FW1",Role.FW));
		members.add(new Member("MF1",Role.MF));
		members.add(new Member("DF1",Role.DF));
		members.add(new Member("GK1",Role.GK));
		members.add(new Member("CT1",Role.CT));
		MemberService ms = new MemberService();
		ms.checkPossitionsForSoccer(members);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testMFいないのでエラー() {
		Collection<Member> members = new ArrayList<>();
		members.add(new Member("FW1",Role.FW));
		//members.add(new Member("MF1",Role.MF));
		members.add(new Member("DF1",Role.DF));
		members.add(new Member("GK1",Role.GK));
		members.add(new Member("CT1",Role.CT));
		MemberService ms = new MemberService();
		ms.checkPossitionsForSoccer(members);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testDFいないのでエラー() {
		Collection<Member> members = new ArrayList<>();
		members.add(new Member("FW1",Role.FW));
		members.add(new Member("MF1",Role.MF));
		//members.add(new Member("DF1",Role.DF));
		members.add(new Member("GK1",Role.GK));
		members.add(new Member("CT1",Role.CT));
		MemberService ms = new MemberService();
		ms.checkPossitionsForSoccer(members);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testGKいないのでエラー() {
		Collection<Member> members = new ArrayList<>();
		members.add(new Member("FW1",Role.FW));
		members.add(new Member("MF1",Role.MF));
		members.add(new Member("DF1",Role.DF));
		//members.add(new Member("GK1",Role.GK));
		members.add(new Member("CT1",Role.CT));
		MemberService ms = new MemberService();
		ms.checkPossitionsForSoccer(members);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testCTいないのでエラー() {
		Collection<Member> members = new ArrayList<>();
		members.add(new Member("FW1",Role.FW));
		members.add(new Member("MF1",Role.MF));
		members.add(new Member("DF1",Role.DF));
		members.add(new Member("GK1",Role.GK));
		//members.add(new Member("CT1",Role.CT));
		MemberService ms = new MemberService();
		ms.checkPossitionsForSoccer(members);
	}
	
	/*
	 * checkSumOf_FW_MF_DF_ForSoccerメソッドのテスト
	 * */
	@Test
	public void testMemberにはFW_MF_DF合わせて10人以上いるので成功() {
		Collection<Member> members = new ArrayList<>();
		members.add(new Member("FW1",Role.FW));
		members.add(new Member("FW2",Role.FW));
		members.add(new Member("MF1",Role.MF));
		members.add(new Member("MF2",Role.MF));
		members.add(new Member("MF3",Role.MF));
		members.add(new Member("MF4",Role.MF));
		members.add(new Member("MF5",Role.MF));
		members.add(new Member("DF1",Role.DF));
		members.add(new Member("DF2",Role.DF));
		members.add(new Member("DF3",Role.DF));
		members.add(new Member("GK1",Role.GK));
		members.add(new Member("CT1",Role.CT));
		
		MemberService ms = new MemberService();
		ms.checkSumOf_FW_MF_DF_ForSoccer(members);
	}
	
	/*
	 * checkSumOf_FW_MF_DF_ForSoccerメソッドのテスト
	 * */
	@Test(expected = IllegalStateException.class)
	public void testMemberにはFW_MF_DF合わせて10人以上いないのでエラー() {
		Collection<Member> members = new ArrayList<>();
		members.add(new Member("FW1",Role.FW));
		members.add(new Member("FW2",Role.FW));
		members.add(new Member("MF1",Role.MF));
		members.add(new Member("MF2",Role.MF));
		members.add(new Member("MF3",Role.MF));
		members.add(new Member("MF4",Role.MF));
		members.add(new Member("MF5",Role.MF));
		members.add(new Member("DF1",Role.DF));
		members.add(new Member("DF2",Role.DF));
		//members.add(new Member("DF3",Role.DF));
		members.add(new Member("GK1",Role.GK));
		members.add(new Member("CT1",Role.CT));
		
		MemberService ms = new MemberService();
		ms.checkSumOf_FW_MF_DF_ForSoccer(members);
	}
}
