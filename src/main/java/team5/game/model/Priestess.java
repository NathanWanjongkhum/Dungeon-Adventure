package team5.game.model;

public class Priestess extends Hero {
    private static final int TURNS = 1;
    public Priestess(String theName) {
        super(theName, 100, 20, 25, new SpecialAttack(TURNS, 5));
    }
    public int useSpecialAttack(DungeonCharacter theOther) {
        if(this.getSpecialAttack().getTurns() == 0) {
            heal(getSpecialAttack().getDamage());
            getStatusEffects().setRegenDuration(2);
            getStatusEffects().setRegenAmount(getSpecialAttack().getDamage());
            this.getSpecialAttack().setTurns(this.getSpecialAttack().getTurns() - 1);
        }
        return 0;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("Special Move: Charges for 1 turn and heals 5 HP " + 
                        "then heals an additional 5 HP for 2 turns.\n");
        return builder.toString();
    }
    public void resetTurns() {
        getSpecialAttack().setTurns(TURNS);
    }
    public String specialAttackText() {
        return String.format("%s casted a healing and restored %d HP!\n", 
                                getName(), getSpecialAttack().getDamage());
    }
}