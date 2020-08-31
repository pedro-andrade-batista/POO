package enumerator;

import java.util.ArrayList;
import java.util.List;

public enum TypeVehicle {
    SEDAN("sedan"),
    HATCH("hatch"),
    MINIVAN("minivan"),
    COUPE("coupe"),
    SPORT("sport"),
    CONVERTIBLE("convertible"),
    SUV("suv");

    private String type;

    TypeVehicle(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
