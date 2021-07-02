package watanabe200ok.mhp.player;

/**
 * 最初の選択から変更するか否かを規則正しく交互に決めるプレイヤーです。
 *
 */
public class SwitchMan extends Player {

    private boolean answer;

    @Override
    public boolean doAnswer() {
        return answer =! answer;
    }

}
