package com.example.nassimnassiim.quran;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by NassimNassiim on 26/06/15.
 */
public class DataListe {

        private static String[] sourateListe = {
                "",
                "الفاتحة", "البقرة", "آل عمران", "النساء", "المائدة", "الأنعام", "الأعراف", "الأنفال",
                "التوب", "يونس", "هود", "يوسف", "الرعد", "إبراهيم", "الحجر", "النحل", "الإسراء",
                "الكهف", "مريم", "طه", "الأنبياء", "الحجّ", "المؤمنون", "النور", "الفرقان", "الشعراء",
                "النمل", "القصص", "العنكبوت", "الروم", "لقمان", "السجدة", "الأحزاب", "سبأ", "فاطر",
                "يس", "الصافـّات", "ص", "الزمر", "غافر", "فصّلت", "الشورى", "الزخرف", "الدخان", "الجاثية",
                "الأحقاف", "محمّد", "الفتح", "الحجرات", "ق", "الذاريات", "الطور", "النجم", "القمر", "الرحمن",
                "الواقعة", "الحديد", "المجادلة", "الحشر", "الممتحنة", "الصفّ", "الجمعة", "المنافقون", "التغابن",
                "الطلاق", "التحريم", "الملك", "القلم", "الحاقـّة", "المعارج", "نوح", "الجنّ", "المزّمّل", "المدّثـّر",
                "القيامة", "الإنسان", "المرسلات", "النبأ", "النازعات", "عبس", "التكوير", "الانفطار", "المطفّفين",
                "الانشقاق", "البروج", "الطارق", "الأعلى", "الغاشية", "الفجر", "البلد", "الشمس", "الليل", "الضحى",
                "الشرح", "التين", "العلق", "االقدر", "البيّنة", "الزلزلة", "العاديات", "القارعة", "التكاثر", "العصر",
                "الهمزة", "الفيل", "قريش", "الماعون", "الكوثر", "الكافرون", "النص", "المسد", "الإخلاص", "الفلق", "الناس"
        };


         public static ArrayList<HashMap<String, String>> getListeItem() {

                ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> map;
                String url;
                for (int i = 1; i < sourateListe.length; i++) {
                        map = new HashMap<String, String>();
                        map.put("img", String.valueOf(R.drawable.icon));
                        map = new HashMap<String, String>();
                        map.put("description", sourateListe[i]);
                        if (String.valueOf(i).length() == 1) {
                                map.put("url", "00" + String.valueOf(i));
                        } else if (String.valueOf(i).length() == 2) {
                                map.put("url", "0" + String.valueOf(i));
                        } else {
                                map.put("url", String.valueOf(i));
                        }
                        listItem.add(map);
                }
                 System.out.println("/////////////////"+sourateListe.length+"////////////////");
                return listItem;
        }

        public static String getSourateAtIndex(int index) {

                if (index > 0 && index < sourateListe.length)
                        return sourateListe[index];
                else
                        return "";
        }

        public static int getnbrSourate(){
                return sourateListe.length-1;
        }
}