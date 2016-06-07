package BluebellAdventures;

import java.io.File;
import java.io.IOException;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.Snack;
import BluebellAdventures.Characters.Enemy;

public class CharacterDriver {
	public static void main(String[] args) {
		try {
			String filename = "resource/image/machi1.png";

			Snack snack = new Snack(filename).setScore(110);
			System.out.println(snack.getScore());

			Enemy enemy = new Enemy(filename).setAttack(10)
												.setDetectionRange(120)
												.setSpeed(100);
			System.out.println(enemy.getAttack());
			System.out.println(enemy.getDetectionRange());
			System.out.println(enemy.getSpeed());

			Character character = new Character(filename).setHp(100)
															.setMp(100)
															.setChargeBar(10)
															.setLives(3)
															.setSpeed(100)
															.setUnlockSpeed(100)
															.setDetectionRange(100)
															.setAttackScore(0)
															.setSnackScore(0);
			System.out.println(character.getHp());
			System.out.println(character.getMp());
			System.out.println(character.getChargeBar());
			System.out.println(character.getLives());
			System.out.println(character.getSpeed());
			System.out.println(character.getUnlockSpeed());
			System.out.println(character.getDetectionRange());
			System.out.println(character.getAttackScore());
			System.out.println(character.getSnackScore());
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
