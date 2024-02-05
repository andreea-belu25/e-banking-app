package org.poo.cb;

public interface ICSVReader {
    public void accept(ICSVVisitor visitor, String path);
}
