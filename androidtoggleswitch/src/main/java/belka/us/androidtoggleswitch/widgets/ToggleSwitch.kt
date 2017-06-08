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
    }
}