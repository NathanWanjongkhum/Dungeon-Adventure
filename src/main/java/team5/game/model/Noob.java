package team5.game.model;

public class Noob extends Hero{
    protected Noob(final String theName) {
        super(theName, 1, 0, 1, new SpecialAttack(0, 0));
    }
    @Override
    public int useSpecialAttack(final DungeonCharacter theOther) {
        int damage = this.getSpecialAttack().getDamage();
        return damage;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("\nSpecial Move: Does nothing\n");
        return builder.toString();
    }
    @Override
    public String specialAttackText() {
        return "Awaiting death\n";
    }
}
