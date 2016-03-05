package com.example.solidexample.solid;

import android.app.Activity;
import android.os.Bundle;

import com.example.solidexample.R;

// https://ko.wikipedia.org/wiki/SOLID
// http://www.nextree.co.kr/p6960/
// https://lostechies.com/wp-content/uploads/2011/03/pablos_solid_ebook.pdf
// http://www.oodesign.com/single-responsibility-principle.html
// "A class should have only one reason to change."

public class SRPActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_srp);

		doSomething();
	}

	// /////////////////////////////////////////
	// Bad case
	// A soldier has multiple job.
	// If one of the job need to changed, the soldier will be affected.
	// /////////////////////////////////////////

	public class BadSoldider {
		double healthPoint;

		void doCorporationWork() {
			healthPoint--;
		}

		void doMillitaryWork() {
			healthPoint--;
			healthPoint--; // It is much more dirty job.
		}

	}

	// /////////////////////////////////////////
	// Good case
	// A Good Soldier has only one job.
	// If the Sergeant job getting harder, it will does not affect to Tech guy.
	// /////////////////////////////////////////

	public abstract class GoodSoldier {
		double healthPoint;

		void doWork() {
			healthPoint--;
		}
	}

	public class Sergeant extends GoodSoldier {

		void doWork() {
			super.doWork();
			healthPoint--;// It is much more dirty job.

		}
	}

	public class TechnicalResearchPersonnel extends GoodSoldier {

		void doWork() {
			super.doWork();
		}
	}

	// /////////////////////////////////////////
	// /////////////////////////////////////////

	public void doSomething() {

		BadSoldider soldier1 = new BadSoldider();
		BadSoldider techResearch1 = new BadSoldider();

		Sergeant soilder2 = new Sergeant();
		TechnicalResearchPersonnel techResearch2 = new TechnicalResearchPersonnel();

		soldier1.doMillitaryWork();
		techResearch1.doCorporationWork();

		soilder2.doWork();
		techResearch2.doWork();

	}
}
