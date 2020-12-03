package unit;

import Utilitiy.CSVReader;
import movement.AttackerMovement;
import movement.DefenderMovement;
import movement.Movement;
import player.PlayerType;
import unitProperty.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class  UnitFactory {
    HashMap<UnitType,String[]>UnitsInfo = new HashMap<UnitType,String[]>() ;

    public UnitFactory() throws FileNotFoundException {
        this.LoadData();
    }
    public synchronized Unit CreateUnit(UnitType unitType , PlayerType playerType) throws InterruptedException {
        String[] info = UnitsInfo.get(unitType) ;
        if (info==null)
            return null ;
        double MaxHealth = 0,AttackDamage=0 , AttackRange=0 , Size=0 , Movement=0 , UnitPrice ;
        double Armor = 0, AttackFrequency = 0;
        String[] CanTarget = new String[0];
        for (int i =0 ; i<info.length ; i++) {
            switch (i) {
                case 0:
                    MaxHealth = Double.parseDouble(info[i]);
                    break;
                case 1:
                    Armor = Double.parseDouble(info[i]);
                    break;
                case 2:
                    AttackDamage = Double.parseDouble(info[i]);
                    break;
                case 3:
                    AttackRange = Double.parseDouble(info[i]);
                    break;
                case 4:
                    AttackFrequency = Double.parseDouble(info[i]);
                    break;
                case 5:
                    Size = Double.parseDouble(info[i]);
                    break;
                case 6:
                    Movement = Double.parseDouble(info[i]);
                    break;
                case 7:
                    CanTarget = info[i].split("-");
                    break;
                case 8:
                    UnitPrice = Double.parseDouble(info[i]);
                    break;
                default:
                    break;

            }
        }
        ArrayList<UnitProperty> properties = new ArrayList<>() ;
        HealthUnitProperty healthUnitProperty = new HealthUnitProperty() ; healthUnitProperty.SetValue(MaxHealth) ;
        ArmorUnitProperty armorUnitProperty = new ArmorUnitProperty() ; armorUnitProperty.SetValue(Armor);
        RangeUnitProperty rangeUnitProperty = new RangeUnitProperty() ; rangeUnitProperty.SetValue(AttackRange);
        AttackSpeedUnitProperty attackSpeedUnitProperty = new AttackSpeedUnitProperty() ; attackSpeedUnitProperty.SetValue(AttackFrequency);
        SizeUnitProperty sizeUnitProperty = new SizeUnitProperty() ; sizeUnitProperty.SetValue(Size);
        properties.add(healthUnitProperty) ;
        properties.add(armorUnitProperty) ;
        properties.add(rangeUnitProperty) ;
        properties.add(attackSpeedUnitProperty) ;
        properties.add(sizeUnitProperty) ;
        Thread.sleep(100);
        return new Unit(unitType,properties, (playerType.toString().contains("Attacker")? AttackerMovement.getObj(): DefenderMovement.getObj()) , getTypes(CanTarget));
    }


    public void LoadData () throws FileNotFoundException {
    CSVReader Reader = new CSVReader("UnitProperties.txt") ;
    String[] Line = Reader.ReadLine() ;
    while (Line !=null)
        {
            try {
                Line = Reader.ReadLine();
                UnitType type = getType(Line[1]);
                UnitsInfo.put(type, Arrays.copyOfRange(Line, 2, Line.length));
            } catch (Exception e){

            }
        }
    }

    public UnitType getType (String type)
    {
        for (int i =0 ; i<UnitType.values().length;i++)
        {
            if  (UnitType.values()[i].toString().contains(type))
                return UnitType.values()[i] ;
        }
        return null ;
    }
    public double getPrice (UnitType unit)
    {
        String[] info = UnitsInfo.get(unit) ;
        return Double.parseDouble(info[info.length-1]) ;
    }
    ArrayList<UnitType> getTypes (String[] CanTarget)
    {
        ArrayList<UnitType> list = new ArrayList<>();
        for (String s : CanTarget)
            list.add(getType(s)) ;
        return list ;
    }
}
