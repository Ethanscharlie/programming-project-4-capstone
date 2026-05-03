package io.github.ethanscharlie;

/*
 * Team name: AEB
 * Team members: Alejandro Pasillas, Ethan Hadley, Ben Paul
 * Course and section: CS 2430-502
 * Project name: Programming Project 4 - Spring 2026
 * Primary author for this file: Ben Paul
 */

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimluatorTest {
    @Test
    void boardLoadsFortySquaresInOrder() throws Exception {
        // The assignment requires the classic 40-square Monopoly board in fixed order.
        var board = new Board();

        assertEquals(40, board.getAmountOfSquares());
        assertEquals("GO", board.getSquareAtLocation(0).name);
        assertEquals("Boardwalk", board.getSquareAtLocation(39).name);
    }

    @Test
    void boardMarksJustVisitingSquareAsJailAtIndexTen() throws Exception {
        // Monopoly's jail / just visiting square should be the board's jail destination.
        var board = new Board();

        assertEquals(10, board.getJailLocation());
        assertEquals(Square.SquareType.Jail, board.getSquareAtLocation(10).type);
    }

    @Test
    void goToJailSquareUsesDedicatedSquareType() throws Exception {
        // The "Go to Jail" space should be distinguishable from the jail destination space
        // so movement logic can redirect the player when square 30 is landed on.
        var board = new Board();

        assertEquals(Square.SquareType.GoToJail, board.getSquareAtLocation(30).type);
    }

    @Test
    void moveWrapsAroundBoardAndCountsLanding() throws Exception {
        // Movement must wrap past Boardwalk back to the beginning of the board, and the
        // landing counter should increment on the square where the player finishes.
        var board = new Board();
        var player = new Player(board, JailStrategy.ImmediateExit);

        player.location = 39;
        invokePrivate(player, "move", 2);

        assertEquals(1, player.location);
        assertEquals(1, board.getSquareAtLocation(1).landingCount);
    }

    @Test
    void landingOnGoToJailSendsPlayerToJail() throws Exception {
        // Landing on square 30 should immediately relocate the player to jail and set
        // the in-jail flag. This is one of the core movement edge cases in the project.
        var board = new Board();
        var player = new Player(board, JailStrategy.ImmediateExit);

        player.location = 29;
        invokePrivate(player, "move", 1);

        assertTrue(player.inJail);
        assertEquals(10, player.location);
    }

    @Test
    void goToJailPlacesPlayerInJailState() throws Exception {
        // This isolates the jail redirection helper itself so failures here are easier
        // to diagnose than if they only showed up through the broader movement flow.
        var board = new Board();
        var player = new Player(board, JailStrategy.ImmediateExit);

        invokePrivate(player, "goToJail");

        assertTrue(player.inJail);
        assertEquals(10, player.location);
    }

    @Test
    void immediateExitStrategyLeavesJailState() throws Exception {
        // Strategy A says the player should leave jail immediately on the next turn.
        // This test checks only the current jail-state transition, not movement after exit.
        var board = new Board();
        var player = new Player(board, JailStrategy.ImmediateExit);

        player.inJail = true;
        invokePrivate(player, "attemptToLeaveJail");

        assertFalse(player.inJail);
    }

    @Test
    void zeroTurnSimulationLeavesAllLandingCountsAtZero() throws Exception {
        // A simulation that runs zero turns should not mutate any landing counters.
        var board = Simulator.simulate(0, JailStrategy.ImmediateExit);

        for (int index = 0; index < board.getAmountOfSquares(); index++) {
            assertEquals(0, board.getSquareAtLocation(index).landingCount);
        }
    }

    @Test
    void rollDiceAlwaysReturnsAStandardDieValue() throws Exception {
        // The simulator uses standard six-sided dice, so repeated rolls must stay
        // inside the inclusive 1..6 range.
        for (int attempt = 0; attempt < 500; attempt++) {
            int roll = (int) invokePrivateStatic(Player.class, "rollDice");
            assertTrue(roll >= 1 && roll <= 6);
        }
    }

    @Test
    void threeConsecutiveDoublesSendPlayerToJail() throws Exception {
        // Part 2 requires: "on the third consecutive double in a single turn,
        // send the player to Jail." We set the double counter to 2 and then call
        // takeTurn() repeatedly until the random dice produce a double. The chance
        // of never rolling doubles in 200 attempts is (5/6)^200, so this will
        // reliably trigger the rule.
        var board = new Board();
        var player = new Player(board, JailStrategy.ImmediateExit);

        boolean jailTriggered = false;
        for (int attempt = 0; attempt < 200 && !jailTriggered; attempt++) {
            // Reset state before each attempt so earlier moves don't interfere.
            player.consecutiveDoubles = 2;
            player.inJail = false;
            player.location = 0;

            player.takeTurn();

            if (player.inJail && player.location == board.getJailLocation()) {
                jailTriggered = true;
            }
        }

        assertTrue(jailTriggered,
                "Rolling doubles with consecutiveDoubles already at 2 should trigger the jail rule");
    }

    @Test
    void goToJailRedirectShouldCountLandingOnJailNotGoToJailSquare() throws Exception {
        // The assignment says: "Incrementing the landing counter for the square
        // you end on (after all movement effects are resolved)." When the player
        // lands on Go to Jail (index 30) they are redirected to Jail (index 10),
        // so only Jail's counter should go up.
        var board = new Board();
        var player = new Player(board, JailStrategy.ImmediateExit);

        player.location = 29;
        invokePrivate(player, "move", 1);

        assertEquals(0, board.getSquareAtLocation(30).landingCount,
                "Go to Jail square should not record a landing since the player is redirected");
        assertEquals(1, board.getSquareAtLocation(10).landingCount,
                "Jail square should record the landing as the player's final destination");
    }

    @Test
    void tryForDoublesStrategyShouldNotImmediatelyLeaveJail() throws Exception {
        // Strategy B says the player should try to roll doubles for up to 3 turns
        // before paying to leave jail. Calling attemptToLeaveJail once without
        // rolling doubles should keep the player in jail.
        var board = new Board();
        var player = new Player(board, JailStrategy.TryForDoubles);

        player.inJail = true;
        player.location = board.getJailLocation();

        invokePrivate(player, "attemptToLeaveJail");

        // Under correct Strategy B behavior the player stays in jail when no
        // doubles are rolled. This test will fail against the current stub
        // implementation, which Ethan needs to complete.
        assertTrue(player.inJail,
                "TryForDoubles should keep the player in jail when doubles are not rolled");
    }

    private static Object invokePrivate(Object target, String methodName, Object... args) throws Exception {
        // Reflection lets the verification tests exercise narrow pieces of logic such as
        // move() and goToJail() without changing the production code's access modifiers.
        var parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = primitiveAwareType(args[i]);
        }

        Method method = target.getClass().getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(target, args);
    }

    private static Object invokePrivateStatic(Class<?> type, String methodName, Object... args) throws Exception {
        // Static helpers, such as the die roller, need a separate reflection path because
        // they are invoked without an object instance.
        var parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = primitiveAwareType(args[i]);
        }

        Method method = type.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(null, args);
    }

    private static Class<?> primitiveAwareType(Object value) {
        // Reflection signatures must use primitive parameter types for methods like move(int).
        if (value instanceof Integer) {
            return int.class;
        }
        return value.getClass();
    }
}
