package unit;

import Utilities.CSVReader;
import gameManager.DoDGameManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;

public class  UnitFactory {
    HashMap<UnitType,String[]>UnitsInfo = new HashMap<UnitType,String[]>() ;

    public UnitFactory() throws FileNotFoundException {
        this.LoadData();
    }

    public Unit CreateUnit(UnitType unitType) {
        String[] info = UnitsInfo.get(unitType) ;
        if (info==null)
            return null ;
        int MaxHealth = 0,AttackDamage=0 , AttackRange=0 , Size=0 , Movement=0 , UnitPrice ;
        double Armor = 0, AttackFrequency = 0;
        String[] CanTarget = new String[0];
        for (int i =0 ; i<info.length ; i++) {
            switch (i) {
                case 0:
                    MaxHealth = Integer.parseInt(info[i]);
                    break;
                case 1:
                    Armor = Double.parseDouble(info[i]);
                    break;
                case 2:
                    AttackDamage = Integer.parseInt(info[i]);
                    break;
                case 3:
                    AttackRange = Integer.parseInt(info[i]);
                    break;
                case 4:
                    AttackFrequency = Double.parseDouble(info[i]);
                    break;
                case 5:
                    Size = Integer.parseInt(info[i]);
                    break;
                case 6:
                    Movement = Integer.parseInt(info[i]);
                    break;
                case 7:
                    CanTarget = info[i].split("-");
                    break;
                case 8:
                    UnitPrice = Integer.parseInt(info[i]);
                    break;
                default:
                    break;

            }
        }
        //return new Unit(MaxHealth,Armor,AttackRange,AttackFrequency,Size,Movement,CanTarget) ;
        return new Unit() ;
    }


    public void LoadData () throws FileNotFoundException {
    CSVReader Reader = new CSVReader("UnitProperties.txt") ;
    String[] Line = Reader.ReadLine() ;
    while (Line !=null)
        {
            Line = Reader.ReadLine() ;
            for (String s: Line) {
                System.out.println(s);
            }
            UnitType type = getType(Line[1]) ;
            UnitsInfo.put(type ,Arrays.copyOfRange(Line ,2 , Line.length) )  ;
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
    public int getPrice (UnitType unit)
    {
        String[] info = UnitsInfo.get(unit) ;
        return Integer.parseInt(info[info.length-1]) ;
    }
}
