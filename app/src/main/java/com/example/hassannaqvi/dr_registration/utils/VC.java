package com.example.hassannaqvi.dr_registration.utils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Umeed-e-Nau on 12/21/2016.
 */
public class VC {

    private static Multimap<String, String> data = HashMultimap.create();

    public static List<String> get(String district) {

        data.put("Badin", "Badin");
        data.put("Badin", "Golarchi");
        data.put("Badin", "Matli");
        data.put("Badin", "Talhar");

        data.put("Jafferabad", "Gandakha");
        data.put("Jafferabad", "Jhat Pat");
        data.put("Jafferabad", "Usta Muhammad");

        data.put("Lasbella", "Bela");
        data.put("Lasbella", "Dureji");
        data.put("Lasbella", "Gaddani");
        data.put("Lasbella", "Kanraj");
        data.put("Lasbella", "Lakhra");
        data.put("Lasbella", "Sonmiani");
        data.put("Lasbella", "Uthal");

        data.put("Muzaffargarh", "Ali Pur");
        data.put("Muzaffargarh", "Jatoi");
        data.put("Muzaffargarh", "Kot Adu");
        data.put("Muzaffargarh", "Muzaffargarh");

        data.put("Nasirabad", "Chatter");
        data.put("Nasirabad", "Dera Murad Jamali");
        data.put("Nasirabad", "Tamboo");

        data.put("Qambar Shahdadkot", "Miro Khan");
        data.put("Qambar Shahdadkot", "Nasirabad");
        data.put("Qambar Shahdadkot", "Qambar");
        data.put("Qambar Shahdadkot", "Qubo Saeed Khan");
        data.put("Qambar Shahdadkot", "Shahdadkot");
        data.put("Qambar Shahdadkot", "Sijawal");
        data.put("Qambar Shahdadkot", "Warah");

        data.put("Rahim Yar Khan", "Khan Pur");
        data.put("Rahim Yar Khan", "Liaquat Pur");
        data.put("Rahim Yar Khan", "Rahim Yar Khan");
        data.put("Rahim Yar Khan", "Sadiqabad");

        data.put("Sanghar", "Jaam Nawaz Ali");
        data.put("Sanghar", "Khipro");
        data.put("Sanghar", "Khipro");
        data.put("Sanghar", "Sanghar");
        data.put("Sanghar", "Shahdad Pur");
        data.put("Sanghar", "Shahpur Chakar");
        data.put("Sanghar", "Sinjhoro");
        data.put("Sanghar", "Tando Adam");

        data.put("Karachi", "Gulshan-e-Iqbal");
        data.put("Karachi", "Korangi");

        List<String> list = new ArrayList<>();

        list.add("Select");
        Collection<String> coll = data.get(district);
        for (String item : coll) {
            list.add(item);
        }


        return list;
      /*  // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
         android.R.layout.simple_spinner_item, list_tehsil);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp_tehsil.setAdapter(dataAdapter);
        sp_tehsil.setSelection(coll_district.size());*/
    }
}
