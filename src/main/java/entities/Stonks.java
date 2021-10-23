package entities;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stonks {

    @SerializedName("code")
    String code = "VCB";

    @SerializedName("date")
    String date= "2021-10-04";

    @SerializedName("time")
    String time= "15=10=01";

    @SerializedName("floor")
    String floor= "HOSE";

    @SerializedName("type")
    String type= "STOCK";

    @SerializedName("basicPrice")
    double basicPrice= 95.9;

    @SerializedName("ceilingPrice")
    double ceilingPrice= 102.6;

    @SerializedName("floorPrice")
    double floorPrice= 89.2;
    @SerializedName("open")
    double open= 96.0;

    @SerializedName("high")
    double high= 96.5;

    @SerializedName("low")
    double low= 94.4;

    @SerializedName("close")
    double close= 94.6;

    @SerializedName("average")
    double average= 94.99;

    @SerializedName("adOpen")
    double adOpen= 96.0;

    @SerializedName("adHigh")
    double adHigh= 96.5;

    @SerializedName("adLow")
    double adLow= 94.4;

    @SerializedName("adClose")
    double adClose= 94.6;

    @SerializedName("adAverage")
    double adAverage= 94.99;

    @SerializedName("nmVolume")
    double nmVolume= 913400.0;

    @SerializedName("nmValue")
    double nmValue= 8.676442E10;

    @SerializedName("ptVolume")
    double ptVolume= 45900.0;

    @SerializedName("ptValue")
    double ptValue= 4.09428E9;

    @SerializedName("change")
    double change= -1.3;

    @SerializedName("adChange")
    double adChange= -1.3;

    @SerializedName("pctChange")
    double pctChange= -1.3556;

}
