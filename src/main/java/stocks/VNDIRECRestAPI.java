package stocks;

import com.google.gson.Gson;
import entities.Stonks;
import ultis.StonksUltis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class VNDIRECRestAPI {

    private String JSON;
    private URL url;
    private HttpURLConnection connection;

    public VNDIRECRestAPI(String symbol, String startDate, String endDate) {
        String apiLink = "https://finfo-api.vndirect.com.vn/v4/stock_prices/?q=code:" + symbol + "~date:gte:" + startDate + "~date:lte:" + endDate;
        try {
            this.url = new URL(apiLink);
            this.connection = (HttpURLConnection) url.openConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPureJSON () {
        try {
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            connection.setConnectTimeout(5000);

            if (responseCode == 200) {
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                JSON = buffReader.readLine();
                StringBuilder sb = new StringBuilder(JSON);
                sb.delete(0,8);
                sb.delete(JSON.length()-69, JSON.length());
                JSON = sb.toString();
                return JSON;
            }
            return "Connection timeout";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error when fetching data";
        }
    }

    public Stonks[] getListObject() {
        Gson gson = new Gson();
        Stonks[] stonks;
        return StonksUltis.sortByDate(gson.fromJson(this.JSON, Stonks[].class));
    }

}
