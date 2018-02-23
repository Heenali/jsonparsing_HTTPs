package com.example.jsonparsing_example;

public class Sectionsub_method {
	private String title;
	private String desc;
	private String image;
	private String id;
	private String text1;
	private String text2;
	private String text3;
	private String text4;
	public Sectionsub_method() 
	{
		
	}

	public Sectionsub_method(String id ,String title, String desc,String image,String text1,String text2,String text3,String text4) {
		super();
		this.title = title;
		this.id = id;
		this.image = image;
		this.desc = desc;
		this.text1 = text1;
		this.text2 = text2;
		this.text3 = text3;
		this.text4 = text4;
	}


	public String gettitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}


	public String gettext4() {
		return text4;
	}

	public void settext4(String title) {
		this.text4 = text4;
	}



	public String gettext3() {
		return text3;
	}

	public void settext3(String title) {
		this.text3 = text3;
	}

	public String gettext2() {
		return text2;
	}

	public void settext2(String title) {
		this.text2 = text2;
	}




	public String gettext1() {
		return text1;
	}

	public void settext1(String title) {
		this.text1 = text1;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getId()
	{
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getdesc()
	{
		return desc;
	}

	public void setdesc(String desc) {
		this.desc = desc;
	}

	

}
