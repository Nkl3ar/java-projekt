package com.ftutic.itoprema;

public class Tipkovnica extends Uredaj {


    protected int brojTipki;



    public Tipkovnica(String ime, double cijena, int brojTipki)
    {
        super(ime,cijena);
        this.brojTipki = brojTipki;
        generateSerijskiBroj();

    }
    public Tipkovnica(String ime, double cijena, long serijskiBr, int brojTipki)
    {
        super(ime,cijena);
        this.brojTipki = brojTipki;
        generateSerijskiBroj(serijskiBr);

    }




    public void setAll(String ime, double cijena, long serijskiBr, int brojTipki) {
        setBrojTipki(brojTipki);
        super.setAll(ime, cijena, serijskiBr);
    }

    public void setBrojTipki(int brojTipki) {
        this.brojTipki = brojTipki;
    }

    public int getBrojTipki() {
        return brojTipki;
    }

    @Override
    public String toString() {
        return super.toString()+"Broj Tipki:"+getBrojTipki()+"\n";
    }

    @Override
    void generateSerijskiBroj() {
        serijskiBrUniqueExt = (genRandom.generateRandom());
        serijskiBr = getIme()+"-"+getBrojTipki()+"-"+serijskiBrUniqueExt;

    }

    @Override
    void generateSerijskiBroj(long SerijskiBr) {
        serijskiBr = getIme()+"-"+getBrojTipki()+"-"+SerijskiBr;
        genRandom.addNumberToSkip(SerijskiBr);
        serijskiBrUniqueExt = SerijskiBr;

    }

}
