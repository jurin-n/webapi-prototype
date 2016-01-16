package com.jurin_n.domain.model;

// 実験中：Idクラスのスーパークラス検討のため試しに実装。
public abstract class BaseIdEntity {
	
	protected abstract boolean isEquals(Object anObject);
	protected abstract int addHashCodeValue(int aResult);
	
	@Override
	public boolean equals(Object anObject){
		
		if(anObject == this) return true; //等値なので等価
		if(anObject == null) return false;
		
		if(this.getClass() == anObject.getClass()){
			return isEquals(anObject);
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		//適当な初期値
		int result = 37;

		//resultに各フィールドの影響を加える
		result = addHashCodeValue(result);
		
		return result;
	}
}
