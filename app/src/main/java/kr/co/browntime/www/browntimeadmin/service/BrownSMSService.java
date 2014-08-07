package kr.co.browntime.www.browntimeadmin.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.util.Log;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import kr.co.browntime.www.browntimeadmin.JSONRequest;
import kr.co.browntime.www.browntimeadmin.model.BrownBuyer;

/**
 * Created by kimsanghwan on 8/4/2014.
 */
public class BrownSMSService extends IntentService {
    public static final String NOTIFICATION = "com.browntime.android.SMSservice.receiver";
    public static final String RESULT = "result";


    public BrownSMSService() {
        super("BrownSMSService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        new HttpRequestSendSMS().execute();
    }

    private class HttpRequestSendSMS extends AsyncTask<Void, Void, List<BrownBuyer>> {
        @Override
        protected List<BrownBuyer> doInBackground(Void... params) {
            try {

//                final String url = "http://10.0.2.2:8080/BrownTime/json/sendSMS";
                final String url = "http://browntime123.cafe24.com/json/sendSMS";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(new JSONRequest().getMessageConverters());

                BrownBuyer[] buyers = restTemplate.getForObject(url, BrownBuyer[].class);

                return Arrays.asList(buyers);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<BrownBuyer> buyers) {

            SmsManager sms = SmsManager.getDefault();
            for (BrownBuyer buyer : buyers) {
                sms.sendTextMessage("01099155894", null, String.valueOf(buyer.getmSMSNumber()), null, null);
            }
        }
    }
}
