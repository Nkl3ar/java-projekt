package com.ftutic.itoprema;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Uredaji {

    private static Uredaji instance;

    public Uredaj selectedUredaj;

    public Map<Integer,Uredaj> uredajBaseList; //jer nije moguće serialisati ObservableList

    ObservableList<Uredaj> uredajList;
    ObservableList<Uredaj> filteredUredajList;

    public static Uredaji getInstance() {
        if (instance==null)
        {
            instance=new Uredaji();
        }
        return instance;
    }

    public void getByCijenaBiggerThan(double cijena)
    {

        filteredUredajList = FXCollections.observableArrayList();
        uredajBaseList.entrySet().stream().filter(e1 -> e1.getValue().getCijena() > cijena).sorted((e1,e2)->
                        Double.compare(e1.getValue().getCijena(), e2.getValue().getCijena())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new)).forEach((key, value) -> filteredUredajList.add(value));
        uredajList = filteredUredajList;

    }

    public void getByCijenaSmallerThan(double cijena)
    {
        filteredUredajList = FXCollections.observableArrayList();
        uredajBaseList.entrySet().stream().filter(e1 -> e1.getValue().getCijena() < cijena).sorted((e1,e2)->
                Double.compare(e1.getValue().getCijena(), e2.getValue().getCijena())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new)).forEach((key, value) -> filteredUredajList.add(value));
        uredajList = filteredUredajList;
    }


    public void add(Uredaj u)
    {
        uredajBaseList.put(u.getId(),u);
        serialize();
        updateObservableList();
    }

    public void remove(Uredaj u)
    {
        uredajBaseList.remove(u.getId());
        serialize();
        updateObservableList();
    }

    public void remove(int id)
    {
        uredajBaseList.remove(id);
        serialize();
        updateObservableList();
    }

    public Uredaj get(int id)
    {
        return uredajBaseList.get(id);
    }

    public void update(Uredaj u)
    {
        remove(u.getId());
        add(u);
        serialize();
        updateObservableList();
    }

    public void serialize()
    {
        //koristim built in binary serializer jer je gson bio noćna mora zbog abstraktne klase i private/protected polja
        try (FileOutputStream fos = new FileOutputStream("uredajiData");
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(uredajBaseList); //structura s uredajima
            oos.writeObject(Uredaj.getIdCount());   //trenutacni max id uredaja
            oos.writeObject(genRandom.set); //iskoristeni random brojevi
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public boolean deserialize() {
        try (FileInputStream fis = new FileInputStream("uredajiData");
             ObjectInputStream ois = new ObjectInputStream(fis);) {
            uredajBaseList = (Map<Integer, Uredaj>) ois.readObject();
            Uredaj.setIdCount((int) ois.readObject());
            genRandom.set = (Set<Long>) ois.readObject();
            return true;
        } catch (IOException | ClassNotFoundException ioe) {
            return false;
        }
    }

    public void updateObservableList()
    {
        uredajList = FXCollections.observableArrayList(uredajBaseList.values());
    }




    private Uredaji()
    {
        if(!deserialize())
        {
            uredajBaseList = new HashMap<>();
            for(int i = 0; i<3; i++) {
                for(int x = 0; i<3; i++) {
                    add(genRandom.generateRandomUredaj(x));
                }
            }
            for(int i = 0; i<25; i++) {
                add(genRandom.generateRandomUredaj());
            }
        }
        updateObservableList();
        serialize();
    }





}
