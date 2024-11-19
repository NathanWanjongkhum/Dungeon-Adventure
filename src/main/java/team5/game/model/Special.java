package team5.game.model;

public interface Special{

    public SpecialAttack getSpecialAttack();

    public int useSpecialAttack(DungeonCharacter theOther);

    public String specialAttackText();

    public void resetTurns();

}
