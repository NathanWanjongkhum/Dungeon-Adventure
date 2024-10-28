package team5.game.model;

public interface DungeonCharacter {
    String getName();

    int getHealth();

    int getSpeed();

    void setHealth(final int theHealth);

    void setDamage(final int theDamage);

    void setSpeed(final int theSpeed);
}
