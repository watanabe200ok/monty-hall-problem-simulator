package watanabe200ok.mhp.player;

import java.text.MessageFormat;

/**
 * プレイヤーの基底クラスです。
 *
 */
public abstract class Player {
    private int win;
    private int lost;
    private int change;
    private boolean lastAnswer;

    /**
     * 最初の選択から変更するか否かの問いかけに対して返答します。
     *
     * @return 変更する場合はtrue
     */
    protected abstract boolean doAnswer();

    /**
     * 最初の選択から変更するか否かの問いかけに対して返答します。
     *
     * @return 変更する場合はtrue
     */
    public boolean answer() {
        lastAnswer = doAnswer();
        return lastAnswer;
    }

    /**
     * 勝利した時のコールバックメソッドです。
     */
    public void win() {
        win++;
        updateStatsWhenWin();
    }

    /**
     * 負けた時のコールバックメソッドです。
     */
    public void lost() {
        lost++;
    }

    private void updateStatsWhenWin() {
        if (lastAnswer) {
            change++;
        }
    }

    private double percent(int value) {
        int total = win + lost;
        return total == 0 ? 0 : (double) value / total;
    }

    private double changeRateWhenWon() {
        return win == 0 ? 0 : (double) change / win;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                "{0}:\t win% = {1,number,percent}\t lose% = {2,number,percent}\t changed%[@win] = {3,number,percent}",
                getClass().getSimpleName(), percent(win), percent(lost), changeRateWhenWon());
    }
}
