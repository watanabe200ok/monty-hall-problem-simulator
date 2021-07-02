package watanabe200ok.mhp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * ドアを集約するモデルを表現します。
 *
 */
public class Doors {
    /**
     * 最小のドアの数
     */
    private static final int MIN_NUMBER_OF_DOORS = 3;
    private List<Door> doors;

    /**
     * @param numberOfDoors ドアの数
     */
    Doors(final int numberOfDoors) {
        if (numberOfDoors < MIN_NUMBER_OF_DOORS) {
            throw new IllegalArgumentException("ドアの数は" + MIN_NUMBER_OF_DOORS + "以上を入力して下さい。");
        }
        doors = new ArrayList<>(numberOfDoors);
        for (int count = 0; count < numberOfDoors; count++) {
            doors.add(new Door());
        }
    }

    /**
     * 全てドアの状態をリセットし、1つを当たりとして設定します。
     */
    public void ready() {
        doors.forEach(Door::reset);
        doors.stream()
            .findFirst().get()
            .asStrike();
    }

    /**
     * 1度も選択されたことがない、かつまだ開いていないドアの中からランダムで1つ選択します。
     */
    public void selectAsRandom() {
        Collections.shuffle(doors);
        findFirst(door -> door.isEnabled())
            .select();
    }

    /**
     * まだ開いていないドアの中からランダムではずれのドアを開けます。
     */
    public void openBadDoor() {
        Collections.shuffle(doors);
        findFirst(door -> door.isEnabled() && !door.isStrike())
            .open();
    }

    /**
     * ドアの選択を解除します。
     */
    public void cancel() {
        doors.stream()
            .filter(Door::isSelected)
            .forEach(Door::cancel);
    }

    /**
     * 選択されたドアが当たりであるか否かを判定します。
     *
     * @return 選択されたドアが当たりである場合はtrue
     */
    public boolean isStrike() {
        final int CORRECT_SELECTED_SIZE = 1;
        final Set<Door> selected = doors.stream()
                .filter(Door::isSelected)
                .collect(Collectors.toSet());
        if (selected.size() != CORRECT_SELECTED_SIZE) {
            throw new RuntimeException(
                    "ドアが1だけ選択された状態にして下さい。現在の選択されたドアの数: " + selected.size());
        }
        return selected.stream()
                .findFirst().get()
                .isStrike();
    }

    Door findFirst(Predicate<Door> test) {
        return doors.stream()
                .filter(test)
                .findFirst().get();
    }

}
