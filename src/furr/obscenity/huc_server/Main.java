// Project started 17/01/17 - 4:50PM AEST
/*
	Things the Java program should do:
	
	- Get latest Github homebrew release version | Done 17/01/17 - 5:59PM
	- Compare with local version
	- Display title and version comparison
	- Optionally download updates into .\%title%\%version%\ directory
	- Or just open the versions page in the default browser
*/

package furr.obscenity.huc_server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
	  
	  
	  System.out.print("Enter repo owners username: \n");
	  Scanner auser = new Scanner(System.in);
	  String a_user = auser.next();

	  
	  System.out.print("Enter repo name: \n");
	  Scanner arepo = new Scanner(System.in);
	  String a_repo = arepo.next();
	  auser.close();
	  arepo.close();
	  System.out.print("\n");
	
	String p_url1 = "https://api.github.com/repos/";
	String i_user = a_user;
	String i_repo = a_repo;
	String p_url2 = "/releases/latest";
	String u_total = p_url1 + i_user + "/" + i_repo + p_url2;
    JSONObject json = readJsonFromUrl(u_total);
    System.out.print("Downloadable files: \n");
    JSONArray what = json.getJSONArray("assets");
    for (int i = 0; i < what.length(); ++i) {
    	JSONObject rec = what.getJSONObject(i);
    	String id = rec.getString("browser_download_url");
    	System.out.println(id.toString());
    }
    
   /* broken
    
    String dir = "./" + i_user+ "/" + i_repo;
    File folder = new File(dir);
    File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
          if (listOfFiles[i].isFile()) {
            System.out.println("File " + listOfFiles[i].getName());
          } else if (listOfFiles[i].isDirectory()) {
            System.out.println("Directory " + listOfFiles[i].getName());
          }
        }
    */
    
  }
}