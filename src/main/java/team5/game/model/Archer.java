package team5.game.model;


public class Archer extends Hero {
    public Archer(String theName) {
        super(theName, 150, 10, 20, new SpecialAttack(0, 5));
    }
    public int useSpecialAttack(DungeonCharacter theOther) {
        int damage = 0;
        if(getSpecialAttack().getTurns() == 0) {
            theOther.getStatusEffects().setVulnerableDuration(2);
            damage = getSpecialAttack().getDamage();
            damage = super.checkVulnerableDamage(theOther, damage);
            theOther.setHealth(theOther.getHealth() - damage);
        } else {
            getSpecialAttack().setTurns(getSpecialAttack().getTurns() - 1);
        }
        return damage;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("Special Move: Does low damage, but makes " + 
                        "enemies take double damage on the next basic attack\n");
        return builder.toString();
    }
    public String specialAttackText() {
        return "Vulnerabled the enemy for the next normal attack!\n";
    }
}