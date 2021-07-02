package watanabe200ok.mhp.player;

import java.util.Random;

/**
 * 最初の選択から変更するか否かをランダムで決めるプレイヤーです。
 *
 */
public class RandomMan extends Player {
    private final static Random RANDOM = new Random();

    @Override
    public boolean doAnswer() {
        // 0 or 1
        return RANDOM.nextInt(2) == 0;
    }
}
