package com.example.jsonparsing_example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity{
 

ProgressDialog loading;
Context mcontext;
JSONObject alldata=new JSONObject();
String no_record="";
TextView title;
String ID,Catname;
Button back;
MainActivity all_stuff = null;
String lodingmsg,connmsg;
ArrayList<Sectionsub_method> actorsList;
Button post;
	SectionsubAdapter adapter;
	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mcontext=this;
        loading=new ProgressDialog(mcontext);
		listview = (ListView)findViewById(R.id.listsection);


		try
		{
			loading.setMessage(lodingmsg);
			loading.setTitle(connmsg);
			loading.show();
			loading.setCancelable(false);
			SyncMethod("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors");
		}
		catch (Exception e) 
	 	{
			e.printStackTrace();
	 	}
	
		listview.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) 
			{

				Intent i = new Intent(getApplicationContext(),contactpage.class);
				startActivity(i);
				/*String Item_Name =actorsList.get(position).gettitle();
				String Item_ID = actorsList.get(position).getId();
				String Item_desc =actorsList.get(position).getdesc();
				Log.i("Item_desc",Item_desc);
				Intent i = new Intent(getApplicationContext(),sectiondetaile.class);
				i.putExtra("postid", Item_ID);
				i.putExtra("id", ID);
				i.putExtra("name",Catname);
				i.putExtra("idescription", Item_desc);
				i.putExtra("title",Item_Name );
				
				startActivity(i);*/
				
				// Toast.makeText(getApplicationContext(), Item_ID, 0).show();
				 //Toast.makeText(getApplicationContext(), Item_Price, 0).show();		
			}
		});
		

		
	}

	 //Method for call url and get data		
	 	public void SyncMethod(final String GetUrl)
	 	{
	 		  Log.i("Url.............",GetUrl);
	 		  final Thread background = new Thread(new Runnable() 
	 		  {
	 		      // After call for background.start this run method call
	 		      public void run() 
	 		      {
	 		          try 
	 		          {
	 		        	  String url=GetUrl;
	 		        	  String SetServerString = "";
						  SetServerString=fetchResult(url);
	 		              threadMsg(SetServerString);
	 		          }
	 		          catch (Throwable t) 
	 		          {
	 		        	  Log.e("Animation", "Thread  exception " + t);
	 		          }
	 		      }
	 		      private void threadMsg(String msg) 
	 		      {
	 		      	
	 		          if (!msg.equals(null) && !msg.equals("")) 
	 		          {
	 		              Message msgObj = handler11.obtainMessage();
	 		              Bundle b = new Bundle();
	 		              b.putString("message", msg);
	 		              msgObj.setData(b);
	 		              handler11.sendMessage(msgObj);
	 		          }
	 		      }
	 		      // Define the Handler that receives messages from the thread and update the progress
	 		     private final Handler handler11 = new Handler()
	 		      {
	 		          public void handleMessage(Message msg) 
	 		          {
	 		        	 try
	 		        	 {
	 		              String aResponse = msg.getData().getString("message");

							loading.cancel();
							 JSONObject get_res=new JSONObject(aResponse);

							 JSONObject res_data=new JSONObject();

							 actorsList = new ArrayList<Sectionsub_method>();
	 		                 JSONArray array=new JSONArray();


	 		                    			array=get_res.getJSONArray("actors");
											Log.e("Exam","screen>>"+array+"");
	 		                    			for(int aa=0;aa<array.length();aa++)
		 		                   		  	{
	 		                    		
	 		                    					actorsList.add(new Sectionsub_method(array.getJSONObject(aa).getString("name"),array.getJSONObject(aa).getString("name"),array.getJSONObject(aa).getString("name"),array.getJSONObject(aa).getString("name"),array.getJSONObject(aa).getString("name"),array.getJSONObject(aa).getString("name"),array.getJSONObject(aa).getString("name"),array.getJSONObject(aa).getString("name")));

	 		                    			
											}
											adapter = new SectionsubAdapter(getApplication(),actorsList);
											listview.setAdapter(adapter);
											adapter.notifyDataSetChanged();

	 		        	 }
	 		        	 catch(Exception e)
	 		        	 {
	 		        		 
	 		        	 }
	 		                      
	 		          }
	 		      };
	 		  });
	 		 
	 		  background.start();
	 	 }

	public String fetchResult(String url) throws JSONException
	{
		String responseString = "";
		HttpClient httpClient = HTTPUtils.getNewHttpClient(url.startsWith("https"));
		HttpResponse response = null;
		InputStream in;
		URI newURI = URI.create(url);
		HttpGet getMethod = new HttpGet(newURI);
		try {
			response = httpClient.execute(getMethod);
			in = response.getEntity().getContent();
			responseString = convertStreamToString(in);
		} catch (Exception e) {}
		return responseString;
	}
	public static String convertStreamToString(InputStream is) throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null)
		{
			sb.append(line);
		}
		is.close();
		return sb.toString();
	}

}
