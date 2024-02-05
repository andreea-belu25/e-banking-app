package org.poo.cb;

import java.util.ArrayList;

public class PortofolioFactory {
    private static PortofolioFactory portofolioFactory;
    private PortofolioFactory () {}
    public static PortofolioFactory InstantaPortofolioFactory() {
        if (portofolioFactory == null)
            portofolioFactory = new PortofolioFactory();
        return portofolioFactory;
    }

    public ObjectPortofolio createPortofolioObject(String type, ArrayList<Object> params) {
        if (type.equals(String.valueOf(ObjectPortofolio.Type.ACTION)))
            return new Action((String) params.get(0), (Integer) params.get(1));

        //  vreau in fata lui currency sa pun un diez pentru a face distinctia intre action in eur si account in eur
        return new Account((String) params.get(0), (Double) params.get(1));
    }
}
