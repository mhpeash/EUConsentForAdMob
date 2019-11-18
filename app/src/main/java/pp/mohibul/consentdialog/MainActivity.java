package pp.mohibul.consentdialog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.consent.AdProvider;
import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.ads.consent.DebugGeography;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_PHONE_STATE;
import static java.lang.Math.log;

public class MainActivity extends AppCompatActivity {

    // public static String admov="ca-app-pub-3940256099942544/6300978111";
    public static String admov = "pub-7100389293873601";

    public static final String PRIVACY_URL = "https://technoven.de/lid/privacy.html";
    public boolean mShowNonPersonalizedAdRequests = false;
    private AlertDialog mEuDialog;

    //-----------------------------------------
    ConsentForm form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RunConsent();
    }

    public void RunConsent() {

        ConsentInformation consentInformation = ConsentInformation.getInstance(this);
        ConsentInformation.getInstance(this).addTestDevice(GetDeveiceID());
        String[] publisherIds = {admov};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                Log.d("Consent", "User is not from EU ");
                if (ConsentInformation.getInstance(MainActivity.this).isRequestLocationInEeaOrUnknown()) {
                    if (consentStatus == ConsentStatus.UNKNOWN) {
                    }
                    // If the returned ConsentStatus is PERSONALIZED or NON_PERSONALIZED
                    // the user has already provided consent. Forward consent to the Google Mobile Ads SDK.
                    else if (consentStatus == ConsentStatus.NON_PERSONALIZED) {
                        mShowNonPersonalizedAdRequests = true;
                        // The default behavior of the Google Mobile Ads SDK is to serve personalized ads.
                        // If a user has consented to receive only non-personalized ads, you can configure
                        // an AdRequest object with the following code to specify that only non-personalized
                        // ads should be returned.
                    }

                } else {
                    Log.d("Consent", "User is not from EU ");
                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
            }
        });

        URL privacyUrl = null;
        try {
            privacyUrl = new URL(PRIVACY_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Handle error.
        }
        form = new ConsentForm.Builder(this, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        // Consent form loaded successfully.
                         form.show();
                    }

                    @Override
                    public void onConsentFormOpened() {
                        // Consent form was displayed.
                    }

                    @Override
                    public void onConsentFormClosed(
                            ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        // Consent form was closed.
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        // Consent form error.
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                .withAdFreeOption()
                .build();
        form.load();

        // Geography appears as in EEA for test devices.
        ConsentInformation.getInstance(this).setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_EEA);
// Geography appears as not in EEA for debug devices.
        ConsentInformation.getInstance(this).setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_NOT_EEA);
    }


    //---------------------------------------------------------------------------------------------------------
    // https://developers.google.com/admob/android/eu-consent
//    private void checkConsentStatus() {
//        ConsentInformation consentInformation = ConsentInformation.getInstance(this);
//        ConsentInformation.getInstance(this).addTestDevice(GetDeveiceID()); // enter your device id, if you need it for testing
//        String[] publisherIds = {admov}; // enter your admob pub-id
//        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
//            @Override
//            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
//                toast("User's consent status successfully updated: " + consentStatus, MainActivity.this);
//                if (ConsentInformation.getInstance(MainActivity.this).isRequestLocationInEeaOrUnknown()) {
//                    toast("User is from EU", MainActivity.this);
//                    /////////////////////////////
//                    // TESTING - reset the choice
//                    //  ConsentInformation.getInstance(MainActivity.this).setConsentStatus(ConsentStatus.UNKNOWN);
//                    /////////////////////////////
//                    // If the returned ConsentStatus is UNKNOWN, collect user's consent.
//                    if (consentStatus == ConsentStatus.UNKNOWN) {
//                        showMyConsentDialog(false);
//                    }
//                    // If the returned ConsentStatus is PERSONALIZED or NON_PERSONALIZED
//                    // the user has already provided consent. Forward consent to the Google Mobile Ads SDK.
//                    else if (consentStatus == ConsentStatus.NON_PERSONALIZED) {
//                        mShowNonPersonalizedAdRequests = true;
//                        // The default behavior of the Google Mobile Ads SDK is to serve personalized ads.
//                        // If a user has consented to receive only non-personalized ads, you can configure
//                        // an AdRequest object with the following code to specify that only non-personalized
//                        // ads should be returned.
//                    }
//
//                } else {
//                    toast("User is NOT from EU", MainActivity.this);
//                    // we don't have to do anything
//                }
//            }
//
//            @Override
//            public void onFailedToUpdateConsentInfo(String errorDescription) {
//                toast("User's consent status failed to update: " + errorDescription, MainActivity.this);
//                Log.e("ERROR", errorDescription);
//            }
//        });
//    }

//    public void showMyConsentDialog(boolean showCancel) {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogStyle);
//        LayoutInflater inflater = getLayoutInflater();
//        View eu_consent_dialog = inflater.inflate(R.layout.eu_consent, null);
//        alertDialog.setView(eu_consent_dialog).setCancelable(false);
//        if (showCancel) alertDialog.setPositiveButton(R.string.dialog_close, null);
//        mEuDialog = alertDialog.create();
//        mEuDialog.show();
//        Button btn_eu_consent_yes = eu_consent_dialog.findViewById(R.id.btn_eu_consent_yes);
//        Button btn_eu_consent_no = eu_consent_dialog.findViewById(R.id.btn_eu_consent_no);
//        Button btn_eu_consent_remove_ads = eu_consent_dialog.findViewById(R.id.btn_eu_consent_remove_ads);
//        btn_eu_consent_yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEuDialog.cancel();
//                toast(getString(R.string.thank_you), MainActivity.this);
//                ConsentInformation.getInstance(MainActivity.this).setConsentStatus(ConsentStatus.PERSONALIZED);
//                mShowNonPersonalizedAdRequests = false;
//            }
//        });
//        btn_eu_consent_no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEuDialog.cancel();
//                toast(getString(R.string.thank_you), MainActivity.this);
//                ConsentInformation.getInstance(MainActivity.this).setConsentStatus(ConsentStatus.NON_PERSONALIZED);
//                mShowNonPersonalizedAdRequests = true;
//            }
//        });
//        btn_eu_consent_remove_ads.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEuDialog.cancel();
//                // IAP_buyAdsFree(); // YOUR REMOVE ADS METHOD
//            }
//        });
//
//        TextView tv_eu_learn_more = eu_consent_dialog.findViewById(R.id.tv_eu_learn_more);
//        tv_eu_learn_more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                euMoreInfoDialog();
//            }
//        });
//    }
//
//    private void toast(String string, MainActivity mainActivity) {
//
//        Toast.makeText(mainActivity, string, Toast.LENGTH_LONG).show();
//    }
//
//    private void euMoreInfoDialog() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogStyle);
//
//        ScrollView sv = new ScrollView(this);
//        LinearLayout ll = new LinearLayout(this);
//        ll.setOrientation(LinearLayout.VERTICAL);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(40, 20, 40, 20);
//
//        TextView tv_my_privacy_policy = new TextView(this);
//        String link = "<a href=" + PRIVACY_URL + ">" + getResources().getString(R.string.app_name) + "</a>";
//        tv_my_privacy_policy.setText(Html.fromHtml(link));
//        tv_my_privacy_policy.setMovementMethod(LinkMovementMethod.getInstance());
//        ll.addView(tv_my_privacy_policy, params);
//
//        TextView tv_google_partners = new TextView(this);
//        tv_google_partners.setText(R.string.google_partners);
//        tv_google_partners.setPadding(40, 40, 40, 20);
//        ll.addView(tv_google_partners);
//
//        List<AdProvider> adProviders = ConsentInformation.getInstance(this).getAdProviders();
//        for (AdProvider adProvider : adProviders) {
//            //log("adProvider: " +adProvider.getName()+ " " +adProvider.getPrivacyPolicyUrlString());
//            link = "<a href=" + adProvider.getPrivacyPolicyUrlString() + ">" + adProvider.getName() + "</a>";
//            TextView tv_adprovider = new TextView(this);
//            tv_adprovider.setText(Html.fromHtml(link));
//            tv_adprovider.setMovementMethod(LinkMovementMethod.getInstance());
//            ll.addView(tv_adprovider, params);
//        }
//        sv.addView(ll);
//
//        builder.setTitle(R.string.privacy_policy)
//                .setView(sv)
//                .setPositiveButton(R.string.dialog_close, null);
//
//        final AlertDialog createDialog = builder.create();
//        createDialog.show();
//    }

    public void GotoSecondPage(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(new Intent(this, Main2Activity.class));
        }
    }

    protected String GetDeveiceID() {

        TelephonyManager tManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 100);
            }
        }
        String uid = tManager.getDeviceId();
        return uid;
    }
}
