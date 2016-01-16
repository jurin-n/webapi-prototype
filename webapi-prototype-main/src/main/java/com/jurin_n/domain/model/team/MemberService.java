package com.jurin_n.domain.model.team;

import java.util.Collection;

public class MemberService {
	
	/*
	 * Soccurチームメンバーに必要な人員構成をチェック。
	 * */
	public void checkMembersForSoccer(Collection<Member> aMembers){
		//MemberにはFW,MF,DF合わせて10人以上いる。
		checkPossitionsForSoccer(aMembers);
		
		//MemberにはFW,MF,DF合わせて10人以上いる。
		checkSumOf_FW_MF_DF_ForSoccer(aMembers);
		
		//MemberにはGKが1人以上いる。
		// ＊「MemberにはFW,MF,DF,GK,CTがいる。」でチェック済みのためロジックなし。
		
		//MemberにはCTが1人以上いる。
		// ＊「MemberにはFW,MF,DF,GK,CTがいる。」でチェック済みのためロジックなし。
	}
	
	/*
	 * 「MemberにはFW,MF,DF,GK,CTがいる。」ことをチェック。
	 * */
	protected void checkPossitionsForSoccer(Collection<Member> aMembers){
		
        boolean result = false;
		boolean isFW = false;
		boolean isMF = false;
		boolean isDF = false;
		boolean isGK = false;
		boolean isCT = false;
		for(Member aMember : aMembers){
			if(aMember.getRole() == Role.FW) isFW = true;
			if(aMember.getRole() == Role.MF) isMF = true;
			if(aMember.getRole() == Role.DF) isDF = true;
			if(aMember.getRole() == Role.GK) isGK = true;
			if(aMember.getRole() == Role.CT) isCT = true;
			
			if(isFW && isMF && isDF && isGK && isCT){
				//すべてのポジション存在したので検査終了。
				result = true;
				break;
			}
		}
		if(result == false){
			throw new IllegalStateException("MemberにはFW,MF,DF,GK,CTがすべて含まれてません。");
		}
	}
	
	/*
	 * 「MemberにはFW,MF,DF合わせて10人以上いる。」ことをチェック。
	 * */
	protected void checkSumOf_FW_MF_DF_ForSoccer(Collection<Member> aMembers){
		boolean result = false;
		int countFW = 0;
		int countMF = 0;
		int countDF = 0;
		for(Member aMember : aMembers){
			if(aMember.getRole() == Role.FW) ++countFW;
			if(aMember.getRole() == Role.MF) ++countMF;
			if(aMember.getRole() == Role.DF) ++countDF;
			
			if(countFW + countMF + countDF >= 10){
				//10人以上なので検査終了。
				result = true;
				break;
			}
		}
		if(result == false){
			throw new IllegalStateException("MemberにはFW,MF,DF合わせて10人以上存在しません。");
		}
	}
}
