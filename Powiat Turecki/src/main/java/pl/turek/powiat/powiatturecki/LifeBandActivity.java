package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

public class LifeBandActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_content);
        stub.setLayoutResource(R.layout.life_band);
        View inflated = stub.inflate();
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);

        //try {
        //    ndef.addDataType("*/*");
        //} catch (IntentFilter.MalformedMimeTypeException e) {
        //    throw new RuntimeException("fail", e);
        //}
        //mFilters = new IntentFilter[] {
        //        ndef,
        //};

        // Setup a tech list for all NfcF tags
        //mTechLists = new String[][] { new String[] { MifareClassic.class.getName() } };

        Intent intent = getIntent();
        loading_done();
    }
}
