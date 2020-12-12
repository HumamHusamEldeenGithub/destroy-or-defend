package Utilitiy;

import unit.UnitType;

public class UniqueIDGenerator {
    static int[] IdNumber = new int[UnitType.values().length]  ;
    public static String GenerateID(UnitType unitType)
    {
        return unitType.toString()+(UniqueIDGenerator.IdNumber[unitType.ordinal()]++) ;
    }
}
