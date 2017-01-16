package team.ycpy.tinybookmark.model;

public class Folder {
	private String Ino;
	private String Iname;
	private int Ftype;
	private String CreaterId;
	
	public String getIno(){
		return this.Ino;
	}
	
	public String getIname(){
		return this.Iname;
	}
	
	public int getFtype(){
		return this.Ftype;
	}
	
	public String getCreaterId(){
		return this.CreaterId;
	}
	
	public void setIno(String str){
		this.Ino = str;
	}
	
	public void setIname(String str){
		this.Iname = str;
	}
	
	public void setCreaterId(String str){
		this.CreaterId = str;
	}
	
	public void setFtype(int ft){
		this.Ftype = ft;
	}

}
