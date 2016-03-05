package com.example.solidexample;

import com.example.solidexample.solid.DIPActivity;
import com.example.solidexample.solid.ISPActivity;
import com.example.solidexample.solid.LSPActivity;
import com.example.solidexample.solid.OCPActivity;
import com.example.solidexample.solid.SRPActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

// https://ko.wikipedia.org/wiki/SOLID
// https://lostechies.com/wp-content/uploads/2011/03/pablos_solid_ebook.pdf
public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String[] testValues = new String[] {//
		"SRP: Single responsibility", //
				"OCP: Open/closed", //
				"LSP: Liskov substitution",//
				"ISP: Interface segregation",//
				"DIP: Dependency inversion",//
		};
		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testValues);

		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(this, SRPActivity.class);
			break;

		case 1:
			intent = new Intent(this, OCPActivity.class);
			break;

		case 2:
			intent = new Intent(this, LSPActivity.class);
			break;

		case 3:
			intent = new Intent(this, ISPActivity.class);
			break;

		case 4:
			intent = new Intent(this, DIPActivity.class);
			break;

		default:
			intent = new Intent(this, SRPActivity.class);
			break;

		}

		startActivity(intent);
	}

}
