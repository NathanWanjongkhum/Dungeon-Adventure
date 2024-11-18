package team5.game.model;

public class Mage extends Hero {
    public Mage(String theName) {
        super(theName, 250, 50, 10, new SpecialAttack(2, 200));
    }
    public int useSpecialAttack(DungeonCharacter theOther) {
        if (this.getSpecialAttack().getTurns() < 0) {
            this.getSpecialAttack().setTurns(2);
        }
        int damage = 0;
        if(this.getSpecialAttack().getTurns() == 0) {
            damage = this.getSpecialAttack().getDamage();
            theOther.setHealth(theOther.getHealth() - damage);
            System.out.println(this.getSpecialAttack().getTurns());
            this.getSpecialAttack().setTurns(this.getSpecialAttack().getTurns() - 1);
        } else {
            this.getStatusEffects().setVulnerable(true);
            this.getStatusEffects().setVulnerableDuration(this.getSpecialAttack().getTurns());
            // this.getSpecialAttack().setTurns(this.getSpecialAttack().getTurns() - 1);
        }
        return damage;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("\nSpecial Move: Takes double damage for 2 turns, then does massive damage to enemies");
        return builder.toString();
    }
}