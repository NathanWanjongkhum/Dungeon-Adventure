package team5.game.model;

public interface Consumable extends Item{
        public int useItem(DungeonCharacter theCharacter);
        public String getDescription();        
}
