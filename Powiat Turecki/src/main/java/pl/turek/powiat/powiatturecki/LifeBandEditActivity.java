package pl.turek.powiat.powiatturecki;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class LifeBandEditActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_content);
        stub.setLayoutResource(R.layout.life_band_edit);
        View inflated = stub.inflate();
        Intent intent = getIntent();

        String[] alergies = {"Apple", "Apple 2", "Banana", "Cherry", "Date", "Grape", "Kiwi", "Mango", "Pear"};
        AutoCompletMultiSelect alergies_acms = (AutoCompletMultiSelect) findViewById(R.id.life_band_edit_alergies);
        alergies_acms.setTitle("Alergie");
        alergies_acms.setItems(alergies);

        loading_done();
    }
}