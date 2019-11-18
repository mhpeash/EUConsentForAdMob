package pp.mohibul.consentdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;

import java.net.MalformedURLException;
import java.net.URL;

import static pp.mohibul.consentdialog.MainActivity.PRIVACY_URL;
import static pp.mohibul.consentdialog.MainActivity.admov;


public class Main2Activity extends AppCompatActivity {

    public boolean mShowNonPersonalizedAdRequests = false;
    private ConsentForm consentForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    }

//    public  void  StartAdRequest(){
//        Bundle extras = new Bundle();
//        if (mShowNonPersonalizedAdRequests)
//            extras.putString("npa", "1");
//}

//AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("YOUR-DEVICE-ID-GOES-HERE") // insert your device id
//                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
//                .build();

