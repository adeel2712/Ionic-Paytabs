package cordova.plugin.paytab;


import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import android.util.Log;
import paytabs.project.PayTabActivity;
import java.util.Locale;
import android.util.DisplayMetrics;
import android.content.res.Configuration;
import android.content.res.Resources;

public class paytab extends CordovaPlugin {

CallbackContext callback;


    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        this.callback = callbackContext;
        if (action.equals("add")) {


            JSONObject args = data.getJSONObject(0);

            String lang = "ar";

            if (args.getString("language") == "English") {
                 lang = "en";
            } else {
                 lang = "ar";
            }

            Locale myLocale = new Locale(lang);
            Resources res = cordova.getActivity().getApplicationContext().getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);


            Intent PayTabs = new Intent(this.cordova.getActivity(), PayTabActivity.class);



            PayTabs.putExtra("pt_merchant_email", "kunwar.adeel@gmail.com");
            PayTabs.putExtra("pt_secret_key","F6oUQA0OMANTdh6MFNYiLbGRihq19HFPO3JFEJtqkgkxxrjceiv0ubSNsiPC0urOyatcUXCedXLLp5YDotETEwG7PvJP0bEym8Kh");
            PayTabs.putExtra("pt_transaction_title", args.getString("transactionTitle"));
            PayTabs.putExtra("pt_amount", args.getString("amount"));
            PayTabs.putExtra("pt_currency_code", "SAR");
            PayTabs.putExtra("pt_shared_prefs_name", "myapp_shared");
            PayTabs.putExtra("pt_customer_email", args.getString("customer_email"));
            PayTabs.putExtra("pt_customer_phone_number", args.getString("customer_phone_number"));
            PayTabs.putExtra("pt_order_id", args.getString("order_id"));
            PayTabs.putExtra("pt_product_name", args.getString("product_name"));
            PayTabs.putExtra("pt_timeout_in_seconds", "100");
            PayTabs.putExtra("pt_address_billing", args.getString("address_billing"));
            PayTabs.putExtra("pt_city_billing", args.getString("city_billing"));
            PayTabs.putExtra("pt_state_billing", args.getString("state_billing"));
            PayTabs.putExtra("pt_country_billing", "SAU");
            PayTabs.putExtra("pt_postal_code_billing", args.getString("postal_code_billing"));
            PayTabs.putExtra("pt_address_shipping", args.getString("address_shipping"));
            PayTabs.putExtra("pt_city_shipping", args.getString("city_shipping"));
            PayTabs.putExtra("pt_state_shipping", args.getString("state_shipping"));
            PayTabs.putExtra("pt_country_shipping", "SAU");
            PayTabs.putExtra("pt_postal_code_shipping", args.   getString("postal_code_shipping"));


            cordova.startActivityForResult((CordovaPlugin) this, PayTabs, 0);
//            getResult(callbackContext);
        }

//        // Send no result, to execute the callbacks later
//        PluginResult pluginResult = new  PluginResult(PluginResult.Status.NO_RESULT);
//        pluginResult.setKeepCallback(true); // Keep callback

        return true;
    }

    private boolean getResult(CallbackContext callbackContext) throws JSONException {
        SharedPreferences shared_prefs = cordova.getActivity().getApplicationContext().getSharedPreferences("myapp_shared", Context.MODE_PRIVATE);
        String pt_response_code = shared_prefs.getString("pt_response_code", "");
        String pt_transaction_id = shared_prefs.getString("pt_transaction_id", "");

        Log.d("Response Code: ",pt_response_code);

        Map<String,String> object = new HashMap<String,String>();
        object.put("response_code", pt_response_code);
        object.put("transaction_id", pt_transaction_id);

        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, new JSONObject(object)));
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.println(requestCode,"Hello","Hello");
        Log.println(resultCode,"Hello","Hello");
        SharedPreferences shared_prefs = cordova.getActivity().getApplicationContext().getSharedPreferences("myapp_shared", Context.MODE_PRIVATE);
            String pt_response_code = shared_prefs.getString("pt_response_code", "");
            String pt_transaction_id = shared_prefs.getString("pt_transaction_id", "");

        Log.d("Response Code: ",pt_response_code);
        Log.d("Response Code: ",pt_transaction_id);

        Map<String,String> object = new HashMap<String,String>();
        object.put("response_code", pt_response_code);
        object.put("transaction_id", pt_transaction_id);

//        cordova.setActivityResultCallback(result);
        this.callback.sendPluginResult(new PluginResult(PluginResult.Status.OK, new JSONObject(object)));

    }

//    @Override
//    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
//        if(resultCode == cordova.getActivity().RESULT_OK){
//            Context context = cordova.getActivity();
//            SharedPreferences shared_prefs = context.getSharedPreferences("myapp_shared", MODE_PRIVATE);
//            String pt_response_code = shared_prefs.getString("pt_response_code", "");
//            String pt_transaction_id = shared_prefs.getString("pt_transaction_id", "");
//
//            JSONObject obj = new JSONObject();
//
//
//            try {
//
//                obj.put("response_code", pt_response_code);
//                obj.put("transaction_id", pt_transaction_id);
//            } catch (Exception ex) {
//                PUBLIC_CALLBACKS.error("Something went wrong " + ex);
//            }
//
//            PluginResult result = new PluginResult(PluginResult.Status.OK, obj);
//            result.setKeepCallback(true);
//            PUBLIC_CALLBACKS.success(obj);
//            return;
//
//        }
//        else if(resultCode == cordova.getActivity().RESULT_CANCELED){
//            PluginResult resultado = new PluginResult(PluginResult.Status.OK, "canceled action, process this in javascript");
//            resultado.setKeepCallback(true);
//            PUBLIC_CALLBACKS.sendPluginResult(resultado);
//            return;
//        }
//        // Handle other results if exists.
//        super.onActivityResult(requestCode, resultCode, data);
//    }


}
