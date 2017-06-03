package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.LinearLayout
import belka.us.androidtoggleswitch.R
import belka.us.androidtoggleswitch.R.attr.separatorColor


/**
 * Created by lorenzorigato on 02/06/2017.
 */


class BaseToggleSwitch : LinearLayout {

    private val ACTIVE_BACKGROUND_COLOR     = R.color.blue
    private val ACTIVE_BORDER_COLOR         = R.color.blue
    private val ACTIVE_TEXT_COLOR           = android.R.color.white

    private val BORDER_WIDTH                = 2
    private val CORNER_RADIUS_DP            = 4

    private val INACTIVE_BACKGROUND_COLOR   = R.color.gray_light
    private val INACTIVE_BORDER_COLOR       = R.color.gray_light
    private val INACTIVE_TEXT_COLOR         = R.color.gray
    private val SEPARATOR_COLOR             = R.color.gray_very_light

    private val TEXT_SIZE                   = 12f
    private val TOGGLE_WIDTH                = 64f


    var activeBackgroundColor : Int
    var activeBorderColor : Int
    var activeTextColor : Int

    var borderWidth : Float
    var cornerRadius : Float

    var inactiveBackgroundColor : Int
    var inactiveBorderColor : Int
    var inactiveTextColor : Int
    var textSize : Int
    var toggleWidth: Float


    constructor(context : Context, attrs: AttributeSet?) : super(context, attrs) {

        layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT)

        orientation = HORIZONTAL

        if (attrs != null) {

            val attributes = context.obtainStyledAttributes(attrs, R.styleable.ToggleSwitchOptions, 0, 0)

            try {

                activeBackgroundColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_activeBackgroundColor,
                        ContextCompat.getColor(context, ACTIVE_BACKGROUND_COLOR))

                activeBorderColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_activeBgColor,
                        ContextCompat.getColor(context, ACTIVE_BORDER_COLOR))

                activeTextColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_activeTextColor,
                        ContextCompat.getColor(context, ACTIVE_TEXT_COLOR))

                borderWidth = attributes.getDimensionPixelSize(
                        R.styleable.ToggleSwitchOptions_borderWidth,
                        Util.dp2px(context, BORDER_WIDTH.toFloat()).toInt()).toFloat()

                inactiveBackgroundColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_inactiveBackgroundColor,
                        ContextCompat.getColor(context, INACTIVE_BACKGROUND_COLOR))

                inactiveBorderColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_inactiveBorderColor,
                        ContextCompat.getColor(context, INACTIVE_BORDER_COLOR))

                inactiveTextColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_inactiveTextColor,
                        ContextCompat.getColor(context, INACTIVE_TEXT_COLOR))

                separatorColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_separatorColor,
                        ContextCompat.getColor(context, SEPARATOR_COLOR))

                textSize = attributes.getDimensionPixelSize(
                        R.styleable.ToggleSwitchOptions_android_textSize,
                        Util.dp2px(context, TEXT_SIZE).toInt())

                toggleWidth = attributes.getDimension(
                        R.styleable.ToggleSwitchOptions_toggleWidth,
                        Util.dp2px(getContext(), TOGGLE_WIDTH))

                cornerRadius = attributes.getDimensionPixelSize(
                        R.styleable.ToggleSwitchOptions_cornerRadius,
                        Util.dp2px(context, CORNER_RADIUS_DP.toFloat()).toInt()).toFloat()
            }
            finally {
                attributes.recycle()
            }
        }
        else {
            throw RuntimeException("AttributeSet is null!")
        }

    }

}