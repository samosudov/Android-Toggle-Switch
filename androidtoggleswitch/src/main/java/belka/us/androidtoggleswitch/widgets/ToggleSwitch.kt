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

    var checkedPosition: Int? = null
    var onChangeListener : OnChangeListener? = null


    override fun onRedrawn() {
        if (checkedPosition != null) {
            val currentToggleSwitch = buttons[checkedPosition!!]
            currentToggleSwitch?.check()
            currentToggleSwitch?.isClickable = false
        }
        manageSeparatorVisiblity()
    }

    override fun onToggleSwitchClicked(toggleSwitchButton: ToggleSwitchButton) {

        if (checkedPosition != null) {
            val currentToggleSwitch = buttons[checkedPosition!!]
            currentToggleSwitch?.uncheck()
            currentToggleSwitch?.isClickable = true
        }

        checkedPosition = buttons.indexOf(toggleSwitchButton)

        toggleSwitchButton.isClickable = false

        manageSeparatorVisiblity()

        onChangeListener?.onToggleSwitchChanged(checkedPosition!!)
    }

    fun getCheckedPosition() : Int {
        return checkedPosition ?: -1
    }

    fun setCheckedPosition(checkedPosition: Int) {
        this.checkedPosition = checkedPosition
        for ((index, toggleSwitchButton) in buttons.withIndex()) {
            if (checkedPosition == index) {
                toggleSwitchButton.check()
            }
            else {
                toggleSwitchButton.uncheck()
            }
        }
    }
}