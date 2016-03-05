package com.example.solidexample.solid;

import android.app.Activity;
import android.os.Bundle;

import com.example.solidexample.R;

// https://ko.wikipedia.org/wiki/SOLID

// CLIENTS SHOULD NOT BE FORCED TO DEPEND UPON INTERFACES THAT THEY DO NOT USE
public class ISPActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_isp);
	}

	// ////////////////////////////////
	// Bad case
	// ////////////////////////////////
	
	public interface IBadSoldier {
		// for Private
		void doEat();
		void doSleep();
		void doWork();
		void doBreath();

		// for Private first class
		void makeACallAlone();
		void imagineDischarge();
		void teachPrivate();

		// for Specialist
		void teachPFC();

		// for Sergeant
		void teachSpecialist();

		// for Officer. If soldier do this, all company will be **cked up.
		void doHandsInPocket();
	}

	public class BadSoldider implements IBadSoldier {
		@Override	public void doEat() {}
		@Override	public void doSleep() {}
		@Override	public void doWork() {}
		@Override	public void doBreath() {}
		@Override	public void makeACallAlone() {}
		@Override	public void imagineDischarge() {}
		@Override	public void teachPrivate() {}
		@Override	public void teachPFC() {}
		@Override	public void teachSpecialist() {}
		@Override	public void doHandsInPocket() {}
	}
	
	
	// ////////////////////////////////
	// Good case
	// ////////////////////////////////
	
	public interface IPrivate {
		void doEat();
		void doSleep();
		void doWork();
		void doBreath();
	}
	
	public interface IPFC {
		void makeACallAlone();
		void imagineDischarge();
		void teachPrivate();
	}
	
	public interface ISpecialist {
		void teachPFC();
	}
	
	public interface ISergeant {
		void teachSpecialist();
	}
	
	public interface IOfficer {
		void doHandsInPocket();
	}
	
	public class GoodPrivate implements IPrivate {
		@Override	public void doEat() {}
		@Override	public void doSleep() {}
		@Override	public void doWork() {}
		@Override	public void doBreath() {}
	}
	
	public class GoodPFC implements IPrivate, IPFC {
		@Override	public void doEat() {}
		@Override	public void doSleep() {}
		@Override	public void doWork() {}
		@Override	public void doBreath() {}

		@Override	public void makeACallAlone() {}
		@Override	public void imagineDischarge() {}
		@Override	public void teachPrivate() {}
	}
	
	public void doSomething() {
		BadSoldider someone1 = new BadSoldider();
		// Now, do something proper for private first class.
		// If you failes, all company will be **ed up.
		
		
		GoodPrivate someone2 = new GoodPrivate();
		// GoodPrivate only can do something approved for private.
		
		GoodPFC someone3 = new GoodPFC();
		// GoodPFC cannot put his hands in pocket, evne if he want.
	}
}
