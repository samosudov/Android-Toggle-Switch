package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.util.AttributeSet
import java.util.*

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

    override fun onToggleSwitchClicked(button: ToggleSwitchButton) {
        val position = buttons.indexOf(button)
        if (button.isChecked) {
            button.uncheck()
            checkedPositions.remove(position)
        }
        else {
            button.check()
            checkedPositions.add(position)
        }
        manageSeparatorVisiblity()
        onChangeListener?.onMultipleToggleSwitchChanged(position, button.isChecked)
    }

    override fun onRedrawn() {
        setCheckedPositions(ArrayList(checkedPositions))
        manageSeparatorVisiblity()
    }
}