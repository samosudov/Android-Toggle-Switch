package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import belka.us.androidtoggleswitch.R
import belka.us.androidtoggleswitch.databinding.ToggleSwitchButtonBinding

/**
 * Created by lorenzorigato on 01/06/2017.
 */

class ToggleSwitchButton : RelativeLayout {

    var activeBgColor : Int
    var activeBorderColor : Int
    var activeTextColor : Int

    var borderWidth : Float

    var inactiveBgColor : Int
    var inactiveBorderColor : Int
    var inactiveTextColor : Int

    var binding : ToggleSwitchButtonBinding

    constructor(context : Context, attrs: AttributeSet?) : super(context, attrs) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ToggleSwitchButtonBinding.inflate(inflater)

        activeBgColor       = ContextCompat.getColor(context, R.color.blue)
        activeBorderColor   = ContextCompat.getColor(context, R.color.blue)
        activeTextColor     = ContextCompat.getColor(context, android.R.color.white)

        borderWidth         = Util.dp2px(context, 2f)

        inactiveBgColor     = ContextCompat.getColor(context, R.color.gray_light)
        inactiveBorderColor = ContextCompat.getColor(context, R.color.gray_light)
        inactiveTextColor   = ContextCompat.getColor(context, R.color.gray)

    }

}