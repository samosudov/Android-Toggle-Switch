package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.util.AttributeSet

/**
 * Created by lorenzorigato on 12/06/2017.
 */


class MultipleToggleSwitch(context: Context, attrs: AttributeSet?) : BaseToggleSwitch(context, attrs) {

    public interface OnChangeListener {
        fun onMultipleToggleSwitchChanged(position: Int, checked: Boolean)
    }

    var onChangeListener: OnChangeListener? = null
    private var checkedPositions: ArrayList<Int> = ArrayList()

    fun getCheckedPositions() : List<Int> {
        return checkedPositions
    }

    fun setCheckedPositions(checkedPositions: List<Int>) {
        this.checkedPositions.clear()
        this.checkedPositions.addAll(checkedPositions)
        for ((index, toggleSwitchButton) in buttons.withIndex()) {
            if (checkedPositions.contains(index)) {
                toggleSwitchButton.check()
            }
            else {
                toggleSwitchButton.uncheck()
            }
        }
    }

    override fun onToggleSwitchClicked(toggleSwitchButton: ToggleSwitchButton) {
        val position = buttons.indexOf(toggleSwitchButton)
        if (toggleSwitchButton.isChecked) {
            toggleSwitchButton.uncheck()
            checkedPositions.remove(position)
        }
        else {
            toggleSwitchButton.check()
            checkedPositions.add(position)
        }
        manageSeparatorVisiblity()
        onChangeListener?.onMultipleToggleSwitchChanged(position, toggleSwitchButton.isChecked)
    }

    override fun onRedrawn() {
        setCheckedPositions(ArrayList(checkedPositions))
        manageSeparatorVisiblity()
    }
}