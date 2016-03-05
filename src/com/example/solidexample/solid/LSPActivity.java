package com.example.solidexample.solid;

import com.example.solidexample.R;
import com.example.solidexample.R.layout;

import android.app.Activity;
import android.os.Bundle;

// https://ko.wikipedia.org/wiki/SOLID
// http://code.tutsplus.com/tutorials/solid-part-3-liskov-substitution-interface-segregation-principles--net-36710

// FUNCTIONS THAT USE ... REFERENCES TO BASE CLASSES MUST BE ABLE TO USE OBJECTS OF DERIVED CLASSES WITHOUT KNOWING IT.

public class LSPActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lsp);
	}

	public abstract class Soldider {
		double healthPoint;
		double pride;

		void eat() {
			healthPoint++;
		}

		void sleep() {
			healthPoint++;
		}

		void work() {
			healthPoint--;
			healthPoint--;
		}

		// Do not do this!
		// void shoutToOthers() {
		// if (this instanceof Private) {
		// // Do nothing
		// } else if (this instanceof Sergeant) {
		// ((Sergeant) this).shoutToOthers();
		// }
		// }
	}

	public class Private extends Soldider {
		// eat, sleep, work
	}

	public class Sergeant extends Soldider {
		void shoutToOthers() {
			pride++;
		}
	}

	public void doSomething() {
		Soldider someone1 = new Private();
		Soldider someone2 = new Sergeant();

		// ////////////////////////////////
		// Good case
		// ////////////////////////////////

		someone1.eat();
		someone1.sleep();
		someone1.work();

		someone2.eat();
		someone2.sleep();
		someone2.work();
		// someone2.shoutToOthers(); // Error. A soldier cannot shout to others.

		// ////////////////////////////////
		// Bad case
		// ////////////////////////////////

		((Sergeant) someone2).shoutToOthers(); // Bad case. If we want to make a soldier shout to others, we must know he is a sergeant before we order.

	}

}
