package cordova.plugin.paytab;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import paytabs.project.PayTabActivity;
import org.apache.cordova.PluginResult;

/**
 * This class echoes a string called from JavaScript.
 */
public class paytab extends CordovaPlugin {


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException
    {
        if(action.equals("add"))
        {
            this.add(args, callbackContext);
            return true;
        }else if(action.equals("substract"))
        {
            this.substract(args, callbackContext);
            return true;
        }
        return false;
    }



    private void add(JSONArray data, CallbackContext callback)
    {
        if(data != null)
        {
            try

            {
                JSONObject args = data.getJSONObject(0);

                Context context =  cordova.getActivity().getApplicationContext();
                Intent PayTabs = new Intent(this.cordova.getActivity(), PayTabActivity.class);
//                PayTabs.putExtra("pt_merchant_email", args.getString("merchantEmail"));
//                PayTabs.putExtra("pt_secret_key", args.getString("secretKey"));
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
                PayTabs.putExtra("pt_postal_code_shipping", args.getString("postal_code_shipping"));


                this.cordova.getActivity().startActivityForResult(PayTabs,0);

                SharedPreferences shared_prefs = context.getSharedPreferences("myapp_shared", MODE_PRIVATE); //SP from pay hit
                String pt_response_code= shared_prefs.getString("pt_response_code", "");
                String pt_transaction_id = shared_prefs.getString("pt_transaction_id", "");

                JSONObject obj = new JSONObject();
                try {
                    obj.put("response_code", pt_response_code);
                    obj.put("transaction_id", pt_transaction_id);
                } catch(Exception ex) {
                    callback.error("Something went wrong "  + ex);
                }

                PluginResult result = new PluginResult(PluginResult.Status.OK, obj);
                callback.sendPluginResult(result);

            }catch(Exception ex) {
                callback.error("Something went wrong "  + ex);
            }
        }
        else {
            callback.error("Please donot pass null value");
        }
    }


    private void substract(JSONArray args, CallbackContext callback) {}

}
