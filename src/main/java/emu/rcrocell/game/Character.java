package emu.rcrocell.game;

public class Character {
    public int id;
    public int accountId;
    public String name;
    public int classId;
    public int baseLevel;
    public int jobLevel;

    // Stats
    public int strength;
    public int agility;
    public int vitality;
    public int intelligence;
    public int dexterity;
    public int luck;
    public int hp;
    public int hpMax;
    public int sp;
    public int spMax;

    // Localization
    public String lastMap;
    public int lastX;
    public int lastY;
    public String saveMap;
    public int saveX;
    public int saveY;
}
