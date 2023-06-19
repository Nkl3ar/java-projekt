package com.ftutic.itoprema;


public class Monitor extends Uredaj {


    private int dijagonala;


    public Monitor(String ime, double cijena, int dijagonala)
    {
        super(ime,cijena);
        generateSerijskiBroj();
        setDijagonala(dijagonala);

    }
    public Monitor(String ime, double cijena, long serijskiBr, int dijagonala)
    {
        super(ime,cijena);
        setDijagonala(dijagonala);
        generateSerijskiBroj(serijskiBr);

    }
    public void setAll(String ime, double cijena, long serijskiBr, int dijagonala) {
        setDijagonala(dijagonala);
        super.setAll(ime, cijena, serijskiBr);
    }


    public int getDijagonala() {
        return dijagonala;
    }

    public void setDijagonala(int dijagonala) {
        this.dijagonala = dijagonala;
    }

    @Override
    public String toString() {
        return super.toString()+"Dijagonala:"+getDijagonala()+"\n";
    }

    @Override
    void generateSerijskiBroj() {
        serijskiBrUniqueExt =genRandom.generateRandom();
        serijskiBr = getIme()+"-"+getDijagonala()+"-"+serijskiBrUniqueExt;


    }

    @Override
    void generateSerijskiBroj(long SerijskiBr) {
        this.serijskiBr = getIme()+"-"+getDijagonala()+"-"+SerijskiBr;
        genRandom.addNumberToSkip(SerijskiBr);
        serijskiBrUniqueExt=SerijskiBr;
    }

}
