package com.potmo.screamer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class StartActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);

    final String myPackageName = getPackageName();
    if (!Telephony.Sms.getDefaultSmsPackage(this).equals(myPackageName)) {

      Log.d("HELLo", "Not my package");
      // App is not default.
      Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
      intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, myPackageName);
      startActivity(intent);
    }
    else {
      Log.d("HELLo", "I am it");
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_start, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
