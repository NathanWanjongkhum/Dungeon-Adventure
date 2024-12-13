package team5.game.model;

/**
 * Consumable is an item that can be used by the player. Havomg quantity and
 * description. Unlike generic items which do not can not stack in one inventory
 * slot.
 */
public interface Consumable extends Item {
        /**
         * Use the consumable on the character
         * 
         * @param theCharacter the character to use the consumable on
         * @return the damage dealt
         */
        public int useItem(DungeonCharacter theCharacter);

        /**
         * Gets the description of the consumable
         * 
         * @return the description of the consumable
         */
        public String getDescription();
}
