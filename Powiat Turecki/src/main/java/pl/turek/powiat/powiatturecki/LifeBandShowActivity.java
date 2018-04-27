package pl.turek.powiat.powiatturecki;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

public class LifeBandShowActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_content);
        stub.setLayoutResource(R.layout.life_band_show);
        View inflated = stub.inflate();
        loading_done();
    }


}