package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.HistoryInfo;
import com.example.android.quickinventoryapp.BaseClasses.ProductInfo;
import com.example.android.quickinventoryapp.BaseClasses.ProductPoints;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ChartActivity extends AppCompatActivity {
    DatabseHelper databseHelper;
    PieChart chart;
    static String USERNAME = "UserName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        chart =(PieChart)findViewById(R.id.pieChart);

        databseHelper = new DatabseHelper(this);

        Intent intent = getIntent();
        String u_name = null;
        try {
            u_name =  intent.getStringExtra(AnalysisActivity.USERNAME).toString();
            Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }

        List<HistoryInfo> allHistoryInfoList = databseHelper.getAllHsitory(u_name);

        final String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        List<ProductInfo> allProductInfo = databseHelper.getAllProductInfo(u_name);

         Set<String> products = getAllProductsName(allProductInfo);

         List<ProductPoints> listOfcurrentlyAvailableProductAndQuantity = getAllCurrentlyAvailableProductAndQuanity(allProductInfo);
         List<ProductPoints> listOfProductsSellInThisMonth  = getAllSellProductOfLastOneMonth(allHistoryInfoList,date);
         List<ProductPoints>  listOfTotalQuantityOfEachProduct =  ListOfTotalQuantityOfEachProduct(listOfcurrentlyAvailableProductAndQuantity,listOfProductsSellInThisMonth,products);
         List<ProductPoints>  listOfQuantityofEachProductSellInlastMonth = getListOfQuantityofEachProductSellInlastMonth(listOfProductsSellInThisMonth,products);

        List<Float>  parsantageOfEachProductSell = getparsantageOfEachProductSell(listOfQuantityofEachProductSellInlastMonth,listOfTotalQuantityOfEachProduct,products);

        showChart(parsantageOfEachProductSell,products);




    }

    public void showChart(List<Float> parsantageOfEachProductSell, Set<String> products) {
        List<PieEntry> pieEntries = new ArrayList<>();

        int count =0;

        Iterator<String> it = products.iterator();
        while (it.hasNext()){
            String product_name = it.next();
            pieEntries.add(new PieEntry(parsantageOfEachProductSell.get(count),product_name));
            count++;
        }

        PieDataSet dataset = new PieDataSet(pieEntries,"A CHART");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataset);
        chart.setData(data);
        chart.animateY(1000);
        Description desc = new Description();
        desc.setText("Analysis Of Your Products Sell Based On Last 30 Days");
        chart.setDescription(desc);
        chart.invalidate();


    }


    public List<Float> getparsantageOfEachProductSell(List<ProductPoints> listOfProductsSellInThisMonth, List<ProductPoints> listOfTotalQuantityOfEachProduct, Set<String> products) {

      List<Float> list = new ArrayList<>();

        Iterator<String> it = products.iterator();
        while (it.hasNext()){
            String product = it.next();
            int a=0;
            int b =0;

            for(int i=0;i<listOfProductsSellInThisMonth.size();i++){
                if(listOfProductsSellInThisMonth.get(i).getName().equals(product)){
                    a = listOfProductsSellInThisMonth.get(i).getQuantity();
                }
            }

            for(int i=0;i<listOfTotalQuantityOfEachProduct.size();i++){
                if(listOfTotalQuantityOfEachProduct.get(i).getName().equals(product)){
                    b = listOfTotalQuantityOfEachProduct.get(i).getQuantity();
                }
            }

            list.add((float) ((a/(float)b)*100));
          //  Toast.makeText(this,Float.toString((a/(float)b)*100),Toast.LENGTH_LONG).show();


        }
        return list;
    }

    public List<ProductPoints> ListOfTotalQuantityOfEachProduct(List<ProductPoints> listOfcurrentlyAvailableProductAndQuantity, List<ProductPoints> listOfProductsSellInThisMonth, Set<String> products) {

        List<ProductPoints> list = new ArrayList<>();

        Iterator<String> it = products.iterator();
        while (it.hasNext()){
            String product = it.next();
            int a =0;
            int b= 0;
            for(int i=0;i<listOfcurrentlyAvailableProductAndQuantity.size();i++){
                if(listOfcurrentlyAvailableProductAndQuantity.get(i).getName().equals(product)){
                    a += listOfcurrentlyAvailableProductAndQuantity.get(i).getQuantity();
                }
            }

            for(int i=0;i<listOfProductsSellInThisMonth.size();i++){
                if(listOfProductsSellInThisMonth.get(i).getName().equals(product)){
                    b += listOfProductsSellInThisMonth.get(i).getQuantity();
                   // Toast.makeText(this,Integer.toString(i)+" "+Integer.toString(b),Toast.LENGTH_LONG).show();
                }
            }


            list.add(new ProductPoints(product,(a+b)));

        }

        return list;

    }

    public List<ProductPoints> getListOfQuantityofEachProductSellInlastMonth(List<ProductPoints> listOfProductsSellInThisMonth,Set<String> products){
        List<ProductPoints> list = new ArrayList<>();

        Iterator<String> it = products.iterator();
        while (it.hasNext()){
            String product = it.next();
            int b=0;

            for(int i=0;i<listOfProductsSellInThisMonth.size();i++){
                if(listOfProductsSellInThisMonth.get(i).getName().equals(product)){
                    b += listOfProductsSellInThisMonth.get(i).getQuantity();
                }
            }

            list.add(new ProductPoints(product,b));
        }
        return list;

    }

    private List<ProductPoints> getAllSellProductOfLastOneMonth(List<HistoryInfo> allHistoryInfoList, String date) {

        List<ProductPoints> list = new ArrayList<>();

        SimpleDateFormat myFormet = new SimpleDateFormat("dd-MM-yyyy");


        try{
            Date date2 = myFormet.parse(date);

            for(int i=0;i<allHistoryInfoList.size();i++){
                String saleDate = allHistoryInfoList.get(i).getDate();

                Date date1 = myFormet.parse(saleDate);
                long diff = date2.getTime() - date1.getTime();

              long diff_long=  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                if(diff_long<=30){
                    list.add(new ProductPoints(allHistoryInfoList.get(i).getProductName(),Integer.parseInt(allHistoryInfoList.get(i).getQuantity())));
                }


            }

        }catch (Exception e){
            Toast.makeText(this,"ERROR IN DATE CONVERSION",Toast.LENGTH_LONG).show();
        }

        return list;

    }
    public List<ProductPoints> getQuantityOfSellOfEachProduct(Set<String> products, List<HistoryInfo> allhisHistoryInfoList) {

        List<ProductPoints> list = new ArrayList<>();
        Iterator<String> it = products.iterator();

        while (it.hasNext()){
            String pName  = it.next();
            int count =0;
            for(int i=0;i<allhisHistoryInfoList.size();i++){

                if(allhisHistoryInfoList.get(i).getProductName().equals(pName)){
                    count += Integer.parseInt(allhisHistoryInfoList.get(i).getQuantity());
                }
            }

            list.add(new ProductPoints(pName,count));
        }

        return list;
    }

    public List<ProductPoints> getAllCurrentlyAvailableProductAndQuanity(List<ProductInfo> allProductInfo) {

        List<ProductPoints> points = new ArrayList<>();

        for (int i = 0; i < allProductInfo.size(); i++) {
            points.add(new ProductPoints(allProductInfo.get(i).getProductName(), Integer.parseInt(allProductInfo.get(i).getQuatity())));


        }

        return points;
    }

    public Set<String> getAllProductsName(List<ProductInfo> allProductInfo){
        Set<String> set = new HashSet<>();

        for(int i=0;i<allProductInfo.size();i++){
            set.add(allProductInfo.get(i).getProductName());
        }

        return set;
    }

}
