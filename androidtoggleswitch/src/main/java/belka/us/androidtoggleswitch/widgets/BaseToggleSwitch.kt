package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.LinearLayout
import belka.us.androidtoggleswitch.R
import java.util.*

abstract class BaseToggleSwitch : LinearLayout, ToggleSwitchButton.Listener {

    /*
       Default Values
     */

    companion object Default {
        @JvmStatic private val ACTIVE_BACKGROUND_COLOR     = R.color.blue
        @JvmStatic private val ACTIVE_BORDER_COLOR         = R.color.blue
        @JvmStatic private val ACTIVE_TEXT_COLOR           = android.R.color.white

        @JvmStatic private val BORDER_RADIUS_DP            = 4
        @JvmStatic private val BORDER_WIDTH                = 0

        @JvmStatic private val INACTIVE_BACKGROUND_COLOR   = R.color.gray_light
        @JvmStatic private val INACTIVE_BORDER_COLOR       = R.color.gray_light
        @JvmStatic private val INACTIVE_TEXT_COLOR         = R.color.gray

        @JvmStatic private val LAYOUT_HEIGHT               = LinearLayout.LayoutParams.WRAP_CONTENT
        @JvmStatic private val LAYOUT_WIDTH                = LinearLayout.LayoutParams.WRAP_CONTENT

        @JvmStatic private val SEPARATOR_COLOR             = R.color.gray_very_light
        @JvmStatic private val SEPARATOR_VISIBLE           = true

        @JvmStatic private val TEXT_SIZE                   = 16f

        @JvmStatic private val TOGGLE_DISTANCE             = 0f
        @JvmStatic private val TOGGLE_HEIGHT               = 38f
        @JvmStatic private val TOGGLE_WIDTH                = 72f
    }




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

    var textSize: Float

    var toggleMargin: Float
    var toggleHeight: Float
    var toggleWidth: Float

    var layoutHeight: Int
    var layoutWidth: Int

    var buttons = ArrayList<ToggleSwitchButton>()


    /*
       Constructors
     */

    constructor(context: Context) : super(context) {
        prepareLayout()

        activeBackgroundColor       = ContextCompat.getColor(context, ACTIVE_BACKGROUND_COLOR)
        activeBorderColor           = ContextCompat.getColor(context, ACTIVE_BORDER_COLOR)
        activeTextColor             = ContextCompat.getColor(context, ACTIVE_TEXT_COLOR)

        borderRadius                = dp2px(context, BORDER_RADIUS_DP.toFloat())
        borderWidth                 = dp2px(context, BORDER_WIDTH.toFloat())

        inactiveBackgroundColor     = ContextCompat.getColor(context, INACTIVE_BACKGROUND_COLOR)
        inactiveBorderColor         = ContextCompat.getColor(context, INACTIVE_BORDER_COLOR)
        inactiveTextColor           = ContextCompat.getColor(context, INACTIVE_TEXT_COLOR)

        separatorColor              = ContextCompat.getColor(context, SEPARATOR_COLOR)
        separatorVisible            = SEPARATOR_VISIBLE

        textSize                    = dp2px(context, TEXT_SIZE)

        toggleMargin              = dp2px(getContext(), TOGGLE_DISTANCE)
        toggleHeight                = dp2px(getContext(), TOGGLE_HEIGHT)
        toggleWidth                 = dp2px(getContext(), TOGGLE_WIDTH)

        layoutHeight                = LAYOUT_HEIGHT
        layoutWidth                 = LAYOUT_WIDTH
    }

    constructor(context: Context, attrs: AttributeSet?) : super (context, attrs) {
        prepareLayout()
        if (attrs != null) {

            val attributes = context.obtainStyledAttributes(attrs, R.styleable.BaseToggleSwitch, 0, 0)

            try {

                activeBackgroundColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_activeBackgroundColor,
                        ContextCompat.getColor(context, ACTIVE_BACKGROUND_COLOR))

                activeBorderColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_activeBorderColor,
                        ContextCompat.getColor(context, ACTIVE_BORDER_COLOR))

                activeTextColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_activeTextColor,
                        ContextCompat.getColor(context, ACTIVE_TEXT_COLOR))

                borderRadius = attributes.getDimensionPixelSize(
                        R.styleable.BaseToggleSwitch_borderRadius,
                        dp2px(context, BORDER_RADIUS_DP.toFloat()).toInt()).toFloat()

                borderWidth = attributes.getDimensionPixelSize(
                        R.styleable.BaseToggleSwitch_borderWidth,
                        dp2px(context, BORDER_WIDTH.toFloat()).toInt()).toFloat()

                inactiveBackgroundColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_inactiveBackgroundColor,
                        ContextCompat.getColor(context, INACTIVE_BACKGROUND_COLOR))

                inactiveBorderColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_inactiveBorderColor,
                        ContextCompat.getColor(context, INACTIVE_BORDER_COLOR))

                inactiveTextColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_inactiveTextColor,
                        ContextCompat.getColor(context, INACTIVE_TEXT_COLOR))

                separatorColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_separatorColor,
                        ContextCompat.getColor(context, SEPARATOR_COLOR))

                separatorVisible = attributes.getBoolean(
                        R.styleable.BaseToggleSwitch_separatorVisible,
                        SEPARATOR_VISIBLE)

                textSize = attributes.getDimensionPixelSize(
                        R.styleable.BaseToggleSwitch_android_textSize,
                        dp2px(context, TEXT_SIZE).toInt()).toFloat()

                toggleMargin = attributes.getDimension(
                        R.styleable.BaseToggleSwitch_toggleMargin,
                        dp2px(getContext(), TOGGLE_DISTANCE))

                toggleHeight = attributes.getDimension(
                        R.styleable.BaseToggleSwitch_toggleHeight,
                        dp2px(getContext(), TOGGLE_HEIGHT))

                toggleWidth = attributes.getDimension(
                        R.styleable.BaseToggleSwitch_toggleWidth,
                        dp2px(getContext(), TOGGLE_WIDTH))

                layoutHeight = attributes.getLayoutDimension(
                        R.styleable.BaseToggleSwitch_android_layout_height,
                        LAYOUT_HEIGHT)

                layoutWidth = attributes.getLayoutDimension(
                        R.styleable.BaseToggleSwitch_android_layout_width,
                        LAYOUT_WIDTH)

                val entries         = attributes.getTextArray(R.styleable.BaseToggleSwitch_android_entries)
                if (entries == null || entries.isEmpty()) {

                    val entriesList = ArrayList<String>()

                    val textToggleLeft  = attributes.getString(R.styleable.BaseToggleSwitch_textToggleLeft)
                    val textToggleRight = attributes.getString(R.styleable.BaseToggleSwitch_textToggleRight)

                    if (!TextUtils.isEmpty(textToggleLeft) && !TextUtils.isEmpty(textToggleRight)) {
                        entriesList.add(textToggleLeft)
                        val textToggleCenter  = attributes.getString(R.styleable.BaseToggleSwitch_textToggleCenter)
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
        Overrode instance methods
    */

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        for (button in buttons) {
            if (!isFullWidth()) {
                button.layoutParams.width = toggleWidth.toInt()
            }

            if (!isFullHeight()) {
                button.layoutParams.height = toggleHeight.toInt()
            }
        }
    }


    /*
        Public instance methods
    */

    fun getLabels() : List<String> {
        return buttons.map { it.getText() }
    }

    fun setEntries(stringArrayId: Int) {
        setEntries(resources.getStringArray(stringArrayId))
    }

    fun setEntries(entries: Array<String>) {
        val entriesList = ArrayList<String>()
        for (entry in entries) {
            entriesList.add(entry)
        }
        setEntries(entriesList)
    }

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
            var button = ToggleSwitchButton(context, entry, getPosition(index, entries),
                    this, activeBackgroundColor, activeBorderColor,
                    activeTextColor, borderRadius, borderWidth, inactiveBackgroundColor,
                    inactiveBorderColor, inactiveTextColor, textSize, separatorColor,
                    toggleMargin.toInt())
            buttons.add(button)
            addView(button)
        }
        manageSeparatorVisiblity()
    }

    public fun reDraw() {
        setEntries(buttons.map { it.getText() })
        onRedrawn()
    }



    /*
       Protected instance methods
     */

    protected abstract fun onRedrawn()

    protected fun manageSeparatorVisiblity() {
        for ((index, button) in buttons.withIndex()) {
            if (separatorVisible && index < buttons.size - 1 && !hasBorder() && !areToggleSeparated()) {
                button.setSeparatorVisibility(button.isChecked == buttons[index + 1].isChecked)
            }
            else {
                button.setSeparatorVisibility(false)
            }
        }
    }

    /*
       Private instance methods
     */

    private fun areToggleSeparated() : Boolean {
        return toggleMargin > 0
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

    private fun hasBorder() : Boolean {
        return borderWidth > 0f
    }

    private fun isFullHeight() : Boolean {
        return layoutHeight == LinearLayout.LayoutParams.MATCH_PARENT
    }

    private fun isFullWidth() : Boolean {
        return layoutWidth == LinearLayout.LayoutParams.MATCH_PARENT
    }

    private fun prepareLayout() {
        layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        orientation = HORIZONTAL
    }

}