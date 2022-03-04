package configuration;
import java.util.Hashtable;

public class ConstantTexts {
	public final static Hashtable<String, String> GeneralTexts = new Hashtable<String, String>();
	
	static
	{
		GeneralTexts.put("titleMainMenu", "Bienvenue sur BattleShip !");
		GeneralTexts.put("startLocal", "Contre l'ordinateur");
		GeneralTexts.put("startMultiplayer", "Multijoueur");
		GeneralTexts.put("howToPlay", "Comment jouer ?");
		GeneralTexts.put("quit", "Quitter");
		
		GeneralTexts.put("choiceDifficulty", "Choisissez votre difficulté : ");
		GeneralTexts.put("easy", "Débutant");
		GeneralTexts.put("challenger", "Challenger");
		
		GeneralTexts.put("soundEffectOff", "Sound Effect : Off");
		GeneralTexts.put("soundEffectOn", "Sound Effect : On");
		
		GeneralTexts.put("soundGameOff", "Sound Game : Off");
		GeneralTexts.put("soundGameOn", "Sound Game : On");
		
		GeneralTexts.put("yourTurn", "C'est votre tour !");
		GeneralTexts.put("notYourTurn", "L’adversaire est en train de jouer…");
		
		GeneralTexts.put("youWin", "Vous avez gagné !");
		GeneralTexts.put("youLose", "Vous avez perdu !");
		
		GeneralTexts.put("putForPlay", "Appuyez sur 'Débuter la partie' pour commencer...");
	}
}
