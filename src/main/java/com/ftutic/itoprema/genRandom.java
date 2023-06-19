package com.ftutic.itoprema;

import java.util.*;

public class genRandom {

    protected static Set<Long> set = new HashSet<Long>();

    public static long generateRandom() {
        Random random = new Random();
        char[] digits = new char[12];
        long randNumber;
        do {
            digits[0] = (char) (random.nextInt(9) + '1');
            for (int i = 1; i < 12; i++) {
                digits[i] = (char) (random.nextInt(10) + '0');
            }
            randNumber = Long.parseLong(new String(digits));
        }while (set.contains(randNumber));
        set.add(randNumber);
        return randNumber;
    }

    public static void addNumberToSkip(long num)
    {
        set.add(num);
    }

    public static void removeNumberToSkip(long num)
    {
        set.remove(num);
    }
    public static Uredaj generateRandomUredaj(){
        Random rand = new Random();
        int upperBound = 3;
        return generateRandomUredaj(rand.nextInt(upperBound));
    }

    public static Uredaj generateRandomUredaj(int type)
    {
        Uredaj u;
        Random rand = new Random();
        int upperBound;
        if(type>2 || type<0)//ako je tip van granica, random tip
        {
             upperBound = 2;
            type = rand.nextInt(upperBound);
        }

        StringBuilder randomModel = new StringBuilder();
        upperBound=26;
        for(int i = 0; i<4; i++)//random slova
        {
            char randomModelChar = (char)('A' + rand.nextInt(upperBound));
            randomModel.append(randomModelChar);
        }
        upperBound=10;
        for(int i = 0; i<1; i++)//random brojevi
        {
            randomModel.append(rand.nextInt(upperBound));
        }
        List<String> brands;
        String ime = "";
        double cijena;
        switch (type) {
            case 0: //Monitor
                brands =  Arrays.asList("HP", "Asus","Alienware","Dell","Samsung","AOC");
                upperBound=32;
                int dijagonala = 19+rand.nextInt(upperBound);
                upperBound=1001;
                cijena = 100+rand.nextDouble(upperBound);
                cijena =  Math.floor(cijena * 100) / 100;
                upperBound=6;
                ime = brands.get(rand.nextInt(upperBound))+" "+dijagonala+randomModel;
                u = new Monitor(ime,cijena,dijagonala);
                break;

            case 1://Mis
                brands = Arrays.asList("Logitech","Razer","Corsair","Neki Kinez");
                upperBound=4;
                ime = brands.get(rand.nextInt(upperBound))+" "+randomModel;
                upperBound=201;
                cijena = 10+rand.nextDouble(upperBound);
                cijena =  Math.floor(cijena * 100) / 100;
                List<Integer> DPI = Arrays.asList(200,400,800,1000,2000,4000,8000,10000,16000);
                upperBound=9;

                int DPIrand = DPI.get(rand.nextInt(upperBound));
                List<String> Sensors = Arrays.asList("PMW 3360","PAW 3333","AM010","PMW 3389","PMW 3310","TrueMove3+","SDNS 3988","Finalsensor");
                upperBound=8;
                String SensRand = Sensors.get(rand.nextInt(upperBound));
                u = new Mis(ime,cijena,DPIrand,SensRand);
                break;

            default: //tipkovnica

                brands = Arrays.asList("Logitech","Razer","Corsair","Neki Kinez");
                upperBound=4;
                ime = brands.get(rand.nextInt(upperBound))+" "+randomModel;
                upperBound=551;
                cijena = 50+rand.nextDouble(upperBound);
                cijena =  Math.floor(cijena * 100) / 100;
                upperBound=22;
                List<Integer> keycount = Arrays.asList(104,105,104,105,104,105,104,105,104,105,104,105,94,95,78,79,78,79,68,69,42,43);
                upperBound=22;
                Integer rankeycount = keycount.get(rand.nextInt(upperBound));
                u = new Tipkovnica(ime,cijena,rankeycount);
                break;
        }
        return u;
    }


}
