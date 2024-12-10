package team5.game.model;

public class Admin extends Hero {

    public Admin(final String theName) {
        super(theName, 100000, 10000, 20000, new SpecialAttack(0, 0));
    }
    @Override
    public int useSpecialAttack(final DungeonCharacter theOther) {
        int damage = theOther.getMaxHealth();
        theOther.getStatusEffects().setVulnerableDuration(10);
        damage = this.checkVulnerableDamage(theOther, damage);
        theOther.setHealth(theOther.getHealth() - damage);
        return damage;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("\nSpecial Move: Instakills\n");
        return builder.toString();
    }
    @Override
    public String specialAttackText() {
        return "Death\n";
    }
}
