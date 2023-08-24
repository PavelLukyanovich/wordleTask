import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordleGame {

    public Color[] showResultColorArray(String inputString, String secretString) {

        validateInputString(inputString, secretString);
        Color[] resulColorArray = new Color[5];
        List<Character> characterList = new ArrayList<>(secretString.chars().mapToObj(c -> (char) c).toList());

        for (int i = 0; i < secretString.length(); i++) {

            if (inputString.charAt(i) == secretString.charAt(i)) {
                characterList.remove(Character.valueOf(inputString.charAt(i)));
                resulColorArray[i] = Color.GREEN;
            }
        }
        for (int i = 0; i < secretString.length(); i++) {

            if (resulColorArray[i] != Color.GREEN
                    && inputString.charAt(i) != secretString.charAt(i)
                    && characterList.contains(inputString.charAt(i))) {
                characterList.remove(Character.valueOf(inputString.charAt(i)));
                resulColorArray[i] = Color.YELLOW;
            } else if (resulColorArray[i] != Color.GREEN) {
                resulColorArray[i] = Color.BLACK;
            }
        }
        return resulColorArray;
    }

    private void validateInputString(String inputString, String secretString) {

        if (inputString.length() != 5) {
            throw new IllegalArgumentException("InputString length must be 5!");
        }
        if (secretString.length() != 5) {
            throw new IllegalArgumentException("SecretString length must be 5!");
        }
    }

    public static void main(String[] args) {

        WordleGame game = new WordleGame();
        System.out.println(Arrays.toString(game.showResultColorArray("aaaaa", "aalaa")));
    }
}
