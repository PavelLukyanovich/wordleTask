import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordleGameTest {
    WordleGame game = new WordleGame();

    @Test
    void whenInputStringLengthNotFive_shouldThrowIllegalArgumentException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> game.showResultColorArray("we", "found"));
    }

    @Test
    void whenInputStringIsNull_shouldThrowNPE() {

        Assertions.assertThrows(NullPointerException.class,() -> game.showResultColorArray(null, "helps"));
    }

    @Test
    void whenInputStringHasAllSameCharacters_resultColorArrayShouldBeGreen() {

        Enum[] expectedArray = new Enum[5];
        expectedArray[0] = Colors.GREEN;
        expectedArray[1] = Colors.GREEN;
        expectedArray[2] = Colors.GREEN;
        expectedArray[3] = Colors.GREEN;
        expectedArray[4] = Colors.GREEN;

        Assertions.assertArrayEquals(expectedArray, game.showResultColorArray("helps", "helps"));

    }
    @Test
    void whenInputStringHasDifferentFirstCharacterAndThisCharacterContainsInSecretString_resultArrayHasYellowFirstElement() {

        Assertions.assertEquals(Colors.YELLOW, game.showResultColorArray("crown", "socle")[0]);
    }

    @Test
    void whenInputStringHasNoSameCharacters_resultColorArrayShouldBeGray() {

        Enum[] expectedArray = new Enum[5];
        expectedArray[0] = Colors.GRAY;
        expectedArray[1] = Colors.GRAY;
        expectedArray[2] = Colors.GRAY;
        expectedArray[3] = Colors.GRAY;
        expectedArray[4] = Colors.GRAY;

        Assertions.assertArrayEquals(expectedArray, game.showResultColorArray("close", "funny"));
    }
}