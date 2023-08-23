import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WordleGame {
    public Enum[] resulColorArray = new Enum[5];

    public Enum[] showResultColorArray(String inputString, String secretString) {

        List<Character> characterList = initCharacterList(secretString);
        validateInputString(inputString);

        for (int i = 0; i < secretString.length(); i++) {

            if (inputString.charAt(i) == secretString.charAt(i)) {
                characterList.remove(Character.valueOf(inputString.charAt(i)));
                resulColorArray[i] = Colors.GREEN;
            } else if (inputString.charAt(i) != secretString.charAt(i) && characterList.contains(inputString.charAt(i))) {
                characterList.remove(Character.valueOf(inputString.charAt(i)));
                resulColorArray[i] = Colors.YELLOW;
            } else {
                resulColorArray[i] = Colors.GRAY;
            }
        }
        return resulColorArray;
    }

    private List<Character> initCharacterList(String secretString) {

        return Arrays.stream(secretString.split(""))
                .map(s -> s.charAt(0))
                .collect(Collectors.toList());
    }

    private void validateInputString(String inputString) {

        if (Objects.isNull(inputString)) {
            throw new NullPointerException("InputString is null!");
        }
        if (inputString.length() != 5) {
            throw new IllegalArgumentException("InputString length must be 5!");
        }
    }

    public static void main(String[] args) {

        WordleGame game = new WordleGame();
        System.out.println(Arrays.toString(game.showResultColorArray("error", "rerun")));
    }
}
