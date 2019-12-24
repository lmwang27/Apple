package com.apple.mywork;
/**

 Problem One:

 Given a log file for the home page visits of users for a month. Tab separated. Code with input validations and unit tests.

 productId    userId    timestamp (yyyy-mm-dd hh:mm)
 p1           u1         2019-01-02 02:12
 p3           u2         2019-01-02 04:13
 p1           u1         2019-01-02 03:12
 p12          u1         2019-01-02 02:20
 p1           u1         2019-01-02 06:12
 p89          u11        2019-01-03 02:20
 p1           u10        2019-01-03 03:20
 p1           u20        2019-01-03 02:19
 p1           u20        2019-01-03 02:20
 p11          u2         2019-01-05 02:21
 p11          u1         2019-01-06 02:22
 p12          u19        2019-01-08 02:20


 1.) For each product how many unique users have visited the product ?

 Output
 p1:3
 p3:1
 p12:2 ... and so on

 2.) Given a range of timestamp find the number of home page visits in that duration. Input will be in format:
 (yyyy-mm-dd hh - yyyy-mm-dd hh)

 e.g.

 Input
 2019-01-01 00 - 2019-01-03 00

 Output
 5

 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PageVisitors {

    //reading the file
    private ArrayList<String> timeBuffer;
    //map for product to unique visitors set
    private Map<String, Set<String>> hash;

    //timestamp format
    private  SimpleDateFormat sdf;

    public PageVisitors(String filePath) {
        this.timeBuffer = new ArrayList<String>();
        this.hash = new TreeMap<String,Set<String>>(new MyKeyCompare());
        this.sdf = new SimpleDateFormat("yyyy-mm-dd hh");

        try{
            readFile(filePath);
        }catch(Exception e){
            System.out.println(e);
        }

    }

    //comparator for the output
    class MyKeyCompare implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int num1 = Integer.parseInt(o1.substring(1));
            int num2 = Integer.parseInt(o2.substring(1));
            return num1 - num2;
        }
    }

    /*
     * Reading the given file.
     * @param : none
     * @return: Void
     */

    public void readFile(String filePath) throws IOException {

        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        try {
            st = br.readLine();
            //System.out.println(st);
            while ((st = br.readLine()) != null && !st.equals("")) {
                st = st.trim();
                //System.out.println(st);
                String[] arr = st.split("\\t");
                timeBuffer.add(arr[2]);
                int len = arr.length;

                if (hash.containsKey(arr[0])) {
                    hash.get(arr[0]).add(arr[1]);
                } else {
                    hash.put(arr[0], new HashSet<String>());
                    hash.get(arr[0]).add(arr[1]);
                }

            }

            br.close();
        } catch (IOException e) {
            System.out.print(e);
        } finally {
            br.close();
        }

    }


    /*
     * Reading the given file.
     * @param : none
     * @return: HashMap A HashMap contains each product to visitors
     */

    public TreeMap<String,Integer> numOfVistorsPerPage(){

        if(hash.size() == 0){
            return new TreeMap<>();
        }

        TreeMap<String,Integer> results = new TreeMap<>(new MyKeyCompare());
        for(String product : hash.keySet()){
            if(results.containsKey(product)){
                continue;
            }else {
                results.put(product, hash.get(product).size());
            }
        }

        return results;
    }

    /*
     * Calculate the number of visitors between two timestamp.
     * @param : none
     * @return: Void
     */

    public int numOfVisitorBetweenTimes(String t1, String t2) {
        //check the input
        if(t1 == null || t1.length() == 0 || t2 == null || t2.length()==0){
            return 0;
        }
        int res = 0;

        try {
            Date parsedDate1 = sdf.parse(t1);
            Date parsedDate2 = sdf.parse(t2);
            Timestamp timestamp1 = new Timestamp(parsedDate1.getTime());
            Timestamp timestamp2 = new Timestamp(parsedDate2.getTime());
            ArrayList<Timestamp> timestamps = new ArrayList<Timestamp>();

            for (String t : timeBuffer) {
                Date cur = sdf.parse(t);
                timestamps.add(new Timestamp(cur.getTime()));
            }

            Collections.sort(timestamps);
            for (Timestamp tt : timestamps) {
                if (tt.after(timestamp1) && tt.before(timestamp2)) {
                    res += 1;
                }
            }


        } catch (Exception e) {
            System.out.println(e);
        }


        return res;

    }
}
