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


abstract class BaseToggleSwitch : LinearLayout, ToggleSwitchButton.Listener {

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
    private val SEPARATOR_VISIBLE           = true

    private val TEXT_SIZE                   = 12f
    private val TOGGLE_HEIGHT               = 38f
    private val TOGGLE_WIDTH                = 72f


    /*
       Instance Variables
     */

    var activeBackgroundColor: Int
    var activeBorderColor: Int
    var activeTextColor: Int

    var borderRadius: Float
    var borderWidth: Float

    var inactiveBackgroundColor: Int
    var inactiveBorderColor: Int
    var inactiveTextColor: Int

    var separatorColor: Int
    var separatorVisible: Boolean

    var textSize: Int
    var toggleHeight: Float
    var toggleWidth: Float

    var layoutHeight: Int
    var layoutWidth: Int

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
                        dp2px(context, BORDER_WIDTH.toFloat()).toInt()).toFloat()

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

                separatorVisible = attributes.getBoolean(
                        R.styleable.ToggleSwitchOptions_separatorVisible,
                        SEPARATOR_VISIBLE)

                textSize = attributes.getDimensionPixelSize(
                        R.styleable.ToggleSwitchOptions_android_textSize,
                        dp2px(context, TEXT_SIZE).toInt())

                toggleHeight = attributes.getDimension(
                        R.styleable.ToggleSwitchOptions_toggleHeight,
                        dp2px(getContext(), TOGGLE_HEIGHT))

                toggleWidth = attributes.getDimension(
                        R.styleable.ToggleSwitchOptions_toggleWidth,
                        dp2px(getContext(), TOGGLE_WIDTH))

                borderRadius = attributes.getDimensionPixelSize(
                        R.styleable.ToggleSwitchOptions_cornerRadius,
                        dp2px(context, BORDER_RADIUS_DP.toFloat()).toInt()).toFloat()

                layoutHeight = attributes.getLayoutDimension(
                        R.styleable.ToggleSwitchOptions_android_layout_height,
                        LinearLayout.LayoutParams.WRAP_CONTENT)

                layoutWidth = attributes.getLayoutDimension(
                        R.styleable.ToggleSwitchOptions_android_layout_width,
                        LinearLayout.LayoutParams.WRAP_CONTENT)

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

        manageSeparatorVisiblity()
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (!isCustomHeightSet()) {
            layoutParams.height = dp2px(context, TOGGLE_HEIGHT).toInt()
        }
        if (!isFullWidth()) {
            for (button in buttons) {
                button.layoutParams.width = toggleWidth.toInt()
            }
        }
    }


    fun manageSeparatorVisiblity() {
        for ((index, button) in buttons.withIndex()) {
            if (separatorVisible && index < buttons.size - 1) {
                button.setSeparatorVisibility(button.isChecked == buttons[index + 1].isChecked)
            }
            else {
                button.setSeparatorVisibility(false)
            }
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

    fun setEntries(entries : List<String>) {
        removeAllViews()
        buttons.clear()

        for ((index, entry) in entries.withIndex()) {

            val toggleWidth = if (isFullWidth()) 0 else this.toggleWidth.toInt()
            var button = ToggleSwitchButton(context, entry, getPosition(index, entries),
                    this, activeBackgroundColor, activeBorderColor,
                    activeTextColor, borderRadius, borderWidth, inactiveBackgroundColor,
                    inactiveBorderColor, inactiveTextColor, textSize, separatorColor, toggleWidth)

            buttons.add(button)
            addView(button)
        }
    }

    public fun reDraw() {
        setEntries(buttons.map { it.getText() })
        onRedrawn()
    }


    /*
       Protected instance methods
     */

    protected abstract fun onRedrawn()

    /*
       Private instance methods
     */

    private fun isFullWidth() : Boolean {
        return layoutWidth == LinearLayout.LayoutParams.MATCH_PARENT
    }

    private fun isCustomHeightSet() : Boolean {
        return layoutHeight > 0
                && layoutWidth != LinearLayout.LayoutParams.MATCH_PARENT
                && layoutWidth != LinearLayout.LayoutParams.WRAP_CONTENT
    }


    private fun getPosition(index : Int, entries : List<String>) : ToggleSwitchButton.Position {
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