package team5.game.model;

public class Priestess extends Hero {
    public Priestess(String theName) {
        super(theName, 100, 20, 25, new SpecialAttack(0, 5));
    }
    public int useSpecialAttack(DungeonCharacter theOther) {
        heal(getSpecialAttack().getDamage());
        getStatusEffects().setRegen(true);
        getStatusEffects().setRegenDuration(2);
        return 0;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("\nSpecial Move: Heals 5 HP " + 
                        "then heals an additional 5 HP for 2 turns");
        return builder.toString();
    }
}