package watanabe200ok.mhp;

/**
 * ドアを表現するモデルです。
 */
public class Door {
    private boolean isSelected;
    private boolean isOpend;
    private boolean isStrike;
    private boolean isDisabled;

    /**
     * このドアの状態をリセットします。
     */
    public void reset() {
        isSelected = false;
        isOpend = false;
        isStrike = false;
        isDisabled = false;
    }

    /**
     * @return このドアが選択された状態である場合はtrue
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * このドアを選択します。
     */
    public void select() {
        if (isDisabled) {
            throw new RuntimeException("このドアは選択できません。既に選択されたことがあるか、開いています。");
        }
        this.isSelected = true;
        this.isDisabled = true;
    }

    /**
     * このドアの選択を解除します。
     */
    public void cancel() {
        this.isSelected = false;
    }

    /**
     * @return このドアが開いている場合はtrue
     */
    public boolean isOpend() {
        return isOpend;
    }

    /**
     * このドアを開きます。
     */
    public void open() {
        this.isOpend = true;
        this.isDisabled = true;
    }

    /**
     * @return このドアが当たりである場合はtrue
     */
    public boolean isStrike() {
        return isStrike;
    }

    /**
     * このドアを当たりとして設定します。
     */
    public void asStrike() {
        this.isStrike = true;
    }

    /**
     * このドアを選択できるか否かを判定します。
     *
     * このドアが既に選択されたことがあるか、開いている場合はfalse
     *
     * @return このドアを選択できる場合はtrue
     */
    public boolean isEnabled() {
        return !isDisabled;
    }

}
