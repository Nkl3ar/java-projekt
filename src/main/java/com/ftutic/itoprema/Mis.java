package com.ftutic.itoprema;

public class Mis extends Uredaj {


    private int DPI;
    private String imeSenzora;



    public Mis(String ime, double cijena, int DPI, String imeSenzora)
    {
        super(ime,cijena);
        this.DPI = DPI;
        this.imeSenzora = imeSenzora;
        generateSerijskiBroj();

    }
    public Mis(String ime, double cijena, long serijskiBr, int DPI, String imeSenzora)
    {
        super(ime,cijena);
        this.DPI = DPI;
        this.imeSenzora = imeSenzora;
        generateSerijskiBroj(serijskiBr);

    }




    public void setAll(String ime, double cijena, long serijskiBr, int DPI, String imeSenzora) {
        setImeSenzora(imeSenzora);
        setDPI(DPI);
        super.setAll(ime, cijena, serijskiBr);
    }


    public void setDPI(int DPI) {
        this.DPI = DPI;
    }

    public int getDPI() {
        return DPI;
    }

    public void setImeSenzora(String imeSenzora) {
        this.imeSenzora = imeSenzora;
    }

    public String getImeSenzora() {
        return imeSenzora;
    }

    @Override
    public String toString() {
        return super.toString()+"Ime senzora:"+getImeSenzora()+"\nDPI:"+getDPI()+"\n";
    }

    @Override
    void generateSerijskiBroj() {
        serijskiBrUniqueExt = (genRandom.generateRandom());
        String serijski = getIme()+"-"+getImeSenzora()+"-"+getDPI()+"-"+serijskiBrUniqueExt;
        serijskiBr = (serijski);


    }


    @Override
    void generateSerijskiBroj(long SerijskiBr) {
        String serijski = getIme()+"-"+getImeSenzora()+"-"+getDPI()+"-"+SerijskiBr;
        serijskiBr = (serijski);
        genRandom.addNumberToSkip(SerijskiBr);
        serijskiBrUniqueExt = (SerijskiBr);

    }

}
