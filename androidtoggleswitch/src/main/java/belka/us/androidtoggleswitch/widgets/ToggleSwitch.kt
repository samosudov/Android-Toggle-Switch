package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.util.AttributeSet

/**
 * Created by lorenzorigato on 08/06/2017.
 */

class ToggleSwitch(context: Context, attrs: AttributeSet?) : BaseToggleSwitch(context, attrs) {

    public interface OnChangeListener {
        fun onToggleSwitchChanged(position: Int)
    }

    var currentToggleSwitch : ToggleSwitchButton? = null
    var onChangeListener : OnChangeListener? = null

    override fun onToggleSwitchClicked(toggleSwitchButton: ToggleSwitchButton) {
        currentToggleSwitch?.uncheck()
        currentToggleSwitch?.isClickable = true

        toggleSwitchButton.isClickable = false
        toggleSwitchButton.check()

        currentToggleSwitch = toggleSwitchButton

        manageSeparatorVisiblity()

        onChangeListener?.onToggleSwitchChanged(buttons.indexOf(toggleSwitchButton))
    }

    fun getCheckedPosition() : Int {
        return if (currentToggleSwitch == null) -1 else buttons.indexOf(currentToggleSwitch!!)
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