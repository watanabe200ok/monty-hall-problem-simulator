package watanabe200ok.mhp;

import java.text.MessageFormat;
import java.util.List;

import watanabe200ok.mhp.player.ChangeMan;
import watanabe200ok.mhp.player.KeepMan;
import watanabe200ok.mhp.player.Player;
import watanabe200ok.mhp.player.RandomMan;
import watanabe200ok.mhp.player.SwitchMan;

public class MontyHallProblemSimulator {
    /**
     * ドアの数の既定値
     */
    private static final int DEFAULT_NUMBER_OF_DOORS = 3;
    /**
     * ゲーム回数の既定値
     */
    private static final int DEFAULT_NUMBER_OF_GAMES = 10000;

    /**
     * シミュレーターを実行します。
     *
     * @param numberOfDoors ドアの数
     * @param numberOfGames ゲーム回数
     */
    public void execute(final int numberOfDoors, final int numberOfGames) {
        final Doors doors = new Doors(numberOfDoors);
        final List<Player> players = List.of(
                new KeepMan(),
                new ChangeMan(),
                new RandomMan(),
                new SwitchMan());

        for (int count = 0; count < numberOfGames; count++) {
            players.forEach(player -> {
                doors.ready();

                // プレイヤーが当たりのドアをランダムで選択する。この時点ではドアを開けない
                doors.selectAsRandom();

                // 司会者がハズレのドアを1つ開ける
                doors.openBadDoor();

                // プレイヤーが回答する
                final boolean changed = player.answer();

                // 最初の選択から変更する場合は、残りからランダムで選択する
                if (changed) {
                    doors.cancel();
                    doors.selectAsRandom(); // ドアの数が3の場合は選択余地はない
                }

                // 結果判定
                if (doors.isStrike()) {
                    player.win();
                } else {
                    player.lost();
                }
            });
        }

        printResult(numberOfDoors, numberOfGames, players);
    }

    private void printResult(
            final int numberOfDoors,
            final int numberOfGames,
            final List<Player> players) {
        System.out.println(MessageFormat.format("{0} doors - {1} games", numberOfDoors, numberOfGames));
        players.forEach(System.out::println);
    }

    /**
     * @param args ドアの数 ゲーム回数
     */
    public static void main(final String[] args) {
        final int numberOfDoors = intParameter(args, 1, DEFAULT_NUMBER_OF_DOORS);
        final int numberOfGames = intParameter(args, 0, DEFAULT_NUMBER_OF_GAMES);
        final MontyHallProblemSimulator simulator = new MontyHallProblemSimulator();
        simulator.execute(numberOfDoors, numberOfGames);
    }

    private static int intParameter(final String[] args, final int index, final int defaultValue) {
        return index < args.length ? Integer.parseInt(args[index]) : defaultValue;
    }

}
