import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class WordleGameTest {
    WordleGame game = new WordleGame();

    @Test
    void whenInputStringLengthNotFive_shouldThrowIllegalArgumentException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> game.showResultColorArray("we", "found"));
    }

    @Test
    void whenInputStringIsNull_shouldThrowNPE() {

        Assertions.assertThrows(NullPointerException.class, () -> game.showResultColorArray(null, "helps"));
    }

    @Test
    void whenInputStringHasAllSameCharacters_resultColorArrayShouldBeGreen() {

        Color[] expectedArray = new Color[5];
        Arrays.fill(expectedArray, Color.GREEN);

        assertArrayEquals(expectedArray, game.showResultColorArray("helps", "helps"));
    }

    @Test
    void whenInputStringHasDifferentFirstCharacterAndThisCharacterContainsInSecretString_resultArrayHasYellowFirstElement() {

        Assertions.assertEquals(Color.YELLOW, game.showResultColorArray("crown", "socle")[0]);
    }

    @Test
    void whenInputStringHasNoSameCharacters_resultColorArrayShouldBeGray() {

        Color[] expectedArray = new Color[5];
        Arrays.fill(expectedArray, Color.BLACK);

        assertArrayEquals(expectedArray, game.showResultColorArray("close", "funny"));
    }

    @ParameterizedTest
    @MethodSource("mapProvider")
    void test_wordle(String attempt, String guess, Color[] expectedResult) {
        //when
        var actualResult = new WordleGame().showResultColorArray(attempt, guess);
        //then
        System.out.println(Arrays.toString(actualResult));
        assertArrayEquals(expectedResult, actualResult);
    }

//    @ParameterizedTest
//    @ValueSource(strings = {"error", "rerun","asdsd"})
//    public void test_wordle(String inputString) {
//        //when
//        var actualResult = new WordleGame().showResultColorArray(inputString, "rerun");
//        //then
//        System.out.println(Arrays.toString(actualResult));
//    }

    static Stream<Arguments> mapProvider() {
        return Stream.of(
                Arguments.of("rules", "rebus", new Color[]{Color.GREEN, Color.YELLOW, Color.BLACK, Color.YELLOW, Color.GREEN}),
                Arguments.of("divan", "nadiv", new Color[]{Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW}),
                Arguments.of("aaaaa", "world", new Color[]{Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK}),

                Arguments.of("aaaaa", "aalaa", new Color[]{Color.GREEN, Color.GREEN, Color.BLACK, Color.GREEN, Color.GREEN}),
                Arguments.of("aaaal", "flaaa", new Color[]{Color.YELLOW, Color.BLACK, Color.GREEN, Color.GREEN, Color.YELLOW}),

                Arguments.of("qwert", "kloij", new Color[]{Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK}),
                Arguments.of("asdfg", "kloij", new Color[]{Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK}),
                Arguments.of("zxcvb", "kloij", new Color[]{Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK}),
                Arguments.of("hello", "hello", new Color[]{Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN}),
                Arguments.of("world", "world", new Color[]{Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN}),
                Arguments.of("javac", "javac", new Color[]{Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN}));
    }
}