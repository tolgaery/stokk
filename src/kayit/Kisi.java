
package kayit;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ali
 */
public class Kisi {
	private int id;
	private String Adi;
	private String Tel;

	public Kisi() {
		this.id = -1;
		this.Adi = "";
		this.Tel = "";
	}
	
	public Kisi(int id, String Adi, String Tel) {
		this.id = id;
		this.Adi = Adi;
		this.Tel = Tel;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String Tel) {
		this.Tel = Tel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdi() {
		return Adi;
	}

	public void setAdi(String Adi) {
		this.Adi = Adi;
	}
	
	
}
