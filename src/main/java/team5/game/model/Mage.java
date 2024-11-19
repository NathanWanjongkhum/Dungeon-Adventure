package team5.game.model;

public class Mage extends Hero {
    private static final int TURNS = 2;
    public Mage(String theName) {
        super(theName, 250, 50, 10, new SpecialAttack(TURNS, 300));
    }
    public int useSpecialAttack(DungeonCharacter theOther) {
        if (this.getSpecialAttack().getTurns() < 0) {
        }
        int damage = 0;
        if(this.getSpecialAttack().getTurns() == 0) {
            damage = this.getSpecialAttack().getDamage();
            damage = super.checkVulnerableDamage(theOther, damage);
            theOther.setHealth(theOther.getHealth() - damage);
            this.getSpecialAttack().setTurns(this.getSpecialAttack().getTurns() - 1);
        } else {
            this.getStatusEffects().setVulnerable(true);
            this.getStatusEffects().setVulnerableDuration(this.getSpecialAttack().getTurns());
        }
        return damage;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("Special Move: Takes double damage for 2 turns, then does massive damage to enemies!\n");
        return builder.toString();
    }
    @Override
    public void resetTurns() {
        getSpecialAttack().setTurns(TURNS);
    }
    public String specialAttackText() {
        return "\nUnleashed their magic!\n";
    }
}