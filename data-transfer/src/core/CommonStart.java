package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CommonStart {

    static CommonTemplate mtl;
    static CommonTemplate gmp;

    public static void main(String[] args) throws IOException {
        mtl = new CommonTemplate("mtl");
        mtl.init();
        gmp = new CommonTemplate("gmp");
        gmp.init();
        CsvWriter csvWriter = new CsvWriter("/Users/mishi/Documents/workspace/data-transfer/files/target1.csv", ',',
                                            Charset.forName("GBK"));
        InputStream is = null;
        CsvReader reader = null;

        is = new FileInputStream("/Users/mishi/Documents/workspace/data-transfer/files/0413/305.csv");

        reader = new CsvReader(is, ';', Charset.forName("UTF-8"));

        String[] oneRow = new String[] {};
        String TRACKING = "";

        List<TargetBO> targetBOs = new ArrayList<>();
        List<SrcBO> srcBOs = new ArrayList<>();

        // 解析表头
        reader.readRecord();
        String[] row = reader.getValues();
        Map<TittleEm, Integer> indexMaps = new HashMap<>();

        // ship date,tracking,state,zip,weight,zone
        for (TittleEm eachObj : TittleEm.values()) {
            for (int i = 0; i < row.length; i++) {
                if (eachObj.title.equalsIgnoreCase(row[i])) {
                    indexMaps.put(eachObj, i);
                }
            }
        }

        while (reader.readRecord()) {
            row = reader.getValues();
            SrcBO eachObj = new SrcBO();
            if (indexMaps.get(TittleEm.city) != null) {
                eachObj.setCity(row[indexMaps.get(TittleEm.city)]);
            }
            eachObj.setG(row[6]);
            eachObj.setH(row[7]);
            eachObj.setcharge(row[8]);
            eachObj.setJ(row[9]);
            eachObj.setCustomer_weight(row[indexMaps.get(TittleEm.customer_weight)]);
            eachObj.setShip_date(row[indexMaps.get(TittleEm.ship_date)]);
            eachObj.setShipper(row[indexMaps.get(TittleEm.shipper)]);
            eachObj.setState(row[indexMaps.get(TittleEm.state)]);

            eachObj.setTracking(row[indexMaps.get(TittleEm.tracking)]);
            eachObj.setWeight(row[indexMaps.get(TittleEm.weight)]);
            eachObj.setZip(row[indexMaps.get(TittleEm.zip)]);
            eachObj.setZone(row[indexMaps.get(TittleEm.zone)]);
            srcBOs.add(eachObj);

        }

        List<TargetBO> targets = importData(srcBOs);

        csvWriter.writeRecord(new String[] { TittleEm.shipper.title, "tracking", "ship date", "state", "zip",
                "customer weight", "weight", "zone", TittleEm.Ground_Commercial.title,
                TittleEm.Ground_Residential.title, TittleEm.Ground_Residential_Third_Party.title,
                TittleEm.Ground_Commercial_Third_Party.title, TittleEm.residential_surcharge.title,
                TittleEm.delivery_area_surcharge.title, TittleEm.Delivery_Area_Surcharge_Extended.title,
                TittleEm.fuel_surcharge.title, TittleEm.Additional_Handling.title, "total",
                TittleEm.freight_rate.title, TittleEm.I2nd_Day_Air_Residential.title,
                TittleEm.Delivery_Confirmation_Signature.title, TittleEm.Declared_Value.title,
                TittleEm.Delivery_Confirmation_Response.title, TittleEm.Returns_Print_and_Mail_Label.title,
                TittleEm.Residential_Adjustment.title, TittleEm.Remote_Area_Surcharge.title, "exception" });
        for (TargetBO eachObj : targets) {
            oneRow = new String[] { eachObj.getShipper(), eachObj.getTracking(), eachObj.getShip_date(),
                    eachObj.getState(), eachObj.getZip(), eachObj.getCustomer_weight(), eachObj.getWeight(),
                    eachObj.getZone(), eachObj.getGround_Commercial(), eachObj.getGround_Residential(),
                    eachObj.getGround_Residential_Third_Party(), eachObj.getGround_Commercial_Third_Party(),
                    eachObj.getResidential_surcharge(), eachObj.getDelivery_area_surcharge(),
                    eachObj.getDelivery_Area_Surcharge_Extended(), eachObj.getFuel_surcharge(),
                    eachObj.getAdditional_Handling(), eachObj.getTatal(), eachObj.getFreight_rate(),

                    eachObj.getI2nd_Day_Air_Residential(), eachObj.getDelivery_Confirmation_Signature(),
                    eachObj.getDeclared_Value(),

                    eachObj.getDelivery_Confirmation_Response(), eachObj.getReturns_Print_and_Mail_Label(),
                    eachObj.getResidential_Adjustment(), eachObj.getRemote_Area_Surcharge(), eachObj.getFailReason() };
            csvWriter.writeRecord(oneRow);
        }
        csvWriter.close();
    }

    public static List<TargetBO> importData(List<SrcBO> srcBOs) {
        List<TargetBO> targetBOs = new ArrayList<>();
        TargetBO target = new TargetBO();
        for (int i = 0; i < srcBOs.size(); i++) {
            SrcBO eachObj = srcBOs.get(i);
            boolean flag = true;
            for (TittleEm tittleEm : TittleEm.values()) {
                if (tittleEm.title.equalsIgnoreCase(eachObj.getcharge())) {
                    flag = false;
                }
            }
            if (flag) {
                System.out.println(eachObj.getcharge());
            }
            if (eachObj.getWeight() != null && !eachObj.getWeight().equals("0")) {
                target.setWeight(eachObj.getWeight());
                target.setCustomer_weight(eachObj.getCustomer_weight());
                target.setZone(eachObj.getZone());
                target.setTracking(eachObj.getTracking());
                target.setShipper(eachObj.getShipper());
            }
            boolean isFRT = false;
            String currentWeight = eachObj.getWeight();
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Declared_Value.title)) {
                target.setDeclared_Value(add(target.getDeclared_Value(), eachObj.getJ(), isFRT, target));
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.delivery_area_surcharge.title)) {
                target.setDelivery_area_surcharge(eachObj.getJ());
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.delivery_area_surcharge_extend.title)) {
                target.setDelivery_area_surcharge_extend(add(target.getDelivery_area_surcharge_extend(),
                                                             eachObj.getJ(), isFRT, target));
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Delivery_Confirmation_Signature.title)) {
                target.setDelivery_Confirmation_Signature("4.25");
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.freight_rate.title)) {
                target.setFreight_rate(add(target.getFreight_rate(), eachObj.getJ(), isFRT, target));
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.fuel_surcharge.title)) {
                target.setFuel_surcharge(add(target.getFuel_surcharge(), eachObj.getJ(), isFRT, target));
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Ground_Commercial.title) && !currentWeight.equals("0")) {
                target.setGround_Commercial(add(target.getGround_Commercial(), eachObj.getJ(), true, target));
            }

            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Ground_Residential.title) && !currentWeight.equals("0")) {
                target.setGround_Residential(add(target.getGround_Commercial(), eachObj.getJ(), true, target));
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.I2nd_Day_Air_Residential.title)) {
                target.setI2nd_Day_Air_Residential(add(target.getI2nd_Day_Air_Residential(), eachObj.getJ(), isFRT,
                                                       target));
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.residential_surcharge.title)) {
                target.setResidential_surcharge("3.25");
            }

            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Ground_Residential_Third_Party.title)
                && !currentWeight.equals("0")) {
                target.setGround_Residential_Third_Party(add(target.getGround_Residential_Third_Party(),
                                                             eachObj.getJ(), true, target));
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Ground_Commercial_Third_Party.title)
                && !currentWeight.equals("0")) {
                target.setGround_Commercial_Third_Party(add(target.getGround_Commercial_Third_Party(), eachObj.getJ(),
                                                            true, target));
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Delivery_Area_Surcharge_Extended.title)) {
                target.setDelivery_Area_Surcharge_Extended(eachObj.getJ());
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Returns_Print_and_Mail_Label.title)) {
                target.setReturns_Print_and_Mail_Label(add(target.getReturns_Print_and_Mail_Label(), eachObj.getJ(),
                                                           isFRT, target));
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Residential_Adjustment.title)) {
                target.setResidential_Adjustment(add(target.getResidential_Adjustment(), eachObj.getJ(), isFRT, target));
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Delivery_Confirmation_Response.title)) {
                target.setDelivery_Confirmation_Response("2.00");
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Remote_Area_Surcharge.title)) {
                target.setRemote_Area_Surcharge(add(target.getRemote_Area_Surcharge(), eachObj.getJ(), isFRT, target));
            }
            if (eachObj.getcharge().equalsIgnoreCase(TittleEm.Additional_Handling.title)) {
                target.setAdditional_Handling(add(target.getAdditional_Handling(), eachObj.getJ(), isFRT, target));
            }
            //
            if (i == srcBOs.size() - 1 || !eachObj.getTracking().equalsIgnoreCase(srcBOs.get(i + 1).getTracking())) {
                // 新target
                target.setCity(eachObj.getCity());
                target.setShip_date(eachObj.getShip_date());
                target.setState(eachObj.getState());

                target.setZip(eachObj.getZip());
                target.setShip_date(eachObj.getShip_date());

                targetBOs.add(target);
                target = new TargetBO();
            }

        }

        for (TargetBO eachOBj : targetBOs) {
            if (eachOBj.getDelivery_area_surcharge() != null) {
                if (eachOBj.getResidential_surcharge() != null) {
                    eachOBj.setDelivery_area_surcharge("3.15");
                } else {
                    eachOBj.setDelivery_area_surcharge("2.3");
                }

            }
            if (eachOBj.getDelivery_area_surcharge_extend() != null) {
                eachOBj.setDelivery_area_surcharge_extend(eachOBj.getResidential_surcharge() != null ? "4.00" : "2.3");
            }
            eachOBj.setFuel_surcharge(String.format("%.2f",
                                                    add(eachOBj.getGround_Commercial(),
                                                        eachOBj.getGround_Residential(),
                                                        eachOBj.getGround_Commercial_Third_Party(),
                                                        eachOBj.getGround_Residential_Third_Party(),
                                                        eachOBj.getResidential_surcharge(),
                                                        eachOBj.getDelivery_area_surcharge(),
                                                        eachOBj.getDelivery_area_surcharge_extend()) * 0.0525));
            eachOBj.setTatal(String.format("%.2f",
                                           add(eachOBj.getGround_Commercial(), eachOBj.getGround_Residential(),
                                               eachOBj.getGround_Commercial_Third_Party(),
                                               eachOBj.getGround_Residential_Third_Party(),
                                               eachOBj.getResidential_surcharge(),
                                               eachOBj.getDelivery_area_surcharge(),
                                               eachOBj.getDelivery_area_surcharge_extend(),
                                               eachOBj.getFuel_surcharge(), eachOBj.getAdditional_Handling())));

        }

        Collections.sort(targetBOs, new TargetCompartor());
        return targetBOs;

    }

    static class TargetCompartor implements Comparator<TargetBO> {

        @Override
        public int compare(TargetBO o1, TargetBO o2) {
            if (o1.getShipper() == null) {
                return -1;
            }
            return o1.getShipper().compareTo(o2.getShipper());
        }

    }

    static double add(String... a) {
        double d = 0;
        for (String str : a) {
            if (str != null) {
                d = d + Double.valueOf(str);
            }
        }
        return d;
    }

    static String add(String a, String b, boolean isFrt, TargetBO target) {
        if (isFrt) {
            String charge = null;
            try {
                CommonTemplate dhl = null;
                if ("M T L International".equalsIgnoreCase(target.getShipper())) {
                    dhl = mtl;
                } else {
                    dhl = gmp;
                }
                charge = String.format("%.2f",
                                       dhl.getTemplate()
                                          .get(Integer.valueOf(target.getZone()))
                                          .get(Integer.valueOf(target.getWeight())));
            } catch (Exception e) {
                System.out.println(target.getTracking());
                target.setFailReason("!!!exception!!! ");
            }
            if (a == null) {
                return charge;

            } else {
                return String.valueOf(Double.valueOf(a) + Double.valueOf(charge));
            }
        }

        else {
            if (a == null) {
                return b;

            } else {
                return String.valueOf(Double.valueOf(a) + Double.valueOf(b));
            }
        }
    }
}
