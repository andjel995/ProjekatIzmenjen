/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Angazovanje;
import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import domen.Predmet;
import domen.Profesor;
import domen.RezultatCuvanja;
import domen.TipNastave;
import java.util.ArrayList;
import java.util.List;
import servisImplementacija.KorisnickiServis;
import so.OpstaSistemskaOperacija;
import so.angazovanje.ObrisiAngazovanje;
import so.angazovanje.SacuvajAngazovanje;
import so.angazovanje.VratiListuAngazovanja;
import so.predmet.DajAngazovanjaZaPredmet;
import so.predmet.NadjiPredmetPoId;
import so.predmet.VratiListuPredmeta;
import so.profesor.VratiListuProfesora;


/**
 *
 * @author User
 */
public class Kontroler {
    private static Kontroler instanca;
    private final KorisnickiServis korisnickiServis;

    private Kontroler() {
        korisnickiServis = new KorisnickiServis();
    }
    
   public static Kontroler dajInstancu()
   {
     if(instanca == null)
         instanca = new Kontroler();
     return instanca;
   }
    
   public Korisnik prijava(String korisnickoIme, String lozinka) throws Exception
   {  
      return korisnickiServis.pronadjiPoKorImenuiLozinci(korisnickoIme, lozinka);     
   }

    public List<OpstiDomenskiObjekat> dajPredmete() throws Exception {
        OpstaSistemskaOperacija op = new VratiListuPredmeta();
        op.izvrsiOperaciju(new Predmet());
         return ((VratiListuPredmeta)op).vratiListu();
    }

    public List<OpstiDomenskiObjekat> dajListuProfesora() throws Exception {
        OpstaSistemskaOperacija op = new VratiListuProfesora();
        op.izvrsiOperaciju(new Profesor());
        return ((VratiListuProfesora)op).vratiListu();
    }

    public List<OpstiDomenskiObjekat> dajAngazovanja() throws Exception {
        OpstaSistemskaOperacija op = new VratiListuAngazovanja();
        op.izvrsiOperaciju(new Angazovanje());
        return ((VratiListuAngazovanja)op).vratiListu();
    }


    public void sacuvajAngazovanje(Angazovanje a) throws Exception 
    {
        OpstaSistemskaOperacija so=new SacuvajAngazovanje();
        so.izvrsiOperaciju(a);
    }

    public List<OpstiDomenskiObjekat> dajAngazovanjaZaOdredjeniPredmet(Long predmetId) throws Exception {       
        OpstaSistemskaOperacija so = new DajAngazovanjaZaPredmet();
        Predmet p = new Predmet();
        p.setPredmetId(predmetId);
        so.izvrsiOperaciju(p);
        return ((VratiListuAngazovanja)so).vratiListu();
    }

    public Predmet nadjiPredmetPoId(Long predmetId) throws Exception 
    {
        OpstaSistemskaOperacija so = new NadjiPredmetPoId();
        Predmet p = new Predmet();
        p.setPredmetId(predmetId);
        so.izvrsiOperaciju(p);
        return ((NadjiPredmetPoId)so).vratiPredmet();
    }
    
    public void obrisiAngazovanje(Angazovanje a) throws Exception
    {
         OpstaSistemskaOperacija so = new  ObrisiAngazovanje();
         so.izvrsiOperaciju(a);      
    }
}
