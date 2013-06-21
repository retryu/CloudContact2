package com.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.db.OrmDateBaseHelper;

public class CommonActivity extends  SherlockFragmentActivity  {

	private  OrmDateBaseHelper ormDateBaseHelper ; 
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ormDateBaseHelper=new OrmDateBaseHelper(this,
				"cloud_contact", null, 1);
	}  
  
	public OrmDateBaseHelper getOrmDateBaseHelper() {
		return ormDateBaseHelper;
	}

	public void setOrmDateBaseHelper(OrmDateBaseHelper ormDateBaseHelper) {
		this.ormDateBaseHelper = ormDateBaseHelper;
	}
	
	


}
