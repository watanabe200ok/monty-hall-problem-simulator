package watanabe200ok.mhp.player;

/**
 * 最初選択から常に変更しないプレイヤーです。
 *
 */
public class KeepMan extends Player {

    @Override
    public boolean doAnswer() {
        return false;
    }

}
