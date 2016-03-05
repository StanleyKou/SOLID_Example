package com.example.solidexample.solid;

import android.app.Activity;
import android.os.Bundle;

import com.example.solidexample.R;

// https://ko.wikipedia.org/wiki/SOLID
// http://code.tutsplus.com/tutorials/solid-part-4-the-dependency-inversion-principle--net-36872
// http://programmers.stackexchange.com/questions/220765/open-close-principle-ocp-vs-dependency-inversion-principle-dip
// https://en.wikipedia.org/wiki/Military_organization

// A. HIGH LEVEL MODULES SHOULD NOT DEPEND UPON LOW LEVEL MODULES.
// BOTH SHOULD DEPEND UPON ABSTRACTIONS
// B. ABSTRACTIONS SHOULD NOT DEPEND UPON DETAILS. DETAILS SHOULD
// DEPEND UPON ABSTRACTIONS

public class DIPActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dip);
	}

	// ////////////////////////
	// Bad case
	// ////////////////////////

	class BadPrivate {
	}

	class BadPrivateFirstClass {
	}

	class BadCompany {
		BadPrivate soldier1;
		BadPrivateFirstClass soldier2;
	}

	class BadBattallion {
		BadCompany company1;
	}

	class BadTaskForce {
		BadPrivate one;
		BadPrivateFirstClass two;
		BadBattallion three;
		BadCompany four;
	}

	// ////////////////////////
	// Good case
	// ////////////////////////

	interface ISoldier {
	}

	class GoodPrivate implements ISoldier {
	}

	class GoodPrivateFirstClass implements ISoldier {
	}

	interface IOrganization {
	};

	class GoodCompany implements IOrganization {
		ISoldier soldier1;
		ISoldier soldier2;
	}

	class GoodBattallion implements IOrganization {
		IOrganization company1;
		IOrganization company2;
		IOrganization fiveMinuteReadyForce;

	}

	class GoodSpecialForce {
		IOrganization tempOrgan;
		IOrganization tempOrgan2;
		IOrganization tempOrgan3;

		ISoldier tempSoldier1;
		ISoldier tempSoldier2;
		ISoldier tempSoldier3;
	}
}

// 사단: division
// 연대: regiment
// 대대: battalion
// 중대: company
// 소대: platoon
// 분대: squad

// Symbol Name Nature Strength Constituent units Commander or leader
// XXXXXX region, theater Command 1,000,000–10,000,000 4+ army groups general, army general, five-star general or field marshal
// XXXXX army group, front Command 400,000–1,000,000 2+ armies general, army general, five-star general or field marshal
// XXXX army Command 100,000–200,000 2–4 corps general, army general, four-star general or colonel general
// XXX corps Formation 40,000–80,000 2+ divisions lieutenant general, corps general or three-star general
// XX division Formation 10,000–25,000 2–4 brigades or regiments major general, divisional general or two-star general
// X brigade Formation 3,000–5,000 2+ regiments, 3–6 battalions or Commonwealth regiments brigadier, brigadier general, brigade general, or one-star general (sometimes colonel)
// III regiment or group Formation 1,000–3,000 2+ battalions or U.S. Cavalry squadrons colonel
// II infantry battalion, U.S. Cavalry squadron, Commonwealth armoured regiment or Argentine Army regiment/artillery group/battalion Unit 300–1,300 2–6 companies, batteries, U.S. Cavalry troops, or Commonwealth squadrons, Argentine squadrons/companies lieutenant colonel
// I infantry company, artillery battery, U.S. Cavalry troop, or Commonwealth armour or combat engineering squadron Subunit 80–250 2–8 platoons or Commonwealth troops chief warrant officer, captain or major
// ••• platoon or Commonwealth troop Sub-subunit 26–55 2+ Section, or vehicles warrant officer, first or second lieutenant
// •• section or patrol - 12–24 1-2+ squad or 3-6 fireteams corporal to sergeant
// • squad or crew - 8–12 2-3 fireteams or 1+ cell corporal to staff sergeant
// Ø fireteam - 4 n/a lance corporal to sergeant
// Ø fire and maneuver team - 2 n/a any/private first class
