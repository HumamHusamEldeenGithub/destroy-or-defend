package unitProperty;

import unit.Unit;

public class UnitProperty {
    protected double Value;
    UnitProperty(double vl){
        Value = vl;
    }
    public double GetValue(){
        return Value;
    }
    public void SetValue(double value){ this.Value=value  ; }
}
