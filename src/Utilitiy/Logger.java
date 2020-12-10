package Utilitiy;

import unit.Unit;
import unit.UnitType;

public class Logger {

    static Logger logger;
    Logger(){

    }
    public synchronized static Logger GetObj (){
        if (Logger.logger==null)
        {
            synchronized (Logger.class)
            {
                if (Logger.logger==null)
                    logger = new Logger() ;
            }
        }
        return Logger.logger;
    }
    public static void  Movelog(Position position, UnitType type)
    {
        System.out.println("Unit " + type +"is in " + position);

    }
    public static void Damagelog(Unit attacker , Unit enemy, double damage){
        System.out.println(attacker.GetType()+" at "+ attacker.GetPosition() +
                " has attacked " + enemy.GetType() + " at " + attacker.GetPosition());

    }
    public static void  Deadlog(Unit unit){
        System.out.println(unit.GetType()+"at"+unit.GetPosition()+"has died");
    }

}
