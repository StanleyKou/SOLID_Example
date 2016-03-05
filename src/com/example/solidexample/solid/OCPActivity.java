package com.example.solidexample.solid;

import android.app.Activity;
import android.os.Bundle;

import com.example.solidexample.R;

// https://ko.wikipedia.org/wiki/SOLID
// http://code.tutsplus.com/tutorials/solid-part-2-the-openclosed-principle--net-36600

// SOFTWARE ENTITIES (CLASSES, MODULES, FUNCTIONS, ETC.) SHOULD BE OPEN FOR EXTENSION BUT CLOSED FOR MODIFICATION. 

public class OCPActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ocp);
	}

	// /////////////////////////////////////////
	// Bad case
	// A soldier has multiple job.
	// If one of the job need to changed, the soldier will be affected.
	// /////////////////////////////////////////

	public class BadSoldider {
		double healthPoint;
		double balance;

		void doCorporationWork() {
			healthPoint--;
		}

		void doMillitaryWork() {
			healthPoint--;
			healthPoint--; // It is much more dirty job.
		}

		void addPayment(double amount) {
			balance += amount * 0.9; // 10% tax
		}

		double withdrawal(double amount) {
			balance = balance - amount - (amount * 0.01); // 1% Commission
			return amount;
		}

	}

	// Q: What if tax rate changed only for soldier?
	// Q: What if bank commission changed?

	// /////////////////////////////////////////
	// Good case
	// A Good Soldier has only one job.
	// He has a bank account pocket for a bank book. So he doesn't need to write balance to his own palm.
	// /////////////////////////////////////////

	public abstract class GoodSoldier {
		double healthPoint;
		IBankAccount account;

		void doWork() {
			healthPoint--;
		}

		void setAccount(IBankAccount account) {
			this.account = account;
		}

		void addPayment(double amount) {
			account.addPayment(amount);
		}

		double withdrawal(double amount) {
			return account.withdrawal(amount);
		}

	}

	public interface IBankAccount {
		void addPayment(double amount);

		double withdrawal(double amount);
	}

	public class Sergeant extends GoodSoldier {

		void doWork() {
			super.doWork();
			healthPoint--;// It is much more dirty job.

		}

		public Sergeant() {

			IBankAccount soldierAccount = new IBankAccount() {
				double balance;

				@Override
				public void addPayment(double amount) {
					balance += amount * 0.9; // 10% tax
				}

				public double withdrawal(double amount) {
					balance = balance - amount; // no Commission
					return amount;
				}
			};

			setAccount(soldierAccount);
		}

	}

	public class TechnicalResearchPersonnel extends GoodSoldier {

		void doWork() {
			super.doWork();
		}

		public TechnicalResearchPersonnel() {
			IBankAccount civillianAccount = new IBankAccount() {
				double balance;

				@Override
				public void addPayment(double amount) {
					balance += amount * 0.8; // 20% tax
				}

				public double withdrawal(double amount) {
					balance = balance - amount - (amount * 0.01); // 1% Commission
					return amount;
				}
			};

			setAccount(civillianAccount);
		}

	}

	// /////////////////////////////////////////
	// /////////////////////////////////////////

	public void doSomething() {

		BadSoldider soldier1 = new BadSoldider();
		BadSoldider techResearch1 = new BadSoldider();

		Sergeant soilder2 = new Sergeant();
		TechnicalResearchPersonnel techResearch2 = new TechnicalResearchPersonnel();

		soldier1.addPayment(1000);
		techResearch1.addPayment(1000);

		soilder2.addPayment(1000);
		techResearch2.addPayment(1000);

	}

}
