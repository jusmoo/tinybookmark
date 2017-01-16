package team.ycpy.tinybookmark.model;

public class Item {
	private String Ino;
	private String Iname;
	private String Fno;
	
	public String getIno(){
		return this.Ino;
	}
	
	public String getIname(){
		return this.Iname;
	}
	
	public String getFno(){
		return this.Fno;
	}
	
	public void setIno(String str){
		this.Ino = str;
	}
	
	public void setIname(String str){
		this.Iname = str;
	}
	
	public void setFno(String str){
		this.Fno = str;
	}

}
