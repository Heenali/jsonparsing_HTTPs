package com.example.jsonparsing_example;
//http://www.technotalkative.com/android-json-parsing/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


public class contactpage extends Activity
{
	
	List<NameValuePair> params11;
	ProgressDialog loading;
	Context mcontext;
	Button savebtn;
	String connmsg,lodingmsg;
	EditText name_txt,lastname_txt,email_txt,comment_txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactpage);

		mcontext=this;
		name_txt=(EditText)findViewById(R.id.name_txt);
		lastname_txt=(EditText)findViewById(R.id.lastname_txt);
		email_txt=(EditText)findViewById(R.id.email_txt);
		comment_txt=(EditText)findViewById(R.id.comment_txt);
		savebtn=(Button)findViewById(R.id.savebtn);

		savebtn.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				try
				{
					if(name_txt.getText().toString().trim().equalsIgnoreCase("") || lastname_txt.getText().toString().trim().equalsIgnoreCase("") || email_txt.getText().toString().trim().equalsIgnoreCase("") || comment_txt.getText().toString().trim().equalsIgnoreCase(""))
					{
						final AlertDialog.Builder alert = new AlertDialog.Builder(mcontext);
						//alert.setTitle("Alert");
						alert.setMessage("All Fileds required");
						alert.setPositiveButton("ok", new DialogInterface.OnClickListener() 
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								dialog.cancel();
							}
						});
						
						alert.show();
						
					}
					else
					{
						if(isEmailValid(email_txt.getText().toString()) == true)
		   				{
							params11 = new ArrayList<NameValuePair>();
					        params11.add(new BasicNameValuePair("iCustomerId", ""+"119"));
					        params11.add(new BasicNameValuePair("pageStart", ""+"1"));
					        params11.add(new BasicNameValuePair("vLatitude", ""+"22.3071588"));
					        params11.add(new BasicNameValuePair("vLongitude", ""+"73.1812187"));
							params11.add(new BasicNameValuePair("iSubServiceIdArr", ""+"1"));
							params11.add(new BasicNameValuePair("bookingDate", ""+"2017-05-03"));
							params11.add(new BasicNameValuePair("bookingTime", ""+"18:30"));
							params11.add(new BasicNameValuePair("eBookingTyp", ""+"Pre"));


				        	
				        		new background().execute();
							
		   				}
						else
		   				{
							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mcontext);

							alertDialogBuilder
								.setMessage("Email Address Not correct")
								.setCancelable(false)
								.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) 
									{
										dialog.cancel();
									}
								  });
								AlertDialog alertDialog = alertDialogBuilder.create();
				 				alertDialog.show();
		   				}
					}
					}catch (Exception e) 
					{
						e.printStackTrace();
					}
				
			}
		});
		
}
	   
	
		
	 public boolean isEmailValid(String email) {
		    boolean isValid = false;

		    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		    CharSequence inputStr = email;

		    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(inputStr);
		    if (matcher.matches()) {
		        isValid = true;
		    }
		    return isValid;
		}
	
		class background extends AsyncTask<Void, Void, String>
		{
			
			@Override
			protected void onPreExecute()
			{
				super.onPreExecute();
				loading=new ProgressDialog(mcontext);
				loading.setMessage(lodingmsg);
				loading.setTitle(connmsg);
				loading.setCanceledOnTouchOutside(false);
				loading.show();
			}
			protected String doInBackground(Void... params) 
			{
				String obj;//new JSONArray();
			try
			{
				
		        obj=getJSONFromUrl("http://52.26.76.186/api/web/v1/api-customer/technician-list", params11);
		        
				return obj;
			}
			catch(Exception e)
			{
			}
			return null;	 
			}
			
			@Override
			protected void onPostExecute(final String result) 
			{
				super.onPostExecute(result);
				loading.cancel();
				Log.e("Result",""+result);

				try
				{
					JSONObject obj=new JSONObject(result);
					String s=obj.getString("status");
					//Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
					JSONArray subArray = obj.getJSONArray("data");
					for(int i=0; i<subArray.length(); i++)
					{
						Toast.makeText(getApplicationContext(),subArray.getJSONObject(i).getString("iBeauticianId").toString(),Toast.LENGTH_LONG).show();
					}


				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}
		
		 public String getJSONFromUrl(String url, List<NameValuePair> params) 
		    {
		    	  InputStream is = null;
		    	    String json = "";
		    	 
		        // Making HTTP request
		        try {
		            // defaultHttpClient
		            DefaultHttpClient httpClient = new DefaultHttpClient();
		            HttpPost httpPost = new HttpPost(url);
		            httpPost.setEntity(new UrlEncodedFormEntity(params));
		            HttpResponse httpResponse = httpClient.execute(httpPost);
		            HttpEntity httpEntity = httpResponse.getEntity();
		            is = httpEntity.getContent();
		        } catch (UnsupportedEncodingException e) {
		            e.printStackTrace();
		        } catch (ClientProtocolException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		 
		        try {
		            BufferedReader reader = new BufferedReader(new InputStreamReader(
		                    is, "iso-8859-1"), 8);
		            StringBuilder sb = new StringBuilder();
		            String line = null;
		            while ((line = reader.readLine()) != null) {
		                sb.append(line);
		                //sb.append(line + "\n");
		            }
		            is.close();
		            json = sb.toString();
		            Log.e("JSON", json);
		        } catch (Exception e) 
		        {
		            Log.e("Buffer Error", "Error converting result " + e.toString());
		        }
		      
		        return json;
		 
		    }

}
