package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class CombatTest {
    private Pokemon fastPokemon;
    private Pokemon slowPokemon;
    private Pokemon averagePokemon;
    private List<Move> moves;

    @BeforeEach
    void setUp() {
        Move testMove = new Move("Test Move", 50, Type.PSYCHIC, true, 0.0, null);
        moves = List.of(testMove);

        // Create Pokémon with different speeds
        fastPokemon = new Pokemon("FastMon", 100, 50, 50, 50, 50, 80,
                new Type[]{Type.PSYCHIC}, moves);    // Speed 80
        slowPokemon = new Pokemon("SlowMon", 100, 50, 50, 50, 50, 30,
                new Type[]{Type.PSYCHIC}, moves);    // Speed 30
        averagePokemon = new Pokemon("AverageMon", 100, 50, 50, 50, 50, 50,
                new Type[]{Type.PSYCHIC}, moves);    // Speed 50
    }

    @Test
    void testConstructorWithInvalidTeamSize() {
        List<Pokemon> smallTeam = Arrays.asList(fastPokemon, slowPokemon);
        assertThrows(IllegalArgumentException.class, () ->
                new Combat(smallTeam, Arrays.asList(fastPokemon, slowPokemon, averagePokemon)));
    }

    @Test
    void testSwitchToFaintedPokemon() {
        List<Pokemon> playerTeam = Arrays.asList(fastPokemon, slowPokemon, averagePokemon);
        List<Pokemon> opponentTeam = Arrays.asList(
                createPokemonCopy(slowPokemon),
                createPokemonCopy(averagePokemon),
                createPokemonCopy(fastPokemon)
        );

        Combat combat = new Combat(playerTeam, opponentTeam);
        slowPokemon.setHp(0); // Make slowPokemon fainted

        combat.switchPlayerPokemon(1); // Try to switch to fainted Pokémon
        assertEquals(fastPokemon, combat.getActivePlayerPokemon()); // Should still be the original Pokémon
    }

    @Test
    void testSpeedBasedSwitching() {
        List<Pokemon> playerTeam = Arrays.asList(slowPokemon, fastPokemon, averagePokemon);
        List<Pokemon> opponentTeam = Arrays.asList(
                createPokemonCopy(averagePokemon),
                createPokemonCopy(slowPokemon),
                createPokemonCopy(fastPokemon)
        );

        Combat combat = new Combat(playerTeam, opponentTeam);
        int initialHp = fastPokemon.getHp();

        combat.switchPlayerPokemon(1); // Switch to fastPokemon

        // Since fastPokemon is faster than averagePokemon, it should take the hit
        assertTrue(fastPokemon.getHp() < initialHp);
    }

    @Test
    void testBattleEnd() {
        Pokemon weakPokemon1 = new Pokemon("Weak1", 1, 10, 10, 10, 10, 10,
                new Type[]{Type.PSYCHIC}, moves);
        Pokemon weakPokemon2 = new Pokemon("Weak2", 1, 10, 10, 10, 10, 10,
                new Type[]{Type.PSYCHIC}, moves);
        Pokemon weakPokemon3 = new Pokemon("Weak3", 1, 10, 10, 10, 10, 10,
                new Type[]{Type.PSYCHIC}, moves);

        List<Pokemon> weakTeam = Arrays.asList(weakPokemon1, weakPokemon2, weakPokemon3);
        List<Pokemon> strongTeam = Arrays.asList(fastPokemon, slowPokemon, averagePokemon);

        Combat combat = new Combat(weakTeam, strongTeam);
        combat.startBattle();

        assertTrue(weakPokemon1.isFainted());
        assertTrue(weakPokemon2.isFainted());
        assertTrue(weakPokemon3.isFainted());
    }

    // Helper method to create a copy of a Pokémon
    private Pokemon createPokemonCopy(Pokemon original) {
        return new Pokemon(
                original.getName(),
                original.getMaxHp(),
                original.getAttack(),
                original.getSpecialAttack(),
                original.getDefense(),
                original.getSpecialDefense(),
                original.getSpeed(),
                original.getTypes(),
                original.getMoves()
        );
    }
}