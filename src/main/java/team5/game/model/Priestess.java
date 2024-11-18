package team5.game.model;

public class Priestess extends Hero {
    public Priestess(String theName) {
        super(theName, 100, 20, 25, new SpecialAttack(1, 5));
    }
    public int useSpecialAttack(DungeonCharacter theOther) {
        if (this.getSpecialAttack().getTurns() < 0) {
            this.getSpecialAttack().setTurns(1);
        }
        if(this.getSpecialAttack().getTurns() == 0) {
            heal(getSpecialAttack().getDamage());
            getStatusEffects().setRegen(true);
            getStatusEffects().setRegenDuration(2);
            this.getSpecialAttack().setTurns(this.getSpecialAttack().getTurns() - 1);
        }
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