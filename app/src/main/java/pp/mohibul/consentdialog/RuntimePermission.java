//package pp.mohibul.consentdialog;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.os.Build;
//
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
//import static android.Manifest.permission.ACCESS_NETWORK_STATE;
//import static android.Manifest.permission.ACCESS_WIFI_STATE;
//import static android.Manifest.permission.CAMERA;
//import static android.Manifest.permission.CHANGE_WIFI_STATE;
//import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
//import static android.Manifest.permission.READ_PHONE_STATE;
//import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
//
//public class RuntimePermission {
//
//    //   Multiple permission checking
//    private void RequestMultiplePermission() {
//
//        // Creating String Array with Permissions.
//        ActivityCompat.requestPermissions(SplashActivity.this, new String[]
//                {
//                        CHANGE_WIFI_STATE,
//                        CAMERA,
//                        READ_PHONE_STATE,
//                        ACCESS_WIFI_STATE,
//                        ACCESS_NETWORK_STATE,
//                        READ_EXTERNAL_STORAGE,
//                        WRITE_EXTERNAL_STORAGE
//                }, RequestPermissionCode);
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//
//            case RequestPermissionCode:
//
//                if (grantResults.length > 0) {
//
//                    boolean CHANGE_WIFI_STATE_Permission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean CAMERA_Permission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                    boolean READ_PHONE_STATE_Permission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
//                    boolean ACCESS_WIFI_STATE_Permission = grantResults[3] == PackageManager.PERMISSION_GRANTED;
//                    boolean ACCESS_NETWORK_STATE_Permission = grantResults[4] == PackageManager.PERMISSION_GRANTED;
//                    boolean READ_EXTERNAL_STORAGE_Permission = grantResults[5] == PackageManager.PERMISSION_GRANTED;
//                    boolean WRITE_EXTERNAL_STORAGE_Permission = grantResults[6] == PackageManager.PERMISSION_GRANTED;
//
//                    if (CHANGE_WIFI_STATE_Permission && CAMERA_Permission && READ_PHONE_STATE_Permission
//                            && ACCESS_WIFI_STATE_Permission && ACCESS_NETWORK_STATE_Permission && READ_EXTERNAL_STORAGE_Permission
//                            && WRITE_EXTERNAL_STORAGE_Permission) {
//                        Toast.makeText(SplashActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(SplashActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
//                    }
//                }
//                break;
//        }
//    }
//
//    // Checking permission is enabled or not using function starts from here.
//    public boolean CheckingPermissionIsEnabledOrNot(Context context) {
//
//        int FirstPermissionResult = ContextCompat.checkSelfPermission(context, READ_PHONE_STATE);
//        int SecondPermissionResult = ContextCompat.checkSelfPermission(context, ACCESS_WIFI_STATE);
//        int ThirdPermissionResult = ContextCompat.checkSelfPermission(context, CHANGE_WIFI_STATE);
//        int ForthPermissionResult = ContextCompat.checkSelfPermission(context, CAMERA);
//        int FifthPermissionResult = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
//        int SixthPermissionResult = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
//        int SeventhPermissionResult = ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION);
//
//
//        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
//                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
//                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
//                ForthPermissionResult == PackageManager.PERMISSION_GRANTED &&
//                FifthPermissionResult == PackageManager.PERMISSION_GRANTED &&
//                SixthPermissionResult == PackageManager.PERMISSION_GRANTED &&
//                SeventhPermissionResult == PackageManager.PERMISSION_GRANTED;
//    }
//    public void RequestPermission() {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            requestPermissions(new String[]{
//
//                            READ_PHONE_STATE,
//                            ACCESS_WIFI_STATE,
//                            CHANGE_WIFI_STATE,
//                            CAMERA,
//                            READ_EXTERNAL_STORAGE,
//                            ACCESS_COARSE_LOCATION,
//                            WRITE_EXTERNAL_STORAGE},
//
//                    100);
//        }
//    }
//}
