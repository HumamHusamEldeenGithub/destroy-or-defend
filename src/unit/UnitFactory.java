package unit;

import Strategies.AttackStrategy;
import Utilitiy.CSVReader;
import Utilitiy.PathFinder;
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
    static HashMap<UnitType,String[]>UnitsInfo = new HashMap<UnitType,String[]>() ;
    static UnitFactory unitFactory;
    public static UnitFactory GetObj(){
        synchronized (UnitFactory.class)
        {
            if (UnitFactory.unitFactory==null)
                unitFactory = new UnitFactory() ;
        }
        return unitFactory;
    }
    UnitFactory() {
        LoadData();
    }
    public static synchronized Unit CreateUnit(UnitType unitType , PlayerType playerType , AttackStrategy strategy) {
        String[] info = UnitsInfo.get(unitType) ;
        if (info==null)
            return null ;
        double MaxHealth = 0,AttackDamage=0 , AttackRange=0 , Size=0 , Movement=0 , UnitPrice = 0;
        double Armor = 0, AttackFrequency = 0;
        String[] CanTarget = new String[0];
        for (int i =0 ; i<info.length ; i++) {
            try{
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
            } catch (Exception e){
                System.err.println("Parsing error");
            }
        }
        ArrayList<UnitProperty> properties = new ArrayList<>() ;
        properties.add(new HealthUnitProperty(MaxHealth)) ;
        properties.add(new ArmorUnitProperty(Armor)) ;
        properties.add(new RangeUnitProperty(AttackRange)) ;
        properties.add(new AttackSpeedUnitProperty(AttackFrequency)) ;
        properties.add(new SizeUnitProperty(Size)) ;
        properties.add(new PriceUnitProperty(UnitPrice));
        try {
            Thread.sleep(100);
        }catch (Exception e){

        }
        return new Unit(unitType,properties, (playerType.toString().contains("Attacker")? AttackerMovement.getObj(): DefenderMovement.getObj()) , getTypes(CanTarget) , strategy);
    }


    public static void LoadData () {
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

    public static UnitType getType (String type)
    {
        for (int i =0 ; i<UnitType.values().length;i++)
        {
            if  (UnitType.values()[i].toString().contains(type))
                return UnitType.values()[i] ;
        }
        return null ;
    }

    public static double getPrice (UnitType unit)
    {
        String[] info = UnitsInfo.get(unit) ;
        return Double.parseDouble(info[info.length-1]) ;
    }

    static ArrayList<UnitType> getTypes (String[] CanTarget)
    {
        ArrayList<UnitType> list = new ArrayList<>();
        for (String s : CanTarget)
            list.add(getType(s)) ;
        return list ;
    }

    public static HashMap<UnitType,String[]> GetUnitsInfo(){
        return UnitsInfo;
    }
}
