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


    fun getCheckedPositions() : List<Int> {
        val checkedPositions = ArrayList<Int>()

        for ((index, toggleSwitchButton) in buttons.withIndex()) {
            if (toggleSwitchButton.isChecked) {
                checkedPositions.add(index)
            }
        }

        return checkedPositions
    }

    fun setCheckedPositions(checkedPositions: List<Int>) {
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
        if (toggleSwitchButton.isChecked) {
            toggleSwitchButton.uncheck()
        }
        else {
            toggleSwitchButton.check()
        }

        val position = buttons.indexOf(toggleSwitchButton)

        manageSeparatorVisiblity()

        onChangeListener?.onMultipleToggleSwitchChanged(position, toggleSwitchButton.isChecked)
    }
}