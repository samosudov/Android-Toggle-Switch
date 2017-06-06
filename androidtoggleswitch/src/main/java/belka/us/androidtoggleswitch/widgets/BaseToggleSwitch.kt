package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.LinearLayout
import belka.us.androidtoggleswitch.R




/**
 * Created by lorenzorigato on 02/06/2017.
 */


class BaseToggleSwitch : LinearLayout {

    /*
       Default Values
     */

    private val ACTIVE_BACKGROUND_COLOR     = R.color.blue
    private val ACTIVE_BORDER_COLOR         = R.color.blue
    private val ACTIVE_TEXT_COLOR           = android.R.color.white

    private val BORDER_RADIUS_DP            = 4
    private val BORDER_WIDTH                = 2

    private val INACTIVE_BACKGROUND_COLOR   = R.color.gray_light
    private val INACTIVE_BORDER_COLOR       = R.color.gray_light
    private val INACTIVE_TEXT_COLOR         = R.color.gray
    private val SEPARATOR_COLOR             = R.color.gray_very_light

    private val TEXT_SIZE                   = 12f
    private val TOGGLE_WIDTH                = 64f


    /*
       Instance Variables
     */

    var activeBackgroundColor : Int
    var activeBorderColor : Int
    var activeTextColor : Int

    var borderRadius: Float
    var borderWidth : Float

    var inactiveBackgroundColor : Int
    var inactiveBorderColor : Int
    var inactiveTextColor : Int

    var separatorColor : Int

    var textSize : Int
    var toggleWidth: Float

    var buttons = ArrayList<ToggleSwitchButton>()


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
                        R.styleable.ToggleSwitchOptions_activeBorderColor,
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

                borderRadius = attributes.getDimensionPixelSize(
                        R.styleable.ToggleSwitchOptions_cornerRadius,
                        Util.dp2px(context, BORDER_RADIUS_DP.toFloat()).toInt()).toFloat()


                val entries         = attributes.getTextArray(R.styleable.ToggleSwitchOptions_android_entries)
                if (entries == null || entries.isEmpty()) {

                    val entriesList = ArrayList<String>()

                    val textToggleLeft  = attributes.getString(R.styleable.ToggleSwitchOptions_textToggleLeft)
                    val textToggleRight = attributes.getString(R.styleable.ToggleSwitchOptions_textToggleRight)

                    if (!TextUtils.isEmpty(textToggleLeft) && !TextUtils.isEmpty(textToggleRight)) {
                        entriesList.add(textToggleLeft)
                        val textToggleCenter  = attributes.getString(R.styleable.ToggleSwitchOptions_textToggleCenter)
                        if (!TextUtils.isEmpty(textToggleCenter)) {
                            entriesList.add(textToggleCenter)
                        }
                        entriesList.add(textToggleRight)
                        setEntries(entriesList)
                    }
                }
                else {
                    setEntries(entries)
                }

            }
            finally {
                attributes.recycle()
            }
        }
        else {
            throw RuntimeException("AttributeSet is null!")
        }

    }


    /*
       Public instance methods
     */

    fun setEntries(entries : Array<CharSequence>) {
        val entriesList = ArrayList<String>()
        for (entry in entries) {
            entriesList.add(entry.toString())
        }
        setEntries(entriesList)
    }

    fun setEntries(entries : ArrayList<String>) {
        removeAllViews()
        buttons.clear()

        for ((index, entry) in entries.withIndex()) {

            var button = ToggleSwitchButton(context, entry, getPosition(index, entries),
                    activeBackgroundColor, activeBorderColor,
                    activeTextColor, borderRadius, borderWidth, inactiveBackgroundColor,
                    inactiveBorderColor, inactiveTextColor)
            buttons.add(button)
            addView(button)
        }
    }


    /*
       Private instance methods
     */

    private fun getPosition(index : Int, entries : ArrayList<String>) : ToggleSwitchButton.Position {
        if (index == 0) {
            return ToggleSwitchButton.Position.LEFT
        }
        else if (index == entries.size - 1) {
            return ToggleSwitchButton.Position.RIGHT
        }
        else {
            return ToggleSwitchButton.Position.CENTER
        }
    }

}