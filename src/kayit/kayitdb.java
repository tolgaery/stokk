package kayit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ali
 */
public class kayitdb {
	private final String connectionUrl;
	private final String user;
	private final String pass;

	public kayitdb(String connectionUrl, String user, String pass) {
		this.connectionUrl = connectionUrl;
		this.user = user;
		this.pass = pass;
	}
	
	public ArrayList<Kisi> KisiListesi(){
		ArrayList<Kisi> Listem = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(connectionUrl, user, pass))
		{
		    Statement stmt = con.createStatement(); 
            String SQL = "SELECT [Id] ,[Adi] ,[Tel] FROM [stokkk].[dbo].[Liste]";
			String SQL2 = "WHERE (Adi LIKE N'xxx%')";
            ResultSet rs = stmt.executeQuery(SQL);
       
			// Iterate through the data in the result set and display it.
			while(rs.next()){
				Kisi k=new Kisi();
				//Retrieve by column name
				k.setId(rs.getInt("Id"));
				k.setAdi(rs.getString("Adi"));
				k.setTel(rs.getString("Tel"));
				Listem.add(k);
			}
			rs.close();
			//con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return Listem;
	}
		
	public int KisiSil(String Kid, String Adi, String Tel)
	{
        int say = -1;
		Boolean giris_hazir = 
			(Kid.length()>0) &&		// kayit no olmali
			(Adi.length()>0) &&		// adi yazilmali
			(Tel.length()>0);		// telefon yazilmali
		
		int id=-1;
		
		try {
			id = Integer.valueOf(Kid);
		} catch (Exception e) {
			return say;
		}
				
		if (!giris_hazir)
			return say;

        try (  Connection con = DriverManager.getConnection(connectionUrl,user,pass); ) 
        {
			String SQL1 = "DELETE FROM dbo.Liste WHERE id=?";
			PreparedStatement stmt = con.prepareStatement(SQL1);
			stmt.setInt(1, id);			
            say = stmt.executeUpdate();
			stmt.close();
            con.close();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
		
    	return say;
	}

	public int KisiGuncellle(String Kid, String Adi, String Tel)
	{
        int say = -1;
		Boolean giris_hazir = 
			(Kid.length()>0) &&		// kayit no olmali
			(Adi.length()>0) &&		// adi yazilmali
			(Tel.length()>0);		// telefon yazilmali
		
		int id=-1;
		
		try {
			id = Integer.valueOf(Kid);
		} catch (Exception e) {
			return say;
		}
				
		if (!giris_hazir)
			return say;

        try (  Connection con = DriverManager.getConnection(connectionUrl,user,pass); ) 
        {
			String SQL1 = "UPDATE dbo.Liste SET Adi=?, Tel=? WHERE Id= ?";
			PreparedStatement stmt = con.prepareStatement(SQL1);
			stmt.setString(1, Adi);		// 1. parametreyi ekle = Adi bilgisi
			stmt.setString(2, Tel);		// 2. parametreyi ekle = Tel bilgisi
			stmt.setInt(3, id);			// 2. parametreyi ekle = Tel bilgisi
            say = stmt.executeUpdate();
			stmt.close();
            con.close();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
		
        return say;
	}

	public int KisiEkle(String Kid, String Adi, String Tel)
	{
        int say = -1;
		Boolean giris_hazir = 
			(Kid.length()==0) &&	// kayit no bos olmali
			(Adi.length()>0) &&		// adi yazilmali
			(Tel.length()>=0);		// telefon yazilmali
		
		if (!giris_hazir)
			return say;

        try (  Connection con = DriverManager.getConnection(connectionUrl,user,pass); ) 
        {
			String SQL1 = "INSERT INTO dbo.Liste (Adi, Tel,) VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(SQL1);
			stmt.setString(1, Adi);		// 1. parametreyi ekle = Adi bilgisi
			stmt.setString(2, Tel);		// 2. parametreyi ekle = Tel bilgisi
            say = stmt.executeUpdate();
			stmt.close();
            con.close();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
		
		return say;
	}

}
