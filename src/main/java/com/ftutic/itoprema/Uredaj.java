package com.ftutic.itoprema;


import java.io.Serializable;
import java.text.DecimalFormat;

abstract public class Uredaj implements Serializable, Comparable<Uredaj> {


    protected String ime;
    protected String serijskiBr;

    protected long serijskiBrUniqueExt;
    protected double cijena;
    protected static int idCount = 1;
    protected int id;

    private static final DecimalFormat cijenaFormat = new DecimalFormat("0.00");

    @Override
    public int compareTo(Uredaj o) {
        return (int)(getCijena()-o.getCijena());
    }

    public Uredaj(String ime, double cijena)
    {
        id = idCount;
        idCount++;
        this.ime = ime;
        this.cijena = cijena;

    }


    public void setAll(String ime, double cijena, long serijskiBr){
        setIme(ime);
        setCijena(cijena);
        replaceSerijskiBr(serijskiBr);
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setId(int id) {
        this.id = id;
    }


    public long getSerijskiBrUniqueExt() {
        return serijskiBrUniqueExt;
    }

    public int getId() {
        return id;
    }

    public String getSerijskiBr() {
        return serijskiBr;
    }

    public String getIme() {
        return ime;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {

        if(cijena<0)
        {
            cijena=0;
        }
        this.cijena = (Math.floor(cijena * 100) / 100);
    }

    @Override
    public String toString() {
        return "Ime:"+getIme()+"\nCijena:"+cijenaFormat.format(getCijena())+"€\nSerijski Broj:"+getSerijskiBr()+"\n";

    }



    abstract void generateSerijskiBroj();
    abstract void generateSerijskiBroj(long serijskiBr);
    public void replaceSerijskiBr(long serijskiBr){

        genRandom.removeNumberToSkip(serijskiBrUniqueExt);
        generateSerijskiBroj(serijskiBr);

    }




    protected static void setIdCount(int id)
    {
        if(id>idCount)
            idCount=id;
    }

    public static int getIdCount() {
        return idCount;
    }
}
