package Units;

import Enums.TeamType;
import Enums.UnitType;
import Interfaces.IDestroyable;
import Interfaces.IGetAttributes;
import Tactics.Tactic;
import Utilities.CSVReader;
import Utilities.Position;

public abstract class Unit implements IDestroyable , IGetAttributes {
    // Attributes
    UnitType type;
    int hp;
    int armor;
    int damage;
    int attackspeed;
    int range;
    boolean canMove;
    Position position;
    TeamType team;
    Tactic PlayerTactic ;
    public Position GetPos(){
        return position;
    }

    public UnitType GetType(){
        return type;
    }
    public void SetPos(Position position){
        this.position = position;
    }

    @Override
    public void GetDamage(int Damage) {
        hp-=damage;
        if(hp<=0)
            Destroy();
    }

    @Override
    public void Initialize() {
        boolean Initialized = false;
        CSVReader csvReader = new CSVReader("src/Info.csv");
        String[] info;
        csvReader.ReadLine();
        while (!Initialized){
            info = csvReader.ReadLine();
            try {
                if (type == UnitType.valueOf(info[0])) {
                    try {
                        hp = Integer.parseInt(info[1]);
                        armor = Integer.parseInt(info[2]);
                        damage = Integer.parseInt(info[3]);
                        attackspeed = Integer.parseInt(info[4]);
                        range = Integer.parseInt(info[5]);
                        canMove = Boolean.parseBoolean(info[6]);
                        Initialized = true;
                    } catch (Exception e) {
                        System.err.println("Error initializing " + type.toString());
                    }
                }
            }
            catch (Exception e){
                System.err.println("EnumType error in file");
                break;
            }
        }
        csvReader.Close();
    }
}
