package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.util.AttributeSet

/**
 * Created by lorenzorigato on 08/06/2017.
 */

class ToggleSwitch(context: Context, attrs: AttributeSet?) : BaseToggleSwitch(context, attrs) {

    var currentToggleSwitch : ToggleSwitchButton? = null

    override fun onToggleSwitchClicked(toggleSwitchButton: ToggleSwitchButton) {
        currentToggleSwitch?.uncheck()
        currentToggleSwitch?.isClickable = true

        toggleSwitchButton.isClickable = false
        toggleSwitchButton.check()

        currentToggleSwitch = toggleSwitchButton

        manageSeparatorVisiblity()
    }

    fun getCheckedPosition() : Int {
        val checkedPositions = ArrayList<Int>()

        if (currentToggleSwitch == null) {
            return -1
        }
        else {
            return buttons.indexOf(currentToggleSwitch!!)
        }
    }

    fun setCheckedPosition(checkedPositions: Int) {
        for ((index, toggleSwitchButton) in buttons.withIndex()) {
            if (checkedPositions == index) {
                toggleSwitchButton.check()
            }
            else {
                toggleSwitchButton.uncheck()
            }
        }
    }
}