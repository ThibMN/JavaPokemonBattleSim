package models;

import java.util.Arrays;
import java.util.List;

public class PokemonSetup {
    public static Pokemon createPokemon(String name, int hp, int attack, int specialAttack,
                                        int defense, int specialDefense, int speed,
                                        Type[] types, Move move1, Move move2, Move move3, Move move4) {
        List<Move> moves = Arrays.asList(move1, move2, move3, move4);
        return new Pokemon(name, hp, attack, specialAttack, defense, specialDefense, speed, types, moves);
    }
}