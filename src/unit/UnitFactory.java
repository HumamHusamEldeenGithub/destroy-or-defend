package unit;

import gameManager.DoDGameManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class  UnitFactory {
    HashMap<UnitType,String>UnitsInfo = new HashMap<UnitType,String>() ;

    public UnitFactory() throws FileNotFoundException {
        this.LoadData();
    }

    public Unit CreateUnit(UnitType unitType) {
        String Line = UnitsInfo.get(unitType) ;
        if (Line==null)
            return null ;
        String[] info = Line.split(",") ;
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
        File file = new File("UnitProperties.csv") ;
        Scanner scanner = new Scanner(file) ;
        while (scanner.hasNextLine())
        {
            String Line = scanner.nextLine() ;
            UnitType type = getType(Line.split(",")[1]) ;
            UnitsInfo.put(type ,Line.substring(2) )  ;
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
        String[] info = UnitsInfo.get(unit).split(",") ;
        return Integer.parseInt(info[info.length-1]) ;
    }
}
