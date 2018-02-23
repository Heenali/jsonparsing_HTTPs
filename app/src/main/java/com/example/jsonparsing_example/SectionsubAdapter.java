package com.example.jsonparsing_example;

import java.io.InputStream;
import java.util.ArrayList;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SectionsubAdapter extends ArrayAdapter<Sectionsub_method> 
{
	ArrayList<Sectionsub_method> actorList;
	LayoutInflater vi;
	Context context;
	
	public SectionsubAdapter(Context context, ArrayList<Sectionsub_method> objects) {
		super(context,  R.layout.sectionsub_list, objects);
		this.context = context;
		this.vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		this.actorList = objects;
	}
 
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View rowView;
	    ViewHolder vh;
		if (convertView  == null) {
			
			rowView = vi.inflate(R.layout.sectionsub_list, null);
			setViewHolder(rowView);
		} else 
		{
			rowView = convertView;
			
		}
		vh = (ViewHolder)rowView.getTag();
		
		String s=actorList.get(position).gettitle().toString();



		vh.text1.setText(Html.fromHtml("Email Id :"+actorList.get(position).getId().trim()));
		vh.text2.setText(Html.fromHtml("MobNo :"+actorList.get(position).getImage().trim()));
		//UrlImageViewHelper.setUrlDrawable(vh.imageview, image_path, R.drawable.lode);
		vh.title.setText(Html.fromHtml(actorList.get(position).gettitle().trim()));
		vh.desc.setText(Html.fromHtml(actorList.get(position).getdesc().trim()));

		vh.text3.setText(Html.fromHtml("Date :"+actorList.get(position).gettext1().trim()));
		vh.text4.setText(Html.fromHtml("Time :"+actorList.get(position).gettext2().trim()));
		vh.text5.setText(Html.fromHtml("Hospital Name :"+actorList.get(position).gettext3().trim()));
		vh.text6.setText(Html.fromHtml("speciality :"+actorList.get(position).gettext4().trim()));
	    
		return rowView;

	}

	static class ViewHolder 
	{
		public ImageView imageview;
		public TextView title,desc,text1,text2,text3,text4,text5,text6,text7;
	}
	private void setViewHolder(View rowView) 
	   {
	    			ViewHolder vh = new ViewHolder();
	    		  
					//vh.imageview = (ImageView) rowView.findViewById(R.id.imageView1);
		   vh.title = (TextView) rowView.findViewById(R.id.textView1);
		   vh.desc= (TextView) rowView.findViewById(R.id.textView2);

		   vh.text1= (TextView) rowView.findViewById(R.id.text1);
		   vh.text2= (TextView) rowView.findViewById(R.id.text2);
		   vh.text3= (TextView) rowView.findViewById(R.id.text3);
		   vh.text4= (TextView) rowView.findViewById(R.id.text4);
		   vh.text5= (TextView) rowView.findViewById(R.id.text5);
		   vh.text6= (TextView) rowView.findViewById(R.id.text6);

			rowView.setTag(vh);
			
	   }


}