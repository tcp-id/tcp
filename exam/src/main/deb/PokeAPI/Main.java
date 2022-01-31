package main.deb.PokeAPI;

import java.lang.reflect.Field;
import java.util.List;

class Result{
    public List<Pokemon> documents;
}

class Pokemon{
    public String name;
    public Field fields;
    public String createTime;
    public String updateTime;
}

class Fields{
    public StringValue imagen, nombre, elemento;
}

class StringValue{
    public String stringValue;
}

public class Main {
}
